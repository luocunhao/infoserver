package com.pulan.task;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pulan.model.Message;
import com.pulan.model.MessageParam;
import com.pulan.model.User;
import com.pulan.service.DefaultService;
import com.pulan.service.MessageService;
import com.pulan.service.UserService;
import com.pulan.utils.FillMessageUtil;
import com.pulan.utils.HttpClient;
import com.pulan.utils.JpushClientUtil;
import com.pulan.utils.MsgTaskListener;
import com.pulan.utils.RedisClient;
import com.pulan.utils.ReflectUtil;

@Component
public class JpushScheduleDayTask {
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private MessageService messageServiceImpl;
	@Autowired
	private DefaultService defaultServiceImpl;
	@Autowired
	private RedisClient redisClient;
	Logger logger = Logger.getLogger(JpushScheduleDayTask.class.getName());

	// 每天0点触发
	// @Scheduled(cron = "0 0 0 * * ?")
	@Scheduled(cron = "0 0 1 * * ?")
	public void excute() {
		logger.info("--------------day push start----------------");
		logger.info("-------------生日提醒任务开始------------------");
		logger.info("-------------出差提醒任务开始------------------");
		List<User> users = userServiceImpl.getAllUser();
		DateFormat birthdf = new SimpleDateFormat("MM-dd");
		// 遍历所有用户
		for (User user : users) {
			String alias = user.getImei();
			// String alias = "861535032038336";
			Message message = messageServiceImpl.getAllMessageByType("birth");
			// 其他按天定时关怀:生日
			List<String> params = messageServiceImpl.getAllParamsByMessage(message.getMessage_uuid());
			// 反射获取属性填充sql;
			String sql = ReflectUtil.getSQL(message.getData_sql(), user, params);
			List<LinkedHashMap<String, Object>> listmap = defaultServiceImpl.getObject(message.getData_source(), sql);
			if (listmap.size() > 0) {
				for (int i = 0; i < listmap.size(); i++) {
					Timestamp ts = null;
					try {
						ts = (Timestamp) listmap.get(i).get("begintime");
					} catch (Exception e) {
						// TODO: handle exception
						continue;
					}
					String birth = birthdf.format(ts);
					if (birth.equals(birthdf.format(new Date()))) {
						// JSONObject jo = new JSONObject();
						// jo.put("alias", alias);
						// jo.put("msg_content",
						// message.getMessage_template());
						// jo.put("msg_type", message.getMessage_type());
						// jo.put("msg_name", message.getMessage_name());
						// String taskId = jo.toJSONString();
						if (alias != null & !"".equals(alias)) {
							int ret = JpushClientUtil.sendPush(alias, message.getMessage_type(),
									message.getMessage_name(), message.getMessage_template(),
									message.getMessage_type());
							logger.info("----------生日提醒推送成功------------");
						} 
						//一下为离线推送缓存程序  生日提醒不缓存
//						else {
//							// 没有注册极光别名的 推送消息缓存一天
//							JSONObject jmessage = new JSONObject();
//							jmessage.put("mailname", user.getMail_name());
//							jmessage.put("notification_title", message.getMessage_type());
//							jmessage.put("msg_title", message.getMessage_name());
//							jmessage.put("msg_content", message.getMessage_template());
//							jmessage.put("extrasparam", message.getMessage_type());
//							try {
//								logger.info("-----------------birth push cache start-------------");
//								String key = user.getMail_name() + ":birth:" + UUID.randomUUID().toString();
//								redisClient.set(3, key, jmessage.toJSONString());
//								redisClient.expire(3, key, 86400);
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.getStackTrace();
//							}
//						}
					}
				}
			}
			//出差提醒
			Message businessmessage = messageServiceImpl.getAllMessageByType("business");
			List<String> businessparams = messageServiceImpl.getAllParamsByMessage(businessmessage.getMessage_uuid());
						// 反射获取属性填充sql;
			String businesssql = ReflectUtil.getSQL(businessmessage.getData_sql(), user, businessparams);
			List<LinkedHashMap<String, Object>> businesslistmap = defaultServiceImpl.getObject(businessmessage.getData_source(), businesssql);
			if(businesslistmap.size()>0){
				if (alias != null & !"".equals(alias)) {
					String msgContent = FillMessageUtil.fileeMessage(businesslistmap.get(0), businessmessage, 0l);
					int ret = JpushClientUtil.sendPush(alias, businessmessage.getMessage_type(),
							businessmessage.getMessage_name(), msgContent,
							businessmessage.getMessage_type());
					logger.info("----------出差提醒推送成功------------");
				} else {
					// 没有注册极光别名的 推送消息缓存一天
					String msgContent = FillMessageUtil.fileeMessage(businesslistmap.get(0), businessmessage, 0l);
					JSONObject jmessage = new JSONObject();
					jmessage.put("mailname", user.getMail_name());
					jmessage.put("notification_title", businessmessage.getMessage_type());
					jmessage.put("msg_title", businessmessage.getMessage_name());
					jmessage.put("msg_content", msgContent);
					jmessage.put("extrasparam", businessmessage.getMessage_type());
					try {
						logger.info("-----------------出差  push cache start-------------");
						String key = user.getMail_name() + ":business:" + UUID.randomUUID().toString();
						redisClient.set(3, key, jmessage.toJSONString());
						redisClient.expire(3, key, 86400);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.getStackTrace();
					}
				}
			}
		}
	}

