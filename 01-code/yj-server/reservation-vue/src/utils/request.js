
import router from "@/router/router.js";
import axios from 'axios'
import qs from 'qs'
import global from '../global'
import { getToken, clearAll } from './auth'
import { ElLoading, ElMessage } from 'element-plus'
import {keys} from "xe-utils";

const http = axios.create();

http.defaults.baseURL = global.request_host;
http.defaults.withCredentials = true; //设置携带cookie
http.defaults.timeout = 200000;

/**
 * request请求拦截器配置
 */
http.interceptors.request.use(config=>{
        //配置请求前处理逻辑
        // 是否需要设置 token
        const isToken = (config.headers || {}).isToken === false;
        if (getToken() && !isToken) {
            // config.headers["Authorization"] = "Bearer " + getToken(); // 让每个请求携带自定义token 请根据实际情况自行修改
            config.headers["Authorization"] = getToken(); // 让每个请求携带自定义token 请根据实际情况自行修改
        }
        //get请求参数映射处理
        if (config.method === "get" && config.params) {
            let url = config.url + "?";
            for (const propName of Object.keys(config.params)) {
                const value = config.params[propName];
                let part = encodeURIComponent(propName) + "=";
                if (value !== null && typeof value !== "undefined") {
                    if (typeof value === "object") {
                        for (const key of Object.keys(value)) {
                            let params = propName + "[" + key + "]";
                            let subPart = encodeURIComponent(params) + "=";
                            url += subPart + encodeURIComponent(value[key]) + "&";
                        }
                    } else {
                        url += part + encodeURIComponent(value) + "&";
                    }
                }
            }
            url = url.slice(0, -1);
            config.params = {};
            config.url = url;
        }
        return config;
    },
    error => {
        console.log(error);
    })



/**
 * request封装请求
 *  url:请求地址
 *  method:请求方式
 *  type: json or form
 *  data: 参数
 *  success: 成功回调方法
 *  fail: 失败回调方法
 *  complete: 回调方法
 *  isLoading: 是否显示加载中
 */
let requestCall = function(url, method, type, data, success, fail, complete, isLoading = true) {
    console.log(url)
    if (url.indexOf('/') === 0) {
        url = url.substr(1);
    }
    let loadingInstance = undefined;
    if(isLoading){

        loadingInstance = ElLoading.service({
            lock: true,
            text: 'Loading',
            background: 'rgba(0, 0, 0, 0.7)',
        })
    }

    let successCallback = function(response) {
        if(isLoading){loadingInstance.close();}
        // console.log('response:' + JSON.stringify(response));
        // console.log('response:' + JSON.stringify(response.data));
        let result = response.data;
        if (result.errcode === 0) {
            if (success && typeof success === 'function') {
                success(result.data, result.msg);
            } else {}
        } else if (result.errcode === 1) {
            // that.$cookies.remove('userInfo');
            // that.$router.push({
            //     path: '/login'
            // });
            clearAll();//清除所有auth
            router.push('/login');
            ElMessage({ type: 'error', message: result.msg})
        }else if(result.errcode === 9){
            // 当前登录已过期 或 未登录，需要点击登录 或 跳转到登录界面
            //未登录或登录过期
            ElMessage({ type: 'error', message: result.msg})
            clearAll();//清除所有auth

            router.push('/login');
        } else {
            if (fail && typeof fail === 'function') {
                fail(result.msg, result.errcode);
            } else {
                ElMessage({ type: 'error', message: result.msg})
            }
        }
        if (complete && typeof complete === 'function') {
            complete();
        }
    }

    let failCallback = function(error) {
        if(isLoading){loadingInstance.close();}
        console.error(error)
        let responseData = error.response && error.response.data ? error.response.data : null;
        let errmsg = '网络异常或者服务器服务异常';
        if (responseData && responseData.msg) {
            errmsg = responseData.msg;
        }
        if (fail && typeof fail === 'function') {
            fail(errmsg, responseData ? responseData.code : null);
        } else {
            ElMessage({ type: 'error', message: errmsg})
        }
        if (complete && typeof complete === 'function') {
            complete();
        }
    }

    console.log("啊哈哈哈哈哈type is",type==='formdata')
    console.log(data+"data")
    if (type === 'formdata' && method.toUpperCase() ==='POST'){
        console.log('formdataaa')

        let formData = new FormData();
        if (data instanceof FormData) {
            console.log("哈哈哈哈")
            // 下面对了
            data.forEach(key=>{
                console.log("依次遍历", key);

            })
            formData = data;
        } else {
            // 下面这个代码有问题 赋值会报错,formdata就变成空的了
            Object.keys(data).forEach(key => {
                console.log("依次遍历", key);
                formData.append(key, data[key]);
            });
        }
        // Object.keys(data).forEach(key=>{
        //     console.log("依次便利",data[key])
        //     formData.append(key, data[key]);
        //
        // });
        console.log(formData, "formdata")
        http.post(url, formData, {
            headers:{
                'Content-Type': 'multipart/form-data'
            }
        }).then(successCallback).catch(failCallback);

    }
    else if (method && method.toUpperCase() === 'POST') {
        http.post(url, type === 'json' ? data : qs.stringify(data, { indices: false })).then(successCallback).catch(failCallback);
    } else if (method && method.toUpperCase() === 'GET') {
        if (data) {
            let param = '';
            for (let key in data) {
                param += key + '=' + data[key] + '&'
            }
            if (param) {
                param = param.substring(0, param.length - 1)
                url += (url.indexOf('?') > 0 ? '&' : '?') + param;
            }
        }
        http.get(url).then(successCallback).catch(failCallback);
    } else if (method && method.toUpperCase() === 'DELETE') {
        http.delete(url, type === 'json' ? data : qs.stringify(data, { indices: false })).then(successCallback).catch(failCallback);
    } else if (method && method.toUpperCase() === 'PUT') {
        http.put(url, type === 'json' ? data : qs.stringify(data, { indices: false })).then(successCallback).catch(failCallback);
    }
}

let requestPromise = function(url, method, type, data, isLoading){
    return new Promise (function(resolve, reject){
        requestCall(url, method, type, data, resolve, reject, null, isLoading);
    })
}




export function request(url, method, data, success, fail, complete, isLoading = true) {
    requestCall.call(this, url, method, null, data, success, fail, complete, isLoading);
}

export function requestJson(url, method, data, success, fail, complete, isLoading = true) {
    requestCall.call(this, url, method, 'json', data, success, fail, complete, isLoading);
}

// 使用promise方式回调 !!待测试
export function requestProJson(url, method, data, isLoading = true){
    return requestPromise(url, method, 'json', data, isLoading)
}
export function requestFormData(url,method,data,success,fail,complete,isLoading = true){
    requestCall.call(this, url, method, 'formdata', data, success, fail, complete, isLoading);
}
export default {
    request, requestJson, requestProJson
}
