package com.example.tuicool;

import com.example.database_utils.DBUtils;
import com.example.fragment.HotFragment;
import com.example.tuicool.R;
import com.example.util.FragmentUtils;
import com.example.util.TKConstants;
import com.example.util.ThemeToggleUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MyFavorActivity extends AppCompatActivity
{

	private Fragment mFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式 切换
		ThemeToggleUtils.setThemeToggle(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_favor);
		initUI();
		initData();
	}
	
	
	
	
	private void initUI()
	{
		initToolbar();
		
		mFragment = new HotFragment(); // 复用热门页面的文章收藏
		Bundle param = new Bundle();
		param.putString(TKConstants.bundleKey.FRAGMENT_TYPE, TKConstants.bundleKey.FRAGMENT_TYPE);
		mFragment.setArguments(param ); // android系统的API 传参 复用
		
		FragmentUtils.replaceFragment(this, mFragment);// 动态替换Fragment
	}




	private void initData()
	{
		
	}
	private void initToolbar()
	{
		// 在getSupportActionBar()方法之前调用
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // 设置显示返回键
		// 设置标题
		actionBar.setTitle("我的收藏");
		// actionBar.setSubtitle("subTitle");
		// // 设置背景图片
		// Drawable actionbarbg =
		// getResources().getDrawable(R.drawable.actionbar_bg);
		// actionBar.setBackgroundDrawable(actionbarbg );
		// // 设置LOGO
		// actionBar.setDisplayUseLogoEnabled(true);
		// actionBar.setDisplayShowHomeEnabled(true);
		// actionBar.setLogo(R.drawable.ic_launcher);
	}



//	@Override
//	public boolean onCreateOptionsMenu(Menu menu)
//	{
//		getMenuInflater().inflate(R.menu.my_favor, menu);
//		return true;
//	}

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
//			Intent intent = new Intent(this, MainActivity.class);
//			startActivity(intent );
			overridePendingTransition(0, 0);
			finish(); 
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed()
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent );
		overridePendingTransition(0, 0);
		finish(); 
		super.onBackPressed();
	}
}
