package com.duan.lostandfound.utils;


import com.duan.lostandfound.R;

import android.os.CountDownTimer;
import android.widget.TextView;

public class MyCountTimer extends CountDownTimer {

	public static final int TIME_COUNT = 61 * 1000;// 时间防止从59s开始显示（以倒计时60s为例子）
	private TextView button;
	private int endStringRid;
	private int normalColor = 0xff18b4ed; // 未计时的按钮背景颜色
	private int normalTextColor = 0xffffffff; // 未计时的文字颜色
	private int timingColor = 0xffe4e6e7; // 计时期间的按钮背景颜色
	private int timingTextColor = 0xffc8c8c8; // 计时期间的文字颜色

	/**
	 * 参数 millisInFuture 倒计时总时间（如60S，120s等） 参数 countDownInterval 渐变时间（每次倒计1s） 参数
	 * button 点击的按钮(因为Button是TextView子类，为了通用我的参数设置为TextView） 参数 endStrRid
	 * 倒计时结束后，按钮对应显示的文字
	 */
	public MyCountTimer(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		this.button = button;
		this.endStringRid = endStringRid;
	}

	/**
	 * 参数上面有注释
	 */
	public MyCountTimer(TextView button, int endStrRid) {
		super(TIME_COUNT, 1000);
		this.button = button;
		this.endStringRid = endStrRid;
	}

	public MyCountTimer(TextView button) {
		super(TIME_COUNT, 1000);
		this.button = button;
		this.endStringRid = R.string.regist_get_verify_code;
	}

	public MyCountTimer(TextView button, int normalColor, int normalTextColor,
			int timingColor, int timingTextColor) {
		this(button);
		this.normalColor = normalColor;
		this.normalTextColor = normalTextColor;
		this.timingColor = timingColor;
		this.timingTextColor = timingTextColor;
	}

	// 计时完毕时触发
	@Override
	public void onFinish() {
		button.setBackgroundColor(normalColor);
		button.setTextColor(normalTextColor);
		button.setText(endStringRid);
		button.setEnabled(true);

	}

	// 计时过程显示
	@Override
	public void onTick(long millisUntilFinished) {
		button.setBackgroundColor(timingColor);
		button.setTextColor(timingTextColor);
		button.setEnabled(false);
		button.setText(millisUntilFinished / 1000 + "s");
	}

	public void setButton(TextView button) {
		this.button = button;
	}

	public void setEndStringRid(int endStringRid) {
		this.endStringRid = endStringRid;
	}

	public void setNormalColor(int normalColor) {
		this.normalColor = normalColor;
	}

	public void setNormalTextColor(int normalTextColor) {
		this.normalTextColor = normalTextColor;
	}

	public void setTimingColor(int timingColor) {
		this.timingColor = timingColor;
	}

	public void setTimingTextColor(int timingTextColor) {
		this.timingTextColor = timingTextColor;
	}
}
