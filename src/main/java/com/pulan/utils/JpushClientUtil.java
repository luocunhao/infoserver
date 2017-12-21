package com.pulan.utils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.schedule.ScheduleListResult;
import cn.jpush.api.schedule.ScheduleResult;

public class JpushClientUtil {
	private static Logger logger = Logger.getLogger(JpushClientUtil.class);
    private final static String appKey = "94e6d37b8eea2da522b6467c";
 
    private final static String masterSecret = "6189b3100c8197924c341dcf";
 
    private static JPushClient jPushClient = new JPushClient(masterSecret,appKey);
    /**
     * 推送给设备标识参数的用户
     * @param registrationId 设备标识
     * @param notification_title 通知内容标题
     * @param msg_title 消息内容标题
     * @param msg_content 消息内容
     * @param extrasparam 扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendPush(String alais,String notification_title, String msg_title, String msg_content, String extrasparam){
    	PushPayload pushPayload = PushPayload.newBuilder()
                  //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                  .setPlatform(Platform.android_ios())
                  //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                  .setAudience(Audience.alias(alais))
                  //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                  .setNotification(Notification.newBuilder()
                		  .setAlert(msg_content)
                          //指定当前推送的android通知
                          .addPlatformNotification(AndroidNotification.newBuilder()
                                  .setTitle(msg_title)
                                  //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                  .addExtra("extrakey",extrasparam)
                                  .addExtra("open_id", alais)
                                  .build())
                          .addPlatformNotification(IosNotification.newBuilder()
                                  .addExtra("extrakey",extrasparam)
                                  .addExtra("open_id", alais)
                                  .setContentAvailable(true)
                                  .build())
                        		  
                          .build()
                          
                  )
                  //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                  // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                  // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
//                  .setMessage(Message.newBuilder()
//                          .setMsgContent(msg_content)
//                          .setTitle(msg_title)
//                          .addExtra("message extras key",extrasparam)
//                          .build())
//   
                  .setOptions(Options.newBuilder()
                          //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                          .setApnsProduction(false)
                          //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                          .setSendno(1)
                          //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                          .setTimeToLive(86400)
                          .build())
                  .build();
    	int result = 0;
    	try {
    		logger.info("pushPayload"+pushPayload);
			PushResult pushResult=jPushClient.sendPush(pushPayload);
			logger.info("pushResult"+pushResult);
			  if(pushResult.getResponseCode()==200){
	                result=1;
	            }
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			logger.error(e.getErrorMessage());
		}
    	return result;
    }
     public static int schedualPush(LinkedHashMap<String, Object> map,String alais,com.pulan.model.Message message,long begin_time,String extrasparam){
     	 String notification_title = message.getMessage_type();
     	 String msg_title = message.getMessage_name();
     	 String message_template = message.getMessage_template();
     	 String time =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(begin_time));
     	 //定时任务名  确保schdule_name唯一
     	 String schdule_name = alais+begin_time;
     	 Object[] array = new Object[map.size()];
         int i = 0;
     	 for(Object v:map.values()){
     		 //将时间放在第一个字段
     		 if(i==0){
     			 array[0] = time;
     			 i++;
     			 continue;
     		 }
     	   array[i] = v;
     	   i++;
     	 }
     	 //如果定时任务已经存在 直接return 不执行下面代码
     	 if(isSchedulePush(schdule_name)){
     		 return 0;
     	 }
     	String msg_content = MessageFormat.format(message_template,array);
    	 PushPayload pushPayload = PushPayload.newBuilder()
                   //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                   .setPlatform(Platform.all())
                   //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                   .setAudience(Audience.alias(alais))
                   //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                   .setNotification(Notification.newBuilder()
                           //指定当前推送的android通知
                           .addPlatformNotification(AndroidNotification.newBuilder()
                                   .setAlert(notification_title)
                                   .setTitle(msg_title)
                                   //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                   .addExtra("androidNotification extras key",extrasparam)
                                   .build())
                           .build()
                   )
                   //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                   // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                   // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                   .setMessage(Message.newBuilder()
                           .setMsgContent(msg_content)
                           .setTitle(msg_title)
                           .addExtra("message extras key",extrasparam)
                           .build())
    
                   .setOptions(Options.newBuilder()
                           //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                           .setApnsProduction(false)
                           //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                           .setSendno(1)
                           //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                           .setTimeToLive(86400)
                           .build())
                   .build();
     	int result = 0;
     	try {
     		logger.info("pushPayload"+pushPayload);
     		//参数  1:极光定时任务名(自定义) 2:推送时间  3:推送对象
     		ScheduleResult pushResult =  jPushClient.createSingleSchedule(schdule_name, time, pushPayload);
 	//		PushResult pushResult=jPushClient.sendPush(pushPayload);
 			logger.info("pushResult"+pushResult);
 			  if(pushResult.getResponseCode()==200){
 	                result=1;
 	            }
 		} catch (APIConnectionException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (APIRequestException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	return result;
     } 
     public static int schedualPushtest(String time){
    	 PushPayload pushPayload = PushPayload.newBuilder()
                   //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                   .setPlatform(Platform.all())
                   //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                   .setAudience(Audience.alias("861535032038336"))
                   //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                   .setNotification(Notification.newBuilder()
                           //指定当前推送的android通知
                           .addPlatformNotification(AndroidNotification.newBuilder()
                                   .setAlert("notification_title")
                                   .setTitle("msg_title")
                                   //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                   .addExtra("androidNotification extras key","extrasparam")
                                   .build())
                           .build()
                   )
                   //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                   // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                   // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                   .setMessage(Message.newBuilder()
                           .setMsgContent("msg_content")
                           .setTitle("msg_title")
                           .addExtra("message extras key","extrasparam")
                           .build())
    
                   .setOptions(Options.newBuilder()
                           //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                           .setApnsProduction(false)
                           //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                           .setSendno(1)
                           //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                           .setTimeToLive(86400)
                           .build())
                   .build();
     	int result = 0;
     	try {
     		logger.info("pushPayload"+pushPayload);
     		//参数  1:极光定时任务名(自定义) 2:推送时间  3:推送对象
     		ScheduleResult pushResult =  jPushClient.createSingleSchedule("notification_title", time, pushPayload);
 	//		PushResult pushResult=jPushClient.sendPush(pushPayload);
 			logger.info("pushResult"+pushResult);
 			  if(pushResult.getResponseCode()==200){
 	                result=1;
 	            }
 		} catch (APIConnectionException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (APIRequestException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	return result;
     }
     //判断定时任务是否已经存在
     public static boolean isSchedulePush(String schedulename){
    	 ScheduleListResult ret = null;
		try {
			ret = jPushClient.getScheduleList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 List<ScheduleResult> list = ret.getSchedules();
    	 Set<String> namesets = new HashSet<String>();
    	 for(ScheduleResult sr:list){
    		 namesets.add(sr.getName());
    	 }
    	 if(namesets.contains(schedulename)){
    		 return true;
    	 }else{
    		 return false;
    	 }
     }
}
