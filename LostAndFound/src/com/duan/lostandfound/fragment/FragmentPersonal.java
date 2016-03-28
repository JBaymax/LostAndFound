package com.duan.lostandfound.fragment;

import com.duan.lostandfound.R;
import com.duan.lostandfound.utils.ActionSheet;
import com.duan.lostandfound.utils.ActionSheet.MenuItemClickListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 个人信息
 * 
 * @author Duan
 * 
 */
public class FragmentPersonal extends Fragment implements OnClickListener,
		MenuItemClickListener {
	private RelativeLayout relativeHead, relativeName, relativeTelephone,
			relativeSex, relativePassword;

	/**
	 * 获取整个布局
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_personal, null);
		initView(view);
		initEvent();
		return view;
	}

	private void initView(View view) {
		relativeHead = (RelativeLayout) view
				.findViewById(R.id.relative_personal_about_head);
		relativeName = (RelativeLayout) view
				.findViewById(R.id.relative_personal_about_name);
		relativeTelephone = (RelativeLayout) view
				.findViewById(R.id.relative_personal_about_telephone);
		relativeSex = (RelativeLayout) view
				.findViewById(R.id.relative_personal_about_sex);
		relativePassword = (RelativeLayout) view
				.findViewById(R.id.relative_personal_safety_password);

	}

	private void initEvent() {
		relativeHead.setOnClickListener(this);
		relativeName.setOnClickListener(this);
		relativeTelephone.setOnClickListener(this);
		relativeSex.setOnClickListener(this);
		relativePassword.setOnClickListener(this);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	public void showActionSheet() {
		ActionSheet menuView = new ActionSheet(getActivity());
		menuView.setCancelButtonTitle("cancel");// before add items
		menuView.addItems("Item1", "Item2", "Item3", "Item4");
		menuView.setItemClickListener(this);
		menuView.setCancelableOnTouchMenuOutside(true);
		menuView.showMenu();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.relative_personal_about_head:

			break;
		case R.id.relative_personal_about_name:

			break;
		case R.id.relative_personal_about_telephone:
			Toast.makeText(getActivity(), "电话被点击", Toast.LENGTH_LONG).show();
			break;
		case R.id.relative_personal_about_sex:
			getActivity().setTheme(R.style.ActionSheetStyle);
			System.out.println("性别--->");
			showActionSheet();
			break;
		case R.id.relative_personal_safety_password:

			break;
		default:
			break;
		}

	}

	@Override
	public void onItemClick(int itemPosition) {
		Toast.makeText(getActivity(), (itemPosition + 1) + " click", 0).show();

	}

}
