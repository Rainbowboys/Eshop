package com.test.DaoIMP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.test.Dao.ProductDao;
import com.test.Model.ProductBean;
import com.test.util.DateUtil;
import com.test.util.StringUtil;

public class ProductDaoImp implements ProductDao {

	JdbcTemplate jdbcTemplate;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean add(ProductBean productBean) {
		String sql = "insert into product(name,original_price,price,intro,product_type_id,number,pic,product_properties,create_date) values(?,?,?,?,?,?,?,?,?)";

		String create_date = df.format(new Date());
		int a = 0;
		a = jdbcTemplate.update(
				sql,
				new Object[] { productBean.getName(),
						productBean.getOriginalPrice(), productBean.getPrice(),
						productBean.getIntro(), productBean.getProductTypeId(),
						productBean.getNumber(), productBean.getPic(),
						productBean.getProductProperties(), create_date });
		if (a > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean update(ProductBean productBean) {
		String sql = "update product set name=? ,original_price=? ,price=?,intro=?,product_type_id=?,number=?,pic=?,product_properties=?,create_date=? where id=?";
		int a = 0;
		a = jdbcTemplate
				.update(sql,
						new Object[] { productBean.getName(),
								productBean.getOriginalPrice(),
								productBean.getPrice(), productBean.getIntro(),
								productBean.getProductTypeId(),
								productBean.getNumber(), productBean.getPic(),
								productBean.getProductProperties(),
								DateUtil.getDate() }, productBean.getId());
		if (a > 0) {
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("all")
	public ProductBean getProductBeanById(int productId) {
		String sql = "select * from product where id=" + productId + " ";

		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		ProductBean productBean = null;

		while (iterator.hasNext()) {
			Map productMap = (Map) iterator.next();
			productBean = new ProductBean();
			productBean.setId(StringUtil.StringToInt(productMap.get("id")
					.toString()));
			productBean.setName(productMap.get("name").toString());
			System.out.println(productMap.get("original_price").toString());
			productBean.setOriginalPrice(StringUtil.strToFlo(productMap.get(
					"original_price").toString()));
			productBean.setPrice(StringUtil.strToFlo(productMap.get("price")
					.toString()));
			productBean.setIntro(productMap.get("intro").toString());
			productBean.setProductTypeId(StringUtil.StringToInt(productMap.get(
					"product_type_id").toString()));
			productBean.setNumber(StringUtil.StringToInt(productMap.get(
					"number").toString()));

			productBean.setProductProperties(productMap.get(
					"product_properties").toString());
			productBean.setCreateDate(productMap.get("create_date").toString());
			productBean.setPic(productMap.get("pic").toString());

		}
		return productBean;
	}

	@Override
	public List<ProductBean> productBeanlist() {

		String sql = "select * from product";
		List<ProductBean> productBeanlist = new ArrayList<>();
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		ProductBean productBean = null;
		while (iterator.hasNext()) {
			Map productMap = (Map) iterator.next();
			productBean = new ProductBean();
			productBean.setId(StringUtil.StringToInt(productMap.get("id")
					.toString()));
			productBean.setName(productMap.get("name").toString());
			productBean.setOriginalPrice(StringUtil.strToFlo(productMap.get(
					"original_price").toString()));
			productBean.setPrice(StringUtil.strToFlo(productMap.get("price")
					.toString()));
			productBean.setIntro(productMap.get("intro").toString());
			productBean.setProductTypeId(StringUtil.StringToInt(productMap.get(
					"product_type_id").toString()));
			productBean.setNumber(StringUtil.StringToInt(productMap.get(
					"number").toString()));

			productBean.setProductProperties(productMap.get(
					"product_properties").toString());
			productBeanlist.add(productBean);

		}

		return productBeanlist;
	}

	@Override
	public boolean delete(int id) {
		String sql = "delete from product where id=" + id + "";
		int a = jdbcTemplate.update(sql);
		if (a > 0) {
			return true;
		}
		return false;
	}
}
