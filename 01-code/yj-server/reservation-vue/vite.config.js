import { defineConfig } from 'vite'
import {fileURLToPath, URL} from 'node:url'
import vue from '@vitejs/plugin-vue'
// import global from './src/global.js'

// const url = global.request_host

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  css:{
    preprocessorOptions:{
      scss: {
        // prependData: `@import "./src/styles/element/index.scss"`
      }
    }
  },
  // 配置转发代理
  server: {
    // port: 8080,
    host: '0.0.0.0', // 服务器地址
    port: 8888, // 服务器端口号
    proxy: {
      '/api': {// 代理接口前缀为/api的请求
        target: 'http://localhost:7676', // 对应的代理地址
        secure: false,// 接受运行在https上，默认不接受
        changeOrigin: true,// 如果设置为true,那么本地会虚拟一个服务器接收你的请求并代你发送该请求，这样就不会有跨域问题（只适合开发环境）
        ws: true,
        logLevel: 'debug',
        //重写路径 比如'/api/aaa/ccc'重写为'/aaa/ccc'
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
