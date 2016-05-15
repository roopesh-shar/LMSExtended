package in.sg.rpc.common.domain;

public class QuizQuestion {

	long questionId;
	long questionNumber;
	String question;
	String choiceA;
	String choiceB;
	String choiceC;
	String choiceD;
	String correctAnswer;
	long courseId;
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(long questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getChoiceA() {
		return choiceA;
	}
	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}
	public String getChoiceB() {
		return choiceB;
	}
	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getChoiceC() {
		return choiceC;
	}
	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}
	public String getChoiceD() {
		return choiceD;
	}
	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}


	
	
}
