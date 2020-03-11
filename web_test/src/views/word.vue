<template>
  <div>
    <div>
      <el-upload action :before-upload="beforeUpload">
        <el-button size="small" type="primary">上传文档</el-button>
      </el-upload>  
    </div>
    <div style="padding-top:30px;">
      <div v-for="(word,index) in wordArray" v-bind:key="index" style="margin-right:15px;">
        <span>{{word.url}}</span>
        <el-button size="small" type="primary" @click="showword(word.url)">展示</el-button>
        <el-button size="small" type="primary" @click="delword(word.url)">删除</el-button>
      </div>
    </div>
    <div style="padding-top:30px;width:500px;height:500px;">
      //这个地址是office的第三方插件，这个后面跟着的furl必须是在互联网上可以访问的地址才行，我实际测试了，用我的服务器，这个挺好用的。获取文件的接口的权限后端已经放开了。
      <iframe
        :src="'http://ow365.cn/?i=20891&furl='+wordUrl"
        width="100%"
        height="100%"
        frameborder="1"
        v-if="wordUrl!=''"
      ></iframe>
    </div>
  </div>
</template>
<script>
import { uploadFileFun, selectFileList, getUploadFile, delUploadFile, getWordFun } from "@/api/uploadFile";
export default {
  data() {
    return {
      wordArray: [],
      wordUrl:""
    };
  },
  created() {
    this.selectFileList();
  },
  methods: {
    beforeUpload(file) {
      uploadFileFun(file)
        .then(res => {
          alert(JSON.stringify(res.data));
          this.selectFileList();
        })
        .catch(err => {
          alert("err");
        });
      return false;
    },
    selectFileList() {
      selectFileList()
        .then(res => {
          if (res.data.success) {
            this.wordArray = res.data.result;
          }
        })
        .catch(err => {
          alert("err");
        });
    },
    showword(url){
       this.wordUrl = getWordFun(url);
    },
    delword(url) {
      delUploadFile(url)
        .then(res =>{
           if(res.data.success){
              alert(res.data.message);
              this.selectFileList();
           }
        }).catch(err => {
           alert("err"); 
        })
    }
  }
};
</script>
<style scoped>
</style>