package com.pulan.service;

public interface IRedisEventHandler {
	public void onEvent(String channel, String message);
}
