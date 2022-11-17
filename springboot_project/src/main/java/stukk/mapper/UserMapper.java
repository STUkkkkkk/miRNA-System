package stukk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import stukk.Pojo.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {


    List<User> GetByBlogCount(Long id);

    List<User> findPage(String username, Integer first,Integer second);

    User getOne(String username, String password);

    boolean updateByEmail(String email, String password);
}
