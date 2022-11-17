<template>
    <div >
        <div style="margin: 10px; width: 500px;height: 650px; background-color: beige;">
            <h2 style="text-align: center; padding-top: 20px">管理大大kk的个人信息</h2><br/>
            <el-form :model="form" label-width="20%" :rules="rules">
                <el-form-item label="头像:" prop="icon">
                    <img class="head" :src="form.icon ? FileName+form.icon : CommonName">
                    <input id="ICON" @change="GetFile" style="margin-left: 8px" type="file"/>
                </el-form-item>
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
    import {f} from "../utils/FileUtil";
    export default {
        name: "People",
        data(){
            return {
                FileName: f(),
                CommonName : f()+'BigProject/People/user_icon.jpg',
                headFile:'', // 头像
                FileList:[],//图片墙的图片
                user_id: 1,
                form: [],
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
        created() {
            this.load();
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
            },
            load(){
                let king = localStorage.getItem("king");
                if(king == null){
                    this.$router.push("/login");
                    return;
                }
                this.user_id = JSON.parse(king).id;
                console.log(this.user_id);
                request.get("/user/"+this.user_id).then(res=>{
                    console.log(res);
                    this.form = res.data;
                    console.log(this.form);
              });
                // if(this.headFile != ''){
                //     this.form.icon = this.headFile;
                //     console.log(this.headFile);
                // }
                // console.log(this.form.icon);
            },
            GetFile(){
                var icon = document.getElementById('ICON').files[0];
                let file = new FormData();
                file.append('file',icon);
                request.post("/qiNiu/Peopleupload",file).then(res=>{
                    if(res.code === "0"){
                        this.form.icon = res.data;
                        this.$message({
                            type: "success",
                            message: "上传图片成功"
                        })
                    }
                    else{
                        this.$message({
                            type: "error",
                            message: "上传图片失败"
                        })
                    }
                });
                // this.load();
            },
        }
    }
</script>

<style scoped>
    .upload-box {
        padding: 15px;
        display: flex;
        overflow-x: scroll;
    }
    .upload-btn {
        width: 70px;
        height: 125px;
        line-height: 40px;
        text-align: center;
        align-items: center;
        border: 1px dashed #3a8ee6;
        border-radius: 5px;
        font-size: 30px;
        color: #82848a;
        margin-right: 10px;
    }
    .upload-btn i {
        margin-top: 35px;
    }

    .pic-list {
        display: flex;
        overflow-x: scroll;
        height: 125px;
    }
    .pic-box{
        width: 100px;
        height: 120px;
        border-radius: 5px;
        margin-right: 2px;
        border: 1px solid #c0ccda;
        position: relative;
    }
    .pic-box img {
        width: 100px;
        height: 120px;
        border-radius: 5px;
    }
    .pic-box i {
        position: absolute;
        z-index: 99;
        top: 2px;
        right: 2px;
        color: gray;
    }
    .head{
        width: 40px;
        height: 40px;
        padding: 1px;
        background-color: #fff;
        border-radius: 50%;
        box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
    }
</style>