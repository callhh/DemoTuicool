package com.example.tuicool;

import com.example.tuicool.R;
import com.example.util.ThemeToggleUtils;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class LoginActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式  切换
		ThemeToggleUtils.setThemeToggle(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initUI();
		
	}

	private void initUI()
	{
		initToolbar();
		
	}

	private void initToolbar()
	{
		// 在getSupportActionBar()方法之前调用
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // 设置显示返回键
		// 设置标题
		actionBar.setTitle("登陆");
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id)
		{
		case R.id.action_regist: // 注册
			
			return true;
		case android.R.id.home: // 返回键监听
			overridePendingTransition(0, 0);
			finish(); 
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
