package com.duan.lostandfound.fragment;

import com.duan.lostandfound.R;
import com.duan.lostandfound.activity.ModifyNameActivity;
import com.duan.lostandfound.finaldata.FinalData;
import com.duan.lostandfound.utils.ActionSheet;
import com.duan.lostandfound.utils.ActionSheet.MenuItemClickListener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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
	private Button buttonExit;

	String telephone;
	String password;

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
		buttonExit = (Button) view.findViewById(R.id.btn_setting_quit);

	}

	private void initEvent() {
		relativeHead.setOnClickListener(this);
		relativeName.setOnClickListener(this);
		relativeTelephone.setOnClickListener(this);
		relativeSex.setOnClickListener(this);
		relativePassword.setOnClickListener(this);
		buttonExit.setOnClickListener(this);

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
			Intent nameIntent = new Intent(getActivity(),
					ModifyNameActivity.class);
			startActivity(nameIntent);
			getActivity().finish();
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
		case R.id.btn_setting_quit:
			// 弹出提示框，确定退出
			showDialog();
			break;
		default:
			break;
		}

	}

	@Override
	public void onItemClick(int itemPosition) {

		Toast.makeText(getActivity(), (itemPosition + 1) + " click", 0).show();

	}

	private void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("是否确定退出？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// 实现退出
				quit();
			}

		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	/**
	 * 退出的方法
	 */
	public void quit() {
		SharedPreferences sharedPreferences = getActivity()
				.getSharedPreferences(FinalData.CONFIG_FILE_NAME,
						Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();// 获取编辑器
		editor.putString("telephone", "0");
		editor.putString("password", "0");
		editor.commit();// 提交修改;
		getActivity().finish();
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(startMain);
		System.exit(0);
	}
}
