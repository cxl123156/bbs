package edu.zsc.cxl.bbs.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;

import edu.zsc.cxl.bbs.container.GetPost;
import edu.zsc.cxl.bbs.container.GetReview;
import edu.zsc.cxl.bbs.container.GetTopic;
import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.entity.Post;
import edu.zsc.cxl.bbs.entity.PostText;
import edu.zsc.cxl.bbs.entity.Topic;
import edu.zsc.cxl.bbs.entity.User;
import edu.zsc.cxl.bbs.service.GetPostService;
import edu.zsc.cxl.bbs.service.GetReviewService;
import edu.zsc.cxl.bbs.service.GetTopicService;
import edu.zsc.cxl.bbs.service.PostService;
import edu.zsc.cxl.bbs.service.PostTextService;
import edu.zsc.cxl.bbs.service.TopicService;

public class PostAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Post post; 
	private PostService postService ;
	private PostText postText;
	private Long topicId;
	private TopicService topicService;
	private GetPostService getPostService;
	private GetTopicService getTopicService;
	private int page=1;
	private PostTextService postTextService;
	private GetReviewService getReviewService;
	private Long postId;

	
	
	
	
	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public void setGetReviewService(GetReviewService getReviewService) {
		this.getReviewService = getReviewService;
	}

	@Autowired
    public void setPostTextService(PostTextService postTextService) {
		this.postTextService = postTextService;
	}

	public PostService getPostService() {
		return postService;
	}

	public TopicService getTopicService() {
		return topicService;
	}

	public GetPostService getGetPostService() {
		return getPostService;
	}

	public GetTopicService getGetTopicService() {
		return getTopicService;
	}

	@Autowired
	public void setGetTopicService(GetTopicService getTopicService) {
		this.getTopicService = getTopicService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Autowired
	public void setGetPostService(GetPostService getPostService) {
		this.getPostService = getPostService;
	}

	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public PostText getPostText() {
		return postText;
	}
	public void setPostText(PostText postText) {
		this.postText = postText;
	}
	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	/**
	 * @return
	 */
	public String createPost(){
		String createrIp = getRequest().getRemoteAddr();
		Date currentTime = new Date();
		User user =(User) getSession().getAttribute("currentUser");
		System.out.println(user.getUserName());
		Post p = new Post();
		p.setPostText(postText);
		p.setCreaterIp(createrIp);
		p.setCreatTime(currentTime);
		Topic topic =topicService.findTopicById(topicId);
		topic.setLastReplyTime(currentTime);
		topic.setPostCount(topic.getPostCount()+1);
		p.setTopicId(topic);
		if(user.getUserId().equals(topic.getCreaterId().getUserId()))
			p.setPostCreater(topic.getCreaterId());
		else
			p.setPostCreater(user);
		postText.setPost(p);
		postService.savePost(p);
		topicService.updateTopic(topic);
		return "createPost";
		
	}
	
	public String showAllPost(){
		if(topicId==null){
			GetTopic gt=(GetTopic) getSession().getAttribute("currentTopic");
			topicId=gt.getTopicId();
		}
		PageModel<GetPost> pm = new PageModel<GetPost>();
		pm.setPageSize(10);
		pm.setPageNo(page);		
		Topic topic = topicService.findTopicById(topicId);
		topic.setViewCount(topic.getViewCount()+1);
		topicService.updateTopic(topic);
		List<GetPost> list = getPostService.showAllPost(pm, topicId);
		LinkedHashMap<GetPost, List<GetReview>> postAndReview =new LinkedHashMap<GetPost, List<GetReview>>();
		pm.setTotalRecords((long) list.size());
		for(Object o : list){
			GetPost gp = (GetPost) o;
			gp.getPostId();		
			postAndReview.put(gp, getReviewService.getAllReview(gp.getPostId()));
			
		}
		getRequest().setAttribute("allPost", postAndReview);
		getSession().setAttribute("currentTopic", getTopicService.getTopicById(topicId));
		return "showAllPost";
		
	}
	
	public String deletePost(){
		
		postService.deletePost(postService.findPostById(postId));
		return "deletePost";
		
	}


}
