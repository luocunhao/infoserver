package com.pulan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pulan.model.oa.MeetingMain;
import com.pulan.model.oa.MeetingMainAreader;
import com.pulan.model.oa.MeetingMainAttendPerson;
import com.pulan.model.oa.MeetingMainCopyPerson;
import com.pulan.model.oa.MeetingMainReader;
import com.pulan.model.oa.MeetingMainUseSource;
import com.pulan.model.oa.MeetingresMain;
import com.pulan.model.oa.NotifyTodo;
import com.pulan.model.oa.NotifyTodoTarget;
import com.pulan.model.oa.Template;
@Service
public interface MeetingOAService {
	public void insertMeetingMain(MeetingMain meetingMain);
	public void insertMeetingMainAttendPerson(MeetingMainAttendPerson meetingMainAttendPerson);
	public void insertMeetingMainCopyPerson(MeetingMainCopyPerson meetingMainCopyPerson);
	public void insertMeetingMainUseSource(MeetingMainUseSource meetingMainUseSource);
	public void insertMeetingresMain(MeetingresMain meetingresMain);
	public void insertNotifyTodo(NotifyTodo notifyTodo);
	public void insertNotifyTodoTarget(NotifyTodoTarget notifyTodoTarget);
	public void insertMeetingMainAreader(MeetingMainAreader MeetingMainAreader);
	public void insertMeetingMainReader(MeetingMainReader meetingMainReader);
	public String getEleIdByLoginName(String loginName);
	//获取所有顶层会议名称
	public List<Template> getAllCategoryMain();
	//根据顶层会议获取所有下次会议模板
	public List<Template> getAllKmMeetingTemplate(String doc_category_id);
	//获取人员信息
	public List<Template> getAllOrgPerson();
	//根据人获取其部门
	public List<String> getEmpByfdid(String fd_id);
}
