package stukk.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stukk.Pojo.User;
import stukk.config.Result;
import stukk.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Result<?> save(@RequestBody User user){
        User ans = userService.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if(ans == null){
            boolean isOk = userService.save(user);
            if(isOk){
                return Result.success();
            }
            else{
                return Result.error("505","新增失败");
            }
        }
        else{
            return Result.error("504","注册失败，因为你他妈用户名重复了");
        }

    }

    @GetMapping
    public Result<?> findPage(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value="pageSize") Integer pageSize, @RequestParam(value = "search") String search){
        IPage<User> userPage = userService.selectPage(new Page<>(pageNum,pageSize), Wrappers.<User>lambdaQuery().like(User::getNickname,search));
        return Result.success(userPage);
    }

    @PutMapping
    public Result<?> update(@RequestBody User user){
        if(userService.update(user)){
            return Result.success();
        }
        else{
            return Result.error("500","更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        if(userService.delete(id)){
            return Result.success();
        }
        else{
            return Result.error("405","删除失败");
        }
    }

    @PostMapping("/login")
    public Result<?> Login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        User ans = userService.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,username).eq(User::getPassword,password));
        if(ans == null){
            return Result.error("101","登陆失败，请进行注册");
        }
        else{
            return Result.success(ans);
        }
    }

    @PostMapping("/batch")
    public Result<?> DeleteByIds(@RequestBody List<Long> ids){
        if(userService.deleteSome(ids)){
            return Result.success();
        }
        else{
            return Result.error("555","批量删除失败");
        }
    }


}
