package com.duan.lostandfound.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.duan.lostandfound.R;
import com.duan.lostandfound.adapter.LostAdapter;
import com.duan.lostandfound.analysis.AnalysisGetLostInfoResponseParam;
import com.duan.lostandfound.dto.Lost;
import com.duan.lostandfound.finaldata.HttpClient;
import com.duan.lostandfound.finaldata.Request;
import com.duan.lostandfound.param.RequestParam;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 失物招领
 * 
 * @author Duan
 * 
 */
public class FragmentLost extends Fragment {

	private ListView alllostlv;// listview
	RelativeLayout progress;
	LinearLayout layoutNoData;
	TextView tvNoData;
	private LostAdapter adapter;

	private List<Lost> datalist;

	/**
	 * 获取整个布局
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_lost, null);
		initView(view);
		return view;
	}

	/**
	 * 获取控件
	 * 
	 * @param view
	 */
	private void initView(View view) {
		progress = (RelativeLayout) view.findViewById(R.id.progress);
		layoutNoData = (LinearLayout) view.findViewById(R.id.layout_no);
		tvNoData = (TextView) view.findViewById(R.id.tv_no);
		alllostlv = (ListView) view.findViewById(R.id.list_lost);
		getLosts();
	}

	/**
	 * 向服务器发出请求获取所有失物招领信息
	 */
	public void getLosts() {
		RequestParam requestParam = new RequestParam();
		requestParam.setRequestType(RequestParam.GETLOST);
		requestParam.setParams(new JSONObject[0]);
		new GetLostMyAsyncTask().execute(requestParam);
	}

	/**
	 * 展示出所有的失物招领信息
	 */
	private void showLost() {
		List<Lost> lostList = new ArrayList<Lost>();
		for (int i = 0; i < datalist.size(); i++) {
			System.out
					.println("----Duan.showLost().datalist.get(i).getLostid()---->"
							+ datalist.get(i).getLostid());

			lostList.add(datalist.get(i));

			System.out.println("----Duan.showLost().lostList.get(i)---->"
					+ lostList.get(i));
		}
		adapter = new LostAdapter(getActivity(), lostList);
		System.out.println("adapter-->" + adapter);
		alllostlv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	/**
	 * 查询失物招领的异步通信任务
	 */
	public class GetLostMyAsyncTask extends
			AsyncTask<RequestParam, Integer, Integer> {

		@Override
		protected Integer doInBackground(RequestParam... params) {
			RequestParam requestParams = params[0];

			System.out
					.println("---Duan:GetLostMyAsyncTask.doInBackground.requestParams--->"
							+ requestParams.getJSON());

			if (!HttpClient.isConnect(getActivity())) {
				System.out
						.println("---Duan:GetLostMyAsyncTask.doInBackground.!HttpClient.isConnect(getActivity())--->");
				return -1; // 表示请求失败
			}
			String response = Request.request(requestParams.getJSON());

			System.out
					.println("----Duan:GetLostMyAsyncTask.doInBackground.response--->"
							+ response);

			AnalysisGetLostInfoResponseParam alaysisResponse = new AnalysisGetLostInfoResponseParam(
					response);
			if (alaysisResponse.getResult() != 0) {
				return alaysisResponse.getResult();
			}
			System.out
					.println("----Duan:GetLostMyAsyncTask.doInBackground.alaysisResponse.getLostInfo() --->"
							+ alaysisResponse.getLostInfo());
			if (alaysisResponse.getLostInfo() != null) {
				// 从服务端获取的数据
				datalist = alaysisResponse.getLostInfo();
				System.out.println("datalist--->" + datalist);

				return 0;
			}
			return -1;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			switch (result) {
			case 0:
				showLost(); // 显示获取到的所有课程类型方法
				Toast.makeText(getActivity(), "访问成功！", Toast.LENGTH_SHORT)
						.show();

				break;
			case -1:
				Toast.makeText(getActivity(), "访问失败！", Toast.LENGTH_SHORT)
						.show();
				break;
			}

		}

	}
}
