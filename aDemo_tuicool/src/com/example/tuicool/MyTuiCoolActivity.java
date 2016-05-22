package com.example.tuicool;

import com.example.tuicool.R;
import com.example.util.ThemeToggleUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MyTuiCoolActivity extends AppCompatActivity implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式  切换
				ThemeToggleUtils.setThemeToggle(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_tui_cool);
		initUI();
	}
	
	private void initUI()
	{
		initToolbar(); // 初始化Toolbar
		RelativeLayout layout1_login = (RelativeLayout) findViewById(R.id.relativeLayout1_login);
		RelativeLayout layout2_waitRead = (RelativeLayout) findViewById(R.id.relativeLayout2_waitRead);
		RelativeLayout layout3_myFavor = (RelativeLayout) findViewById(R.id.relativeLayout3_myFavor);
		RelativeLayout layout4_myTuiKan = (RelativeLayout) findViewById(R.id.relativeLayout4_myTuiKan);
		layout1_login.setOnClickListener(this);
		layout2_waitRead.setOnClickListener(this);
		layout3_myFavor.setOnClickListener(this);
		layout4_myTuiKan.setOnClickListener(this);
		
	}
	private void initToolbar()
	{
		// 在getSupportActionBar()方法之前调用
		Toolbar toolbar = (Toolbar) findViewById(R.id.tuicool_toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // 设置显示返回键
		// 设置标题
		actionBar.setTitle("我的推酷");
	}

	@Override
	public void onClick(View v)
	{
		Intent intent = new Intent(this, MyFavorActivity.class);
		int id = v.getId();
		switch (id)
		{
		case R.id.relativeLayout1_login: // 登陆
			intent.setClass(this, LoginActivity.class);
			startActivity(intent);
			// 第一个参数是当前activity退出时的动画，第二个参数则是要跳转的activity进入时动画
			overridePendingTransition(R.anim.activity_open_enter,R.anim.activity_close_exit);
			break;
		case R.id.relativeLayout2_waitRead: // 我的待读
			intent.setClass(this, MyWaitReadActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.activity_open_enter,R.anim.activity_close_exit);
			break;
		case R.id.relativeLayout3_myFavor: // 我的收藏
			intent.setClass(this, MyFavorActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.activity_open_enter,R.anim.activity_close_exit);
			break;
		case R.id.relativeLayout4_myTuiKan: // 我的推刊
			intent.setClass(this, MyTuiKanActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.activity_open_enter,R.anim.activity_close_exit);
			break;

		default:
			break;
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_tui_cool, menu);
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
		case R.id.action_settings:
			return true;
		
	case android.R.id.home: // 返回键监听
//		Intent intent = new Intent(this, MainActivity.class);
//		startActivity(intent );
		overridePendingTransition(0, 0);
		finish(); 
		return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
