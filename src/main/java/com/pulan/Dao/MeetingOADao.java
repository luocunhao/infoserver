package com.pulan.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

public interface MeetingOADao {
	public void insertMeetingMain(MeetingMain meetingMain);
	public void insertMeetingMainAttendPerson(MeetingMainAttendPerson meetingMainAttendPerson);
	public void insertMeetingMainCopyPerson(MeetingMainCopyPerson meetingMainCopyPerson);
	public void insertMeetingMainUseSource(MeetingMainUseSource meetingMainUseSource);
	public void insertMeetingresMain(MeetingresMain meetingresMain);
	public void insertNotifyTodo(NotifyTodo notifyTodo);
	public void insertNotifyTodoTarget(NotifyTodoTarget notifyTodoTarget);
	public void insertMeetingMainAreader(MeetingMainAreader meetingMainAreader);
	public void insertMeetingMainReader(MeetingMainReader meetingMainReader);
	public String getEleIdByLoginName(@Param("loginName") String loginName);
	public List<Template> getAllCategoryMain();
	public List<Template> getAllKmMeetingTemplate(@Param("doc_category_id") String doc_category_id);
	public List<Template> getAllOrgPerson();
	public List<String> getEmpByfdid(@Param("fd_id") String fd_id);
	
}
