<template>

<!--这个是帖子的详细页面-->
    <div style="width: 1480px; height: auto;background-color: whitesmoke;float: left">

        <div style="position:fixed;width: 190px; height:720px ; background-color:whitesmoke;float: left">
            <div @click="this.$router.back()" style="width: 170px; height: 50px;text-align: center; background-color: white;cursor: pointer; font-family: 楷体;font-size: 30px;padding-top: 10px;"> 返回 </div>

            <div style="float: right;width: 50px; height: 600px;">

                <div style="border-radius: 50%;box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
                width: 50px;height: 50px;float: left; margin-top: 50px; background-color: white;cursor: pointer">
                    <el-badge :value="Blog.liked">
                        <img @click="Liked()" v-if="Blog.isLiked"  src="https://csdnimg.cn/release/blogv2/dist/pc/img/newHeart2021Active.png" referrerpolicy="no-referrer" style="width: 40px; height: 40px; float: left;margin-top: 4px; margin-left: 6px">
                        <img @click="Liked()" v-if="!Blog.isLiked"  src="https://csdnimg.cn/release/blogv2/dist/pc/img/newHeart2021White.png" referrerpolicy="no-referrer" style="width: 40px; height: 40px; float: left;margin-top: 4px;margin-left: 6px">
                    </el-badge>
                </div>

                <div style="border-radius: 50%;box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
                width: 50px;height: 50px;float: left; margin-top: 50px; background-color: white;cursor: pointer">
                    <el-badge :value="Blog.collection">
                        <img @click="Collected()" v-if="Blog.isCollected" :src=star2  style="width: 50px; height: 50px; float: left;border-radius: 50%;box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);">
                        <img @click="Collected()" v-if="!Blog.isCollected" :src=star1  style="width: 50px; height: 50px; float: left;border-radius: 50%;box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);">
                    </el-badge>
                </div>

                <div style="border-radius: 50%;box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
                width: 50px;height: 50px;float: left; margin-top: 50px; background-color: white;cursor: pointer">
                    <el-badge :value="Blog.comments">
                        <img @click="toComment()" src="https://csdnimg.cn/release/blogv2/dist/pc/img/newComment2021Black.png" referrerpolicy="no-referrer" style="width: 40px; height: 40px; float: left;margin-top: 6px; margin-left: 5px">
                    </el-badge>
                </div>

                <div style="border-radius: 50%;box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
                width: 50px;height: 50px;float: left; margin-top: 50px; background-color: white;cursor: pointer">
                    <el-badge :value="Blog.view">
                        <View style="width: 40px; height: 40px; float: left;margin-top: 6px; margin-left: 5px"/>
                    </el-badge>
                </div>


                <div style="border-radius: 50%;box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
                width: 50px;height: 50px;float: left; margin-top: 50px; background-color: white;cursor: pointer">
                    <ArrowUpBold @click="toTop()" style="width: 40px; height: 40px; float: left;margin-top: 6px; margin-left: 5px"/>
                </div>

                <!--                <div style="float: left; margin-top: 50px; cursor: pointer">-->
<!--                    <el-badge :value="Blog.collection">-->
<!--                        <img @click="Collected(i)" v-if="Blog.isCollected" :src=star2 style="width: 40px; height: 40px;  float: left; ">-->
<!--                        <img @click="Collected(i)" v-if="!Blog.isCollected" :src=star1 style="width: 40px; height: 40px; float: left; ">-->
<!--                    </el-badge>-->
<!--                </div>-->

<!--                <div style="float: left; margin-top: 50px; cursor: pointer">-->
<!--                    <el-badge :value="Blog.liked">-->
<!--                        <img @click="" v-if="Blog.isLiked"  src="https://csdnimg.cn/release/blogv2/dist/pc/img/newComment2021Black.png" referrerpolicy="no-referrer" style="width: 40px; height: 40px; float: left">-->
<!--                        <img @click="" v-if="!Blog.isLiked"  src="https://csdnimg.cn/release/blogv2/dist/pc/img/newComment2021Black.png" referrerpolicy="no-referrer" style="width: 40px; height: 40px; float: left">-->
<!--                    </el-badge>-->
<!--                </div>-->

            </div>
