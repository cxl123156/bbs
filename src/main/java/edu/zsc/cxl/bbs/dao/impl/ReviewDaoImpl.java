package edu.zsc.cxl.bbs.dao.impl;


import org.springframework.stereotype.Repository;

import edu.zsc.cxl.bbs.dao.ReviewDao;
import edu.zsc.cxl.bbs.entity.Review;

@Repository("reviewDao")
public class ReviewDaoImpl extends baseDaoImpl<Review> implements ReviewDao {


}
