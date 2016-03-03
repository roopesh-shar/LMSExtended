package in.sg.rpc.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import in.sg.rpc.common.PropertiesReader;
import in.sg.rpc.common.RPCService;
import in.sg.rpc.common.UserLogin;
import in.sg.rpc.common.UserRegistration;
import in.sg.rpc.common.domain.Course;
import in.sg.rpc.common.domain.FeeDetails;
import in.sg.rpc.common.exception.UserExistsException;
import in.sg.rpc.common.exception.UserLoginException;
import in.sg.rpc.server.service.DBService;

//Service Implementation
@WebService(endpointInterface = "in.sg.rpc.common.RPCService")
public class RPCServer implements RPCService {

	private long idleTimeInMinute;
	private long maxIdleTimeLimit;
	private Date requestTime;
	private Date previousRequestTime;
	private Endpoint endpoint;

	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

		RPCServer server = new RPCServer();

		if (args.length != 1) {
			System.err.println("Please enter max idle time(minute). You may enter any integer between 1 and 60. Usage: java -jar RPCServer.jar <minute>");
		}
		server.setMaxIdleTimeLimit(Integer.valueOf(args[0]));

		server.start();

		System.out.println("RPC Server started. host= " + PropertiesReader.getProperty("server.ip") + " port=" + PropertiesReader.getProperty("server.port"));
	
	}

	private void setMaxIdleTimeLimit(Integer idleTimeLimit) {

		this.maxIdleTimeLimit = idleTimeLimit;

	}

	private void stop() {

		endpoint.stop();
		System.exit(0);

	}

	private void start() throws FileNotFoundException, IOException {

		init();
		
		System.out.println("Hi");

	}

	private void init() throws FileNotFoundException, IOException {
		endpoint = Endpoint.publish("http://" + PropertiesReader.getProperty("server.ip") + ":"
				+ PropertiesReader.getProperty("server.port") + "/ws/register", this);
		
		Executor executor = Executors.newFixedThreadPool(10);
		endpoint.setExecutor(executor);
		
		previousRequestTime = new Date();
		
		// This is a polling thread
		new Thread(new Runnable() {

			@Override
			public void run() {

				long diff;

				while (true) {

					diff = System.currentTimeMillis() - previousRequestTime.getTime();

					idleTimeInMinute = TimeUnit.MILLISECONDS.toMinutes(diff);
					
					if (idleTimeInMinute > maxIdleTimeLimit) {

						System.err.println("Max Idle Time Limit breach . Stoping Server.");

						stop();

					}
				}

			}
		}).start();
	}

	@Override
	public String register(String userName, String passWord) throws UserExistsException, IOException {

		requestTime = new Date();
		idleTimeInMinute = TimeUnit.MILLISECONDS.toMinutes(requestTime.getTime() - previousRequestTime.getTime());

		if (idleTimeInMinute <= maxIdleTimeLimit) {

			previousRequestTime = requestTime;
			UserRegistration user = new UserRegistration();

			return user.register(userName, passWord);
		} else {
			stop();
		}
		return null;

	}

	@Override
	public String login(String userName, String password) throws UserLoginException {

		requestTime = new Date();

		idleTimeInMinute = TimeUnit.MILLISECONDS.toMinutes(requestTime.getTime() - previousRequestTime.getTime());

		if (idleTimeInMinute <= maxIdleTimeLimit) {
			UserLogin login = new UserLogin();
			previousRequestTime = requestTime;
			return login.login(userName, password);
		} else {
			stop();
		}

		return null;

	}



	@Override
	public String getCourseDetailForUser(int userId) throws Exception {
		return DBService.getInstance().getCourseDetailForUser(userId);
	}

	@Override
	public FeeDetails getFeeDetailsforUserid(int userId) throws Exception {
		return DBService.getInstance().getFeeDetailsforUserid(userId);
	}







	
	
}