<!--            旁栏-->
        </div>
<!--           主体-->
        <div style="height: 720px">
            <div class="Body">
                <div style="width: 720px; height: auto">
                    <h1 ref="top" style="margin-left: 50px;margin-top: 20px">{{Blog.title}}</h1>
                </div>

                <div style="margin-left: 50px;width: 720px; height: 50px; margin-top: 20px">
                    <img class="head" style="margin-top: 5px;float: left"
                         @click="this.$router.push('/user_info')"
                         :src="Blog.userIcon ? FileName+Blog.userIcon : CommonName"
                    >
                    <!--                头像+ 一些记录-->
                    <div style="float:left;margin-left: 10px">
                        <p style="font-family: 楷体;font-size: 20px">{{Blog.userName}}</p><br>
                        <p style="margin-top: -16px;font-family: 楷体;font-size: 15px;">{{new Date(Blog.createTime).toLocaleDateString() +' '+ new Date(Blog.createTime).toLocaleTimeString() }} · {{Blog.view}}阅读</p>
                    </div>
                </div>

                <!--            这里开始是blog.主体了-->
                <div style="width: 720px; height: auto;margin-top: 20px">
                    <p style="text-indent:2em; font-family: 楷体;font-size: 22px;margin-bottom: 30px">{{Blog.content}}</p>
                </div>
            </div>
            <!--        接下来是评论-->
            <div class="Comment">
                <div class="Font_Mid" style="margin-left: 20px">评论</div>
                <!--            这里是发布评论的地儿-->
                <div style="height: auto;width: 100%;float: left;padding-top: 20px;" ref="pinglun">
                    <img style="float: left" :src="user.icon ? FileName+user.icon : CommonName" class="Picture_Mid">
                    <el-input
                            @click="ok = true"
                            v-model="comment_content"
                            maxlength="200"
                            placeholder="请输入评论....."
                            show-word-limit
                            :rows="3"
                            type="textarea"
                            style="width: 85%;margin-left: 5px;"
                    />
                    <el-button @click="PutComment()" style="float:right;margin-right: 45px; margin-top: 10px" size="30px" v-show="ok" type="primary">发表评论</el-button>
                </div>
                <!--z              这里是看评论-->
                <div style="float: left;margin-left: 20px;margin-bottom: -15px; margin-top: 20px" class="Font_Mid">全部评论{{Comments.length}}</div>
                <el-divider class="Font_Mid" style="float: left;margin-bottom: 0px"></el-divider>

                <div v-for="(comments,index) in Comments" style="width: 100%; height: auto; float: left">
                    <img style="float: left; margin-top: 10px" :src="comments.userIcon ? FileName+comments.userIcon : require('@/utils/img/user_icon.jpg')" class="Picture_Mid">
                    <div style="float:left;width: 720px;margin-left: 10px;height: auto">
                        <div style="margin-top: 10px;">
                            <p style="float:left;">{{comments.userName}}</p>
                            <p style="float: left;margin-left: 560px; color: red;cursor: pointer" @click="deleteComment(comments.id,index)" v-show = "comments.userId == this.user.id">删除</p>
                            <p style="float:right;">{{comments.time}}</p>
                        </div>
                        <br>
                        <p style="margin-top: 5px">{{comments.content}}</p>
                    </div>
                    <div style="width: 720px;margin-top: 15px; height: auto; float: left;margin-bottom: 10px">
                        <!--                    评论的点赞-->
                        <img @click="CommentLiked(comments.id,index)" v-if="comments.isLiked"  src="https://csdnimg.cn/release/blogv2/dist/pc/img/newHeart2021Active.png" referrerpolicy="no-referrer" style="cursor: pointer;margin-left: 80px;width: 20px; height: 20px; float: left">
                        <img @click="CommentLiked(comments.id,index)" v-if="!comments.isLiked"  src="https://csdnimg.cn/release/blogv2/dist/pc/img/newHeart2021White.png" referrerpolicy="no-referrer" style="margin-left: 80px;cursor: pointer;width: 20px; height: 20px; float: left">
                        <div style="float: left;margin-left: 2px">{{comments.liked}}</div>
                        <p style="margin-left: 30px; float:left;cursor: pointer" @click="comments.okComment = !comments.okComment">
                            <img src="https://csdnimg.cn/release/blogv2/dist/pc/img/newComment2021Black.png" referrerpolicy="no-referrer" style="float:left;width: 20px;height: 20px;margin-top: 1px;margin-right: 3px">
                            {{comments.children.length}}</p>
                        <el-input maxlength="200" v-show="comments.okComment" rows="3" :placeholder="'回复 @'+comments.userName" type="textarea" style="width: 90%; float:left;margin-left: 80px" v-model="reply_content[index]"></el-input>
                        <el-button v-show="comments.okComment" style="float:right; margin-top: 10px" size="30px" type="primary" @click="reply(comments.id,index)">发表回复</el-button>
                    </div>
                    <!--                芝士回复-->
                    <div style="width: 700px; height: auto;float:left; margin-bottom: 30px;margin-left: 80px; background-color: whitesmoke">
                        <div v-for="(chileComment,i) in comments.children" style="width: 700px;height: auto;float: left">
                            <div style="width: 700px; float:left; height: 40px" >
                                <img style="float: left; margin-top: 10px" :src="chileComment.userIcon ? FileName+chileComment.userIcon : require('@/utils/img/user_icon.jpg')" class="Picture_Min">
                                <div style="padding-top: 12px;margin-left: 5px;float: left">{{chileComment.userName}}</div>
                                <div v-show="!(chileComment.bereplyName === null)" style="float:left; margin-left: 10px;padding-top: 12px; color: #c0ccda">回复</div>
                                <div v-show="!(chileComment.bereplyName === null)" style="float:left; margin-left: 10px;padding-top: 12px; color: deepskyblue">{{chileComment.bereplyName}}</div>
                                <div style="float: right;padding-top: 12px;margin-right: 15px">{{chileComment.time}}</div>
                            </div>
                            <div style="width: 600px; float:left; height: auto;margin-left: 60px;margin-top: 8px"  @click="chileComment.okComment = true" class="REPLY">
                                {{chileComment.content}}
                            </div>
                            <div style="width: 700px; height: auto;margin-top: 10px; float: left;margin-bottom: 10px">
                                <!--                            <p style="margin-left: 30px; float:left;cursor: pointer" @click="chileComment.okComment = !chileComment.okComment"><Message/>回复</p>-->
                                <el-input maxlength="200" v-show="chileComment.okComment" rows="3" :placeholder="'回复 @'+chileComment.userName" type="textarea" style="width: 85%; float:left;
                            margin-left: 50px"
                                          v-model="chileComment.write"
                                ></el-input>
                                <el-button v-show="chileComment.okComment" @click="chileComment.okComment = false" style="float:left; margin-left: 62px;margin-top: 10px" size="30px" type="danger">取消</el-button>
                                <el-button v-show="chileComment.okComment" style="float:right; margin-right: 70px;margin-top: 10px" size="30px" type="primary" @click="addReply(chileComment.id,index,i)">发表回复</el-button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div style="float: left; background-color: white; width: 300px;height: auto;margin-top: -250px;margin-left: 25px">
                <div style="height: 75px; width: 100%;float: left">
                    <img class="head_big"
                         @click="this.$router.push('/user_info')"
                         :src="Blog.userIcon ? FileName+Blog.userIcon : CommonName">
                    <div style="height: 75px; text-align: center; float: left;margin-left: 18px; margin-top: 38px;font-family: 楷体; font-size: 23px">
                        {{Blog.userName}}
                    </div>
                </div>

                <div v-show="!(Blog.userId === this.user.id)"  style="height: 55px; width: 90%;margin-left: 25px;float: left;margin-top: 20px">
                    <el-button @click="concern()" v-show="!Blog.isConcern" type="primary" style="float: left;height:35px; width: 100px" plain>关注</el-button>
                    <el-button @click="concern()" v-show="Blog.isConcern" type="info" style="float: left;height:35px; width: 100px" plain>已关注</el-button>
                    <el-button type="primary" style="margin-left: 30px; float:left;height:35px;width: 100px" plain>私信</el-button>
                </div>
                <el-divider style="float:left;margin-top: 0px; margin-bottom: 0px"></el-divider>
                <div style="height: 40px;width: 100%; float:left; font-family: 楷体; font-size: 25px;text-align: center;margin-top: 10px">
                    <img :src=Hot style="width: 25px;height: 25px;">其他热门帖子:
                </div>
                <el-divider style="float:left;margin-top: 0px; margin-bottom: 0px"></el-divider>
                <div v-show="blogs.length === 0" style="width:100%;height: 50px;font-family: 楷体;text-align: center;padding-left: 10px;padding-top: 10px;font-size: 25px;float:left;">
                    No Data......
                </div>


                <div v-for="(blog) in blogs">
                    <div class="Blog" @click="open(blog.id)" style="cursor: pointer;width:100%;height: 50px;font-family: 楷体;padding-left: 10px;padding-top: 10px;font-size: 25px;float:left;">
                        {{blog.title.substring(0,10) + '...'}}</div>
                    <el-divider style="margin: 0px; float:left;"></el-divider>
                </div>
            </div>
        </div>



    </div>

