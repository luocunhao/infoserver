package com.pulan.model;

import java.math.BigDecimal;

public class Meeting {
	private String id;
	private String meeting_subject;
	private BigDecimal meeting_begintime;
	private String meeting_address;
	
	public Meeting(String id, String meeting_subject, BigDecimal meeting_begintime, String meeting_address) {
		this.id = id;
		this.meeting_subject = meeting_subject;
		this.meeting_begintime = meeting_begintime;
		this.meeting_address = meeting_address;
	}
	public Meeting(String meeting_subject, BigDecimal meeting_begintime, String meeting_address) {
		this.meeting_subject = meeting_subject;
		this.meeting_begintime = meeting_begintime;
		this.meeting_address = meeting_address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMeeting_subject() {
		return meeting_subject;
	}
	public void setMeeting_subject(String meeting_subject) {
		this.meeting_subject = meeting_subject;
	}
	public BigDecimal getMeeting_begintime() {
		return meeting_begintime;
	}
	public void setMeeting_begintime(BigDecimal meeting_begintime) {
		this.meeting_begintime = meeting_begintime;
	}
	public String getMeeting_address() {
		return meeting_address;
	}
	public void setMeeting_address(String meeting_address) {
		this.meeting_address = meeting_address;
	}
	
}
