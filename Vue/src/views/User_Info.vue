<template>
    <div style=" height: 650px; background-color: #eff1f9">
<!--        最上面上面分割线-->
        <div class="el-divider el-divider--horizontal"
             role="separator"
             data-v-5ddfe2f8=""
             style="--el-border-style:solid; background-color: rgb(239, 241, 249);">
            <div class="el-divider__text is-center"> 个人信息
            </div>
        </div><br/>
        <div style="width: 300px; height: 580px; margin-left: 30px; float: left ">
<!--            头像-->
<!--            <input type="file" @change="GetFile" hidden id="FileInput">-->
<!--            <img class="head" @click="changeFile" :src="user.icon ? FileName+user.icon : CommonName" referrerpolicy="no-referrer">-->
            <el-upload
                    class="avatar-uploader"
                    action="#"
                    :show-file-list="false"
                    :http-request="uploadImage"
                    :before-upload="beforeAvatarUpload">
                <img v-if="user.icon" class="head" @click="changeFile" :src="user.icon ? FileName+user.icon : CommonName" referrerpolicy="no-referrer">
                <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>


<!--            昵称-->
            <div style="text-align: center; font-family: 宋体; font-size: 30px; margin-top: 10px">{{user.nickname}}</div>
<!--            分割线-->
            <div class="el-divider el-divider--horizontal"
                 role="separator"
                 data-v-5ddfe2f8=""
                 style="--el-border-style:solid; background-color: rgb(239, 241, 249); margin-top: 70px">
                <div class="el-divider__text is-center"> 个性签名
                </div>
            </div><br/>

            <div style="width: 100%; height: 265px; background-color: #eff1f9">
                <textarea class="inputt" @change="saveInfo()" style="width: 100%;height: 150px; border: none ;background-color: #f2f9f9;resize: none;font-size: 20px;font-family: 楷体; color: plum" placeholder="请输入个性签名"
                          v-model="signature"></textarea>
            </div>
        </div>
        <el-divider direction="vertical" style="float: left; margin-left: 30px; margin-right: 100px"></el-divider>

        <div style="width: 600px; height: 580px ; float: left;">
            <div class="divv1">
                用户名: <input v-model="user.username" class="inputt" style="background-color: #eff1f9; font-size: 20px; font-family: 楷体" type="text">
            </div>
<!---->
            <el-divider style="margin: 10px"/>
            <div class="divv2">
                昵称: <input v-model="user.nickname" class="inputt" style="background-color: #eff1f9; font-size: 20px; font-family: 楷体" type="text">
            </div>

            <el-divider style="margin: 10px"/>
            <div class="divv2">
                年龄: <input v-model="user.age" class="inputt" style="background-color: #eff1f9; font-size: 20px; font-family: 楷体" type="text">
            </div>

            <el-divider style="margin: 10px"/>
            <div class="divv2">
                性别: <input v-model="user.sex" class="inputt" style="background-color: #eff1f9; font-size: 20px; font-family: 楷体" type="text">
            </div>

            <el-divider style="margin: 10px"/>
            <div class="divv2">
                密码: <input v-model="user.password" class="inputt" type="password" style="background-color: #eff1f9; font-size: 20px;">
            </div>

            <el-divider style="margin: 10px"/>
            <div class="divv2">
                重复密码: <input v-model="user.password" class="inputt" type="password" style="background-color: #eff1f9; font-size: 20px; ">
            </div>

            <el-divider style="margin: 10px"/>
            <div class="divv2">
                地址: <input v-model="user.address" class="inputt" style="background-color: #eff1f9; font-size: 20px; font-family: 楷体" type="text">
            </div>

            <el-button @click="saveUserInfo()" style="border: none ;margin-top: 30px; margin-left: 30px;width: 60px; height: 30px; background-color: greenyellow; color: plum">保存</el-button>

            <el-button @click="this.$router.push('/index')" style="border: none;margin-top: 30px; margin-left: 130px;width: 60px; height: 30px; background-color: red">取消</el-button>

        </div>
    </div>
