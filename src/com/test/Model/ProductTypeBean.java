package com.test.Model;

import java.util.List;

public class ProductTypeBean {
	private int id;
	private String name;
	private int parentId;
	private int sort;
	private String intro;
	private String createDate;
	private List<ProductTypeBean> childBeans;
	private ProductTypeBean parentBean;
	public ProductTypeBean(){
	}

	public ProductTypeBean(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	public ProductTypeBean(int sort,int parentId,String name,String intro){
		this.setSort(sort);
		this.setParentId(parentId);
		this.setName(name);
		this.setIntro(intro);
	}
	public ProductTypeBean(int id,int sort,int parentId,String name,String intro){
		this.setId(id);
		this.setSort(sort);
		this.setParentId(parentId);
		this.setName(name);
		this.setIntro(intro);
	}
	public ProductTypeBean(int id, String name,int sort, String intro, String createDate) {
		this.setId(id);
		this.setName(name);
		this.setSort(sort);
		this.setIntro(intro);
		this.setCreateDate(createDate);
	}
	public ProductTypeBean(int id,int sort,ProductTypeBean parentBean,String name,String intro,String createDate){
		this.setId(id);
		this.setSort(sort);
		this.setParentBean(parentBean);
		this.setName(name);
		this.setIntro(intro);
		this.setCreateDate(createDate);
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<ProductTypeBean> getChildBeans() {
		return childBeans;
	}

	public void setChildBeans(List<ProductTypeBean> childBeans) {
		this.childBeans = childBeans;
	}

	public ProductTypeBean getParentBean() {
		return parentBean;
	}

	public void setParentBean(ProductTypeBean parentBean) {
		this.parentBean = parentBean;
	}

}
