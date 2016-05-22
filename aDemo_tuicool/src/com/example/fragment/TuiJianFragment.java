package com.example.fragment;

import com.example.tuicool.LoginActivity;
import com.example.tuicool.R;
import com.example.util.ThemeToggleUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TuiJianFragment extends Fragment implements OnClickListener {

	public TuiJianFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 设置夜间模式  切换
				ThemeToggleUtils.setThemeToggle(getContext());
		View layout = inflater.inflate(R.layout.fragment_tuijian, container, false);
		Button btn_tuijian_login = (Button) layout.findViewById(R.id.btn_fragment_tuijian_login);
		btn_tuijian_login.setOnClickListener(this);
		return layout;
	}

	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		switch (id)
		{
		case R.id.btn_fragment_tuijian_login:
			Intent intent = new Intent(getContext(), LoginActivity.class);
			getContext().startActivity(intent );
			break;

		default:
			break;
		}
	}
	
	
	

}
