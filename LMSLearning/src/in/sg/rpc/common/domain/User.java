package in.sg.rpc.common.domain;

import java.io.Serializable;

public class User implements Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 252097048550406218L;
	private String course;
	private String name;
	private String emailid;
	private String password;
	private long phoneNum;
	private String country;
	private String state;
	private String city;
	private String address;
	private long pinCode;
	private String firstName;
	private String lastName;
	private String userType;
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}
	private String dob;
	
	
	private int userid;

	public User() {
	}	
	
	public User(int userId) {
		this.userid = userId;	
	}	
	
	public User(int userid, String name, String address, String emailid, String dob, String course) {
	this.userid = userid;
	this.name = name;
	this.address=address;
	this.emailid= emailid;
	this.dob=dob;
	this.course=course;
		
	}
	
	public void setName (String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setUserid (int userid) {
		this.userid = userid;
	}
	public int getUserid() {
		return this.userid;
	}

	public void setAddress (String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setCourse (String course) {
		this.course = course;
	}
	public String getCourse() {
		return this.course;
	}
	


}
