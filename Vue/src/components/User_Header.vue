<template>
    <!--头部-->
    <div style="height: 70px;line-height: 70px; border-bottom: 1px solid #cccccc ;display: flex;background-color: #f7f8fc">
        <div @click="this.$router.push('/index')" style="width: 200px; padding-left: 25px; font-weight: bold;font-size: larger; color: crimson; font-family: 楷体;cursor: pointer; text-align: center">坤坤网</div>
        <div style="width: 650px; padding-left:550px"><img :src="Lyy1" style="width: 100px"/></div>
        <div style="width: 450px"></div>
        <div style="width: 200px;">
            <div style="width: 200px;margin-top: 15px">
                <img class="head"
                     @click="this.$router.push('/user_info')"
                     :src="user.icon ? FileName+user.icon : CommonName"
                     referrerpolicy="no-referrer"
                >
                <el-dropdown>
                 <span class="el-dropdown-link" style="color: black">
                     {{user.nickname}}
                     <el-icon style="margin-left: 3px"> <ArrowDown/></el-icon>
                 </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item @click="this.$router.push('/user_info')">个人信息</el-dropdown-item>
                            <el-dropdown-item @click="Leave()">退出系统</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </div>
    </div>
    <!--头部-->
</template>

<script>
    import { ArrowDown} from "@element-plus/icons";
    import {f} from "../utils/FileUtil";
    export default {
        name: "User_Header",
        components: {
            ArrowDown
        },
        created() {
            this.load();
        },data(){
            return {
                FileName: f(),
                CommonName : this.FileName+'BigProject/People/user_icon.jpg',
                LYY: require("@/utils/img/lyy.gif"),
                Lyy1: require("@/utils/img/lyy1.gif"),
                user:[
                    {
                        icon: this.FileName + 'lyy.jpg',
                        nickname: '1234',
                    }
                ],
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
                this.user = JSON.parse(king);
            }
        }
    }
</script>

<style scoped>
    .head{
        width: 40px;
        height: 40px;
        padding: 1px;
        background-color: #fff;
        border-radius: 50%;
        box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
    }
    .el-dropdown-link {
        cursor: pointer;
        color: var(--el-color-primary);
        display: flex;
        align-items: center;
        padding-top: 17px;
        margin-left: 7px;
    }
</style>