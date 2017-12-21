package com.pulan.model;


public class MeetingRoom {
	private String fd_id;
	private String meetingRoomName;
	public MeetingRoom(String fd_id, String meetingRoomName) {
		this.fd_id = fd_id;
		this.meetingRoomName = meetingRoomName;
	}
	public String getFd_id() {
		return fd_id;
	}
	public void setFd_id(String fd_id) {
		this.fd_id = fd_id;
	}
	public String getMeetingRoomName() {
		return meetingRoomName;
	}
	public void setMeetingRoomName(String meetingRoomName) {
		this.meetingRoomName = meetingRoomName;
	}
	@Override
	public String toString() {
		return "MeetingRoom [fd_id=" + fd_id + ", meetingRoomName=" + meetingRoomName + "]";
	}
	
}
