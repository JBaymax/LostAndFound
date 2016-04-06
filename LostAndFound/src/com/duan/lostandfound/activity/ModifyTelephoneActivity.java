package com.duan.lostandfound.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duan.lostandfound.R;
import com.duan.lostandfound.analysis.AnalysisGetUsersInfoResponseParam;
import com.duan.lostandfound.dto.Users;
import com.duan.lostandfound.finaldata.FinalData;
import com.duan.lostandfound.finaldata.HttpClient;
import com.duan.lostandfound.finaldata.Request;
import com.duan.lostandfound.fragment.FragmentPersonal;
import com.duan.lostandfound.param.RequestParam;
import com.duan.lostandfound.utils.IntentCode;

/**
 * 此类是修改手机号; 在此界面之前，还应该有提示界面，，请参考滴滴打车
 * 
 * @author Administrator
 * 
 */
public class ModifyTelephoneActivity extends Activity implements
		OnClickListener {

	private ImageView back;// 返回
	private TextView title;// 头部信息
	private EditText content;// 修改内容
	private Button submit;// 完成

	String telephone;// 手机号
	int userId;

	Users currentUsers = null; //

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify);// 修改昵称、手机号、密码使用了同一个布局
		initView();
		initEvent();
	}

	private void initView() {
		back = (ImageView) findViewById(R.id.iv_modify_back);
		title = (TextView) findViewById(R.id.tv_modify_title);
		content = (EditText) findViewById(R.id.et_modify_content);
		content.setInputType(InputType.TYPE_CLASS_NUMBER);// 调数字键盘
		submit = (Button) findViewById(R.id.btn_modify_submit);
		title.setText("更改手机号");

	}

	private void initEvent() {
		back.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	public void getTelephone() {
		// 读取共享参数中的uesrId值,,,,该值是user表的主键
		SharedPreferences pref = getSharedPreferences(
				FinalData.CONFIG_FILE_NAME, MODE_PRIVATE);
		Editor editor = pref.edit();
		userId = pref.getInt("id", 292033670);
		editor.commit(); // 提交
		telephone = content.getText().toString();// 获取edittext中的值
		if (!TextUtils.isEmpty(telephone)) {
			if (telephoneFormat(telephone) == 1) {
				RequestParam requestParams = new RequestParam();
				requestParams.setRequestType(RequestParam.MODIFYTELEPHONE);
				// 传输过去的数据
				try {
					JSONObject[] params = new JSONObject[1];
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", userId);
					jsonObject.put("telephone", telephone);
					params[0] = jsonObject;
					requestParams.setParams(params);
				} catch (Exception e) {
					e.printStackTrace();
				}
				new ModifyTelephoneMyAsyncTask().execute(requestParams);
			}
		} else {
			Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_LONG).show();
		}
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
	 * 改变昵称的异步通信任务
	 */
	public class ModifyTelephoneMyAsyncTask extends
			AsyncTask<RequestParam, Integer, Integer> {

		@Override
		protected Integer doInBackground(RequestParam... params) {
			RequestParam requestParams = params[0];

			System.out
					.println("---Duan:GetUsersInfoAsyncTask.doInBackground.requestParams--->"
							+ requestParams.getJSON());
			if (!HttpClient.isConnect(ModifyTelephoneActivity.this)) {
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

				System.out.println("---Duan:currentUsers--->" + currentUsers);
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
				Toast.makeText(ModifyTelephoneActivity.this, "修改成功！",
						Toast.LENGTH_LONG).show();
				Intent intent = new Intent(ModifyTelephoneActivity.this,
						FragmentPersonal.class);
				// 得到相应位置的textView的值
				intent.putExtra("telephone", telephone);
				setResult(IntentCode.MODIFY_TELEPHONE_INTENT_RESULT, intent);
				ModifyTelephoneActivity.this.finish();

				break;
			case -1:
				Toast.makeText(ModifyTelephoneActivity.this, "修改失败！",
						Toast.LENGTH_LONG).show();
				break;
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_modify_back:
			// 返回直接用finish
			ModifyTelephoneActivity.this.finish();
			break;
		case R.id.btn_modify_submit:
			// 用来实现异步通信任务
			getTelephone();
			break;
		default:
			break;
		}
	}

	/**
	 * 调用系统的返回按钮
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
