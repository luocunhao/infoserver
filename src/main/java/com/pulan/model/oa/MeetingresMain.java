package com.pulan.model.oa;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class MeetingresMain {
	private String fd_id;
	private String doc_subject;
	private String doc_create_time;
	private String fd_time_interval;
	private String doc_status="30";
	private String hbm_start_date;
	private String hbm_start_time;
	private String fd_order_start_date_time;
	private String hbm_finish_date;
	private String hbm_finish_time;
	private String fd_order_finish_date_time;
	private String fd_resource_continue = "0";
	private String fd_borrow_reason;
	private String auth_reader_flag = "0";
	private String doc_publish_time;
	private String fd_last_modified_time;
	private String fd_meeting_id;
	private String doc_creator_id;
	private String fd_proposer_id;
	private String doc_dept_id;
	private String fd_resource_id;
	public MeetingresMain(){}
	public MeetingresMain(String fd_id, String doc_subject, String doc_create_time, String fd_time_interval,
			String doc_status, String hbm_start_date, String hbm_start_time, String fd_order_start_date_time,
			String hbm_finish_date, String hbm_finish_time, String fd_order_finish_date_time,
			String fd_resource_continue, String fd_borrow_reason, String auth_reader_flag, String doc_publish_time,
			String fd_last_modified_time, String fd_meeting_id, String doc_creator_id, String fd_proposer_id,
			String doc_dept_id, String fd_resource_id) {
		this.fd_id = fd_id;
		this.doc_subject = doc_subject;
		this.doc_create_time = doc_create_time;
		this.fd_time_interval = fd_time_interval;
		this.doc_status = doc_status;
		this.hbm_start_date = hbm_start_date;
		this.hbm_start_time = hbm_start_time;
		this.fd_order_start_date_time = fd_order_start_date_time;
		this.hbm_finish_date = hbm_finish_date;
		this.hbm_finish_time = hbm_finish_time;
		this.fd_order_finish_date_time = fd_order_finish_date_time;
		this.fd_resource_continue = fd_resource_continue;
		this.fd_borrow_reason = fd_borrow_reason;
		this.auth_reader_flag = auth_reader_flag;
		this.doc_publish_time = doc_publish_time;
		this.fd_last_modified_time = fd_last_modified_time;
		this.fd_meeting_id = fd_meeting_id;
		this.doc_creator_id = doc_creator_id;
		this.fd_proposer_id = fd_proposer_id;
		this.doc_dept_id = doc_dept_id;
		this.fd_resource_id = fd_resource_id;
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
	public BigDecimal getFd_time_interval() {
		 return new BigDecimal(fd_time_interval);
	}
	public void setFd_time_interval(String fd_time_interval) {
		this.fd_time_interval = fd_time_interval;
	}
	public String getDoc_status() {
		return doc_status;
	}
	public void setDoc_status(String doc_status) {
		this.doc_status = doc_status;
	}
	public BigDecimal getHbm_start_date() {
		return new BigDecimal(hbm_start_date);
	}
	public void setHbm_start_date(String hbm_start_date) {
		this.hbm_start_date = hbm_start_date;
	}
	public BigDecimal getHbm_start_time() {
		return new BigDecimal(hbm_start_time);
	}
	public void setHbm_start_time(String hbm_start_time) {
		this.hbm_start_time = hbm_start_time;
	}
	public Date getFd_order_start_date_time() {
		Date date = null;
		try {
			Timestamp stp=Timestamp.valueOf(fd_order_start_date_time);
			date = new Date(stp.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public void setFd_order_start_date_time(String fd_order_start_date_time) {
		this.fd_order_start_date_time = fd_order_start_date_time;
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
	public Date getFd_order_finish_date_time() {
		Date date = null;
		try {
			Timestamp stp=Timestamp.valueOf(fd_order_finish_date_time);
			date = new Date(stp.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public void setFd_order_finish_date_time(String fd_order_finish_date_time) {
		this.fd_order_finish_date_time = fd_order_finish_date_time;
	}
	public byte getFd_resource_continue() {
		return (byte) (fd_resource_continue.getBytes()[0] - 48);
	}
	public void setFd_resource_continue(String fd_resource_continue) {
		this.fd_resource_continue = fd_resource_continue;
	}
	public String getFd_borrow_reason() {
		return fd_borrow_reason;
	}
	public void setFd_borrow_reason(String fd_borrow_reason) {
		this.fd_borrow_reason = fd_borrow_reason;
	}
	public byte getAuth_reader_flag() {
		return (byte) (auth_reader_flag.getBytes()[0] - 48);
	}
	public void setAuth_reader_flag(String auth_reader_flag) {
		this.auth_reader_flag = auth_reader_flag;
	}
	public Date getDoc_publish_time() {
		Date date = null;
		try {
			Timestamp stp=Timestamp.valueOf(doc_publish_time);
			date = new Date(stp.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public void setDoc_publish_time(String doc_publish_time) {
		this.doc_publish_time = doc_publish_time;
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
	public String getFd_meeting_id() {
		return fd_meeting_id;
	}
	public void setFd_meeting_id(String fd_meeting_id) {
		this.fd_meeting_id = fd_meeting_id;
	}
	public String getDoc_creator_id() {
		return doc_creator_id;
	}
	public void setDoc_creator_id(String doc_creator_id) {
		this.doc_creator_id = doc_creator_id;
	}
	public String getFd_proposer_id() {
		return fd_proposer_id;
	}
	public void setFd_proposer_id(String fd_proposer_id) {
		this.fd_proposer_id = fd_proposer_id;
	}
	public String getDoc_dept_id() {
		return doc_dept_id;
	}
	public void setDoc_dept_id(String doc_dept_id) {
		this.doc_dept_id = doc_dept_id;
	}
	public String getFd_resource_id() {
		return fd_resource_id;
	}
	public void setFd_resource_id(String fd_resource_id) {
		this.fd_resource_id = fd_resource_id;
	}
	@Override
	public String toString() {
		return "MeetingresMain [fd_id=" + fd_id + ", doc_subject=" + doc_subject + ", doc_create_time="
				+ doc_create_time + ", fd_time_interval=" + fd_time_interval + ", doc_status=" + doc_status
				+ ", hbm_start_date=" + hbm_start_date + ", hbm_start_time=" + hbm_start_time
				+ ", fd_order_start_date_time=" + fd_order_start_date_time + ", hbm_finish_date=" + hbm_finish_date
				+ ", hbm_finish_time=" + hbm_finish_time + ", fd_order_finish_date_time=" + fd_order_finish_date_time
				+ ", fd_resource_continue=" + fd_resource_continue + ", fd_borrow_reason=" + fd_borrow_reason
				+ ", auth_reader_flag=" + auth_reader_flag + ", doc_publish_time=" + doc_publish_time
				+ ", fd_last_modified_time=" + fd_last_modified_time + ", fd_meeting_id=" + fd_meeting_id
				+ ", doc_creator_id=" + doc_creator_id + ", fd_proposer_id=" + fd_proposer_id + ", doc_dept_id="
				+ doc_dept_id + ", fd_resource_id=" + fd_resource_id + "]";
	}
	
}
