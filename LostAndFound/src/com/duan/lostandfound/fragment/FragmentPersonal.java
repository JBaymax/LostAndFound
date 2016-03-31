package com.duan.lostandfound.fragment;

import org.json.JSONObject;

import com.duan.lostandfound.R;
import com.duan.lostandfound.activity.ModifyNameActivity;
import com.duan.lostandfound.activity.ModifyPasswordActivity;
import com.duan.lostandfound.activity.ModifyTelephoneActivity;
import com.duan.lostandfound.activity.ModifyNameActivity.ModifyNameMyAsyncTask;
import com.duan.lostandfound.analysis.AnalysisGetUsersInfoResponseParam;
import com.duan.lostandfound.dto.Users;
import com.duan.lostandfound.finaldata.FinalData;
import com.duan.lostandfound.finaldata.HttpClient;
import com.duan.lostandfound.finaldata.Request;
import com.duan.lostandfound.param.RequestParam;
import com.duan.lostandfound.utils.ActionSheet;
import com.duan.lostandfound.utils.ActionSheet.MenuItemClickListener;
import com.duan.lostandfound.utils.IntentCode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

	private TextView nameTextView;
	private TextView telephoneTextView;
	private TextView sexTextView;

	String telephone;
	String phone;
	String A;
	String B;
	String password;

	String name;
	String sex;
	int userId;
	Users currentUsers = null; //

	/**
	 * 获取整个布局
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_personal, null);
		initView(view);
		getUserInfo();
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
		nameTextView = (TextView) view
				.findViewById(R.id.tv_main_personal_about_name);
		telephoneTextView = (TextView) view
				.findViewById(R.id.tv_personal_about_telephone);
		sexTextView = (TextView) view.findViewById(R.id.tv_personal_about_sex);
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

	/**
	 * 从共享参数中得到手机号
	 */
	public void getUserInfo() {
		SharedPreferences preferences = getActivity().getSharedPreferences(
				FinalData.CONFIG_FILE_NAME, Activity.MODE_PRIVATE);
		name = preferences.getString("name", "0");// 读取到昵称
		phone = preferences.getString("telephone", "0");// 读取到的手机号
		sex = preferences.getString("sex", "男");
		System.out.println("读取手机号和名字--->" + name + phone);
		A = phone.substring(0, 3);// 前三位数
		B = phone.substring(phone.length() - 4, phone.length());// 后四位数
		telephone = A + "****" + B;

		telephoneTextView.setText(telephone);
		nameTextView.setText(name);
		sexTextView.setText(sex);
	}

	/**
	 * 数据的传输
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		SharedPreferences pref = getActivity().getSharedPreferences(
				FinalData.CONFIG_FILE_NAME, Activity.MODE_PRIVATE);
		Editor editor = pref.edit();
		if (data != null) {
			if (resultCode == IntentCode.MODIFY_NAME_INTENT_RESULT) {

				name = data.getExtras().getString("name");
				nameTextView.setText(name);

				System.out.println("-----onActivityResult.name--->" + name);
				editor.putString("name", name);

			} else if (resultCode == IntentCode.MODIFY_TELEPHONE_INTENT_RESULT) {

				phone = data.getExtras().getString("telephone");
				A = phone.substring(0, 3);// 前三位数
				B = phone.substring(phone.length() - 4, phone.length());// 后四位数
				telephone = A + "****" + B;
				telephoneTextView.setText(telephone);

				System.out.println("-----onActivityResult.telephone--->"
						+ phone);
				editor.putString("telephone", phone);

			}
			editor.commit(); // 提交
		}

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public void showActionSheet() {
		ActionSheet menuView = new ActionSheet(getActivity());
		menuView.setCancelButtonTitle("取消");// before add items
		menuView.addItems("请选择性别", "男", "女");
		menuView.setItemClickListener(this);
		menuView.setCancelableOnTouchMenuOutside(true);
		menuView.showMenu();

	}

	@Override
	public void onItemClick(int itemPosition) {
		SharedPreferences pref = getActivity().getSharedPreferences(
				FinalData.CONFIG_FILE_NAME, Activity.MODE_PRIVATE);
		Editor editor = pref.edit();
		userId = pref.getInt("id", 1234567890);
		RequestParam requestParams = new RequestParam();
		requestParams.setRequestType(RequestParam.MODIFYSEX);
		if (itemPosition == 1) {
			sexTextView.setText("男");
			sex = sexTextView.getText().toString();
			editor.putString("sex", sex);
			System.out.println("点击-男--->" + sex);
			editor.commit(); // 提交

		} else if (itemPosition == 2) {
			sexTextView.setText("女");
			sex = sexTextView.getText().toString();
			editor.putString("sex", sex);
			editor.commit(); // 提交
			System.out.println("点击-女--->" + sex);
		}
		// 传输过去的数据
		try {
			JSONObject[] params = new JSONObject[1];
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", userId);
			jsonObject.put("sex", sex);
			params[0] = jsonObject;
			requestParams.setParams(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ModifySexMyAsyncTask().execute(requestParams);

	}

	/**
	 * 改变昵称的异步通信任务
	 */
	public class ModifySexMyAsyncTask extends
			AsyncTask<RequestParam, Integer, Integer> {

		@Override
		protected Integer doInBackground(RequestParam... params) {
			RequestParam requestParams = params[0];

			System.out
					.println("---Duan:GetUsersInfoAsyncTask.doInBackground.requestParams--->"
							+ requestParams.getJSON());
			if (!HttpClient.isConnect(getActivity())) {
				System.out
						.println("---Duan:GetUsersInfoAsyncTask.doInBackground.!HttpClient.isConnect(getActivity())--->");
				return -1; // 表示请求失败
			}
			String response = Request.request(requestParams.getJSON());
			System.out
					.println("----Duan:GetUsersInfoAsyncTask.doInBackground.response--->"
							+ response);
			System.out
					.println("----Duan:GetUsersInfoAsyncTask.doInBackground.response2--->"
							+ Request.request(requestParams.getJSON()));
			AnalysisGetUsersInfoResponseParam alaysisResponse = new AnalysisGetUsersInfoResponseParam(
					response);
			if (alaysisResponse.getResult() != 0) {
				return alaysisResponse.getResult();
			}
			System.out
					.println("----Duan:GetUsersInfoAsyncTask.doInBackground.alaysisResponse.getCourseVideoInfo() --->"
							+ alaysisResponse.getUsersInfo());
			if (alaysisResponse.getUsersInfo() != null) {
				currentUsers = alaysisResponse.getUsersInfo();

				System.out.println("---Duan:currentUsers--->" + currentUsers);
				return 0;
			}

			return -1;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			switch (result) {
			case 0:
				Toast.makeText(getActivity(), "修改成功！", Toast.LENGTH_LONG)
						.show();

				break;
			case -1:
				Toast.makeText(getActivity(), "修改失败！", Toast.LENGTH_LONG)
						.show();
				break;
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.relative_personal_about_head:

			break;
		case R.id.relative_personal_about_name:
			Intent nameIntent = new Intent(getActivity(),
					ModifyNameActivity.class);
			startActivityForResult(nameIntent, 0);
			break;
		case R.id.relative_personal_about_telephone:
			Intent telephoneIntent = new Intent(getActivity(),
					ModifyTelephoneActivity.class);
			startActivityForResult(telephoneIntent, 0);
			break;
		case R.id.relative_personal_about_sex:
			getActivity().setTheme(R.style.ActionSheetStyle);
			showActionSheet();

			System.out.println("性别--->");
			break;
		case R.id.relative_personal_safety_password:
			Intent passwordIntent = new Intent(getActivity(),
					ModifyPasswordActivity.class);
			startActivity(passwordIntent);
			break;
		case R.id.btn_setting_quit:
			// 弹出提示框，确定退出
			showDialog();
			break;
		default:
			break;
		}

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
