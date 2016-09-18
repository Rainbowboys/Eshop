package com.test.DaoIMP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.test.Dao.ProductTypeDao;
import com.test.Model.ProductTypeBean;

public class ProductTypeDaoImp implements ProductTypeDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	private ProductTypeBean productTypeBean;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("all")
	@Override
	public List<ProductTypeBean> getTypeBeans(int parentId) {
		String sql = "select * from product_type where parent_id='" + parentId
				+ "'";

		List<ProductTypeBean> typeBeans = new ArrayList<ProductTypeBean>();
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Map beanMap = (Map) iterator.next();
			productTypeBean = new ProductTypeBean();
			int id = (Integer) beanMap.get("id");
			String name = (String) beanMap.get("name");
			productTypeBean.setId(id);
			productTypeBean.setName(name);
			typeBeans.add(productTypeBean);
		}
		return typeBeans;
	}

	@Override
	public boolean add(ProductTypeBean productTypeBean) {
		String sql = "insert into product_type(name,parent_id,sort,intro,create_date) values(?,?,?,?,?)";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_date = df.format(new Date());
		int a = jdbcTemplate.update(
				sql,
				new Object[] { productTypeBean.getName(),
						productTypeBean.getParentId(),
						productTypeBean.getSort(), productTypeBean.getIntro(),
						create_date });
		if (a > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * 获取一个分类 包含所有的父类 包含一级子类list
	 */

	@Override
	public ProductTypeBean getType(int id) {
		List<ProductTypeBean> list = getTypeList(id);
		ProductTypeBean productTypeBean = new ProductTypeBean();
		if (id == 0) {
			productTypeBean.setId(0);
		} else {
			productTypeBean = getTypeById(id);
		}
		productTypeBean.setChildBeans(list);
		return productTypeBean;
	}

	/**
	 * 通过父分类获取子分类列表
	 * 
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	@Override
	public List<ProductTypeBean> getTypeList(int parentId) {
		String sql = "select * from product_type where parent_id='" + parentId
				+ "'";
		List<ProductTypeBean> typeBeans = new ArrayList<ProductTypeBean>();
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		ProductTypeBean productTypebean = null;
		while (iterator.hasNext()) {
			Map beanMap = (Map) iterator.next();
			productTypebean = new ProductTypeBean();
			productTypebean.setId((Integer) beanMap.get("id"));
			productTypebean.setIntro((String) beanMap.get("intro"));
			productTypebean.setName((String) beanMap.get("name"));
			productTypebean.setSort((Integer) beanMap.get("sort"));
			productTypebean
					.setCreateDate(beanMap.get("create_date").toString());
			typeBeans.add(productTypebean);
		}

		return typeBeans;
	}

	/**
	 * 根据ID值获取类型
	 */
	@SuppressWarnings("rawtypes")
	public ProductTypeBean getTypeById(int typeId) {
		String sql = "select * from product_type where id='" + typeId + "'";
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		ProductTypeBean parentBean = null;
		while (iterator.hasNext()) {
			parentBean = new ProductTypeBean();
			Map beanMap = (Map) iterator.next();
			int parentId = (Integer) beanMap.get("parent_id");
			parentBean = getTypeById(parentId);
			parentBean.setParentId(parentId);
			productTypeBean.setId((Integer) beanMap.get("id"));
			productTypeBean.setIntro((String) beanMap.get("intro"));
			productTypeBean.setName((String) beanMap.get("name"));
			productTypeBean.setSort((Integer) beanMap.get("sort"));
			productTypeBean
					.setCreateDate(beanMap.get("create_date").toString());
			productTypeBean.setParentBean(parentBean);
		}
		return productTypeBean;
	}

	@Override
	public boolean update(ProductTypeBean productTypeBean) {
		String sql = "update product_type set name='"
				+ productTypeBean.getName() + "', sort='"
				+ productTypeBean.getSort() + "', intro='"
				+ productTypeBean.getIntro() + "' where id='"
				+ productTypeBean.getId() + "'";
		int a = jdbcTemplate.update(sql);
		if (a > 0) {
			return true;

		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean flag = true;
		// 删除子分类
		List<ProductTypeBean> typelis = getTypeList(id);
		for (ProductTypeBean productTypeBean : typelis) {
			boolean f1 = delete(productTypeBean.getId());
			if (!f1) {
				flag = false;
			}
		}
		String sql = "delete from product_type where id='" + id + "'";
		int a = jdbcTemplate.update(sql);
		if (a > 0) {
			flag = true;
		}
		return flag;
	}
}
