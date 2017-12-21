package com.pulan.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pulan.service.IRedisEventHandler;
import com.pulan.utils.JpushClientUtil;
import com.pulan.utils.MsgTaskListener;

public class SchedualThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			MsgTaskListener.ListenTimedTask(new IRedisEventHandler() {
				@Override
				public void onEvent(String channel, String message) {
					System.out.println("thread");
					// TODO Auto-generated method stub
					JSONObject jo = JSON.parseObject(message);
					String alias = jo.getString("alias");
					String msg_content = jo.getString("msg_content");
					String msg_type = jo.getString("msg_type");
					String msg_name = jo.getString("msg_name");
					int result = JpushClientUtil.sendPush(alias, msg_type, msg_name, msg_content, msg_type);
				}
			});
		}
	}

}
