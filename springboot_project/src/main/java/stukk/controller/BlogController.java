package stukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stukk.Pojo.Blog;
import stukk.Pojo.Collect;
import stukk.Pojo.Concern;
import stukk.Pojo.Like;
import stukk.config.Result;
import stukk.config.UserHolder;
import stukk.service.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    LikeService likeService;

    @Autowired
    CommentService commentService;

    @Autowired
    CollectService collectService;

    @Autowired
    ConcernService concernService;

    //1、获取所有的blog
    @GetMapping
    public Result GetAll(@RequestParam String Search){
        return blogService.getAll(Search);
    }
    //2、新增
    @PostMapping
    public Result add(@RequestBody Blog blog){
        return blogService.add(blog);
    }

    @GetMapping("/GetByUserId/{userId}")
    public Result GetByUserId(@PathVariable Long userId){
        return blogService.GetByUserId(userId);
    }

    @GetMapping("/GetCount/{id}")
    public Result GetCount(@PathVariable Long id){
        return blogService.GetCount(id);
    }

    @GetMapping("/{id}")
    public Result GetOne(@PathVariable Long id){
        Blog blog = blogService.getById(id);
        Like one = likeService.query().eq("blog_id", id).eq("user_id", UserHolder.getUser().getId()).one();
        Collect one1 = collectService.query().eq("blog_id", id).eq("user_id", UserHolder.getUser().getId()).one();
        blog.setIsCollected(one1 == null ? false : true);
        Integer cnt = commentService.query().eq("blog_id", id).count();
        blog.setComments(Long.parseLong(cnt.toString()));
        blog.setIsLiked(one == null ? false : true);

        Concern one12 = concernService.query().eq("user_id", UserHolder.getUser().getId()).eq("concerned_id", blog.getUserId()).one();
        if(one12 == null){//没有关注
            blog.setIsConcern(false);
        }
        else{
            blog.setIsConcern(true);
        }
        if(blog == null){
            return Result.error("111","查无此帖子");
        }
        blog.setView(blog.getView() + 1);
        blogService.updateById(blog);
        return Result.success(blog);
    }


    @GetMapping("/getByUserId/{id}")
    public Result getByUserId(@PathVariable("id") Long id){
        List<Blog> user_id = blogService.query().eq("user_id", id).list();
        List<Blog> blogs = user_id.size() < 5 ? user_id:user_id.subList(0,5);
        return Result.success(blogs);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        return blogService.DeleteById(id);
    }


}
