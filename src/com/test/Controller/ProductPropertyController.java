package com.test.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.test.Dao.ProductPropertyDao;
import com.test.Dao.ProductTypeDao;
import com.test.Model.ProductPropertyBean;
import com.test.Model.ProductTypeBean;
import com.test.util.StringUtil;

@Controller
@RequestMapping("/property")
public class ProductPropertyController {

	@Autowired
	ProductPropertyBean productPropertyBean;
	@Autowired
	ProductPropertyDao productPropertyDao;
	@Autowired
	ProductTypeDao productTypeDao;

	@RequestMapping("/toadd.do")
	public String Toadd(HttpServletRequest req, HttpServletResponse resp,
			Model model) {

		String sta = req.getParameter("status");
		List<ProductTypeBean> typeList = productTypeDao.getTypeList(0);
		req.setAttribute("productTypeList", typeList);
		if (sta != null && "1".equals(sta)) {
			model.addAttribute("status", "1");
		}
		return "admin/property/add";
	}

	@RequestMapping("/add.do")
	public String add(String name, String[] productTypeId, String sort,
			String id, Model model) {
		int pid = 0;
		for (String pids : productTypeId) {
			pid = Math.max(pid, StringUtil.StringToInt(pids));

		}
		productPropertyBean.setProductTypeId(pid);
		productPropertyBean.setName(name);
		productPropertyBean.setSort(StringUtil.StringToInt(sort));
		boolean flag = false;
		// 当id不为空时 表示更新
		if (!id.equals("") && id != null) {
			productPropertyBean.setId(StringUtil.StringToInt(id));
			flag = productPropertyDao.update(productPropertyBean);
			if (flag) {
				model.addAttribute("status", "1");
			}
			return "redirect:tolist.do";

		} else {
			flag = productPropertyDao.add(productPropertyBean);
			if (flag) {
				model.addAttribute("status", "1");
			}
			return "redirect:toadd.do";
		}

	}

	@RequestMapping("/getType.do")
	public void getType(HttpServletResponse resp, String id) throws IOException {
		int parentId = StringUtil.StringToInt(id);
		List<ProductTypeBean> typeList = productTypeDao.getTypeList(parentId);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(typeList));
		out.flush();
		out.close();
	}

	@RequestMapping("/tolist.do")
	public String tolist(HttpServletRequest req) {

		List<ProductTypeBean> typeList = productTypeDao.getTypeList(0);
		req.setAttribute("productTypeList", typeList);
		return "admin/property/list";
	}

	@RequestMapping("/toupdate.do")
	public String toupdate(String id, HttpServletRequest req, Model model) {
		String sta = req.getParameter("status");
		System.out.println(id);
		ProductPropertyBean productPropertyBean = productPropertyDao
				.getPropertyBeanById(StringUtil.StringToInt(id));
		req.setAttribute("productPropertyBean", productPropertyBean);
		List<ProductTypeBean> typeList = productTypeDao.getTypeList(0);
		req.setAttribute("productTypeList", typeList);
		if (sta != null && "1".equals(sta)) {
			model.addAttribute("status", "1");
		}
		return "admin/property/add";
	}

}
