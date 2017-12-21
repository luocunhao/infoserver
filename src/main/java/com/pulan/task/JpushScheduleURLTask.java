package com.pulan.task;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pulan.model.Message;
import com.pulan.model.MessageParam;
import com.pulan.model.User;
import com.pulan.service.MessageService;
import com.pulan.service.UserService;
import com.pulan.utils.HttpClient;
import com.pulan.utils.JpushClientUtil;
import com.pulan.utils.MsgTaskListener;
import com.pulan.utils.RedisClient;

@Component
public class JpushScheduleURLTask {
	Logger logger = Logger.getLogger(JpushScheduleURLTask.class.getName());
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private MessageService messageServiceImpl;
	@Autowired
	private RedisClient redisClient;
	// 每天0点触发
	@Scheduled(cron = "0 30 7 * * ?")
	public void excute() {
		List<User> users = userServiceImpl.getAllUser();
		// 遍历所有用户
		List<Message> messages = messageServiceImpl.getAllMessageNameByType("day");
		for (Message message : messages) {
			// 遍历所有消息
			logger.info("-----------------weather push cache start-------------");
			if ("第三方".equals(message.getData_source())) {
				JSONObject jo = new JSONObject();
				String msg_content = "";
				List<MessageParam> params = messageServiceImpl.getAllParamsObjByMessage(message.getMessage_uuid());
				String url = message.getData_sql();
				JSONObject response = HttpClient.getResponse(url, params);
				JSONObject heWeather6 = response.getJSONArray("HeWeather6").getJSONObject(0);
				JSONArray lifestyles = heWeather6.getJSONArray("lifestyle");
				for (int i = 0; i < 3; i++) {
					msg_content += lifestyles.getJSONObject(i).getString("txt");
				}
				for (User user : users) {
					String alias = user.getImei();
					// String alias = "861535032038336";
					// jo.put("alias", alias);
					// jo.put("msg_content", msg_content);
					// jo.put("msg_type", message.getMessage_type());
					// jo.put("msg_name", message.getMessage_name());
					// String taskId = jo.toJSONString();
					if (alias != null & !"".equals(alias)) {
						JpushClientUtil.sendPush(alias, message.getMessage_type(), message.getMessage_name(),
								msg_content, message.getMessage_type());
					}else{
						JSONObject jmessage = new JSONObject();
						jmessage.put("mailname", user.getMail_name());
						jmessage.put("notification_title", message.getMessage_type());
						jmessage.put("msg_title", message.getMessage_name());
						jmessage.put("msg_content", msg_content);
						jmessage.put("extrasparam", message.getMessage_type());
						try {
							String key = user.getMail_name() + ":weather:" + UUID.randomUUID().toString();
							redisClient.set(3,
									key,
									jmessage.toJSONString());
							redisClient.expire(3, key, 86400);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.getStackTrace();
						}
					}
				}
			}
		}
	}
}
