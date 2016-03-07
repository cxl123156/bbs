package edu.zsc.cxl.bbs.dao.impl;


import org.springframework.stereotype.Repository;

import edu.zsc.cxl.bbs.dao.TopicDao;
import edu.zsc.cxl.bbs.entity.Topic;

@Repository("topicDao")
public class TopicDaoImpl extends baseDaoImpl<Topic> implements TopicDao {


}
