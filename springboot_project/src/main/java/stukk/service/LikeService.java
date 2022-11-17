package stukk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import stukk.Pojo.Blog;
import stukk.Pojo.Like;
import stukk.config.Result;

public interface LikeService extends IService<Like> {
    Result Liked(Blog blog);
}
