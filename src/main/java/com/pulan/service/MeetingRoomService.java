package com.pulan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pulan.model.MeetingRoom;


@Service
public interface MeetingRoomService {
	//获取所有会议室
	public List<String> getAllMeetingRoom();
	//根据时间获取被预定的会议室
	public List<String> getMeetingRoomBytime(long time,long endtime);
	//根据fd_id查会议室
	public List<MeetingRoom> getRoomNameByString(String fd_id);
}
