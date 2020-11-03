package com.example.dockerdemo.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TextCompUtils {

	private Integer insert_num = 0; // 插入总数
	private Integer delete_num = 0; // 删除总数
	private Integer substitute_num = 0; // 替换总数
	private Integer correct_num = 0; // 正确个数
	private Integer target_num = 0; // 总字数
	private static List<Integer> list_insert = new ArrayList<Integer>(); // 存放插入的位置
	private static List<Integer> list_delete = new ArrayList<Integer>(); // 存放删除的位置
	private static List<Integer> list_substitute = new ArrayList<Integer>(); // 存放替换的位置

	private final static Logger logger = LoggerFactory.getLogger(TextCompUtils.class);


	/**
	 *
	 * @param str 语音识别文本
	 * @param target 标准文本
	 * @return
	 */
	private int compare(String str, String target) {
		int[][] d; // 矩阵
		int n = str.length();
		int m = target.length();
		target_num = m;

		int i; // 遍历str的
		int j; // 遍历target的
		char ch1; // str的
		char ch2; // target的
		int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1

		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // 初始化第一列
			d[i][0] = i;
		}

		for (j = 0; j <= m; j++) { // 初始化第一行
			d[0][j] = j;
		}

		for (i = 1; i <= n; i++) { // 遍历str
			ch1 = str.charAt(i - 1); // 去匹配target
			for (j = 1; j <= m; j++) {
				ch2 = target.charAt(j - 1);
				if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				} // 左边+1,上边+1, 左上角+temp取最小
				d[i][j] = min_num(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
			}
		}

		// 分别提取 增 删 改的个数
		int temp_i = n, temp_j = m;
		while (d[temp_i][temp_j] != 0) {
			if (temp_i > 0 && d[temp_i][temp_j] == d[temp_i - 1][temp_j] + 1) {
				list_insert.add(temp_i);
				insert_num += 1;
				temp_i = temp_i - 1;
			} else if (d[temp_i][temp_j] == d[temp_i][temp_j - 1] + 1) {
				list_delete.add(temp_i);
				delete_num += 1;
				temp_j = temp_j - 1;
			} else if (d[temp_i][temp_j] == d[temp_i - 1][temp_j - 1] + 1) {
				list_substitute.add(temp_i);
				substitute_num += 1;
				temp_i = temp_i - 1;
				temp_j = temp_j - 1;
			} else {
				temp_i = temp_i - 1;
				temp_j = temp_j - 1;
			}
		}

		correct_num = m - (delete_num + substitute_num);

		return d[n][m];
	}

	//求三个数中的最小值
	private int min_num(int one, int two, int three) {
		int temp;
		temp = (one = one < two ? one : two) < three ? one : three;
		return temp;
	}

	/**
	 * 配置化
	 * @param resultString
	 * @param standardScript

	 * @return
	 */

	public JSONObject getTextComp(String resultString, String standardScript) {
		//textCompConfig.getTextcomp();

		return this.getSimilarityRatio(resultString,standardScript);

	}

	/**
	 * * 获取两字符串的相似度数据      
	 */
	public JSONObject getSimilarityRatio(String str, String target) {
		JSONObject resultJ = new JSONObject();

		// 去除空白字符、换行、标点符号
		String regex = "[\\pP\\p{Punct}\\s]";
//		System.out.println("str= " + str.replaceAll(regex, ""));
//		System.out.println("target= " + target.replaceAll(regex, ""));
		resultJ.put("recLabel", str);
		resultJ.put("manualTxt", target);


		// 最小改变步骤
		int minStep = compare(str.replaceAll(regex, ""), target.replaceAll(regex, ""));
		resultJ.put("minStep", minStep);
//		System.out.println("最少改动步骤数：" + minStep + " 步");
//		System.out.println("insert_num= " + insert_num);
//		System.out.println("delete_num= " + delete_num);
//		System.out.println("substitute_num= " + substitute_num);
//		System.out.println("correct_num= " + correct_num);
//		System.out.println("target_num= " + target_num);

		resultJ.put("numI", insert_num);
		resultJ.put("numD", delete_num);
		resultJ.put("numS", substitute_num);
		resultJ.put("numH", correct_num);
		resultJ.put("numN", target_num);

		float corr, acc;
		if (correct_num == 0 || target_num == 0 || correct_num - insert_num < 0) {
			corr = 0;
			acc = 0;
		} else {
			corr = (float) ((int) (((float) correct_num / (float) target_num) * 10000)) / 100;
			acc = (float) ((int) (((float) (correct_num - insert_num) / (float) target_num) * 10000)) / 100;
		}

		resultJ.put("Acc", acc);
		resultJ.put("Corr", corr);
//		System.out.println("Acc= " + acc);
//		System.out.println("Corr= " + corr);

		return resultJ;
	}


	/**
	 * 单独计算准确率和正确率
	 * @param taskDataAccList
	 * @return
	 */
	public JSONObject calcuAccAndCorr(List<JSONObject> taskDataAccList){
		JSONObject jsonObject = new JSONObject();
		Integer sumI = 0;
		Integer sumH = 0;
		Integer sumN = 0;
		for (JSONObject taskDataAccJ : taskDataAccList) {
			sumI += taskDataAccJ.getInteger("numI");
			sumH += taskDataAccJ.getInteger("numH");
			sumN += taskDataAccJ.getInteger("numN");
		}

		//正确率
		float sumAcc = 0;
		//准确率
		float sumCorr = 0;
		if (sumN > 0) {
			sumCorr = (float) ((int) (((float) sumH / (float) sumN) * 10000)) / 100;
			sumAcc = (float) ((int) (((float) (sumH - sumI) / (float) sumN) * 10000)) / 100;
			sumAcc = (sumAcc < 0) ? 0 : sumAcc;
		}
		jsonObject.put("sumAcc", sumAcc);
		jsonObject.put("sumCorr", sumCorr);
		return  jsonObject;
	}

	/**
	 *
	 * @param resultString 语音识别文本
	 * @param standardScript 标准文本
	 * @return 偏移率
	 */
	public float getDeviationRate(String resultString, String standardScript) {
		//去掉标点符号，标点符号不做偏移率计算
		String strstandardScript = standardScript.replaceAll("[\\pP\\p{Punct}]", "");
		String strresultString = resultString.replaceAll("[\\pP\\p{Punct}]", "");
		logger.info("standardScript:" + strstandardScript);
		logger.info("resultString:  " + strresultString);
		int length= 0;
		char[] charstandardScripts = strstandardScript.toCharArray();
		char[] chartrresultStrings = strresultString.toCharArray();
		logger.info("标准文本长度:" + charstandardScripts.length);
		logger.info("识别文本长度:" + chartrresultStrings.length);
		if (charstandardScripts.length>chartrresultStrings.length) {
			length=chartrresultStrings.length;
		}else {
			length=charstandardScripts.length;
		}
		logger.info("使用文本长度:" + length);
		float state = 0;
		for (int i = 0; i < length; i++) {
			if (chartrresultStrings[i] == charstandardScripts[i]) {
				state++;
			}
		}
		logger.info("正确数:" + state);
		float rate = changeDeviationRate(state/charstandardScripts.length);
		//JSONObject rate = changeDeviationRate(state/charstandardScripts.length);

		return rate;
	}

	//flaot保留两位
	public float changeDeviationRate(float rate) {
		Float priceCar = rate;
		// 设置位数
		int scale = 2;
		// 表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
		int roundingMode = 4;
		BigDecimal bd = new BigDecimal((float) priceCar);
		bd = bd.setScale(scale, roundingMode);
		priceCar = bd.floatValue();
		return priceCar;
	}
}

