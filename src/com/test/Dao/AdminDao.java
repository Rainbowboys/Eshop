package com.test.Dao;

import java.util.List;

import com.test.Model.AdminBean;

public interface AdminDao {
	// 检测登录
	public AdminBean checkLogin(String username, String password);

	// 更具ID获取AdminBean
	public AdminBean getById(int id);

	// 检测用户名是否存在
	public boolean checkReg(String name);

	// 更新修改admin
	public void update(AdminBean adminBean);

	// 添加新用户
	public void save(AdminBean adminBean);

	// 统计数量
	public int getCount();

	// 返回指定页数list
	public List<AdminBean> getListByPage(int i, int pageSize1);

	public void delete(int ids);
}
