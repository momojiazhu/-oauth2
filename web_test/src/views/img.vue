<template>
  <div>
    <div>
      <el-upload action :before-upload="beforeUpload">
        <el-button size="small" type="primary">上传图片</el-button>
      </el-upload>
    </div>
    <div style="padding-top:30px;">
      <div v-for="(img,index) in imgArray" v-bind:key="index" style="margin-right:15px;">
        <span>{{img.url}}</span>
        <el-button size="small" type="primary" @click="showImg(img.url)">展示</el-button>
        <el-button size="small" type="primary" @click="delImg(img.url)">删除</el-button>
      </div>
    </div>
    <div style="padding-top:30px;">
      <el-image style="width: 500px; height: 500px" :src="img"></el-image>
    </div>
  </div>
</template>
<script>
import {
  uploadFileFun,
  selectFileList,
  getUploadFile,
  delUploadFile,
} from "@/api/uploadFile";
export default {
  data() {
    return {
      imgArray: [],
      img: null,
    };
  },
  created() {
    this.selectFileList();
  },
  methods: {
    beforeUpload(file) {
      uploadFileFun(file)
        .then((res) => {
          this.selectFileList();
        })
        .catch((err) => {
          console.log("err");
        });
      return false;
    },
    selectFileList() {
      selectFileList()
        .then((res) => {
          this.imgArray = res.data.result;
        })
        .catch((err) => {
          console.log("err");
        });
    },
    //以流的形式返回图片这样写
    showImg(url) {
      getUploadFile(url)
        .then((res) => {
          let that = this;
          const content = res.data;
          const blob = new Blob([content]);
          this.blobToDataURI(blob, function (result) {
            //blob格式再转换为base64格式
            that.img = result;
          });
        })
        .catch((err) => {
          alert("err");
        });
    },
    delImg(url) {
      delUploadFile(url)
        .then((res) => {
          this.selectFileList();
        })
        .catch((err) => {
          alert("err");
        });
    },
    blobToDataURI(blob, callback) {
      var reader = new FileReader();
      reader.readAsDataURL(blob);
      reader.onload = function (e) {
        callback(e.target.result);
      };
    },
  },
};
</script>
<style scoped>
</style>