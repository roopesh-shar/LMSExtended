package in.co.thingsdata.lms.domain;

import java.io.Serializable;

public class User implements Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 252097048550406218L;
	private String name;
	String address;
	String emailid;
	String dob;
	String course;
	private int userid;

	public User() {
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
