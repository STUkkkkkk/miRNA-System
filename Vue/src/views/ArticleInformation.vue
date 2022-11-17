<template>
    <div style="background-color: #eff1f9; min-height: 720px; min-width: 1535px; ;display: flex">
        <div style="background-color: #f2f9f9; width: 800px; height: 100%;margin-left: 350px; margin-top: 50px">
            <div class="title">{{articleData.title}}</div>
<!--            是标题呀集美们-->
<!--            中间层-->
            <div style="width: 650px; padding-left: 150px; height: 150px;">

                <div style="margin-top: 5px; margin-bottom: 5px; font-family: Comic Sans MS;display: flex">
                    <div style=" font-family: Comic Sans MS; float: left">authors:</div>
                    {{articleData.authors}}
                </div>
                <div style="margin-top: 5px;display: flex">
                    <div style=" font-family: Comic Sans MS; float: left">pmid:</div>
                    {{articleData.pmid}}
                </div>
                <div style="margin-top: 5px;display: flex">
                    <div style=" font-family: Comic Sans MS; float: left">type:</div>
                    {{articleData.type}}
                </div>
                <div style="margin-top: 5px; font-family: Comic Sans MS;display: flex">
                    <div style=" font-family: Comic Sans MS; float: left">keywords:</div>
                    {{articleData.keywords}}
                </div>
            </div>

<!--            摘要-->
            <div style="margin-top: 25px; font-family: Consolas; font-size: 20px; text-indent: 2em">abstract----{{articleData.abs}}</div>

            <div style=" margin-right: 20px;float: right;margin-top: 15px; font-family: 楷体; font-size: 20px">-----{{articleData.date}}</div>
        </div>
    </div>

</template>

<script>
    import request from "../utils/request";

    export default {
        name: "ArticleInformation",
        data(){
            return{
                articleData: [
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
            }
        },
        created() {
            let pmid = JSON.parse(this.$route.query.articleId);
            console.log(pmid);
            request.get("/article",{
                params:{
                    pmid : pmid,
                }
            }).then(res=>{
               this.articleData = res.data;
            });
        },
        methods: {

        }
    }
</script>

<style scoped>
    .title{
        margin-left: 50px;
        margin-top: 50px;
        width: 700px;
        font-family: "Lucida Bright";
        color: #3a3747;
        font-size: 25px;
        text-align: center;
        display: flex;
    }
</style>