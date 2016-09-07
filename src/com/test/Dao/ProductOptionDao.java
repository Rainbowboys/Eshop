package com.test.Dao;

import java.util.List;

import com.test.Model.ProductOptionBean;
import com.test.Model.ProductPropertyBean;

public interface ProductOptionDao {

	public boolean add(ProductOptionBean productOptionBean);

	public List<ProductPropertyBean> getListProperty(int typeId);

	public List<ProductOptionBean> getOptionByProperty(int propertyId);

	public ProductOptionBean getOptionByID(int id);

	public boolean update(ProductOptionBean productOptionBean);

	public boolean delete(int optionId);

}
