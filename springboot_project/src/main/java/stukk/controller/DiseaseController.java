package stukk.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stukk.Pojo.Disease;
import stukk.Pojo.User;
import stukk.config.Result;
import stukk.service.DiseaseService;

@RestController()
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    DiseaseService diseaseService;

    @GetMapping
    public Result<?> findPage(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value="pageSize") Integer pageSize, @RequestParam(value = "search") String search){
        IPage<Disease> page = diseaseService.selectPage(new Page<>(pageNum,pageSize),Wrappers.<Disease>lambdaQuery().like(Disease::getDisname,search));
        return Result.success(page);
    }

    @PostMapping
    public Result<?> add(@RequestBody Disease disease){
        if(diseaseService.save(disease)){
            return Result.success();
        }
        else{
            return Result.error("100","新增失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        return diseaseService.deleteById(id) ? Result.success() : Result.error("200","删除失败");
    }

    @PutMapping
    public Result<?> update(@RequestBody Disease disease){
        if(diseaseService.update(disease)){
            return Result.success();
        }
        else{
            return Result.error("300","更新失败");
        }
    }

}
