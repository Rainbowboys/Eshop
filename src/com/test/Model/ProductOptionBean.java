package com.test.Model;

public class ProductOptionBean {

	private int id;
	private String name;
	private int sort;
	private String createDate;
	private int propertyId;
	private ProductPropertyBean productPropertyBean;

	// 构造方法

	public ProductOptionBean() {

	}

	public ProductOptionBean(int sort, int productPropertyId, String name) {
		this.setSort(sort);
		this.setName(name);
		this.setPropertyId(productPropertyId);
	}

	public ProductOptionBean(int id, int sort, int productPropertyId,
			String name, String createDate) {
		this.setId(id);
		this.setSort(sort);
		this.setName(name);
		this.setPropertyId(productPropertyId);
		this.setCreateDate(createDate);
	}

	public ProductOptionBean(int id, int sort, int productPropertyId,
			String name) {
		this.setId(id);
		this.setSort(sort);
		this.setName(name);
		this.setPropertyId(productPropertyId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public ProductPropertyBean getProductPropertyBean() {
		return productPropertyBean;
	}

	public void setProductPropertyBean(ProductPropertyBean productPropertyBean) {
		this.productPropertyBean = productPropertyBean;
	}

}
