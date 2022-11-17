<template>
    <!--左边-->
    <div style="width: 250px; height: 650px; background-color: #f7f8fc">
        <!--        头像区域-->
        <div style="width: 100%; height: 250px;">
            <img :src="user.icon ? FileName+user.icon : CommonName" style="width: 150px; height: 150px; margin-left: 50px; margin-top: 50px"><br/>
            <div style="width: 100%;text-align: center; font-size: 30px; margin-top: 10px; font-family: 宋体">{{user.nickname}}</div>
        </div>
        <!--        头像区域-->
        <!--        分别搜索-->
        <div style="width: 100%; height: 320px; margin-top: 80px">
            <div @click="this.$router.push('/user_info')" :class="['s1',$route.meta.index == 0 ? 's2':'']">个人信息</div>
            <div @click="this.$router.push('/search_info')" id="search_info"  :class="['s1',$route.meta.index == 1 ? 's2':'']">搜索记录</div>
            <div @click="this.$router.push('/buy_info')" id="buy_info"  :class="['s1',$route.meta.index == 2 ? 's2':'']">交易记录</div>
            <div @click="this.$router.push('/send_Blog')" id="Send_Blog"  :class="['s1',$route.meta.index == 3 ? 's2':'']">发帖记录</div>
        </div>
        <!--        分别搜索-->
    </div>
    <!--左边-->
</template>

<script>
    import {ArrowDown} from "@element-plus/icons";
    import {f} from "../utils/FileUtil";
    export default {
        name: "User_Aside",
        components: {
            ArrowDown
        },
        created() {
            this.load();
        },
        data(){
            return {
                FileName: f(),
                CommonName : this.FileName+'BigProject/People/user_icon.jpg',
                LYY: require("@/utils/img/lyy.gif"),
                Lyy1: require("@/utils/img/lyy1.gif"),
                user:[
                    {
                        icon: this.LYY,
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
            },
        }
    }
</script>

<style scoped>
    .s1{
        text-align: center;
        font-size: 20px;
        font-family: 宋体;
        margin-top: 30px;
        cursor: pointer;
    }
    .s1:hover{
        color: darkorchid;
    }
    .s2{
        color: darkorchid;
    }
</style>