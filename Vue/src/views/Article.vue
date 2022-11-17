<template>
    <div style="padding: 10px">
        <div style="margin: 10px 0">
            <el-button @click="deleteSome()" style="color: red">批量删除</el-button>
            <el-button style="color: black">导入</el-button>
            <el-button style="color: crimson">导出</el-button>
            <el-input v-model="search" style="width: 20%;margin-left: 100px" placeholder="请输入论文标题" clearable></el-input> <el-button type="primary" @click="load()">搜索</el-button>
        </div>
<!--        ////头 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑-->
<!--        ///表格 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓-->
        <el-table
                :data="tableData"
                border
                stripe
                max-height="550px"
                style="width: 100%;" @selection-change="selection">
<!--            对选框-->
            <el-table-column type="selection" width="55"></el-table-column>
<!--            论文id-->
            <el-table-column prop="pmid" label="ID" sortable>
            </el-table-column>
<!--            论文标题-->
            <el-table-column prop="title" label="Title">
            </el-table-column>
            <!--            论文的类型-->
            <el-table-column prop="type" label="Type">
            </el-table-column>
<!--            论文的作者-->
            <el-table-column prop="authors" label="Author">
            </el-table-column>
<!--            论文的doi-->
            <el-table-column prop="library" label="Library">
            </el-table-column>

<!--            论文的library-->
            <el-table-column prop="doi" label="Doi">
            </el-table-column>

            <!--            论文的发表年限-->
            <el-table-column prop="date" label="Date">
            </el-table-column>

            <el-table-column label="详情">
                <template #default="scope">
                    <el-button @click="enter(scope.row)">进入论文</el-button>
                </template>
            </el-table-column>


        </el-table>

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
        </div>

    </div>
</template>

<script>
    import request from "../utils/request";

    export default {
        name: "Article",
        components:{

        },
        created() {
            this.load();
        },
        data(){
            return {
                search: '',
                tableData: [
                    {
                        "pmid": "15517969",
                        "title": "Profiling microRNA expression using sensitive cDNA probes and filter arrays.",
                        "type": "['Journal Article' \"Research Support Non-U.S. Gov't\"]",
                        "authors": "['Sioud Mouldy' 'Rosok Oystein']",
                        "keywords": "nan",
                        "doi": "['10.2144/04374ST01 [doi]']",
                        "library": "NLM",
                        "abs": "MicroRNAs (miRNAs) are small noncoding RNAs (approximately 22 nucleotides) that have recently emerged as important regulators of gene expression in both plants and animals. With few exceptions however the target genes and the expression levels of most miRNAs are unknown. Here we show that direct random-primed cDNA synthesis on either chemically synthesized small RNAs (21-22 nucleotides) or gel-purified mature miRNAs from human cells can produce specific and sensitive full-length cDNA probes. Using oligonucleotide filter arrays we demonstrate that the internally labeled cDNA probes are sensitive for detecting differential miRNA expression between untreated and O-tetradecanoylphorbol-13-acetate (TPA)-treated HL60 cells. The present study should facilitate a high-throughput analysis of miRNA expression between samples.",
                        "date": "2004 Oct"
                    },
                ],
                ids: [],
                currentPage: 1,
                pageSize: 10,
                dialogVisible: false,
                total: 0,
            }
        },
        methods: {
            deleteSome(){

            },
            load(){
                request.get("/article/All",{params: {pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search}}
                ).then((res) => {
                    console.log(res);
                    this.tableData = res.data.data;
                    this.total = res.data.total;
                });
            },
            selection(val){
                let nums = [];
                for(let i in val){
                    nums.push(val[i].id);
                }
                this.ids = nums;
            },
            handleSizeChange(PageSize){
                this.pageSize = PageSize;
                this.load();
            },
            handleCurrentChange(PageNum){
                this.currentPage = PageNum;
                this.load();
            },
            enter(row){
                this.$router.push(
                    {
                        path: "/articleInformation",
                        query: {
                            articleId: row.pmid,
                        }
                    });
            },
        }
    }
</script>

<style scoped>

</style>