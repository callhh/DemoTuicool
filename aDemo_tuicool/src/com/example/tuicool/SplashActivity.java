package com.example.tuicool;

import com.example.util.ThemeToggleUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity
{
	Handler handler = new Handler();
	private boolean isFirst; // 保存是否第一次启动页面
	private SharedPreferences sp;
	private Editor edit;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式  切换
		ThemeToggleUtils.setThemeToggle(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		initActivity(); // 初始化界面
	}

	private void initActivity()
	{
		sp = getSharedPreferences("isFirst", 0);
		edit = sp.edit();

		isFirst = sp.getBoolean("isFirst", true);
		handler.postDelayed(new Runnable()
		{
			Intent intent = new Intent();

			public void run()
			{
				if (isFirst)
				{
					intent.setClass(SplashActivity.this, ProductTourActivity.class);
					overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					edit.putBoolean("isFirst", false);
					edit.commit(); // 提交
				} else
				{
					intent.setClass(SplashActivity.this, MainActivity.class);
					overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

				}

				startActivity(intent);
				finish();
			}
		}, 1200);
	}

}
