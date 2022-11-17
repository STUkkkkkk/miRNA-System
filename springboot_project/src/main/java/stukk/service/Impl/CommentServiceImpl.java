package stukk.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stukk.Pojo.Comment;
import stukk.Pojo.CommentLike;
import stukk.Pojo.Reply;
import stukk.Utils.DateUtil;
import stukk.config.Result;
import stukk.config.UserHolder;
import stukk.mapper.CommentLikeMapper;
import stukk.mapper.CommentMapper;
import stukk.service.CommentService;
import stukk.service.ReplyService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private CommentLikeMapper commentLikeMapper;

    @Override
    public Result GetByUserId(Long blogId) {
        List<Comment> comments = commentMapper.GetByUserId(blogId);

        //根据每个comments的id查询一下对应的回复。
        for(Comment comment : comments){
            //判断一下是不是被点赞过
            Long commentId = comment.getId();
            Long userId = UserHolder.getUser().getId();
            QueryWrapper<CommentLike> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("comment_id",commentId).eq("user_id",userId);
            CommentLike commentLike = commentLikeMapper.selectOne(queryWrapper);
            comment.setTime(DateUtil.format(comment.getCreateTime()));
            if(commentLike != null){
                comment.setIsLiked(true);
            }
            //根据commentId查询对应的回复
            List<Reply> replies = replyService.query().eq("comment_id", commentId).orderByAsc("create_time").list();
            comment.setChildren(replies);//
        }

        return Result.success(comments);//没这么简单啊
    }

    @Override
    public Result deleteById(Long id) {
        //根据id删除
        if(removeById(id)){
            return Result.success();
        }
        else{
            return Result.error("111","删除失败");
        }
    }
}
