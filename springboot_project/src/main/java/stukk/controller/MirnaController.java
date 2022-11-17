package stukk.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stukk.Pojo.Mirna;
import stukk.config.Result;
import stukk.service.MirnaService;

@RestController
@RequestMapping("/mirna")
public class MirnaController {

    @Autowired
    MirnaService mirnaService;

    @GetMapping
    public Result<?> selectPage(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "search") String search){
        Page<Mirna> ans = mirnaService.selectPage(new Page<>(pageNum,pageSize), Wrappers.<Mirna>lambdaQuery().like(Mirna::getMirnaName,search));
        if(ans == null){
            return Result.error("111","查询失败");
        }
        else{
            return Result.success(ans);
        }
    }

    @GetMapping("GetAll")
    public Result GetAll(){
        return mirnaService.getAll();
    }


    @PostMapping
    public Result<?> save(@RequestBody Mirna mirna){
        if(mirnaService.save(mirna)){
            return Result.success();
        }
        else{
            return Result.error("121","新增失败");
        }
    }

    @PutMapping
    public Result<?> update(@RequestBody Mirna mirna){
        if(mirnaService.update(mirna)){
            return Result.success();
        }
        else{
            return Result.error("122","更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        if(mirnaService.delete(id)){
            return Result.success();
        }
        else{
            return Result.error("133","删除失败");
        }
    }

}
