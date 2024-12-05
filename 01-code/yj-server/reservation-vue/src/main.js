import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/router.js';
import store from './store/index.js'

import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import 'element-plus/dist/index.css'
// import 'element-plus/theme-chalk/dark/css-vars.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// import Editor from '@tinymce/tinymce-vue'

// import 'src/styles/element/index.scss'
import VXETable from 'vxe-table'
import 'vxe-table/lib/style.css'
// makedown
import VMdEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
// Prism
import Prism from 'prismjs';
// highlight code
import 'prismjs/components/prism-json';


//
//
// markdown 的引入
import VueMarkdownEditor from '@kangc/v-md-editor'
import '@kangc/v-md-editor/lib/style/base-editor.css'
import VMdpreview from '@kangc/v-md-editor/lib/preview'
import '@kangc/v-md-editor/lib/theme/style/vuepress.css'

// github主题
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js'
import '@kangc/v-md-editor/lib/theme/style/github.css'
// 引入 highlight核心代码
import hljs from 'highlight.js/lib/core'
// 引入代码高亮
import json from 'highlight.js/lib/languages/json'
import java from 'highlight.js/lib/languages/java'
import javascript from 'highlight.js/lib/languages/javascript'
import c from 'highlight.js/lib/languages/c'
import cpp from 'highlight.js/lib/languages/cpp'
import armasm from 'highlight.js/lib/languages/armasm'
// 按需引入 代码高亮
hljs.registerLanguage('json',json)
hljs.registerLanguage('java',java)
hljs.registerLanguage('javascript',javascript)
hljs.registerLanguage('c',c)
hljs.registerLanguage('cpp',cpp)
hljs.registerLanguage('armasm',armasm)
// 配置
VMdpreview.use(vuepressTheme)
VueMarkdownEditor.use(githubTheme,{
    Hljs: hljs,
    extend(md){
        // 扩展插件
    }
})
import {api} from "./api/api.js"

import {requestJson, requestProJson} from './utils/request.js'
VMdEditor.use(vuepressTheme, {
    Prism,
});

const app = createApp(App)

//全局变量定义
app.config.globalProperties.$api = api();//全局api
app.config.globalProperties.$request = requestJson;//全局requestJson调用
app.config.globalProperties.$requestPro = requestProJson;//全局requestJson调用

app.use(ElementPlus, {locale: zhCn})
// app.use(Editor)
app.use(VXETable)

app.use(VMdEditor)
app.use(VueMarkdownEditor)
app.use(VMdpreview)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app
    .use(router) // 路由
    .use(store)
    .mount('#app')
