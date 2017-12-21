package com.pulan.utils;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;

import com.pulan.model.User;

public class ReflectUtil {
	public static String getSQL(String sql,User user,List<String> params){
		if(params.size()>0){
		Class userCla = user.getClass();
		String[] args = new String[params.size()];
		for(int i = 0;i<params.size();i++){
			try {
				Field f = userCla.getDeclaredField(params.get(i));
				f.setAccessible(true);
				args[i] = "'"+(String)f.get(user)+"'";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return MessageFormat.format(sql,args);
		}else{
			return sql;
		}
	}
}
