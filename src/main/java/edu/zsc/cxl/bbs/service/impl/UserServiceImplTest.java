package edu.zsc.cxl.bbs.service.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.zsc.cxl.bbs.service.PostService;
import edu.zsc.cxl.bbs.service.UserService;

public class UserServiceImplTest {

	private static UserServiceImpl userServic=new UserServiceImpl();
	ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
	UserService userService = (UserService) ac.getBean("userService");
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindUserByName() {
//		fail("Not yet implemented");
		System.out.println(userService.findUserByName("1"));
	}

	@Test
	public void testFinAllUser() {
//		fail("Not yet implemented");
		System.out.println(userService.finAllUser());
	}

}
