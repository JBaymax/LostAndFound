package com.duan.lostandfound.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.duan.lostandfound.R;
import com.duan.lostandfound.analysis.AnalysisGetUsersInfoResponseParam;
import com.duan.lostandfound.dto.Users;
import com.duan.lostandfound.finaldata.FinalData;
import com.duan.lostandfound.finaldata.HttpClient;
import com.duan.lostandfound.finaldata.Request;
import com.duan.lostandfound.param.RequestParam;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 此类是修改昵称
 * 
 * @author Administrator
 * 
 */
public class ModifyNameActivity extends Activity implements OnClickListener {

	private ImageView backImageView;// 返回
	private TextView titleTextView;// 头部信息
	private EditText contentEditText;// 修改内容
	private Button submitButton;// 完成

	int userId;// user表中的主键
	String content;

	Users currentUsers = null; //

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify);// 修改昵称、手机号、密码使用了同一个布局
		initView();
		initEvent();
	}

	private void initView() {

		backImageView = (ImageView) findViewById(R.id.iv_modify_back);
		titleTextView = (TextView) findViewById(R.id.tv_modify_title);
		contentEditText = (EditText) findViewById(R.id.et_modify_content);
		submitButton = (Button) findViewById(R.id.btn_modify_submit);
		titleTextView.setText("更改昵称");
	}

	private void initEvent() {

		backImageView.setOnClickListener(this);
		submitButton.setOnClickListener(this);

	}

	public void getUserNameInfo() {
		// 读取共享参数中的uesrId值,,,,该值是user表的主键
		SharedPreferences pref = getSharedPreferences(
				FinalData.CONFIG_FILE_NAME, MODE_PRIVATE);
		Editor editor = pref.edit();
		userId = pref.getInt("id", 1234567890);

		System.out.println("Duan:userId--->" + userId);

		editor.commit(); // 提交
		content = contentEditText.getText().toString();// 获取edittext中的值
		if (!TextUtils.isEmpty(content)) {
			if (nickNameFormat(content) == 1) {
				RequestParam requestParams = new RequestParam();
				requestParams.setRequestType(RequestParam.MODIFYNAME);
				// 传输过去的数据
				try {
					JSONObject[] params = new JSONObject[1];
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", userId);
					jsonObject.put("name", content);
					// jsonObject.put("password",
					// Encrypt.md5Encrypt(password));
					params[0] = jsonObject;
					requestParams.setParams(params);
				} catch (Exception e) {
					e.printStackTrace();
				}
				new ModifyNameMyAsyncTask().execute(requestParams);
			}

		} else {

			Toast.makeText(this, "昵称不能设置为空！", Toast.LENGTH_LONG).show();
		}

	}

	/**
	 * 验证码校验
	 * 
	 * @param name
	 * @return
	 */
	private int nickNameFormat(String name) {
		String line = name;
		String pattern = "^.{2,6}$";

		Pattern r = Pattern.compile(pattern);// 创建 Pattern 对象
		Matcher m = r.matcher(line);// 现在创建 matcher 对象

		if (m.find()) {
			Log.i("LOG", "验证 Yes");
			return 1;
		} else {
			Toast.makeText(this, "昵称长度为2-6位", Toast.LENGTH_LONG).show();
			return 0;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_modify_back:
			// 返回直接用finish
			ModifyNameActivity.this.finish();
			break;
		case R.id.btn_modify_submit:
			// 用来实现异步通信任务
			getUserNameInfo();
			break;
		default:
			break;
		}
	}

	/**
	 * 改变密码的异步通信任务
	 */
	public class ModifyNameMyAsyncTask extends
			AsyncTask<RequestParam, Integer, Integer> {

		@Override
		protected Integer doInBackground(RequestParam... params) {
			RequestParam requestParams = params[0];

			System.out
					.println("---Duan:GetUsersInfoAsyncTask.doInBackground.requestParams--->"
							+ requestParams.getJSON());
			if (!HttpClient.isConnect(ModifyNameActivity.this)) {
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
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			switch (result) {
			case 0:
				Toast.makeText(ModifyNameActivity.this, "修改成功！",
						Toast.LENGTH_LONG).show();
				ModifyNameActivity.this.finish();

				break;
			case -1:
				Toast.makeText(ModifyNameActivity.this, "修改失败！",
						Toast.LENGTH_LONG).show();
				break;
			}

		}
	}

	/**
	 * 调用系统的返回按钮
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
