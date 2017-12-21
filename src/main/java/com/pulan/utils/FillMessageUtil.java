package com.pulan.utils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pulan.model.Message;
import com.pulan.model.MessageParam;


public class FillMessageUtil {
	public static String fileeMessage(LinkedHashMap<String, Object> map,Message message,long begintime){
		String msg_content = "";
		if("meetting".equals(message.getMessage_type())){
		Object[] array = new Object[map.size()];
		String time =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(begintime));
		int i = 0;
    	 for(Object v:map.values()){
    		 //将第一个字段填充为时间
    		 if(i==0){
    			 array[0] = time;
    			 i++;
    			 continue;
    		 }
    	   array[i] = v;
    	   i++;
    	 }
    	 msg_content = MessageFormat.format(message.getMessage_template(),array);
    	 }else if("calendar".equals(message.getMessage_type())){
    		 map.remove("begintime");
    		 Object[] array = new Object[map.size()];
    		 int i = 0;
        	 for(Object v:map.values()){
        	   array[i] = v;
        	   i++;
        	 }
        	 msg_content = MessageFormat.format(message.getMessage_template(),array);
    	 }else if("todo".equals(message.getMessage_type())){
    		 Object[] array = new Object[map.size()];
    		 int i = 0;
        	 for(Object v:map.values()){
        	   array[i] = v;
        	   i++;
        	 }
        	 msg_content = MessageFormat.format(message.getMessage_template(),array);
    	 }else if("business".equals(message.getMessage_type())){
    		 String weather = "";
    		 Object[] array = new Object[map.size()];
    		 int i = 0;
        	 for(Object v:map.values()){
        	   array[i] = v;
        	   i++;
        	 }
        	 //如果出差目的地不为空
        	 if(array[5]!=null&!"".equals(array[5].toString())){
        		 List<MessageParam> params = new ArrayList<MessageParam>();
        		 params.add(new MessageParam("location","",(String)array[5],""));
        		 params.add(new MessageParam("key","","1a224c73d7a040c690494714f615e9c3",""));
        		 JSONObject response = HttpClient.getResponse("https://free-api.heweather.com/s6/weather/forecast", params);
        		 JSONObject heWeather6 = response.getJSONArray("HeWeather6").getJSONObject(0);
        		 JSONArray daily_forecasts = heWeather6.getJSONArray("daily_forecast");
        		 for(int j = 0;j<daily_forecasts.size();j++){
        			 JSONObject df = daily_forecasts.getJSONObject(j);
        			 if(j == daily_forecasts.size()-1){
        				 weather += df.getString("cond_txt_n");
        			 }else{
        				 weather += df.getString("cond_txt_n")+",";
        			 }
        		 }
        		 weather = ",为您查询到"+array[5]+"未来三天天气:"+weather;
        	 }
        	 msg_content = message.getMessage_template() + weather;
    	 }else if("attendance".equals(message.getMessage_type())){
    		 Object[] array = new Object[map.size()];
    		 int i = 0;
        	 for(Object v:map.values()){
        	   array[i] = v;
        	   i++;
        	 }
        	 //工作时长为0 有未打卡记录
        	 if((Double)array[6]==0.0){
        		 msg_content = "您昨天有未打卡记录,请提交未打卡说明";
        	 }else{
        		 //工作时长不为0
        		 //迟到
        		 if((Integer)array[4]==1){
        			 if((Integer)array[5]==0){
        				 msg_content = "您昨天有迟到记录";
        			 }else{
        				 msg_content = "您昨天有早退和迟到记录";
        			 }
        		 }else{
        			 //早退
        			 if((Integer)array[5]==1){
        				 msg_content = "您昨天有早退记录";
        			 }
        		 }
        	
        	 }
    	 }
    	 return msg_content;
	}
}
