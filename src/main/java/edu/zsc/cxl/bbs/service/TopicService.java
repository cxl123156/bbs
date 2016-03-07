package edu.zsc.cxl.bbs.service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.container.GetTopic;
import edu.zsc.cxl.bbs.entity.Topic;

public interface TopicService {

	public void saveTopic(Topic topic);
	public void updateTopic(Topic topic);
	public void deleteTopic(Topic topic);
	public Topic findTopicById(Serializable id);
	public Topic findTopicByTittle(String tittle);
	public List<Topic> findAllTopic();
	public void deleteByHQL(Serializable topicId);
	public String uploadPicture(String imageFileName, String folder,File image) throws IOException;
}
