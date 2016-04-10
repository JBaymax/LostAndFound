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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddFoundActivity extends Activity implements OnClickListener {

	private ImageView backImageView;// 返回
	private TextView titleTextView;// 头部信息

	private EditText titleEditText;// 标题
	private EditText telephoneEditText;// 手机号
	private EditText contentEditText;// 内容
	private Button submitButton;// 完成

	int userId;// 用户ID
	String name;// 用户名
	String telephone;// 手机号
	String title;// 标题
	String content;// 内容

	Users currentUsers = null; //

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);// 编辑、添加失失物招领
		initView();
		initEvent();
	}

	private void initView() {
		backImageView = (ImageView) findViewById(R.id.iv_add_back);
		titleTextView = (TextView) findViewById(R.id.tv_add_title);
		titleEditText = (EditText) findViewById(R.id.et_add_title);
		telephoneEditText = (EditText) findViewById(R.id.et_add_telephone);
		getLostInfo();
		contentEditText = (EditText) findViewById(R.id.et_add_content);
		submitButton = (Button) findViewById(R.id.btn_add_submit);
		titleTextView.setText("添加寻物启事");
	}

	private void initEvent() {
		backImageView.setOnClickListener(this);
		submitButton.setOnClickListener(this);
	}

	/**
	 * 直接获取手机号
	 */
	private void getLostInfo() {
		// 读取共享参数中的uesrId值,,,,该值是user表的主键
		SharedPreferences pref = getSharedPreferences(
				FinalData.CONFIG_FILE_NAME, MODE_PRIVATE);
		Editor editor = pref.edit();
		telephone = pref.getString("telephone", "18365265051");
		telephoneEditText.setText(telephone);
		System.out.println("Duan:telephone--->" + telephone);

		editor.commit(); // 提交
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

	public void getAddFoundInfo() {
		// 读取共享参数中的uesrId值,,,,该值是user表的主键
		SharedPreferences pref = getSharedPreferences(
				FinalData.CONFIG_FILE_NAME, MODE_PRIVATE);
		Editor editor = pref.edit();
		userId = pref.getInt("id", 1234567890);
		name = pref.getString("name", "段雪庆");
		System.out.println("Duan:userId--->" + userId);

		editor.commit(); // 提交
		title = titleEditText.getText().toString();// 获取标题
		content = contentEditText.getText().toString();// 获取内容
		telephone = telephoneEditText.getText().toString();// 获取手机号
		if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
			if (telephoneFormat(telephone) == 1) {
				RequestParam requestParams = new RequestParam();
				requestParams.setRequestType(RequestParam.ADDFOUND);
				// 传输过去的数据
				try {
					JSONObject[] params = new JSONObject[1];
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", userId);
					jsonObject.put("name", name);
					jsonObject.put("title", title);
					jsonObject.put("telephone", telephone);
					jsonObject.put("content", content);
					params[0] = jsonObject;
					requestParams.setParams(params);
				} catch (Exception e) {
					e.printStackTrace();
				}
				new AddFoundMyAsyncTask().execute(requestParams);
			}

		} else {

			Toast.makeText(this, "标题或内容不能设置为空！", Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * 增加寻物启事的异步通信任务
	 */
	public class AddFoundMyAsyncTask extends
			AsyncTask<RequestParam, Integer, Integer> {

		@Override
		protected Integer doInBackground(RequestParam... params) {
			RequestParam requestParams = params[0];

			System.out
					.println("---Duan:AddFoundMyAsyncTask.doInBackground.requestParams--->"
							+ requestParams.getJSON());
			if (!HttpClient.isConnect(AddFoundActivity.this)) {
				System.out
						.println("---Duan:AddFoundMyAsyncTask.doInBackground.!HttpClient.isConnect(getActivity())--->");
				return -1; // 表示请求失败
			}
			String response = Request.request(requestParams.getJSON());
			System.out
					.println("----Duan:AddFoundMyAsyncTask.doInBackground.response--->"
							+ response);
			System.out
					.println("----Duan:AddFoundMyAsyncTask.doInBackground.response2--->"
							+ Request.request(requestParams.getJSON()));
			AnalysisGetUsersInfoResponseParam alaysisResponse = new AnalysisGetUsersInfoResponseParam(
					response);
			if (alaysisResponse.getResult() != 0) {
				return alaysisResponse.getResult();
			}
			System.out
					.println("----Duan:AddFoundMyAsyncTask.doInBackground.alaysisResponse.getCourseVideoInfo() --->"
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
			super.onPostExecute(result);
			switch (result) {
			case 0:
				Toast.makeText(AddFoundActivity.this, "添加成功！",
						Toast.LENGTH_SHORT).show();

				break;
			case -1:
				Toast.makeText(AddFoundActivity.this, "添加失败！",
						Toast.LENGTH_SHORT).show();
				break;
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_add_back:
			// 返回直接用finish
			AddFoundActivity.this.finish();
			break;
		case R.id.btn_add_submit:
			// 用来实现异步通信任务
			getAddFoundInfo();
			break;
		default:
			break;
		}

	}

}
