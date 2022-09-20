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
    public Result<?> upload(@RequestParam MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();//获取文件名
        String fileLoad = System.getProperty("user.dir")+"/springboot_project/src/main/resources/files/" + fileName;
        FileUtil.writeBytes(file.getBytes(),fileLoad);
        return Result.success(fileLoad);
    }





}
