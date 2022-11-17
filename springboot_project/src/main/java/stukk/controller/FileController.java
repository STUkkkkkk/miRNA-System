package stukk.controller;

import cn.hutool.core.io.FileUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import stukk.config.Result;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping
    public Result<?> upload(@RequestParam MultipartFile file) throws IOException { //将文件保存并返回路径
        String fileType = file.getOriginalFilename();//获取文件后缀👇👇👇👇👇👇👇
        long l = System.currentTimeMillis();
        System.out.println(fileType);
        String a[] = fileType.split("\\.");
        String fileName = l+"."+a[1];
        String fileLoad = System.getProperty("user.dir")+"/Vue/src/utils/img/" + fileName;
        FileUtil.writeBytes(file.getBytes(),fileLoad);
        return Result.success("utils/img/"+fileName);
    }





}
