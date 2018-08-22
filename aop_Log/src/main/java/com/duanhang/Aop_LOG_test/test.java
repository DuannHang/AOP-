package com.duanhang.Aop_LOG_test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.duanhang.controller.UserController;
import com.duanhang.entity.User;

public class test {

	@Test
	public void testAopLog() {
		// ����Spring����
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		// ��ȡservice��controller���
		UserController userController = (UserController) ctx.getBean("userController");
		User user = new User();
		user.setUsername("zhangsan");
		user.setPassword("123456");
		userController.testAOP(user);
	}

}
