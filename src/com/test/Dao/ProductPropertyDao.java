package com.test.Dao;

import com.test.Model.ProductPropertyBean;

public interface ProductPropertyDao {
	// 添加商品属性
	public boolean add(ProductPropertyBean productPropertyBean);

	public boolean update(ProductPropertyBean productPropertyBean);

	public boolean delete(int propertyId);

	public ProductPropertyBean getPropertyBeanById(int id);
}
