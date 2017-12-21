package com.pulan.model.oa;

public class MeetingMainUseSource {
	private String fd_main_id;
	private String fd_resource_info_id;
	
	public MeetingMainUseSource() {
	}
	public MeetingMainUseSource(String fd_main_id, String fd_resource_info_id) {
		this.fd_main_id = fd_main_id;
		this.fd_resource_info_id = fd_resource_info_id;
	}
	public String getFd_main_id() {
		return fd_main_id;
	}
	public void setFd_main_id(String fd_main_id) {
		this.fd_main_id = fd_main_id;
	}
	public String getFd_resource_info_id() {
		return fd_resource_info_id;
	}
	public void setFd_resource_info_id(String fd_resource_info_id) {
		this.fd_resource_info_id = fd_resource_info_id;
	}
	@Override
	public String toString() {
		return "MeetingMainUseSource [fd_main_id=" + fd_main_id + ", fd_resource_info_id=" + fd_resource_info_id + "]";
	}
	
}
