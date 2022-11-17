package stukk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import stukk.Pojo.Blog;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> getAll(String Search);

    List<Blog> GetCount(Long id);

}
