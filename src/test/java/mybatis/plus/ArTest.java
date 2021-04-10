package mybatis.plus;

import com.mp.dao.UserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @program: plus
 * @description
 * @author: xiangyuyi
 * @create: 2021-03-30 17:49
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setName("刘华");
        user.setAge(29);
        user.setEmail("lh@aaaa.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        boolean ok = user.insert();
        System.out.println(ok);
    }

    @Test
    public void selectById(){
        User user = new User();
        User user1 = user.selectById(1094592041087729666L);
        System.out.println(user1);
    }

    @Test
    public void selectByUserId(){
        User user = new User();
        user.setId(1094592041087729666L);
        User user1 = user.selectById(1094592041087729666L);
        System.out.println(user1);
    }

    @Test
    public void updateByUserId(){
        User user = new User();
        user.setId(1094592041087729666L);
        user.setAge(35);
        boolean ok = user.updateById();
        System.out.println(ok);
    }


    @Test
    public void deleteById(){
        User user = new User();
        user.setId(1094592041087729666L);
        boolean ok = user.deleteById();
        System.out.println(ok);
    }

    @Test
    public void insertOrUpdate(){
        User user = new User();
        user.setId(1376505918140784645L);
        user.setName("张总");
        user.setAge(25);
        boolean ok = user.insertOrUpdate();
        System.out.println(ok);
    }

}
