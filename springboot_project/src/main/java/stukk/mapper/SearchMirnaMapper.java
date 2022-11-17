package stukk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScans;
import stukk.Pojo.Article;

import java.util.List;

@Mapper
public interface SearchMirnaMapper {
    List<Object> GetByMirna(String mirnaName,Integer num1,Integer num2);
}
