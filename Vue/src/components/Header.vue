<template>
    <div style="height: 50px;line-height: 50px; border-bottom: 1px solid #cccccc ;display: flex">
        <div style="width: 200px; padding-left: 25px; font-weight: bold ; color: aqua">后台管理</div>
        <div style="width: 600px; padding-left: 500px; font-weight: bold; color: darkcyan; cursor: pointer" @click="this.$router.push('/index')">MiRNA系统</div>
        <div style="flex: 1px"></div>
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
</template>

    <script>
    import { ArrowDown} from "@element-plus/icons";
    export default {
        name: "Header.vue",
        data(){
          return{
              nickname: '张三'
          }
        },
        created() {
            let json = sessionStorage.getItem("user");
            if(json)
            this.nickname = JSON.parse(json).nickname;
        },
        components:{
            ArrowDown
        },
        methods:{
            Leave(){
                sessionStorage.removeItem("user")
                this.$router.push("/login")
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
        padding-top: 15px;
    }
</style>