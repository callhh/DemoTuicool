package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.util.Log;
import com.android.volley.VolleyError;
import com.example.model.topic.Item;
import com.example.model.topic.Topic;
import com.example.tuicool.R;
import com.example.util.APIClient;
import com.example.util.JsonHelper;
import com.example.util.ThemeToggleUtils;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.UILUtils;
import com.xinbo.utils.VolleyListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TopicFragment extends Fragment {

	/*public TopicFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_topic, container, false);
	}*/

	private List<Item> mData = new ArrayList<Item>();
	private View layout;
	private LayoutInflater mInflater;
	private myAdapter adapter;
	private int position;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 设置夜间模式  切换
		ThemeToggleUtils.setThemeToggle(getContext());
		initUI(inflater);
		initData();
		return layout;
	}

	public TopicFragment() {

	}

	public TopicFragment(int position) {
		this.position = position;

	}


	private void initData() {
		APIClient.getTopic(getContext(), new VolleyListener() {

			@Override
			public void onResponse(String arg0) {
				Topic topic = JsonHelper.fromExposeJson(arg0, Topic.class);
				List<Item> items = topic.getItems();
				mData.addAll(items);
				// data变化后通知更新
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {
				Log.e("onErrorResponse", "error: " + arg0.getMessage());
			}
		});
	}


	public void initUI(LayoutInflater inflater) {
		this.mInflater = inflater;
		if (layout == null) {
			layout = inflater.inflate(R.layout.fragment_topic, null);
			ListView mlistview = (ListView) layout.findViewById(R.id.listView1);
			adapter = new myAdapter();
			mlistview.setAdapter(adapter);
		}
	}

	class myAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View layout = convertView;
			ViewHolder holder = null;
			if (layout == null) {
				layout = mInflater.inflate(R.layout.listitem_image_topic, null);
				holder = new ViewHolder();
				holder.tv_Topic_texttitle = (TextView) layout.findViewById(R.id.tv_Topic_texttitle);
				holder.image_topic = (ImageView) layout.findViewById(R.id.image_topic);
				layout.setTag(holder);
			}
			else {
				holder = (ViewHolder) layout.getTag();
			}
			 Item item = mData.get(position);
			holder.tv_Topic_texttitle.setText(item.getName());
			UILUtils.displayImage(item.getImage(), holder.image_topic);
			return layout;
		}

	}

	class ViewHolder {
		private TextView tv_Topic_texttitle;
		private ImageView image_topic;
	}

}
