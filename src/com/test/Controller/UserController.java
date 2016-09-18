package com.test.Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.test.Dao.UserDao;
import com.test.Model.UserBean;
import com.test.util.Constants;
import com.test.util.DateUtil;
import com.test.util.MD5;
import com.test.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Value("#{configProperties['pic_upload_path']}")
	private String pic_upload_path;
	@Value("#{configProperties['pic_show_path']}")
	private String pic_show_path;

	@Autowired
	UserDao userDao;

	@RequestMapping("/checkaccout.do")
	public void checkaccount(String account, HttpServletResponse resp)
			throws IOException {
		boolean flag = userDao.checkReg(account);
		String meg = "";
		if (!flag) {
			meg = "用户名已经存在";
		}
		PrintWriter pw = resp.getWriter();
		pw.write(JSON.toJSONString(meg));
		pw.flush();
		pw.close();
	}

	@RequestMapping("/reg.do")
	public String reg(HttpServletRequest req, HttpServletResponse reps,
			Model model) throws Exception {
		UserBean userBean = dealForm(req, reps);
		if (!userDao.checkReg(userBean.getUsername())) {
			// 用户名已经存在
			model.addAttribute("status", "2");

		} else {
			// 随机数 盐值
			String salt = StringUtil.getRandomString(10);
			userBean.setSalt(salt);

			// MD5 加密后的密码
			String md5Pwd = MD5.GetMD5Code(MD5.GetMD5Code(userBean
					.getPassword()) + salt);
			userBean.setPassword(md5Pwd);

			boolean flag = userDao.reg(userBean);
			if (flag) {
				model.addAttribute("status", "1");

			}
		}
		// http://localhost:8080/SpringMVC/user/add.jsp?status=1
		return "redirect:../front/user/add.jsp";
	}

	@RequestMapping("/login.do")
	@SuppressWarnings("all")
	public String login(String account, String password, Model model,
			HttpServletRequest req, HttpSession session) {

		UserBean userBean = userDao.login(account);

		if (userBean == null) {
			// 用户不存在
			model.addAttribute("status", "2");
			return "redirect:../front/user/login.jsp";
		} else {
			String userpassword = userBean.getPassword();
			String md5pwd = MD5.GetMD5Code(MD5.GetMD5Code(password)
					+ userBean.getSalt());
			if (md5pwd.equals(userBean.getPassword())) {
				session.setAttribute(Constants.SESSION_USER_BEAN, userBean);
				Enumeration<String> enumer = session.getAttributeNames();
				while (enumer.hasMoreElements()) {
					String string = (String) enumer.nextElement();
					System.out.println(session.getAttribute(string));
					System.out.println(string);

				}
				return "redirect:../show/tolist.do";
			} else {
				// 用户名密码不匹配
				model.addAttribute("status", "1");
				return "redirect:../front/user/login.jsp";
			}

		}

	}

	@RequestMapping("/tolist.do")
	public String tolist(Model model) {
		List<UserBean> userBeans = userDao.QueryUserList();
		model.addAttribute("userBeans", userBeans);
		// return "redirect:../admin/frontUser/listUsers.jsp";
		return "admin/frontUser/listUsers";
	}

	@RequestMapping("/details.do")
	public String details(String search, String id, HttpServletRequest req,
			HttpServletResponse reps, Model model) {
		UserBean userBean = null;
		String ids = req.getParameter("id");
		if (id != null) {
			userBean = userDao.queryUserBeanbyUsername(id, "id");
			model.addAttribute("status", "1");
		} else {
			userBean = userDao.queryUserBeanbyUsername(search, "username");
		}

		if (userBean != null) {

			req.setAttribute("userBean", userBean);
		} else {

			model.addAttribute("status", "0");
		}

		return "admin/frontUser/blockUser";

	}

	@RequestMapping("/blockuser.do")
	public String blockuser(String id, String status, String flag,
			HttpServletResponse resp, HttpServletRequest rep) {
		if (status.equals("0")) {
			userDao.doBlock(StringUtil.StringToInt(id), "1");
		} else {
			userDao.doBlock(StringUtil.StringToInt(id), "0");
		}
		if (flag != null) {
			return "redirect:tolist.do";
		}
		return "redirect:details.do?id=" + id + "";

	}

	public UserBean dealForm(HttpServletRequest req, HttpServletResponse reps)
			throws Exception {
		// 用于文件上传
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		UserBean userBean = null;
		try {
			List<FileItem> items = upload.parseRequest(req);
			Iterator iterator = items.iterator();
			PrintWriter pW = reps.getWriter();
			userBean = new UserBean();
			while (iterator.hasNext()) {
				FileItem item = (FileItem) iterator.next();
				// 处理表单数据
				if (item.isFormField()) {
					processFormField(item, pW, userBean);

				} else {
					// 处理非表单数据 文件上传
					processFileupload(item, pW, userBean);

				}

			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userBean;
	}

	/**
	 * 处理文件上传的 用户头像等
	 * 
	 * @param item
	 * @param pW
	 * @param userBean
	 * @throws Exception
	 */
	private void processFileupload(FileItem item, PrintWriter pW,
			UserBean userBean) throws Exception {
		String lastname = item.getName();
		lastname = lastname.substring(lastname.lastIndexOf(".") + 1,
				lastname.length());
		String picPath = pic_show_path + "/" + DateUtil.getDateStr() + "/"
				+ DateUtil.getTimeStr() + "." + lastname;
		long fileSize = item.getSize();
		if ("".equals(lastname) && fileSize == 0) {
			return;
		}

		// 新建文件夹，日期为文件夹名，时间为文件名
		File file = new File(pic_upload_path + "/" + DateUtil.getDateStr());
		file.mkdirs();
		File uploadFile = new File(pic_upload_path + "/"
				+ DateUtil.getDateStr() + "/" + DateUtil.getTimeStr() + "."
				+ lastname);

		// 写操作
		item.write(uploadFile);
		userBean.setPic(picPath);

	}

	private void processFormField(FileItem item, PrintWriter pW,
			UserBean userBean) throws UnsupportedEncodingException {

		String options = item.getFieldName();
		String value = new String(item.getString("UTF-8"));
		switch (options) {
		case "account":
			userBean.setUsername(value);
			break;
		case "nickname":
			userBean.setNickname(value);
			break;
		case "password":
			userBean.setPassword(value);

			break;
		case "sex":
			if (value.equals("男")) {
				userBean.setSex(0);
			} else {
				userBean.setSex(1);
			}

			break;
		case "truename":
			userBean.setTruename(value);
			break;

		default:
			break;
		}

	}

}
