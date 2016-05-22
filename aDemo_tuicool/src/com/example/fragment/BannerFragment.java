package com.example.fragment;

import com.example.tuicool.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class BannerFragment extends Fragment
{

	private static final int DILAY_MILLIS = 2500; // 轮播睡眠
	private static final int MAX_LENGTH = 400000; // 轮播无限
	private ViewPager mPager;
	private LayoutInflater mInflater;
	private View layout;
	protected boolean isDragging;
	private Runnable action;

	public BannerFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		initUI(inflater, container);
		return layout;
	}

	private void initUI(LayoutInflater inflater, ViewGroup container)
	{
		// 判断layout为空则为第一次创建，否则不要创建
		if (layout == null)
		{
			// 只需要执行一次的代码
			mInflater = inflater;
			layout = inflater.inflate(R.layout.fragment_banner, container, false);
			initListView();
		}
		// 每当 fragment被激活时都会执行  // 自动轮播
		autoScroll(); 
	}

	private void initListView()
	{
		ListView mListView = (ListView) layout.findViewById(R.id.listView1);
		// 初始化Banner
		View headerView = initBanner();
		mListView.addHeaderView(headerView, null, true);
		mListView.setAdapter(new MyAdapter());
	}

	private View initBanner()
	{
		View headerView = mInflater.inflate(R.layout.banner_headerview, null);
		mPager = (ViewPager) headerView.findViewById(R.id.pager);
		// 初始化时设置Pager显示中间页面
		mPager.setCurrentItem(MAX_LENGTH / 2);
		// 片段里面嵌套片段要调getChildFragmentManager()方法
		FragmentManager fm = getChildFragmentManager();
		// mPager.setOffscreenPageLimit(2); // 设置左右两边缓存的个数
		mPager.setAdapter(new BannerAdapter(fm));
		// 手动自动的冲突问题
		mPager.addOnPageChangeListener(new BannerPAageListener());
		
		return headerView;
	}

	private void autoScroll()
	{
		Log.e("autoScroll()", "开始滚动");
		action = new Runnable()
		{
			// 定时每2.5秒自动滑到下一页
			public void run()
			{
				// 定时任务一直持续
				mPager.postDelayed(this, DILAY_MILLIS); // 持续自动轮播
				if (!isDragging) // 没有拖拽滑块，自动下一页
				{
					int currentItem = mPager.getCurrentItem();
					int nextItem = currentItem + 1;
					mPager.setCurrentItem(nextItem);
				}
			}
		};
		mPager.postDelayed(action, DILAY_MILLIS);
	}

	@Override
	public void onDestroyView()
	{
		super.onDestroyView();
		// 多次滑动首页Pager ，Banner自动滚动速度变快
		mPager.removeCallbacks(action);
		Log.e("onDestroyView()", "停止滚动");
	}
	private final class BannerPAageListener implements OnPageChangeListener
	{
		@Override
		public void onPageSelected(int arg0)
		{
			// 在页面选择
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2)
		{
			// 在页面滚动
		}

		@Override
		public void onPageScrollStateChanged(int state)
		{
			switch (state)
			{
			case ViewPager.SCROLL_STATE_DRAGGING:
				Log.e("onPageScrollStateChanged", "用户拖拽");
				isDragging = true;
				break;
			case ViewPager.SCROLL_STATE_SETTLING:
				Log.e("onPageScrollStateChanged", "复位");
				isDragging = false;
				break;
			case ViewPager.SCROLL_STATE_IDLE:
				Log.e("onPageScrollStateChanged", "空闲");
				isDragging = false;
				break;

			default:
				break;
			}
		}
	}

	// Banner(ViewPager)性能优化
	// 当Banner的Item较多时，必须使用FragmentStatePagerAdapter
	private final class BannerAdapter extends FragmentStatePagerAdapter
	{
		private BannerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public int getCount()
		{
			// 无限循环 修改Pager适配器的getCount()返回400000
			return MAX_LENGTH;
		}

		@Override
		public Fragment getItem(int position)
		{
			return new HotFragment();
		}
	}

	class MyAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			return 10;
		}

		@Override
		public Object getItem(int position)
		{
			return null;
		}

		@Override
		public long getItemId(int position)
		{
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View layout = mInflater.inflate(R.layout.list_item, null);
			TextView textView = (TextView) layout.findViewById(R.id.textView1);
			textView.setText("position: " + position);
			return layout;
		}

	}

}
