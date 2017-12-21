package com.pulan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.MeetingOADao;
import com.pulan.datasource.DatabaseContextHolder;
import com.pulan.datasource.DatabaseType;
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
import com.pulan.service.MeetingOAService;
@Service
public class MeetingOAServiceImpl implements MeetingOAService{
    @Autowired
    private MeetingOADao meetingOADao;
	@Override
	public void insertMeetingMain(MeetingMain meetingMain) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		meetingOADao.insertMeetingMain(meetingMain);
	}

	@Override
	public void insertMeetingMainAttendPerson(MeetingMainAttendPerson meetingMainAttendPerson) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		meetingOADao.insertMeetingMainAttendPerson(meetingMainAttendPerson);
	}

	@Override
	public void insertMeetingMainCopyPerson(MeetingMainCopyPerson meetingMainCopyPerson) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		meetingOADao.insertMeetingMainCopyPerson(meetingMainCopyPerson);
	}

	@Override
	public void insertMeetingMainUseSource(MeetingMainUseSource meetingMainUseSource) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		meetingOADao.insertMeetingMainUseSource(meetingMainUseSource);
	}

	@Override
	public void insertMeetingresMain(MeetingresMain meetingresMain) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		meetingOADao.insertMeetingresMain(meetingresMain);
	}

	@Override
	public void insertNotifyTodo(NotifyTodo notifyTodo) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		meetingOADao.insertNotifyTodo(notifyTodo);
	}

	@Override
	public void insertNotifyTodoTarget(NotifyTodoTarget notifyTodoTarget) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		meetingOADao.insertNotifyTodoTarget(notifyTodoTarget);
	}

	@Override
	public void insertMeetingMainAreader(MeetingMainAreader MeetingMainAreader) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		meetingOADao.insertMeetingMainAreader(MeetingMainAreader);
	}

	@Override
	public void insertMeetingMainReader(MeetingMainReader meetingMainReader) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		meetingOADao.insertMeetingMainReader(meetingMainReader);
	}

	@Override
	public String getEleIdByLoginName(String loginName) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		return meetingOADao.getEleIdByLoginName(loginName);
	}

	@Override
	public List<Template> getAllCategoryMain() {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		return meetingOADao.getAllCategoryMain();
	}

	@Override
	public List<Template> getAllKmMeetingTemplate(String doc_category_id) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		return meetingOADao.getAllKmMeetingTemplate(doc_category_id);
	}

	@Override
	public List<Template> getAllOrgPerson() {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		return meetingOADao.getAllOrgPerson();
	}

	@Override
	public List<String> getEmpByfdid(String fd_id) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		return meetingOADao.getEmpByfdid(fd_id);
	}

}