	@Scheduled(cron = "0 0 8 ? * MON-FRI")
	public void exec() {
		logger.info("-------------饱和度提醒任务开始------------------");
		logger.info("-------------考勤提醒任务开始------------------");
		List<User> users = userServiceImpl.getAllUser();
		// 遍历所有用户
		for (User user : users) {
			String alias = user.getImei();
			Message satmessage = messageServiceImpl.getAllMessageByType("saturation");
			List<String> satparams = messageServiceImpl.getAllParamsByMessage(satmessage.getMessage_uuid());
			// 反射获取属性填充sql;
			String satsql = ReflectUtil.getSQL(satmessage.getData_sql(), user, satparams);
			List<LinkedHashMap<String, Object>> satlistmap = defaultServiceImpl.getObject(satmessage.getData_source(),
					satsql);
			if (satlistmap.size() > 0) {
				if (alias != null & !"".equals(alias)) {
					int ret = JpushClientUtil.sendPush(alias, satmessage.getMessage_type(),
							satmessage.getMessage_name(), satmessage.getMessage_template(),
							satmessage.getMessage_type());
					logger.info("----------饱和度提醒推送记录数------------:" + ret);
				} else {
					// 没有注册极光别名的 推送消息缓存一天
					JSONObject jmessage = new JSONObject();
					jmessage.put("mailname", user.getMail_name());
					jmessage.put("notification_title", satmessage.getMessage_type());
					jmessage.put("msg_title", satmessage.getMessage_name());
					jmessage.put("msg_content", satmessage.getMessage_template());
					jmessage.put("extrasparam", satmessage.getMessage_type());
					try {
						logger.info("-----------------饱和度  push cache start-------------");
						String key = user.getMail_name() + ":saturation:" + UUID.randomUUID().toString();
						redisClient.set(3, key, jmessage.toJSONString());
						redisClient.expire(3, key, 86400);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.getStackTrace();
					}
				}
			}
			Message attenmessage = messageServiceImpl.getAllMessageByType("attendance");
			List<String> attenparams = messageServiceImpl.getAllParamsByMessage(attenmessage.getMessage_uuid());
			// 反射获取属性填充sql;
			String attensql = ReflectUtil.getSQL(attenmessage.getData_sql(), user, attenparams);
			List<LinkedHashMap<String, Object>> attenlistmap = defaultServiceImpl.getObject(attenmessage.getData_source(),
								attensql);
			if(attenlistmap.size() > 0){
				if (alias != null & !"".equals(alias)) {
					String msg_content = FillMessageUtil.fileeMessage(attenlistmap.get(0), attenmessage, 0l);
					JpushClientUtil.sendPush(alias, attenmessage.getMessage_type(),
							attenmessage.getMessage_name(), attenmessage.getMessage_template()+","+msg_content,
							attenmessage.getMessage_type());
					logger.info("----------考勤提醒推送成功------------");
				} else {
					// 没有注册极光别名的 推送消息缓存一天
					String msg_content = FillMessageUtil.fileeMessage(attenlistmap.get(0), attenmessage, 0l);
					JSONObject jmessage = new JSONObject();
					jmessage.put("mailname", user.getMail_name());
					jmessage.put("notification_title", attenmessage.getMessage_type());
					jmessage.put("msg_title", attenmessage.getMessage_name());
					jmessage.put("msg_content", attenmessage.getMessage_template()+","+msg_content);
					jmessage.put("extrasparam", attenmessage.getMessage_type());
					try {
						String key = user.getMail_name() + ":attendance:" + UUID.randomUUID().toString();
						redisClient.set(3, key, jmessage.toJSONString());
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
