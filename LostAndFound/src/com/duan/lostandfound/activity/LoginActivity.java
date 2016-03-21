package com.duan.lostandfound.activity;

import org.json.JSONObject;

import com.duan.lostandfound.R;
import com.duan.lostandfound.analysis.AnalysisGetUsersInfoResponseParam;
import com.duan.lostandfound.dto.Users;
import com.duan.lostandfound.finaldata.FinalData;
import com.duan.lostandfound.finaldata.HttpClient;
import com.duan.lostandfound.finaldata.Request;
import com.duan.lostandfound.param.RequestParam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 登录:传输昵称和密码
 * 
 * @author Duan
 * 
 */
public class LoginActivity extends Activity {

	private RelativeLayout RelativeLayout;

	private EditText accountEditText;
	private EditText passwordEditText;

	private Button loginButton;// 登录按钮

	String account;// 账号
	String password;// 密码

	Users currentUsers = null; //

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initEvent();
	}

	private void initView() {

		RelativeLayout = (RelativeLayout) findViewById(R.id.relative_register);

		accountEditText = (EditText) findViewById(R.id.et_login_account);
		passwordEditText = (EditText) findViewById(R.id.et_login_password);

		loginButton = (Button) findViewById(R.id.btn_login);

	}

	private void initEvent() {
		RelativeLayout.setOnClickListener(new registTextViewOnClickListener());
		loginButton.setOnClickListener(new loginButtonOnClickListener());

	}

	/**
	 * 点击"新用户注册"进入手机注册界面
	 * 
	 * @author Duan
	 * 
	 */
	public class registTextViewOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// 跳转到注册界面
			Intent registIntent = new Intent(LoginActivity.this,
					RegistPhoneActivity.class);
			startActivity(registIntent);
			finish();
		}
	}

	/**
	 * 实现登录
	 * 
	 * @author Duan
	 * 
	 */
	public class loginButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			// 获取账号和密码
			getUserInfo();
		}

	}

	private void getUserInfo() {
		// 得到用户登录信息
		account = accountEditText.getText().toString();
		password = passwordEditText.getText().toString();

		RequestParam requestParams = new RequestParam();
		requestParams.setRequestType(RequestParam.LOGIN);

		// 传输过去的数据
		try {
			JSONObject[] params = new JSONObject[1];
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("account", account);
			jsonObject.put("password", password);
			params[0] = jsonObject;
			requestParams.setParams(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new GetUsersInfoAsyncTask().execute(requestParams);
	}

	public class GetUsersInfoAsyncTask extends
			AsyncTask<RequestParam, Integer, Integer> {

		@Override
		protected Integer doInBackground(RequestParam... params) {
			RequestParam requestParams = params[0];

			System.out
					.println("---Duan:GetUsersInfoAsyncTask.doInBackground.requestParams--->"
							+ requestParams.getJSON());
			if (!HttpClient.isConnect(LoginActivity.this)) {
				System.out
						.println("---Duan:GetUsersInfoAsyncTask.doInBackground.!HttpClient.isConnect(getActivity())--->");
				return -1; // 表示请求失败
			}
			String response = Request.request(requestParams.getJSON());
			System.out
					.println("----Duan:GetUsersInfoAsyncTask.doInBackground.response--->"
							+ response);
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
				account = currentUsers.getName();
				password = currentUsers.getPassword_md5();

				// 登录时,将用户ID存储到共享参数中
				SharedPreferences pref = getSharedPreferences(
						FinalData.CONFIG_FILE_NAME, MODE_PRIVATE);
				Editor editor = pref.edit();
				editor.putString("account", account);
				editor.putString("password", password);

				editor.commit(); // 提交
				System.out.println("---Duan:account--password--->" + account
						+ "," + password);
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

				Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG)
						.show();
				LoginActivity.this.finish();

				break;
			case -1:
				Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_LONG)
						.show();
				break;
			}

		}
	}

}
