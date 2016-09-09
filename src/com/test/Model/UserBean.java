package com.test.Model;

public final class UserBean {
	private int id;
	private int status; // 冻结状态，默认为1（激活状态），0（冻结）
	private String nickname;
	private String username;
	private String truename;
	private String password;
	private String salt; // 盐值
	private int sex;
	private String pic; // 图片的显示地址（即pic_show_path）
	private String createDate;

	// 构造函数
	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public UserBean(int id, int status, String nickname, String username,
			String truename, String password, String salt, int sex, String pic,
			String createDate) {
		super();
		this.id = id;
		this.status = status;
		this.nickname = nickname;
		this.username = username;
		this.truename = truename;
		this.password = password;
		this.salt = salt;
		this.sex = sex;
		this.pic = pic;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", status=" + status + ", nickname="
				+ nickname + ", username=" + username + ", truename="
				+ truename + ", password=" + password + ", salt=" + salt
				+ ", sex=" + sex + ", pic=" + pic + ", createDate="
				+ createDate + "]";
	}

}
