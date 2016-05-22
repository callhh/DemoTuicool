package com.example.tuicool;

import com.example.tuicool.R;
import com.example.util.ThemeToggleUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OffLineReadActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式  切换
				ThemeToggleUtils.setThemeToggle(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_off_line_read);
		initUI();
	}

	private void initUI()
	{
		initToolbar();
		TextView tv_offline_read_intro = (TextView) findViewById(R.id.tv_offline_read_intro);
		tv_offline_read_intro.setText("使用说明：点击"+"进入离线下载列表页，选择你想要下载的频道，下载完成后重新进入本页可以看到已下载的频道，可在无网络的时候点击频道进行阅读。\n对于缓存的文章和图片，应用会在启动时删除过期的数据（默认是1个月前已读的频道）。");
		
	}

	private void initToolbar()
	{
		// 在getSupportActionBar()方法之前调用
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // 设置显示返回键
		// 设置标题
		actionBar.setTitle("离线阅读");
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.off_line_read, menu);
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
		case R.id.action_offline_download:
			Toast.makeText(this, "即将更新，请稍后下载",Toast.LENGTH_SHORT ).show();
			return true;
		case android.R.id.home: // 返回键监听
			overridePendingTransition(0, 0);
			finish(); 
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
