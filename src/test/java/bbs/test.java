package bbs;

import java.nio.channels.GatheringByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.hibernate.HibernateUtil;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

import edu.zsc.cxl.bbs.action.TopicAction;
import edu.zsc.cxl.bbs.container.GetCategory;
import edu.zsc.cxl.bbs.container.GetForum;
import edu.zsc.cxl.bbs.container.GetForumTopic;
import edu.zsc.cxl.bbs.container.GetPost;
import edu.zsc.cxl.bbs.container.GetReview;
import edu.zsc.cxl.bbs.container.GetTopic;
import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.dao.baseDao;
import edu.zsc.cxl.bbs.entity.Category;
import edu.zsc.cxl.bbs.entity.CategoryAndForum;
import edu.zsc.cxl.bbs.entity.Forum;
import edu.zsc.cxl.bbs.entity.Post;
import edu.zsc.cxl.bbs.entity.PostText;
import edu.zsc.cxl.bbs.entity.Review;
import edu.zsc.cxl.bbs.entity.Topic;
import edu.zsc.cxl.bbs.entity.User;
import edu.zsc.cxl.bbs.service.AdminService;
import edu.zsc.cxl.bbs.service.CAndFService;
import edu.zsc.cxl.bbs.service.CategoryService;
import edu.zsc.cxl.bbs.service.ForumService;
import edu.zsc.cxl.bbs.service.GetCategoryService;
import edu.zsc.cxl.bbs.service.GetForumService;
import edu.zsc.cxl.bbs.service.GetForumTopicService;
import edu.zsc.cxl.bbs.service.GetPostService;
import edu.zsc.cxl.bbs.service.GetReviewService;
import edu.zsc.cxl.bbs.service.GetTopicService;
import edu.zsc.cxl.bbs.service.PostService;
import edu.zsc.cxl.bbs.service.ReviewService;
import edu.zsc.cxl.bbs.service.TopicService;
import edu.zsc.cxl.bbs.service.UserService;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
//		PostService postService = (PostService) ac.getBean("postService");
//		UserService userService = (UserService) ac.getBean("userService");
//		ReviewService reviewService = (ReviewService) ac.getBean("reviewService");
//		AdminService adminService = (AdminService) ac.getBean("adminService");
//		CategoryService categoryService = (CategoryService) ac.getBean("categoryService");
//		ForumService forumService = (ForumService) ac.getBean("forumService");
//		TopicService topicService = (TopicService) ac.getBean("topicService");
//		CAndFService cAndFService = (CAndFService) ac.getBean("cAndFService");
//		GetTopicService getTopicService = (GetTopicService) ac.getBean("getTopicService");
//		GetPostService getPostService = (GetPostService) ac.getBean("getPostService");
//		GetReviewService getReviewService = (GetReviewService) ac.getBean("getReviewService");
//		GetForumTopicService getForumTopicService = (GetForumTopicService) ac.getBean("getForumTopicService");
//		GetCategoryService getCategoryService = (GetCategoryService) ac.getBean("getCategoryService");
//		GetForumService getForumService = (GetForumService) ac.getBean("getForumService");
//		Set<User> userSet = new HashSet<User>();
//		PostText postText = new PostText();
//		
//
//		
//		User user = userService.findUserByName("cxl123156");	
//		User user = new User();
//		user.setUserName("lshlmy");
//		user.setPassword("123456789");
//		user.setEmail("123456@123.com");
//		user.setQQ("499369843");
		
//		Post post = new Post();
//		Topic topic = new Topic();
//		
//		topic.setTittle("heihei");
//		topic.setViewCount(20);
//		topic.setCreaterId(user);
//		
//		post.setCreaterIp("192.168.199.1");
//		
//		postText.setTittle("hahahaha");
//		postText.setText("hehe123456一二三");
//		postText.setPost(post);
//		post.setPostText(postText);
//		post.setTopicId(topic);
//		
//		postService.savePost(post);
		
//		topicService.saveTopic(topic);
//		userService.saveUser(user);

//		categoryService.deleteCategory(categoryService.findCategoryById(13l));
//		forumService.deleteForum(forumService.findForumById(12l));
//		System.out.println(postService.findPostById(16l).getPostId());
//		postService.deletePost(postService.findPostById(16l));
//		Topic topic = new Topic();
//		topic.setTittle("testestest");
//		topic.setForumId(forumService.findForumById(1l));
//		topic.setCreaterId(userService.findUserById(1l));
//		topicService.saveTopic(topic);
//		PageModel<GetTopic> pm = new PageModel<GetTopic>();
//		List<GetTopic> gt=getTopicService.findTopicInPage(pm);
//		pm.setPageNo(1);
//		pm.setPageSize(10);
//		for(Object o : gt){
//			GetTopic g = (GetTopic) o;
//		System.out.println(g.getTittle());
		
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		String date = bartDateFormat.format(new Date());
		System.out.println(date);
		try {
			Date newDate = bartDateFormat.parse(date);
			System.out.println(newDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
