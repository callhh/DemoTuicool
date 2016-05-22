package com.example.tuicool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.example.tuicool.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;

public class UpDataActivity extends Activity
{
	//模拟从服务器中获得的新的版本是2
		int newversionCode =2;
		private int NOTIFY_ID = 1;
		// 文件保存路径
		// sd卡保存路径
		String saveSDFile =Environment.getExternalStorageDirectory() + "/DownloadApk";
		String fileName ="kugouplay.apk";
		String apkURL ="http://192.168.56.1:1230/kugouplay1.apk";
		private Notification notification;
		private NotificationManager manager;
		private int versionCode;
		private AnimationDrawable animationDrawable; 
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_up_data);
			ImageView img_fragment_animation = (ImageView) findViewById(R.id.img_fragment_animation);
			
			animationDrawable = (AnimationDrawable) img_fragment_animation.getDrawable();
			animationDrawable.start(); // 播放动画
		}

		public void btnUpData(View v)
		{
			// 是否需要更新 要判断当前版本号是否最新== 比较版本号
			try
			{
				// 获得包管理器
				PackageManager packageManager = getPackageManager();
				// 获得包的信息
				PackageInfo info = packageManager.getPackageInfo(getPackageCodePath(), 0);
				// 获得当前版本号
				versionCode = info.versionCode;
				String versionName = info.versionName; // 获得当前版本名
				
			} catch (NameNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 判断  如果当前版本小于最新版本 就弹出更新提示
			if (versionCode < newversionCode)
			{
				showUpDataDialog(); // 弹出更新的对话框提示
			}
		}
		// 弹出更新的对话框提示
		private void showUpDataDialog()
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("更新提示")
			.setMessage("1,更新了XXXXBUG \n2,增加了XXXX功能")
			.setNegativeButton("稍后更新", new OnClickListener()
			{
				
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
				}
			})
			.setPositiveButton("立即更新", new OnClickListener()
			{
				
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// 发送通知
					sendNotification();
					////下载apk文件
					new APKTask().execute(apkURL);
				}
			});
			builder.show(); // 弹出对话框提示
		}
		
		// 弹出文件下载 状态栏通知
		protected void sendNotification()
		{
			manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
			
			RemoteViews views = new RemoteViews(getPackageName(), R.layout.notify_updata_layout);
			Intent intent1 = new Intent(this, MainActivity.class);
			PendingIntent intent = PendingIntent.getActivity(this, 0, intent1 , 0);
			
			builder.setAutoCancel(true) //设置点击可取消
//			.setOngoing(true) //设置点击不可取消
			.setTicker("开始下载文件") // 设置上升动画的文本提示信息
			.setSmallIcon(R.drawable.ic_download) //设置上升动画的图标
//			.setSound(Uri.parse("")) // 设置上升动画的声音提示
//			.setVibrate(new long[]{11,22,11,22}) // 设置振动  要加权限
			.setContent(views) // 设置关联布局
			.setContentIntent(intent); //设置延时意图
			
			notification = builder.build();
			// 发送通知
			manager.notify(NOTIFY_ID, notification );
			
			
		}
		
//		apk 异步下载
		class APKTask extends AsyncTask<String, Integer, String>
		{

			private FileOutputStream fos;

			@Override
			protected String doInBackground(String... params)
			{
				String str= "";
				try
				{
					// 获得URL对象
					URL url = new URL(params[0]);
					// 打开连接
					URLConnection connection = url.openConnection();
					// 获得文件的总长度
					int length = connection.getContentLength();
					// 获得输入流
					InputStream is = connection.getInputStream();
					File path = new File(saveSDFile);
					// 判断当前是否有文件夹 
					if (!path.exists()) // 如果没有文件就创建一个文件夹
					{
						path.mkdir(); // 不存在就创建一个文件夹
					}
					File file = new File(saveSDFile + fileName);
					fos = new FileOutputStream(file);
					int len =0;
					byte[] buffer = new byte[1024];
					int dlLength =0;
					int node=5120;//下载节点5kb
					int nodeNum=0;//节点次数
					while( -1 != (len = is.read(buffer )))
					{
						
						dlLength+=len;
						fos.write(buffer, 0, len);
						if (dlLength > node*(nodeNum))
						{
							nodeNum++;
							// 推送更新进度
							publishProgress(dlLength*100/length);
						}
					}
					// 如果下载的长度等于文件的总长度
					if (dlLength == length)
					{
						publishProgress(100);
					}
					str = "下载成功";
				} catch (MalformedURLException e)
				{
					str = "下载失败";
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e)
				{
					str = "下载失败";
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if (fos != null)
					{
						try
						{
							fos.close();
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				return str;
			}
			
			// 接收 推送进度更新的方法  更新控件显示
			@Override
			protected void onProgressUpdate(Integer... values)
			{
				// 在通知上更新进度
				// 设置文本内容
				notification.contentView.setTextViewText(R.id.textView1, "已下载"+ values[0] + "%");
				// 设置进度
				notification.contentView.setProgressBar(R.id.progressBar1, 100,// 设置progressBar1总进度
						values[0], // 设置当前进度
						false); // 是否显示进度
				
				// 改变通知的内容 ，要更新通知
				manager.notify(NOTIFY_ID, notification);
				
			super.onProgressUpdate(values);
			}
			
			@Override
			protected void onPostExecute(String result)
			{
				if (result.equals("下载成功"))
				{
					Intent intent = new Intent(Intent.ACTION_VIEW);
					//指定文件的路径，以及文件类型 mimeType:APK
					Uri data = Uri.fromFile(new File(saveSDFile + fileName));
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setDataAndType(data,"application/vnd.android.package-archive" );
//					startActivity(intent ); // 直接跳转安装界面
					
					//点击状态栏通知跳转安装界面
					PendingIntent contentIntent = PendingIntent.getActivity(UpDataActivity.this, 0, intent, 0);
					notification.contentIntent =contentIntent;
					manager.notify(NOTIFY_ID, notification);
				}
				
			super.onPostExecute(result);
			}
			
		}
}
