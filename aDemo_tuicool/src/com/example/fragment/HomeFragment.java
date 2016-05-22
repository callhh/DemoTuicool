package com.example.fragment;

import com.astuetz.PagerSlidingTabStrip;
import com.example.tuicool.R;
import com.example.util.ThemeToggleUtils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeFragment extends Fragment
{
	private final String[] TITLES = { "热门", "推荐", "科技", "创业", 
			"数码", "技术","设计","营销" };
	private ViewPager mPager;
	private PagerSlidingTabStrip tabs;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// 设置夜间模式  切换
		ThemeToggleUtils.setThemeToggle(getContext());
		View layout = inflater.inflate(R.layout.fragment_home, container, false);
			mPager = (ViewPager) layout.findViewById(R.id.pager);
//			tabs = (PagerSlidingTabStrip) layout.findViewById(R.id.tabs);
			// 修改pager默认缓存页数
			// mPager.setOffscreenPageLimit(3);
			
			// 注意：Fragment嵌套Fragment，必须使用childFragmentMananger
			// 不能使用getSupportFragmentManager()
			FragmentManager fm = getChildFragmentManager();
			mPager.setAdapter(new FragmentPagerAdapter(fm)
			{
				@Override
				public CharSequence getPageTitle(int position) {
					if (position == 0)
					{
						return TITLES[0];
					}
					return TITLES[position];
				}
				@Override
				public int getCount()
				{
					return TITLES.length;
				}
				
				@Override
				public Fragment getItem(int position)
				{
					// 根据水平滑动标签 下标  判断跳转哪个片段页面
					switch (position)
					{
					case 0:
						return new HotFragment(position);
					case 1:
						return new TuiJianFragment();
					case 2:
						return new HotFragment(position);
					case 3:
						return new HotFragment(position);
					case 4:
						return new HotFragment(position);
					case 5:
						// 根据position参数下标的不同    请求不同页面   复用
						return new HotFragment(position);
					case 6:
						return new HotFragment(position);
					case 7:
						return new HotFragment(position);
					}
					
					return new BlankFragment(position);
				}
			});
			SmartTabLayout mSmartTabLayout = (SmartTabLayout) layout.findViewById(R.id.viewpagertab);
			mSmartTabLayout.setViewPager(mPager);
		
		return layout;
	}

}
