    package com.pulan.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.pulan.model.Message;
import com.pulan.model.User;
import com.pulan.service.DefaultService;
import com.pulan.service.MessageService;
import com.pulan.service.UserService;
import com.pulan.utils.FillMessageUtil;
import com.pulan.utils.JpushClientUtil;
import com.pulan.utils.MsgTaskListener;
import com.pulan.utils.RedisClient;
import com.pulan.utils.ReflectUtil;

@Component
public class JpushScheduleHourTask {
	// @Autowired
	// private MeetingService meetingServiceImpl;
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private MessageService messageServiceImpl;
	@Autowired
	private DefaultService defaultServiceImpl;
	@Autowired
	private RedisClient redisClient;

	// 每隔1小时
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void excute() {
		Logger logger = Logger.getLogger(JpushScheduleHourTask.class.getName());
		logger.info("--------------hour:calendar/meetingbegin push start----------------");
		List<User> users = userServiceImpl.getAllUser();
		long now = new Date().getTime();
		// 遍历所有用户
		for (User user : users) {
			String alias = user.getImei();
			List<Message> messages = messageServiceImpl.getAllMessageNameByType("hour");
			// 遍历所有消息
			for (Message message : messages) {
				List<String> params = messageServiceImpl.getAllParamsByMessage(message.getMessage_uuid());
				// 反射获取属性填充sql;
				String sql = ReflectUtil.getSQL(message.getData_sql(), user, params);
				List<LinkedHashMap<String, Object>> listmap = defaultServiceImpl.getObject(message.getData_source(),
						sql);
				// String alias = "861535032038336";
				if (listmap.size() > 0) {
					// 遍历listmap
					for (int i = 0; i < listmap.size(); i++) {
						if ("calendar".equals(message.getMessage_type())) {
							String msg_content = FillMessageUtil.fileeMessage(listmap.get(i), message, 0l);
							if (alias != null & !"".equals(alias)) {
								int ret = JpushClientUtil.sendPush(alias, message.getMessage_type(),
										message.getMessage_name(), msg_content, message.getMessage_type());
							} else {
								// 没有注册极光别名的 推送消息缓存一天
								JSONObject jmessage = new JSONObject();
								jmessage.put("mailname", user.getMail_name());
								jmessage.put("notification_title", message.getMessage_type());
								jmessage.put("msg_title", message.getMessage_name());
								jmessage.put("msg_content", msg_content);
								jmessage.put("extrasparam", message.getMessage_type());
								try {
									redisClient.set(3,
											user.getMail_name() + ":" + "calendar" + ":" + UUID.randomUUID().toString(),
											jmessage.toJSONString());
									redisClient.expire(3, jmessage.toJSONString(), 86400);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.getStackTrace();
								}

							}
						} else {
							logger.info("--------------meeting push start----------------");
							BigDecimal decimaltime = (BigDecimal) listmap.get(i).get("begintime");
							long time = decimaltime.longValue();
							String msg_content = FillMessageUtil.fileeMessage(listmap.get(i), message, time);
							JSONObject jo = new JSONObject();
							jo.put("alias", alias);
							jo.put("msg_content", msg_content);
							jo.put("msg_type", message.getMessage_type());
							jo.put("msg_name", message.getMessage_name());
							String taskId = jo.toJSONString();
							logger.info("--------------meeting push taskid----------------:" + taskId);
							if (alias != null & !"".equals(alias)) {
								MsgTaskListener.SetTimedTask(taskId,
										((int) (time - now) / 1000) - Integer.parseInt(message.getExpire_time()));
							} else {
								jo.put("mailname", user.getMail_name());
								jo.put("expire_time", message.getExpire_time());
								try {
									logger.info("-----------------meeting begin cache start-------------");
									String key = user.getMail_name()+":meetingbegin:"+UUID.randomUUID().toString();
									redisClient.set(3,key,jo.toJSONString());
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
	}
}
