package com.lostfoundserver.service;

import java.sql.Connection;
import java.util.List;

import com.lostfoundserver.dao.FoundDAO;
import com.lostfoundserver.database.DatabaseManager;
import com.lostfoundserver.dto.Found;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FoundService {
	private FoundDAO foundDAO;
	private static FoundService foundService;

	public static FoundService getInstance() {
		if (foundService == null) {
			foundService = new FoundService();
		}
		return foundService;
	}

	public FoundService() {
		foundDAO = FoundDAO.getInstance();
	}

	/**
	 * 查询所有的寻物启事信息
	 *
	 * @return
	 */

	public JSONArray getFound() {
		Connection conn = DatabaseManager.getInstance().getConnection();
		foundDAO.setConnection(conn);

		JSONArray result = null;

		try {
			List<Found> founds = foundDAO.getFounds();
			if (founds != null) {
				result = new JSONArray();
				Found found = null;
				JSONObject object = null;
				for (int i = 0; i < founds.size(); i++) {
					object = new JSONObject();
					found = founds.get(i);
					object.put("foundid", found.getFoundid());
					object.put("userid", found.getUserid());
					object.put("name", found.getUsername());
					object.put("title", found.getFoundtitle());
					object.put("content", found.getFoundcontent());
					object.put("time", found.getFoundtime());
					object.put("telephone", found.getUsertelephone());
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

	/**
	 * 添加寻物启事
	 * 
	 * @param id
	 * @param telephone
	 * @param title
	 * @param content
	 * @param time
	 * @return
	 */
	public JSONArray AddFound(int id, String telephone, String title, String content, String time) {
		Connection conn = DatabaseManager.getInstance().getConnection();
		foundDAO.setConnection(conn);

		JSONArray result = null;
		Found found = null;
		try {
			found = new Found();
			found.setUserid(id);
			found.setUsertelephone(telephone);
			found.setFoundtitle(title);
			found.setFoundcontent(content);
			found.setFoundtime(time);

			if (foundDAO.insert(found)) {
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
	public boolean foundIsExist(String telephone, String title, String content) {
		Connection conn = DatabaseManager.getInstance().getConnection();
		foundDAO.setConnection(conn);
		boolean result = false;
		try {
			Found found = foundDAO.getFound(telephone, title, content);// 注册前，先进行查询
			if (found != null) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.CloseConnection(conn);
		}

		return result;
	}
}
