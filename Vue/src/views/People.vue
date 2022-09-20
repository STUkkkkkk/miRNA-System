<template>
    <div >
        <div style="margin: 10px; width: 500px;height: 650px; background-color: beige;">
            <h2 style="text-align: center; padding-top: 20px">管理大大kk的个人信息</h2><br/>
            <el-form :model="form" label-width="20%" :rules="rules">
                <el-form-item label="用户名:" prop="username">
                    <el-input v-model="form.username" style="padding-right: 20px"></el-input>
                </el-form-item>
                <el-form-item label="密码:" prop="password">
                    <el-input v-model="form.password" style="padding-right: 20px" show-password></el-input>
                </el-form-item>
                <el-form-item label="昵称:" prop="nickname">
                    <el-input v-model="form.nickname" style="padding-right: 20px"></el-input>
                </el-form-item>
                <el-form-item label="年龄:" prop="age">
                    <el-input v-model="form.age" style="padding-right: 20px"></el-input>
                </el-form-item>
                <el-form-item label="地址:" prop="address">
                    <el-input v-model="form.address" style="padding-right: 20px"></el-input>
                </el-form-item>
                <el-form-item label="性别:">
                    <el-radio v-model="form.sex" label="男" style="margin-left: 10px">男</el-radio>
                    <el-radio v-model="form.sex" label="女">女</el-radio>
                    <el-radio v-model="form.sex" label="未知">未知</el-radio>
                </el-form-item>
                <el-form-item label="头像:">
                    <el-upload
                            ref="uploadFile"
                            action='#'
                            :http-request="uploadAction"
                            :on-exceed="handleExceed"
                            :limit="1"
                            :show-file-list="false"
                            :auto-upload="true"
                            :before-upload="beforeUpload" style="width: 100%;" class="left-view">
                        <img v-if="bannerUrl" :src="bannerUrl" class="avatar" />
                        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                    </el-upload>
                </el-form-item>
                <el-form-item>
                    <el-button style="background-color: greenyellow; color: black" @click="update()">保存信息</el-button>
                    <el-button style="margin-left: 120px;background-color: indigo; color: white" @click="this.$router.push('/')">返回主页</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
    <div style="padding: 10px">
        <img :src="Lyy" style="width: 400px"/>
    </div>
</template>

<script>
    import request from "../utils/request";
    export default {
        name: "People",
        data(){
            return {
                form: JSON.parse(sessionStorage.getItem("user")),
                Lyy: require("@/utils/img/lyy.gif"),
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
        methods:{
            update(){
                request.put("/user",this.form).then(res=>{
                    if(res.code === "0"){
                        this.$message({
                            type: "success",
                            message: "修改成功"
                        })
                    }
                    else{
                        this.$message({
                            type: "error",
                            message: "修改失败"
                        })
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>