package com.duan.lostandfound.activity;

import com.duan.lostandfound.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Window;

public class WelcomeActivity extends Activity {
	protected int mScreenWidth;
	protected int mScreenHeight;
	private static final int GO_HOME = 100;

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
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}

	/**
	 * 获取当前状态栏的高度 getStateBar
	 * 
	 * @Title: getStateBar
	 * @throws
	 */
	public int getStateBar() {
		Rect frame = new Rect();
		getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		return statusBarHeight;
	}

	public static int dip2px(Context context, float dipValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (scale * dipValue + 0.5f);
	}
}
