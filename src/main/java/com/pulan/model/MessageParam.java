package com.pulan.model;

public class MessageParam {
	private	String message_param_uuid;
	private String message_param_name;
	private String message_param_index;
	private String message_param_value;
	private String message_uuid;
	public MessageParam(){}
	public MessageParam(String message_param_name, String message_param_index,
			String message_param_value, String message_uuid) {
		this.message_param_name = message_param_name;
		this.message_param_index = message_param_index;
		this.message_param_value = message_param_value;
		this.message_uuid = message_uuid;
	}
	public MessageParam(String message_param_uuid, String message_param_name, String message_param_index,
			String message_param_value, String message_uuid) {
		this.message_param_uuid = message_param_uuid;
		this.message_param_name = message_param_name;
		this.message_param_index = message_param_index;
		this.message_param_value = message_param_value;
		this.message_uuid = message_uuid;
	}
	public String getMessage_param_uuid() {
		return message_param_uuid;
	}
	public void setMessage_param_uuid(String message_param_uuid) {
		this.message_param_uuid = message_param_uuid;
	}
	public String getMessage_param_name() {
		return message_param_name;
	}
	public void setMessage_param_name(String message_param_name) {
		this.message_param_name = message_param_name;
	}
	public String getMessage_param_index() {
		return message_param_index;
	}
	public void setMessage_param_index(String message_param_index) {
		this.message_param_index = message_param_index;
	}
	public String getMessage_param_value() {
		return message_param_value;
	}
	public void setMessage_param_value(String message_param_value) {
		this.message_param_value = message_param_value;
	}
	public String getMessage_uuid() {
		return message_uuid;
	}
	public void setMessage_uuid(String message_uuid) {
		this.message_uuid = message_uuid;
	}
	@Override
	public String toString() {
		return "MessageParam [message_param_uuid=" + message_param_uuid + ", message_param_name=" + message_param_name
				+ ", message_param_index=" + message_param_index + ", message_param_value=" + message_param_value
				+ ", message_uuid=" + message_uuid + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message_uuid == null) ? 0 : message_uuid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageParam other = (MessageParam) obj;
		if (message_uuid == null) {
			if (other.message_uuid != null)
				return false;
		} else if (!message_uuid.equals(other.message_uuid))
			return false;
		return true;
	}
	
}
