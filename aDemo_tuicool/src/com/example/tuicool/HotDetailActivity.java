package com.example.tuicool;

import java.text.SimpleDateFormat;

import com.android.volley.VolleyError;
import com.example.database_utils.DBUtils;
import com.example.model.Article;
import com.example.model.detail.HotDetail;
import com.example.util.APIClient;
import com.example.util.JsonHelper;
import com.example.util.PreferenceUtils;
import com.example.util.TKConstants;
import com.example.util.ThemeToggleUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.demo.AccessTokenKeeper;
import com.sina.weibo.sdk.demo.Constants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class HotDetailActivity extends SwipeBackActivity
{

	private WebView mWebView;
	private WebSettings settings;
	private HotDetail hotDetail;
	private Article mArticle;
	private MenuItem menuItemFavor;
	private String[] textSize = new String[]
	{ "最小", "偏小", "中等", "偏大", "最大" };

	/** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能 */
	private Oauth2AccessToken mAccessToken;
	/** 注意：SsoHandler 仅当 SDK 支持 SSO 时有效 */
	private SsoHandler mSsoHandler;
	private AuthInfo mAuthInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// 设置夜间模式 切换
		ThemeToggleUtils.setThemeToggle(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hot_detail);
		//滑动关闭Activity 四种模式 SwipeBackLayout.EDGE_LEFT ; .EDGE_RIGHT ; .EDGE_BOTTOM ; .EDGE_ALL
		SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT); //从左向右滑动关闭
		initUI();
		initData();
		initWeiboSDK();
	}

	@Override
	protected void onStart()
	{
		// int fontSize = sp.getInt(TKConstants.fontSizeKey.DETAIL_FONT_SIZE,
		// 2);
		int fontSize = PreferenceUtils.readSize(this); // 初始化   读取设置字体大小的数据
		// if (fontSize < 0)
		// {
		// builder.setTitle("字体大小(当前是" + textSize[2] + "):");
		// }else
		// {
		// builder.setTitle("字体大小(当前是" + textSize[fontSize] + "):");
		// }
		switch (fontSize)
		{
		case 0:
			settings.setTextSize(TextSize.SMALLEST);
			break;
		case 1:
			settings.setTextSize(TextSize.SMALLER);
			break;
		case 2:
			settings.setTextSize(TextSize.NORMAL);
			break;
		case 3:
			settings.setTextSize(TextSize.LARGER);
			break;
		case 4:
			settings.setTextSize(TextSize.LARGEST);
			break;

		default:
			break;
		}
		super.onStart();
	}

	private void initData()
	{
		Intent intent = getIntent();
		mArticle = (Article) intent.getSerializableExtra(TKConstants.intentKey.ARTICLE_FAVOR);
		// 获得热门详情页的数据
		APIClient.getHotDetail(this, new VolleyListener()
		{

			@Override
			public void onResponse(String arg0)
			{
				hotDetail = JsonHelper.fromExposeJson(arg0, HotDetail.class);
				mWebView.loadDataWithBaseURL(null, hotDetail.getArticle().getContent(), "text/html", "utf-8", null);
				// 设置图片自适应浏览器大小 4.4版本之前有效
				mWebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
			}

			@Override
			public void onErrorResponse(VolleyError arg0)
			{
				Log.e("VolleyListener", "Error" + arg0.getMessage());
			}
		});
	}

	private void initUI()
	{
		initToolbar();
		initWebView();
	}

	private void initWebView()
	{
		mWebView = (WebView) findViewById(R.id.detail_WebView);
		settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setTextSize(TextSize.NORMAL); // 设置字体大小 --->中等
		// 防止跳出到其他浏览器
		mWebView.setWebChromeClient(new WebChromeClient()
		{
			public void onProgressChanged(WebView view, int newProgress)
			{
				// TODO 监控浏览器下载htm进度
			}
		});
		mWebView.setWebViewClient(new WebViewClient()
		{
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				// 监控网址变化
				Log.e("WebViewClient", "UrlLoading " + url);
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}

	private void initToolbar()
	{
		// 在getSupportActionBar()方法之前调用
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true); // 设置显示返回键
		// 设置标题
		actionBar.setTitle("详情");
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hot_detail, menu);
		return true;
	}

	@Override // 重写方法onPrepareOptionsMenu()，找到收藏菜单对象
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		// 如果菜单xml配置文件使用group
		// 通过索引获得"添加菜单"项对象
		// if (menuItemLike == null) {
		// menuItemLike = menu.getItem(3);
		// }

		// 如果菜单xml配置文件使用submenu
		MenuItem item = menu.getItem(2); // 获得Actionbar菜单下标
		SubMenu subMenu = item.getSubMenu();
		menuItemFavor = subMenu.getItem(1); // 通过索引 获得“添加菜单”对象
		// 如果查询对象非空 已收藏
		boolean hasFavor = DBUtils.hasFavor("article_id=?", mArticle);
		if (hasFavor)
		{
			menuItemFavor.setTitle("取消收藏"); // 菜单文字改为"取消收藏"
		}
		return super.onPrepareOptionsMenu(menu);
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
		case R.id.action_share: // 简单分享(参考SupportV4的分享例子)
