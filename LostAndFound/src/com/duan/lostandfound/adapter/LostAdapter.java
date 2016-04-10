package com.duan.lostandfound.adapter;

import java.util.List;

import com.duan.lostandfound.R;
import com.duan.lostandfound.dto.Lost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LostAdapter extends BaseAdapter {

	List<Lost> dataLostList;// 将要显示的数据
	private Context context;

	public LostAdapter(Context context, List<Lost> dataLostList) {
		this.context = context;
		this.dataLostList = dataLostList;
	}

	@Override
	public int getCount() {
		return dataLostList == null ? 0 : dataLostList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataLostList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		System.out.println("进入getView()");
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_lost_list, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView
					.findViewById(R.id.tv_item_lost_title);
			holder.name = (TextView) convertView
					.findViewById(R.id.tv_item_lost_name);
			holder.telephone = (TextView) convertView
					.findViewById(R.id.tv_item_lost_telephone);
			holder.content = (TextView) convertView
					.findViewById(R.id.tv_item_lost_content);
			holder.time = (TextView) convertView
					.findViewById(R.id.tv_item_lost_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Lost lost = dataLostList.get(position);
		holder.title.setText(lost.getLosttitle());
		holder.name.setText(lost.getUsername());
		holder.content.setText(lost.getLostcontent());
		holder.telephone.setText(lost.getUsertelephone());
		holder.time.setText(lost.getLosttime());
		return convertView;
	}

	class ViewHolder {
		TextView name;
		TextView title;
		TextView content;
		TextView telephone;
		TextView time;
	}
}
