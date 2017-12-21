package com.pulan.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pulan.model.oa.MeetingMainAreader;
import com.pulan.model.oa.NotifyTodoTarget;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;

public class Test {

	public static void main(String[] args) {
	JSONArray ja = new JSONArray();
	JSONArray ja1 = new JSONArray();
	JSONObject jo = new JSONObject();
	jo.put("id", "1");
	JSONObject jo2 = new JSONObject();
	jo2.put("id", "2");
	JSONObject jo3 = new JSONObject();
	jo3.put("id", "3");
	ja.add(jo);
	ja.add(jo2);
	System.out.println(ja);
	ja1.add(jo);
	ja1.add(jo3);
	System.out.println(ja1);
	ja.addAll(ja1);
	Set<String> set = new HashSet<String>();
	for(int i=0;i<ja.size();i++){
		String id = ja.getString(i);
		if(!set.contains(id)){
			System.out.println(ja);
//			meetingOAServiceImpl.insertMeetingMainAreader(mmr);
//			meetingOAServiceImpl.insertNotifyTodoTarget(ntt);
			set.add(id);
		}
	}
	}

}
