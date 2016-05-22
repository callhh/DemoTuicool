package com.example.tuicool;

import com.example.tuicool.R;
import com.example.util.ThemeToggleUtils;
import com.sevenheaven.iosswitch.ShSwitchView;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class SetShareActivity extends AppCompatActivity
{
	private ShSwitchView switch_view_setShare_weibo;
	private ShSwitchView switch_view_setShare_weixin;
	private ShSwitchView switch_view_setShare_qq_zone;
	private ShSwitchView switch_view_setShare_douban;
	private ShSwitchView switch_view_setShare_evernote;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式  切换
				ThemeToggleUtils.setThemeToggle(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_share);
		initUI();
	}
	private void initUI()
	{
		initToolbar();
		initSwitchView();
		
	}
	private void initToolbar()
	{
		// 在getSupportActionBar()方法之前调用
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // 设置显示返回键
		// 设置标题
		actionBar.setTitle("分享设置");
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
	private void initSwitchView()
	{
		switch_view_setShare_weibo = (ShSwitchView) findViewById(R.id.switch_view_setShare_weibo);
		switch_view_setShare_weixin = (ShSwitchView) findViewById(R.id.switch_view_setShare_weixin);
		switch_view_setShare_qq_zone = (ShSwitchView) findViewById(R.id.switch_view_setShare_qq_zone);
		switch_view_setShare_douban = (ShSwitchView) findViewById(R.id.switch_view_setShare_douban);
		switch_view_setShare_evernote = (ShSwitchView) findViewById(R.id.switch_view_setShare_evernote);
		
		switch_view_setShare_weibo.setOn(false, true); // animated参数决定是否以动画方式切换switch状态
//		switch_view_onlyWIFI.setOn(true); //通过setOn(boolean on)方法设置switch状态
		switch_view_setShare_weixin.setOn(false);
		switch_view_setShare_qq_zone.setOn(false);
		switch_view_setShare_douban.setOn(false);
		switch_view_setShare_evernote.setOn(false);
		
//		通过isOn()获取switch状态 TODU -- using isOn() method to get switch state
//		通过setOnSwitchStateChangeListener方法增加状态改变回调
//		可在xml文件内通过tintColor标签改变颜色
//		});
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu)
//	{
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.set_share, menu);
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
			overridePendingTransition(0, 0);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
