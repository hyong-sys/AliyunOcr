package com.retes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * TODO 描述
 * </p>
 *
 * @author hyong
 * @since 2021/11/29
 */
public class RegexDemo {
	public static void main(String[] args) {
		//String regex3 = "^([0-9]|\\+|\\-)";
		String regex3 = "^([0-9]+|\\+|\\-).*";
		String a= "0主题灵活配置混合0.00+38.59%";
		boolean matches = a.matches(regex3);

		//支付宝regex
		/*String regex = "[\\u4E00-\\u9FA5]+[a-z]*\\d+([\uff0c\\d]*|[\\,\\d+]*)\\.\\d+(\\+|\\-)\\d+([\uff0c\\d]*|[\\,\\d+]*)\\d*\\.\\d+" +
				"[\\u4E00-\\u9FA5]*(\\D)*[A-Z]*[(\\+|\\-)]*" +
				"\\d+\\.\\d+(\\+|\\-)\\d+\\.\\d+\\%";*/
		//支付宝 content
		/*String content = "HD Cal 零 5：14 < 基金 ? 我的持有口 持有收益排序丶 名称 金额/昨日收益 持有收益/率 浮动类 主要包含股票、混合、指数 东方新能源汽车 10.81 +0.81 主题混合 -0.16 +8.15% 微观点 全球多重利好催化新能源车行情 银河创新成长混 9.35 -0.65 合A -0.04 -6.54% 重大提醒 银河创新混合C类来啦! 买入C类份... 中欧医疗健康混 849.65 -87.93 合A +10.28 -9.06% 支付宝金选 景顺长城新兴成 13，248.27 -1，463.71 长混合 +214.53 -9.76% 方 5，723.19 快看看昨天赚得最多的产品 × -16.03% 小宝帮你准备了专属收益分析 L村宝 Lw + 基金市场 自选 持有";*/
		//String content = "13：47 *il 基金 总金额(元) P投资钥匙 14，748.28 昨日收益(元) 持有收益(元) 累计收益(元) 0.00 -147.05 -681.88 目 收益明细 交易记录 我的定投 我的持有吕 持有收益率排序√ 求助 名称 金额/昨日收益 持有 反馈 申万菱信新能源汽车 694.41 +231.52 主题灵活配置混合 0.00 +38.59% 易方达科翔混合 234.76 +34.76 支付宝金选 0.00 +17.38% 前海联合研究优选混 334.43 +34.43 合A 0.00 +11.48% 天弘惠利灵活配置混 748.84 +48.84 合 0.00 +6.98% 申万菱信乐同混合c 527.67 +27.67 0.00 +5.53% 博时鑫泰灵活配置混 1，053.20 +53.20 合A 0.00 +5.32% 中欧丰泓沪港深混合 1，698.57 +87.81 A 0.00 +5.17% 富国高端制造行业股 209.40 +9.40 票 0.00 +4.70% 广发兴诚混合C 400.67 +8.39 0.00 +2.14% 微观点A股连续21个交易日成交额破万亿，伴随投... 招商中证白酒指数 1，798.35 +14.54 (L OF) A 0.00 +0.81% 景顺长城新兴成长混 190.96 -9.04 合 0.00 -4.52% 市场解读北交所今日开市，如何把握机会?速看解 招商国证生物医药指 953.39 -46.61 数(L OF) A 0.00 -4.66% 中欧阿尔法混合c 561.83 -38.17 0.00 -6.36% 该产品基金经理葛兰入选金牌基金经理 中欧医疗健康混合A 3，242.02 -331.89 支付宝金选 0.00 -8.97% 易方达蓝筹精选混合 1，518.34 -181.66 支付宝金选 0.00 -10.69% 易方达消费行业股票 175.20 -24.80 0.00 -12.40% 微观点 三季度我们降低了一些家电板块的仓位，同 富国中证煤炭指数 174.36 -25.64 (L OF) A 0.00 -12.82% 产品月报 请查收富国中证煤炭十月月报 易方达优质精选混合 231.88 -39.80 (QDI I) 0.00 -13.27% 支付宝金选 更多产品，去市场看看> 易方达优质精选混合 231.88 -39.80 (QDI I) 0.00 -13.27% 支付宝金选 更多产品，去市场看看> 基金销售服务由蚂蚁 (杭州) 基金销售有限公司提供 本页面非任何法律文件，收益数据仅供参考。过往业绩不预示 未来表现，市场有风险，投资需谨慎。 该页面由蚂蚁财富平台设计 > + 市场 自选 持有";
		/*Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content.replace(" ",""));
		while (matcher.find()){
			System.out.println(matcher.group(0));
		}*/


		//招商regex
		/*String regex = "(\\u003e[\\u4E00-\\u9FA5]+[a-z]*)([\\d\\,]*\\d+\\.\\d\\d)([\\d\\,]*\\d+\\.\\d\\d)([\\u4E00-\\u9FA5]+|\\d+\\.\\d\\d)(\\d+\\.\\d{2}\\%)";*/
		//招商 content
		/*String content = "9：42：：!令99基金101.00尾号8881-总金额(元)包含在途资金100.00元0.000.000.00%昨日收益持仓收益持仓收益率>当前有1笔交易进行中>￥我的定投历史交易收益监控台其他名称金额/昨日收益持仓收益/率易方达基金招财号" +
				">易方达蓝筹精选100.000.00暂未更新0.00%尾号8881博时基金招财号>博时现金宝A1.000.000.000.00%今日收益尾号8881";
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content.replace(" ",""));
		while (matcher.find()){
			System.out.println(matcher.group(0).replace(">",""));
			System.out.println(matcher.group(1).replace(">",""));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
			System.out.println(matcher.group(4));
			System.out.println(matcher.group(5));
		}*/


		//天天regex
		String regex =  "([\\u4E00-\\u9FA5]+([(|（][a-z]+[)|）])*[a-z]*)" +
						"(\\d{6,})" +
						"资产昨日收益持仓收益/率" +
						"((\\d+[,|，])*\\d{1,3}\\.\\d{2})" +
						"([+|-](\\d+[,|，])*\\d{1,3}\\.\\d{2})" +
						"([+|-](\\d+[,|，])*\\d{1,3}\\.\\d{2})" +
						"([+|-](\\d+[,|，])*\\d{1,3}\\.\\d{2}\\%)";
		//天天content
		String content = "下午2：33您@ 四lC79 基金 组合 资产(元) 2，997.66 昨日收益 持仓收益 +24.98 -2.34 自 交易记录 定投计划 到期提醒 暂无在途交易 1个定投进行中 30天内无到期 全部产品 银行卡 资产序 招商中证白酒指数（L OF) A 161725" +
				" 资产 昨日收益 持仓收益/率 2，997.66 +24.98 -2.34 -0.08% 市场布局北交所?看看这只基! 11-22 已经到底了 三 口 < 天天基金 基金 上线基金超1万只，申购费率1折起 长按识别二维码 ";
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content.replace(" ",""));
		while (matcher.find()){
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));//基金代码
			System.out.println(matcher.group(4));//资产
			//System.out.println(matcher.group(5));
			System.out.println(matcher.group(6));//昨日收益
			System.out.println(matcher.group(8));//持仓收益
			//System.out.println(matcher.group(9));
			System.out.println(matcher.group(10));//持仓收益率
		}


		//String content = "景顺长城新兴成A13，14，248.27-1，463，222.71长混合A+214.53-9.76%";
		//String content = "申万菱信乐同混合c527.67+27.670.00+5.53%";
		//String content = "东方新能源汽车10.81+1，463.81";

		/*String regex = "[\\u4E00-\\u9FA5]+[a-z]*\\d+[\uff0c\\d]*\\.\\d+(\\+|\\-)\\d+[\uff0c\\d]*\\d*\\.\\d+" +
				       "[\\u4E00-\\u9FA5]*[A-Z]*[(\\+|\\-)]*" +
				       "\\d+\\.\\d+(\\+|\\-)\\d+\\.\\d+\\%";*/


		//String regex = "[\\u4E00-\\u9FA5]+\\d+([\uff0c\\d]*|[\\,\\d+]*)\\.\\d+(\\+|\\-)\\d+([\uff0c\\d]*|[\\,\\d+]*)\\.\\d+";
		//String content = "东方新能源汽车10.81+0.81主题混合-0.16+8.15%";



	}
}
