package stukk.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stukk.Pojo.Blog;
import stukk.Pojo.Like;
import stukk.config.Result;
import stukk.config.UserHolder;
import stukk.mapper.BlogMapper;
import stukk.mapper.LikeMapper;
import stukk.service.LikeService;

@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    LikeMapper likeMapper;

    @Override
    public Result Liked(Blog blog) {
        //点赞业务
        boolean isLiked = blog.getIsLiked();
        if(isLiked){        //如果已经点过赞了
            blog.setIsLiked(false);//设置为未点赞
            blog.setLiked(blog.getLiked() - 1);//liked --
            //更新操作
            //对blog进行更新
            int i = blogMapper.updateById(blog);
            if(i != 1){
                return Result.error("111","点赞失败");
            }
            QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("blog_id",blog.getId()).eq("user_id", UserHolder.getUser().getId());
            int delete = likeMapper.delete(queryWrapper);
            if(delete != 1){
                return Result.error("111","点赞失败");
            }
        }
        else{ //还没有点赞
            blog.setIsLiked(true);//设置为未点赞
            blog.setLiked(blog.getLiked() + 1);//liked --
            //更新操作
            //对blog进行更新
            int i = blogMapper.updateById(blog);
            if(i != 1){
                return Result.error("111","点赞失败");
            }
            Like like = new Like();
            like.setBlogId(blog.getId());
            like.setUserId(UserHolder.getUser().getId());
            int add = likeMapper.insert(like);
            if(add != 1){
                return Result.error("111","点赞失败");
            }
        }
        return Result.success();
    }
}
