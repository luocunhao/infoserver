package com.pulan.serviceImpl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.InfoServer;
import com.pulan.Dao.DefaultDao;
import com.pulan.datasource.DatabaseContextHolder;
import com.pulan.datasource.DatabaseType;
import com.pulan.service.DefaultService;
@Service
public class DefaultServiceImpl implements DefaultService{
	@Autowired
	private DefaultDao defaultDao;
	@Override
	public List<LinkedHashMap<String, Object>> getObject(String data_souce,String sql) {
		DatabaseContextHolder.setDatabaseType(InfoServer.dataSourceMap.get(data_souce));
		// TODO Auto-generated method stub
		return defaultDao.getObject(sql);
	}

}
