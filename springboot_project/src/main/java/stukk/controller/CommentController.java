package stukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stukk.Pojo.Comment;
import stukk.Pojo.Reply;
import stukk.Utils.DateUtil;
import stukk.config.Result;
import stukk.service.CommentService;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //根据blog的id来查询所有的评论
    @GetMapping("/{blogId}")
    public Result GetByUserId(@PathVariable Long blogId){
        return commentService.GetByUserId(blogId);
    }

    @PostMapping
    public Result add(@RequestBody Comment comment){ // 新增
        boolean save = commentService.save(comment);
        Long id = comment.getId();
        System.out.println(id);
        if(save){
            Comment c = commentService.getById(id);
            c.setTime("1秒前");
            List<Reply> aa = new LinkedList<>();
            c.setChildren(aa);
            return Result.success(c);
        }
        else {
            return Result.error("1001","发布评论失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return commentService.deleteById(id);
    }
}
