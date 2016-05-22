/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tuicool;

import com.example.fragment.HomeFragment;
import com.example.fragment.SiteFragment;
import com.example.fragment.TopicFragment;
import com.example.tuicool.R;
import com.example.util.FragmentUtils;
import com.example.util.TKConstants;
import com.example.util.ThemeToggleUtils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnCloseListener;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//	4. 侧滑UI框架搭建
//参考SupportV7Demo的ActionBarWithDrawerLayout.java
//	1) 修改类名和布局名称
//	2) 重构代码，抽取方法，便于代码阅读
public class MainActivity extends AppCompatActivity implements OnClickListener
{
	private DrawerLayout mDrawerLayout;
	private ListView mDrawer;
	private String[] LEFT_MENU;
	private ActionBarHelper mHelper;
	private ActionBarDrawerToggle mDrawerToggle;
	private TextView mTvSearchText;
	private SearchView mSearchView;
	private HomeFragment mFragment;
	static final int FRAGMENT_HOME = 0;
	static final int FRAGMENT_ZHANDIAN = 1;
	static final int FRAGMENT_ZHUTI = 2;
	static final int FRAGMENT_ZHOUKAN = 3;
	static final int FRAGMENT_MY = 4;

	private boolean isNight;
	private TextView drawer_tv_title;
	private ImageView drawer_img_icon;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式  切换
		ThemeToggleUtils.setThemeToggle(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI(); //初始化UI
	}

	private void initUI()
	{
		mTvSearchText = (TextView) findViewById(R.id.tv_search_text);
		initResource();
		initDrawer(); // 初始化
		initActionbar(); // 初始化标题栏
		initDawerToggle(); // 侧滑切换按钮

		addFragment();
		
	}
	private void initResource() {
		LEFT_MENU =getResources().getStringArray(R.array.left_menu);
//		getResDrawableArray(R.array.left_menuimgs);
	}
	private void addFragment()
	{
		mFragment = new HomeFragment();
		FragmentUtils.replaceFragment(MainActivity.this, mFragment);
	}


	private void initDawerToggle()
	{
		// ActionBarDrawerToggle provides convenient helpers for tying together
		// the
		// prescribed interactions between a top-level sliding drawer and the
		// action bar.
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
	}

	private void initActionbar()
	{
		mHelper = createActionBarHelper();
		mHelper.init();
	}

	private void initDrawer()
	{
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawer = (ListView) findViewById(R.id.start_drawer);
		mDrawerLayout.setDrawerListener(new DemoDrawerListener());
		// 为侧滑菜单添加HeaderView和FooterView 要在调用listview的setAdapter()之前
		View drawer_HeaderView = getLayoutInflater().inflate(R.layout.drawer_headerview, null);
		mDrawer.addHeaderView(drawer_HeaderView);
		LinearLayout mDrawerHeader = (LinearLayout) drawer_HeaderView.findViewById(R.id.left_login_bar);
		mDrawerHeader.setOnClickListener(this);
		
		View drawer_FooterView = getLayoutInflater().inflate(R.layout.drawer_footerview, null);
		mDrawer.addFooterView(drawer_FooterView,null, false);
		
		footV_layout_nightModel = (RelativeLayout) drawer_FooterView.findViewById(R.id.footV_layout_nightModel);
		footV_layout_nightModel.setOnClickListener(this); //夜间模式点击监听事件
		tv_drawer_footV_nightModel = (TextView) drawer_FooterView.findViewById(R.id.tv_drawer_footV_nightModel);
		drawer_FooterView.findViewById(R.id.footV_layout_offline_download).setOnClickListener(this);
		drawer_FooterView.findViewById(R.id.footV_layout_settings).setOnClickListener(this);
		
		mDrawerLayout.setDrawerTitle(GravityCompat.START, getString(R.string.drawer_title));

		mDrawer.setAdapter(new DrawerAdapter());
		// 设置listview的选中状态
		mDrawer.setTextFilterEnabled(true);
		mDrawer.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// Start with first item activated.
		// Make the newly clicked item the currently selected one.
		mDrawer.setItemChecked(0, true);

		mDrawer.setOnItemClickListener(new DrawerItemClickListener());
	}

