package mybatis.plus;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.entity.User;
import com.mp.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @program: plus
 * @description
 * @author: xiangyuyi
 * @create: 2021-03-30 19:50
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getOne(){
        userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge,30),false);
    }

    @Test
    public void Batch(){
        User user = new User();
        user.setAge(25);
        user.setName("老许");
        User user1 = new User();
        user1.setAge(22);
        user1.setName("老胡");
        boolean success = userService.saveBatch(Arrays.asList(user,user1));
        System.out.println(success);
    }

    @Test
    public void chain(){
       userService.lambdaQuery().gt(User::getAge,25).like(User::getName,"雨").list().forEach(x -> System.out.println(x));
    }

    @Test
    public void chain1(){
        userService.lambdaUpdate().eq(User::getAge,25).like(User::getName,"雨").set(User::getAge,28).update();
    }

    @Test
    public void chain2(){
        userService.lambdaUpdate().eq(User::getAge,25).like(User::getName,"雨").remove();
    }
}
