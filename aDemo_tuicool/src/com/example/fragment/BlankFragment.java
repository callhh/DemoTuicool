package com.example.fragment;

import com.example.tuicool.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class BlankFragment extends Fragment
{

	private int position;

	public BlankFragment(int position)
	{
		Log.e("构造方法", "position =" + position);
		this.position = position;
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View layout = inflater.inflate(R.layout.fragment_blank, container, false);
		TextView textView = (TextView) layout.findViewById(R.id.textView1);
		textView.setText("position "+ position);
		
		return layout;
	}

}
