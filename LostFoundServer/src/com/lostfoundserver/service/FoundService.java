package com.lostfoundserver.service;

import java.sql.Connection;

import com.lostfoundserver.dao.FoundDAO;
import com.lostfoundserver.dao.LostDAO;
import com.lostfoundserver.database.DatabaseManager;
import com.lostfoundserver.dto.Found;
import com.lostfoundserver.dto.Lost;

import net.sf.json.JSONArray;

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
