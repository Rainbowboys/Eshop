package com.test.Dao;

import java.util.List;

import com.test.Model.ProductTypeBean;

public interface ProductTypeDao {
	public List<ProductTypeBean> getTypeBeans(int parentId);
	public boolean add(ProductTypeBean productTypeBean);
	public List<ProductTypeBean> getTypeList(int id);
	public ProductTypeBean getType(int id);
	public ProductTypeBean getTypeById(int id);
	public boolean update(ProductTypeBean productTypeBean);
	public boolean delete(int id);

}
