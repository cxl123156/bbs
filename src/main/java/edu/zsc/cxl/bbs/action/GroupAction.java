package edu.zsc.cxl.bbs.action;

import org.springframework.beans.factory.annotation.Autowired;

import edu.zsc.cxl.bbs.entity.Group;
import edu.zsc.cxl.bbs.service.GroupService;

public class GroupAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Group group;
	private GroupService groupService;
	private Long groupId;
	
	
	
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	@Autowired
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	public String createGroup(){
		System.out.println(group.getGroupName());
		groupService.saveGroup(group);
		return "createGroup";	
	}
	
	public String deleteGroup(){
		
		groupService.deleteGroup(groupService.findGroupById(group.getGroupId()));
		return "deleteGroup";
	}

	public String updateGroup(){
		groupService.updateGroup(group);
		return "updateGroup";
		
	}
	
	public String findAllGroup(){
		
		getRequest().setAttribute("allGroup", groupService.findAllGroup());
		return "findAllGroup";
		
	}
	
}
