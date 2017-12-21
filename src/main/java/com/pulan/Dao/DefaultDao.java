package com.pulan.Dao;

import java.util.LinkedHashMap;
import java.util.List;

public interface DefaultDao {
	public List<LinkedHashMap<String,Object>> getObject(String sql);
}
