<template>
    <div style="height: 70px;line-height: 70px; border-bottom: 1px solid #cccccc ;display: flex; background-color: lightcyan">
        <div style="width: 200px; padding-left: 25px; font-weight: bold;font-size: larger; color: gold; text-align: center">MiRNA系统</div>
        <div style="flex: 1px; padding-left: 500px"><img :src="LYY" style="width: 100px"/></div>
        <div style="width: 100px; padding-right: 25px">
            <el-dropdown>
            <span class="el-dropdown-link">
                {{nickname}}
                <el-icon> <ArrowDown/></el-icon>
            </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item @click="this.$router.push('/people')">个人信息</el-dropdown-item>
                        <el-dropdown-item @click="Leave()">退出系统</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>

    <div style="padding-top: 20px; background-color: mintcream">
        <el-input style="width: 1000px; padding-left: 550px;" placeholder="请输入疾病..." clearable v-model="disease"></el-input>
        <el-button primary style="margin-left: 30px; background-color: lightcyan" @click="searchDisease()">搜索</el-button>
    </div>

    <div style="height: 455px; background-color: mintcream">
        <h2 style="text-align: center; color: gold; padding-top: 10px; padding-bottom: 10px">关于{{message}}以及相应的MIRNA的搜索结果为: </h2>

        <el-table
                :data="tableData" style="width: 100%; background-color: mintcream"
        >
            <el-table-column prop="id" label="MiRNA‘s ID" sortable></el-table-column>
            <el-table-column prop="rnaname" label="MiRNA" ></el-table-column>
            <el-table-column prop="title" label="论文标题" ></el-table-column>
            <el-table-column prop="writer" label="作者" ></el-table-column>
            <el-table-column prop="doi" label="论文doi" ></el-table-column>
            <el-table-column prop="nlm" label="论文类型" ></el-table-column>
            <el-table-column prop="pt" label="刊物" ></el-table-column>
            <el-table-column prop="year" label="发表年限" ></el-table-column>
            <el-table-column prop="abs" label="摘要" ></el-table-column>
            <el-table-column label="详情">
                <template #default="scope">
                    <el-button @click="enter(scope.row)">进入论文</el-button>
                </template>
            </el-table-column>
        </el-table>


    </div>

    <el-divider style="margin: 0"></el-divider>

    <footer style="background-color: lightcyan; height: 175px">
        <div class="divv">
            <p style="text-align: center; margin-top: 30px; color: purple">好好编程，天天向上</p>
            <p style="text-align: center; margin-top: 30px;color: purple">Springboot+Vue</p>
            <p style="text-align: center; margin-top: 30px;color: purple">MiRNA与疾病系统</p>
        </div>
        <div class="divv">
            <p style="text-align: center; margin-top: 30px; color: purple" @click="connect()">联系我们</p>
            <p style="text-align: center; margin-top: 30px;color: purple" @click="this.$router.push('/about')">关于开发者团队</p>
            <p style="text-align: center; margin-top: 30px;color: purple">机器学习</p>
        </div>
        <div class="divv">
            <p style="text-align: center; margin-top: 30px; color: purple">该项目用于学习，</p>
            <p style="text-align: center; margin-top: 30px;color: purple">欢迎联系开发者团队</p>
            <p style="text-align: center; margin-top: 30px;color: purple">一起学习，一起努力！</p>
        </div>
    </footer>

    <el-dialog v-model="isconnect" title="联系开发者" width="20%" style="text-align: center">
        <span class="dialog-footer">
            <img :src="Lyy1" style="width: 200px; padding-left: 50px"/>
            <p style="padding-left: 30px;">开发者: 懒羊羊</p>
            <p style="padding-left: 30px; padding-top: 10px">联系电话: 13415100179</p>
            <p style="padding-left: 30px; padding-top: 10px; padding-bottom: 10px">邮箱: 20jkwu1@stu.edu.cn</p>
            <el-button style="margin-left: 100px; background-color: khaki" @click="this.isconnect = false">确定</el-button>
        </span>
    </el-dialog>
</template>

<script>
    import request from "../utils/request";
    import {ArrowDown} from "@element-plus/icons";

    export default {
        name: "SearchDisease",
        components:{
            ArrowDown
        },
        data(){
            return{
                message:'',
                disease: '',
                tableData: [],
                LYY: require("@/utils/img/lyy.gif"),
                Lyy1: require("@/utils/img/lyy1.gif"),
                JJ: require("@/utils/img/jiyin.gif"),
                nickname: '请登录',
                isconnect: false,
            }
        },
        methods: {
            load(){
                let js = sessionStorage.getItem("king");
                if(!js){
                    this.nickname = "请登录";
                }
                else{
                    this.nickname = JSON.parse(js).nickname;
                }
                this.disease = this.$route.params.disease;
                this.message = this.disease;
                request.get("/searchDisease",{
                    params:{
                        disease: this.disease,
                    }
                    }
                ).then(res=>{
                    console.log(res)
                    this.tableData = res.data;
                })
            },
            enter(row){

            },
            find(){
                this.message = this.disease;
                request.get("/searchDisease",{
                        params:{
                            disease: this.disease,
                        }
                    }
                ).then(res=>{
                    console.log(res)
                    this.tableData = res.data;
                })
            },
            Leave(){
                this.$router.push("/login");
                sessionStorage.removeItem("king");
            },
            connect(){
                this.isconnect = true;
            },
            searchDisease(){
                this.find();
            },
        },
        created() {
            this.load();
        }
    }
</script>

<style scoped>
    .el-dropdown-link {
        cursor: pointer;
        color: var(--el-color-primary);
        display: flex;
        align-items: center;
        padding-top: 25px;
    }
    .divv{
        height: 100%;
        width: 300px;
        margin-left: 155px;
        float: left;
    }
</style>