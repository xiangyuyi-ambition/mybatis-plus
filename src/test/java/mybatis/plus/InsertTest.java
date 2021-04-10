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
public class InsertTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setName("刘de华");
        user.setAge(29);
        user.setEmail("lh@aaaa.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        int row = userMapper.insert(user);
        System.out.println(row);
        System.out.println(user.getId());
    }


}
