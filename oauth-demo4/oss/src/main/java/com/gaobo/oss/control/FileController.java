package com.gaobo.oss.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaobo.common.entity.Result;
import com.gaobo.oss.model.UploadFile;
import com.gaobo.oss.model.UploadedResult;
import com.gaobo.oss.service.FileStoreService;
import com.gaobo.oss.service.FileUploadService;
import com.gaobo.oss.utils.FileOpUtils;
import com.gaobo.oss.utils.ServletUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 文件的操作
 */

@RestController
@RequestMapping({ "/files/**" })
@Slf4j
public class FileController {

    @Autowired
    private FileStoreService fileStoreService;

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 单文件的上传，我为了防止相同名字的文件上传，覆盖了过去的文件，所以在文件数据库表，先搜索了一下，然后再上传。
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(consumes = { "multipart/form-data" })
    public Result post(@RequestParam("file") MultipartFile file) throws Exception {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setPaths(this.getFilePaths());
        uploadFile.getPaths().add(file.getOriginalFilename());
        uploadFile.setContent(file.getBytes());
        String url = StringUtils.join(uploadFile.getPaths(), "/");
        Integer count = fileUploadService.countByUrl(url);
        if(count>0){
            return Result.error("该图片的地址已经存在");
        }
        Integer id = fileStoreService.save(uploadFile);
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        return Result.ok(map);
    }

    /**
     * 这是以流的形式put方法上传单文件
     * @param filename
     * @param body
     * @return
     * @throws Exception
     */

    @PutMapping( value= { "/{filename:.*}" } , consumes = { "application/octet-stream" })
    public Result put(@PathVariable("filename") String filename, @RequestBody byte[] body) throws Exception {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setPaths(this.getFilePaths());
        uploadFile.setContent(body);
        String url = StringUtils.join(uploadFile.getPaths(), "/");
        Integer count = fileUploadService.countByUrl(url);
        if(count>0){
            return Result.error("该图片的地址已经存在");
        }
        Integer id = fileStoreService.save(uploadFile);
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        return Result.ok(map);
    }

    /**
     * 上传多文件，没有详细测试
     * @param files
     * @return
     * @throws Exception
     */
    @PostMapping(params = { "upload" }, consumes = { "multipart/form-data" })
    public Result postMultiple(@RequestParam("file") List<MultipartFile> files) throws Exception {
        UploadedResult result = new UploadedResult();
        Iterator<MultipartFile> mulFile = files.iterator();
        while (mulFile.hasNext()) {
            MultipartFile file = mulFile.next();
            UploadFile uploadFile = new UploadFile();
            uploadFile.setPaths(this.getFilePaths());
            uploadFile.getPaths().add(file.getOriginalFilename());
            uploadFile.setContent(file.getBytes());
            String url = StringUtils.join(uploadFile.getPaths(), "/");
            try {
                this.fileStoreService.save(uploadFile);
                UploadedResult.Uploaded uploaded = new UploadedResult.Uploaded();
                uploaded.setLocation(url);
                result.getUploaded().add(uploaded);

            } catch (Exception e) {
                log.error("上传文件时出现异常:",e);
                UploadedResult.NotUploaded notUploaded = new UploadedResult.NotUploaded();
                notUploaded.setLocation(url);
                result.getNotUploaded().add(notUploaded);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(result));
        return null;
    }

    /**
     * 获取文件，返回的结果就是这个文件实体
     * @param filename
     * @return
     * @throws Exception
     */
    @GetMapping({ "/{filename:.*}" })
	public ResponseEntity<Resource> get(@PathVariable("filename") String filename) throws Exception {
		UploadFile uploadFile = new UploadFile();
		uploadFile.setPaths(this.getFilePaths());
		File file = this.fileStoreService.get(uploadFile);
		String contentType = FileOpUtils.getContentType(file.getName());
		Resource body = new FileSystemResource(file);
		System.out.println(MediaType.valueOf(contentType));
		ResponseEntity<Resource> response = (ResponseEntity.ok().header("Content-Disposition",
				new String[] { "inline;filename=" + file.getName() })).contentType(MediaType.valueOf(contentType))
						.body(body);
		return response;
	}

    /**
     * 删除文件
     * @param filename
     * @return
     * @throws Exception
     */
    @DeleteMapping({ "/{filename:.*}" })
	public Result delete(@PathVariable("filename") String filename) throws Exception {
        System.out.println(filename);
        System.out.println(this.getFilePaths());
		UploadFile uploadFile = new UploadFile();
		uploadFile.setPaths(this.getFilePaths());
		Boolean flag = this.fileStoreService.delete(uploadFile);
		if(flag){
		    return Result.ok("删除成功");
        }else {
            return Result.error("删除失败");
        }
	}

    /**
     * 对于请求的url进行的处理
     * @return
     * @throws UnsupportedEncodingException
     */
    private List<String> getFilePaths() throws UnsupportedEncodingException{
        List<String> list = new ArrayList<>();
        String requestURI = ServletUtils.getRequest().getRequestURI();

        //解决url带有中文名的问题
        requestURI = java.net.URLDecoder.decode(requestURI,"UTF-8");
        String contextPath = ServletUtils.getRequest().getContextPath();
        String pathPrefix = "";
        if (StringUtils.equals(contextPath, "/")) {
            pathPrefix = "/files";
        } else {
            pathPrefix = contextPath + "/files";
        }

        String path = StringUtils.removeStart(requestURI, pathPrefix);
        String[] pathArray = StringUtils.splitByWholeSeparator(path, "/");
        return pathArray != null ? Lists.newArrayList(pathArray) : Lists.newArrayList();
    }
}
