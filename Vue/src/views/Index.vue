<template>
    <div style="height: 70px;line-height: 70px; border-bottom: 1px solid #cccccc ;display: flex; background-color: #eff1f9">
        <div style="width: 200px; padding-left: 25px; font-weight: bold;font-size: larger; color: crimson;font-family: 楷体; text-align: center">坤坤网</div>
        <div style="width: 650px;margin-left: 20px; cursor: pointer;font-weight: bold;font-size: 22px;font-family: 楷体; color: darkcyan; text-align: center">
            <div class="mirna" style="width: 25%; float: left;color: firebrick">MiRNA系统</div>
            <div class="community" @click="this.$router.push('/community')" style="width: 20%; float: left;margin-left: 15px">进入社区</div>
            <div class="other" style="width: 20%; float: left;margin-left: 15px">其他</div>
        </div>
        <div style="width: 150px; padding-left:50px"><img :src="Lyy1" style="width: 100px"/></div>
        <div style="width: 450px"></div>
        <div style="width: 300px; margin-left: 150px">
            <div style="width: 200px;margin-top: 15px">
                <img class="head" @click="this.$router.push('/user_info')" :src="icon ? FileName+icon : CommonName" referrerpolicy="no-referrer">
                <el-dropdown>
            <span class="el-dropdown-link">
                {{nick}}
                <el-icon style="margin-left: 3px"> <ArrowDown/></el-icon>
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
    </div>

    <div style="padding-top: 20px;background-color: #f7f8fc">
        <h1 style="text-align: center">欢迎来到MiRNA系统</h1>
    </div>

    <div style="padding-top: 20px;background-color: #f7f8fc">
<!--        <el-input style="width: 900px; padding-left: 600px;" placeholder="请输入疾病..." clearable v-model="disease"></el-input>-->
      <el-autocomplete style="width: 300px; margin-left: 600px" v-model="disease" placeholder="请输入疾病..." :fetch-suggestions="queryDisease" :trigger-on-focus="true" @select="searchDisease">
      </el-autocomplete>
        <el-button primary style="margin-left: 30px; background-color: lightcyan" @click="searchDisease()">搜索</el-button>
    </div>

    <div style="padding-top: 20px;background-color: #f7f8fc">
      <el-autocomplete style="width: 300px; margin-left: 600px" v-model="mirna" placeholder="请输入MiRNA..." :fetch-suggestions="queryMirna" :trigger-on-focus="true" @select="searchMirna">
      </el-autocomplete>
      <el-button primary style="margin-left: 30px; background-color: aliceblue" @click="searchMirna()">搜索</el-button>

    </div>



    <div style="height: 318px;background-color: #f7f8fc"></div>


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
    import { ArrowDown} from "@element-plus/icons";
    import {f} from "../utils/FileUtil";
    import request from "@/utils/request";
    import {ref} from "@vue/runtime-core";
    export default {
        name: "Index",
        data(){
            return{
              resp: [],
              resp_mirna: [],
                FileName: f(),
                CommonName : this.FileName+'BigProject/People/user_icon.jpg',
                icon: '',
                nick:'',
                LYY: require("@/utils/img/lyy.gif"),
                Lyy1: require("@/utils/img/lyy1.gif"),
                JJ: require("@/utils/img/jiyin.gif"),
                isconnect: false,
                user: [
                    {
                        nickname: '1234',
                        icon: this.LYY,
                    }
                ],
                disease: '',
                mirna: '',
            }
        },
        components:{
            ArrowDown
        },
        created() {
            this.load();
            request.get("/disease/GetAll").then(res=>{
            for(let i = 0;i<res.data.length;i++){
              this.resp[i] = res.data[i];
              this.resp[i].value = this.resp[i].diseaseName;
            }
          });
            request.get("/mirna/GetAll").then(res=>{
              for(let i = 0;i<res.data.length;i++){
                this.resp_mirna[i] = res.data[i];
                this.resp_mirna[i].value = this.resp_mirna[i].mirnaName;
              }
            });
          console.log(this.resp_mirna);
        },
        methods:{
            load(){
              let js = localStorage.getItem("king");
              if(js === null){
                  this.$router.push("/login");
                  return ;
              }
              this.user = JSON.parse(js);
              this.nick = this.user.nickname;
              this.icon = this.user.icon;
            },
            Leave(){
                this.$router.push("/login");
                localStorage.removeItem("king");
            },
            connect(){
                this.isconnect = true;
            },
          queryMirna(queryString,cb){
            if(queryString != ""){
              setTimeout(() => {
                let Arr = []; // 用来保存所有的mirna
                this.resp_mirna.forEach((item)=>{
                  if(item.value.indexOf(queryString) > -1){
                    Arr.push(item);
                  }
                });
                if(Arr.length == 0){
                  cb([{value : "暂无数据", price: "暂无数据"}]);
                }
                else{
                  cb(Arr);
                }
              }, 200);
            }
            else{
              cb([{value : "暂无数据", price: "暂无数据"}]);
            }

          },
          queryDisease(queryString,cb){
                if (queryString != "") {
                  // 输入内容以后才去做模糊查询
                  setTimeout(() => {
                    let callBackArr = []; // 准备一个空数组，此数组是最终返给输入框的数组
                    // 这个res是发请求，从后台获取的数据
                    this.resp.forEach((item) => {
                      // 把数据库做遍历，拿用户输入的这个字，和数据库中的每一项做对比
                      // if (item.value.indexOf(queryString) == 0) { // 等于0 以什么什么开头
                      if (item.value.indexOf(queryString) > -1) { // 大于-1，只要包含就行，不再乎位置
                        // 如果有具有关联性的数据
                        callBackArr.push(item); // 就存到callBackArr里面准备返回呈现
                      }
                    });
                    // 经过这么一波查询操作以后，如果这个数组还为空，说明没有查询到具有关联的数据，就直接返回给用户暂无数据
                    if (callBackArr.length == 0) {
                      cb([{ value: "暂无数据", price: "暂无数据" }]);
                    }
                    // 如果经过这一波查询操作以后，找到数据了，就把装有关联数据的数组callBackArr呈现给用户
                    else {
                      cb(callBackArr);
                    }
                  }, 200);
              }
                else{
                  cb([{ value: "暂无数据", price: "暂无数据" }]);
                }
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
              name: "SearchMirna",
              params: {
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
        padding-top: 17px;
        margin-left: 7px;
    }
    .divv{
        height: 100%;
        width: 300px;
        margin-left: 155px;
        float: left;
    }
    .head{
        width: 40px;
        height: 40px;
        padding: 1px;
        background-color: #fff;
        border-radius: 50%;
        box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
    }
    .community:hover{
        color: firebrick;
    }
    .other:hover{
        color: firebrick;
    }
</style>