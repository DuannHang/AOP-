package com.duanhang.Aop_LOG_test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.duanhang.controller.UserController;
import com.duanhang.entity.User;

public class test {

	@Test
	public void testAopLog() {
		// 启动Spring容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		// 获取service或controller组件
		UserController userController = (UserController) ctx.getBean("userController");
		User user = new User();
		user.setUsername("zhangsan");
		user.setPassword("123456");
		userController.testAOP(user);
	}

}
