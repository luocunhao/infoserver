package com.pulan.controller;

import java.util.Date;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pulan.service.UserService;
import com.pulan.utils.JpushClientUtil;
import com.pulan.utils.MsgTaskListener;
import com.pulan.utils.RedisClient;

@Controller
@RequestMapping(value = "/info")
public class OfflinePushController {
	@Autowired
	private RedisClient redisClient;
	@Autowired
	private UserService userServiceImpl;
	Logger logger = Logger.getLogger(OfflinePushController.class.getName());
	@RequestMapping("/offlinePush")
	public void OfflinePush(@Param("mailname") String mailname) {
		String alias = userServiceImpl.getImeiByMainame(mailname);
		logger.info("===============离线消息推送==================");
		try {
			Set<String> keys = redisClient.muhuKey(3, mailname + ":*");
			if (keys!=null&&!keys.isEmpty()) {
				logger.info("别名:---------"+alias);
				for (String key : keys) {
					if(key.contains(":meetingbegin:")){
						long now = new Date().getTime();
						JSONObject jo = JSON.parseObject(redisClient.get(3, key));
						long meetingbegintime = jo.getLongValue("meetingbegintime");
						String expire_time = jo.getString("expire_time");
					    jo.put("alias", alias);
						MsgTaskListener.SetTimedTask(jo.toJSONString(),
								((int) (meetingbegintime - now) / 1000) - Integer.parseInt(expire_time));
						redisClient.del(3, key);
					}else{
						JSONObject jo = JSON.parseObject(redisClient.get(3, key));
						String msg_title = jo.getString("msg_title");
						String msg_content = jo.getString("msg_content");
						String notification_title = jo.getString("notification_title");
						String extrasparam = jo.getString("extrasparam");
						JpushClientUtil.sendPush(alias, notification_title, msg_title, msg_content, extrasparam);
						redisClient.del(3, key);
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
