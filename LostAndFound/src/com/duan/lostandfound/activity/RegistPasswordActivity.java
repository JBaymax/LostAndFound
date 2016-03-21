package com.duan.lostandfound.activity;

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
		name = nameEditText.getText().toString();
		password = passwordEditText.getText().toString();

	}

	private void initEvent() {
		backImageView.setOnClickListener(this);
		submitButton.setOnClickListener(this);

	}

	/**
	 * 实现注册功能
	 */
	private void regist() {

		System.out.println("regist.password--!isEmpty" + password);

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
		case R.id.btn_regist_next_step:
			// 假如password控件不为空
			if (!TextUtils.isEmpty(password)) {
				System.out.println("password--!isEmpty" + password);
				regist();
			} else {
				System.out.println("password--isEmpty" + password);
				Toast.makeText(RegistPasswordActivity.this, "密码不能为空",
						Toast.LENGTH_LONG).show();
			}
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
