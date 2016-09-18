package com.test.Model;

/**
 * 商品订单Bean
 * 
 * 
 * 
 */
public class OrderProductBean {
	private int id;
	private int orderId;
	private OrderBean orderBean;
	private int productId;
	private ProductBean productBean;
	private float price;
	private int number;
	private String datetime;

	public OrderProductBean() {
	}

	public OrderProductBean(ProductBean productBean, int number) {
		this.setProductBean(productBean);
		this.setNumber(number);
	}

	public OrderProductBean(OrderBean orderBean, ProductBean productBean,
			int number) {
		this.setOrderBean(orderBean);
		this.setProductBean(productBean);
		this.setNumber(number);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
}
