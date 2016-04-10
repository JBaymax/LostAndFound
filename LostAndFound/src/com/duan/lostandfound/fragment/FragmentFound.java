package com.duan.lostandfound.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.duan.lostandfound.R;
import com.duan.lostandfound.adapter.FoundAdapter;
import com.duan.lostandfound.analysis.AnalysisGetFoundInfoResponseParam;
import com.duan.lostandfound.dto.Found;
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
 * 寻物启事
 * 
 * @author Duan
 * 
 */
public class FragmentFound extends Fragment {
	private ListView allfoundlv;// listview
	RelativeLayout progress;
	LinearLayout layoutNoData;
	TextView tvNoData;
	private FoundAdapter adapter;

	private List<Found> datalist;

	/**
	 * 获取整个布局
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_found, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		progress = (RelativeLayout) view.findViewById(R.id.progress);
		layoutNoData = (LinearLayout) view.findViewById(R.id.layout_no);
		tvNoData = (TextView) view.findViewById(R.id.tv_no);
		allfoundlv = (ListView) view.findViewById(R.id.list_found);
		getFounds();

	}

	/**
	 * 向服务器发出请求获取所有寻物启事信息
	 */
	private void getFounds() {
		RequestParam requestParam = new RequestParam();
		requestParam.setRequestType(RequestParam.GETFOUND);
		requestParam.setParams(new JSONObject[0]);
		new GetFoundMyAsyncTask().execute(requestParam);

	}

	/**
	 * 展示出所有的寻物启事信息
	 */
	private void showFound() {
		List<Found> foundList = new ArrayList<Found>();
		for (int i = 0; i < datalist.size(); i++) {
			System.out
					.println("----Duan.showFound().datalist.get(i).getFoundid()---->"
							+ datalist.get(i).getFoundid());

			foundList.add(datalist.get(i));
			System.out.println("----Duan.showFound().foundList.get(i)---->"
					+ foundList.get(i));
		}
		adapter = new FoundAdapter(getActivity(), foundList);
		allfoundlv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	/**
	 * 查询寻物启事的异步通信任务
	 */
	public class GetFoundMyAsyncTask extends
			AsyncTask<RequestParam, Integer, Integer> {

		@Override
		protected Integer doInBackground(RequestParam... params) {
			RequestParam requestParams = params[0];

			System.out
					.println("---Duan:GetFoundMyAsyncTask.doInBackground.requestParams--->"
							+ requestParams.getJSON());

			if (!HttpClient.isConnect(getActivity())) {
				System.out
						.println("---Duan:GetFoundMyAsyncTask.doInBackground.!HttpClient.isConnect(getActivity())--->");
				return -1; // 表示请求失败
			}
			String response = Request.request(requestParams.getJSON());

			System.out
					.println("----Duan:GetFoundMyAsyncTask.doInBackground.response--->"
							+ response);

			AnalysisGetFoundInfoResponseParam alaysisResponse = new AnalysisGetFoundInfoResponseParam(
					response);
			if (alaysisResponse.getResult() != 0) {
				return alaysisResponse.getResult();
			}
			System.out
					.println("----Duan:GetFoundMyAsyncTask.doInBackground.alaysisResponse.getLostInfo() --->"
							+ alaysisResponse.getFoundInfo());
			if (alaysisResponse.getFoundInfo() != null) {
				// 从服务端获取的数据
				datalist = alaysisResponse.getFoundInfo();
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
				showFound(); // 显示获取到的所有课程类型方法
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
