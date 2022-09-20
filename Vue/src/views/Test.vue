<template>
    <div>
        <el-button type="success"
                   @click="dialogVisible2 = true">点击打开 Dialog</el-button>
        <el-dialog title="上传头像"
                   v-model="dialogVisible2"
                   width="30%">
            <el-form :model="form">
                <el-form-item :label-width="formLabelWidth"
                              ref="uploadElement">
                    <el-upload ref="upload"
                               action="#"
                               accept="image/png,image/gif,image/jpg,image/jpeg"
                               list-type="picture-card"
                               :limit=limitNum
                               :auto-upload="false"
                               :on-exceed="handleExceed"
                               :before-upload="handleBeforeUpload"
                               :on-preview="handlePictureCardPreview"
                               :on-remove="handleRemove"
                               :on-change="imgChange"
                               :class="{hide:hideUpload}">
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%"
                             :src="dialogImageUrl"
                             alt="">
                    </el-dialog>
                </el-form-item>
                <el-form-item>
                    <el-button size="small"
                               type="primary"
                               @click="uploadFile">立即上传</el-button>
                    <el-button size="small"
                               @click="tocancel">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>

</template>

<script>
    export default {
        name: "Test",
        data () {
            return {
                hideUpload: false,
                dialogImageUrl: '',
                dialogVisible: false,//图片预览弹窗
                formLabelWidth: '80px',
                limitNum: 1,
                form: {},
                dialogVisible2: false//弹窗
            }
        },
        methods: {
            // 上传文件之前的钩子
            handleBeforeUpload (file) {
                if (!(file.type === 'image/png' || file.type === 'image/gif' || file.type === 'image/jpg' || file.type === 'image/jpeg')) {
                    this.$notify.warning({
                        title: '警告',
                        message: '请上传格式为image/png, image/gif, image/jpg, image/jpeg的图片'
                    })
                }
                let size = file.size / 1024 / 1024 / 2
                if (size > 2) {
                    this.$notify.warning({
                        title: '警告',
                        message: '图片大小必须小于2M'
                    })
                }
                let fd = new FormData();//通过form数据格式来传
                fd.append("picFile", file); //传文件
                console.log(fd.get('picFile'));
                this.api({
                    url: "/test/up",
                    method: "post",
                    data: fd,
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }).then((data) => {

                })
            },
            // 文件超出个数限制时的钩子
            handleExceed (files, fileList) {

            },
            // 文件列表移除文件时的钩子
            handleRemove (file, fileList) {
                this.hideUpload = fileList.length >= this.limitNum;

            },
            // 点击文件列表中已上传的文件时的钩子
            handlePictureCardPreview (file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            uploadFile () {
                this.$refs.upload.submit()

            },
            imgChange (files, fileList) {
                this.hideUpload = fileList.length >= this.limitNum;
                if (fileList) {
                    this.$refs.uploadElement.clearValidate();
                }
            },
            tocancel () {
                this.dialogVisible2 = false
            }
        }
    }
</script>

<!--<style>-->
<!--    .hide .el-upload&#45;&#45;picture-card {-->
<!--        display: none;-->
<!--    }-->
<!--</style>-->