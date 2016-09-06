package com.test.DaoIMP;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;

import com.test.Dao.ProductPropertyDao;
import com.test.Model.ProductPropertyBean;

public class ProductPropertyDaoImp implements ProductPropertyDao {

	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean add(ProductPropertyBean productPropertyBean) {
		String sql = "insert into product_type_property(name,product_type_id,sort,create_date) values(?,?,?,?)";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_date = df.format(new Date());
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
}
