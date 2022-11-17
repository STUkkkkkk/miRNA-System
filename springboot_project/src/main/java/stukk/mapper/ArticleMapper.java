package stukk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import stukk.Pojo.Article;
import stukk.config.Result;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    public List<Article> getAll( Integer num1, Integer num2, String search);

    public Integer getSum(String search);

    public Article getById(Long pmid);
}
