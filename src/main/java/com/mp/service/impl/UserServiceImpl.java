package com.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
import com.mp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @program: plus
 * @description
 * @author: xiangyuyi
 * @create: 2021-03-30 19:43
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
