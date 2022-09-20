<template>
    <div style="width: 100%;height: 100vh ;background-color: darkslateblue; overflow: hidden">
        <div style= "width:400px ;margin: 150px auto; ">
            <div style="color: white;font-size: 30px; text-align: center; padding-bottom: 15px">欢迎登录</div>
            <el-form :model="form" ref="form" size="normal" :rules="rules">
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" clearable v-model="form.username" placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" clearable v-model="form.password" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="width: 100%; background-color: aquamarine" @click="login()">登录</el-button>
                </el-form-item>
            </el-form>
            <a style="text-decoration: none ; color: gold" href="http://localhost:8090/register">还未有账号?点击注册</a>
        </div>
    </div>
</template>

<script>
    // 一个用户图像+密码锁图标
    import { User, Lock } from "@element-plus/icons-vue";
    import request from "../utils/request";
    export default {
        name: "Login",
        data() {
            return {
                form: {},
                rules:{
                    username:[
                        {required: true, message: '请输入用户名' , trigger: 'blur'},
                    ],
                    password:[
                        {required: true, message: '请输入密码' , trigger: 'blur'},
                    ],
                }
            }
        },
        setup(){
            return {
                User,
                Lock
            }
        },
        methods:{
            login(){
                this.$refs['form'].validate((valid=> {
                    if(valid){//验证规则合法
                        request.post("/user/login",this.form).then(res=>{
                            console.log(res);
                            if(res.code === "0"){
                                this.$message({
                                    type: "success",
                                    message: "登录成功"
                                })
                                sessionStorage.setItem("king",JSON.stringify(res.data));
                                if(res.data.username === 'stukk' && res.data.password === 'xp908098'){
                                    sessionStorage.setItem("user",JSON.stringify(res.data));
                                    this.$router.push("/");//登录成功，跳转页面
                                }

                                else{
                                    this.$router.push("/index");
                                }
                            }
                            else{
                                this.$message({
                                    type: "error",
                                })
                            }
                        });
                    }
                    else{
                        alert("请完善信息");
                    }
                }))



            }
        }


    }
</script>

<style scoped>

</style>