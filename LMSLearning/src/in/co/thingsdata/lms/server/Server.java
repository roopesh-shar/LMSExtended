package in.co.thingsdata.lms.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private boolean stop;

	public void start() {

		/*int portNumber = Integer.parseInt("4444");*/
		final int portNumber = Integer.parseInt("4444");

		new Thread(new Runnable() {

			@Override
			public void run() {

				stop = false;

				try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt("4444"));
						Socket clientSocket = serverSocket.accept();
						PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
						BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
					String inputLine;

					while (!stop) {
						while ((inputLine = in.readLine()) != null) {
							out.println(inputLine);
						}
					}
					System.out.println("Quiting...");
				} catch (IOException e) {
					System.out.println("Exception caught when trying to listen on port " + portNumber
							+ " or listening for a connection");
					System.out.println(e.getMessage());
				}
			}
		}).start();

	}

	public void stop() {
		stop = true;
	}
	
	public boolean isRunning() {
		return !stop;
	}

}
