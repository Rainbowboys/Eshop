package com.test.Tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.Dao.ProductTypeDao;
import com.test.Model.ProductTypeBean;

public class TypeTag extends SimpleTagSupport {
	// 路径
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	ApplicationContext ac = new ClassPathXmlApplicationContext(
			"classpath:applicationContext.xml");
	ProductTypeDao productTypeDao = (ProductTypeDao) ac
			.getBean("ProductTypeDao");

	@Override
	public void doTag() throws JspException, IOException {

		List<ProductTypeBean> typeBeans0 = productTypeDao.getTypeBeans(0);
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='typeTag' id='typeTag'>");
		sb.append(getTop(typeBeans0));
		sb.append("<div class='wrap' hidden><div class='all-sort-list' id='list'>");
		sb.append(getStr0(typeBeans0));
		sb.append("</div></div>");
		sb.append("</div>");
		try {
			getJspContext().getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取一级菜单栏
	 * 
	 * @param typeBeans1
	 * @return
	 */
	private String getStr0(List<ProductTypeBean> typeBeans1) {
		StringBuffer sb = new StringBuffer();
		if (typeBeans1 != null) {
			sb.append("<div class='item-list clearfix'>");
			sb.append("<div class='subitem'>");
			for (ProductTypeBean item : typeBeans1) {
				sb.append("<dl class='fore'>");
				sb.append("<dt><a href='").append(path)
						.append("/show/sort.do?id=").append(item.getId())
						.append("'>").append(item.getName())
						.append("</a></dt>");
				List<ProductTypeBean> childBeans = productTypeDao
						.getTypeBeans(item.getId());
				if (!childBeans.isEmpty()) {
					sb.append(getStr2(childBeans));
				}
				sb.append("</dl>");
			}
			sb.append("</div></div>");
		}
		return sb.toString();

	}

	/**
	 * 获取三级分类
	 * 
	 * @param childBeans
	 * @return
	 */

	private Object getStr2(List<ProductTypeBean> typeBeans2) {
		StringBuffer sb = new StringBuffer();
		if (typeBeans2 != null) {
			sb.append("<dd>");
			for (ProductTypeBean item : typeBeans2) {
				sb.append("<em>");
				sb.append("<a href='").append(path).append("/show/sort.do?id=")
						.append(item.getId()).append("'>")
						.append(item.getName()).append("</a>");
				sb.append("</em>");
			}
			sb.append("</dd>");
		}
		return sb.toString();

	}

	/**
	 * 获取分类菜单栏顶栏
	 * 
	 * @param typeBeans0
	 * @return
	 */

	private String getTop(List<ProductTypeBean> typeBeans0) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='menu'>");
		sb.append("<div class='all-sort'><h2><a href='#'  class='menu1'>全部商品分类</a></h2></div>");
		sb.append("<div class='nav'><ul class='clearfix'>");
		sb.append("<li><a href='#' class='current'>首页</a></li>");
		if (typeBeans0 != null) {
			for (ProductTypeBean item : typeBeans0) {
				sb.append("<li><a href='").append(path)
						.append("/show/sort.do?id=").append(item.getId())
						.append("'>").append(item.getName())
						.append("</a></li>");
			}
		}
		sb.append("</ul></div>");
		sb.append("</div>");
		return sb.toString();

	}

}
