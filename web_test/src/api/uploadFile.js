import axios from 'axios'
import { service,service2,service3,service4,baseURL2 } from '@/utils/request'

//以流的形式获取文件
export const getUploadFile = url => {
  return service4.request({
	url: '/files/'+url,
	method: 'get',
    responseType: 'blob'
  })
}
//上传文件
export const uploadFile = data => {
	return service4.request({
	  url: '/files/2019-01-01',
	  method: 'post',
	  data
	})
}

//删除文件
export const delUploadFile = url => {
	return service4.request({
	  url: '/files/'+url,
	  method: 'delete'
	})
}

//获取文件列表
export const selectFileList = () => {
	return service3.request({
	  url: '/selectFileList',
	  method: 'post'
	})
}

//将文件以这种形式，multipart/form-data，所以这样写
export function uploadFileFun(file){
	let data = new FormData();
    data.append('file', file)
	return uploadFile(data);
}

export function getWordFun(url){
	let str = baseURL2+'files/'+url;
	console.log(str);
	return str;
}

