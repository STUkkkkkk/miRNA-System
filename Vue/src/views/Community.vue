<template>
    <div style="z-index: 999; position: fixed;height: 70px;line-height: 70px; border-bottom: 1px solid #cccccc ;display: flex; background-color: #eff1f9">
        <div style="width: 200px; padding-left: 25px; font-weight: bold;font-size: larger; color: crimson;font-family: 楷体; text-align: center">坤坤网</div>
        <div style="width: 650px;margin-left: 20px; cursor: pointer;font-weight: bold;font-size: 22px;font-family: 楷体; color: darkcyan; text-align: center">
            <div class="mirna" @click="this.$router.push('/index')" style="width: 20%; float: left">MiRNA系统</div>
            <div class="community" style="width: 15%; float: left;margin-left: 10px;color: firebrick">进入社区</div>
            <div class="other" style="width: 15%; float: left;margin-left: 10px">其他</div>
        </div>
        <div style="width: 150px;margin-left: -50px  "><img :src="Lyy1" style="width: 100px"/></div>
        <div style="width: 130px"></div>
        <div style="width: 300px; margin-left: 150px">
            <div style="width: 200px;margin-top: 15px">
                <img class="head" @click="this.$router.push('/user_info')" :src="icon ? FileName+icon : CommonName">
                <el-dropdown>
            <span class="el-dropdown-link">
                {{nick}}
                <el-icon style="margin-left: 3px"> <ArrowDown/></el-icon>
            </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item @click="dialogVisible = true">发布帖子</el-dropdown-item>
                            <el-dropdown-item @click="this.$router.push('/user_info')">个人中心</el-dropdown-item>
                            <el-dropdown-item @click="Leave()">退出系统</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </div>
    </div>




    <div style="height: auto;width: 1470px; background-color: whitesmoke;">
        <div style="height:auto; width: 1100px; background-color: whitesmoke;float: left">
            <el-timeline style="margin-left: 50px; padding-top: 90px; margin-right: 50px;" >
                <div v-for="(blog,i) in blogs">
                    <el-timeline-item  :timestamp = blog.createTime.substring(0,10) placement="top" >
                        <el-card style="height: 155px">
                            <h3 @click="open(blog.id)" class="NAME" style="cursor: pointer;margin-top: -5px; margin-bottom: -10px">{{blog.title}}</h3><br>
                            <img :src="blog.icon ? FileName+blog.icon : JJ" style="width: 140px; height: 90px;float: left">
                            <div style="float: left;height: 90px; width: 760px; margin-left: 15px">
                                <div style="width: 760px; height: 60px">
                                    <p style="font-family: 华文楷体; font-size: 18px"> {{blog.abs}}</p>
                                </div>
                                <div style="width: 760px; height: 30px">
                                    <div style="font-family: 楷体; float: left; font-size: 18px; padding-top: 7px; margin-right: 30px">---{{blog.userName}}</div>
                                    <div  v-if="!blog.isMyself && !blog.isConcern" @click="Concern(i)">
                                        <el-button  size="small" style="float: left;margin-left: -25px; margin-right: 10px; margin-top: 5px;" plain type="primary">关注</el-button>
                                    </div>
                                    <div v-if="!blog.isMyself && blog.isConcern" @click="Concern(i)">
                                        <el-button size="small" style="float: left;margin-left: -25px; margin-right: 10px; margin-top: 5px;" plain type="info">取消关注</el-button>
                                    </div>
                                    <img @click="Liked(i)" v-if="blog.isLiked"  src="https://csdnimg.cn/release/blogv2/dist/pc/img/newHeart2021Active.png" referrerpolicy="no-referrer" style="width: 30px; height: 30px; float: left">
                                    <img @click="Liked(i)" v-if="!blog.isLiked"  src="https://csdnimg.cn/release/blogv2/dist/pc/img/newHeart2021White.png" referrerpolicy="no-referrer" style="width: 30px; height: 30px; float: left">
                                    <span style="font-family: 楷体;font-size: 17px;height: 30px; float: left; text-align: center;margin-top: 7px;margin-right: 10px">{{blog.liked}}</span>

