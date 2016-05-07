package in.sg.rpc.server.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.sg.rpc.common.Business;
import in.sg.rpc.common.domain.Course;
import in.sg.rpc.common.domain.FeeDetails;
import in.sg.rpc.common.domain.Feedback;
import in.sg.rpc.common.domain.QuizQuestion;
import in.sg.rpc.common.domain.User;

public class DBService {

	private final static DBService _INSTANCE = new DBService();
	private long sequenceId;

	private DBService() {

	}

	public static DBService getInstance() {
		return _INSTANCE;
	}

	public static void setUpDB() {
		Business.getInstance().initProperties();
	}

	public User getUserDetails(int userId) /* throws CUSTOMException */ {
		Statement stmt = null;
		Connection con = null;
		User user = null;
		try {
			user = new User();
			con = getConnection();
			String lSqlString = "select * from users where id=" + userId;
			stmt = con.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(lSqlString);
			while (rs.next()) {
				user.setName(rs.getString("user_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public boolean saveUserDetails(User user) throws IOException, SQLException {
		Statement stmt = null;
		Connection con = null;
		boolean status = false;
		int rowCount;
		try {
			user = new User();
			con = getConnection();
			String lSqlString = "update users set user_name=" + user.getName() + " where id=" + user.getUserid();
			stmt = con.createStatement();
			ResultSet rs = null;
			rowCount = stmt.executeUpdate(lSqlString);
			con.commit();
			status = true;
		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	@SuppressWarnings("resource")
	public String getCourseDetailForUser(int userId) {
		String line;
		BufferedReader reader = null;
		Course course = null;
		String courseContent = "";
		String coursecontentpath = null;

		try {
			reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File("resources/CourseDetails.txt"))));
			while (null != (line = reader.readLine())) {
				if (null != line.split(",")[0] && Integer.valueOf(line.split(",")[1]) == userId) {
					coursecontentpath = line.split(",")[3];
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		courseContent = DBService.getCourseContent(coursecontentpath);
		return courseContent;

	}

	public static String getCourseContent(String ContentPath) {
		BufferedReader reader = null;
		String line = null;
		;
		String courseContent = "";
		try {
			reader = new BufferedReader(new FileReader(new File(ContentPath)));
			while (null != (line = reader.readLine())) {
				courseContent = courseContent.concat(line).concat("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return courseContent;
	}

	public FeeDetails getFeeDetailsforUserid(int userId) {
		FeeDetails feeDetails = null;
		String line;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File("resources/FeeReceipt.txt"))));
			while (null != (line = reader.readLine())) {
				if (null != line.split(",")[0] && Integer.valueOf(line.split(",")[0]) == userId) {
					feeDetails = new FeeDetails(userId);
					feeDetails.setCourseId(Integer.valueOf(line.split(",")[1]));
					feeDetails.setCourseName(String.valueOf(line.split(",")[2]));
					feeDetails.setCourseFee((Integer.valueOf(line.split(",")[3])));
					feeDetails.setPaidFees(Integer.valueOf(line.split(",")[4]));
					feeDetails.setRemainingFees(Integer.valueOf(line.split(",")[4]));

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return feeDetails;
	}

	public void init() throws SQLException {
		Business.getInstance().setUpDB();
		try {
			Class.forName(Business.getInstance().getDatabaseDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			checkDBConnection();
		}

	}

	public void checkDBConnection() throws SQLException {
		Connection conn = getConnection();
		java.sql.Statement stmt = conn.createStatement();
		String lSqlString = "select id from LMS.profile";
		ResultSet rs = null;
		rs = stmt.executeQuery(lSqlString);
		while (rs.next()) {
			System.out.println(rs.getInt("id"));
		}
		closeConnection(conn);
	}

	public int returnUserId(String userName, String password) throws SQLException {
		Connection conn = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			String validUser = null;
			String lSqlString = "select id,user_name from users where user_name='" + userName + "' and password = '"
					+ password + "'";
			ResultSet rs = null;
			rs = stmt.executeQuery(lSqlString);
			if (rs.next()) {
				validUser = rs.getString("user_name");
				if (userName.equalsIgnoreCase(validUser)) {
					return rs.getInt("id");
				} else {
					return 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

		return 0;
	}

	public void registerUser(User user) throws Exception {
		Connection conn = null;
		String insertUserProfile = "insert into LMS.PROFILE values("
				+ " ((select max(id) from profile) +1), (select id from users where user_name = ?) , "
				+ " ?, 'testfname','testlname' , 'testfathername' ," + " ? , ?, ? , ?, ?, ?, ?)";
		String insertUserStatement = "insert into LMS.USERS values( ((select max(id) from LMS.Users) +1) , ?, ?,0)";
		PreparedStatement insertProfile = null;
		PreparedStatement insertUser = null;
		try {
			conn = getConnection();
			insertUser = conn.prepareStatement(insertUserStatement);
			insertUser.setString(1, user.getName());
			insertUser.setString(2, user.getPassword());
			insertUser.executeUpdate();
			insertProfile = conn.prepareStatement(insertUserProfile);
			insertProfile.setString(1, user.getName());
			insertProfile.setString(2, user.getUserType());
			insertProfile.setString(3, user.getEmailid());
			insertProfile.setLong(4, user.getPhoneNum());
			insertProfile.setString(5, user.getAddress());
			insertProfile.setString(6, user.getCountry());
			insertProfile.setString(7, user.getState());
			insertProfile.setString(8, user.getCity());
			insertProfile.setLong(9, user.getPinCode());
			insertProfile.execute();
			commit(conn);
		} catch (SQLException e) {
			if (conn != null) {
				try {
					System.err.print("Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException excep) {
					excep.printStackTrace();
				}
			}
		} finally {
			if (insertUser != null) {
				insertUser.close();
			}
			if (insertProfile != null) {
				insertProfile.close();
			}
			conn.close();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection con = Business.getInstance().getDBConnection();
		con.setAutoCommit(false);
		return con;
	}

	public void closeConnection(Connection con) throws SQLException {

		try {
			if (null != con) {
				if (!con.isClosed()) {
					con.close();
				}
			}
		} catch (Exception e) {
			System.err.println("Error occured while closing connection." + e.getMessage());
			e.printStackTrace();
		}

	}

	public void commit(Connection con) throws Exception {
		con.commit();
	}

	public void rollBack(Connection con) throws SQLException {
		con.rollback();
	}

	public synchronized long getSequenceId() {
		sequenceId += 1;
		return sequenceId;
	}

	public Feedback[] getUserFeedback(int userId) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		Feedback[] feedbacks = null;
		String rowCountSql = "select count(*) from feedback where  is_approved=1 or user_id = ";
		String lSqlString = "select User_name, Feedback from Users U inner join Feedback F on U.id=f.user_id where F.is_approved=1";
		Statement stmt = null;
		int count = 0;
		Feedback feedback = null;
		int index = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rowCountSql += userId;
			rs = stmt.executeQuery(rowCountSql);
			rs.next();
			count = rs.getInt("count(*)");
			feedbacks = new Feedback[count];
			rs = stmt.executeQuery(lSqlString);
			while (rs.next()) {
				feedback = new Feedback();
				feedback.setUserName(rs.getString("user_name"));
				feedback.setFeedback(rs.getString("Feedback"));
				feedbacks[index++] = feedback;
			}
		} finally {
			closeConnection(conn);
		}
		return feedbacks;
	}
	
	public void submitFeedback(Feedback feedbackSubmit) throws SQLException {
		Connection conn = null;
		String insertFeedbackStatement = "insert into LMS.Feedback Values ((select max(id) from LMS.Feedback) +1,"
				+ "(select course_id from LMS.Profile where user_id = ?),"
				+ "?,?,0,?)";
		
		
				
		PreparedStatement insertFeedback = null;
		try{
			conn = getConnection();
			insertFeedback = conn.prepareStatement(insertFeedbackStatement);
			insertFeedback.setLong(1, feedbackSubmit.getUserId());
			insertFeedback.setString(2, feedbackSubmit.getFeedbackArea());
			insertFeedback.setString(3, feedbackSubmit.getFeedback());
			insertFeedback.setLong(4, feedbackSubmit.getUserId());
			//System.out.println(feedbackSubmit.getCourseName()+" "+feedbackSubmit.getFeedbackArea() +" "+feedbackSubmit.getFeedback() +" "+ feedbackSubmit.getUserId());
			insertFeedback.execute();
			commit(conn);
		}catch (SQLException e) {
			if (conn != null) {
				try {
					System.err.print("Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException excep) {
					excep.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (insertFeedback != null) {
				insertFeedback.close();
			}

			conn.close();
		}
		
		
	}
	

public QuizQuestion[] getgetQuizQuestionfromDB(long userId) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		QuizQuestion[] quizquestions = 	null;
		QuizQuestion quizquestion = null;
		String rowCountSql = "select count(*) from Profile P inner join Course C on P.course_id = C.id inner join quiz_question QQ on C.id = QQ.course_id where P.user_id="+userId;;
		String lSqlString = "select QQ.Question_number, qq.question, qq.choice_a, qq.choice_b, qq.choice_c,qq.choice_d, qq.choice_correct from Profile P inner join Course C on P.course_id = C.id inner join quiz_question QQ on C.id = QQ.course_id where P.user_id=" +userId;
		Statement stmt = null;
		int count = 0;
		int index = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(rowCountSql);
			rs.next();
			count = rs.getInt("count(*)");
			quizquestions = new QuizQuestion[count];
			rs = stmt.executeQuery(lSqlString);
			while (rs.next()) {
				quizquestion = new QuizQuestion();
				quizquestion.setQuestionNumber(rs.getLong("Question_number"));
				quizquestion.setQuestion(rs.getString("question"));
				quizquestion.setChoiceA(rs.getString("choice_a"));
				quizquestion.setChoiceB(rs.getString("choice_b"));
				quizquestion.setChoiceC(rs.getString("choice_c"));
				quizquestion.setChoiceD(rs.getString("choice_d"));
				quizquestion.setCorrectAnswer(rs.getString("choice_correct"));
				quizquestions[index++] = quizquestion;
			}
		}finally {
			closeConnection(conn);
		
		return quizquestions;
	}
	}

		public Boolean uploadCourseManager(File fileStream, String uploadItem, String courseName) {
			
			
			
			return null;
		}
		
	
}
