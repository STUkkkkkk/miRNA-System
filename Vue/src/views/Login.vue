<template>
    <div style="width: 100%;height: 100vh ;background-color: white; overflow: hidden">
        <div style= "width:400px ;margin: 130px auto; margin-top: 50px">
            <div style="padding-left: 100px; width: 100px; height: 100px; margin-bottom: 40px"><img :src="LYY"></div>
            <div style="color: black; font-family: 楷体; font-size: 30px; text-align: center; padding-bottom: 15px">欢迎登录</div>
            <el-form :model="form" ref="form" size="normal" :rules="rules" style="width: 300px;  margin-left: 50px">
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" clearable v-model="form.username" placeholder="请输入用户名/邮箱"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" clearable v-model="form.password" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="font-family: 楷体;width: 30%; background-color: azure" @click="login()">登录</el-button>
                    <el-button style="margin-left: 120px;font-family: 楷体;width: 30%; background-color: mistyrose" @click="this.$router.push('/register')">注册账号</el-button>

                </el-form-item>
            </el-form>
            <a style="font-family: 楷体 ;text-decoration: none ; color: black; margin-left: 50px;cursor: pointer" @click="this.$router.push('/resetPassword')">忘记密码？</a>
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
                LYY: require("@/utils/img/lyy1.gif"),
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
                            if(res.code === "0"){
                                this.$message({
                                    type: "success",
                                    message: "登录成功"
                                })
                                localStorage.setItem("king",JSON.stringify(res.data));
                                if(res.data.username === 'stukk' && res.data.password === 'xp908098'){
                                    localStorage.setItem("user",JSON.stringify(res.data));
                                    this.$router.push("/user");//登录成功，跳转页面
                                }
                                else{
                                    this.$router.push("/index");
                                }
                            }
                            else{
                                this.$message({
                                    type: "error",
                                    message: "账号密码错误，登陆失败"
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