<!--                                    收藏展示-->
                                    <img @click="Collected(i)" v-if="blog.isCollected" :src=star2 style="width: 20px; height: 20px;  float: left;  margin-left: 10px; margin-top: 7px">
                                    <img @click="Collected(i)" v-if="!blog.isCollected" :src=star1 style="width: 20px; height: 20px; float: left; margin-left: 10px; margin-top: 7px">
                                    <span style="font-family: 楷体;font-size: 17px;height: 30px; float: left; text-align: center;margin-top: 7px">{{blog.collection}}</span>

<!--                                    浏览量展示-->
                                    <div style="float: left;height: 30px; float: left; text-align: center;margin-left: 7px;">
                                        <el-icon size="20px" style="margin-top: 7px; margin-left: 10px"><View/></el-icon>
                                    </div>
                                    <div style="margin-left: 5px;font-family: 楷体;font-size: 17px;float: left;margin-top: 7px">
                                    {{blog.view}}
                                    </div>
                                    </div>
                            </div>
                        </el-card>
                    </el-timeline-item>
                </div>
            </el-timeline>
        </div>
        <el-divider direction="vertical" style="float: left;height: auto;margin-left: -8px; margin-right: -8px"></el-divider>

        <div style="position: fixed;height: 655px ;width: 370px;background-color: whitesmoke;margin-left: 1100px; margin-top: 70px">
            <el-input style="margin-left: 22px;margin-top: 15px; width: 70%" v-model="Search" placeholder="查询内容...."></el-input>
            <el-button @click="load" style="margin-left: 7px">搜索</el-button>
            <div style="width: 300px; height: 260px; background-color: white;margin-left: 35px;margin-top: 15px">
                <el-icon style="float: left; margin-top: 13px;margin-left: 10px;"><Sunny/></el-icon>
                <div style="width: 150px;height: 30px;margin-left: 7px;float: left;padding-top: 10px;font-family: 楷体;font-size: 20px">作者榜</div>
                <el-divider style="margin-left: 30px;float: left; margin-top: 10px; width: 80%;margin-bottom: 0px"></el-divider>

                <div v-for="(people, i) in peoples" style="float: left;width: 300px; height: 55px;cursor: pointer" class="People">
                    <img v-if="!(people.sort === null)" :src="require('@/'+people.sort)" style="float: left; margin-left: 5px;width: 35px;height: 35px;margin-top: 10px;margin-right: -10px">
                    <img :src="people.icon ? FileName+people.icon : CommonName" class="avator" >
                    <div style="float: left; margin-left: 8px; margin-top: 8px">
                        {{people.username}}
                        <br>
                        <div style="font-size: 10px;margin-top: 2px">第{{i+1}}名</div>
                    </div>
                    <div style="float: right;margin-right: 30px;margin-top: 18px; font-family: 楷体;font-size: 20px">{{people.blogCount ? people.blogCount : 0}}篇</div>
                </div>

                <el-divider style="margin-left: 30px;float: left; margin-top: 10px; width: 80%;margin-bottom: 0px"></el-divider>

                <div style="width: 300px;margin-top: 10px;font-family: 楷体;font-size: 20px;cursor: pointer;float: left;text-align: center">完整榜单>></div>
            </div>

            <div style="width: 300px; height: 320px; background-color: white;margin-left: 35px;margin-top: 20px">
                <div style="width: 300px;height: 50px;font-family: 楷体;text-align: center;padding-top: 10px;font-size: 25px;">最新推荐</div>
                <el-divider style="margin: 0px"></el-divider>

                <div v-for="(blog) in blogs1">
                    <div @click="open(blog.id)" class="Blog" style="cursor: pointer;padding-left: 10px;width: 300px;height: 50px;font-family: 楷体;text-align: left;padding-top: 15px;font-size: 20px;">
                        {{blog.title.substring(0,10) + '...'}}</div>
                    <div style="margin-top: -37px;float:right;">>>></div>
                    <el-divider style="margin: 0px"></el-divider>
                </div>

            </div>

        </div>

    </div>

    <el-dialog
            v-model="dialogVisible"
            title="发布帖子"
            :close-on-click-modal="false"
            width="30%">
        <el-form :model="form" label-width="120px">
            <el-form-item label="封面">
                <el-upload
                        style="width: 150px; height: 80px;text-align: center;border: 1px dashed var(--el-border-color);border-radius: 6px;"
                        action="#"
                        :show-file-list="false"
                        :http-request="uploadImage"
                        :before-upload="beforeAvatarUpload">

                    <img v-if="form.icon" style="width: 150px; height: 80px;cursor: pointer" :src="form.icon ? FileName+form.icon : require('@/utils/img/1665309773736.jpg')" />
                    <el-icon v-else class="avatar-uploader-icon" style="width: 150px; height: 80px;cursor: pointer; border: #3a3747"><Plus /></el-icon>
                </el-upload>
            </el-form-item>

            <el-form-item label="标题">
                <el-input maxlength="30" v-model="form.title" style="width: 90%"></el-input>
            </el-form-item>
            <el-form-item label="类型">
                <el-tag type="success" style="cursor: pointer" @click="TypeWay()">{{AddType.typeName}}</el-tag>
            </el-form-item>

            <el-form-item label="摘要">
                <el-input maxlength="70" v-model="form.abs" style="width: 90%"></el-input>
            </el-form-item>

            <el-form-item label="内容">
                <el-input type="textarea" maxlength="200" v-model="form.content" minlength="5" style="width: 90%"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save()">发布</el-button>
      </span>
        </template>
    </el-dialog>

    <el-dialog v-model="TypeShow" title="选择发帖类型" width="40%">
      <el-input v-model="TypeSearch" style="width: 70%;margin-left: 55px"></el-input>
      <el-button @click="SearchTypes" type="primary" plain style="margin: 5px;cursor: pointer;" >搜索</el-button>
      <br>
        <el-tag type="success" v-if="types" style="margin: 5px;cursor: pointer;" size="large" v-for="(type,index) in types" @click="AddType = type;form.typeId = type.id;TypeShow = false">{{type.typeName}}</el-tag>
    </el-dialog>


