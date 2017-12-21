package com.pulan.utils;

import org.apache.log4j.Logger;

import com.pulan.service.IRedisEventHandler;




public class MsgTaskListener {
	private static  Logger logger = Logger.getLogger(MsgTaskListener.class);
	private static RedisClient redisClient = new RedisClient();
	
	public static boolean SetTimedTask(String taskId, int timeout) {
		try {
			redisClient.set(0,taskId, "timedtask");
			redisClient.expire(0,taskId, timeout);
			return true;
		} catch (Exception e) {
			logger.error("set timed task failed: " + e.getMessage());
			return false;
		}
	}
	
	public static boolean ListenTimedTask(IRedisEventHandler eventHandler) {
		try {
			redisClient.subscrib("__keyevent@0__:expired", eventHandler);
			return true;
		} catch (Exception e) {
			logger.error("listen timed task failed: " + e.getMessage());
			return false;
		}
	}

}
