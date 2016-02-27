package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.util.GUIDomain;
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
					GUIDomain.REMOTE_RPC_SERVICE.login(userNameTextField.getText(), String.valueOf(passwordTextField.getPassword()));
					} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UserLoginException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (isValidUser (userNameTextField.getText(), passwordTextField.getPassword(), instructionsLabel, userDatailPanel)) {
					//TODO: Go to home screen
					System.out.println("Validation successful");
					frame.setVisible(false);
					HomeScreen screen = new HomeScreen();
					screen.setUser (userNameTextField.getText());
					screen.go();
				}
			}
			
		});
		
		userDatailPanel.setLayout(null);
		instructionsLabel.setBounds(10,10,200,25);
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
	
	private boolean isValidUser(String user, char[] pass, JLabel instructionsLabel, JPanel userDatailPanel) {
		
		try {
			String loginUser = user;
			String loginPassword = String.valueOf(pass);
			if (null == loginUser || null == loginPassword) {
				throw new Exception ("user name or password is null");
			}
			if (loginUser.isEmpty() || loginPassword.isEmpty()) {
				throw new Exception ("user name or password is empty");
			}
			
			//TODO:[Brajveer] The value for username and password shall come from the database/file
			String userName = "";
			String password = "";
			String line;
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("resources/out.txt"))));
			
			while (null != (line = reader.readLine())) {
				
				userName = line.split(",")[0];
				password = line.split(",")[1];
				
			}
			
			if (!loginUser.equalsIgnoreCase(userName)) {
				throw new Exception ("Invalid user name.");
			}
			if (!loginPassword.equals(password)) {
				throw new Exception ("Invalid password.");
			}
				
			return true;
			
		}catch (Exception e) {
			instructionsLabel.setForeground(Color.RED);
			instructionsLabel.setText (e.getMessage());
			userDatailPanel.repaint();
		}
		return false;
		
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
