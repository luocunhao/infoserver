package com.pulan.model.oa;

public class MeetingMainCopyPerson {
	private String fd_main_id;
	private String fd_main_copy_id;
	
	public MeetingMainCopyPerson() {
	}

	public MeetingMainCopyPerson(String fd_main_id, String fd_main_copy_id) {
		this.fd_main_id = fd_main_id;
		this.fd_main_copy_id = fd_main_copy_id;
	}

	public String getFd_main_id() {
		return fd_main_id;
	}

	public void setFd_main_id(String fd_main_id) {
		this.fd_main_id = fd_main_id;
	}

	public String getFd_main_copy_id() {
		return fd_main_copy_id;
	}

	public void setFd_main_copy_id(String fd_main_copy_id) {
		this.fd_main_copy_id = fd_main_copy_id;
	}

	@Override
	public String toString() {
		return "MeetingMainCopyPerson [fd_main_id=" + fd_main_id + ", fd_main_copy_id=" + fd_main_copy_id + "]";
	}
	
}
