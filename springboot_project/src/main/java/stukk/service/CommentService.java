package stukk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import stukk.Pojo.Comment;
import stukk.config.Result;

public interface CommentService extends IService<Comment> {


    Result GetByUserId(Long blogId);

    Result deleteById(Long id);
}
