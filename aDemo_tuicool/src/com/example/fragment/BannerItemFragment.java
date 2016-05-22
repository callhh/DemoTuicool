package com.example.fragment;

import com.example.tuicool.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class BannerItemFragment extends Fragment
{

	private int position;
	private int[] mImagRes = new int[]{R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4};
	
	public BannerItemFragment(int position)
	{
		Log.e("构造方法", "position =" + position);
		this.position = position;
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View layout = inflater.inflate(R.layout.fragment_banner_item, container, false);
		ImageView mImageView = (ImageView) layout.findViewById(R.id.img_banner);
		position %= mImagRes.length; //图片的数组长度 循环
		mImageView.setImageResource(mImagRes[position]);
		mImageView.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				
				Log.e("onCreateView", "position: " + position);
			}
		});
		return layout;
	}

	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e("onDestroyView", "position: " + position);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("onDestroy", "position: " + position);
	}
}
