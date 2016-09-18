package com.test.Model;

/**
 * 收货地址Bean.
 * 
 * @author Administrator
 * 
 */
public class AddressBean {
	private int id;
	private String name;
	private int province;
	private String provincename;
	private String cityname;
	private String areaname;
	private int city;
	private int region;
	private String address;
	private String cellphone;
	private int user_id;
	private int status;
	private String create_date;

	public AddressBean() {

	}

	public AddressBean(String provincename) {
		this.provincename = provincename;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
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

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	

	public AddressBean(int id, String name, int province, String provincename,
			String cityname, String areaname, int city, int region,
			String address, String cellphone, int user_id, int status,
			String create_date) {
		super();
		this.id = id;
		this.name = name;
		this.province = province;
		this.provincename = provincename;
		this.cityname = cityname;
		this.areaname = areaname;
		this.city = city;
		this.region = region;
		this.address = address;
		this.cellphone = cellphone;
		this.user_id = user_id;
		this.status = status;
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		return "AddressBean [id=" + id + ", name=" + name + ", province="
				+ province + ", provincename=" + provincename + ", cityname="
				+ cityname + ", areaname=" + areaname + ", city=" + city
				+ ", region=" + region + ", address=" + address
				+ ", cellphone=" + cellphone + ", user_id=" + user_id
				+ ", status=" + status + ", create_date=" + create_date + "]";
	}
}
