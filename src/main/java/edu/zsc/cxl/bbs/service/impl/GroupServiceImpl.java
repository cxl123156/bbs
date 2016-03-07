package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.dao.GroupDao;
import edu.zsc.cxl.bbs.entity.Group;
import edu.zsc.cxl.bbs.service.GroupService;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

	GroupDao groupDao;
	
	@Autowired
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void saveGroup(Group group) {
		groupDao.save(group);
	}

	@Override
	public void updateGroup(Group group) {
		groupDao.update(group);
	}

	@Override
	public void deleteGroup(Group group) {
		groupDao.delete(group);
	}

	@Override
	public Group findGroupById(Serializable groupId) {
		
		return groupDao.findById(groupId);
	}

	@Override
	public List<Group> findAllGroup() {
		String hql ="from Group";	
		return groupDao.getListByHQL(hql);
	}

	@Override
	public Group findGroupByName(String name) {
		String hql = "from Group where groupName = ?";
		return groupDao.getByHQL(hql, name);
	}

}
