package com.test.DaoIMP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.jdbc.core.JdbcTemplate;

import com.test.Dao.UserDao;
import com.test.Model.UserBean;
import com.test.util.DateUtil;
import com.test.util.StringUtil;

public class UserDaoImp implements UserDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("all")
	/**
	 * 、检测用户名是否存在
	 */
	@Override
	public boolean checkReg(String name) {
		boolean flag = true;
		String sql = "select username from user";
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Map beanMap = (Map) iterator.next();
			if (name.equals(beanMap.get("username").toString())) {

				flag = false;
			}
		}
		return flag;
	}

	@Override
	public boolean reg(UserBean userBean) {
		String sql = "insert into user(username,password,salt,nickname,truename,sex,pic,create_date) values(?,?,?,?,?,?,?,?)";
		int a = jdbcTemplate.update(
				sql,
				new Object[] { userBean.getUsername(), userBean.getPassword(),
						userBean.getSalt(), userBean.getNickname(),
						userBean.getTruename(), userBean.getSex(),
						userBean.getPic(), DateUtil.getDate() });

		if (a > 0) {
			return true;
		}
		return false;
	}

	@Override
	public UserBean login(String account) {

		String sql = "select * from user where username=?";
		List list = jdbcTemplate.queryForList(sql, new Object[] { account });
		Iterator iterator = list.iterator();
		UserBean userBean = new UserBean();
		while (iterator.hasNext()) {
			Map userMap = (Map) iterator.next();
			userBean.setPassword(userMap.get("password").toString());
			userBean.setSalt(userMap.get("salt").toString());
		}
		if (userBean.getPassword() != null) {
			return userBean;
		}

		return null;
	}

	@Override
	public List<UserBean> QueryUserList() {

		String sql = "select * from user";
		List<UserBean> userList = new ArrayList<UserBean>();
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		UserBean userBean = null;
		while (iterator.hasNext()) {
			userBean = new UserBean();
			Map userMap = (Map) iterator.next();
			userBean.setId(StringUtil.StringToInt(userMap.get("id").toString()));
			userBean.setUsername(userMap.get("username").toString());
			userBean.setPic((userMap.get("pic").toString()));
			userBean.setSex(StringUtil.StringToInt(userMap.get("sex")
					.toString()));
			userBean.setSalt(userMap.get("salt").toString());
			userBean.setNickname(userMap.get("nickname").toString());
			userBean.setStatus(StringUtil.StringToInt(userMap.get("status")
					.toString()));
			userBean.setTruename(userMap.get("truename").toString());
			userList.add(userBean);

		}
		return userList;
	}

	@Override
	public UserBean queryUserBeanbyUsername(String... param) {
		String sql = "";
		if (param[1].equals("username")) {
			sql = "select * from user where username=" + param[0] + "";

		} else {
			sql = "select * from user where id=" + param[0] + "";
		}
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		UserBean userBean = null;
		while (iterator.hasNext()) {
			userBean = new UserBean();
			Map userMap = (Map) iterator.next();
			userBean.setId(StringUtil.StringToInt(userMap.get("id").toString()));
			userBean.setUsername(userMap.get("username").toString());
			userBean.setPic((userMap.get("pic").toString()));
			userBean.setSex(StringUtil.StringToInt(userMap.get("sex")
					.toString()));
			userBean.setSalt(userMap.get("salt").toString());
			userBean.setNickname(userMap.get("nickname").toString());
			userBean.setStatus(StringUtil.StringToInt(userMap.get("status")
					.toString()));
			userBean.setTruename(userMap.get("truename").toString());
			userBean.setCreateDate(userMap.get("create_date").toString());

		}

		return userBean;
	}

	@Override
	public void doBlock(int id, String status) {
		String sql = "update user set status=" + status + " where id=" + id
				+ "";
		int a = jdbcTemplate.update(sql);
	}

}
