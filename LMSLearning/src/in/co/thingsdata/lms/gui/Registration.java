package in.co.thingsdata.lms.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class Registration extends JFrame implements ActionListener 
  { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
 
    Registration()
     {
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Registration");
 
        labelRegistrationForm = new JLabel("Registration Form:");
        labelRegistrationForm.setForeground(Color.blue);
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
 
        buttonSubmit.addActionListener(this);
        buttonClear.addActionListener(this);
 
        labelRegistrationForm.setBounds(100, 30, 400, 30);
        labelName.setBounds(80, 70, 200, 30);
        labelEmailId.setBounds(80, 110, 200, 30);
        labelCreatePassword.setBounds(80, 150, 200, 30);
        labelConfirmPassword.setBounds(80, 190, 200, 30);
        labelCountry.setBounds(80, 230, 200, 30);
        labelState.setBounds(80, 270, 200, 30);
        labelPhoneNumber.setBounds(80, 310, 200, 30);
        textFieldName.setBounds(300, 70, 200, 30);
        textFieldEmailId.setBounds(300, 110, 200, 30);
        textFieldCreatePassword.setBounds(300, 150, 200, 30);
        textFieldConfirmPassword.setBounds(300, 190, 200, 30);
        textFieldCountry.setBounds(300, 230, 200, 30);
        textFieldState.setBounds(300, 270, 200, 30);
        textFieldPhoneNumber.setBounds(300, 310, 200, 30);
        buttonSubmit.setBounds(50, 350, 100, 30);
        buttonClear.setBounds(170, 350, 100, 30);
 
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
 
    public void actionPerformed(ActionEvent e) 
     {
        if (e.getSource() == buttonSubmit)
         {
            int x = 0;
            String s1 = textFieldName.getText();
            String s2 = textFieldEmailId.getText();
 
            char[] s3 = textFieldCreatePassword.getPassword();
            char[] s4 = textFieldConfirmPassword.getPassword(); 
            String s8 = new String(s3);
            String s9 = new String(s4);
 
            String s5 = textFieldCountry.getText();
            String s6 = textFieldState.getText();
            String s7 = textFieldPhoneNumber.getText();
            if (s8.equals(s9))
           {
                try
               {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@mcndesktop07:1521:xe", "sandeep", "welcome");
                    PreparedStatement ps = con.prepareStatement("insert into reg values(?,?,?,?,?,?)");
                    ps.setString(1, s1);
                    ps.setString(2, s2);
                    ps.setString(3, s8);
                    ps.setString(4, s5);
                    ps.setString(5, s6);
                    ps.setString(6, s7);
                    ResultSet rs = ps.executeQuery();
                    x++;
                    if (x > 0) 
                    {
                        JOptionPane.showMessageDialog(buttonSubmit, "Data Saved Successfully");
                    }
                }
          catch (Exception ex) 
                {
                    System.out.println(ex);
                }
            }
          else
           {
                JOptionPane.showMessageDialog(buttonSubmit, "Password Does Not Match");
            } 
        } 
          else
       {
            textFieldName.setText("");
            textFieldEmailId.setText("");
            textFieldCreatePassword.setText("");
            textFieldConfirmPassword.setText("");
            textFieldCountry.setText("");
            textFieldState.setText("");
            textFieldPhoneNumber.setText("");
        }
    } 
    public static void main(String args[])
   {
    	SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {

				 new Registration();
				 
			}
		});
       
    }
}