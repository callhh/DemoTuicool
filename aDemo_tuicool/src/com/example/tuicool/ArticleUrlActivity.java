package com.example.tuicool;

import com.activeandroid.util.Log;
import com.android.volley.VolleyError;
import com.example.tuicool.R;
import com.example.util.APIClient;
import com.example.util.TKConstants;
import com.example.util.ThemeToggleUtils;
import com.xinbo.utils.VolleyListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class ArticleUrlActivity extends AppCompatActivity
{

	private WebView article_url_webview;
	private ProgressBar article_url_bar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式 切换
		ThemeToggleUtils.setThemeToggle(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_url);
		initUI();
		initData();
	}
	
	private void initUI()
	{
		article_url_bar = (ProgressBar) findViewById(R.id.article_url_bar);
		initToolbar();
		initWebView();		
	}

	public void initWebView()
	{
		article_url_webview = (WebView) findViewById(R.id.article_url_webview);
		WebSettings settings = article_url_webview.getSettings();
		settings.setJavaScriptCanOpenWindowsAutomatically(true); // 支持JavaScript
		// settings.setTextSize(TextSize.LARGER); // 枚举方式 设置webView字体大小

		// 2) 防止跳出到其他浏览器
		article_url_webview.setWebChromeClient(new WebChromeClient()
		{

			@Override
			public void onProgressChanged(WebView view, int newProgress)
			{
				// 监控 浏览器下载htm进度
				article_url_bar.setProgress(newProgress);
				super.onProgressChanged(view, newProgress);
			}
		});
		article_url_webview.setWebViewClient(new WebViewClient()
		{
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				// 监控网址变化
				// Log.e("WebViewClient", "UrlLoading" + url);
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}

	private void initData()
	{
		// 获得热门详情页的数据
		APIClient.getHotDetail(this, new VolleyListener()
		{

			@Override
			public void onResponse(String arg0)
			{
				// JSON解析
				//HotDetail hotDetail = GsonUtils.parseJSON(arg0, HotDetail.class);
				//article_url_webview.loadDataWithBaseURL(null, hotDetail.getArticle().getUrl(), "text/html", "utf-8",null);
				Intent intent = getIntent();
				String detailUrl = intent.getStringExtra(TKConstants.intentKey.DETAIL_URL);
				article_url_webview.loadUrl(detailUrl);
						
			}

			@Override
			public void onErrorResponse(VolleyError arg0)
			{
				Log.e("VolleyListener", "Error" + arg0.getMessage());
			}
		});
	}

//	// goBack()表示返回webView的上一页面
//	public boolean onKeyDown(int keyCoder, KeyEvent event)
//	{
//		if (article_url_webview.canGoBack() && keyCoder == KeyEvent.KEYCODE_BACK)
//		{
//			article_url_webview.goBack();
//			return true;
//		}
//		return false;
//	}
	private void initToolbar()
	{
		// 在getSupportActionBar()方法之前调用
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // 设置显示返回键
		// 设置标题
		actionBar.setTitle("");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.article_url, menu);
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
			finish(); 
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
