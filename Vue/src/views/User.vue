<template >
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-button @click="add()" style="color: aqua">新增</el-button>
      <el-button @click="deleteSome()" style="color: red">批量删除</el-button>
      <el-button style="color: black">导入</el-button>
      <el-button style="color: crimson">导出</el-button>
      <el-input v-model="search" style="width: 20%;margin-left: 100px" placeholder="请输入内容" clearable></el-input> <el-button type="primary" @click="load()">查询</el-button>
    </div>
<!--搜索区域-->
    <el-table
            :data="tableData"
            border
            stripe
            style="width: 100%;" @selection-change="selection">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column
              prop="id"
              label="ID"
              sortable
      >
      </el-table-column>
      <el-table-column
              prop="username"
              label="用户名">
      </el-table-column>
      <el-table-column
              prop="nickname"
              label="昵称">
      </el-table-column>

      <el-table-column
              prop="age"
              label="年龄">
      </el-table-column>

      <el-table-column
              prop="sex"
              label="性别">
      </el-table-column>

      <el-table-column
              prop="address"
              label="地址">
      </el-table-column>

      <el-table-column
              fixed="right"
              label="操作"
              width="200px">
        <template #default="scope">
          <el-button @click="handleEdit( scope.row)" style="background-color: cornflowerblue;color: white">编辑</el-button>
          <el-button @click="handleDelete(scope.row)" style="background-color: crimson; color: white">删除</el-button>
        </template>
      </el-table-column>

    </el-table>
<!--    分页-->
    <div style="margin: 10px 0;">
      <el-pagination
              v-model:currentPage="currentPage"
              v-model:page-size="pageSize"
              :page-sizes="[5,10,20]"
              :small="small"
              :disabled="disabled"
              :background="background"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
      />

      <el-dialog
              v-model="dialogVisible"
              title="填写新增信息"
              width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item label="昵称">
            <el-input v-model="form.nickname" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男" style="margin-left: 10px">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
            <el-radio v-model="form.sex" label="未知">未知</el-radio>
          </el-form-item>

          <el-form-item label="地址">
            <el-input v-model="form.address" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save()">提交</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>


import request from "../utils/request";

export default {
  name: 'HomeView',
  components: {

  },
  data(){
    return {
      form:{},
      search: '',
      currentPage: 1,
      pageSize: 10,
      ids: [],
      dialogVisible: false,
      total: 0,
      tableData :[]
    }
  },
  created() {
    this.load();
  },
  methods:{
    load(){
      request.get("/user",{params: {pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search},
      }).then((res) => {
        console.log(res);
        this.tableData = res.data.records;
        this.total = res.data.total;
      });
    },
    add(){
      this.dialogVisible = true;
      this.form = {};
    },
    handleDelete(row){
      request.delete("/user/"+row.id).then(res=>{
        console.log(res);
        if(res.code === "0"){
          this.$message({
            type: "success",
            message: "删除成功"
          })
        }
        else{
          this.$message({
            type: "error",
            message: "删除失败"
          })
        }
      });
      this.load();
    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },
    save(){
      // axios前后端数据交互
      if(this.form.id){ //更新
        request.put("/user",this.form).then(res =>{
          console.log(res);
          if(res.code === "0"){
            this.$message({
              type: "success",
              message: "更新成功"
            })
          }
          else{
            this.$message({
              type: "error",
              message: "更新失败"
            })
          }
        });
      }
      else{//新增
        request.post("/user",this.form).then(res =>{
          console.log(res);
          if(res.code === "0"){
            this.$message({
              type: "success",
              message: "新增成功"
            })
          }
          else{
            this.$message({
              type: "error",
              message: "新增失败"
            })
          }
          this.load();
        });
      }
      this.dialogVisible = false;
      this.load();
    },
    handleSizeChange(PageSize){
      this.pageSize = PageSize;
      this.load();
    },
    selection(val){
      let nums = [];
      for(let i in val){
        nums.push(val[i].id);
      }
      this.ids = nums;
    },
    deleteSome(){
      request.post("/user/batch",this.ids).then(res=>{
        if(res.code === "0"){
          this.$message({
            type: "success",
            message: "批量删除成功"
          })
        }
        else{
          this.$message({
            type: "error",
            message: "删除失败"
          })
        }
        this.load();
      });
    },
    handleCurrentChange(PageNum){
      this.currentPage = PageNum;
      this.load();
    },
  }
}
</script>