</template>

<script>
    import request from "../../utils/request";
    import {ArrowUpBold, View} from "@element-plus/icons";
    import {f} from "../../utils/FileUtil";
    export default {
        name: "Blog_info",
        components:{
            View,
            ArrowUpBold
        },
        data(){
            return{
                FileName: f(),
                CommonName : this.FileName+'BigProject/People/user_icon.jpg',
                blog_id: 1,
                user:{},
                ok: false,
                reply_content:[],
                comment_content: '',
                star1: require("@/utils/img/ok1.jpg"),
                star2: require("@/utils/img/ok2.jpg"),
                Hot: require("@/utils/img/hot.png"),
                blogs: [
                    {
                        id: 1,
                        userId: 111,
                        title: '测试使用测试使用测试使用 测试使用 测试使用 ',//标题
                    },
                    {
                        id: 1,
                        userId: 111,
                        title: '测试使用测试使用测试使用 测试使用 测试使用 ',//标题
                    },
                    {
                        id: 1,
                        userId: 111,
                        title: '测试使用测试使用测试使用 测试使用 测试使用 ',//标题
                    },
                ],
                Comments: [
                    {
                        id: 1,
                        content : '评论测试。。。。。',
                        userId: '发布评论人的id',
                        userIcon: '',
                        liked: 2,//点赞数量
                        userName: '测试人名',
                        okComment: false,
                        time: '一个月前',
                        isLiked: false,
                        children:[
                            {
                                content : '评论测试。。。。。',
                                userId: 2,//回复的人的id
                                userIcon: '',
                                commentId: 100,
                                userName: '测试回复人名',
                                time: '一天前',
                                bereplyName:'被回复的人',
                                bereplyId:'被回复的人id',
                                okComment: false,
                            },
                            {
                                content: '评论测试。。。。。',
                                userId: 2,//回复的人的id
                                userIcon: '',
                                commentId: 100,
                                userName: '测试回复人名',
                                time: '一分钟前',
                                bereplyName:'被回复的人',
                                bereplyId:'被回复的人id',
                                okComment: false,
                            },
                        ]
                    },
                    {
                        id: 1,
                        content : '评论测试。。。。。',
                        userId: '发布评论人的id',
                        userIcon: '',
                        liked: 2,//点赞数量
                        userName: '测试人名',
                        time: '一个月前',
                        isLiked: true,
                        children:[
                            // {
                            //
                            //     id: 1,
                            //     content : '评论测试。。。。。',
                            //     userId: '发布评论人的id',
                            //     userIcon: '',
                            //     userName: '测试人名',
                            //     Time: '2020-01-12',
                            //     okComment: false,
                            //     to_user_name:'被回复的人'
                            // }
                        ]
                    }
                ],
                Blog: {
                    id: 1,
                    userId: 111,
                    title: '测试使用 测试使用 测试使用 测试使用 测试使用 ',//标题
                    author: '测试使用 ',//作者
                    liked: 12,//点赞数
                    abs: '测试使用 ',//摘要
                    createTime: '2018-04-12 20:46',//时间
                    icon: 'utils/img/niubi.png', //图片
                    userName: '无敌暴龙战士', //用户名字
                    userIcon: '用户头车型啊',
                    view: 1000, //浏览量
                    collection: 12,
                    content: '测试测试',
                    isLiked: false,
                    isCollected: false,
                    isConcern: false,
                    isMyself: false,
                },
            }
        },
        methods:{
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
            addReply(id,index,i){
                let ans = this.Comments[index].children[i];
                console.log(ans);
                let form = {
                    content : ans.write, // 内容
                    CommentId : this.Comments[index].id, //评论的id
                    bereplyName : ans.userName, //被回复的人的名字
                    bereplyId : ans.userId,
                }
                request.post("/reply",form).then(res=>{
                    if(res.code === "0"){
                        this.Comments[index].children.push(res.data);
                        this.$message({
                            type:"success",
                            message: "回复成功"
                        })
                        this.Comments[index].children[i].okComment = false;
                    }
                    else{
                        this.$message({
                            type:"error",
                            message: res.message
                        })
                    }
                });


            },
            load(){
                console.log(this.$route.query);
                this.blog_id = this.$route.query.id;
                let item = localStorage.getItem("king");
                this.user = JSON.parse(item);
                request.get("/blog/"+this.blog_id).then(res=>{
                   this.Blog = res.data;
                    console.log(res);
                });
                request.get("/comment/"+this.blog_id).then(res=>{
                    console.log(res);
                   this.Comments = res.data;
                });
                let len = this.Comments.length;
                for(let i = 0;i<len;i++){
                    this.reply_content[i] = '';
                }
                request.get("/blog/getByUserId/"+this.user.id).then(res=>{
                   this.blogs = res.data;
                });
            },
            concern(){
                //关注也是,业务逻辑全给后端，但是其实后端也是坤坤自己捏/(ㄒoㄒ)/~~
                request.put("/concern",this.Blog).then(res=>{
                    if(!(res.code === "0")){
                        this.$message({
                            type: 'error',
                            message: res.message
                        })
                    }
                    else{//如果关注了，所有的这个人都要被关注，或取关;
                        this.Blog.isConcern = !this.Blog.isConcern;
                    }
                })
            },
            reply(id,index){
                console.log(this.reply_content[index]);//ok极了
                let form = {
                    content : this.reply_content[index], // 内容
                    CommentId : this.Comments[index].id, //被回复的人的id
                    bereplyName :  this.Comments[index].userName, //被回复的人的名字
                    bereplyId : this.Comments[index].userId,
                }
                request.post("/reply",form).then(res=>{
                    if(res.code === "0"){
                        this.Comments[index].children.unshift(res.data);
                      this.reply_content[index] = '';
                        this.$message({
                            type:"success",
                            message: "回复成功"
                        })
                    }
                    else{
                        this.$message({
                            type:"error",
                            message: res.message
                        })
                    }
                });
            },
            toComment(){
                this.$refs["pinglun"].scrollIntoView(true);
            },
            toTop(){
                this.$refs["top"].scrollIntoView(true);
            },
            deleteComment(id,index){
                //删除这个id的评论
                request.delete("/comment/"+id).then(res=>{
                    if(res.code === "0"){
                        this.Comments.splice(index,1);
                        this.$message(
                            {
                                type: "success",
                                message: "删除成功"
                            }
                        )
                    }
                    else{
                        this.$message(
                            {
                                type: "error",
                                message: "删除失败"
                            }
                        )
                    }
                })
            },
            CommentLiked(id,index){
                let data = {
                    idLiked: this.Comments[index].isLiked? 1:0,//用来判断是不是点赞过了
                    commentId: id
                }
                request.post("/comment_like" ,data).then(res=>{
                    if(res.code === "0"){
                        //成功
                        this.Comments[index].isLiked ? this.Comments[index].liked-- : this.Comments[index].liked++;
                        this.Comments[index].isLiked = !this.Comments[index].isLiked;
                    }
                    else{
                        this.$message({
                            type: "error",
                            message: "点赞失败"
                        })
                    }
                });


            },


            Liked(){
                //对于点赞操作，我们直接让后端去解决。
                request.put("/like",this.Blog).then(res=>{
                    if(!(res.code === "0")){
                        this.$message({
                            type: 'error',
                            message: res.message
                        })
                    }
                    else{
                        let isOk = this.Blog.isLiked;
                        this.Blog.liked = isOk ? this.Blog.liked - 1 : this.Blog.liked + 1;
                        this.Blog.isLiked = !isOk;
                    }
                })
            },
            Collected(){
                //收藏同理，让后端干
                request.put("/collect", this.Blog).then(res =>{
                    if(!(res.code === "0")){
                        this.$message({
                            type: 'error',
                            message: res.message
                        })
                    }
                    else{
                        let isOk = this.Blog.isCollected;
                        this.Blog.collection = isOk ? this.Blog.collection - 1 : this.Blog.collection + 1;
                        this.Blog.isCollected = !isOk;
                    }
                })
            },

            PutComment(){ // 发布评论,新增
                let Data = {
                    userId: this.user.id,
                    blogId: this.blog_id,
                    liked: 0,
                    userName: this.user.username,
                    userIcon: this.user.icon,
                    content: this.comment_content
                };//转字符串
                request.post("/comment",JSON.stringify(Data)).then(res=>{
                    if(res.code === "0"){
                        this.Comments.unshift(res.data);
                        this.$message(
                            {
                                type: "success",
                                message: "发布评论成功"
                            }
                        )
                    }
                    else{
                        this.$message(
                            {
                                type: "error",
                                message: res.message
                            }
                        )
                    }
                })
            },

        },
        created() {
            this.load();
        }
    }
</script>

<style scoped>
    @import "../../utils/Css/Index.css";

    .Body{
        width: 820px;
        height: 250px;
        background-color: white;
        float: left;
        margin-left: 230px;
        margin-top: 30px;
    }
    .Comment{
        width: 820px;
        height: auto;
        background-color: white;
        float: left;
        margin-left: 230px;
        margin-top: 20px;
    }
    .head{
        width: 40px;
        height: 40px;
        padding: 1px;
        background-color: #fff;
        border-radius: 50%;
        box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
    }
    .head_big{
        margin-left: 25px;
        margin-top: 25px;
        float: left;
        width: 50px;
        height: 50px;
        padding: 1px;
        background-color: #fff;
        border-radius: 50%;
        box-shadow: 0 0 3px 2px rgba(0, 0, 0, 0.07);
    }
    .REPLY:hover{
        margin-top: 3px;
        background-color: #f8f6f6;
        cursor: pointer;
    }
    .Blog:hover{
        background-color: whitesmoke;
    }
</style>