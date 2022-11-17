package stukk.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stukk.Pojo.User;
import stukk.config.Result;
import stukk.config.UserHolder;
import stukk.service.UserService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Result<?> save(@RequestBody User user){
        User ans = userService.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail,user.getEmail()));
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
            return Result.error("504","注册失败，邮箱已被注册");
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

            return Result.success(user);
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
        return userService.login(user);
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

    //获取用户信息
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Long id){
        return userService.getById(id);
    }


    @PostMapping("/reset")
    public Result ResetPassword(@RequestBody User user){
        return userService.reset(user);
    }

    @GetMapping("/ByBlogCount/{id}")
    public Result GetByBlogCount(@PathVariable Long id){
        return userService.GetByBlogCount(id);
    }

}
