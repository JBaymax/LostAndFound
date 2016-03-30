package com.duan.lostandfound.activity;

import com.duan.lostandfound.R;
import com.duan.lostandfound.finaldata.FinalData;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

public class WelcomeActivity extends Activity {
	protected int mScreenWidth;
	protected int mScreenHeight;
	private static final int GO_HOME = 100;

	String telephone;
	String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		initData();
	}

	private void initData() {
		mHandler.sendEmptyMessageDelayed(GO_HOME, 3000);

	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				goHome();
				break;
			}
		}
	};

	public void goHome() {
		// 先读取共享参数
		SharedPreferences preferences = getSharedPreferences(
				FinalData.CONFIG_FILE_NAME, Activity.MODE_PRIVATE);
		telephone = preferences.getString("telephone", "0");
		password = preferences.getString("password", "0");
		if ((!telephone.equals("0")) && (!password.equals("0"))) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			this.finish();
		} else {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			this.finish();
		}

	}
}
