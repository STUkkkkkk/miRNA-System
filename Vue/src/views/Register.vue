<template>
    <div style="width: 100%;height: 100vh ;background-color: gold; overflow: hidden">
        <div style= "width:400px ;margin: 150px auto; ">
            <div style="color: white;font-size: 30px; text-align: center; padding-bottom: 15px">欢迎注册</div>
            <el-form :model="form" ref="form" label-width="70px" :rules="rules">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username" clearable></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="form.password" clearable></el-input>
                </el-form-item>
                <el-form-item label="昵称" prop="nickname">
                    <el-input v-model="form.nickname" clearable></el-input>
                </el-form-item>
                <el-form-item label="年龄" prop="age">
                    <el-input v-model="form.age" clearable ></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio v-model="form.sex" label="男" style="margin-left: 10px">男</el-radio>
                    <el-radio v-model="form.sex" label="女">女</el-radio>
                    <el-radio v-model="form.sex" label="未知">未知</el-radio>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input v-model="form.address" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="width: 100%; background-color: indigo" @click="register()">注册</el-button>
                </el-form-item>
            </el-form>
            <a style="text-decoration: none ; color: blueviolet" href="http://localhost:8090/login">已有账号?点击登录</a>
        </div>
    </div>
</template>

<script>
    import request from "../utils/request";
    export default {
        name: "Register",
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
                    nickname:[
                        {required: true, message: '请输入昵称' , trigger: 'blur'},
                    ],
                    age:[
                        {required: true, message: '请输入年龄' , trigger: 'blur'},
                    ],
                    address:[
                        {required: true, message: '请输入地址' , trigger: 'blur'},
                    ],

                }
            }
        },
        setup(){
            return {
            }
        },
        methods:{
            register(){
                this.$refs['form'].validate((valid=> {
                   if(valid){
                       request.post("/user",this.form).then(res=>{
                           if(res.code === "0"){
                               this.$message({
                                   type: "success",
                                   message: "注册成功"
                               })
                               this.$router.push("/login");//登录成功，跳转页面
                           }
                           else if(res.code === "504"){
                               this.$message({
                                   type: "error",
                                   message: res.message
                               })

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
                       alert("请完善信息");
                   }
                }))

            }
        }
    }
</script>

<style scoped>

</style>