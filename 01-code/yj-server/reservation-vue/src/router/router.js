import {
	createRouter,
	createWebHashHistory,
	createWebHistory
} from 'vue-router';

import SystemLayout from '@/layout/system/index.vue'

export const constantRouterMap = [
	// { path: '/', component: }

	/*
	该： resolve => require(["../layout/system/index.vue"], resolve) 方式已经废弃 会出现：
	    error：[Vue Router warn]: Unexpected error when starting the router: ReferenceError: require is not defined
	            ReferenceError: require is not defined
	从vue-router 4+开始使用：component: () => import('@/layout/system/index.vue') 方式加载
	    其中的组件“default”是 Promise 。 不能直接将import('@/layout/system/index.vue') 放入component中
	        而是需要一个函数 () => import('@/layout/system/index.vue')
	*/
	// component: resolve => require(["../layout/system/index.vue"], resolve)
	// component: ()=> import('@/layout/system/index.vue'),

	{
		name: '/',
		path: "/",
		redirect: "/index",
		meta:{title:'Home'},
		component: SystemLayout,
		children:[
			{
				name: '/index',
				path: '/index',
				meta:{title:'首页'}

			},
			{
				path: '/system',
				name: '/system',
				meta:{title:'系统管理'},
				children: [
					{
						path: "/system/user",
						component: ()=> import('@/view/system/UserContainer.vue'),
						meta: { permission: true, title: "用户管理" }
					},
					{
						path: "/system/role",
						component: ()=> import('@/view/system/RoleContainer.vue'),
						meta: { permission: true, title: "角色管理" }
					},
					{
						path: "/system/permission",
						component: ()=> import('@/view/system/PermissionContainer.vue'),
						meta: { permission: true, title: "权限管理" }
					},
					{
						path: "/system/dictConfig",
						component: ()=> import('@/view/system/DictContainer.vue'),
						meta: { permission: true, title: "字典配置" }
					}
				]
			},{
				path: '/content',
				name: '/content',
				meta:{title:'内容管理'},
				children: [
					{
						path: "/content/menu",
						component: ()=> import('@/view/content/menu.vue'),
						meta: { permission: true, title: "菜单管理" }
					},
					{
						path: "/content/article",
						component: ()=> import('@/view/content/article.vue'),
						meta: { permission: true, title: "文章管理" }
					}

				]

			},
			{
				path: '/yj-overview',
				component: () => import('@/view/YJIntroduction/yj-overview.vue'),
				meta: {
					title: '余江概况'
				},

			},
			{
				path: '/starHotel',
				name: 'yj',
				meta:{title:'余江旅游'},
				children: [
					//         {
					//             path: '/yj-tourism-hotel',
					//             component: () => import('@/view/yj-tourism-hotel.vue'),
					// meta:{title:'余江旅游'}

					//         },
					{
						path: "/starHotel",
						component: () => import("@/view/YJTravel/starHotel.vue"),
						meta: {
							title: '星级酒店'
						}

					},
					{
						path: "/redTour",
						component: () => import("@/view/YJTravel/redTour.vue"),
						meta: {
							title: '红色旅游'
						}
					},
					{
						path: "/transport",
						component: () => import("@/view/YJTravel/transport.vue"),
						meta: {
							title: '交通旅游'
						}
					},
					{
						path: "/yjFood",
						component: () => import("@/view/YJTravel/yjFood.vue"),
						meta: {
							title: '余江美食'
						}
					},
					{
						path: "/yjScenery",
						component: () => import("@/view/YJTravel/yjScenery.vue"),
						meta: {
							title: '余江美景'
						}
					},
				]
			},
			{
				path: "/xingchengArrangement",
				name: "xingchengArrangement",
				meta: {
					title: '接待安排'
				},
				children: [{
						path: "/xingchengArrangement",
						component: () => import("@/view/YJArrangement/xingcheng-arrangement.vue"),
						meta: {
							title: '行程安排'
						}

					},
					{
						path: "/laibin",
						component: () => import("@/view/YJArrangement/laibin-people.vue"),
						meta: {
							title: '来宾人员'
						}

					},
					{
						path: "/peitong",
						component: () => import("@/view/YJArrangement/peitong-people.vue"),
						meta: {
							title: '陪同人员'
						}

					},
					{
						path: "/kaochadian",
						component: () => import("@/view/YJArrangement/kaocha-introduction.vue"),
						meta: {
							title: '考察点介绍'
						}

					}
				]
			},
			{
				path: "/industryOutline",
				component: () => import("@/view/YJIndustry/industy-outline.vue"),
				meta: {
					title: '产业布局'
				}

			},
			{
				path: "/policySupport",
				component: () => import("@/view/YJPolicy/policy-support.vue"),
				meta: {
					title: '政策支持'
				}

			}
		]
	},

	{
		path: "/login",
		component: () => import("@/view/login/login.vue"),
		hidden: true,
		meta:{title:'login'}
	},

	{
		path: "/404",
		component: () => import("@/layout/common/404.vue"),
		hidden: true,
		meta:{title:'404'}
	},
	{
		path: "/500",
		component: () => import("@/layout/common/500.vue"),
		hidden: true

	},
	{
		path: "/403",
		component: () => import("@/layout/common/403.vue"),
		hidden: true

	},
	{
		path: "/:pathMatch(.*)*", //不能完全使用/* 在4版本中必须使用正则表达式
		redirect: "/404",
		hidden: true

	}

]

const router = createRouter({
	history: createWebHashHistory(),
	routes: constantRouterMap
})



// 路由加载前
router.beforeEach((to, from, next) => {
	console.log("to.path", to, from);
	next();
});

// 路由加载后
router.afterEach(() => {
	console.log("路由加载后")
});
export default router;
