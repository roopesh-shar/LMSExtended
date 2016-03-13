package in.co.thingsdata.lms.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.common.Business;
import in.sg.rpc.common.exception.UserExistsException;
import in.sg.rpc.common.exception.UserLoginException;

public class LoginScreen extends Screen {
	private JFrame frame;
	private JPanel userDatailPanel;
	private JLabel instructionsLabel;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JTextField userNameTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton;
	private int userId;

	public void initializeLoginScreen() {
		frame = GUIUtil.createFrame("LMS Login");
		GUIUtil.setDefaultCloseOperation(frame, JFrame.DISPOSE_ON_CLOSE);
		GUIUtil.setResizable(frame, true);
		userDatailPanel = GUIUtil.createPanel();
		GUIUtil.addPanel(frame, userDatailPanel);
		placeComponents(userDatailPanel, frame);
		GUIUtil.setVisible(frame, true);
	}
	private void placeComponents(final JPanel userDatailPanel, final JFrame frame) {
		instructionsLabel = GUIUtil.createLabel("Enter user name and password");
		userNameLabel = GUIUtil.createLabel("user name ");
		passwordLabel = GUIUtil.createLabel("password  ");
		userNameTextField = GUIUtil.createTextField(25);
		passwordTextField = GUIUtil.createPasswordField(25);
		loginButton = GUIUtil.createButton("login");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					connectToServer();
					userId = getUserId();
					if (!(userId <= 0)) {
						openHomeScreen();
					} else {
						throw new UserExistsException("Invalid Username or Password");
					}
				} catch (UserLoginException | MalformedURLException e) {
					e.printStackTrace();
				} catch (UserExistsException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		userDatailPanel.setLayout(null);
		instructionsLabel.setBounds(10, 10, 200, 25);
		userDatailPanel.add(instructionsLabel);
		userNameLabel.setBounds(10, 40, 80, 25);
		userDatailPanel.add(userNameLabel);
		userNameTextField.setBounds(100, 40, 160, 25);
		userDatailPanel.add(userNameTextField);
		passwordLabel.setBounds(10, 80, 80, 25);
		userDatailPanel.add(passwordLabel);
		passwordTextField.setBounds(100, 80, 160, 25);
		userDatailPanel.add(passwordTextField);
		loginButton.setBounds(100, 110, 80, 25);
		userDatailPanel.add(loginButton);
	}
	protected int getUserId() throws UserLoginException {
		return Business.getInstance().getUserId(userNameTextField.getText(),
				String.valueOf(passwordTextField.getPassword()));
	}
	protected void connectToServer() throws MalformedURLException , Exception{
		Business.getInstance().connectToServer();
	}
	protected void openHomeScreen() throws Exception {
		GUIUtil.setVisible(frame,false);
		updateCurrentUser();
		System.out.println(GUIDomain.CURRENT_USER_ID + "," + GUIDomain.CURRENT_USER_NAME);
		HomeScreen screen = new HomeScreen();
		screen.setUser(userNameTextField.getText());
		screen.open();
	}
	private void updateCurrentUser() {
		GUIDomain.CURRENT_USER_ID = userId;
		GUIDomain.CURRENT_USER_NAME = userNameTextField.getText();
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LoginScreen().initializeLoginScreen();
			}
		});
	}
	@Override
	protected void open() throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LoginScreen().initializeLoginScreen();
			}
		});		
	}
}
