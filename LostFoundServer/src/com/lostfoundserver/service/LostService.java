package com.lostfoundserver.service;

import java.sql.Connection;
import java.util.List;

import com.lostfoundserver.dao.LostDAO;
import com.lostfoundserver.database.DatabaseManager;
import com.lostfoundserver.dto.Lost;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LostService {
	private LostDAO lostDAO;
	private static LostService lostService;

	public static LostService getInstance() {
		if (lostService == null) {
			lostService = new LostService();
		}
		return lostService;
	}

	public LostService() {
		lostDAO = LostDAO.getInstance();
	}

	/**
	 * 添加失物招领
	 * 
	 * @param id
	 * @param telephone
	 * @param title
	 * @param content
	 * @param time
	 * @return
	 */
	public JSONArray AddLost(int id, String name, String telephone, String title, String content, String time) {
		Connection conn = DatabaseManager.getInstance().getConnection();
		lostDAO.setConnection(conn);

		JSONArray result = null;
		Lost lost = null;
		try {
			lost = new Lost();
			lost.setUserid(id);
			lost.setUsername(name);
			lost.setUsertelephone(telephone);
			lost.setLosttitle(title);
			lost.setLostcontent(content);
			lost.setLosttime(time);

			if (lostDAO.insert(lost)) {
				result = new JSONArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.CloseConnection(conn);
		}
		return result;
	}

	/**
	 * 判断失物招领是否存在
	 * 
	 * @param telephone
	 * @param title
	 * @param content
	 * @return
	 */
	public boolean lostIsExist(String telephone, String title, String content) {
		Connection conn = DatabaseManager.getInstance().getConnection();
		lostDAO.setConnection(conn);
		boolean result = false;
		try {
			Lost lost = lostDAO.getLost(telephone, title, content);// 注册前，先进行查询
			if (lost != null) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.CloseConnection(conn);
		}

		return result;
	}

	/**
	 * 查询所有的失物招领信息
	 * 
	 * @return
	 */

	public JSONArray getLost() {
		Connection conn = DatabaseManager.getInstance().getConnection();
		lostDAO.setConnection(conn);

		JSONArray result = null;

		try {
			List<Lost> losts = lostDAO.getLosts();
			if (losts != null) {
				result = new JSONArray();
				Lost lost = null;
				JSONObject object = null;
				for (int i = 0; i < losts.size(); i++) {
					object = new JSONObject();
					lost = losts.get(i);
					object.put("lostid", lost.getLostid());
					object.put("userid", lost.getUserid());
					object.put("name", lost.getUsername());
					object.put("title", lost.getLosttitle());
					object.put("content", lost.getLostcontent());
					object.put("time", lost.getLosttime());
					object.put("telephone", lost.getUsertelephone());
					result.add(object);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.CloseConnection(conn);
		}
		return result;
	}

}
