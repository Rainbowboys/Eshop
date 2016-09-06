package com.test.DaoIMP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.test.Dao.ProductOptionDao;
import com.test.Model.ProductOptionBean;
import com.test.Model.ProductPropertyBean;
import com.test.util.StringUtil;

public class ProducyOptionDaoImp implements ProductOptionDao {

	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean add(ProductOptionBean productOptionBean) {
		String sql = "insert into product_type_property_option(name,product_type_property_id,sort,create_date) values(?,?,?,?)";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_data = df.format(new Date());

		boolean flag = false;
		int a = 0;

		a = jdbcTemplate.update(sql, new Object[] {
				productOptionBean.getName(), productOptionBean.getPropertyId(),
				productOptionBean.getSort(), create_data });

		if (a > 0) {
			flag = true;
		}

		return flag;
	}

	@Override
	@SuppressWarnings("all")
	public List<ProductPropertyBean> getListProperty(int typeId) {
		String sql = "select * from product_type_property where product_type_id="
				+ typeId + "";

		List<ProductPropertyBean> listProductProperty = new ArrayList<>();
		ProductPropertyBean productPropertyBean = null;
		List list = jdbcTemplate.queryForList(sql);
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Map object = (Map) iterator.next();
			productPropertyBean = new ProductPropertyBean();
			productPropertyBean.setName(object.get("name").toString());
			productPropertyBean.setId(StringUtil.StringToInt(object.get("id")
					.toString()));
			productPropertyBean.setSort(StringUtil.StringToInt(object.get(
					"sort").toString()));
			productPropertyBean.setCreateDate(object.get("create_date")
					.toString());
			listProductProperty.add(productPropertyBean);
		}
		return listProductProperty;
	}

	@SuppressWarnings("all")
	@Override
	public List<ProductOptionBean> getOptionByProperty(int propertyId) {
		String sql = "select * from product_type_property_option where product_type_property_id="
				+ propertyId + "";

		ProductOptionBean productOptionBean = null;

		List list = new ArrayList<>();
		List propertyoptionList = jdbcTemplate.queryForList(sql);
		Iterator iterator = propertyoptionList.iterator();
		while (iterator.hasNext()) {
			productOptionBean = new ProductOptionBean();
			Map optionMap = (Map) iterator.next();
			productOptionBean.setName(optionMap.get("name").toString());
			productOptionBean.setSort(StringUtil.StringToInt(optionMap.get(
					"sort").toString()));
			productOptionBean.setPropertyId(propertyId);
			productOptionBean.setCreateDate(optionMap.get("create_date")
					.toString());
			list.add(productOptionBean);

		}

		return list;
	}
}
