package edu.zsc.cxl.bbs.action;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import edu.zsc.cxl.bbs.container.GetTopic;
import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.entity.Admin;
import edu.zsc.cxl.bbs.entity.Category;
import edu.zsc.cxl.bbs.entity.Forum;
import edu.zsc.cxl.bbs.entity.HighLight;
import edu.zsc.cxl.bbs.entity.Post;
import edu.zsc.cxl.bbs.entity.PostText;
import edu.zsc.cxl.bbs.entity.Topic;
import edu.zsc.cxl.bbs.entity.User;
import edu.zsc.cxl.bbs.service.CategoryService;
import edu.zsc.cxl.bbs.service.ForumService;
import edu.zsc.cxl.bbs.service.GetTopicService;
import edu.zsc.cxl.bbs.service.PostService;
import edu.zsc.cxl.bbs.service.PostTextService;
import edu.zsc.cxl.bbs.service.TopicService;
import edu.zsc.cxl.bbs.service.UserService;

public class TopicAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private Topic topic;
	private PostText postText;
	private TopicService topicService;
	private PostService postService;
	private PostTextService postTextService;
	private Forum forum;
	private Long topicId;
	private GetTopicService getTopicService;
	private int page =1;
	private CategoryService categoryService;
	private ForumService forumService;
	private UserService userService;
	private String admin;
	private User user;
	private HighLight highLight;
	private String imageFileName;
	private File image;
	private String imageContentType;

	

	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public HighLight getHighLight() {
		return highLight;
	}
	public void setHighLight(HighLight highLight) {
		this.highLight = highLight;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Autowired
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	@Autowired
	public void setGetTopicService(GetTopicService getTopicService) {
		this.getTopicService = getTopicService;
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public PostText getPostText() {
		return postText;
	}
	public void setPostText(PostText postText) {
		this.postText = postText;
	}

	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	public PostService getPostService() {
		return postService;
	}
	
	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	@Autowired
	public void setPostTextService(PostTextService postTextService) {
		this.postTextService = postTextService;
	}
	
	public String createTopic(){
		User user = (User) getSession().getAttribute("currentUser");
		Map forumMap=  (Map) getSession().getAttribute("currentForum");
		for(Object o: forumMap.values()){
			forum = (Forum) o;
		}
		user.setTotalPost(user.getTotalPost()+1);
		Post post = new Post();
		String createrIp = getRequest().getRemoteAddr();
		Date currTime = new Date();
		Set<Post> postSet =new HashSet<Post>();
		topic.setCreaterId(user);
		post.setPostCreater(user);
		topic.setForumId(forum);
		topic.setCreatTime(currTime);
		System.out.println(postText.getText());
		postText.setTittle(topic.getTittle());
		postText.setPost(post);
		
		post.setCreaterIp(createrIp);
		post.setCreatTime(currTime);
		post.setPostText(postText);
		post.setTopicId(topic);
		postSet.add(post);
		topic.setPostId(postSet);
		topicService.saveTopic(topic);
		userService.updateUser(user);
//		postService.savePost(post);
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("EEEE-MMMM-dd-yyyy"); 
		String date = bartDateFormat.format(new Date());
		try {
			Date newDate = bartDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "createTopic";
		
		
	}
	

	public String deleteTopic(){
		topicService.deleteTopic(topicService.findTopicById(topicId));
		if(admin!=null && admin.equals("1")){
			return "adminDeleteTopic";
		}
		else{
		return "deleteTopic";
		}
	}
	
	public String findAllTopic(){
		List<Topic> allTopic = topicService.findAllTopic();
		getApplication().setAttribute("AllTopic", allTopic);
		return "findAllTopic";
		
	}
	
	public String showTopic(){	
		System.out.println(topic.getTopicId());
		Post post = (Post) postService.findPostByTopic(topic.getTopicId());		
		System.out.println(post.getPostId());
		getRequest().setAttribute("post", post);
		getRequest().setAttribute("postText",postTextService.findPostTextById(post.getPostId()));
		System.out.println(postTextService.findPostTextById(post.getPostId()).getTittle());
		return "showTopic";
		
	}
//	public String ByHQLshowTopic(){
//		System.out.println(postService.findByHql(topic.getTopicId()));
//		return "ByHQLshowTopic";		
//	}

	public String findInPageTopic(){
		PageModel<GetTopic> pm1 = new PageModel<GetTopic>();
		PageModel<GetTopic> pm2= new PageModel<GetTopic>();
		pm1.setPageSize(10);
		pm1.setPageNo(page);
		pm2.setPageSize(9);
		pm2.setPageNo(1);
		getTopicService.getTotalRecords();
		pm1.setTotalRecords(getTopicService.getTotalRecords());
		getApplication().setAttribute("NewTopic", getTopicService.getNewTopic(pm2));
		getApplication().setAttribute("HotTopic", getTopicService.getHotTopic(pm2));
		getApplication().setAttribute("AllTopic", getTopicService.findTopicInPage(pm1));
		return "findInPageTopic";
	
	}
	
	public String getNewAndHotTopic(){
		PageModel<GetTopic> pm = new PageModel<GetTopic>();
		pm.setPageSize(9);
		pm.setPageNo(1);
		getApplication().setAttribute("NewTopic", getTopicService.getNewTopic(pm));
		getApplication().setAttribute("HotTopic", getTopicService.getHotTopic(pm));
		return "getNewAndHotTopic";	
	}
	
	public String adminCreateTopic(){
		Admin admin = (Admin) getSession().getAttribute("currentAdmin");
		System.out.println(admin.getUsername());
		Forum temp = forumService.findForumById(forum.getForumId());
		System.out.println(temp.getTittle());
		User user =userService.findUserByName(admin.getUsername());
		user.setTotalPost(user.getTotalPost()+1);
		Post post = new Post();
		String createrIp = getRequest().getRemoteAddr();
		Date currTime = new Date();
		Set<Post> postSet =new HashSet<Post>();
		topic.setCreaterId(user);
		post.setPostCreater(user);
		topic.setForumId(temp);
		topic.setCreatTime(currTime);
		topic.setType("普通帖子");
		System.out.println(postText.getText());
		postText.setTittle(topic.getTittle());
		postText.setPost(post);
		post.setCreaterIp(createrIp);
		post.setCreatTime(currTime);
		post.setPostText(postText);
		post.setTopicId(topic);
		postSet.add(post);
		topic.setPostId(postSet);
		topicService.saveTopic(topic);
		userService.updateUser(user);
		return "adminCreateTopic";
		
	}

	public String setHighLightTopic(){
		
		Topic temp =topicService.findTopicById(topic.getTopicId());
		temp.setTopicHighLight(highLight);
		highLight.setTopic(temp);
		highLight.setHighLightAdmin((Admin) getSession().getAttribute("currentAdmin"));
		topicService.updateTopic(temp);
		return "setHighLightTopic";
	}

	public String setCarouselTopic() throws IOException{
		System.out.println(topic.getTopicId());
		System.out.println(imageFileName);
		System.out.println(image);
		Topic temp = topicService.findTopicById(topic.getTopicId());
		temp.setCarousel("1");
		String uploadFile = topicService.uploadPicture(imageFileName,"category", image);
		temp.setCover(uploadFile);
		topicService.updateTopic(temp);
		return "setCarouselTopic";
	}
	
	public String getHighLightTopic(){
		List<Topic> list = topicService.findAllTopic();
		List<Topic> highLight  = new ArrayList<Topic>();
		for(Topic t : list){
			if(t.getTopicHighLight() != null){
				highLight.add(t);
			}
		}
		getRequest().setAttribute("highLight", highLight);
		return "getHighLightTopic";		
	}
	
	public String getCarouselTopic(){
		List<Topic> list = topicService.findAllTopic();
		List<Topic> carousel  = new ArrayList<Topic>();
		for(Topic t : list){
			if(t.getCarousel()!=null && !t.getCarousel().equals("")){
				carousel.add(t);
			}
		}
		getApplication().setAttribute("carousel", carousel);
		return "getCarouselTopic";
		
	}
	
}
