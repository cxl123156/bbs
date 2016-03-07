package edu.zsc.cxl.bbs.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import edu.zsc.cxl.bbs.container.GetTopic;
import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.entity.Category;
import edu.zsc.cxl.bbs.entity.Forum;
import edu.zsc.cxl.bbs.entity.Topic;
import edu.zsc.cxl.bbs.service.CategoryService;
import edu.zsc.cxl.bbs.service.GetTopicService;
import edu.zsc.cxl.bbs.service.TopicService;
/**
 * 
 */
public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private ServletContext application;
	private GetTopicService getTopicService;
	private CategoryService categoryService;
	private TopicService topicService;
	private String admin1;
	
	
	
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	public String getAdmin1() {
		return admin1;
	}
	public void setAdmin1(String admin1) {
		this.admin1 = admin1;
	}
	@Autowired
	public void setGetTopicService(GetTopicService getTopicService) {
		this.getTopicService = getTopicService;
	}
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		session = request.getSession();
		application = session.getServletContext();
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public HttpSession getSession() {
		return session;
	}
	public ServletContext getApplication() {
		return application;
	}
	
	public String findInPageTopicIndex(){
		PageModel<GetTopic> pm1 = new PageModel<GetTopic>();
		PageModel<GetTopic> pm2= new PageModel<GetTopic>();
		pm1.setPageSize(10);
		pm1.setPageNo(1);
		pm2.setPageSize(9);
		pm2.setPageNo(1);
		getTopicService.getTotalRecords();
		pm1.setTotalRecords(getTopicService.getTotalRecords());		
		List<Topic> list = topicService.findAllTopic();
		List<Topic> carousel  = new ArrayList<Topic>();
		for(Topic t : list){
			if(t.getCarousel()!=null && !t.getCarousel().equals("")){
				carousel.add(t);
			}
		}
		getApplication().setAttribute("carousel", carousel);	
		getApplication().setAttribute("NewTopic", getTopicService.getNewTopic(pm2));
		getApplication().setAttribute("HotTopic", getTopicService.getHotTopic(pm2));
		getApplication().setAttribute("AllTopic", getTopicService.findTopicInPage(pm1));
		return "findInPageTopicIndex";
	
	}
	public String getForumAndCategoryIndex(){
		List<Category> catList=categoryService.findAllCategory();
		getApplication().setAttribute("category", catList);
		Iterator<Category> itCate = catList.iterator();
		LinkedHashMap<Category, List<Forum>> catMap = new LinkedHashMap<Category, List<Forum>>();
		Category cat;
		while(itCate.hasNext()){
			List<Forum> forumList = new ArrayList<>();
			cat=itCate.next();
			List<Forum> getForumList =new ArrayList<Forum>();
			getForumList=cat.getForumId();
			Iterator<Forum> itForum = getForumList.iterator();
			while(itForum.hasNext()){
				forumList.add(itForum.next());
			}
			catMap.put(cat, forumList);
		}
		getApplication().setAttribute("cateAndForum", catMap);
		if(admin1!=null&&admin1.equals("1")){
			return "adminGetForumAndCategoryIndex";
		}
		else{
		return "getForumAndCategoryIndex";
		}
	}

}
