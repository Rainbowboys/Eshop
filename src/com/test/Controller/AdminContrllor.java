package com.test.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.Dao.AdminDao;
import com.test.Model.AdminBean;
import com.test.Model.PagingBean;
import com.test.util.Constants;
import com.test.util.MD5;
import com.test.util.StringUtil;

@Controller
@RequestMapping("/admin")
public class AdminContrllor extends BaseController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	AdminDao adminDao;

	/**
	 * 登录功能
	 * 
	 * @param username
	 * @param password
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login.do")
	private String login(String username, String password, HttpSession session,
			Model model) throws Exception {
		request.setCharacterEncoding("utf-8");
		String pwd = MD5.GetMD5Code(password);
		AdminBean adminBean = null;
		adminBean = adminDao.checkLogin(username, pwd);
		if (adminBean != null) {
			// 登录成功
			session.setAttribute(Constants.SESSION_ADMIN_BEAN, adminBean);
			return "admin/main";

		} else {
			model.addAttribute("status", "1");
			return "redirect:login.jsp";
		}
	}

	/**
	 * 添加管理员功能和更新
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/addUser.do")
	private String addUser(String username, String password, String updateId,
			Model model) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		AdminBean adminBean = atx.getBean("AdminBean", AdminBean.class);
		adminBean.setUsername(username);
		String salt = StringUtil.getRandomString(10);
		String md5Pwd = MD5.GetMD5Code(MD5.GetMD5Code(password) + salt);
		adminBean.setSalt(salt);
		adminBean.setPassword(md5Pwd);
		SimpleDateFormat createDate = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		adminBean.setCreateDate(createDate.format(new Date()));
		// 如果updateId不为空则添加新管理员
		if (!updateId.equals("")) {
			// 修改
			int id = StringUtil.StringToInt(updateId);
			adminBean.setId(id);
			boolean flag = true;
			if (!username.equals(adminDao.getById(id).getUsername())) {
				flag = adminDao.checkReg(username);
			}
			if (flag) {
				adminDao.update(adminBean);
				// 修改成功 写入数据库
				model.addAttribute("status", "2");
				return "redirect:list.do";
			} else {
				// 修改失败，跳回
				model.addAttribute("status", "2");
				return "admin/addUser";
			}

		} else {
			// 添加新用户
			boolean flag = adminDao.checkReg(username);
			if (flag) {
				adminDao.save(adminBean);
				// 添加成功
				model.addAttribute("status", "1");
				return "admin/addUser";
			} else {
				// 添加失败
				model.addAttribute("status", "2");
				return "admin/addUser";

			}
		}
	}

	/**
	 * 修改管理员
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/update.do")
	private String toUpdate(String id, Model model) throws ServletException,
			IOException {
		request.setCharacterEncoding("utf-8");
		int ids = StringUtil.StringToInt(id);
		AdminBean updatebean = adminDao.getById(ids);
		model.addAttribute("updatebean", updatebean);
		if (ids > 1) {
			return "admin/addUser";
		} else if (ids == 1) {
			model.addAttribute("status", "1");
			return "redirect:list.do";
		} else {
			model.addAttribute("status", "2");
			return "redirect:list.do";
		}

	}

	/**
	 * 查看管理员
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/list.do")
	private String listUser(String status, String currentPage, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int CurrentPage = StringUtil.StringToInt(currentPage);
		int countSize = adminDao.getCount();
		PagingBean pagingBean = new PagingBean(CurrentPage, countSize,
				Constants.PAGE_SIZE_1);
		List<AdminBean> adminBeans = adminDao.getListByPage(CurrentPage
				* Constants.PAGE_SIZE_1, Constants.PAGE_SIZE_1);
		pagingBean.setPrefixUrl("list.do");
		pagingBean.setAnd(true);
		model.addAttribute(Constants.SESSION_ADMIN_BEANS, adminBeans);
		model.addAttribute("pagingBean", pagingBean);
		if (status != null) {
			model.addAttribute("status", status);
			return "admin/listUsers";
		} else {
			// return "redirect:listUsers.jsp";
			return "admin/listUsers";
		}
	}
	
	/**
	 * 删除管理员
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("delete.do")
	private String  delete(String id,Model model)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int ids = StringUtil.StringToInt(id);
		if (ids > 1) {
			adminDao.delete(ids);
			model.addAttribute("status", "3");
			return "redirect:list.do";
			
		} else if (ids == 1) {
			model.addAttribute("status", "1");
			return "redirect:list.do";
		} else {
			model.addAttribute("status", "2");
			return "redirect:list.do";
		}
	}


}
