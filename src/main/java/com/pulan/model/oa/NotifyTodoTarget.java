package com.pulan.model.oa;

public class NotifyTodoTarget {
	private String fd_todoid;
	private String fd_orgid;
	
	public NotifyTodoTarget() {
	}

	public NotifyTodoTarget(String fd_todoid, String fd_orgid) {
		this.fd_todoid = fd_todoid;
		this.fd_orgid = fd_orgid;
	}

	public String getFd_todoid() {
		return fd_todoid;
	}

	public void setFd_todoid(String fd_todoid) {
		this.fd_todoid = fd_todoid;
	}

	public String getFd_orgid() {
		return fd_orgid;
	}

	public void setFd_orgid(String fd_orgid) {
		this.fd_orgid = fd_orgid;
	}
	
}
