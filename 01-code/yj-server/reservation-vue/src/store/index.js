import { createStore } from 'vuex'
import { constantRouterMap } from '@/router/router'

export default createStore({
	state:{
		routers: constantRouterMap,
	// addRouters: []
		
	},
	mutations:{
		// SET_ROUTERS(state,routers)=>{
		// 	state.addRouters = routers;
		// 	state.routers = constantRouterMap.concat(routers);
			
		// }
	},
	getters:{}
})