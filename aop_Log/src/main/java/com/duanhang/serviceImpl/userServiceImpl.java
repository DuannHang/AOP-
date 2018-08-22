package com.duanhang.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duanhang.dao.userMapper;
import com.duanhang.entity.User;
import com.duanhang.service.UserService;
@Service("userService")
public class userServiceImpl implements UserService {
	@Resource
	public userMapper userMapper;

	public void addUser(User user) {
		userMapper.addUser(user);
	}

}
