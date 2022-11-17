package stukk.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stukk.Pojo.Article;
import stukk.config.ArticleDTO;
import stukk.config.Result;
import stukk.mapper.ArticleMapper;
import stukk.service.ArticleService;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.ArrayList;
import java.util.List;
import static stukk.config.ReidsDTO.*;
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public Result getAll(Integer pageNum,Integer pageSize,String search) {//缓存总数应该会快一点
        String key = searchKey+search;
        Integer num1 = pageNum * 10 - 9;
        search = "%"+search+"%";
        List<Article> objects = articleMapper.getAll(num1, pageSize, search);
        if(objects == null){
            return Result.success(new ArrayList<>());
        }
        int sum = 0;
        String s = stringRedisTemplate.opsForValue().get(key);
        if(s == null){
            sum = articleMapper.getSum(search);
            stringRedisTemplate.opsForValue().set(key,String.valueOf(sum));
        }
        else{
            sum = Integer.parseInt(s);
        }
        return Result.success(ArticleDTO.Build(objects,sum));
    }


    @Override
    public Result getById(Long pmid) {
        Article article = articleMapper.getById(pmid);//////文章详细
        if(article == null){
            return Result.error("155","数据库并无此文章");
        }
        return Result.success(article);
    }
}
