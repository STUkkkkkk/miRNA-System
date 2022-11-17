<template>
    <div style="width: 100%;height: 100vh ;background-color: white; overflow: hidden">
        <div style= "width:400px ;margin: 130px auto; margin-top: 50px">
            <div style="padding-left: 100px; width: 100px; height: 100px; margin-left: 50px"><img :src="LYY"></div>
            <div style="color: black; font-family: 楷体; font-size: 30px; text-align: center; padding-bottom: 15px">忘记密码辽</div>
            <el-form :model="form" ref="form" size="normal" :rules="rules" style="width: 300px;font-family: 楷体;  margin-left: 50px">
                <el-form-item prop="email">
                    <el-input :prefix-icon="Message" clearable v-model="form.email" placeholder="请输入邮箱" ></el-input>
                </el-form-item>
                <el-form-item prop="code" >
                    <el-input :prefix-icon="Key" clearable v-model="form.code" style="width: 190px" placeholder="请输入验证码"></el-input>
                    <el-button :disabled="isShow" @click="sendCode()" style="margin-left: 10px;font-family: 楷体;color: #3a8ee6;width: 100px;float: left ;font-size: 15px;">
                        {{ButtonMessage}}</el-button>
                </el-form-item>

                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" v-model="form.password" clearable show-password placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" v-model="againPassword" clearable show-password placeholder="请重新输入密码"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button style="font-family: 楷体;width: 30%; background-color: mistyrose" @click="this.$router.push('/login')">返回登录</el-button>
                    <el-button style="margin-left: 120px;font-family: 楷体;width: 30%; background-color: azure" @click="reset">重置密码</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import request from "../utils/request";
    import { User, Lock ,Message,Key } from "@element-plus/icons-vue";
    export default {
        name: "ResetPassword",
        data() {
            return {
                LYY: require("@/utils/img/lyy2.gif"),
                form: {},
                againPassword: '',
                count: 60,
                ButtonMessage: '获取验证码',
                isShow: false,
                rules:{
                    username:[
                        {required: true, message: '请输入用户名' , trigger: 'blur'},
                    ],
                    email:[
                        {required: true, message: '请输入邮箱',trigger: 'blur'}
                    ],
                    code:[
                        {required: true, message: '请输入验证码',trigger: 'blur'}
                    ],
                    password:[
                        {required: true, message: '请输入密码' , trigger: 'blur'},
                    ]

                }
            }
        },
        setup(){
            return {
                User,
                Lock,
                Message,
                Key
            }
        },
        methods:{
            load(){
                let before = localStorage.getItem("time");
                let now = new Date().getTime();
                console.log( before  - now + 60)
                if(now - before <=  60000){
                    this.isShow = true;
                    let ok  = (before - now + 60000);
                    let i = (ok/1000).toFixed(0) ;
                    let taskId = setInterval(() => this.ButtonMessage = (i--) + '秒后可重发', 1000);
                    setTimeout(() => {
                        this.isShow = false;
                        clearInterval(taskId);
                        this.ButtonMessage = "发送验证码";
                    }, ok)
                }
            },
            sendCode(){
                if(this.againPassword != this.form.password){
                    this.$message({
                        type: "error",
                        message: "密码不相同"
                    })
                    return;
                }
                let email = {};
                email.email = this.form.email;
                console.log("发送验证码");
                request.post("/code/send?email="+this.form.email
                ).then(res=>{
                    if(res.code === "0"){
                        this.$message({
                            type: "success",
                            message: "发送成功"
                        })
                        //发送成功，按钮失效1分钟
                        localStorage.setItem("time",Date.now());
                        this.load();
                    }
                    else{
                        this.$message({
                            type: "error",
                            message: res.message
                        })
                    }
                })
            },
            reset(){
                this.$refs['form'].validate((valid=> {
                    if(valid){
                        request.post("/user/reset",this.form).then(res=>{
                            if(res.code === "0"){
                                this.$message({
                                    type: "success",
                                    message: "修改密码成功"
                                })
                                this.$router.push("/login");//登录成功，跳转页面
                            }
                            else{
                                this.$message({
                                    type: "error",
                                    message: res.message
                                })
                            }
                        });
                    }
                    else{
                        this.$message(
                            {
                                type: "error",
                                message: "请完善信息！"
                            }
                        )
                    }
                }))

            }
        }
    }
</script>

<style scoped>

</style>