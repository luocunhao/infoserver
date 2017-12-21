package com.pulan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.pulan.model.Message;
import com.pulan.model.MessageParam;
@Service
public interface MessageService {
	public Message getMessageByName(String message_name);
	//获取所有的提醒类别
	public List<Message> getAllMessageName();
	//根据messageuuid获取所有参数
	public List<String> getAllParamsByMessage(String message_uuid);
	public List<Message> getAllMessageNameByType(String schedule_type);
	public List<MessageParam> getAllParamsObjByMessage(String message_uuid);
	public Message getAllMessageByType(String message_type);
}
