package stukk.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import stukk.Pojo.User;
import stukk.config.Result;

import java.util.List;

public interface UserService {
    public boolean save(User user); // 新增

    public Page<User> selectPage(Page page, Wrapper<User> wrapper);//查询

    public boolean update(User user);//更新

    public boolean delete(Long id);//删除

    public User selectOne(Wrapper<User> wrapper);//条件查询

    public List<User> findPage(String username, Integer first, Integer second);

    public boolean deleteSome(List<Long> ids);// 根据id们批量删除

    Result getById(Long id);

    Result<?> login(User user);

    Result reset(User user);

    Result GetByBlogCount(Long id);
}
