package stukk.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stukk.Pojo.Blog;
import stukk.Pojo.Collect;
import stukk.config.Result;
import stukk.config.UserHolder;
import stukk.mapper.BlogMapper;
import stukk.mapper.CollectMapper;
import stukk.service.BlogService;
import stukk.service.CollectService;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    CollectMapper collectMapper;

    @Override
    public Result collected(Blog blog) {
        //收藏业务
        //先判断blog是不是已经点过收藏了
        long id = UserHolder.getUser().getId();//获取用户id
        boolean isCollected = blog.getIsCollected();
        if(query().eq("user_id",id).eq("blog_id",blog.getId()).one() != null && !isCollected){            //如果已经收藏
            return Result.error("111","收藏失败,联系管理员");
        }
        if(query().eq("user_id",id).eq("blog_id",blog.getId()).one() == null && isCollected){            //如果没有收藏
            return Result.error("111","收藏失败,联系管理员");
        }
        if(isCollected){ //已经收藏
            //取消收藏
            blog.setIsCollected(false);
            blog.setCollection(blog.getCollection() - 1); //收藏数--
            int i = blogMapper.updateById(blog);
            if(i != 1){
                return Result.error("111","收藏失败,联系管理员");
            }
            QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", UserHolder.getUser().getId()).eq("blog_id", blog.getId());
            int delete = collectMapper.delete(queryWrapper);// 删除收藏关系
            if(delete != 1){
                return Result.error("111","收藏失败,联系管理员");
            }
        }
        else{
            //收藏
            blog.setIsCollected(true);
            blog.setCollection(blog.getCollection() + 1); //收藏数++
            int i = blogMapper.updateById(blog);
            if(i != 1){
                return Result.error("111","收藏失败,联系管理员");
            }
            Collect collect = new Collect();
            collect.setBlogId(blog.getId());
            collect.setUserId(UserHolder.getUser().getId());
            int insert = collectMapper.insert(collect);
            if(insert != 1){
                return Result.error("111","收藏失败,联系管理员");
            }
        }
        return Result.success();
    }
}
