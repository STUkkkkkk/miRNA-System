<template>
    <div style="padding: 10px">
        <div style="margin: 10px 0">
            <el-button @click="add()" style="color: aqua">新增</el-button>
            <el-button style="color: black">导入</el-button>
            <el-button style="color: crimson">导出</el-button>
            <el-input v-model="search" style="width: 20%;margin-left: 100px" placeholder="请输入内容" clearable></el-input> <el-button type="primary" @click="load()">查询</el-button>
        </div>
        <!--搜索区域-->
        <el-table
                :data="tableData"
                border
                stripe
                style="width: 100%;">
            <el-table-column
                    prop="id"
                    label="ID"
                    sortable>
            </el-table-column>
            <el-table-column
                    prop="disname"
                    label="疾病名称">
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="疾病描述">
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
                    <el-form-item label="疾病名称">
                        <el-input v-model="form.disname" style="width: 80%"></el-input>
                    </el-form-item>

                    <el-form-item label="疾病描述">
                        <el-input v-model="form.description" style="width: 80%;" ></el-input>
                    </el-form-item>
                </el-form>
                <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save()">提交</el-button>
      </span>
                </template>
            </el-dialog>

            <el-dialog v-model="isdelete" title="确定删除?" width="30%">
                <span class="dialog-footer">
               <el-button @click="dialogVisible = false" style="background-color: greenyellow">取消</el-button>
               <el-button style="background-color: red" @click="shanchu()">确定</el-button>
      </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import request from "../utils/request";

    let del;
    export default {
        name: "Disease",
        data(){
            return {
                form:{},
                search: '',
                currentPage: 1,
                isdelete: false,
                pageSize: 10,
                dialogVisible: false,
                total: 0,
                tableData :[
                    {
                        id: 1, disname: '新冠', description: '是个勾巴'
                    }
                ]
            }
        },
        created() {
            this.load();
        },
        methods:{
            add(){
                this.dialogVisible = true;
            },
            load(){
                request.get("/disease",{params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search}
                }).then(res=>{
                    this.tableData = res.data.records;
                    this.total = res.data.total;
                })
            },
            save(){
                if(this.form.id){
                    request.put("/disease",this.form).then(res=>{
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
                else{
                    request.post("/disease",this.form).then(res=>{
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
                    });
                }
                this.dialogVisible = false;
                this.load();
            },
            handleEdit(row){
                this.form = JSON.parse(JSON.stringify(row));
                this.dialogVisible = true;
                this.load();
            },
            shanchu(){
                if(del === null){
                    this.$message({
                        type: "error",
                        message: "删除失败"
                    })
                }
                else{
                    request.delete("/disease/"+del.id).then(res=>{
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
                    this.isdelete = false;
                    this.del = null;
                    this.load();
                }
            },
            handleDelete(row) {
                this.isdelete = true;
                del = row;
            },
            handleSizeChange(pageSize){
                this.pageSize = pageSize;
                this.load();
            },
            handleCurrentChange(pageNum){
                this.currentPage = pageNum;
                this.load();
            }
        }
    }
</script>

<style scoped>

</style>