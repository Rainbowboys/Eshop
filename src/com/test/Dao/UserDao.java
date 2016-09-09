package com.test.Dao;

import java.util.List;

import com.test.Model.UserBean;

public interface UserDao {

	// 检测用户名是否存在
	public boolean checkReg(String account);

	// 用户注册
	public boolean reg(UserBean userBean);

	public UserBean login(String accountString);

	// 查询所有用户
	public List<UserBean> QueryUserList();

	// 根据用户名查询用户
	public UserBean queryUserBeanbyUsername(String... param);

	// 操作用户状态
	public void doBlock(int id, String status);

}
