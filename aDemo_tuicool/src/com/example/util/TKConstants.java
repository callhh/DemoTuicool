package com.example.util;

import com.example.model.detail.Article;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.VolleyListener;

import android.content.Context;

// 保存常量 
//静态内部类 ---> 整理分类 后期优化数据 
public class TKConstants
{
	public static final String SP_THEME_TOGGLE = "themeToggleData"; // 
	public static final int ZUIXIAO = 0;
	public static final int PIANXIAO = 1;
	public static final int ZHONGDENG = 2;
	public static final int PIANDA = 3;
	public static final int ZUIDA = 4;
	
	public static final class Url
	{
//		public static final String HOST = "http://192.168.0.103:8080/tuiku_server/";
		// 七牛云端存储数据
		public static final String HOST = "http://7xt68a.com1.z0.glb.clouddn.com/";
		public static final String HOT = HOST + "tuiku_hot.txt"; // 热门栏目

		public static final String TUIJIAN = HOST + "tuiku_hot.txt"; // 推荐栏目
		public static final String KEJI = HOST + "keji.txt"; 	// 科技栏目
		public static final String CHUANGYE = HOST + "tuiku_chaungye.txt"; // 创业栏目
		public static final String SHUMA = HOST + "shuma.txt"; // 数码栏目
		public static final String JISHU = HOST + "jishu.txt"; // 技术栏目
		public static final String SHEJI = HOST + "sheji.txt"; // 设计栏目
		public static final String YINGXIAO = HOST + "yingxiao.txt"; // 营销栏目
		
		public static final String ZHANDIAN = HOST + "zhandian.txt"; // 站点
		public static final String ZHUTI = HOST + "zhuti.txt"; // 主题
		public static final String ZHOUKAN = HOST + "zhoukan.txt"; // 周刊
		
		public static final String HOT_DETAIL = HOST + "hot_detail.txt"; // 热门详情页
		public static final String UPGRADE_LOG = HOST + "upgradelog.txt"; // 更新日志
		
	}

	public static final class bundleKey
	{
		public static final String FRAGMENT_TYPE = "FRAGMENT_TYPE"; // 跳转界面的key值

	}

	public static final class intentKey
	{
		public static final String DETAIL_URL = "detail_url";
		public static final String ARTICLE_FAVOR = "article_favor"; // 添加收藏key值
	}

	public static final class fontSizeKey
	{
		public static final String DETAIL_FONT_SIZE = "detail_font_size"; // 保存字体大小的Key值

	}
	
	public static final class Opinion
	{
		public static final String OPINION_TABLE = "Feedback";
		public static final String CURRENTTIME = "currenttime";
		public static final String OPTIONINFO = "info";
	}
	
}
