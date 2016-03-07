package in.sg.rpc.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import in.sg.rpc.common.exception.UserLoginException;
import in.sg.rpc.server.service.DBService;

public class UserLogin {

		public String login(String userName, String password) throws UserLoginException {
		boolean validUser = false;
		String response = null;
		try {
			response =DBService.getInstance().returnUserId(userName, password);
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (userName.equalsIgnoreCase(response)){
			validUser = true;
			System.out.println("Login successful for"  + userName);
		}
		if (!validUser) {
			throw new UserLoginException("User " + userName + " does not exists in database");
		} 
		return response;
	}
}
