package com.duan.lostandfound.adapter;

import java.util.List;

import com.duan.lostandfound.R;
import com.duan.lostandfound.dto.Found;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FoundAdapter extends BaseAdapter {
	List<Found> dataFoundList;// 将要显示的数据
	private Context context;

	public FoundAdapter(Context context, List<Found> dataFoundList) {
		this.context = context;
		this.dataFoundList = dataFoundList;
	}

	@Override
	public int getCount() {
		return dataFoundList == null ? 0 : dataFoundList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataFoundList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_found_list, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView
					.findViewById(R.id.tv_item_found_title);
			holder.name = (TextView) convertView
					.findViewById(R.id.tv_item_found_name);
			holder.telephone = (TextView) convertView
					.findViewById(R.id.tv_item_found_telephone);
			holder.content = (TextView) convertView
					.findViewById(R.id.tv_item_found_content);
			holder.time = (TextView) convertView
					.findViewById(R.id.tv_item_found_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Found found = dataFoundList.get(position);
		holder.title.setText(found.getFoundtitle());
		holder.name.setText(found.getUsername());
		holder.content.setText(found.getFoundcontent());
		holder.telephone.setText(found.getUsertelephone());
		holder.time.setText(found.getFoundtime());
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
