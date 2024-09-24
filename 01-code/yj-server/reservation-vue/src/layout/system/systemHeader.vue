<script setup>

import { Menu as IconMenu, Message, Setting } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
const route = useRouter();
const login = function(){
  route.push('/login')
}

</script>

<template>
  <div style="display: flex; justify-content: space-between; width: 100%;;">

    <h3>后台管理</h3>

    <div style="height: 100%; text-align: right; font-size: 12px; ">
      <div class="toolbar">
        <el-dropdown>
          <el-icon style="margin-right: 8px; margin-top: 1px"><setting/></el-icon>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item >首页</el-dropdown-item>
<!--              <el-dropdown-item>Add</el-dropdown-item>-->
<!--              <el-dropdown-item>Delete</el-dropdown-item>-->
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <span @click="login">管理员</span>
      </div>
    </div>
  </div>
</template>

<style scoped>

.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
</style>