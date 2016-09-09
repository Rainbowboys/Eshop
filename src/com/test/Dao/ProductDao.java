package com.test.Dao;

import java.util.List;

import com.test.Model.ProductBean;

public interface ProductDao {
	public boolean add(ProductBean productBean);

	public boolean update(ProductBean productBean);

	public ProductBean getProductBeanById(int productId);

	public List<ProductBean> productBeanlist();

	public boolean delete(int stringToInt);

}