<!--    <el-divider direction="vertical" />-->
</template>

<script>
    import { ArrowDown,Plus} from "@element-plus/icons";
    import request from "../utils/request";
    import {f} from "../utils/FileUtil";
    export default {
        name: "User_Info",
        components: {
            ArrowDown,
            Plus
        },
        created() {
            this.load();
        },
        data(){
            return {
                FileName: f(),
                CommonName : this.FileName+'BigProject/People/user_icon.jpg',
                user:[],
                form: [],
                LYY: require("@/utils/img/lyy.gif"),
                Lyy1: require("@/utils/img/lyy1.gif"),
                signature: '',
            }
        },
        methods: {
            Leave(){
                localStorage.removeItem("king");//用户就是是king
                this.$router.push("/login");
            },
            load(){
                let king = localStorage.getItem("king");
                if(king === null){
                    this.$router.push("/login");//给爷爷爬开
                    return;
                }
                king = localStorage.getItem("king");
                this.user = JSON.parse(king);
                request.get("/signature/"+this.user.id).then(res=>{
                    this.signature = res.data.signature;
                })
            },
            uploadImage(param){
                const formData = new FormData();
                formData.append('file', param.file);
                let file = formData;
                request.post("/qiNiu/Peopleupload",file
                ).then(res=>{
                    if(res.code === "0"){
                        this.user.icon = res.data;
                        this.$message(
                            {
                                type: 'success',
                                message: '上传成功'
                            }
                        )
                    }
                    else{
                        this.$message(
                            {
                                type: 'error',
                                message: res.message,
                            }
                        )
                    }
                })

            },
            // 资质照片上传前校验
            beforeAvatarUpload(file) {
                const isPic = file.type.indexOf('image') >= 0;
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isPic) {
                    this.$message.error('资质照片只能为图片格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传图片大小不能超过 2MB!');
                }
                return isPic && isLt2M;
            },
            saveInfo() {
                let ans = {
                    user_id: this.user.id,
                    signature: this.signature,
                }
                console.log(ans);
                request.put("/signature",ans).then(res=>{
                    if(res.code === "0"){
                        this.$message(
                            {
                                type: 'success',
                                message: '修改个性前签名成功',
                            }
                        )
                    }
                    else{
                        this.$message(
                            {
                                type: 'error',
                                message: res.message,
                            }
                        )
                    }
                })
            },
            saveUserInfo(){//保存用户信息
                request.put("/user",this.user).then(res=>{
                    if(res.code === "0"){
                        this.$message({
                            type: "success",
                            message: "保存用户信息成功！"
                        })
                        localStorage.removeItem("king");
                        localStorage.setItem("king",JSON.stringify(res.data));
                        this.load();
                    }
                    else{
                        this.$message({
                            type: "error",
                            message: "保存用户信息失败！"
                        })
                    }
                });
            },

        }
    }
</script>

<style scoped>
    .el-divider__text.is-center{
        background-color: #eff1f9;
        font-family: 宋体;
        font-size: 25px;
        color: darkorchid;
    }
    .head{
        width: 100px;
        height: 100px;
        padding: 1px;
        background-color: #fff;
        border-radius: 50%;
        box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
        margin-left:100px;
        margin-top: 50px;
    }
    .inputt{
        border: none;
    }
    .inputt:focus{
        outline: none;
    }
    .el-divider--vertical{
        display:inline-block;
        width:1px;
        height:520px;
        margin: 0 8px;
        vertical-align:middle;
        position:relative;
    }
    .divv1{
        margin-left: 40px;
        margin-top: 80px;
        font-size: 20px;
        font-family: 楷体;
    }
    .divv2{
        margin-left: 40px;
        font-size: 20px;
        font-family: 楷体;
    }
</style>