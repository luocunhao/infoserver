package com.pulan.datasource;

public class DatabaseContextHolder {
	 private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<DatabaseType>();
	      
	     public static void setDatabaseType(DatabaseType type){
	         contextHolder.set(type);
	     }
	     
	     public static DatabaseType getDatabaseType(){
	         return contextHolder.get();
	     }
}
