package cn.hellyuestc.caiyuan.entity;

import java.util.Date;

public class User {

	private int id;
	private String name;
	private String password;
	private String avatarPath;
	private String gender;
	private Date birthday;
	private String phone;
	private String email;
	private String address;
	private String job;
	private String introduction;
	private int isExpert;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatarPath() {
		return avatarPath;
	}
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getIsExpert() {
		return isExpert;
	}
	public void setIsExpert(int isExpert) {
		this.isExpert = isExpert;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", avatarPath=" + avatarPath
				+ ", gender=" + gender + ", birthday=" + birthday + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", job=" + job + ", introduction=" + introduction + ", isExpert=" + isExpert
				+ "]";
	}
	
}
