package com.test.DaoIMP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.test.Dao.AdminDao;
import com.test.Model.AdminBean;
import com.test.util.MD5;

public class AdminDaoimp implements AdminDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 检测登录
	 */
	@SuppressWarnings("all")
	public AdminBean checkLogin(String username, final String password) {
		String sql = "select * from admin where username='" + username + "'";
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		AdminBean adminBean = null;
		while (iterator.hasNext()) {
			Map userMap = (Map) iterator.next();
			String pwdString = (String) userMap.get("password");
			String pwdcString = MD5.GetMD5Code(password
					+ (String) userMap.get("salt"));
			System.out.println(pwdcString + "            " + pwdString);
			if (pwdString.equals(pwdcString)) {
				adminBean = new AdminBean();
				adminBean.setId((Integer) userMap.get("id"));
				adminBean.setUsername((String) userMap.get("username"));
				adminBean.setPassword((String) userMap.get("password"));
				adminBean.setSalt((String) userMap.get("salt"));
				adminBean.setCreateDate(userMap.get("create_date").toString());
			}
			return adminBean;

		}
		return null;
	}

	/**
	 * 根据ID查询用户
	 */
	@SuppressWarnings("all")
	@Override
	public AdminBean getById(int id) {
		String sql = "select * from admin where id =" + id;
		AdminBean adminBean = null;
		try {
			List list = jdbcTemplate.queryForList(sql);
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				adminBean = new AdminBean();
				Map beanMap = (Map) iterator.next();
				adminBean.setId((Integer) beanMap.get("id"));
				adminBean.setUsername(beanMap.get("username").toString());
				adminBean.setPassword(beanMap.get("password").toString());
				adminBean.setSalt(beanMap.get("salt").toString());
				adminBean.setCreateDate(beanMap.get("create_date").toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return adminBean;
	}

	@SuppressWarnings("all")
	/**
	 * 、检测用户名是否存在
	 */
	@Override
	public boolean checkReg(String name) {
		boolean flag = true;
		String sql = "select username from admin";
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
	public void update(AdminBean adminBean) {
		String sql = "update admin set salt='" + adminBean.getSalt()
				+ "',username='" + adminBean.getUsername() + "',password='"
				+ adminBean.getPassword() + "' where id='" + adminBean.getId()
				+ "'";
		jdbcTemplate.update(sql);

	}

	@Override
	public void save(AdminBean adminBean) {
		String sql = "insert into admin(username,password,salt,create_date) values(?,?,?,?)";
		jdbcTemplate.update(
				sql,
				new Object[] { adminBean.getUsername(),
						adminBean.getPassword(), adminBean.getSalt(),
						adminBean.getCreateDate() });

	}

	@SuppressWarnings("unused")
	@Override
	public int getCount() {
		int size = 0;
		String sql = "select count(*) count from admin";
		size = jdbcTemplate.queryForInt(sql);
		return size;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<AdminBean> getListByPage(int start, int size) {
		String sql = "select * from admin limit " + start + " , " + size;

		List<AdminBean> adminBeans = new ArrayList<AdminBean>();
		AdminBean adminBean = null;
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		try {
			while (iterator.hasNext()) {
				adminBean = new AdminBean();
				Map beanMap = (Map) iterator.next();
				adminBean.setId((Integer) beanMap.get("id"));
				adminBean.setUsername(beanMap.get("username").toString());
				adminBean.setPassword(beanMap.get("password").toString());
				adminBean.setSalt(beanMap.get("salt").toString());
				adminBean.setCreateDate(beanMap.get("create_date").toString());
				adminBeans.add(adminBean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminBeans;
	}

	@Override
	public void delete(int ids) {
		String sql = "delete from admin where id = " + ids;
		jdbcTemplate.execute(sql);
	}
}
