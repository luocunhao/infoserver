package com.pulan.model.oa;

import java.sql.Timestamp;
import java.util.Date;

public class NotifyTodo {
	private String fd_id;
	private String fd_app_name = "花小秘APP平台";
	private String fd_model_name;
	private String fs_model_id;
	private String fd_subject;
	private String fd_type="1";
	private String fd_link;
	private String fd_bundle;
	private String fd_replace_text;
	private String fd_create_time;
	public NotifyTodo() {
	}

	public NotifyTodo(String fd_id, String fd_app_name, String fd_model_name, String fs_model_id, String fd_subject,
			String fd_type, String fd_link, String fd_bundle, String fd_replace_text,String fd_create_time) {
		this.fd_id = fd_id;
		this.fd_app_name = fd_app_name;
		this.fd_model_name = fd_model_name;
		this.fs_model_id = fs_model_id;
		this.fd_subject = fd_subject;
		this.fd_type = fd_type;
		this.fd_link = fd_link;
		this.fd_bundle = fd_bundle;
		this.fd_replace_text = fd_replace_text;
		this.fd_create_time = fd_create_time;
	}

	public String getFd_id() {
		return fd_id;
	}

	public void setFd_id(String fd_id) {
		this.fd_id = fd_id;
	}

	public String getFd_app_name() {
		return fd_app_name;
	}

	public void setFd_app_name(String fd_app_name) {
		this.fd_app_name = fd_app_name;
	}

	public String getFd_model_name() {
		return fd_model_name;
	}

	public void setFd_model_name(String fd_model_name) {
		this.fd_model_name = fd_model_name;
	}

	public String getFs_model_id() {
		return fs_model_id;
	}

	public void setFs_model_id(String fs_model_id) {
		this.fs_model_id = fs_model_id;
	}

	public String getFd_subject() {
		return fd_subject;
	}

	public void setFd_subject(String fd_subject) {
		this.fd_subject = fd_subject;
	}

	public int getFd_type() {
		return Integer.parseInt(fd_type);
	}

	public void setFd_type(String fd_type) {
		this.fd_type = fd_type;
	}

	public String getFd_link() {
		return fd_link;
	}

	public void setFd_link(String fd_link) {
		this.fd_link = fd_link;
	}

	public String getFd_bundle() {
		return fd_bundle;
	}

	public void setFd_bundle(String fd_bundle) {
		this.fd_bundle = fd_bundle;
	}

	public String getFd_replace_text() {
		return fd_replace_text;
	}

	public void setFd_replace_text(String fd_replace_text) {
		this.fd_replace_text = fd_replace_text;
	}
	
	public Date getFd_create_time() {
		Date date = null;
		try {
			Timestamp stp=Timestamp.valueOf(fd_create_time);
			date = new Date(stp.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public void setFd_create_time(String fd_create_time) {
		this.fd_create_time = fd_create_time;
	}

	@Override
	public String toString() {
		return "NotifyTodo [fd_id=" + fd_id + ", fd_app_name=" + fd_app_name + ", fd_model_name=" + fd_model_name
				+ ", fs_model_id=" + fs_model_id + ", fd_subject=" + fd_subject + ", fd_type=" + fd_type + ", fd_link="
				+ fd_link + ", fd_bundle=" + fd_bundle + ", fd_replace_text=" + fd_replace_text + "]";
	}
	
}
