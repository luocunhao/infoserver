package com.pulan.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.MeetingRoomDao;
import com.pulan.datasource.DatabaseContextHolder;
import com.pulan.datasource.DatabaseType;
import com.pulan.model.MeetingRoom;
import com.pulan.service.MeetingRoomService;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService{
	@Autowired
	private MeetingRoomDao meetingRoomDao;

	@Override
	public List<String> getAllMeetingRoom() {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		return meetingRoomDao.getAllMeetingRoom();
	}

	@Override
	public List<String> getMeetingRoomBytime(long starttime,long endtime) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.EKP);
		return meetingRoomDao.getMeetingRoomBytime(starttime,endtime);
	}


	@Override
	public List<MeetingRoom> getRoomNameByString(String fd_id) {
		// TODO Auto-generated method stub
		return meetingRoomDao.getRoomNameByString(fd_id);
	}
	
}
