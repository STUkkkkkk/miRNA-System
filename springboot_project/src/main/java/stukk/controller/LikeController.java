package stukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stukk.Pojo.Blog;
import stukk.config.Result;
import stukk.service.LikeService;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    @PutMapping
    public Result Liked(@RequestBody Blog blog){ // 点赞操作，传递blog判断 是不是已经点赞，如果已经点赞没那么直接取消点赞--   如果还没有点赞，则++
        return likeService.Liked(blog);
    }
}
