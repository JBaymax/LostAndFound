package com.duan.lostandfound.activity;

import java.util.ArrayList;
import java.util.List;

import com.duan.lostandfound.R;
import com.duan.lostandfound.adapter.MainPageAdapter;
import com.duan.lostandfound.fragment.FragmentFound;
import com.duan.lostandfound.fragment.FragmentLost;
import com.duan.lostandfound.fragment.FragmentPersonal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * FragmentActivity of Activity
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends FragmentActivity implements
		OnPageChangeListener, OnCheckedChangeListener {

	private TextView mainTitleTextView;
	private ImageView addSelectorImageView;
	private ViewPager pager;
	private MainPageAdapter adapter;// 主界面的适配器
	private List<Fragment> fragments;
	private RadioGroup group;
	private RadioButton button0, button1, button2;
	/* 点击后退键的时间 */
	private long exitTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {

		mainTitleTextView = (TextView) findViewById(R.id.tv_main_title);
		mainTitleTextView.setText("失物招领");
		addSelectorImageView = (ImageView) findViewById(R.id.iv_main_add_selector);
		addSelectorImageView.setVisibility(View.VISIBLE);

		fragments = new ArrayList<Fragment>();
		fragments.add(new FragmentLost());
		fragments.add(new FragmentFound());
		fragments.add(new FragmentPersonal());
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(fragments.size() - 1);// 缓存页面,显示第一个缓存最后一个
		pager.setOnPageChangeListener(this);
		group = (RadioGroup) findViewById(R.id.radioGroup1);
		button0 = (RadioButton) findViewById(R.id.radio0);
		button1 = (RadioButton) findViewById(R.id.radio1);
		button2 = (RadioButton) findViewById(R.id.radio2);
		group.setOnCheckedChangeListener(this);

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		getTabState(arg0);

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.radio0:
			pager.setCurrentItem(0);
			mainTitleTextView.setText("失物招领");
			addSelectorImageView.setVisibility(View.VISIBLE);
			break;
		case R.id.radio1:
			pager.setCurrentItem(1);
			mainTitleTextView.setText("寻物启事");
			addSelectorImageView.setVisibility(View.VISIBLE);
			break;
		case R.id.radio2:
			pager.setCurrentItem(2);
			mainTitleTextView.setText("个人信息");
			addSelectorImageView.setVisibility(View.GONE);
			break;
		default:
			break;
		}

	}

	private void getTabState(int index) {
		// TODO Auto-generated method stub
		button0.setChecked(false);
		button1.setChecked(false);
		button2.setChecked(false);

		switch (index) {
		case 0:
			button0.setChecked(true);
			mainTitleTextView.setText("失物招领");
			addSelectorImageView.setVisibility(View.VISIBLE);
			break;
		case 1:
			button1.setChecked(true);
			mainTitleTextView.setText("寻物启事");
			addSelectorImageView.setVisibility(View.VISIBLE);
			break;
		case 2:
			button2.setChecked(true);
			mainTitleTextView.setText("个人信息");
			addSelectorImageView.setVisibility(View.GONE);
			break;
		default:
			break;
		}

	}

	/**
	 * 退出功能
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				this.exitApp();
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	/**
	 * 退出程序
	 */
	private void exitApp() {
		// 判断2次点击事件时间
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT)
					.show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
		}
	}

}
