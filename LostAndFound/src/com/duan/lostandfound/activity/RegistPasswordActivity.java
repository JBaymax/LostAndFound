package com.duan.lostandfound.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.duan.lostandfound.R;
import com.duan.lostandfound.analysis.AnalysisGetRegisterInfoResponseParam;
import com.duan.lostandfound.finaldata.HttpClient;
import com.duan.lostandfound.finaldata.Request;
import com.duan.lostandfound.param.RequestParam;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 设置昵称/密码
 * 
 * @author Duan
 * 
 */
public class RegistPasswordActivity extends Activity implements OnClickListener {

	private ImageView backImageView;// 返回

	private EditText nameEditText;// 昵称
	private EditText passwordEditText;// 密码

	private Button submitButton;// 完成提交

	private String name;// 昵称
	private String password;// 密码
	String telephone;// 接收上一界面传来的手机号

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist_name_password);

		initView();
		initEvent();
	}

	private void initView() {
		// 获取上界面传来的talephone的值
		Bundle bundle = this.getIntent().getExtras();
		telephone = bundle.getString("telephone");
		// 获取控件
		backImageView = (ImageView) findViewById(R.id.iv_regist_name_title_back);
		submitButton = (Button) findViewById(R.id.btn_regist_set_password_submit);
		nameEditText = (EditText) findViewById(R.id.et_regist_set_name);
		passwordEditText = (EditText) findViewById(R.id.et_set_password);

	}

	private void initEvent() {
		backImageView.setOnClickListener(this);
		submitButton.setOnClickListener(this);

	}

	/**
	 * 验证用户名和密码是否填写
	 * 
	 * @return
	 */
	private boolean validate() {
		name = nameEditText.getText().toString();
		if (TextUtils.isEmpty(name)) {
			Toast.makeText(this, "昵称不能为空！", Toast.LENGTH_LONG).show();
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
			Log.i("LOG", "验证  No");
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
			Log.i("LOG", "验证  No");
			return 0;
		}
	}

	/**
	 * 实现注册功能
	 */
	private void regist() {

		System.out.println("regist.password--!isEmpty" + password);
		if (validate()) {
			if (nickNameFormat(name) == 1) {
				if (passwordFormat(password) == 1) {
					RequestParam requestParams = new RequestParam();
					requestParams.setRequestType(RequestParam.REGISTER);// 设置请求类型

					try {

						JSONObject[] params = new JSONObject[1];
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("name", name);
						jsonObject.put("telephone", telephone);
						jsonObject.put("password", password);
						params[0] = jsonObject;
						requestParams.setParams(params);// 设置请求参数

					} catch (Exception e) {
						e.printStackTrace();
					}
					new GetRegistInfoAsyncTask().execute(requestParams);
				} else {
					Toast.makeText(RegistPasswordActivity.this,
							"密码只能是数字、下划线、字母，长度6-12位", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(RegistPasswordActivity.this, "昵称长度为2-6位",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public class GetRegistInfoAsyncTask extends
			AsyncTask<RequestParam, Integer, Integer> {

		@Override
		protected Integer doInBackground(RequestParam... params) {
			// TODO Auto-generated method stub
			RequestParam requestParams = params[0];
			System.out
					.println("----Duan:GetRegistInfoAsyncTask.doInBackground.requestParams--->"
							+ requestParams.getJSON());
			if (!HttpClient.isConnect(RegistPasswordActivity.this)) {
				System.out
						.println("----Duan:GetRegistInfoAsyncTask.doInBackground.!HttpClient.isConnect(getActivity())--->");
				return -1; // 表示请求失败
			}

			String response = Request.request(requestParams.getJSON());

			System.out
					.println("---Duan:GetRegistInfoAsyncTask.doInBackground.response--->"
							+ response);
			AnalysisGetRegisterInfoResponseParam alaysisResponse = new AnalysisGetRegisterInfoResponseParam(
					response);
			return alaysisResponse.getResult();

		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			switch (result) {
			case 0:
				Toast.makeText(RegistPasswordActivity.this, "注册成功！",
						Toast.LENGTH_LONG).show();
				Intent intent = new Intent(RegistPasswordActivity.this,
						LoginActivity.class);
				startActivity(intent);
				RegistPasswordActivity.this.finish();

				break;

			case -1:
				Toast.makeText(RegistPasswordActivity.this, "注册失败！",
						Toast.LENGTH_LONG).show();
				break;
			case -2:
				Toast.makeText(RegistPasswordActivity.this, "该手机号已注册！",
						Toast.LENGTH_LONG).show();
				break;
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_regist_set_password_submit:

			regist();

			break;

		case R.id.iv_regist_name_title_back:
			Intent passwordIntent = new Intent(RegistPasswordActivity.this,
					RegistPhoneActivity.class);
			startActivity(passwordIntent);
			RegistPasswordActivity.this.finish();
			break;
		default:
			break;
		}
	}
}
