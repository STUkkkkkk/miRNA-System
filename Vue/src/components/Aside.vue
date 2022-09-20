<template>
    <div>
        <el-menu
                style="width: 200px ; height: calc(100vh - 50px)"
                default-active="user"
                router
                default-openeds="1"
                class="el-menu-vertical-demo"
        >
            <el-sub-menu index="1">
                <template #title>系统管理</template>
                    <el-menu-item index="user" >用户管理</el-menu-item>
                <el-menu-item index="disease">疾病管理</el-menu-item>
                <el-menu-item index="mirna">MiRNA管理</el-menu-item>
                <el-menu-item index="article">论文管理</el-menu-item>
            </el-sub-menu>
        </el-menu>
    </div>
</template>

<script>
    import { } from "@element-plus/icons";
    export default {
        name: "Aside",
        components:{
        },
        methods:{
        }
    }
</script>

<style scoped>

</style>