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
                        <el-dropdown-item @click="this.$router.push('/user_info')">个人中心</el-dropdown-item>
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

    <div style="height: auto; background-color: mintcream">
        <h2 style="text-align: center; color: gold; padding-top: 10px; padding-bottom: 10px">关于{{message}}以及相应的MIRNA的搜索结果为: </h2>

        <el-table
                :data="tableData" style="width: 100%; background-color: mintcream"
        >
<!--          pmid,title,type,authors,doi,keywords,library,abs,date-->
          <el-table-column prop="pmid" label="论文id" sortable></el-table-column>
            <el-table-column prop="title" label="论文标题" ></el-table-column>
            <el-table-column prop="authors" label="作者" ></el-table-column>
          <el-table-column prop="type" label="论文类型" ></el-table-column>
            <el-table-column prop="doi" label="论文doi" ></el-table-column>
            <el-table-column prop="library" label="图书馆" ></el-table-column>
          <el-table-column prop="date" label="发表年限" ></el-table-column>
            <el-table-column label="详情">
                <template #default="scope">
                    <el-button @click="enter(scope.row)">进入论文</el-button>
                </template>
            </el-table-column>
        </el-table>


    </div>
  <footer style="background-color: lightcyan; height: 175px;width: 100%">
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
              needRefresh: true,
              pageNum: 1,
              pageSize:10,
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
      beforeRouteLeave(to, from, next) {
        //判断将要去的组件名或者组件路径
        if (to.name == "ArticleInformation") {
          //在当前页进行设置缓存为true
          from.meta.keepAlive = true;
        } else {
          // 如果是去往其他页面就执行一下操作
          // 清除上一次保留的keepAlive缓存
          let vnode = this.$vnode;
          let parentVonde = vnode && vnode.parent;
          if (
              parentVonde &&
              parentVonde.componentInstance &&
              parentVonde.componentInstance.cache
          ) {
            var key =
                vnode.key == null
                    ? vnode.componentOptions.Ctor.cid +
                    (vnode.componentOptions.tag
                        ? `::${vnode.componentOptions.tag}`
                        : "")
                    : vnode.key;
            var cache = parentVonde.componentInstance.cache;
            var keys = parentVonde.componentInstance.keys;
            if (cache[key]) {
              this.$destroy();
              // remove key
              if (keys.length) {
                var index = keys.indexOf(key);
                if (index > -1) {
                  keys.splice(index, 1);
                }
              }
              cache[key] = null;
            }
          }
        }
        next();
      },
            methods: {
            load(){
                let js = localStorage.getItem("king");
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
                      disease: this.disease? this.disease:'',
                      pageNum: this.pageNum,
                      pageSize: this.pageSize
                    }
                    }
                ).then(res=>{
                    console.log(res)
                    this.tableData = res.data.diseases;
                })
            },
            enter(row){
                let pmid = row.pmid;
                this.$router.push(
                    {
                        path: "/articleInformation",
                        query: {
                            articleId: pmid,
                        }
                    });
            },
            find(){
                this.message = this.disease;
                request.get("/searchDisease",{
                        params:{
                          disease: this.disease? this.disease:'',
                          pageNum: this.pageNum,
                          pageSize: this.pageSize
                        }
                    }
                ).then(res=>{
                    console.log(res)
                    this.tableData = res.data.diseases;
                })
            },
            Leave(){
                this.$router.push("/login");
                localStorage.removeItem("king");
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