package com.example.tuicool;

import com.example.tuicool.R;
import com.example.util.ThemeToggleUtils;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式  切换
		ThemeToggleUtils.setThemeToggle(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		initUI();
	}
	private void initUI()
	{
		initToolbar();
		TextView tv_AboutUs_text_info = (TextView) findViewById(R.id.tv_aboutUs_text_info);
		tv_AboutUs_text_info.setText("　　推酷专注于IT领域的信息挖掘和聚合推荐，期望通过技术解决信息获取方面的诸多问题。 更长远地，推酷期望能成为IT知识整合社区，给你一站式阅读、学习、交流的平台。目前主推的产品有：\n1) 个性化文章聚合推荐，提供的内容涵盖科技、技术、设计、营销等方面。我们没有小编，聚合和推荐的内容完全依赖于分分钟不停歇的推荐系统。\n2) IT类的线下线上活动聚合推荐，目前收录的活动应该是国内最丰富的，但活动频道还有很长的路要走。\n3) IT类的公开课、活动演讲、节目视频等内容的聚合推荐。\n　　推酷起步于2011年，历经种种于2013年4月网站正式上线。 除了网站，我们还提供优质的安卓和iOS客户端方便你的阅读。\n　　其实，你现在看到的推酷和我们所规划的，还有着很长很长的道路要走。我们还是个小团队，如果你有创业热情并认可我们的愿景，欢迎加入我们。");
		
	}

	private void initToolbar()
	{
		// 在getSupportActionBar()方法之前调用
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // 设置显示返回键
		// 设置标题
		actionBar.setTitle("关于我们");
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
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.about_us, menu);
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
