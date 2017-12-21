package com.pulan.model.oa;

public class MeetingMainAttendPerson {
	private String fd_main_id;
	private String fd_main_attend_id;
	
	public MeetingMainAttendPerson() {
	}

	public MeetingMainAttendPerson(String fd_main_id, String fd_main_attend_id) {
		this.fd_main_id = fd_main_id;
		this.fd_main_attend_id = fd_main_attend_id;
	}

	public String getFd_main_id() {
		return fd_main_id;
	}

	public void setFd_main_id(String fd_main_id) {
		this.fd_main_id = fd_main_id;
	}

	public String getFd_main_attend_id() {
		return fd_main_attend_id;
	}

	public void setFd_main_attend_id(String fd_main_attend_id) {
		this.fd_main_attend_id = fd_main_attend_id;
	}

	@Override
	public String toString() {
		return "MeetingMainAttendPerson [fd_main_id=" + fd_main_id + ", fd_main_attend_id=" + fd_main_attend_id + "]";
	}
	
	
}
