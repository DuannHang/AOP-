package com.duanhang.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duanhang.annotation.Log;
import com.duanhang.entity.User;
import com.duanhang.service.UserService;
@Controller("userController")
//@RequestMapping("/userController")
public class UserController {
	@Resource
	private UserService userService;

//	@RequestMapping("/testAOP")
	@Log(operationType="add操作:",operationName="添加用户")
	public void testAOP(User user) {
		userService.addUser(user);
	}
}
