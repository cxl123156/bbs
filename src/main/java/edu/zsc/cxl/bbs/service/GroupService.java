package edu.zsc.cxl.bbs.service;

import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.entity.Group;

public interface GroupService {

	public void saveGroup(Group group);
	public void updateGroup(Group group);
	public void deleteGroup(Group group);
	public Group findGroupById(Serializable groupId);
	public List<Group> findAllGroup();
	public Group findGroupByName(String name);
}
