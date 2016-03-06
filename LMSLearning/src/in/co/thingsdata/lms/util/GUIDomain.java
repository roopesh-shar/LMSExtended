package in.co.thingsdata.lms.util;

import in.sg.rpc.common.RPCService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

import javax.swing.JFrame;

public class GUIDomain {
	
	public static PropertiesReader propertiesReader;;// = PropertiesReader.getInstance();
	//public final static OutputStream os;
	public static String SOCKET_IP;
	public static String SERVER_IP;
	public static String SOCKET_PORT;
	public static String SERVER_PORT;
	public static Socket SOCKET;
	public static String DB_URL;
	public static String DB_USER;
	public static String DB_PASSWORD;
	public static Connection DB_CONNECTION;
	public static FileInputStream FILE_INPUT_STREAM;
	public static FileOutputStream FILE_OUTPUT_STREAM;
	public static JFrame PARENT_VIEW;
	public static PrintWriter FILE_WRITER;
	public static String DB_DRIVER;
	public static String STUDENT_COURSE;
	public static RPCService REMOTE_RPC_SERVICE;
	public static String CURRENT_USER_ID;
	public static String CURRENT_USER_NAME;

}
