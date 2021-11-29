package com.alibaba.ocr.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.entity.AliRowsEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * TODO 描述
 * </p>
 *
 * @author hyong
 * @since 2021/11/25
 */
public class AliTestMain {

	public static final String REGEX =  "[\\u4E00-\\u9FA5]+[a-z]*\\d+([\uff0c\\d]*|[\\,\\d+]*)\\.\\d+(\\+|\\-)\\d+([\uff0c\\d]*|[\\,\\d+]*)\\d*\\.\\d+" +
										"[\\u4E00-\\u9FA5]*(\\D)*[A-Z]*[(\\+|\\-)]*" +
										"\\d+\\.\\d+(\\+|\\-)\\d+\\.\\d+\\%";

	//String content = "东方新能源汽车10.81+0.81主题混合-0.16+8.15%";
	public static final String REGEX2 = "[\\u4E00-\\u9FA5]+[a-z]*\\d+([\uff0c\\d]*|[\\,\\d+]*)\\.\\d+(\\+|\\-)\\d+([\uff0c\\d]*|[\\,\\d+]*)\\.\\d+";

	public static final String REGEX3 = "^([0-9]+|\\+|\\-).*";;//非数字    +   -   开头

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
		String bodys = "{\"img\":\""+ DailyTestMain.changeToBase64("/Users/hyong/Desktop/451637560097_.pic_hd.jpg")+"\",\"prob\":false,\"charInfo\":false,\"rotate\":false,\"table\":false,\"row\":true,\"paragraph\":true}";
		try {
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			System.out.println(response.toString());
			HttpEntity httpEntity = response.getEntity();
			//获取response的body
			String body = EntityUtils.toString(httpEntity);
			System.out.println(body);
			JSONObject jsonObject = JSON.parseObject(body);
			//Map<String,Object> map = (Map<String,Object>)jsonObject.getString("prism_rowsInfo");

			int rowNums = 0;//每行基金最多名称字数
			String prism_rowsInfo = jsonObject.getString("prism_rowsInfo");
			List<AliRowsEntity> list = JSONObject.parseArray(prism_rowsInfo, AliRowsEntity.class);

			for (int i = 0; i < list.size(); i++) {
				AliRowsEntity aliRowsEntity = list.get(i);
				boolean inRege = aliRowsEntity.getWord().matches(REGEX2);
				if (inRege){
					String nextWord = list.get(i + 1).getWord();
					Boolean noStarMath = nextWord.matches(REGEX3);
					if (!nextWord.contains("支付宝") && noStarMath){
						rowNums = aliRowsEntity.getWord().length();
						break;
					}
				}
			}
			String content = jsonObject.get("content").toString().replace(" ","");
			System.out.println(content);

			Pattern pattern = Pattern.compile(REGEX);
			Matcher matcher = pattern.matcher(content);
			while (matcher.find()){
				String envo = matcher.group(0);

			}

		}catch (Exception e){
			e.getStackTrace();
		}
	}

	public void regex(){
		String content = "东方新能源汽车10.81+0.81主题混合-0.16+8.15%";

		String regex = "[\\u4E00-\\u9FA5]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);

		while (matcher.find()){
			System.out.println(matcher.group(0));
		}

	}
}
