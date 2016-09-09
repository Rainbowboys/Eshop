package com.test.Model;

import java.util.List;

public class ProductBean {

	private int id;
	private String name;
	private float originalPrice;
	private float price;
	private String intro;
	private int productTypeId;
	private ProductTypeBean productTypeBean;
	private int number;
	private int sellNumber;
	private String pic;
	private String productProperties;
	private List<ProductOptionBean> productOptionBeans;
	private String createDate;

	public ProductBean() {
		super();
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

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	public ProductTypeBean getProductTypeBean() {
		return productTypeBean;
	}

	public void setProductTypeBean(ProductTypeBean productTypeBean) {
		this.productTypeBean = productTypeBean;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSellNumber() {
		return sellNumber;
	}

	public void setSellNumber(int sellNumber) {
		this.sellNumber = sellNumber;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getProductProperties() {
		return productProperties;
	}

	public void setProductProperties(String productProperties) {
		this.productProperties = productProperties;
	}

	public List<ProductOptionBean> getProductOptionBeans() {
		return productOptionBeans;
	}

	public void setProductOptionBeans(List<ProductOptionBean> productOptionBeans) {
		this.productOptionBeans = productOptionBeans;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "ProductBean [id=" + id + ", name=" + name + ", originalPrice="
				+ originalPrice + ", price=" + price + ", intro=" + intro
				+ ", productTypeId=" + productTypeId + ", productTypeBean="
				+ productTypeBean + ", number=" + number + ", sellNumber="
				+ sellNumber + ", pic=" + pic + ", productProperties="
				+ productProperties + ", productOptionBeans="
				+ productOptionBeans + ", createDate=" + createDate + "]";
	}

}
