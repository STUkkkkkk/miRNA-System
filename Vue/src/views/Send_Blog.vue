<template>
  <div style="width: 100%; height: 650px;">

    <el-divider style="font-size: 30px">你的发帖记录</el-divider>

    <el-table :data="blogs">
      <el-table-column fixed prop="id" label="编号" width="100"/>
      <el-table-column prop="title" label="标题"/>
      <el-table-column fixed="right" label="操作" >
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleClick(scope.row)"
          >Detail</el-button
          >
          <el-button link type="danger" size="small" @click="deleteBlog(scope.row)">Edit</el-button>
        </template>
      </el-table-column>
    </el-table>


  </div>


</template>

<script>
    import request from "@/utils/request";

    export default {
        name: "Send_Blog",
      data(){
          return{
            user:{},
            blogs : [
              {
                id: 1,
                title:"测试测试标题",
              }
            ]
        }
      },
      created() {
          this.user = JSON.parse(localStorage.getItem("king"));
        console.log(this.user.id)
          this.load();
      },
      methods: {
        load(){
          request.get("/blog/GetByUserId/"+this.user.id).then(res=>{
            this.blogs = res.data;
          });
        },
        handleClick(row){5

          this.$router.push(
              {
                path: '/blog_info',
                query: {
                  id: row.id,
                }
              }
          )
        },
        deleteBlog(row){
          request.delete("/blog/"+row.id).then(res=>{
            console.log(row);
            if(res.code === "0"){
              this.$message({
                type:'success',
                message: '删除成功'
              })
              this.load();
            }
            else{
              this.$message({
                type:'error',
                message: res.message
              })
            }
          })
        },
      }
    }
</script>

<style scoped>

</style>