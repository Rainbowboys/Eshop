package com.test.Model;

public class ProductPropertyBean {
	private int id;
	private int sort;
	private int productTypeId;
	private String name;
	private String createDate;

	public ProductPropertyBean() {

	}

	public ProductPropertyBean(int sort, int productTypeId, String name) {
		this.setSort(sort);
		this.setProductTypeId(productTypeId);
		this.setName(name);
	}

	public ProductPropertyBean(int id, int sort, int productTypeId, String name) {
		this.setId(id);
		this.setSort(sort);
		this.setProductTypeId(productTypeId);
		this.setName(name);
	}

	public ProductPropertyBean(int id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public ProductPropertyBean(int id, int sort, int productTypeId,
			String name, String createDate) {
		super();
		this.id = id;
		this.sort = sort;
		this.productTypeId = productTypeId;
		this.name = name;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
