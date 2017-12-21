package com.pulan.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.MessageDao;
import com.pulan.datasource.DatabaseContextHolder;
import com.pulan.datasource.DatabaseType;
import com.pulan.model.Message;
import com.pulan.model.MessageParam;
import com.pulan.service.MessageService;
@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	private MessageDao messageDao;
	@Override
	public Message getMessageByName(String message_name) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.hyn_profile);
		return messageDao.getMessageByName(message_name);
	}
	@Override
	public List<Message> getAllMessageName() {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.hyn_profile);
		return messageDao.getAllMessageName();
	}
	@Override
	public List<String> getAllParamsByMessage(String message_uuid) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.hyn_profile);
		return messageDao.getAllParamsByMessage(message_uuid);
	}
	@Override
	public List<Message> getAllMessageNameByType(String schedule_type) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.hyn_profile);
		return messageDao.getAllMessageNameByType(schedule_type);
	}
	@Override
	public List<MessageParam> getAllParamsObjByMessage(String message_uuid) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.hyn_profile);
		return messageDao.getAllParamsObjByMessage(message_uuid);
	}
	@Override
	public Message getAllMessageByType(String message_type) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.hyn_profile);
		return messageDao.getAllMessageByType(message_type);
	}
}
