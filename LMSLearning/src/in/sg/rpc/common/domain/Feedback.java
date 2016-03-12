package in.sg.rpc.common.domain;

public class Feedback {

	private long id;
	private long userId;
	private String feedbackArea;
	private String feedback;
	private String userName;
	private String courseName;

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFeedbackArea() {
		return feedbackArea;
	}
	public void setFeedbackArea(String feedbackArea) {
		this.feedbackArea = feedbackArea;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	@Override
	public String toString() {
		return "Feedback [id=" + id + ", userId=" + userId + ", feedbackArea="
				+ feedbackArea + ", feedback=" + feedback + ", userName="
				+ userName + "]";
	}
	
	
}