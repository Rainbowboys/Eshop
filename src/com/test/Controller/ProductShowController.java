package com.test.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.Dao.ProductDao;
import com.test.Dao.ProductOptionDao;
import com.test.Dao.ProductTypeDao;
import com.test.Model.ProductBean;
import com.test.Model.ProductTypeBean;
import com.test.util.StringUtil;

@Controller
@RequestMapping("/show")
public class ProductShowController {
	@Resource
	private ProductDao productDao;
	@Resource
	private ProductTypeDao productTypeDao;
	@Resource
	private ProductOptionDao productOptionDao;

	// ApplicationContext ac = new ClassPathXmlApplicationContext(
	// "classpath:../spring-servlet.xml");

	@RequestMapping("/tolist.do")
	public String list(HttpServletRequest req, HttpServletResponse reps,
			Model model) {
		List productBeans = productDao.productBeanlist();
		model.addAttribute("productBeans", productBeans);
		return "front/productShow/list";
	}

	@RequestMapping("/sort.do")
	public String sort(HttpServletRequest req, String id) {
		List<ProductBean> list = new ArrayList<>();
		//List<ProductBean> beanslist = getListById(StringUtil.StringToInt(id),
//				list);
		System.out.println(list);

		return "front/productShow/list";

	}

	

	@RequestMapping("/info.do")
	public String info(HttpServletRequest req, HttpServletResponse reps,
			String id) {

		ProductBean productBean = new ProductBean();
		productBean = productDao.getProductBeanById(StringUtil.StringToInt(id));
		req.setAttribute("productBean", productBean);
		return "front/productShow/info";

	}

}
