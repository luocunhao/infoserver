package com.pulan.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DefaultService {
	public List<LinkedHashMap<String,Object>> getObject(String data_source,String sql);
}
