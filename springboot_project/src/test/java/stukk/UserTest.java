package stukk;

import com.baomidou.mybatisplus.extension.p6spy.StdoutLogger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;
import stukk.Pojo.User;
import stukk.service.UserService;

import javax.annotation.Resource;

@SpringBootTest
public class UserTest {

    @Resource
    UserService userService;

    @Test
    public void usertaest(){
        User user = new User();
        user.setUsername("20jkwu1@stu.edu.cn");
        user.setPassword("xp908098");
        System.out.println(userService.login(user));
    }
}
