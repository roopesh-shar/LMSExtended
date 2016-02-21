package in.sg.rpc.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.xml.ws.Service;

import in.sg.rpc.common.RPCService;
import in.sg.rpc.common.exception.UserExistsException;
import in.sg.rpc.common.exception.UserLoginException;

public class RPCClient {

	public static void main(String[] args) throws Exception {
		
		try {
			
			go();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void go() throws IOException {

		Service service = ServiceHelper.getRemoteService();

		RPCService rpcService = service.getPort(RPCService.class);

		processRequest(rpcService);
		
	}
	
	public RPCService getRemoteService () throws MalformedURLException {
		Service service = ServiceHelper.getRemoteService();

		RPCService rpcService = service.getPort(RPCService.class);
		
		return rpcService;
	}

	private static void processRequest(RPCService rpcService) throws IOException {

		BufferedReader br = null;
		String choice = "";
	    String userName;
	    String password;
	    System.out.println("********RPC Client started.********");
		System.out.println("Enter your choice: Please enter either register or login");
		System.out.println("Enter quit to quit the application");
		
		do {
			System.out.println("Enter your choice(login/register/quit): ");
			br = new BufferedReader(new InputStreamReader(System.in));
			choice  = br.readLine();
			switch (choice) {
			
			case "register":
				userName="";
				System.out.println("Enter username: ");
				userName = br.readLine();
				System.out.println("Enter password:");
				password = "";
				password = br.readLine();
				register(rpcService, userName, password,br);
				break;
			case "login":
				userName="";
				System.out.println("Enter username: ");
				userName = br.readLine();
				System.out.println("Enter password:");
				password = "";
				password = br.readLine();
				login(rpcService, userName, password, br);
				break;
			case "quit":
				System.out.println("Application is going to shutdown");
				break;
				
				default: 
					System.err.println("Please enter a valid choice");
					
			}
			
		}while(!choice.equalsIgnoreCase("quit"));
		
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void login(RPCService rpcService, String userName, String password, BufferedReader br) throws IOException {
		try {
			//rpcService.login(userName, password);
			System.out.println(rpcService.login(userName, password));
		} catch(UserLoginException e) {
			System.err.println(e.getMessage());
			System.err.println("Try again");
			//br = new BufferedReader(new InputStreamReader(System.in));
			userName="";
			System.out.println("Enter username: ");
			userName = br.readLine();
			System.out.println("Enter password:");
			password = "";
			password = br.readLine();
			login(rpcService, userName, password,br);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void register(RPCService rpcService, String userName, String password, BufferedReader br) throws IOException {
		boolean isError = false;
		//BufferedReader br = null;
		try {
			//rpcService.register(userName, password);
			System.out.println(rpcService.register(userName, password));

		} catch (UserExistsException e) {
			isError = true;
			System.err.println("User " + userName + " already exists. Try a different one");
		} catch (Exception e) {

		} finally {

			if (isError) {
				
			//	br = new BufferedReader(new InputStreamReader(System.in));
				userName="";
				System.out.println("Enter username: ");
				userName = br.readLine();
				System.out.println("Enter password:");
				password = "";
				password = br.readLine();
				register(rpcService, userName, password, br);
				//br.close();
			}
			
			
		}

	}

}
