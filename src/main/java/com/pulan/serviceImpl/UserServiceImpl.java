package com.pulan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.UserDao;
import com.pulan.datasource.DatabaseContextHolder;
import com.pulan.datasource.DatabaseType;
import com.pulan.model.User;
@Service
public class UserServiceImpl implements com.pulan.service.UserService{
    @Autowired
	private UserDao userDao;
    public List<User> getAllUser(){
    	DatabaseContextHolder.setDatabaseType(DatabaseType.hyn_profile);
    	return userDao.getAllUser();
    }
	@Override
	public String getImeiByMainame(String mailname) {
		// TODO Auto-generated method stub
		DatabaseContextHolder.setDatabaseType(DatabaseType.hyn_profile);
		return userDao.getImeiByMainame(mailname);
	}
    
}
