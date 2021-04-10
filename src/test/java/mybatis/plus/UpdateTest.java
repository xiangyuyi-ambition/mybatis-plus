package mybatis.plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: plus
 * @description
 * @author: xiangyuyi
 * @create: 2021-03-30 15:40
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class UpdateTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void updateById(){
        User user = new User();
        user.setId(1088248166370832385L);
        user.setAge(44);
        int rows = userMapper.updateById(user);
        System.out.println(rows);
    }

    @Test
    public void updateByWrapper(){
        User whereUser = new User();
        whereUser.setName("是的");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>(whereUser);
        updateWrapper.eq("name","王天风").eq("age",44);
        User user = new User();
        user.setAge(43);
        int rows = userMapper.update(user, updateWrapper);
        System.out.println(rows);
    }

    @Test
    public void updateByWrapperSet(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","王天风").eq("age",44).set("age",29);
        int rows = userMapper.update(null, updateWrapper);
        System.out.println(rows);
    }

    @Test
    public void updateByWrapperByLambda(){
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getName,"王天风").eq(User::getAge,44).set(User::getAge,29);
        int rows = userMapper.update(null, updateWrapper);
        System.out.println(rows);
    }
}
