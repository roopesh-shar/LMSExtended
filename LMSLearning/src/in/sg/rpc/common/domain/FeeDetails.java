package in.sg.rpc.common.domain;

import java.io.Serializable;

public class FeeDetails implements Serializable,Cloneable {

	private static final long serialVersionUID = 2059126289433740734L;
	
	private int userId;
	private int courseId;
	private String courseName;
	private int courseFee;
	private int paidFees;
	private int remainingFees;
	
	public FeeDetails(){
		
	}
	
	public FeeDetails(int userId){
		this.userId=userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}

	public int getPaidFees() {
		return paidFees;
	}

	public void setPaidFees(int paidFees) {
		this.paidFees = paidFees;
	}

	public int getRemainingFees() {
		return remainingFees;
	}

	public void setRemainingFees(int remainingFees) {
		this.remainingFees = remainingFees;
	}
	
	
}

