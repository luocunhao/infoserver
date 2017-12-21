package com.pulan.model.oa;
/*
 * sys_category_main
 * km_meeting_template
 * */
public class Template {
	private String fd_id;
	private String fd_name;
	public Template() {
	}
	public Template(String fd_id, String fd_name) {
		this.fd_id = fd_id;
		this.fd_name = fd_name;
	}
	public String getFd_id() {
		return fd_id;
	}
	public void setFd_id(String fd_id) {
		this.fd_id = fd_id;
	}
	public String getFd_name() {
		return fd_name;
	}
	public void setFd_name(String fd_name) {
		this.fd_name = fd_name;
	}
	
}