</template>


<script>
    import { ArrowDown, View,Sunny,Plus} from "@element-plus/icons";
    import request from "../utils/request";
    import {f} from "../utils/FileUtil";

    export default {
        name: "Community",

        data(){
            return{
              TypeSearch: '',
                FileName: f(),
                CommonName : this.FileName+'BigProject/People/user_icon.jpg',
                icon: '',
                TypeShow: false,
                isDianZan: false,
                nick:'',
                dialogVisible: false,
                AddType: {
                  id: -1,
                  typeName: '选择类型'
                },
                types: ['校园趣事','体育运动'],
                form:{
                    title: '',
                    abs: '',
                    icon: '',
                    content: '',
                    typeId: -1,
                },
                LYY: require("@/utils/img/lyy.gif"),
                Search:'',
                Lyy1: require("@/utils/img/lyy1.gif"),
                JJ: require("@/utils/img/niubi.png"),
                star1: require("@/utils/img/ok1.jpg"),
                star2: require("@/utils/img/ok2.jpg"),
                user: [],
                blogs: [
                    {
                        id: 1,
                        userId: 111,
                        title: '测试使用 测试使用 测试使用 测试使用 测试使用 ',//标题
                        author: '测试使用 ',//作者
                        liked: 12,//点赞数
                        abs: '测试使用 ',//摘要
                        createTime: '2018-04-12 20:46',//时间
                        icon: 'utils/img/niubi.png', //图片
                        userName: '无敌暴龙战士', //用户名字
                        view: 1000, //浏览量
                        collection: 12,
                        isLiked: false,
                        isCollected: false,
                        isConcern: false,
                        isMyself: false,
                    }
                ],
                blogs1: [],
                peoples:[],
            }
        },
        components:{
            ArrowDown,
            View,
            Sunny,
            Plus
        },
        created() {
            this.load();
        },
        methods:{
            load(){
                let js = localStorage.getItem("king");
                if(js === null){
                    this.$router.push("/login");
                    return;
                }
                this.user = JSON.parse(js);
                this.nick = this.user.nickname;
                this.icon = this.user.icon;
                //查询所有的blog
                request.get("/blog",
                    {
                        params:{
                            Search: this.Search
                        }
                    }).then(res=>{
                    this.blogs = res.data;
                })
                request.get("/blog/GetCount/5").then(res=>{
                    this.blogs1 = res.data;
                })

                request.get("/user/ByBlogCount/3").then(res=>{
                   this.peoples = res.data;
                    console.log(res)
                });
            },
            save(){
                 //保存blog
              if(this.form.typeId === -1){
                this.$message(
                    {
                      type: 'error',
                      message: "请选择一种类型叭",
                    }
                )
                return;
              }
              request.post("/blog",this.form).then(res=>{
                if(res.code === "0"){
                  this.dialogVisible = false;
                  this.blogs.unshift(res.data);
                  this.$message(
                      {
                        type: 'success',
                        message: '发布成功'
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
              });
            },
            Leave(){
                this.$router.push("/login");
                localStorage.removeItem("king");
            },
           TypeWay(){
            this.TypeShow = true;
            this.SearchTypes();
          },
          SearchTypes(){
            request.get("/type",{
              params:{
                typeSearch: this.TypeSearch
              }
            }).then(res=>{
              this.types = res.data;
            });
          },
            uploadImage(param){
                const formData = new FormData();
                formData.append('file', param.file);
                let file = formData;
                request.post("/qiNiu/Blogupload",file).then(res=>{
                    if(res.code === "0"){
                        this.form.icon = res.data;
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
            open(i){
              //打开对应的帖子页面
                this.$router.push(
                    {
                        path: '/blog_info',
                        query: {
                            id: i,
                        }
                    }

                )
            },
            connect(){
                this.isconnect = true;
            },
            Liked(i){
                //对于点赞操作，我们直接让后端去解决。
                request.put("/like",this.blogs[i]).then(res=>{
                    if(!(res.code === "0")){
                        this.$message({
                            type: 'error',
                            message: res.message
                        })
                    }
                    else{
                        let isOk = this.blogs[i].isLiked;
                        this.blogs[i].liked = isOk ? this.blogs[i].liked - 1 : this.blogs[i].liked + 1;
                        this.blogs[i].isLiked = !isOk;
                    }
                })
            },
            Collected(i){
                //收藏同理，让后端干
                request.put("/collect", this.blogs[i]).then(res =>{
                    if(!(res.code === "0")){
                        this.$message({
                            type: 'error',
                            message: res.message
                        })
                    }
                    else{
                        let isOk = this.blogs[i].isCollected;
                        this.blogs[i].collection = isOk ? this.blogs[i].collection - 1 : this.blogs[i].collection + 1;
                        this.blogs[i].isCollected = !isOk;
                    }
                })
            },
            Concern(i){
                //关注也是,业务逻辑全给后端，但是其实后端也是坤坤自己捏/(ㄒoㄒ)/~~
                request.put("/concern",this.blogs[i]).then(res=>{
                    if(!(res.code === "0")){
                        this.$message({
                            type: 'error',
                            message: res.message
                        })
                    }
                    else{//如果关注了，所有的这个人都要被关注，或取关;
                        let user_id = this.blogs[i].userId;
                        for(let i = 0; i<this.blogs.length;i++){
                            if(this.blogs[i].userId == user_id){
                                this.blogs[i].isConcern = !this.blogs[i].isConcern;
                            }
                        }
                    }
                })
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
    .head{
        width: 40px;
        height: 40px;
        padding: 1px;
        background-color: #fff;
        border-radius: 50%;
        box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
    }
    .mirna:hover{
        color: firebrick;
    }
    .other:hover{
        color: firebrick;
    }
    .el-card:hover{
        margin-top: -5px;
    }
    .GaoLiang{
        color: aqua !important;
    }
    .BuGaoLiang{
        color: #cccccc !important;
    }
    .View{
        display: block;
    }
    .NoView{
        display: none;
    }
    .avator{
        width: 40px;
        height: 40px;
        padding: 1px;
        background-color: #fff;
        margin-left: 20px;
        margin-top: 8px;
        float: left;
        border-radius: 50%;
        box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
    }
    .People:hover{
        background-color: #cccccc;
    }
    .Blog:hover{
        background-color: #cccccc;
    }
</style>