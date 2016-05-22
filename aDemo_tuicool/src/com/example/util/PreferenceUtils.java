package com.example.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils
{
	public static void saveSize(Context context, int size)
	{
		SharedPreferences sp = context.getSharedPreferences("setSizeData", Context.MODE_PRIVATE);
		sp.edit().putInt("size", size).commit();
	}
	public static int readSize(Context context)
	{
		SharedPreferences sp = context.getSharedPreferences("setSizeData", Context.MODE_PRIVATE);
		int size = sp.getInt("size", 2);
		return size;
	}
	
	//保存仅WIFI时加载图片
	public static void saveWIFIOnly(Context context, boolean isChecked) {
		SharedPreferences sp = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
		sp.edit().putBoolean("isWIFIOnly", isChecked).commit();
	}
	// 读取保存过的缓存图片
	public static boolean readWIFIOnly(Context context) {
		SharedPreferences sp = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
		boolean isWIFIOnly = sp.getBoolean("isWIFIOnly", false);
		return isWIFIOnly;
	}
	
}
