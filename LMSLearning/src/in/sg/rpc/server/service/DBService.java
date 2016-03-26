package in.sg.rpc.server.service;

import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;
import in.co.thingsdata.lms.util.PropertiesReader;
import in.sg.rpc.common.domain.Course;
import in.sg.rpc.common.domain.FeeDetails;
import in.sg.rpc.common.domain.Feedback;
import in.sg.rpc.common.domain.QuizQuestion;
import in.sg.rpc.common.domain.User;

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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class DBService {

	private final static DBService _INSTANCE = new DBService();
	private long sequenceId;

	private DBService() {

	}

	public static DBService getInstance() {
		return _INSTANCE;
	}

	public static void setUpDB() {
		GUIUtil.initProperties();
		GUIDomain.DATABASE_USER = PropertiesReader.getInstance().getProperty(
				"db.user");
		GUIDomain.DATABASE_PASSWORD = PropertiesReader.getInstance()
				.getProperty("db.password");
		GUIDomain.DATABASE_DRIVER = PropertiesReader.getInstance().getProperty(
				"db.driver");
		GUIDomain.DATABASE_URL = PropertiesReader.getInstance().getProperty(
				"db.url");

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
					new InputStreamReader(new FileInputStream(new File(
							"resources/CourseDetails.txt"))));
			while (null != (line = reader.readLine())) {
				if (null != line.split(",")[0]
						&& Integer.valueOf(line.split(",")[1]) == userId) {
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
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("resources/FeeReceipt.txt"))));
			while (null != (line = reader.readLine())) {
				if (null != line.split(",")[0]
						&& Integer.valueOf(line.split(",")[0]) == userId) {
					feeDetails = new FeeDetails(userId);
					feeDetails.setCourseId(Integer.valueOf(line.split(",")[1]));
					feeDetails
							.setCourseName(String.valueOf(line.split(",")[2]));
					feeDetails
							.setCourseFee((Integer.valueOf(line.split(",")[3])));
					feeDetails.setPaidFees(Integer.valueOf(line.split(",")[4]));
					feeDetails
							.setRemainingFees(Integer.valueOf(line.split(",")[4]));

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
		setUpDB();
		try {
			Class.forName(GUIDomain.DATABASE_DRIVER);
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

	public int returnUserId(String userName, String password)
			throws SQLException {
		Connection conn;
		conn = getConnection();
		try {
			java.sql.Statement stmt = conn.createStatement();
			String validUser = null;
			String lSqlString = "select id,user_name from users where user_name='"
					+ userName + "' and password = '" + password + "'" + "and is_active=1";
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
			else{
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

		return 0;
	}

	

	public Connection getConnection() throws SQLException {
		System.out.println("DBURL" + GUIDomain.DATABASE_URL);
		System.out.println("DBUSER" + GUIDomain.DATABASE_USER);
		System.out.println("DBPASSWORD" + GUIDomain.DATABASE_PASSWORD);
		System.out.println("DBDRIVER" + GUIDomain.DATABASE_DRIVER);
		Connection con = DriverManager.getConnection(GUIDomain.DATABASE_URL,
				GUIDomain.DATABASE_USER, GUIDomain.DATABASE_PASSWORD);
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
			System.err.println("Error occured while closing connection."
					+ e.getMessage());
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
		String lSqlString = "select User_name, Feedback from Users U inner join Feedback F on U.id=f.user_id where (F.is_approved=1 or F.user_id ="+userId +")";
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

	
	public void registerUser(User user) throws Exception {
		Connection conn = null;
		String insertUserProfile = "insert into LMS.PROFILE values("
				+ " ((select max(id) from profile) +1), (select id from users where user_name = ?) , "
				+ " ?, 'testfname','testlname' , 'testfathername' ,"
				+ " ? , ?, ? , ?, ?, ?, ?,(select id from LMS.course where Course_name = ?) )";
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
			insertProfile.setString(10, user.getCourse());
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

	public User getUserDetails(int userId) throws SQLException /* throws CUSTOMException */{
		Connection conn = null;
		ResultSet rs = null;
		User profileDetails = new User();
		String selectUserDetails = "select u.user_name,P.DOB, P.Address,p.Contact_number, p.email_id, p.country,p.city, p.pincode,p.state, c.course_name  from Profile P inner join users U on U.id = P.user_id inner join Course C on P.course_id = c.ID where U.id = ?";
		PreparedStatement selectUser = null;
		try{
			conn = getConnection();
			selectUser = conn.prepareStatement(selectUserDetails);
			selectUser.setInt(1,  userId);
			rs = selectUser.executeQuery();
			while(rs.next()){
				profileDetails.setName(rs.getString("user_name"));
				profileDetails.setDob(rs.getString("dob"));
				profileDetails.setAddress(rs.getString("Address"));
				profileDetails.setEmailid(rs.getString("email_id"));
				profileDetails.setCountry(rs.getString("Country"));
				profileDetails.setCity(rs.getString("city"));
				profileDetails.setAddress(rs.getString("address"));
				profileDetails.setState(rs.getString("state"));
				profileDetails.setPinCode(rs.getLong("pincode"));
				profileDetails.setCourse(rs.getString("course_name"));
				profileDetails.setPhoneNum(rs.getLong("Contact_number"));
					
				return profileDetails;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return profileDetails;
	}
	
	public boolean saveUserDetails(User user) throws IOException, SQLException {
		Connection conn = null;
		String saveUserDetailsToDB = "update LMS.PROFILE set EMAIL_ID = ?, CONTACT_NUMBER =?, ADDRESS=?, COUNTRY = ?, STATE =?, CITY =?, PINCODE =?, DOB =? where USER_ID = ?";
		PreparedStatement saveUser = null;
		try{
			conn = getConnection();
			saveUser = conn.prepareStatement(saveUserDetailsToDB);
			saveUser.setString(1, user.getEmailid());
			saveUser.setLong(2, user.getPhoneNum());
			saveUser.setString(3,  user.getAddress());
			saveUser.setString(4, user.getCountry());
			saveUser.setString(5, user.getState());
			saveUser.setString(6, user.getCity());
			saveUser.setLong(7, user.getPinCode());
			saveUser.setString(8, user.getDob().substring(0,9));
			saveUser.setLong(9, user.getUserid());
			System.out.println(user.getEmailid()+","+user.getPhoneNum()+","+user.getAddress()+","+user.getCountry()+"," +user.getState()+","+user.getCity()+","+user.getPinCode()+","+user.getDob()+","+user.getUserid());
			saveUser.executeUpdate();
			commit(conn);
			return true;
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
		}finally{
			conn.close();
		}
		return false;
		
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
		
}
