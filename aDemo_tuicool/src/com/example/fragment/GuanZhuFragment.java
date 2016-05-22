package com.example.fragment;

import com.example.tuicool.R;
import com.example.util.ThemeToggleUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class GuanZhuFragment extends Fragment {

	public GuanZhuFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		// 设置夜间模式  切换
		ThemeToggleUtils.setThemeToggle(getContext());
		return inflater.inflate(R.layout.fragment_guan_zhu, container, false);
	}

}
