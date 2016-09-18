package com.test.Model;

import java.util.List;

/**
 * 订单Bean.
 * 
 * @author Administrator
 * 
 */
public class OrderBean {
	private int id;
	private String code;
	private float original_price;
	private float price;
	private int address_id;
	private AddressBean addressBean;
	private int user_id;
	private UserBean userBean;
	private int payment_type;
	private int status;
	private String create_date;
	private List<OrderProductBean> orderProductBeans;

	public List<OrderProductBean> getOrderProductBeans() {
		return orderProductBeans;
	}

	public void setOrderProductBeans(List<OrderProductBean> orderProductBeans) {
		this.orderProductBeans = orderProductBeans;
	}

	public OrderBean() {

	}

	public OrderBean(int id, String code, float original_price, float price,
			int address_id, AddressBean addressBean, int user_id,
			UserBean userBean, int payment_type, int status,
			String create_date, List<OrderProductBean> orderProductBeans) {
		super();
		this.id = id;
		this.code = code;
		this.original_price = original_price;
		this.price = price;
		this.address_id = address_id;
		this.addressBean = addressBean;
		this.user_id = user_id;
		this.userBean = userBean;
		this.payment_type = payment_type;
		this.status = status;
		this.create_date = create_date;
		this.orderProductBeans = orderProductBeans;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(float original_price) {
		this.original_price = original_price;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public AddressBean getAddressBean() {
		return addressBean;
	}

	public void setAddressBean(AddressBean addressBean) {
		this.addressBean = addressBean;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public int getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

}
