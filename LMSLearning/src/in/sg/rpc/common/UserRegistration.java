package in.sg.rpc.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import in.sg.rpc.common.exception.UserExistsException;

public class UserRegistration {

	File userDB = new File("resources/users.txt");

	public String register(String userName, String passWord) throws UserExistsException, IOException {

		String user = userName + "," + passWord;
		String line;
		boolean exists = false;
		String result="User already exists.";

		try (PrintWriter pw = new PrintWriter(new FileOutputStream(userDB, true));
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(userDB)));) {
			while (null != (line = br.readLine())) {
				if (userName.equalsIgnoreCase(line.split(",")[0])) {
					exists = true;
					throw new UserExistsException(result);
				}
			}

			if (!exists) {
				pw.println(user);
				pw.flush();
				result = "Registration successful";
			}

		} catch (IOException e) {
			
			e.printStackTrace();
			
			throw e;
		} 
		return result;
	}

}
