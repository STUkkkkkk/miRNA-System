package stukk.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stukk.Pojo.User;
import stukk.mapper.UserMapper;
import stukk.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean save(User user) {
        return userMapper.insert(user) == 1;
    }

    @Override
    public Page<User> selectPage(Page page, Wrapper<User> wrapper) {
        return userMapper.selectPage(page,wrapper);
    }


    @Override
    public boolean update(User user) {
        return userMapper.updateById(user) == 1;
    }

    @Override
    public boolean delete(Long  id) {
        return userMapper.deleteById(id) == 1;
    }

    @Override
    public User selectOne(Wrapper<User> wrapper) {
        return userMapper.selectOne(wrapper);
    }


    @Override
    public List<User> findPage(String username, Integer first, Integer second) {
        Integer num = (first - 1 ) * second;
        return userMapper.findPage(username,num,second);
    }


    @Override
    public boolean deleteSome(List<Long> ids) {
        return userMapper.deleteBatchIds(ids) == ids.size();
    }
}
