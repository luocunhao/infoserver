package com.pulan.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.pulan.model.User;
@Service
public interface UserService {
	public List<User> getAllUser();
	public String getImeiByMainame(String mailname);
}
