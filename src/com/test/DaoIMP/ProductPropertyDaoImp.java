package com.test.DaoIMP;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.test.Dao.ProductPropertyDao;
import com.test.Model.ProductPropertyBean;
import com.test.util.StringUtil;

public class ProductPropertyDaoImp implements ProductPropertyDao {

	JdbcTemplate jdbcTemplate;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String create_date = null;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean add(ProductPropertyBean productPropertyBean) {
		String sql = "insert into product_type_property(name,product_type_id,sort,create_date) values(?,?,?,?)";
		create_date = df.format(new Date());
		boolean flag = false;
		int a = 0;
		a = jdbcTemplate.update(sql,
				new Object[] { productPropertyBean.getName(),
						productPropertyBean.getProductTypeId(),
						productPropertyBean.getSort(), create_date });
		if (a > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean update(ProductPropertyBean productPropertyBean) {
		String sql = "update product_type_property set name=?,product_type_id=?,sort=?,create_date=? where id=?";
		create_date = df.format(new Date());
		int a = 0;
		a = jdbcTemplate.update(sql,
				new Object[] { productPropertyBean.getName(),
						productPropertyBean.getProductTypeId(),
						productPropertyBean.getSort(), create_date,
						productPropertyBean.getId() });

		if (a > 0) {
			return true;
		}
		return false;

	}

	@Override
	public boolean delete(int propertyId) {

		String sql = "delete from product_type_property where id=" + propertyId
				+ " ";
		int a = 0;
		a = jdbcTemplate.update(sql);
		if (a > 0) {
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("all")
	public ProductPropertyBean getPropertyBeanById(int id) {
		String sql = "select * from product_type_property where id=" + id + "";
		ProductPropertyBean productPropertyBean = null;
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			productPropertyBean = new ProductPropertyBean();
			Map properMap = (Map) iterator.next();
			productPropertyBean.setId(id);
			productPropertyBean.setName(properMap.get("name").toString());
			productPropertyBean.setProductTypeId(StringUtil
					.StringToInt(properMap.get("product_type_id").toString()));
			productPropertyBean.setSort(StringUtil.StringToInt(properMap.get(
					"sort").toString()));
		}

		return productPropertyBean;
	}
}
