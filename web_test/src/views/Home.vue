<template>
  <div class="home">
    <el-button type="primary" @click="test()">测试1</el-button>
    <el-button type="primary" @click="test2()">测试2</el-button>
    <el-button type="primary" @click="login()">登陆</el-button>
    <el-button type="primary" @click="logout()">登出</el-button>
    <el-button type="primary" @click="goImg()">跳转img</el-button>
    <el-button type="primary" @click="goWord()">跳转word</el-button>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'
import { Test1,Test2,accountLogin } from "@/api/register";
import { setToken,getToken } from "@/utils/token.js";
import { delCookie } from "@/utils/token";

export default {
  name: 'home',
  components: {
    HelloWorld
  },
  methods:{
    test(){
      Test1().then(res =>{
        console.log(res.data);
      }).catch(err =>{
        console.log(err);
      })
    },
    test2(){
      Test2().then(res =>{
        console.log(res.data);
      }).catch(err =>{
        console.log(err);
      })
    },
    login(){
      accountLogin({username:"gaobo",password:"123456"}).then(res =>{
        console.log(res);
        setToken(res.data.access_token);
      }).catch(err =>{
        console.log("123456"+err);
      })
    },
    //退出暂时在前端删除token，是不是也应该在后端也将token失效，这个没细研究
    logout(){
      delCookie("token");
    },
    goImg(){
      this.$router.push({name:'img'});
    },
    goWord(){
      this.$router.push({name:'word'});
    }
  }
}
</script>
