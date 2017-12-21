package com.pulan.model;

public class Message {
	private String message_uuid;
	private String message_name;
	private String message_type;
	private String data_source;
	private String data_sql;
	private String message_template;
	private String schedule_type;
	private String expire_time;
	public Message(){
		
	}
	public Message(String message_name, String message_type, String data_source, String data_sql,
			String message_template,String schedule_type,String expire_time) {
		this.expire_time = expire_time;
		this.message_name = message_name;
		this.message_type = message_type;
		this.data_source = data_source;
		this.data_sql = data_sql;
		this.message_template = message_template;
		this.schedule_type = schedule_type;
	}
	public Message(String message_uuid, String message_name, String message_type, String data_source, String data_sql,
			String message_template,String schedule_type,String expire_time) {
		this.expire_time = expire_time;
		this.message_uuid = message_uuid;
		this.message_name = message_name;
		this.message_type = message_type;
		this.data_source = data_source;
		this.data_sql = data_sql;
		this.message_template = message_template;
		this.schedule_type = schedule_type;
	}
	public String getMessage_uuid() {
		return message_uuid;
	}
	public void setMessage_uuid(String message_uuid) {
		this.message_uuid = message_uuid;
	}
	public String getMessage_name() {
		return message_name;
	}
	public void setMessage_name(String message_name) {
		this.message_name = message_name;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getData_source() {
		return data_source;
	}
	public void setData_source(String data_source) {
		this.data_source = data_source;
	}
	public String getData_sql() {
		return data_sql;
	}
	public void setData_sql(String data_sql) {
		this.data_sql = data_sql;
	}
	public String getMessage_template() {
		return message_template;
	}
	public void setMessage_template(String message_template) {
		this.message_template = message_template;
	}
	
	public String getSchedule_type() {
		return schedule_type;
	}
	public void setSchedule_type(String schedule_type) {
		this.schedule_type = schedule_type;
	}
	public String getExpire_time() {
		return expire_time;
	}
	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}
	@Override
	public String toString() {
		return "Message [message_uuid=" + message_uuid + ", message_name=" + message_name + ", message_type="
				+ message_type + ", data_source=" + data_source + ", data_sql=" + data_sql + ", message_template="
				+ message_template + ", schedule_type=" + schedule_type + ", expire_time=" + expire_time + "]";
	}
	
	
}
