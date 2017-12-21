package com.pulan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pulan.model.Message;
import com.pulan.model.MessageParam;

public class HttpClient {
	public static JSONObject getResponse(String url,List<MessageParam> params) {
		String param = "";
		if(params.size()>0){
			for(int i = 0;i<params.size();i++){
				if(i == params.size()-1){
					param += params.get(i).getMessage_param_name()+"="+params.get(i).getMessage_param_value();
				}else{
					param += params.get(i).getMessage_param_name()+"="+params.get(i).getMessage_param_value() + "&";
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		InputStream is = null;
		BufferedReader br = null;
		PrintWriter out = null;
		try {
			// 接口地址
			URL uri = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
			connection.setRequestMethod("POST");
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(10000);
			connection.setRequestProperty("accept", "*/*");
			// 发送参数
			connection.setDoOutput(true);
			out = new PrintWriter(connection.getOutputStream());
			out.print(param);
			out.flush();
			// 接收结果
			is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String line;
			// 缓冲逐行读取
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception ignored) {
		} finally {
			// 输出结果
			out.println(sb.toString());
			// 关闭流
			try {
				if (is != null) {
					is.close();
				}
				if (br != null) {
					br.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e2) {
			}
		}
		JSONObject jsonObject = JSON.parseObject(sb.toString());
		return jsonObject;
	}
}

