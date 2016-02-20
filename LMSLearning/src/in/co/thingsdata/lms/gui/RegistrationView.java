package in.co.thingsdata.lms.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class RegistrationView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		RegistrationView view = new RegistrationView();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				view.draw();

			}
		});
	}

	protected void draw() {

		setVisible(true);
		setSize(700, 700);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Student Registration");

		Icon image;

		JLabel labelCompany;
		JLabel labelCompanyLogo;
		JLabel labelRegistrationForm;
		JLabel labelName;
		JLabel labelEmailId;
		JLabel labelCreatePassword;
		JLabel labelConfirmPassword;
		JLabel labelCountry;
		JLabel labelState;
		JLabel labelPhoneNumber;

		JTextField textFieldName;
		JTextField textFieldEmailId;
		JTextField textFieldCountry;
		JTextField textFieldState;
		JTextField textFieldPhoneNumber;

		JButton buttonSubmit, buttonClear;
		JPasswordField textFieldCreatePassword, textFieldConfirmPassword;

		labelCompany = new JLabel("Things Data Technologies Private Limited.");
		labelCompany.setForeground(Color.blue);
		labelCompany.setFont(new Font("Serif", Font.BOLD, 25));

		image = new ImageIcon("C:/Users/Pankaj/workspace/logo_6.png");

		labelCompanyLogo = new JLabel(image, SwingConstants.RIGHT);

		labelRegistrationForm = new JLabel("Registration Form:");
		labelRegistrationForm.setForeground(Color.black);
		labelRegistrationForm.setFont(new Font("Serif", Font.BOLD, 20));

		labelName = new JLabel("Name:");
		labelEmailId = new JLabel("Email-ID:");
		labelCreatePassword = new JLabel("Create Passowrd:");
		labelConfirmPassword = new JLabel("Confirm Password:");
		labelCountry = new JLabel("Country:");
		labelState = new JLabel("State:");
		labelPhoneNumber = new JLabel("Phone No:");
		textFieldName = new JTextField();
		textFieldEmailId = new JTextField();
		textFieldCreatePassword = new JPasswordField();
		textFieldConfirmPassword = new JPasswordField();
		textFieldCountry = new JTextField();
		textFieldState = new JTextField();
		textFieldPhoneNumber = new JTextField();

		buttonSubmit = new JButton("Submit");
		buttonClear = new JButton("Clear");

		buttonSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {

				String name = textFieldName.getText();
				String email = textFieldEmailId.getText();

				char[] passwordCreate = textFieldCreatePassword.getPassword();
				char[] passwordConfirmed = textFieldConfirmPassword.getPassword();
				String paswordCreateStr = new String(passwordCreate);
				String passwordConfirmedStr = new String(passwordConfirmed);

				String country = textFieldCountry.getText();
				String state = textFieldState.getText();
				String phoneNumber = textFieldPhoneNumber.getText();

				if (paswordCreateStr.equals(passwordConfirmedStr)) {
					try {
						/*Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@mcndesktop07:1521:xe",
								"", "");
						PreparedStatement ps = con.prepareStatement("insert into reg values(?,?,?,?,?,?)");
						ps.setString(1, name);
						ps.setString(2, email);
						ps.setString(3, paswordCreateStr);
						ps.setString(4, country);
						ps.setString(5, state);
						ps.setString(6, phoneNumber);
						ResultSet rs = ps.executeQuery();
						x++;
						if (x > 0) {
							JOptionPane.showMessageDialog(buttonSubmit, "Data Saved Successfully");
						}*/
						File file = new File ("E:/student/data/info.txt");
						
						PrintWriter pw = new PrintWriter(file);
						
						pw.println(name);
						pw.println(email);
						pw.println(paswordCreateStr);
						pw.println(country);
						pw.println(state);
						pw.println(phoneNumber);
						
						pw.close();
						
						JOptionPane.showMessageDialog(buttonSubmit, "Data Saved Successfully");
								
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else {
					JOptionPane.showMessageDialog(buttonSubmit, "Password Does Not Match");
				}

			}
		});
		buttonClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				textFieldName.setText("");
				textFieldEmailId.setText("");
				textFieldCreatePassword.setText("");
				textFieldConfirmPassword.setText("");
				textFieldCountry.setText("");
				textFieldState.setText("");
				textFieldPhoneNumber.setText("");

			}
		});

		labelCompany.setBounds(60, 0, 480, 30);
		labelCompanyLogo.setBounds(480, 0, 200, 50);
		labelRegistrationForm.setBounds(60, 40, 400, 30);
		labelName.setBounds(80, 80, 200, 30);
		labelEmailId.setBounds(80, 120, 200, 30);
		labelCreatePassword.setBounds(80, 160, 200, 30);
		labelConfirmPassword.setBounds(80, 200, 200, 30);
		labelCountry.setBounds(80, 240, 200, 30);
		labelState.setBounds(80, 280, 200, 30);
		labelPhoneNumber.setBounds(80, 320, 200, 30);
		textFieldName.setBounds(300, 80, 200, 30);
		textFieldEmailId.setBounds(300, 120, 200, 30);
		textFieldCreatePassword.setBounds(300, 160, 200, 30);
		textFieldConfirmPassword.setBounds(300, 200, 200, 30);
		textFieldCountry.setBounds(300, 240, 200, 30);
		textFieldState.setBounds(300, 280, 200, 30);
		textFieldPhoneNumber.setBounds(300, 320, 200, 30);
		buttonSubmit.setBounds(50, 360, 100, 30);
		buttonClear.setBounds(170, 360, 100, 30);

		add(labelCompany);
		add(labelCompanyLogo);
		add(labelRegistrationForm);
		add(labelName);
		add(textFieldName);
		add(labelEmailId);
		add(textFieldEmailId);
		add(labelCreatePassword);
		add(textFieldCreatePassword);
		add(labelConfirmPassword);
		add(textFieldConfirmPassword);
		add(labelCountry);
		add(textFieldCountry);
		add(labelState);
		add(textFieldState);
		add(labelPhoneNumber);
		add(textFieldPhoneNumber);
		add(buttonSubmit);
		add(buttonClear);

	}

}
