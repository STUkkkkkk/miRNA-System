package stukk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stukk.Pojo.Comment;
import stukk.Pojo.CommentLike;
import stukk.config.Result;
import stukk.config.UserHolder;
import stukk.mapper.CommentLikeMapper;
import stukk.service.CommentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment_like")
public class CommetLikeController {

    @Autowired
    private CommentLikeMapper commentLikeMapper;

    @Autowired
    CommentService commentService;


    @PostMapping
    public Result Like(@RequestBody Map<String,Object> map){
        Integer idLiked = Integer.parseInt( map.get("idLiked").toString()); //判断当前是不是已经点赞了
        Long commentId = Long.parseLong(map.get("commentId").toString()); //评论的id
        Long userId = UserHolder.getUser().getId();
        QueryWrapper<CommentLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id",commentId).eq("user_id",userId);
        CommentLike commentLike = commentLikeMapper.selectOne(queryWrapper);
        Comment comment = commentService.getById(commentId);
        if(idLiked == 1){ // 已经点赞的....先判断是不是存在
            if(commentLike == null){
                return Result.error("101","勿要重复点赞点赞了");
            }
            comment.setIsLiked(false);
            comment.setLiked(comment.getLiked()-1);
            commentService.updateById(comment);
            int i = commentLikeMapper.deleteById(commentLike.getId());
            if(i != 1){
                return Result.error("101","取消点赞失败");
            }
        }
        else{
            if(commentLike != null){
                return Result.error("101","还没点赞捏");
            }
            comment.setIsLiked(true);
            comment.setLiked(comment.getLiked()+1);
            commentService.updateById(comment);
            commentLike = new CommentLike();
            commentLike.setCommentId(commentId);
            commentLike.setUserId(userId);
            int insert = commentLikeMapper.insert(commentLike);
            if(insert != 1){
                return Result.error("101","点赞失败");
            }
        }
        return Result.success();
    }

}
