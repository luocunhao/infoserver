package com.pulan.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pulan.model.Meeting;

public interface MeetingDao {
	public List<Meeting> getAllMeeting(@Param("login_name") String login_time);
}
