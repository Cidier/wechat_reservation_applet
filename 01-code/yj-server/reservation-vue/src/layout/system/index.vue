<script setup>
// import HelloWorld from './components/HelloWorld.vue'
import { ref } from 'vue'
import { Menu as IconMenu, Message, Setting } from '@element-plus/icons-vue'
import leftMenu from './leftMenu.vue'
import systemHeader from './systemHeader.vue'
// import { mapGetters } from 'vuex'
import { useRoute, useRouter } from "vue-router";
import { useStore } from 'vuex'
      
let windowHeight = document.documentElement.clientHeight
const resizeListenerFun = ()=>{
  windowHeight = document.documentElement.clientHeight
  console.log(windowHeight)
}
resizeListenerFun();
window.addEventListener('resize', resizeListenerFun)

//   const num = computed(() => store.state.num)
const activeIndex = ref('1');
// vuex
const store  = useStore()
const routes = store.state.routers

</script>

<template>
  <el-container class="layout-container-demo" :style="{'height': windowHeight +'px'}">

    <el-aside width="220px" style="background-color:rgb(12, 19, 44); border-right: 1px solid #213547;">
      <el-scrollbar>
		  <!--  default-openeds="['1', '3']" 表示默认展开菜单项的 index 值为 '1' 和 '3' 的两个菜单项。-->
		  <el-menu
		  :default-active="activeIndex" :default-openeds="['1', '3']" 
		  background-color="rgb(12, 19, 44)"
		           text-color="#fff"
		           active-text-color="#ffd04b"
				   router
				   >
			<leftMenu :routes="routes"></leftMenu>
		  </el-menu>
		  
        </el-scrollbar>
    </el-aside>

    <el-container>
      <el-header style="display: flex; justify-content: space-between; border-bottom: 1px solid #213547;">
        <systemHeader></systemHeader>
      </el-header>

      <el-main>
        <el-scrollbar>
          <router-view/>
        </el-scrollbar>
      </el-main>
    </el-container>

  </el-container>
</template>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
/* //background-color: var(--el-color-primary-light-7); */
/* //color: var(--el-text-color-primary); */
}
.layout-container-demo .el-aside {
/* //color: var(--el-text-color-primary); */
/* //background: var(--el-color-primary-light-8); */
}
.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}
</style>