//			ShareCompat.IntentBuilder b = ShareCompat.IntentBuilder.from(this);
//			b.setType("text/plain").setText("I'm sharing!").startChooser();
			
			showShare(); // 分享(参考mob例子)
			return true;
		case R.id.action_comment: // 评论
			return true;
		case R.id.action_add_read_wait: // 待读
			return true;
		case R.id.action_add_favor: // 收藏
			// 如果查询对象非空 已收藏
			boolean hasFavor = DBUtils.hasFavor("article_id=?", mArticle);
			if (hasFavor)
			{
				DBUtils.delete("article_id=?", mArticle); // 取消收藏
				menuItemFavor.setTitle("添加收藏");
				Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
			} else
			{
				DBUtils.insert(mArticle); // 将插入到数据库
				menuItemFavor.setTitle("取消收藏"); // 将菜单文字 改为“取消收藏”
				Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show(); // 通知提示
			}
			break;
		case R.id.action_set_size: // 调整字号
			// Log.e("onCreateOptionsMenu", "调整字号");
			showSetTextSizeDialog();
			return true;
		case R.id.action_bg_setting: // 背景设置
			return true;
		case R.id.action_open_next: // 打开翻页
			return true;
		case R.id.action_look_original: // 查看原文
			// 跳转原文的隐式意图
			// Uri uri =
			// Uri.parse("http://tech.qq.com/a/20160317/019951.htm?utm_source=tuicool&utm_medium=referral");
			// Intent intent = new Intent(Intent.ACTION_VIEW, uri );

			Intent intent = new Intent(this, ArticleUrlActivity.class);
			intent.putExtra(TKConstants.intentKey.DETAIL_URL, hotDetail.getArticle().getUrl());
			startActivity(intent);
			return true;
		case R.id.action_error_article: // 文章纠错
			return true;

		case android.R.id.home: // 返回键监听
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private AlertDialog.Builder builder;

	private void showSetTextSizeDialog()
	{
		builder = new AlertDialog.Builder(new ContextThemeWrapper(HotDetailActivity.this, R.style.AlertDialogCustom));

		// builder.setTitle(R.string.select_dialog);
		int readSize = PreferenceUtils.readSize(HotDetailActivity.this);
		if (readSize < 0)
		{
			builder.setTitle("字体大小(当前是" + textSize[2] + "):");
		} else
		{
			builder.setTitle("字体大小(当前是" + textSize[readSize] + "):");
		}
		builder.setItems(R.array.select_dialog_items, new DialogInterface.OnClickListener()
		{

			public void onClick(DialogInterface dialog, int position)
			{
				switch (position)
				{
				case 0:
					settings.setTextSize(TextSize.SMALLEST);
					break;
				case 1:
					settings.setTextSize(TextSize.SMALLER);
					break;
				case 2:
					settings.setTextSize(TextSize.NORMAL);
					break;
				case 3:
					settings.setTextSize(TextSize.LARGER);
					break;
				case 4:
					settings.setTextSize(TextSize.LARGEST);
					break;

				default:
					break;
				}
				// textShow = "字体大小(当前是" + textSize[position] + "):";
				PreferenceUtils.saveSize(HotDetailActivity.this, position);
			}
		});

		builder.show();
	}

	private void showShare() {
		 ShareSDK.initSDK(this);
		 OnekeyShare oks = new OnekeyShare();
		 //关闭sso授权
		 oks.disableSSOWhenAuthorize(); 

		// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
		 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		 oks.setTitle(getString(R.string.share));
		 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		 oks.setTitleUrl("http://sharesdk.cn");
		 // text是分享文本，所有平台都需要这个字段
		 oks.setText("我是分享文本");
		 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		 //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		 // url仅在微信（包括好友和朋友圈）中使用
		 oks.setUrl("http://sharesdk.cn");
		 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
		 oks.setComment("我是测试评论文本");
		 // site是分享此内容的网站名称，仅在QQ空间使用
		 oks.setSite(getString(R.string.app_name));
		 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
		 oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		 oks.show(this);
		 }
	
	private void initWeiboSDK() {
		// 创建微博实例
		// mWeiboAuth = new WeiboAuth(this, Constants.APP_KEY,
		// Constants.REDIRECT_URL, Constants.SCOPE);
		// 快速授权时，请不要传入 SCOPE，否则可能会授权不成功
		mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
		mSsoHandler = new SsoHandler(this, mAuthInfo);
		

		// 从 SharedPreferences 中读取上次已保存好 AccessToken 等信息，
		// 第一次启动本应用，AccessToken 不可用
		mAccessToken = AccessTokenKeeper.readAccessToken(this);
		if (mAccessToken.isSessionValid()) {
			updateTokenView(true);
		}
	}

	/**
	 * 微博认证授权回调类。 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用
	 * {@link SsoHandler#authorizeCallBack} 后， 该回调才会被执行。 2. 非 SSO
	 * 授权时，当授权结束后，该回调就会被执行。 当授权成功后，请保存该 access_token、expires_in、uid 等信息到
	 * SharedPreferences 中。
	 */
	class AuthListener implements WeiboAuthListener {

		@Override
		public void onComplete(Bundle values) {
			// 从 Bundle 中解析 Token
			mAccessToken = Oauth2AccessToken.parseAccessToken(values);
			// 从这里获取用户输入的 电话号码信息
			String phoneNum = mAccessToken.getPhoneNum();
			if (mAccessToken.isSessionValid()) {
				// 显示 Token
				updateTokenView(false);

				// 保存 Token 到 SharedPreferences
				AccessTokenKeeper.writeAccessToken(HotDetailActivity.this, mAccessToken);
				Toast.makeText(HotDetailActivity.this, R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
			} else {
				// 以下几种情况，您会收到 Code：
				// 1. 当您未在平台上注册的应用程序的包名与签名时；
				// 2. 当您注册的应用程序包名与签名不正确时；
				// 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
				String code = values.getString("code");
				String message = getString(R.string.weibosdk_demo_toast_auth_failed);
				if (!TextUtils.isEmpty(code)) {
					message = message + "\nObtained the code: " + code;
				}
				Toast.makeText(HotDetailActivity.this, message, Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onCancel() {
			Toast.makeText(HotDetailActivity.this, R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(HotDetailActivity.this, "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 显示当前 Token 信息。
	 * 
	 * @param hasExisted
	 *            配置文件中是否已存在 token 信息并且合法
	 */
	private void updateTokenView(boolean hasExisted) {
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.format(new java.util.Date(mAccessToken.getExpiresTime()));
		String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
//		mTokenText.setText(String.format(format, mAccessToken.getToken(), date));

		String message = String.format(format, mAccessToken.getToken(), date);
		if (hasExisted) {
			message = getString(R.string.weibosdk_demo_token_has_existed) + "\n" + message;
		}
//		mTokenText.setText(message);
	}


	/**
	 * 当 SSO 授权 Activity 退出时，该函数被调用。
	 * 
	 * @see {@link Activity#onActivityResult}
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// SSO 授权回调
		// 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
		if (mSsoHandler != null) {
			mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
		}

	}
	
}
