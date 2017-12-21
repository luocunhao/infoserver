package com.pulan.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pulan.model.Message;
import com.pulan.model.User;
import com.pulan.service.MessageService;
import com.pulan.service.UserService;
import com.pulan.utils.JpushClientUtil;
/*
 * 
 * 六点下班推送
 * 
 * */
@Component
public class JpushScheduleOffDuty {
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private MessageService messageServiceImpl;
	// 每天下班时间
//		@Scheduled(cron = "0 0 18 * * ?")
		@Scheduled(cron = "0 0 18 ? * MON-FRI")
		public void excute() {
			List<User> users = userServiceImpl.getAllUser();
			Message message = messageServiceImpl.getAllMessageByType("travel");
			String msg_content = message.getMessage_template();
			String msg_type = message.getMessage_type();
			String msg_name = message.getMessage_name();
			for(User user:users){
			    String alias = user.getImei();
//				String alias = "861535032038336";
			    if(alias!=null&!"".equals(alias)){
				JpushClientUtil.sendPush(alias, msg_type, msg_name, msg_content, msg_type);
			    }
			}
		}
}
