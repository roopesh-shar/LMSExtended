package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.client.RPCClient;
import in.sg.rpc.common.RPCService;
import in.sg.rpc.common.exception.UserExistsException;
import in.sg.rpc.common.exception.UserLoginException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.omg.CORBA.UserException;

public class LoginScreen {

	public void initializeLoginScreen() {

		JFrame frame = new JFrame ("LMS Login");
		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true);
		JPanel userDatailPanel = new JPanel();
		
		frame.getContentPane().add(userDatailPanel);
		
		placeComponents (userDatailPanel, frame);
		
		frame.setSize(new Dimension(1200,900));
		
		frame.setVisible(true);
		
	}

	private void placeComponents(final JPanel userDatailPanel, final JFrame frame) {

		JLabel instructionsLabel = new JLabel("Enter user name and password");
		JLabel userNameLabel = new JLabel("user name ");
		JLabel passwordLabel = new JLabel("password  ");
		JLabel exceptionLabel = new JLabel();
		
		 JTextField userNameTextField = new JTextField(25);
		JPasswordField passwordTextField = new JPasswordField(25);
		
		JButton loginButton = new JButton("login");
		loginButton.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				RPCClient client = new RPCClient();
				RPCService stub = null;
				try {
					stub =client.getRemoteService();
					GUIDomain.REMOTE_RPC_SERVICE = stub;
					//GUIDomain.REMOTE_RPC_SERVICE.register("Rsharma", "Qwerty");
					int userId =GUIUtil.getLoggedInUserId(userNameTextField.getText(), String.valueOf(passwordTextField.getPassword()));
					if (userId != 0){
						frame.setVisible(false);
						GUIDomain.CURRENT_USER_ID=userId;
						GUIDomain.CURRENT_USER_NAME=userNameTextField.getText();
						System.out.println(GUIDomain.CURRENT_USER_ID+","+GUIDomain.CURRENT_USER_NAME);
						HomeScreen screen = new HomeScreen();
						screen.setUser (userNameTextField.getText());
						screen.go();	
						}
					else{
						exceptionLabel.setText("Invalid UserName/password or User is not Active");
						throw new UserExistsException("Invalid Username or Password");
					}
					
					} catch (UserLoginException | MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (UserExistsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				
			}
			
		});
		
		userDatailPanel.setLayout(null);
		exceptionLabel.setBounds(10,3,200,25);
		userDatailPanel.add(exceptionLabel);
		instructionsLabel.setBounds(10,15,200,25);
		userDatailPanel.add(instructionsLabel);
		userNameLabel.setBounds(10,40,80,25);
		userDatailPanel.add(userNameLabel);
		userNameTextField.setBounds(100,40,160,25);
		userDatailPanel.add(userNameTextField);
		passwordLabel.setBounds(10,80,80,25);
		userDatailPanel.add(passwordLabel);
		passwordTextField.setBounds(100, 80, 160, 25);
		userDatailPanel.add(passwordTextField);
		loginButton.setBounds(100, 110, 80, 25);
		userDatailPanel.add(loginButton);
		/*shutDownButton.setBounds(200, 110, 100, 25);
		userDatailPanel.add(shutDownButton);*/
		
	}
	

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new LoginScreen().initializeLoginScreen();
				
			}
		});
	}

}
