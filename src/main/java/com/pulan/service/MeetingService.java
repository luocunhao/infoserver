package com.pulan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pulan.model.Meeting;
@Service
public interface MeetingService {
	public List<Meeting> getAllMeeting(String login_time);
}
