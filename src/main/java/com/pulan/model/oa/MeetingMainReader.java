package com.pulan.model.oa;

public class MeetingMainReader {
	private String fd_main_id;
	private String auth_reader_id;
	public MeetingMainReader(){}
	public MeetingMainReader(String fd_main_id, String auth_reader_id) {
		this.fd_main_id = fd_main_id;
		this.auth_reader_id = auth_reader_id;
	}
	public String getFd_main_id() {
		return fd_main_id;
	}
	public void setFd_main_id(String fd_main_id) {
		this.fd_main_id = fd_main_id;
	}
	public String getAuth_reader_id() {
		return auth_reader_id;
	}
	public void setAuth_reader_id(String auth_reader_id) {
		this.auth_reader_id = auth_reader_id;
	}
	
}
