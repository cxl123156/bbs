package edu.zsc.cxl.bbs.service.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.zsc.cxl.bbs.service.TopicService;

public class TopicServiceImplTest {

	ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
	TopicService topicService = (TopicService) ac.getBean("topicService");
	

	@Test
	public void testFindTopicByTittle() {
		topicService.findTopicById(1L);
		System.out.println(topicService.findTopicById(1L).getTittle());
	}


}
