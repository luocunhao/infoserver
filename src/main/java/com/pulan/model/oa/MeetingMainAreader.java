package com.pulan.model.oa;

public class MeetingMainAreader {
	private String fd_main_id;
	private String auth_all_reader_id;
	public MeetingMainAreader(){}
	public MeetingMainAreader(String fd_main_id, String auth_all_reader_id) {
		this.fd_main_id = fd_main_id;
		this.auth_all_reader_id = auth_all_reader_id;
	}
	public String getFd_main_id() {
		return fd_main_id;
	}
	public void setFd_main_id(String fd_main_id) {
		this.fd_main_id = fd_main_id;
	}
	public String getAuth_all_reader_id() {
		return auth_all_reader_id;
	}
	public void setAuth_all_reader_id(String auth_all_reader_id) {
		this.auth_all_reader_id = auth_all_reader_id;
	}
	
}
