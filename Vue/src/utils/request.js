import axios from "axios";
import router from "@/router";

axios.defaults.baseURL='/api/'

const request = axios.create({
    timeout: 5000
})

//request拦截器，请求发送前对请求做一些处理 比如统一加token 加密

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;character=utf-8';

    //取出session的信息
    let userJson = sessionStorage.getItem("user");
    let kingJson = sessionStorage.getItem("king");
    if(!userJson && !kingJson){
        router.push("/login");
    }

    return config
}, error => {
    return Promise.reject(error)
});


//response拦截器，在接口响应后统一做处理
request.interceptors.response.use(
    response=>{
        return response.data;
    },
    error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
);

export default request;