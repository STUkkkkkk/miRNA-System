package stukk.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stukk.Pojo.*;
import stukk.config.Result;
import stukk.config.UserHolder;
import stukk.mapper.BlogMapper;
import stukk.service.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper,Blog> implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Autowired
    CollectService collectService;

    @Autowired
    CommentService commentService;

    @Autowired
    ConcernService concernService;

    @Override
    public Result getAll(String Search) {
        List<Blog> blogs = blogMapper.getAll("%"+Search+"%");
//1、先获取所有的blogs，然后对blogs判断内部所有的 isLiked
        //低效的办法,查了n次数据库！！？！？！！
        //点赞表id  user_id  blog_id  time
        long id = UserHolder.getUser().getId();//获取用户id
        for(Blog blog : blogs){
            if(likeService.query().eq("user_id",id).eq("blog_id",blog.getId()).one() != null){            //如果已经点赞
                blog.setIsLiked(true);
            }
            else{
                blog.setIsLiked(false);
            }
            if(collectService.query().eq("user_id",id).eq("blog_id",blog.getId()).one() != null){            //如果已经收藏
                blog.setIsCollected(true);
            }
            else{
                blog.setIsCollected(false);
            }

            //判断这个帖子的主人，我们是不是关注
            Long user_id = UserHolder.getUser().getId();
            Long blog_user_id = blog.getUserId();
            if(user_id == blog_user_id){
                blog.setIsMyself(true); // 设置为咱自己写的论文
            }
            else{
                blog.setIsMyself(false); // 设置为不是咱自己写的论文
            }

            //判断是不是关注了这个人
            Concern one = concernService.query().eq("user_id", user_id).eq("concerned_id", blog_user_id).one();
            if(one == null){//没有关注
                blog.setIsConcern(false);
            }
            else{
                blog.setIsConcern(true);
            }

        }
        return Result.success(blogs);
    }

    @Override
    public Result add(Blog blog) {
        User user = UserHolder.getUser();
        blog.setUserName(user.getUsername());
        blog.setUserIcon(user.getIcon());
        blog.setUserId(user.getId());
        blog.setView(0L);
        blog.setLiked(0L);
        blog.setCollection(0L);
        System.out.println(blog);
        user.setBlogCount(user.getBlogCount() + 1);
        boolean update = userService.update(user);
        if(!update){
            return Result.error("111","新增失败");
        }
        int insert = blogMapper.insert(blog);
        if(insert == 1){
            return Result.success(blog);
        }
        else{
            return Result.error("111","新增失败");
        }
    }

    @Override
    public Result GetCount(Long id) {
        List<Blog> blogs = blogMapper.GetCount(id);
        return Result.success(blogs);
    }

    @Override
    public Result GetByUserId(Long userId) {
        List<Blog> blogs = query().eq("user_id", userId).list();
        return Result.success(blogs);
    }

    @Override
    public Result DeleteById(Long id) {
        //用 id删除
        //删除对应id的评论啊
        //删除对应id的点赞、收藏
        QueryWrapper<Comment> queryWrapper1 = Wrappers.<Comment>query().eq("blog_id", id);
        commentService.remove(queryWrapper1);

        QueryWrapper<Like> queryWrapper2 = Wrappers.<Like>query().eq("blog_id", id);
        likeService.remove(queryWrapper2);

        QueryWrapper<Collect> queryWrapper3 = Wrappers.<Collect>query().eq("blog_id", id);
        collectService.remove(queryWrapper3);

        User user = UserHolder.getUser();
        user.setBlogCount(user.getBlogCount() - 1);
        boolean update = userService.update(user);
        if(!update){
            return Result.error("111","删除失败");
        }
        boolean b = removeById(id);
        if(b){
            return Result.success();
        }
        else{
            return Result.error("123","删除失败啊");
        }
    }
}
