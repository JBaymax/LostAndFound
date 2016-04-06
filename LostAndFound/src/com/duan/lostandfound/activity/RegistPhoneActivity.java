package com.duan.lostandfound.activity;

import static cn.smssdk.framework.utils.R.getStringRes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.duan.lostandfound.R;
import com.duan.lostandfound.utils.MyCountTimer;

/**
 * 注册
 * 
 * @author Duan
 * 
 */
public class RegistPhoneActivity extends Activity implements OnClickListener {

	private ImageView backImageView;// 返回上一界面
	private Button getVerifyCodeButton;// 获取验证码
	private Button nextStepButton;// 下一步

	private EditText telephoneEditText;// 手机号
	private EditText verifyCodeEditText;// 验证码

	String APPKEY = "1061951a7722e";// 获取短信验证的Key
	String APPSECRET = "ecd8f34fe48f531eaf3bfc1f52be4ce0";// 获取短信验证的secret

	String telephone;// 传输到下一界面的手机号

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist_phone_verifycode);

		intiView();
		initEvent();
		// 初始化验证码
		SMSSDK.initSDK(this, APPKEY, APPSECRET);
		EventHandler eh = new EventHandler() {

			@Override
			public void afterEvent(int event, int result, Object data) {

				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handler.sendMessage(msg);
			}

		};
		SMSSDK.registerEventHandler(eh);
	}

	/**
	 * 设置控件的监听事件
	 */
	private void initEvent() {

		getVerifyCodeButton.setOnClickListener(this);
		nextStepButton.setOnClickListener(this);
		backImageView.setOnClickListener(this);
	}

	private void intiView() {

		getVerifyCodeButton = (Button) findViewById(R.id.btn_regist_get_verify_code);
		nextStepButton = (Button) findViewById(R.id.btn_regist_next_step);

		backImageView = (ImageView) findViewById(R.id.iv_regist_title_back);

		telephoneEditText = (EditText) findViewById(R.id.et_regist_telephone);
		verifyCodeEditText = (EditText) findViewById(R.id.et_regist_verify_code);
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int event = msg.arg1;
			int result = msg.arg2;
			Object data = msg.obj;
			Log.i("event", "event=" + event);
			if (result == SMSSDK.RESULT_COMPLETE) {
				// 短信注册成功后，返回MainActivity,然后提示新好友
				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
					Toast.makeText(getApplicationContext(), "提交验证码成功",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(RegistPhoneActivity.this,
							RegistPasswordActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("telephone", telephone);
					intent.putExtras(bundle);
					startActivity(intent);
					RegistPhoneActivity.this.finish();
				} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
					Toast.makeText(getApplicationContext(), "验证码已经发送",
							Toast.LENGTH_SHORT).show();
				} else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {// 返回支持发送验证码的国家列表
					Toast.makeText(getApplicationContext(), "获取国家列表成功",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				// 验证码错误
				((Throwable) data).printStackTrace();
				int resId = getStringRes(RegistPhoneActivity.this,
						"smssdk_network_error");
				Toast.makeText(RegistPhoneActivity.this, "验证码错误",
						Toast.LENGTH_SHORT).show();
				if (resId > 0) {
					Toast.makeText(RegistPhoneActivity.this, resId,
							Toast.LENGTH_SHORT).show();
				}
			}
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		SMSSDK.unregisterAllEventHandler();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_regist_get_verify_code:
			telephone = telephoneEditText.getText().toString();
			if (!TextUtils.isEmpty(telephone)) {
				if (telephoneFormat(telephone) == 1) {
					MyCountTimer myCountTimer = new MyCountTimer(
							getVerifyCodeButton);// 计时器
					myCountTimer.start();
					SMSSDK.getVerificationCode("86", telephoneEditText
							.getText().toString());// 获取验证码
				}

			} else {
				Toast.makeText(this, "电话不能为空", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.btn_regist_next_step:
			if (!TextUtils.isEmpty(verifyCodeEditText.getText().toString())) {
				SMSSDK.submitVerificationCode("86", telephone,
						verifyCodeEditText.getText().toString());// 验证验证码
			} else {
				Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.iv_regist_title_back:
			// 返回按钮：返回到登录界面
			Intent registIntent = new Intent(RegistPhoneActivity.this,
					LoginActivity.class);
			startActivity(registIntent);
			RegistPhoneActivity.this.finish();
			break;
		}

	}
}
