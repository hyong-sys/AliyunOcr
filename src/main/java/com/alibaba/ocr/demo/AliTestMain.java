package com.alibaba.ocr.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.api.gateway.demo.util.HttpUtils;
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
 * @since 2021/11/25
 */
public class AliTestMain {
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
		String bodys = "{\"img\":\""+ DailyTestMain.changeToBase64("C:\\Users\\A\\Desktop\\微信图片_20211125234342.png")+"\",\"prob\":false,\"charInfo\":false,\"rotate\":false,\"table\":false}";
		try {
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			System.out.println(response.toString());
			HttpEntity httpEntity = response.getEntity();
			//获取response的body
			String body = EntityUtils.toString(httpEntity);
			System.out.println(body);
			JSONObject jsonObject = JSON.parseObject(body);
			JSONArray jsonArray = jsonObject.getJSONArray("prism_wordsInfo");
			List<String> list = new ArrayList<>();
			for (Object obj : jsonArray) {
				JSONObject jsonObject1 = JSONObject.parseObject(obj.toString());////////////
				list.add(jsonObject1.getString("word"));
			}

			for (int i = 0; i < list.size(); i++) {
				String startflag = list.get(i);
				if (startflag.equals("交易记录")){

				}
			}
		}catch (Exception e){
			e.getStackTrace();
		}
	}
}
