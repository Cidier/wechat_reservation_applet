<script setup>
	import {
		Menu as IconMenu,
		Management,
		Menu,
		Setting,
		HomeFilled
	} from '@element-plus/icons-vue'
	import {
		ref
	} from "vue";
import { computed } from 'vue'
	// const activeIndex = ref('1');
	const props = defineProps({
		routes: Array
	})


// const validRoutes0 = computed(() => {
//   return props.routes.filter(item => item && item.path)

// })
const validRoutes = computed(() => {
  return props.routes.filter(item => !item.hidden || item.hidden !== true)

})
// const validRoutes= computed(()=>{
// 	return validRoutes0.filter(item => !item.hidden || item.hidden !== true)
// })
		console.log('valirdrFirst', validRoutes)

	// console.log('cidierFirst', props.routes)
	// for (var i = 0; i < props.routes.length; i++) { 	
	// 	console.log(props.routes[i].path+"cidier_path")
	// }
</script>

<template>
	<div>
		<!-- <template v-for="item in validRoutes " v-if="(item.hidden === undefined ? false : item.hidden) !== true"> -->
		 <template v-for="item in validRoutes[0].children" v-if="item?.hidden === undefined || item.hidden !== true" >
  	
			<template v-if= " !item.children || item.children.length === 0">
			<!-- <router-link > -->
			<el-menu-item :index="item.path||''">
				<el-icon>
					<HomeFilled />
				</el-icon>
				<span v-if="item.meta&&item.meta.title" slot="title">{{item.meta.title}}</span>
			</el-menu-item>
			</template>

			<el-sub-menu v-else :index="item.path||' '" :key="item.name">
				<template #title>
					<el-icon>
						<Menu />
					</el-icon>
					<span v-if="item.meta&&item.meta.title" slot="title">{{item.meta.title}}</span>
				</template>

				<!-- // <template #title>Group 1</template> -->
<!--  v-if="(child.hidden === undefined ? false : child.hidden) !== true -->
				<template v-for="child in item.children">
					<el-menu-item :index="child.path||' '">
						<el-icon>
							<Menu />
						</el-icon>
						<span v-if="child.meta&&child.meta.title" slot="title">{{child.meta.title}}</span>
					</el-menu-item>

				</template>
			</el-sub-menu>
		</template>
	</div>

</template>
<style scoped>
	:deep .el-menu-item-group__title {
		display: none;
	}
</style>