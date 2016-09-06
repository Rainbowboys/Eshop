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
import com.test.Dao.ProductOptionDao;
import com.test.Dao.ProductTypeDao;
import com.test.Model.ProductOptionBean;
import com.test.Model.ProductPropertyBean;
import com.test.Model.ProductTypeBean;
import com.test.util.StringUtil;

@Controller
@RequestMapping("/option")
public class ProductOptionController {
	@Autowired
	ProductOptionDao productOptionDao;
	@Autowired
	ProductOptionBean productOptionBean;
	@Autowired
	ProductTypeDao productTypeDao;

	@RequestMapping("/toadd.do")
	public String toAdd(Model model) {

		List<ProductTypeBean> productTypeList = productTypeDao.getTypeList(0);
		model.addAttribute("productTypeList", productTypeList);
		return "admin/option/add";
	}

	@RequestMapping("/add.do")
	public String Add(String name, String PropertyId, String sort, Model model) {
		productOptionBean.setName(name);
		productOptionBean.setPropertyId(StringUtil.StringToInt(PropertyId));
		productOptionBean.setSort(StringUtil.StringToInt(sort));
		boolean flag = productOptionDao.add(productOptionBean);
		if (flag) {
			model.addAttribute("status", "1");
		}
		return "redirect:toadd.do";
	}

	@RequestMapping("/getProperty.do")
	public void getProperty(String id, HttpServletRequest req,
			HttpServletResponse reqs, Model model) throws IOException {

		List<ProductPropertyBean> list = productOptionDao
				.getListProperty(StringUtil.StringToInt(id));
		PrintWriter pW = reqs.getWriter();
		pW.write(JSON.toJSONString(list));
		pW.flush();
		pW.close();
	}

	@RequestMapping("/tolist.do")
	public String tolist(HttpServletRequest req, Model model) {
		List<ProductTypeBean> productTypeList = productTypeDao.getTypeList(0);
		String sta = req.getParameter("status");
		req.setAttribute("productTypeList", productTypeList);
		if (sta != null && "1".equals(sta)) {
			model.addAttribute("status", "1");
		}
		return "admin/option/list";
	}

	@RequestMapping("/showOption.do")
	public void showOption(HttpServletResponse reps, HttpServletRequest req,
			String id) throws IOException {
		List<ProductOptionBean> list = productOptionDao
				.getOptionByProperty(StringUtil.StringToInt(id));
		PrintWriter pW = reps.getWriter();
		pW.write(JSON.toJSONString(list));
		pW.flush();
		pW.close();
	}

}
