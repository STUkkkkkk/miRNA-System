package stukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import stukk.config.Result;
import stukk.service.Impl.QiNiuServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/qiNiu")
public class QiNiuController {

    @Autowired
    QiNiuServiceImpl qiNiuService;

    @PostMapping("/Peopleupload")
    public Result upload(@RequestParam("file") MultipartFile file){
        if(file == null){
            return Result.error("1111","上传文件为空");
        }
        String s = "";
        try {
            s = qiNiuService.saveImage(file,"People/");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("1111",e.getMessage());
        }
        if(s == null){
            return Result.error("1111","文件出大问题了");
        }
        return Result.success(s);
    }

    @PostMapping("/Blogupload")
    public Result Blogupload(@RequestParam("file") MultipartFile file){
        if(file == null){
            return Result.error("1111","上传文件为空");
        }
        String s = "";
        try {
            s = qiNiuService.saveImage(file,"Blog/");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("1111",e.getMessage());
        }
        if(s == null){
            return Result.error("1111","文件出大问题了");
        }
        return Result.success(s);
    }

}
