package com.pulan.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.pulan.model.User;

public interface UserDao {
	//获取所有用户
	public List<User> getAllUser();

	public String getImeiByMainame(@Param("mailname") String mailname);
}
