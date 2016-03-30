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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
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

	private EditText telephoneEditText;
	private EditText passwordEditText;

	private Button loginButton;// 登录按钮

	String telephone;// 账号
	String password;// 密码

	String username;// 用户昵称
	String usersex;// 用户性别
	int userid;

	Users currentUsers = null; //

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initEvent();
	}

	/**
	 * 获取控件
	 */
	private void initView() {

		RelativeLayout = (RelativeLayout) findViewById(R.id.relative_register);

		telephoneEditText = (EditText) findViewById(R.id.et_login_telephone);
		telephoneEditText.setInputType(InputType.TYPE_CLASS_NUMBER);// 输手机号时，使用数字键盘
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

	/**
	 * 验证用户名和密码是否填写
	 * 
	 * @return
	 */
	private boolean validate() {
		telephone = telephoneEditText.getText().toString();
		if (TextUtils.isEmpty(telephone)) {
			Toast.makeText(this, "手机不能为空！", Toast.LENGTH_LONG).show();
			return false;
		}
		password = passwordEditText.getText().toString();
		if (TextUtils.isEmpty(password)) {
			Toast.makeText(this, "密码不能为空！", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}

	/**
	 * 手机号格式验证
	 * 
	 * @param mobile
	 * @return
	 */
	private int telephoneFormat(String mobile) {

		String line = mobile;
		String pattern = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";

		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find()) {
			return 1;
		} else {
			Toast.makeText(this, "手机格式不对！", Toast.LENGTH_LONG).show();
			return 0;
		}
	}

	/**
	 * 密码校验
	 * 
	 * @param name
	 * @return
	 */
	private int passwordFormat(String name) {
		String line = name;
		String pattern = "^[0-9_a-zA-Z]{6,12}$";

		Pattern r = Pattern.compile(pattern);// 创建 Pattern 对象
		Matcher m = r.matcher(line);// 现在创建 matcher 对象

		if (m.find()) {
			Log.i("LOG", "验证 Yes");
			return 1;
		} else {
			Toast.makeText(this, "密码只能是数字、下划线、字母，长度6-12位", Toast.LENGTH_LONG)
					.show();
			return 0;
		}
	}

	private void getUserInfo() {
		// 得到用户登录信息
		telephone = telephoneEditText.getText().toString();
		password = passwordEditText.getText().toString();
		if (validate()) {
			if (telephoneFormat(telephone) == 1) {
				if (passwordFormat(password) == 1) {
					RequestParam requestParams = new RequestParam();
					requestParams.setRequestType(RequestParam.LOGIN);

					// 传输过去的数据
					try {
						JSONObject[] params = new JSONObject[1];
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("telephone", telephone);
						jsonObject.put("password", password);
						// jsonObject.put("password",
						// Encrypt.md5Encrypt(password));
						params[0] = jsonObject;
						requestParams.setParams(params);
					} catch (Exception e) {
						e.printStackTrace();
					}
					new GetUsersInfoAsyncTask().execute(requestParams);
				}

			}

		}
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
				username = currentUsers.getName();
				userid = currentUsers.getId();
				System.out.println("Duan:返回的值userid-username--->" + userid
						+ "," + username);
				// usersex = currentUsers.getSex();

				// 登录时,将用户ID存储到共享参数中
				SharedPreferences pref = getSharedPreferences(
						FinalData.CONFIG_FILE_NAME, MODE_PRIVATE);
				Editor editor = pref.edit();
				editor.putInt("id", userid);
				editor.putString("telephone", telephone);
				editor.putString("password", password);
				editor.putString("name", username);
				// editor.putString("sex", usersex);

				editor.commit(); // 提交
				System.out.println("---Duan:telephone--password--->"
						+ telephone + "," + password);
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
				Intent loginIntent = new Intent(LoginActivity.this,
						MainActivity.class);
				startActivity(loginIntent);
				LoginActivity.this.finish();

				break;
			case -1:
				Toast.makeText(LoginActivity.this, "手机号或密码错误！",
						Toast.LENGTH_LONG).show();
				break;
			}

		}
	}

}
