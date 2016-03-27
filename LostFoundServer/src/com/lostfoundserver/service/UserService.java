package com.lostfoundserver.service;

import java.sql.Connection;

import com.lostfoundserver.utils.Encrypt;
import com.lostfoundserver.dao.UserDAO;
import com.lostfoundserver.database.DatabaseManager;
import com.lostfoundserver.dto.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserService {
	private UserDAO userDAO;
	private static UserService userService;

	public static UserService getInstance() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}

	public UserService() {
		userDAO = UserDAO.getInstance();
	}

	/**
	 * 用户注册
	 * 
	 * @param telephone
	 * @param password
	 * @return
	 */
	public JSONArray Register(String telephone, String name, String password) {
		Connection conn = DatabaseManager.getInstance().getConnection();
		userDAO.setConnection(conn);

		JSONArray result = null;
		User user = null;
		try {
			user = new User();
			user.setUsername(name);
			user.setUsertelephone(telephone);
			user.setUserpassword(Encrypt.md5Encrypt(password));
			if (userDAO.insert(user)) {
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
	 * 用户登录
	 * 
	 * @param telephone
	 * @param password
	 * @return
	 */
	public JSONArray Login(String telephone, String password) {
		Connection conn = DatabaseManager.getInstance().getConnection();
		userDAO.setConnection(conn);

		JSONArray result = null;
		try {
			// 查询用户
			User user = userDAO.getUser(telephone);
			password = Encrypt.md5Encrypt(password);// 从客户端传来的密码
			System.out.println("password--->" + password);
			if (password.equals(user.getUserpassword())) {
				result = userToJSON(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.CloseConnection(conn);
		}
		return result;
	}

	/**
	 * 判断用户是否存在
	 * 
	 * @param telephone
	 * @return
	 */
	public boolean userIsExist(String telephone) {
		Connection conn = DatabaseManager.getInstance().getConnection();
		userDAO.setConnection(conn);
		boolean result = false;
		try {
			User user = userDAO.getUser(telephone);// 注册前，先进行查询
			if (user != null) {
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
	 * 将User信息转化成JSONArray
	 * 
	 * @param user
	 * @return
	 */
	public JSONArray userToJSON(User user) {
		JSONArray result = null;
		try {
			if (user != null) {
				result = new JSONArray();
				JSONObject object = new JSONObject();
				object.put("id", user.getUserid());
				object.put("name", user.getUsername());
				object.put("telephone", user.getUsertelephone());
				object.put("password", user.getUserpassword());// 此处使用了密码加密
				object.put("sex", user.getUsersex());
				object.put("headimage", user.getUserheadimage());
				result.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
