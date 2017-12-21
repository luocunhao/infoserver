package com.pulan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.MeetingDao;
import com.pulan.datasource.DatabaseContextHolder;
import com.pulan.datasource.DatabaseType;
import com.pulan.model.Meeting;
import com.pulan.service.MeetingService;
@Service
public class MeetingServiceImpl implements MeetingService{
	@Autowired
	private MeetingDao meetingDao;
	@Override
	public List<Meeting> getAllMeeting(String login_name) {
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		// TODO Auto-generated method stub
		return meetingDao.getAllMeeting(login_name);
	}
	
}
