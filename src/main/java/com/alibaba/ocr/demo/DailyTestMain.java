package com.alibaba.ocr.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.entity.Ocrentity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
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
 * @since 2021/10/15
 */
public class DailyTestMain {
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
		String bodys = "{\"img\":\""+ changeToBase64("/Users/hyong/Desktop/2661637649717_.pic.jpg")+"\",\"prob\":false,\"charInfo\":false,\"rotate\":false,\"table\":false}";
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
			JSONArray jsonArray = jsonObject.getJSONArray("prism_wordsInfo");
			List<String> list = new ArrayList<>();
			for (Object obj : jsonArray) {
				JSONObject jsonObject1 = JSONObject.parseObject(obj.toString());////////////
				list.add(jsonObject1.getString("word"));
			}
			List<Ocrentity> result = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				String str = list.get(i);
				if (str.equals("持仓收益/率")){
					String tco = list.get(i - 3);
					Ocrentity ocrentity = new Ocrentity();
					ocrentity.setFundTitle(tco.substring(0,tco.length() - 6));
					ocrentity.setFundCode(tco.substring(tco.length() - 6));
					ocrentity.setIndexDataOne(list.get(i + 1));
					ocrentity.setIndexDataTwo(list.get(i + 3));
					ocrentity.setIndexDataThree(list.get(i + 4));
					result.add(ocrentity);
					i += 4;
				}
			}
			//String content = jsonObject.getString("content");
			//String[] arraylist = content.split(" ");

			//List<String> list = Arrays.asList(arraylist);
			//String regex2 = ".*[0-9].*";//含有数字
			//boolean flag = false;//开始获取数据标识
			/*String s1 = "北上资金缓慢入场，看好慢涨行情";
			String s2 = "04-26";
			boolean b1 = s1.matches(regex2);
			boolean b2 = s2.matches(regex2);*/
			/*for (int i = 0; i < list.size(); i++) {
				String str = list.get(i);
				if (!flag){
					if (str.contains("序")){//盈亏排序 --下一组开始
						flag = true;
					}
				}else {//筛选获取数据
					if (str.matches(regex2)){
						Ocrentity ocrentity = new Ocrentity();
						ocrentity.setFundCode(str.substring(str.length() - 6));//001766
						ocrentity.setFundTitle(str.substring(0,str.length() - 6));//上投摩根医疗健康股票
						ocrentity.setIndexNameOne(list.get(i + 1));//资产
						ocrentity.setIndexNameTwo(list.get(i + 3));//持仓收益
						ocrentity.setIndexDataOne(list.get(i + 4));//资产值
						ocrentity.setIndexDataTwo(list.get(i + 6));//持仓收益值
						result.add(ocrentity);
						i += 6;
					}else{
						i += 1;
					}
				}

			}*/
			/*for (int i = 0; i < list.size(); i++) {
				String str = list.get(i);
				if (str.equals("持仓收益/率")){
					Ocrentity ocrentity = new Ocrentity();
					ocrentity.setFundCode(list.get(i - 3));
					ocrentity.setFundTitle(list.get(i - 4));
					ocrentity.setIndexDataOne(list.get(i + 1));
					ocrentity.setIndexDataTwo(list.get(i + 3));
					ocrentity.setIndexDataThree(list.get(i + 4));
					result.add(ocrentity);
					i += 5;
				}
			}*/

			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将文件读取并转化为Base64字符串
	 * @param fileName 文件名
	 * @return  Base64字符串
	 * @throws Exception
	 */
	public static String changeToBase64(String fileName) throws Exception{
		File file = new File(fileName);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int)file.length()];
		inputFile.read(buffer);
		inputFile.close();
		String base64Code=new BASE64Encoder().encode(buffer);
		return base64Code;
	}

}
