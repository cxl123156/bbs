package edu.zsc.cxl.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import edu.zsc.cxl.bbs.dao.PostTextDao;
import edu.zsc.cxl.bbs.entity.PostText;

@Repository("postTextDao")
public class PostTextDaoImpl extends baseDaoImpl<PostText> implements PostTextDao{

}
