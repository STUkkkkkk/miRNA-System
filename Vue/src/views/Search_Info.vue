<template>
    搜索记录
</template>

<script>
    import { ArrowDown} from "@element-plus/icons";
    export default {
        name: "Search_Info",
        components: {
            ArrowDown
        },
        created() {
            this.load();
        },
        data(){
            return {
                user:[],
                LYY: require("@/utils/img/lyy.gif"),
                Lyy1: require("@/utils/img/lyy1.gif"),
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

</style>