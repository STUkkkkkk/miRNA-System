import axios from "axios";
import router from "@/router";
import { ElMessage } from 'element-plus'
axios.defaults.baseURL='/api/'

const request = axios.create({
    timeout: 50000
})

//request拦截器，请求发送前对请求做一些处理 比如统一加token 加密
function encodeURIParams(config) {
    let url = config.url
    url += '?'
    const keys = Object.keys(config.params)
    for (const key of keys) {
        url += `${key}=${encodeURIComponent(config.params[key])}&`
    }
    url = url.substring(0, url.length - 1)
    config.params = {}
    return url
}

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;character=utf-8';
    let king = localStorage.getItem("king") ? JSON.parse(localStorage.getItem("king")) : null;
    if(king){
        config.headers['token'] = king.token; // 设置请求头
    }
    //取出local的信息
    // let userJson = localStorage.getItem("user");
    // let kingJson = localStorage.getItem("king");
    // if(!userJson && !kingJson){
    //     router.push("/login");
    // }\
    if (config.method === 'get' && config.params) {
        config.url = encodeURIParams(config)
    }
    return config
}, error => {
    return Promise.reject(error)
});


//response拦截器，在接口响应后统一做处理
request.interceptors.response.use(
    response=>{
        let res =  response.data;
        if(res.code === '401'){
            ElMessage.error("登录过期，请重新登录");
            router.push("/login");

        }
        return res;
    },
    error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
);

export default request;