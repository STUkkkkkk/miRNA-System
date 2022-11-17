package stukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import stukk.Pojo.Signature;
import stukk.config.Result;
import stukk.mapper.SignatureMapper;

@RestController
@RequestMapping("/signature")
public class SignatureController {

    @Autowired
    SignatureMapper signatureMapper;

    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Integer id){
        Signature signature = signatureMapper.queryById(id);
        if(signature == null){
            return Result.success(new Signature());
        }
        return Result.success(signature);
    }

    @PutMapping
    public Result save(@RequestBody Signature signature){

        int userId = signature.getUser_id();
        Signature signature1 = signatureMapper.queryById(userId);
        if(signature1 == null){//不存在就插入
            int ok = signatureMapper.insertById(signature);
            if(ok == 1){
                return Result.success();
            }
            else{
                return Result.error("155","新增失败");
            }
        }
        else{//存在则更改
            int i = signatureMapper.updateById(signature);
            if(i == 1){
                return Result.success();
            }
            return Result.error("156","更新失败");
        }


    }

}
