package com.alibaba.ocr.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.entity.Ocrentity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * TODO 描述
 * </p>
 *
 * @author hyong
 * @since 2021/11/30
 */
public class ZstestMain {
	public static void main(String[] args) throws Exception {
		String host = "https://ocrapi-advanced.taobao.com";//https://ocrdiy.market.alicloudapi.com
		String path = "/ocrservice/advanced";//  /api/predict/ocr_sdt
		String method = "POST";
		String appcode = "cabee09b4f904201a4f049604a6cc930";
		Map<String, String> headers = new HashMap<String, String>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		//根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");
		Map<String, String> querys = new HashMap<String, String>();
		String bodys = "{\"img\":\""+ DailyTestMain.changeToBase64("/Users/hyong/Desktop/471637822374_.pic.jpg")+"\",\"prob\":false,\"charInfo\":false,\"rotate\":false,\"table\":false,\"row\":true,\"paragraph\":true}";
		//String bodys = "{\"image\":\""+changeToBase64("/Users/hyong/Desktop/test.jpg")+"\",\"configure\": \"{\\\"template_id\\\":\\\"9e9b4617-24f9-47d5-b1c8-8a91075b35011634191935\\\"}\"}";     模版ocr
		try {
			/**
			 * 重要提示如下:
			 * HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			System.out.println(response.toString());
			HttpEntity httpEntity = response.getEntity();
			//获取response的body
			String body = EntityUtils.toString(httpEntity);
			System.out.println(body);
			JSONObject jsonObject = JSON.parseObject(body);
			String content = jsonObject.getString("content");
			System.out.println(content.replace(" ",""));
			JSONArray jsonArray = jsonObject.getJSONArray("prism_wordsInfo");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
