package in.sg.rpc.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import in.co.thingsdata.lms.server.Server;
import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.PropertiesReader;
import in.sg.rpc.client.RPCClient;
import in.sg.rpc.common.exception.UserLoginException;

public class Business {
	
	private static Business _INSTANCE = new Business();
	
	private Business() {
		
	}
	public static Business getInstance() {
		return _INSTANCE;
	}
	public void connectToServer() throws Exception {
		try {
			GUIDomain.REMOTE_RPC_SERVICE = new RPCClient().getRemoteService();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw e;
		}		
	}
	public void initProperties() {

		GUIDomain.propertiesReader = PropertiesReader.getInstance();
		GUIDomain.propertiesReader.init("resources/");

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

	public void setupFileIO() {

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

	public void setupNetworking() {

		 Server server = new Server();
		 
		startDummyServer(server);
		
		if (!server.isRunning()) {
			return;
		}
		String hostName = GUIDomain.propertiesReader.getProperty("server.host.name");
		
		@SuppressWarnings("unused")
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
		e.printStackTrace();
	}
		
	}
	public int getUserId(String userName, String password) throws UserLoginException {
		int userId = GUIDomain.REMOTE_RPC_SERVICE.login(userName, password);
		return userId;
	}
	public void setUpDB() {
		initProperties();
		GUIDomain.DATABASE_USER = PropertiesReader.getInstance().getProperty(
				"db.user");
		GUIDomain.DATABASE_PASSWORD = PropertiesReader.getInstance()
				.getProperty("db.password");
		GUIDomain.DATABASE_DRIVER = PropertiesReader.getInstance().getProperty(
				"db.driver");
		GUIDomain.DATABASE_URL = PropertiesReader.getInstance().getProperty(
				"db.url");
	}
	public String getDatabaseDriver() {
		return GUIDomain.DATABASE_DRIVER;
	}
	public Connection getDBConnection() throws SQLException {
		Connection con = DriverManager.getConnection(GUIDomain.DATABASE_URL,
				GUIDomain.DATABASE_USER, GUIDomain.DATABASE_PASSWORD);
		return con;
	}
	

	
}
