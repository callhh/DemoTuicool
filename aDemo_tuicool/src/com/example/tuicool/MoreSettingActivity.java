package com.example.tuicool;

import com.example.tuicool.R;
import com.example.util.PreferenceUtils;
import com.example.util.TKConstants;
import com.example.util.ThemeToggleUtils;
import com.sevenheaven.iosswitch.ShSwitchView;
import com.sevenheaven.iosswitch.ShSwitchView.OnSwitchStateChangeListener;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class MoreSettingActivity extends SwipeBackActivity implements OnClickListener
{

	private Editor mEditor;
	private SharedPreferences sp;
	private ShSwitchView switch_view_onlyWIFI;
	private ShSwitchView switch_view_downloadWIFI;
	private ShSwitchView switch_view_huadong;
	private ShSwitchView switch_view_open_inside_link;
	private ShSwitchView switch_view_open_comment;
	private ShSwitchView switch_view_voice_fanye;
	private RelativeLayout layout1_set_wifi_only;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式  切换
		ThemeToggleUtils.setThemeToggle(this);
				
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moresetting);
		//滑动关闭Activity 四种模式 SwipeBackLayout.EDGE_LEFT ; .EDGE_RIGHT ; .EDGE_BOTTOM ; .EDGE_ALL
		SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
		mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT); //从左向右滑动关闭
		initUI();
	}

	private void initUI()
	{
		initToolbar();
		initSpinner(); // 字体调整大小 弹窗模式
		initSwitchView();

		layout1_set_wifi_only = (RelativeLayout) findViewById(R.id.layout1_set_wifi_only);
		layout1_set_wifi_only.setOnClickListener(this);
	}

	private void initToolbar()
	{
		// 在getSupportActionBar()方法之前调用
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_moresetting);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // 设置显示返回键
		// 设置标题
		actionBar.setTitle("更多设置");
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

	private void initSpinner()
	{
		Spinner spinner = (Spinner) findViewById(R.id.widgets_spinner);
		// 设置字号 弹出一个Spinner布局
		// spinner.setAdapter(new ArrayAdapter(this,
		// R.layout.support_simple_spinner_dropdown_item,
		// com.example.sample.R.array.textsize_choose));
		// 为spinner设置监听事件
		spinner.setOnItemSelectedListener(new SpinnerSelectListener());
		// 读取选中的状态
		int selectPosition = PreferenceUtils.readSize(this);
		spinner.setSelection(selectPosition);
	}

	private void initSwitchView()
	{
		switch_view_onlyWIFI = (ShSwitchView) findViewById(R.id.switch_view_onlyWIFI);
		switch_view_downloadWIFI = (ShSwitchView) findViewById(R.id.switch_view_downloadWIFI);
		switch_view_huadong = (ShSwitchView) findViewById(R.id.switch_view_huadong);
		switch_view_open_inside_link = (ShSwitchView) findViewById(R.id.switch_view_open_inside_link);
		switch_view_open_comment = (ShSwitchView) findViewById(R.id.switch_view_open_comment);
		switch_view_voice_fanye = (ShSwitchView) findViewById(R.id.switch_view_voice_fanye);
		switch_view_onlyWIFI.setOn(true, true); // animated参数决定是否以动画方式切换switch状态
//		switch_view_onlyWIFI.setOn(true); //通过setOn(boolean on)方法设置switch状态
		switch_view_downloadWIFI.setOn(false);
		switch_view_huadong.setOn(false);
		switch_view_open_inside_link.setOn(false);
		switch_view_open_comment.setOn(false);
		switch_view_voice_fanye.setOn(false);
		
//		通过isOn()获取switch状态 TODU -- using isOn() method to get switch state
//		通过setOnSwitchStateChangeListener方法增加状态改变回调
//		可在xml文件内通过tintColor标签改变颜色
//		checkWifi.setChecked(checked); // 用系统Demo的CheckBox
//		checkWifi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				PreferenceUtils.saveWIFIOnly(SettingActivity.this, isChecked);
//			}
//		});
		boolean checked = PreferenceUtils.readWIFIOnly(this); //初始化读取缓存图片
		switch_view_onlyWIFI.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener()
		{
			
			@Override
			public void onSwitchStateChange(boolean isOn)
			{	// 用户选中就保存
				PreferenceUtils.saveWIFIOnly(MoreSettingActivity.this, isOn);
			}
		});
		
	}
	private final class SpinnerSelectListener implements OnItemSelectedListener
	{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
		{
			// 状态被选中 就保存
			PreferenceUtils.saveSize(MoreSettingActivity.this, position);
//			switch (position)
//			{  // 状态已都被保存过  不用重复保存  封装在工具类中PreferenceUtils
//			case TKConstants.ZUIXIAO:
//				mEditor.putInt(TKConstants.fontSizeKey.DETAIL_FONT_SIZE, 0).commit();
//				break;
//			case TKConstants.PIANXIAO:
//				mEditor.putInt(TKConstants.fontSizeKey.DETAIL_FONT_SIZE, 1).commit();
//				break;
//			case TKConstants.ZHONGDENG:
//				mEditor.putInt(TKConstants.fontSizeKey.DETAIL_FONT_SIZE, 2).commit();
//				break;
//			case TKConstants.PIANDA:
//				mEditor.putInt(TKConstants.fontSizeKey.DETAIL_FONT_SIZE, 3).commit();
//				break;
//			case TKConstants.ZUIDA:
//				mEditor.putInt(TKConstants.fontSizeKey.DETAIL_FONT_SIZE, 4).commit();
//				break;
//
//			default:
//				break;
//			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent)
		{
			// TODO Auto-generated method stub
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu)
//	{
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.moresetting, menu);
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

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.layout1_set_wifi_only:
//			Toast.makeText(this, "正在下载", Toast.LENGTH_SHORT).show();
			layout1_set_wifi_only.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// 通过setOn()设置switch状态  ； isOn()获取switch状态
					switch_view_onlyWIFI.setOn(!switch_view_onlyWIFI.isOn());
				}
			});
			break;
		// case R.id.widgets_spinner:

		// //
		// PreferenceUtils.saveSize(this, size);
		// break;

		default:
			break;
		}
	}
}
