package in.sg.rpc.common.domain;

public class Course {
		
	private String courseName;
	int courseId;
	int userId;
	String contentPath;
	float feesCourse;
	
	public String getContentPath() {
		return contentPath;
	}

	public float getFeesCourse() {
		return feesCourse;
	}

	public void setFeesCourse(float feesCourse) {
		this.feesCourse = feesCourse;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public Course(){
		
	}
	
	public Course(int courseId, int userId, String courseName, String contentPath){
		this.courseId=courseId;
		this.userId=userId;
		this.courseName=courseName;
		this.contentPath=contentPath;
		
	}
	
	public Course(int courseId){
		this.courseId=courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	
}
