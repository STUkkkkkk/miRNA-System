<template>

    <div style="height: 70px;line-height: 70px; border-bottom: 1px solid #cccccc ;display: flex">
        <div style="width: 200px; padding-left: 25px; font-weight: bold;font-size: larger; color: gold; text-align: center">MiRNA系统</div>
        <div style="flex: 1px; padding-left: 500px"><img :src="Lyy1" style="width: 100px"/></div>
        <div style="width: 100px; padding-right: 25px">
            <el-dropdown>
            <span class="el-dropdown-link" @click="this.$router.push('/login')">
                <el-icon><User/></el-icon>
                {{nickname}}
            </span>
            </el-dropdown>
        </div>
    </div>

    <div style="padding-top: 20px">
        <h1 style="text-align: center">欢迎来到MiRNA系统</h1>
    </div>

    <div style="padding-top: 20px">
        <el-input style="width: 900px; padding-left: 600px; background-color: lightcyan" placeholder="请输入疾病..." clearable v-model="disease"></el-input>
        <el-button primary style="margin-left: 30px; background-color: lightcyan" @click="searchDisease()">搜索</el-button>
    </div>

    <div style="padding-top: 20px">
        <el-input style="width: 900px; padding-left: 600px; background-color: lavender" placeholder="请输入MiRNA..." clearable v-model="mirna"></el-input>
        <el-button primary style="margin-left: 30px; background-color: lavender" @click="searchMirna()">搜索</el-button>
    </div>

    <!--    <div style="">-->
    <!--        <img :src="JJ" style="padding-left: 500px ; padding-top: 30px; width: 1200px"/>-->
    <!--    </div>-->

    <div style="height: 350px">
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
            <img :src="LYY" style="width: 200px; padding-left: 50px"/>
            <p style="padding-left: 30px;">开发者: 懒羊羊</p>
            <p style="padding-left: 30px; padding-top: 10px">联系电话: 13415100179</p>
            <p style="padding-left: 30px; padding-top: 10px; padding-bottom: 10px">邮箱: 20jkwu1@stu.edu.cn</p>
            <el-button style="margin-left: 100px; background-color: khaki" @click="this.isconnect = false">确定</el-button>
        </span>
    </el-dialog>
</template>

<script>
    import { User} from "@element-plus/icons";
    export default {
        name: "Index",
        data(){
            return{
                nickname: '请登录',
                LYY: require("@/utils/img/lyy.gif"),
                Lyy1: require("@/utils/img/lyy1.gif"),
                JJ: require("@/utils/img/jiyin.gif"),
                isconnect: false,
                disease: '',
                mirna: '',
            }
        },
        components:{
            User
        },
        created() {
            let king = localStorage.getItem("king")
            let people = localStorage.getItem("user")
            if(king != null || people != null){
                this.$router.push("/index");
            }
            this.load();
        },
        methods:{
            load(){
                let js = localStorage.getItem("king");
                if(!js){
                    this.nickname = "请登录";
                }
                else{
                    this.nickname = JSON.parse(js).nickname;
                }
            },
            Leave(){
                this.$router.push("/login");
                localStorage.removeItem("king");
            },
            connect(){
                this.isconnect = true;
            },
            searchDisease(){
                this.$router.push({
                    name: "SearchDisease",
                    params: {
                        disease: this.disease,
                    }
                });
            },
            searchMirna(){
                this.$router.push({
                    path: "/searchMirna",
                    query: {
                        mirna: this.mirna,
                    }
                });
            }
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