package in.sg.rpc.server.service;

import in.sg.rpc.common.domain.Course;
import in.sg.rpc.common.domain.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DBService {

	private final static DBService _INSTANCE = new DBService();
	
	private DBService() {
		
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
	

	public Course getCourseDetailsForUser (Integer userId) {
		
		
		return new Course();
		
	}

}
