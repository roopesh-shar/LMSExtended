package in.sg.rpc.server.service;

import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;
import in.co.thingsdata.lms.util.PropertiesReader;
import in.sg.rpc.common.domain.Course;
import in.sg.rpc.common.domain.FeeDetails;
import in.sg.rpc.common.domain.Feedback;
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

	public User getUserDetails(int userId) /* throws CUSTOMException */{

		String name = null;
		String address = null;
		String emailId = null;
		String dob = null;
		String course = null;
		String line;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("resources/Userdetail.txt"))));
			while (null != (line = reader.readLine())) {
				if (null != line.split(",")[0]
						&& Integer.valueOf(line.split(",")[0]) == userId) {
					// userid = Integer.valueOf(line.split(",")[0]);
					name = line.split(",")[1];
					address = line.split(",")[2];
					emailId = line.split(",")[3];
					dob = line.split(",")[4];
					course = line.split(",")[5];
				}
			}

		} catch (FileNotFoundException e) {
			// TODO: Use custom exception here and show appropriate message to
			// user on GUI
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO: Use custom exception here and show appropriate message to
			// user on GUI
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: Use custom exception here and show appropriate message to
			// user on GUI
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

		User userdetails = new User(userId, name, address, emailId, dob, course);
		return userdetails;
	}

	public boolean saveUserDetails(User user) throws IOException {
		String line;
		BufferedReader reader = null;
		BufferedWriter out = null;
		StringBuffer updatedRecord = new StringBuffer();
		StringBuffer currentRecord = new StringBuffer();
		boolean status = false;
		;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("resources/Userdetail.txt"))));

			while (null != (line = reader.readLine())) {
				if (null != line.split(",")[0]
						&& Integer.valueOf(line.split(",")[0]) == user
								.getUserid()) {
					currentRecord = currentRecord.append(line);
					updatedRecord = updatedRecord.append(user.getUserid())
							.append(",").append(user.getName()).append(",")
							.append(user.getAddress()).append(",")
							.append(user.getEmailid()).append(",")
							.append(user.getDob()).append(",")
							.append(user.getCourse());
					out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(new File(
									"resources/Userdetail.txt"))));
					line = line.replace(currentRecord, updatedRecord);
					out.write(line);
					status = true;
				} else {
					status = false;
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				reader.close();
			}
			if (null != out) {
				out.close();
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
					+ userName + "' and password = '" + password + "'";
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
				+ " ?, 'testfname','testlname' , 'testfathername' ,"
				+ " ? , ?, ? , ?, ?, ?, ?)";
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

}
