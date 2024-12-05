<script setup>

import {ref, reactive, inject, getCurrentInstance} from "vue";
import { useRouter} from "vue-router";
import Vcode from "vue3-puzzle-vcode";
import {EditPen, Hide} from "@element-plus/icons-vue";
import { ElMessage } from 'element-plus'
import {getToken, clearToken, setLoginAll} from '@/utils/auth.js'

const router = useRouter();
//判断是否有token，如果有，则跳转index，并在index进行token 登录判断
if(getToken()){
  const router = useRouter();
  router.push({
    path: '/index'
  });
}


const ruleForm = reactive({
  email: '',
  password: ''
});
const rules = ref({});

const isShow = ref(false);
const buttonType = ref("info");
const buttonDisabled = ref(false);
const buttonText = ref("点击校验");

function onSubmit(){
  isShow.value = true
}
function onSuccess(){
  buttonType.value = "success";
  buttonText.value = "验证成功!";
  buttonDisabled.value = true;
  onClose();
}
function onClose(){
  isShow.value = false;
}
const { proxy } = getCurrentInstance();
console.log("打印", proxy)
function submitForm(){

  if(buttonDisabled.value){
    console.log("打印", proxy)

    const api = proxy.$api;
    const request = proxy.$request;

    console.log("测试", api, request)

    // request(api.url.login, 'post', {username:ruleForm.email, password:ruleForm.password}, res=>{
    //   console.log("登录成功", res)
    //
    //   const router = useRouter();
    //   router.push({
    //     path: '/index'
    //   });
    // });
    api.apiLogin({username:ruleForm.email, password:ruleForm.password}
        , res=>{
          console.log("登录成功", res);
          setLoginAll(res);
          router.push({
            path: '/index'
          });
        }, err=>{
          console.log("登录失败!", err)
          ElMessage({ type: 'error', message: result.msg})
        }, com=>{
          console.log("不管什么情况都调用!", com)
        }
    )
  }else{
    ElMessage.info("请先校验后，点击登录!")
  }

}


</script>

<template>
  <div class="login-container">
    <el-form :model="ruleForm" :rules="rules" class="login-form" label-position="left">

      <div class="title-container">
        <h3 class="title">登录系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <el-icon><EditPen /></el-icon>
        </span>
        <el-input
            v-model="ruleForm.email"
            placeholder="请输入用户名"
            name="username"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <el-icon><Hide /></el-icon>
        </span>
        <el-input
            v-model="ruleForm.password"
            type="password"
            placeholder="请输入密码"
            name="password"
            tabindex="2"
        />
      </el-form-item>
      <div>
        <Vcode :show="isShow" @success="onSuccess" @close="onClose" />
        <el-button :type="buttonType" :disabled="buttonDisabled"  style="width:100%;margin-bottom:12px;padding: 5px 12px;" @click="onSubmit">{{buttonText}}</el-button>
      </div>
      <el-button type="primary" style="width:100%;margin-bottom:30px;" @click="submitForm">登录</el-button>
    </el-form>
  </div>
</template>
<style>

</style>
<style lang="scss" scoped>
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {

  .el-input {
    display: flex;
    height: 47px;
    width: 85%;

    :deep(.el-input__wrapper) {
      display: flex;
      background-color: rgba(255,255,255,0);
      border: 0;
      box-shadow: 0 0 0 0 ;
    }

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}

$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  height: 100vh;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .submit{

  }
  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }


  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}

</style>