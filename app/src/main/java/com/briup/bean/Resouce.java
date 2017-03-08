package com.briup.bean;

import java.util.ArrayList;
import java.util.List;

import com.briup.guesspicture.R;


public   class Resouce {
	private static String[] oneOptions={"线","话","飞","人","笑","越","黄","工"
			,"大","超","传","特","宝","鸿","队","赤","光","去","壁"
			,"盒","王"};
	private static String[] twoOptions={"越","狂","头","中","国","赤","派","壁"
			,"合","宝","的","美","疯","线","石","赤","人","去","伙"
			,"盒","王"};
	private static String[] threeOptions={"林","曹","玛","泽","黄","颖","黄","工"
			,"大","超","传","特","亚","鸿","队","赤","光","去","晨"
			,"飞","林"};
	private static String[] fourOptions={"曾","丹","飞","吕","李","弦","黄","工"
			,"大","超","传","特","赫","小","陈","赤","光","去","古"
			,"盒","王"};
	private static String[] fiveOptions={"线","话","飞","人","笑","越","黄","工"
			,"大","超","传","特","宝","间","队","赤","黄","去","壁"
			,"道","王"};
	private static String[] sixOptions={"宝","话","飞","人","峰","越","黄","汪"
			,"大","张","林","庾","俊","鸿","队","赤","光","去","杰"
			,"张","王"};
	private static String[] sevenOptions={"谭","话","栋","人","笑","咏","黄","工"
			,"大","超","传","特","炫","鸿","队","麟","光","去","壁"
			,"张","王"};
	private static String[] eightOptions={"荣","话","飞","郭","笑","越","富","工"
			,"大","国","刘","特","宝","德","城","赤","光","去","壁"
			,"张","华"};
	private static String[] nineOptions={"凌","话","飞","人","泽","越","黄","工"
			,"松","玛","传","岛","心","亚","队","赤","光","丽","峰"
			,"盒","王"};
	private static String[] tenOptions={"虎","话","飞","教","笑","越","古","工"
			,"惑","超","龙","特","宝","仔","父","赤","光","弟","壁"
			,"兄","王"};
	private static String[] answers={"越光宝盒","中国合伙人","曹颖","陈赫","线人","林俊杰","谭咏麟",
			"刘德华","王心凌","教父"};
	private static String[] Titles={"恶搞风格的喜剧大片","创业励志电影","演员、主持人",
			"《爱情公寓》主角","谢霆锋主演的悬疑犯罪片",
			"著名歌手","香港演员、歌手","香港演员、歌手","台湾演员、歌手",
			"黑社会大片"};
	private static int icons[]={R.drawable.movie_ygbh,R.drawable.movie_zghhr,R.drawable.people_caoying,R.drawable.people_zengxiaoxian,
			R.drawable.movie_xr,R.drawable.people_linjunjie,R.drawable.people_tanyonglin,
			R.drawable.people_liudehua,R.drawable.people_wangxinling,R.drawable.movie_jf};
	private static List<String[]> listOptions=new ArrayList<>();
	
	//返回一个list集合 装有 所有的问题
	public static List<Answer> answers(){
		listOptions.add(oneOptions);listOptions.add(twoOptions);
		listOptions.add(threeOptions);listOptions.add(fourOptions);
		listOptions.add(fiveOptions);listOptions.add(sixOptions);
		listOptions.add(sevenOptions);listOptions.add(eightOptions);
		listOptions.add(nineOptions);listOptions.add(tenOptions);
		
		List<Answer> backlist=new ArrayList<>();
		for (int i = 0; i < answers.length; i++) {
			Answer answer=new Answer();
			answer.setTitle(Titles[i]);
         	answer.setIcon(icons[i]);
			answer.setViewAnswer(listOptions.get(i));
			answer.setAnswer(Resouce.answers[i]);
			backlist.add(answer);
		}
		return backlist;
	}
}
