package com.test.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.test.Dao.ProductTypeDao;
import com.test.Model.ProductTypeBean;
import com.test.util.StringUtil;

@Controller
@RequestMapping("/goods")
public class ProductTypeController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	ProductTypeDao productTypeDao;
	@Autowired
	ProductTypeBean productTypeBean;

	@RequestMapping("/toAdd.do")
	private String toAdd(Model model) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		model.addAttribute("productTypeList", productTypeDao.getTypeBeans(0));
		String status = request.getParameter("status");
		model.addAttribute("status", status);
		return "admin/product/type/add";
	}

	@RequestMapping("/list.do")
	private String list(int id, Model model) {
		productTypeBean = productTypeDao.getType(id);
		model.addAttribute("productTypeBean", productTypeBean);
		return "admin/product/type/list";
	}

	@RequestMapping("/update.do")
	private String update(int id, Model model) {
		ProductTypeBean productTypeBean = productTypeDao.getTypeById(id);
		model.addAttribute("productTypeBean", productTypeBean);
		List<ProductTypeBean> typeList = productTypeDao.getTypeList(0);
		model.addAttribute("productTypeList", typeList);
		return "admin/product/type/add";
	}

	@RequestMapping("/add.do")
	private String add(String name, String[] parentId, String sort,
			String desc, String id, Model model)
			throws UnsupportedEncodingException {
		int Id = StringUtil.StringToInt(id);
		int Sort = StringUtil.StringToInt(sort);

		int parent_Id = 0;
		for (String parentIdS : parentId) {
			parent_Id = Math.max(parent_Id, StringUtil.StringToInt(parentIdS));
		}
		boolean flag;
		if (Id == 0) {
			// 添加记录
			productTypeBean.setSort(Sort);
			productTypeBean.setName(name);
			productTypeBean.setIntro(desc);
			productTypeBean.setParentId(parent_Id);
			flag = productTypeDao.add(productTypeBean);

		} else {
			productTypeBean.setId(Id);
			productTypeBean.setSort(Sort);
			productTypeBean.setName(name);
			productTypeBean.setIntro(desc);
			productTypeBean.setParentId(parent_Id);
			flag = productTypeDao.update(productTypeBean);
		}
		if (flag) {
			model.addAttribute("status", true);

		} else {
			model.addAttribute("status", false);
		}
		return "redirect:toAdd.do";
	}

	@RequestMapping("/delete.do")
	private String delete(int id,int pid, Model model) {
		boolean status = productTypeDao.delete(id);
		model.addAttribute("id", pid);
		return "redirect:list.do";

	}

	@RequestMapping("/getType.do")
	private void getType(int id, HttpServletResponse resp) throws IOException {

		List<ProductTypeBean> typeList = productTypeDao.getTypeList(id);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(typeList));
		out.flush();
		out.close();
	}
}
