package com.pulan;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pulan.datasource.DatabaseType;
import com.pulan.task.SchedualThread;
//@EnableDiscoveryClient
@Configuration
@ComponentScan
@EnableScheduling
@EnableTransactionManagement //开启事务
@EnableAutoConfiguration
public class InfoServer {
	public static Map<String,DatabaseType> dataSourceMap = new HashMap<String,DatabaseType>(); 
	private static Logger logger = Logger.getLogger(InfoServer.class.getName());
	public static void main(String[] args) {
		dataSourceMap.put("mysql",DatabaseType.hyn_profile);
		dataSourceMap.put("sqlserver",DatabaseType.EKP);
		SpringApplication.run(InfoServer.class, args);
		Thread schedualThread = new Thread(new SchedualThread());
		schedualThread.start();
	}
}
