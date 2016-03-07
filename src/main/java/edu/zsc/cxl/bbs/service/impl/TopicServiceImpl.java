package edu.zsc.cxl.bbs.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import edu.zsc.cxl.bbs.container.GetTopic;
import edu.zsc.cxl.bbs.dao.TopicDao;
import edu.zsc.cxl.bbs.entity.Topic;
import edu.zsc.cxl.bbs.service.TopicService;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

	TopicDao topicDao;
	
	public TopicDao getTopicDao() {
		return topicDao;
	}
    
	@Autowired
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	@Override
	public void saveTopic(Topic topic) {
		topicDao.save(topic);
	}

	@Override
	public void updateTopic(Topic topic) {
		topicDao.update(topic);
	}

	@Override
	public void deleteTopic(Topic topic) {
		topicDao.delete(topic);
	}

	@Override
	public Topic findTopicById(Serializable id) {
		return topicDao.findById(id);
	}

	@Override
	public Topic findTopicByTittle(String tittle) {
		String hql = "from Topic where tittle = ?";
		return topicDao.getByHQL(hql, tittle);
	}

	@Override
	public List<Topic> findAllTopic() {
		String hql = "from Topic ";	
		return topicDao.getListByHQL(hql);
	}

	@Override
	public void deleteByHQL(Serializable topicId) {
		String hql = "SET FOREIGN_KEY_CHECKS = 0 delete Topic t where t.topicId="+topicId;
		topicDao.deleteByHQL(hql);
		
	}

	@Override
	public String uploadPicture(String filename, String folder, File image) throws IOException{
		String realpath = ServletActionContext.getServletContext().getRealPath("/userUpload/"+folder);
		   if (image != null) {
	            File savefile = new File(new File(realpath), filename);
	            if (!savefile.getParentFile().exists())
	                savefile.getParentFile().mkdirs();
	            FileUtils.copyFile(image, savefile);
	            ActionContext.getContext().put("message", "文件上传成功");
	        }
		   return folder+"/"+filename;
	}



}