	class DrawerAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
//			return Shakespeare.TITLES_LIGHT.length;
			return LEFT_MENU.length;
		}

		@Override
		public Object getItem(int position)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View layout = getLayoutInflater().inflate(R.layout.drawer_list_item, null);
			drawer_tv_title = (TextView) layout.findViewById(R.id.drawer_tv_title);
			drawer_img_icon = (ImageView) layout.findViewById(R.id.drawer_img_icon);
			if (position == 0)
			{
				// 侧滑菜单第一行文字颜色 绿色
				// #ff0aa284
				drawer_tv_title.setTextColor(getResources().getColor(R.color.actionbar_bg));
			}
			drawer_tv_title.setText(LEFT_MENU[position]);
			drawer_img_icon.setImageResource(Shakespeare.IMAGE_TITLES_LIGHT[position]);
//			// 设置夜间、日间模式切换的控件更新
//			SharedPreferences sp = getSharedPreferences(TKConstants.SP_THEME_TOGGLE, 0);
//			boolean isNight = sp.getBoolean("isNight", false);
//			if (isNight)
//			{
//				tv_drawer_footV_nightModel.setText("日间模式");
//			}else
//			{
//				tv_drawer_footV_nightModel.setText("夜间模式");
//			}
			return layout;
		}

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);

		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * This list item click listener implements very simple view switching by
	 * changing the primary content text. The drawer is closed when a selection
	 * is made.
	 */
	private class DrawerItemClickListener implements ListView.OnItemClickListener
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			// 如果有为ListView添加HeaderView 要调整一下position
			position = position - mDrawer.getHeaderViewsCount();
			if (position < 0)
			{
				return;
			}
			// 解决越界问题
			if (LEFT_MENU.length > 5 && LEFT_MENU.length < 0) {
				return;
			}
			mHelper.setToolbarTitle(LEFT_MENU[position]);
			mHelper.setTitle(Shakespeare.TITLES_LIGHT[position]);

			mDrawerLayout.closeDrawer(mDrawer);
			Fragment mFragment = null;
			switch (position)
			{
			case FRAGMENT_HOME:
				mFragment = new HomeFragment();
				break;
			case FRAGMENT_MY:
				Intent intent = new Intent(MainActivity.this, MyTuiCoolActivity.class);
				startActivity(intent);
				break;
				case FRAGMENT_ZHANDIAN:
				mFragment = new SiteFragment();
				break;
				case FRAGMENT_ZHUTI:
				mFragment = new TopicFragment();
				break;
				case FRAGMENT_ZHOUKAN:
					mFragment = new TopicFragment();
					break;
				
//			case FRAGMENT_MYFAVOR: // 我的收藏
//				//mFragment = new FindFragment();
//				Intent intent = new Intent(MainActivity.this, MyFavorActivity.class);
//				startActivity(intent);
//				break;

//			case FRAGMENT_LIXIANYUEDU:
//				mFragment = new YuanZhuoFragment();
//				break;
//			case FRAGMENT_YEJIAN_MODEL: // 夜间模式
//				//mFragment = new PersonalFragment();
//				setNightModel();
//				break;
//			case FRAGMENT_SETTING:
//				mFragment = new PersonalFragment();
//				break;
			default:
				break;
			}
			// 动态替换Fragment (要多次用到添加片段 ，封装在工具类里面，方便调用)
			FragmentUtils.replaceFragment(MainActivity.this, mFragment); 
		}

	}

	/**
	 * A drawer listener can be used to respond to drawer events such as
	 * becoming fully opened or closed. You should always prefer to perform
	 * expensive operations such as drastic relayout when no animation is
	 * currently in progress, either before or after the drawer animates.
	 *
	 * When using ActionBarDrawerToggle, all DrawerLayout listener methods
	 * should be forwarded if the ActionBarDrawerToggle is not used as the
	 * DrawerLayout listener directly.
	 */
	private class DemoDrawerListener implements DrawerLayout.DrawerListener
	{
		@Override
		public void onDrawerOpened(View drawerView)
		{
			mDrawerToggle.onDrawerOpened(drawerView);
			mHelper.onDrawerOpened();
		}

		@Override
		public void onDrawerClosed(View drawerView)
		{
			mDrawerToggle.onDrawerClosed(drawerView);
			mHelper.onDrawerClosed();
		}

		@Override
		public void onDrawerSlide(View drawerView, float slideOffset)
		{
			mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
		}

		@Override
		public void onDrawerStateChanged(int newState)
		{
			mDrawerToggle.onDrawerStateChanged(newState);
		}
	}

	/**
	 * Create a compatible helper that will manipulate the action bar if
	 * available.
	 */
	private ActionBarHelper createActionBarHelper()
	{
		return new ActionBarHelper();
	}

	/**
	 * Action bar helper for use on ICS and newer devices.
	 */
	private class ActionBarHelper
	{
		private final ActionBar mActionBar;
		private CharSequence mDrawerTitle;
		private CharSequence mTitle;

		public void setToolbarTitle(String title)
		{
			mActionBar.setTitle(title);
		}
		ActionBarHelper()
		{
			Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
			setSupportActionBar(toolbar);
			mActionBar = getSupportActionBar();
		}

		public void init()
		{
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setDisplayShowHomeEnabled(false);
			mActionBar.setTitle("首页");
			mTitle = mDrawerTitle = getTitle();
		}

		/**
		 * When the drawer is closed we restore the action bar state reflecting
		 * the specific contents in view.
		 */
		public void onDrawerClosed()
		{
			mActionBar.setTitle(mTitle);
		}

		/**
		 * When the drawer is open we set the action bar to a generic title. The
		 * action bar should only contain data relevant at the top level of the
		 * nav hierarchy represented by the drawer, as the rest of your content
		 * will be dimmed down and non-interactive.
		 */
		public void onDrawerOpened()
		{
			mActionBar.setTitle(mDrawerTitle);
		}

		public void setTitle(CharSequence title)
		{
			mTitle = title;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
		mSearchView.setOnQueryTextListener(mOnQueryTextListener);
		// mSearchView.setSubmitButtonEnabled(true);// 是否显示确认搜索按钮
		// mSearchView.setIconifiedByDefault(false);//
		// 设置展开后图标的样式,这里只有两种,一种图标在搜索框外,一种在搜索框内
		// mSearchView.setIconified(false);// 设置
		// mSearchView.clearFocus();// 清除焦点
		mSearchView.setOnCloseListener(new OnCloseListener()
		{
			public boolean onClose()
			{
				mDrawerToggle.setDrawerIndicatorEnabled(true);
				getSupportFragmentManager().beginTransaction().show(mFragment).commit();
				return false;
			}
		});
		// 监听SearchView的点击事件
		mSearchView.setOnSearchClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 设置DrawerToggle的图标为back箭头
				mDrawerToggle.setDrawerIndicatorEnabled(false);
				// SearchView 显示时，隐藏Fragment
				getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
				Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_SHORT).show();
				// isSearchViewShow = true;
			}
		});
		return true;
	}

	private final SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener()
	{
		public boolean onQueryTextChange(String newText)
		{
			newText = TextUtils.isEmpty(newText) ? "" : "Query so far: " + newText;
			mTvSearchText.setText(newText);
			return true;
		}

		public boolean onQueryTextSubmit(String query)
		{
			Toast.makeText(MainActivity.this, "Searching for: " + query + "...", Toast.LENGTH_SHORT).show();
			return true;
		}
	};

	@Override
	public void onBackPressed()
	{
		// 另一种方法 成员变量保存状态
		// if (isSearchViewShow)
		// {
		// mSearchView.setIconified(true);
		//// isSearchViewShow = false;
		// return;
		// }
		
		/*解决Bug
		 *  如果侧滑菜单处于打开状态，则关闭菜单 ,同时不关闭Activity
		 */ 
		if (mDrawerLayout.isDrawerOpen(mDrawer))
		{
			mDrawerLayout.closeDrawer(mDrawer);
			return;
		}
/*		boolean hasHidden = hideSearchView();
		if (hasHidden)  // 如果有隐藏  
		{
			return;
		}*/
		if (hideSearchView())
		{
			return;
		}
		super.onBackPressed();
	}

	private boolean hideSearchView()
	{
		// 如果Search显示，择关闭SearchView， 不要关闭Activity
		if (!mSearchView.isIconified())
		{
			mDrawerToggle.setDrawerIndicatorEnabled(true); // 修改DrawerToggle的属性
															// 显示出来
			// SearchView隐藏时，Fragment显示
			getSupportFragmentManager().beginTransaction().show(mFragment).commit();
			mSearchView.setIconified(true);
			return true;
		}else
		{
			return false; // SearchView隐藏
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		/*
		 * The action bar home/up action should open or close the drawer.
		 * mDrawerToggle will take care of this.
		 */
		if (mSearchView.isIconified()) // 当SearchView影藏时  才执行mDrawerToggle点击事件
		{
			if (mDrawerToggle.onOptionsItemSelected(item))
			{
				return true;
			}
			
		}
		switch (item.getItemId())
		{
		case R.id.action_only_chinese:
			Toast.makeText(this, "中文阅读", Toast.LENGTH_LONG).show();
			return true;
		case android.R.id.home:
			// Toast.makeText(this, "back home", Toast.LENGTH_LONG).show();
			// 按Back键恢复mDrawerToggle
			// onBackPressed();
			/* 解决Bug
			 *  将onBackPressed()方法，改为调用hideSearchView()
			 */ 
			hideSearchView();
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	long preTime = 0;
	private RelativeLayout footV_layout_nightModel;
	private TextView tv_drawer_footV_nightModel;
	
	// 双击退出
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && 
				event.getAction() == KeyEvent.ACTION_DOWN)
		{
			if (System.currentTimeMillis() - preTime < 2000) // 在两秒内，退出
			{
				finish();
			}else
			Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
			// System.exit(0);
			preTime = System.currentTimeMillis(); // 将第一次点击的时间保存 
		}
		return true;// 拦截系统设置
	}

	@Override
	public void onClick(View v)
	{  // TODO
		int id = v.getId();
		switch (id)
		{
		case R.id.footV_layout_settings: //相关设置
			startActivity(new Intent(this, SettinsActivity.class));
			// 第一个参数是当前activity退出时的动画，第二个参数则是要跳转的activity进入时动画
			overridePendingTransition(R.anim.activity_open_enter,R.anim.activity_close_exit);
			break;
		case R.id.footV_layout_offline_download: // 离线下载
			startActivity(new Intent(this, OffLineReadActivity.class));
			overridePendingTransition(R.anim.activity_open_enter,R.anim.activity_close_exit);
			break;
		case R.id.footV_layout_nightModel: // 夜间日间模式切换
			setNightModel();
			break;
		case R.id.left_login_bar: // 跳转登陆页面
			startActivity(new Intent(this, LoginActivity.class));
			break;

		default:
			break;
		}
	}
	private void setNightModel()
	{
		SharedPreferences sp = getSharedPreferences(TKConstants.SP_THEME_TOGGLE, 0);
		isNight = sp.getBoolean("isNight", false);
		sp.edit().putBoolean("isNight", !isNight).commit();
		Intent intent = new Intent(MainActivity.this, MainActivity.class);
		startActivity(intent );
		finish();
		overridePendingTransition(0,0); // 关闭动画效果
	}
}
