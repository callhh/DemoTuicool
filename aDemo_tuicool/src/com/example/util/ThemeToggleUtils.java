package com.example.util;


import com.example.tuicool.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class ThemeToggleUtils extends AppCompatActivity
{
	public static void setThemeToggle(Context context)
	{
		// 设置夜间模式 切换
		SharedPreferences sp = context.getSharedPreferences(TKConstants.SP_THEME_TOGGLE, 0);
		boolean isNight = sp.getBoolean("isNight", false);
		if (isNight)
		{
			context.setTheme(R.style.night);
		}else
		{
			context.setTheme(R.style.light);
		}
	}
}
