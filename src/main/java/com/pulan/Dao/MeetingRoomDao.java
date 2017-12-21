package com.pulan.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pulan.model.MeetingRoom;


public interface MeetingRoomDao {
	//获取所有会议室
	public List<String> getAllMeetingRoom();
	//根据时间获取被预定的会议室
	public List<String> getMeetingRoomBytime(@Param("starttime")long starttime,@Param("endtime")long endtime);
	public List<MeetingRoom> getRoomNameByString(@Param("fd_id") String fd_id);
}
