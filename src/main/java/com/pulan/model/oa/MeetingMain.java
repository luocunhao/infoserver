package com.pulan.model.oa;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MeetingMain {
	private String fd_id;
	private String doc_subject;
	private String hbm_hold_date;
	private String fd_hold_date_time;
	private String hbm_hold_time;
	private String hbm_finish_date;
	private String hbm_finish_time;
	private String fd_source_continue = "0";
	private String fd_clean_todo_flag = "1";
	private String doc_content;
	private String doc_status = "30";
	private String fd_hurry_summary = "0";
	private String doc_create_time;
	private String auth_reader_flag = "0";
	//最后一次修改时间   默认为doc创建时间
	private String fd_last_modified_time;
	private String doc_creator_id;
	private String fd_emcee_id;
	private String doc_dept_id;
	private String fd_template_id;
	private String auth_att_nodownload = "0";
	private String auth_att_nocopy = "0";
	private String auth_att_noprint = "0";
	//todo  email
	private String fd_notify_type = "todo";
	private String fd_host_id;
	private String fd_hold_place;
	private String fd_summary_flag = "0";
	public MeetingMain() {
	}

	public MeetingMain(String fd_id, String doc_subject, String hbm_hold_date, String fd_hold_date_time,
			String hbm_hold_time, String hbm_finish_date, String hbm_finish_time, String fd_source_continue,
			String fd_clean_todo_flag, String doc_content, String doc_status, String fd_hurry_summary,
			String doc_create_time, String auth_reader_flag, String fd_last_modified_time, String doc_creator_id,
			String fd_emcee_id, String doc_dept_id, String fd_template_id, String auth_att_nodownload,
			String auth_att_nocopy, String auth_att_noprint,String fd_notify_type,String fd_host_id,String fd_hold_place,String fd_summary_flag) {
		this.fd_id = fd_id;
		this.doc_subject = doc_subject;
		this.hbm_hold_date = hbm_hold_date;
		this.fd_hold_date_time = fd_hold_date_time;
		this.hbm_hold_time = hbm_hold_time;
		this.hbm_finish_date = hbm_finish_date;
		this.hbm_finish_time = hbm_finish_time;
		this.fd_source_continue = fd_source_continue;
		this.fd_clean_todo_flag = fd_clean_todo_flag;
		this.doc_content = doc_content;
		this.doc_status = doc_status;
		this.fd_hurry_summary = fd_hurry_summary;
		this.doc_create_time = doc_create_time;
		this.auth_reader_flag = auth_reader_flag;
		this.fd_last_modified_time = fd_last_modified_time;
		this.doc_creator_id = doc_creator_id;
		this.fd_emcee_id = fd_emcee_id;
		this.doc_dept_id = doc_dept_id;
		this.fd_template_id = fd_template_id;
		this.auth_att_nodownload = auth_att_nodownload;
		this.auth_att_nocopy = auth_att_nocopy;
		this.auth_att_noprint = auth_att_noprint;
		this.fd_notify_type = fd_notify_type;
		this.fd_host_id = fd_host_id;
		this.fd_hold_place = fd_hold_place;
		this.fd_summary_flag = fd_summary_flag;
	}

	public String getFd_id() {
		return fd_id;
	}

	public void setFd_id(String fd_id) {
		this.fd_id = fd_id;
	}

	public String getDoc_subject() {
		return doc_subject;
	}

	public void setDoc_subject(String doc_subject) {
		this.doc_subject = doc_subject;
	}

	public BigDecimal getHbm_hold_date() {
		
		return  new BigDecimal(hbm_hold_date);
	}

	public void setHbm_hold_date(String hbm_hold_date) {
		this.hbm_hold_date = hbm_hold_date;
	}

	public java.sql.Date getFd_hold_date_time() {
		//"2017-11-22 15:00:00"
		DateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date date = null;
		try {
			date = new java.sql.Date(sd.parse(fd_hold_date_time).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public void setFd_hold_date_time(String fd_hold_date_time) {
		this.fd_hold_date_time = fd_hold_date_time;
	}

	public BigDecimal getHbm_hold_time() {
		return new BigDecimal(hbm_hold_time);
	}

	public void setHbm_hold_time(String hbm_hold_time) {
		this.hbm_hold_time = hbm_hold_time;
	}

	public BigDecimal getHbm_finish_date() {
		return new BigDecimal(hbm_finish_date);
	}

	public void setHbm_finish_date(String hbm_finish_date) {
		this.hbm_finish_date = hbm_finish_date;
	}

	public BigDecimal getHbm_finish_time() {
		return new BigDecimal(hbm_finish_time);
	}

	public void setHbm_finish_time(String hbm_finish_time) {
		this.hbm_finish_time = hbm_finish_time;
	}

	public byte getFd_source_continue() {
		return (byte) (fd_source_continue.getBytes()[0] - 48);
	}

	public void setFd_source_continue(String fd_source_continue) {
		this.fd_source_continue = fd_source_continue;
	}

	public byte getFd_clean_todo_flag() {
		return (byte) (fd_clean_todo_flag.getBytes()[0] - 48);
	}

	public void setFd_clean_todo_flag(String fd_clean_todo_flag) {
		this.fd_clean_todo_flag = fd_clean_todo_flag;
	}

	public String getDoc_content() {
		return doc_content;
	}

	public void setDoc_content(String doc_content) {
		this.doc_content = doc_content;
	}

	public String getDoc_status() {
		return doc_status;
	}

	public void setDoc_status(String doc_status) {
		this.doc_status = doc_status;
	}

	public byte getFd_hurry_summary() {
		return (byte) (fd_hurry_summary.getBytes()[0] - 48);
	}

	public void setFd_hurry_summary(String fd_hurry_summary) {
		this.fd_hurry_summary = fd_hurry_summary;
	}

	public Date getDoc_create_time() {
		Date date = null;
		try {
			Timestamp stp=Timestamp.valueOf(doc_create_time);
			date = new Date(stp.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public void setDoc_create_time(String doc_create_time) {
		this.doc_create_time = doc_create_time;
	}

	public byte getAuth_reader_flag() {
		return (byte) (auth_reader_flag.getBytes()[0] - 48);
	}

	public void setAuth_reader_flag(String auth_reader_flag) {
		this.auth_reader_flag = auth_reader_flag;
	}

	public Date getFd_last_modified_time() {
		Date date = null;
		try {
			Timestamp stp=Timestamp.valueOf(fd_last_modified_time);
			date = new Date(stp.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public void setFd_last_modified_time(String fd_last_modified_time) {
		this.fd_last_modified_time = fd_last_modified_time;
	}

	public String getDoc_creator_id() {
		return doc_creator_id;
	}

	public void setDoc_creator_id(String doc_creator_id) {
		this.doc_creator_id = doc_creator_id;
	}

	public String getFd_emcee_id() {
		return fd_emcee_id;
	}

	public void setFd_emcee_id(String fd_emcee_id) {
		this.fd_emcee_id = fd_emcee_id;
	}

	public String getDoc_dept_id() {
		return doc_dept_id;
	}

	public void setDoc_dept_id(String doc_dept_id) {
		this.doc_dept_id = doc_dept_id;
	}

	public String getFd_template_id() {
		return fd_template_id;
	}

	public void setFd_template_id(String fd_template_id) {
		this.fd_template_id = fd_template_id;
	}

	public byte getAuth_att_nodownload() {
		return (byte) (auth_att_nodownload.getBytes()[0] - 48);
	}

	public void setAuth_att_nodownload(String auth_att_nodownload) {
		this.auth_att_nodownload = auth_att_nodownload;
	}

	public byte getAuth_att_nocopy() {
		return (byte) (auth_att_nocopy.getBytes()[0] - 48);
	}

	public void setAuth_att_nocopy(String auth_att_nocopy) {
		this.auth_att_nocopy = auth_att_nocopy;
	}

	public byte getAuth_att_noprint() {
		return (byte) (auth_att_noprint.getBytes()[0] - 48);
	}

	public void setAuth_att_noprint(String auth_att_noprint) {
		this.auth_att_noprint = auth_att_noprint;
	}

	public String getFd_notify_type() {
		return fd_notify_type;
	}

	public void setFd_notify_type(String fd_notify_type) {
		this.fd_notify_type = fd_notify_type;
	}

	public String getFd_host_id() {
		return fd_host_id;
	}

	public void setFd_host_id(String fd_host_id) {
		this.fd_host_id = fd_host_id;
	}

	public String getFd_hold_place() {
		return fd_hold_place;
	}

	public void setFd_hold_place(String fd_hold_place) {
		this.fd_hold_place = fd_hold_place;
	}

	public byte getFd_summary_flag() {
		return (byte) (fd_summary_flag.getBytes()[0] - 48);
	}

	public void setFd_summary_flag(String fd_summary_flag) {
		this.fd_summary_flag = fd_summary_flag;
	}
	
}
