package in.sg.rpc.server.service;

import in.co.thingsdata.lms.gui.CourseContentDetails;
import in.co.thingsdata.lms.gui.FeeReceipt;
import in.co.thingsdata.lms.gui.ProfileScreen;
import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.PropertiesReader;
import in.sg.rpc.common.domain.Course;
import in.sg.rpc.common.domain.FeeDetails;
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.SwingUtilities;

public class DBService {

	private final static DBService _INSTANCE = new DBService();
	private static final String DATABASE_URL = PropertiesReader.getInstance().getProperty("db.url");
	private static final String DATABASE_USER = PropertiesReader.getInstance().getProperty("db.user");
	private static final String DATABASE_PASSWORD = PropertiesReader.getInstance().getProperty("db.password");
	private static final String DATABASE_DRIVER = PropertiesReader.getInstance().getProperty("db.driver");
	private long sequenceId;
	
	private DBService() {
		PropertiesReader.getInstance().init("resources/");
	}
	
	public static DBService getInstance() {
		return _INSTANCE;
	}
	
	public User getUserDetails (int userId) /* throws CUSTOMException*/ {
		
		String name = null;
		String address = null;
		String emailId = null;
		String dob = null; 
		String course = null;
		String line;
		BufferedReader reader=null;
		try{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("resources/Userdetail.txt"))));
			while (null != (line = reader.readLine())) {
				if (null != line.split(",")[0] && Integer.valueOf(line.split(",")[0])==userId)
				{
				//userid = Integer.valueOf(line.split(",")[0]);
				name = line.split(",")[1];
				address =line.split(",")[2];
				emailId=line.split(",")[3];
				dob=line.split(",")[4];
				course=line.split(",")[5];
				}
			}

		} catch (FileNotFoundException e) {
			//TODO: Use custom exception  here and show appropriate message to user on GUI
			e.printStackTrace();
		} catch (NumberFormatException e) {
			//TODO: Use custom exception  here and show appropriate message to user on GUI
			e.printStackTrace();
		} catch (IOException e) {
			//TODO: Use custom exception  here and show appropriate message to user on GUI
			e.printStackTrace();
		}finally{
				if(null != reader){
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		
		User userdetails = new User(userId, name, address, emailId, dob, course);
		return userdetails;
	}
	
	
	public boolean saveUserDetails(User user) throws IOException{
		String line;
		BufferedReader reader=null;
		BufferedWriter out=null;
		StringBuffer updatedRecord=new StringBuffer();
		StringBuffer currentRecord=new StringBuffer();
		boolean status=false;;
		try{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("resources/Userdetail.txt"))));

			while (null != (line = reader.readLine())) {
				if (null != line.split(",")[0] && Integer.valueOf(line.split(",")[0])==user.getUserid())
				{
				currentRecord= currentRecord.append(line);	
				updatedRecord= updatedRecord.append(user.getUserid()).append(",").append(user.getName()).append(",").append(user.getAddress()).append(",").append(user.getEmailid()).append(",").append(user.getDob()).append(",").append(user.getCourse());
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("resources/Userdetail.txt"))));
				line=line.replace(currentRecord, updatedRecord);
				out.write(line);
				status = true;
				}
				else{
					status=false;
				}
					
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				if(null != reader){
					reader.close();
				}
				if(null != out){
					out.close();
				}
		}
		return status;
	}
	

	@SuppressWarnings("resource")
	public String getCourseDetailForUser(int userId) {
		String line;
		BufferedReader reader=null;
		Course course = null;
		String courseContent ="";
		String coursecontentpath=null;

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("resources/CourseDetails.txt"))));
			while (null != (line = reader.readLine())) {
				if (null != line.split(",")[0] && Integer.valueOf(line.split(",")[1])==userId)
				{
					coursecontentpath=line.split(",")[3];
				}
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		courseContent=DBService.getCourseContent(coursecontentpath);
		return courseContent;
		
	}
	

	public static String getCourseContent(String ContentPath){
		BufferedReader reader = null;
		String line=null;;
		String courseContent ="";
		try {
			reader = new BufferedReader(new FileReader(new File(ContentPath)));
				while (null!=(line = reader.readLine())) {
				courseContent=courseContent.concat(line).concat("\n");
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(null != reader){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
	}
		

		return courseContent;
	}

	
	public FeeDetails getFeeDetailsforUserid(int userId){
		FeeDetails feeDetails = null;
		String line;
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("resources/FeeReceipt.txt"))));
			while (null != (line = reader.readLine())) {
				if (null != line.split(",")[0] && Integer.valueOf(line.split(",")[0])==userId)
				{
					feeDetails = new FeeDetails(userId);
					feeDetails.setCourseId(Integer.valueOf(line.split(",")[1]));
					feeDetails.setCourseName(String.valueOf(line.split(",")[2]));
					feeDetails.setCourseFee((Integer.valueOf(line.split(",")[3])));
					feeDetails.setPaidFees(Integer.valueOf(line.split(",")[4]));
					feeDetails.setRemainingFees(Integer.valueOf(line.split(",")[4]));
			
				}
			}	
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(null != reader){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return feeDetails;
	}

	public void init() {
		
		try {
			Class.forName(DATABASE_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection () throws SQLException {
		Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
		return con;
	}
	public void closeConnection (Connection con) throws SQLException {

		try {
			if (null != con) {
				if (!con.isClosed()) {
					con.close();
				}
			}
		}catch (Exception e) {
			System.err.println("Error occured while closing connection." + e.getMessage());
			e.printStackTrace();
		}

	}

	public void commit (Connection con) throws Exception {
		con.commit();
	}
	
	public void rollBack(Connection con) throws SQLException {
		con.rollback();
	}
	
	public synchronized long getSequenceId() {
		sequenceId += 1;
		return sequenceId;
	}


}
