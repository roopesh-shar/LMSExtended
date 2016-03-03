package in.co.thingsdata.lms.util;


import in.co.thingsdata.lms.gui.CourseContentDetails;
import in.co.thingsdata.lms.gui.FeeReceipt;
import in.co.thingsdata.lms.gui.ProfileScreen;
import in.co.thingsdata.lms.server.Server;
import in.sg.rpc.common.domain.Course;
import in.sg.rpc.common.domain.FeeDetails;
import in.sg.rpc.common.domain.User;
import in.sg.rpc.server.service.DBService;

import java.awt.Component;
import java.awt.Container;
import java.awt.image.ReplicateScaleFilter;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GUIUtil {

	public static void addComponents(Container container, Component... components) {

		for (Component comp : components) {
			container.add(comp);
		}

	}

	public static void initProperties() {

		GUIDomain.propertiesReader = PropertiesReader.getInstance();
		GUIDomain.propertiesReader.init("resources/");

	}

	public static void setupFileIO() {

		try {

			GUIDomain.FILE_INPUT_STREAM = new FileInputStream(
					new File(GUIDomain.propertiesReader.getProperty("file.input.name")));
			GUIDomain.FILE_OUTPUT_STREAM = new FileOutputStream(
					new File(GUIDomain.propertiesReader.getProperty("file.output.name")), true);
			GUIDomain.FILE_WRITER = new PrintWriter(GUIDomain.FILE_OUTPUT_STREAM);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void setupDB() {

		GUIDomain.DB_DRIVER = GUIDomain.propertiesReader.getProperty("db.driver");
		GUIDomain.DB_URL = GUIDomain.propertiesReader.getProperty("db.url");
		GUIDomain.DB_USER = GUIDomain.propertiesReader.getProperty("db.user");
		GUIDomain.DB_PASSWORD = GUIDomain.propertiesReader.getProperty("db.password");

		try {

			Class.forName(GUIDomain.DB_DRIVER);
			Connection con = DriverManager.getConnection(GUIDomain.DB_URL, GUIDomain.DB_USER, GUIDomain.DB_PASSWORD);
			GUIDomain.DB_CONNECTION = con;

			System.out.println("Database major version=" + con.getMetaData().getDatabaseMajorVersion());
			System.out.println("Database minor version=" + con.getMetaData().getDatabaseMinorVersion());
			System.out.println("Database product name=" + con.getMetaData().getDatabaseProductName());
			System.out.println("Database product version=" + con.getMetaData().getDatabaseProductVersion());

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * 
		 * { Class.forName("oracle.jdbc.driver.OracleDriver"); Connection con =
		 * DriverManager.getConnection("jdbc:oracle:thin:@mcndesktop07:1521:xe",
		 * "", ""); PreparedStatement ps = con.prepareStatement(
		 * "insert into reg values(?,?,?,?,?,?)"); ps.setString(1, s1);
		 * ps.setString(2, s2); ps.setString(3, s8); ps.setString(4, s5);
		 * ps.setString(5, s6); ps.setString(6, s7); ResultSet rs =
		 * ps.executeQuery(); x++; if (x > 0) {
		 * JOptionPane.showMessageDialog(buttonSubmit, "Data Saved Successfully"
		 * ); } } catch (Exception ex) { System.out.println(ex); } }
		 * 
		 */

	}

	public static void setupNetworking() {

		 Server server = new Server();
		 
		startDummyServer(server);
		
		if (!server.isRunning()) {
			return;
		}
		String hostName = GUIDomain.propertiesReader.getProperty("server.host.name");
		
		int portNumber = Integer.parseInt(GUIDomain.propertiesReader.getProperty("server.socket.port"));

		try (Socket socket = new Socket("localhost", 4444);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
			String userInput;
			while (!(userInput = stdIn.readLine()).equalsIgnoreCase("quit")) {
				out.println(userInput);
				System.out.println("echo: " + in.readLine());
			}
			System.out.println("Finished Server conversation ...");
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}

	private static void startDummyServer(Server server) {

    server.start();
     
     try {
		Thread.sleep(5000);
		server.stop();
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	public static void refreshGUI(JFrame view) {

		view.getContentPane().invalidate();
		view.getContentPane().validate();

		GUIDomain.PARENT_VIEW = view;

	}

	public static String getHeaderTitle() {
		initProperties();
		return PropertiesReader.getInstance().getProperty("company.name");

	}

	public static ImageIcon getIcon() {
		initProperties();
		return new ImageIcon(PropertiesReader.getInstance().getProperty("icon.image.path"));

	}

	public static void writeToFile(Object... fields) {

		StringBuilder fieldStr = new StringBuilder();

		for (Object field : fields) {
			fieldStr.append(field);
			fieldStr.append(",");
		}

		GUIDomain.FILE_WRITER.println(fieldStr.toString());
		GUIDomain.FILE_WRITER.flush();

	}
	
	public static String[] getContentList(){
		initProperties();
		String contentlist=PropertiesReader.getInstance().getProperty("content.list");
		String[] linkcontent = contentlist.split(",");
		return linkcontent;
				
	}
	

	public static User getUserDetails(int userId) throws NumberFormatException, IOException{
		return DBService.getInstance().getUserDetails(userId);
	}
	
	public static boolean saveUserDetails(User user) throws NumberFormatException, FileNotFoundException, IOException{
		return DBService.getInstance().saveUserDetails(user);
	}

	public static String getCourseDetailForUser(int userId) throws Exception{
		//return DBService.getInstance().getCourseDetailForUser(userId);
		return GUIDomain.REMOTE_RPC_SERVICE.getCourseDetailForUser(userId);
		

	}
	
	public static FeeDetails getFeeDetailsforUserid(int userId) throws Exception{
		return GUIDomain.REMOTE_RPC_SERVICE.getFeeDetailsforUserid(userId);
	}
	
	public static void goToRequestedPage(String goToPage){
		if(goToPage.equals("Course Content"))
		{
			CourseContentDetails courseContent = new CourseContentDetails(); // Comments to revert
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					try {
						courseContent.go();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
		}
		if(goToPage.equals("Profile"))
		{
			ProfileScreen screen = new ProfileScreen(); // Comments to revert
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					screen.go();
					

				}
			});
		}
		if(goToPage.equals("Fee Receipt"))
		{
			FeeReceipt feeReceipt = new FeeReceipt();
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					try {
						feeReceipt.go();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				}
			});
		}
	}

}
