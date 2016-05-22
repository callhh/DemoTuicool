package com.example.util;

import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.VolleyListener;

import android.content.Context;

public class APIClient
{
	private static final String[] mURL = new String[]
		{ TKConstants.Url.HOT, 
			TKConstants.Url.TUIJIAN, 
			TKConstants.Url.KEJI,
			TKConstants.Url.CHUANGYE,
			TKConstants.Url.SHUMA, 
			TKConstants.Url.JISHU, 
			TKConstants.Url.SHEJI, 
			TKConstants.Url.YINGXIAO 
		};

	// 根据position参数不同 请求不同网址的方法
	public static void getHomeData(Context context, int position, VolleyListener listener)
	{
		HTTPUtils.get(context, mURL[position], listener);
	}
	// 热门页
	public static void getHOT(Context context, VolleyListener listener)
	{
		HTTPUtils.get(context, TKConstants.Url.HOT, listener);
	}

	public static void getHotDetail(Context context, VolleyListener listener)
	{
		HTTPUtils.get(context, TKConstants.Url.HOT_DETAIL, listener);
	}
	// 更新日志 解析
	public static void getUpgradeLog(Context context, VolleyListener listener)
	{
		HTTPUtils.get(context, TKConstants.Url.UPGRADE_LOG, listener);
	}

	// 站点片段
	public static void getSite(Context context, VolleyListener listener)
	{
		HTTPUtils.get(context, TKConstants.Url.ZHANDIAN, listener);
	}
	// 主题片段
	public static void getTopic(Context context, VolleyListener listener)
	{
		HTTPUtils.get(context, TKConstants.Url.ZHUTI, listener);
	}
	// 周刊片段
	public static void getZhoukan(Context context, VolleyListener listener)
	{
		HTTPUtils.get(context, TKConstants.Url.ZHOUKAN, listener);
	}
}
