<script setup>
const goBack = ()=> {
  this.$router.go(-1);
}
</script>

<template>
  <div>
    <div id="stars"></div>
    <div id="stars2"></div>
    <div id="stars3"></div>
    <div class="error-page">
      <div class="error-code">
        <img src="/public/image/svg/500.svg" alt="" />
      </div>
      <div class="error-desc">很抱歉！我们正在努力修复中~</div>
      <div class="error-handle">
        <router-link to="/">
          <el-button size="large">返回首页</el-button>
        </router-link>
        <el-button class="btn-linght" type="primary" size="large" @click="goBack" >返回上一页</el-button>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.error-page {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}

.error-code {
  margin-top: 2rem;
  width: 55rem;
}
.error-desc {
  font-size: 26px;
  color: #fff;
  margin: 3rem 0 6rem;
}

.error-handle {
  padding-bottom: 200px;
}

.error-btn {
  margin-left: 100px;
}
</style>
