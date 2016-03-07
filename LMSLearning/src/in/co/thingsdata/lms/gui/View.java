package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;
import in.co.thingsdata.lms.util.SpringUtilities;

public class View  extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame view;
	private String[] courses = {"", "Core Java", "J2EE", "Big Data And Hadoop", "Android", ".NET" };
	private String courseSelected;
	
	public void setView (JFrame view) {
		
		this.view = view;
		
	}

	public void go() {

		GUIUtil.initProperties();
		initializeGUI();
		GUIUtil.setupNetworking();
		GUIUtil.setupFileIO();
		GUIUtil.refreshGUI(view);
		
	}

	private void initializeGUI() {
		
		JPanel panelHeader = new JPanel();
		JPanel panelDetails = new JPanel(new SpringLayout());
		JPanel panelButtons = new JPanel();
		
		panelHeader.setLayout(new FlowLayout(SwingConstants.LEFT, 8, 8));
		
		JLabel labelHeaderName = new JLabel(GUIUtil.getHeaderTitle());
		
		JLabel labelHeaderImage = new JLabel(GUIUtil.getIcon());
		labelHeaderName.setPreferredSize(new Dimension(500, 30));
		labelHeaderImage.setPreferredSize(new Dimension(400, 100));
		labelHeaderName.setForeground(Color.blue);
		labelHeaderName.setFont(new Font("Serif", Font.BOLD, 20));
		
		GUIUtil.addComponents(panelHeader, labelHeaderName, labelHeaderImage);
		
		view.getContentPane().add(panelHeader, BorderLayout.PAGE_START);
		
		JLabel labelCourse = new JLabel("Course:");
		JLabel labelName = new JLabel("User Name:");
		JLabel labelEmail = new JLabel("Email:");
		JLabel labelPassword = new JLabel("Password:");
		JLabel labelPhoneNumber = new JLabel("Phone Number:");
		JLabel labelCountry = new JLabel("Country:");
		JLabel labelState = new JLabel("State:");
		JLabel labelAddress = new JLabel("Address:");
		JLabel labelCity = new JLabel("City:");
		JLabel labelPinCode = new JLabel("Pin Code:");
		
		
		JComboBox<String> courseList = new JComboBox<String>(courses);
		JTextField textFieldName = new JTextField();
		JTextField textFieldEmail = new JTextField();
		JPasswordField fieldPassword = new JPasswordField();
		JTextField textFieldPhoneNumber = new JTextField();
		JTextField textFieldAddress = new JTextField();
		JTextField textFieldCountry = new JTextField();
		JTextField textFieldState = new JTextField();
		JTextField textFieldCity = new JTextField();
		JTextField textFieldPinCode = new JTextField();
		
		GUIUtil.addComponents (panelDetails,labelCourse, courseList, labelName,textFieldName, labelEmail, textFieldEmail, labelPassword, fieldPassword, labelPhoneNumber, 
					textFieldPhoneNumber,labelCountry,textFieldCountry,labelState, textFieldState,labelCity, textFieldCity,labelAddress, textFieldAddress,labelPinCode,  textFieldPinCode);
		
		SpringUtilities.makeCompactGrid (panelDetails,
										10, 2, 		 //rows, cols
										20, 20,        //initX, initY
										5, 5);       //xPad, yPad
/*courseList.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed (ActionEvent e) {
				
				String course = String.valueOf(((JComboBox)e.getSource()).getSelectedItem());
				
				GUIDomain.STUDENT_COURSE courseSelected = course;
				if(courseList.getSelectedIndex()!=0){
					System.out.println("selection index"+courseList.getSelectedIndex());
					//System.out.println("You have selected " +  course + " course");	
				}
				
			}
		});
*/
		view.getContentPane().add(panelDetails, BorderLayout.CENTER);
		
		JButton buttonSubmit = new JButton("Submit");
		JButton buttonClear = new JButton("Clear");
		
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.LINE_AXIS));
		panelButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		buttonSubmit.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String name = textFieldName.getText();
				char[] pass = fieldPassword.getPassword();
				String password = new String (pass);
				String email = textFieldEmail.getText();
				String phone = textFieldPhoneNumber.getText();
				String address = textFieldAddress.getText();
				String city = textFieldCity.getText();
				String country = textFieldCountry.getText();
				String state = textFieldState.getText();
				
				System.out.println("Name=" + name + " Email=" + email + " Password=" + password+ "Phone Number=" + phone);
				
				GUIUtil.writeToFile (name, password, email, phone, address, city, country, state, courseSelected);	
				view.getContentPane().add(panelHeader, BorderLayout.PAGE_END);
				
			}
		});
		
		 buttonClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				textFieldName.setText("");
				fieldPassword.setText("");
				textFieldEmail.setText("");
				textFieldPhoneNumber.setText("");
				textFieldAddress.setText("");
				textFieldCity.setText("");
				textFieldCountry.setText("");
				textFieldState.setText("");
				textFieldPinCode.setText("");
				courseList.setSelectedIndex(0);
				
			}
		});
		
		GUIUtil.addComponents(panelButtons, Box.createHorizontalGlue(),buttonSubmit,Box.createRigidArea(new Dimension(10, 0)), buttonClear);
		
		view.getContentPane().add(panelButtons, BorderLayout.PAGE_END);
		
		view.setDefaultCloseOperation(View.DISPOSE_ON_CLOSE);
		
		view.pack();
		
		view.setVisible(true);
		
	}

}
