package mybatis.plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: plus
 * @description
 * @author: xiangyuyi
 * @create: 2021-03-30 15:40
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeleteTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void DeleteById(){
        int rows = userMapper.deleteById(1376508043121930242L);
        System.out.println("删除条数:" + rows);
    }

    @Test
    public void DeleteByMap(){
        Map<String, Object> map = new HashMap<>(4);
        map.put("name","王");
        int rows = userMapper.deleteByMap(map);
        System.out.println("删除条数:" + rows);
    }

    @Test
    public void DeleteByIds(){
        int rows = userMapper.deleteBatchIds(Arrays.asList(1L,2L,1376507246699143170L));
        System.out.println("删除条数:" + rows);
    }

    @Test
    public void DeleteByWrapper(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.eq(User::getAge, 25).or().gt(User::getAge, 88);
        int rows = userMapper.delete(lambdaQueryWrapper);
        System.out.println("删除条数:" + rows);
    }


}
