package com.pulan.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pulan.model.Message;
import com.pulan.model.MessageParam;

public interface MessageDao {
	public Message getMessageByName(@Param("message_name") String message_name);
	public List<Message> getAllMessageName();
	public List<String> getAllParamsByMessage(@Param("message_uuid") String message_uuid);
	public List<Message> getAllMessageNameByType(@Param("schedule_type") String day);
	public List<MessageParam> getAllParamsObjByMessage(@Param("message_uuid")String message_uuid);
	public Message getAllMessageByType(String message_type);
}
