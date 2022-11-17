package stukk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stukk.Pojo.Blog;
import stukk.Pojo.Concern;
import stukk.config.Result;
import stukk.config.UserHolder;
import stukk.mapper.BlogMapper;
import stukk.service.BlogService;
import stukk.service.ConcernService;

import javax.management.Query;

@RestController
@RequestMapping("/concern")
public class ConcernController {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    ConcernService concernService;

    //点击关注的操作
    @PutMapping
    public Result GetConcern(@RequestBody Blog blog){
        //先判断blog的
        boolean isConcern = blog.getIsConcern();
        if(isConcern){ // 已经关注，则取消了关注
            blog.setIsConcern(false);
            int i = blogMapper.updateById(blog);
            if(i != 1){
                return Result.error("111","关注失败");
            }
            QueryWrapper<Concern> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", UserHolder.getUser().getId()).eq("concerned_id",blog.getUserId());
            boolean remove = concernService.remove(queryWrapper);
            if(!remove){
                return Result.error("111","关注失败");
            }
        }
        else{ //还没有关注，要设置为关注状态
            blog.setIsConcern(true);
            int i = blogMapper.updateById(blog);
            if(i != 1){
                return Result.error("111","关注失败");
            }
            Concern concern = new Concern();
            concern.setUserId(UserHolder.getUser().getId());
            concern.setConcernedId(blog.getUserId());
            boolean save = concernService.save(concern);
            if(!save){
                return Result.error("111","关注失败");
            }
        }
        return Result.success();
    }


}
