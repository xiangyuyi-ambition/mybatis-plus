package mybatis.plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
class PlusApplicationTests {

	@Autowired
	private UserMapper userMapper;


	@Test
	public void select(){
		List<User> userList = userMapper.selectList(null);
		//Assert.assertEquals(5, userList.size());
		userList.forEach(x -> System.out.println(x));
	}

	@Test
	public void insert(){
		User user = new User();
		user.setName("向跟");
		user.setAge(26);
		user.setEmail("xq@aaaa.com");
		user.setManagerId(1088248166370832385L);
		user.setCreateTime(LocalDateTime.now());
		int row = userMapper.insert(user);
		System.out.println("影响记录数：" + row);
	}

	@Test
	public void selectById(){
		User user = userMapper.selectById(1087982257332887553L);
		System.out.println(user);
	}

	@Test
	public void selectByIds(){
		List<User> user = userMapper.selectBatchIds(Arrays.asList(1087982257332887553L, 1088248166370832385L));
		user.forEach(x ->System.out.println(x));
	}


	@Test
	public void selectByMap(){
		Map<String, Object> map = new HashMap<>(8);
		map.put("age", 25);
		List<User> userList = userMapper.selectByMap(map);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapper(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		//wrapper.like("name","女").lt("age","40");
		//wrapper.like("name","女").between("age",20,40).isNotNull("email");
		wrapper.likeRight("name","女").or().ge("age",30).orderByDesc("age").orderByAsc("id");
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperApply(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.apply("date_format(create_time,'%Y-%m-%d')={0} and age = {1}","2019-02-14",25).inSql("manager_id","select id from user where name like '王%'");
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperApply1(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.likeRight("name","王").and(wq -> wq.lt("age",40).or().isNotNull("email"));
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperApply2(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.likeRight("name","王").or(wq -> wq.lt("age",40).gt("age",30).isNotNull("email"));
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperApply3(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.nested(wq -> wq.lt("age",40).or().isNotNull("email")).likeRight("name","王");
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperApply4(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.in("age",Arrays.asList(30,31,32));
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperApply5(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.in("age",Arrays.asList(30,31,32)).last("limit 1");
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperSupper(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.select("id","name").like("name","女").lt("age","40");
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperSupper2(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.like("name","女").lt("age","40").select(User.class, tableFieldInfo ->
				!tableFieldInfo.getColumn().equals("create_time") && !tableFieldInfo.getColumn().equals("email"));
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void testCondition(){
		condition("王","");
	}
	private void condition(String name,String email){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.like(StringUtils.isNotBlank(name),"name",name).like(StringUtils.isNotBlank(email),"email",email);
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperEntity(){
		User user = new User();
		user.setAge(31);
		QueryWrapper<User> wrapper = new QueryWrapper<User>(user);
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperAlleq(){
		Map<String,Object> map = new HashMap<>(8);
		map.put("name","王");
		map.put("age",null);
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		//wrapper.allEq(map,false);
		wrapper.allEq((k,v) -> !k.equals("name"), map);
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(x ->System.out.println(x));
	}
	@Test
	public void selectByWrapperMaps(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.select("id","name").like("name","女").or().lt("age","40");
		List<Map<String, Object>> userList = userMapper.selectMaps(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperMaps2(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.select("avg(age) avg_age","min(age) min_age","max(age) max_age").groupBy("manager_id").having("sum(age)<{0}",500);
		List<Map<String, Object>> userList = userMapper.selectMaps(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperObjs(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.select("id","name").like("name","女").or().lt("age","40");
		List<Object> userList = userMapper.selectObjs(wrapper);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectByWrapperCount(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.like("name","女").or().lt("age","40");
		Integer count = userMapper.selectCount(wrapper);
		System.out.println(count);
	}

	@Test
	public void selectByWrapperOne(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.like("name","刘玉红").or().lt("age","40");
		User user = userMapper.selectOne(wrapper);
		System.out.println(user);
	}

	@Test
	public void selectLamdba(){
		//LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
		//LambdaQueryWrapper<User> lambdaWrapper = new LambdaQueryWrapper<>();
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.like(User::getName, "王").lt(User::getAge, 30);
		List<User> userList = userMapper.selectList(lambdaQuery);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectLamdba2(){
		//LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
		//LambdaQueryWrapper<User> lambdaWrapper = new LambdaQueryWrapper<>();
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.likeRight(User::getName, "王").and(wq -> wq.lt(User::getAge, 30).or().isNotNull(User::getEmail));
		List<User> userList = userMapper.selectList(lambdaQuery);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectLamdbaSql(){
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.likeRight(User::getName, "王").and(wq -> wq.lt(User::getAge, 30).or().isNotNull(User::getEmail));
		List<User> userList = userMapper.selectAll(lambdaQuery);
		userList.forEach(x ->System.out.println(x));
	}

	@Test
	public void selectPage(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.ge("age",20);
		Page<User> page = new Page<User>(1, 2,true);
//		IPage<User> ipage = userMapper.selectPage(page,wrapper);
//		System.out.println("总页数:" + ipage.getPages());
//		System.out.println("总记录数:" + ipage.getPages());
//		ipage.getRecords().forEach(x -> System.out.println(x));
		IPage<Map<String, Object>> ipage = userMapper.selectMapsPage(page, wrapper);
		System.out.println("总页数:" + ipage.getPages());
		System.out.println("总记录数:" + ipage.getTotal());
		List<Map<String, Object>> users = ipage.getRecords();
		users.forEach(x -> System.out.println(x));
	}

	@Test
	public void selectMyPage(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.ge("age",20);
		Page<User> page = new Page<User>(1, 2);
		IPage<User> ipage = userMapper.selectUserPage(page, wrapper);
		System.out.println("总页数:" + ipage.getPages());
		System.out.println("总记录数:" + ipage.getTotal());
		List<User> users = ipage.getRecords();
		users.forEach(x -> System.out.println(x));
	}


}
