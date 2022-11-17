package stukk.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import stukk.Pojo.User;
import stukk.Utils.TokenUtils;
import stukk.config.Result;
import stukk.config.UserHolder;
import stukk.mapper.UserMapper;
import stukk.service.UserService;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean save(User user) {
        //判断是不是正确的code
        if(user.getPassword().length() < 6){
            return false;
        }
        String s = stringRedisTemplate.opsForValue().get("Email:" + user.getEmail());
        if(s.equals(user.getCode())){
            Random random = new Random();
            user.setAge(0);
            user.setAddress("天涯海角");
            user.setNickname("user_"+random.nextInt(100000));
            user.setSex("未知");
            return userMapper.insert(user) == 1;
        }
        else{
            return false;
        }
    }

    @Override
    public Result GetByBlogCount(Long id) {
        //获取前id个就好了
        List<User> users = userMapper.GetByBlogCount(id);
        users.get(0).setSort("utils/img/first.png");
        users.get(1).setSort("utils/img/second.png");
        users.get(2).setSort("utils/img/third.png");

        return Result.success(users);
    }

    @Override
    public Page<User> selectPage(Page page, Wrapper<User> wrapper) {
        return userMapper.selectPage(page,wrapper);
    }


    @Override
    public boolean update(User user) {
        if(user.getPassword().length() < 6){
            return false;
        }
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

    @Override
    public Result getById(Long id) {
        User user = userMapper.selectById(id);
        if(user == null){
            return Result.error("155","用户信息查询出错");
        }
        return Result.success(user);
    }


    @Override
    public Result<?> login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        User ans = userMapper.getOne(username,password);
        if(ans == null){
            return Result.error("101","登陆失败，请进行注册");
        }
        else{
            if(!ans.getPassword().equals(password) || (!ans.getUsername().equals(username) && !ans.getEmail().equals(username))){
                return Result.error("101","登陆失败，请进行注册");
            }
            //在return 之前设置token
            String token = TokenUtils.getToken(ans.getId().toString(), ans.getPassword());
            ans.setToken(token);
            UserHolder.saveUser(user);
            System.out.println(Thread.currentThread());
            return Result.success(ans);
        }
    }


    @Override
    public Result reset(User user) {
        //1.对比Code
        String code1 = user.getCode();
        String code2 = stringRedisTemplate.opsForValue().get("Email:" + user.getEmail());
        if(!code1.equals(code2)){// 如果不相等的话
            return Result.error("155","验证码出错");
        }
        //相等的话，可以继续进行修改密码操作
        String password = user.getPassword();
        String email = user.getEmail();
        if(!userMapper.updateByEmail(email,password)){
            return Result.error("101","邮箱不存在，密码修改失败");
        }
        return Result.success();
    }
}
