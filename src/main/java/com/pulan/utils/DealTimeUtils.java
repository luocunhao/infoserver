package com.pulan.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class DealTimeUtils {
	public static Map<String,String> getAllFormatTimeForMeetingMain(String starttime,String endtime){
		//"2017-10-22 10:00:00" 
		Map<String,String> map = new HashMap<String,String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String hbm_hold_date = df.parse(starttime).getTime()+"";
			String fd_hold_date_time = starttime.substring(0,10);
			String hbm_hold_time = df1.parse("1970-01-01 "+starttime.substring(11,19)).getTime()+28800000+"";
			String hbm_finish_date = df.parse(endtime).getTime()+"";
			String hbm_finish_time = df1.parse("1970-01-01 "+endtime.substring(11,19)).getTime()+28800000+"";
			map.put("hbm_hold_date", hbm_hold_date);
			map.put("fd_hold_date_time", fd_hold_date_time);
			map.put("hbm_hold_time", hbm_hold_time);
			map.put("hbm_finish_date", hbm_finish_date);
			map.put("hbm_finish_time", hbm_finish_time);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	public static Map<String,String> getAllFormatTimeForMeetingResMain(String starttime,String endtime){
		Map<String,String> map = new HashMap<String,String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String hbm_start_date = df.parse(starttime).getTime()+"";
			long hbm_start_time = df1.parse("1970-01-01 "+starttime.substring(11,19)).getTime()+28800000;
			String fd_order_start_date_time = starttime;
			String hbm_finish_date = df.parse(endtime).getTime()+"";
			long hbm_finish_time = df1.parse("1970-01-01 "+endtime.substring(11,19)).getTime()+28800000;
			String fd_order_finish_date_time = endtime;
			long fd_time_interval = hbm_finish_time - hbm_start_time;
			map.put("hbm_start_date", hbm_start_date);
			map.put("hbm_start_time", hbm_start_time+"");
			map.put("fd_order_start_date_time", fd_order_start_date_time);
			map.put("hbm_finish_date", hbm_finish_date);
			map.put("hbm_finish_time", hbm_finish_time+"");
			map.put("fd_order_finish_date_time", fd_order_finish_date_time);
			map.put("fd_time_interval", fd_time_interval+"");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
