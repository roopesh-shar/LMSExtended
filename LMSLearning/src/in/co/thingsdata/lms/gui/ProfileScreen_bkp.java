/*package in.co.thingsdata.lms.gui;


import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil_bkp;
import in.sg.rpc.common.domain.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ProfileScreen_bkp {

	private JLabel lblname = new JLabel("Name");
	private JLabel lbladdress = new JLabel("Address");
	private JLabel lbldob = new JLabel("Date of Birth");
	private JLabel lblemail = new JLabel("Email Id");
	private JLabel lblcourse = new JLabel("Registered Course");
	private JLabel lblcountry = new JLabel("Country");
	private JLabel lblstate = new JLabel("State");
	private JLabel lblcity = new JLabel("City");
	private JLabel lblpincode = new JLabel("Pin Code");
	private JLabel lblphonenum = new JLabel("Contact Number");
	
	private JTextField txtname = new JTextField();
	private JTextField txtaddress = new JTextField();
	private JTextField txtdob = new JTextField();
	private JTextField txtemail = new JTextField();
	private JTextField txtcourse = new JTextField();
	private JTextField txtcountry = new JTextField();
	private JTextField txtstate = new JTextField();
	private JTextField txtcity = new JTextField();
	private JTextField txtpincode = new JTextField();
	private JTextField txtphonenum = new JTextField();
	private int userId;
	private String userName;
	private JButton btnEditProfile = new JButton("Edit Profile");
	private JButton btnSaveProfile = new JButton("Save");
	private JButton btnBack = new JButton("Back");


	private int i=100;

	User user = null;
	public static void main(String[] args) {

		ProfileScreen_bkp screen = new ProfileScreen_bkp(); // Comments to revert
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				screen.go();
				

			}
		});

	}

	public void go() {

		JFrame frame = new JFrame("Profile");
		JPanel profilepanel = new JPanel();

		try {
			try {
				user = GUIUtil_bkp.getUserDetails(GUIDomain.CURRENT_USER_ID);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setUserName(user.getName());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		addComponents(frame.getContentPane(), profilepanel);
		setUserData(user);
		setTextUneditable();
		
		
		Handle Click Edit profile button
	btnEditProfile.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				setTextEditable();
				btnEditProfile.setVisible(false);
				btnSaveProfile.setVisible(true);
			
			}
			
		});
		
		
	btnSaveProfile.addActionListener (new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			//User saveUser = new User(12345);
			try {
				saveProfile(user);
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setTextUneditable();
			btnEditProfile.setVisible(true);
			btnSaveProfile.setVisible(false);
			
		}
		
	});
		
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setSize(800, 600);
		// frame.pack();

		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		
		btnBack.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.setVisible(false);
				HomeScreen_bkp screen = new HomeScreen_bkp(); // Comments to revert
			    SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
					screen.go();
					}
				});
			}
			});

	}

	private void setUserData(User user) {

		txtname.setText(user.getName());
		txtaddress.setText(user.getAddress());
		txtemail.setText(user.getEmailid());
		txtdob.setText(user.getDob());
		txtcourse.setText(user.getCourse());
		txtphonenum.setText(String.valueOf(user.getPhoneNum()));
		txtcity.setText(user.getCity());
		txtstate.setText(user.getState());
		txtcountry.setText(user.getCountry());
		txtpincode.setText(String.valueOf(user.getPinCode()));

	}

	protected  void setTextUneditable() {
		txtname.setEditable(false);
		txtaddress.setEditable(false);
		txtemail.setEditable(false);
		txtdob.setEditable(false);
		txtcourse.setEditable(false);
		txtpincode.setEditable(false);
		txtcity.setEditable(false);
		txtstate.setEditable(false);
		txtcountry.setEditable(false);
		txtphonenum.setEditable(false);
	}
	
	protected  void setTextEditable() {
		txtname.setEditable(false);
		txtaddress.setEditable(true);
		txtemail.setEditable(true);
		txtdob.setEditable(true);
		txtcourse.setEditable(false);
		txtpincode.setEditable(true);
		txtcity.setEditable(true);
		txtstate.setEditable(true);
		txtcountry.setEditable(true);
		txtphonenum.setEditable(true);
	}
	
	private void saveProfile(User user) throws  SQLException, NumberFormatException, FileNotFoundException, IOException{
		//user.setUserid(12345);

		user.setName(txtname.getText());
		user.setEmailid(txtemail.getText());
		user.setDob(txtdob.getText());
		user.setAddress(txtaddress.getText());
		user.setCountry(txtcountry.getText());
		user.setCity(txtcity.getText());
		user.setState(txtstate.getText());
		user.setPhoneNum(Long.valueOf(txtphonenum.getText()));
		user.setPinCode(Long.valueOf(txtpincode.getText()));
		user.setUserid(GUIDomain.CURRENT_USER_ID);
		System.out.println(user.getPhoneNum());
		
		boolean status =GUIUtil_bkp.saveUserDetails(user);
		if (status=true){
			JOptionPane.showMessageDialog(new JFrame("Success"), "Profile Successfully Updated");
		}
		else{
			JOptionPane.showMessageDialog(new JFrame("Success"), "Error in Profile Update, Please try again later");
		}
	}

	private void addComponents(Container contentPane, JPanel profilepanel) {

		 Adding Header panel 
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		JPanel cmpinfoPanel = new JPanel();
		JLabel cmpnamelebel = new JLabel(GUIUtil_bkp.getHeaderTitle());
		cmpinfoPanel.add(cmpnamelebel);
		JLabel cmpimage = new JLabel(GUIUtil_bkp.getIcon());
		cmpinfoPanel.add(cmpimage);
		headerPanel.add(cmpinfoPanel);
		JLabel welcomeLabel = new JLabel("Welcome " + getUserName());
		headerPanel.add(welcomeLabel);
		contentPane.add(headerPanel, BorderLayout.PAGE_START);

		 Adding panel for details 
		JPanel detailpanel = new JPanel();
		detailpanel.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(detailpanel, BorderLayout.CENTER);

		 Adding Panel for Profile details under detailpanel 
		JPanel profiledtlpanel = new JPanel();
		detailpanel.setLayout(new BorderLayout());
		profiledtlpanel.setBorder(BorderFactory.createEtchedBorder());

		 Adding imagepanel for Profile Image 
		JPanel imagepanel = new JPanel();

		JLabel profileimage = new JLabel(new ImageIcon(
				"C:/Users/roopesh.sharma/workspace/logo_6.png"));
		imagepanel.add(profileimage);

		detailpanel.add(profiledtlpanel, BorderLayout.CENTER);
		detailpanel.add(imagepanel, BorderLayout.EAST);

		profiledtlpanel.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.gridwidth = 1;
		gBC.weightx = 1.0;
		gBC.gridx = 0;
		gBC.gridy = 0;

		profiledtlpanel.add(lblname, gBC);
		gBC.gridx = 1;
		gBC.gridy = 0;
		profiledtlpanel.add(txtname, gBC);
		gBC.gridx = 0;
		gBC.gridy = 2;
		profiledtlpanel.add(lbldob, gBC);
		gBC.gridx = 1;
		gBC.gridy = 2;
		profiledtlpanel.add(txtdob, gBC);
		gBC.gridx = 0;
		gBC.gridy = 4;
		profiledtlpanel.add(lblemail, gBC);
		gBC.gridx = 1;
		gBC.gridy = 4;
		profiledtlpanel.add(txtemail, gBC);
		gBC.gridx = 0;
		gBC.gridy = 6;
		profiledtlpanel.add(lblcourse, gBC);
		gBC.gridx = 1;
		gBC.gridy = 6;
		profiledtlpanel.add(txtcourse, gBC);
		gBC.gridx = 0;
		gBC.gridy = 7;
		profiledtlpanel.add(lblcity, gBC);
		gBC.gridx = 1;
		gBC.gridy = 7;
		profiledtlpanel.add(txtcity, gBC);
		gBC.gridx = 0;
		gBC.gridy = 8;
		profiledtlpanel.add(lblstate, gBC);
		gBC.gridx = 1;
		gBC.gridy = 8;
		profiledtlpanel.add(txtstate, gBC);
		gBC.gridx = 0;
		gBC.gridy = 9;
		profiledtlpanel.add(lblcountry, gBC);
		gBC.gridx = 1;
		gBC.gridy = 9;
		profiledtlpanel.add(txtcountry, gBC);
		gBC.gridx = 0;
		gBC.gridy = 10;
		profiledtlpanel.add(lblpincode, gBC);
		gBC.gridx = 1;
		gBC.gridy = 10;
		profiledtlpanel.add(txtpincode, gBC);
		gBC.gridx = 0;
		gBC.gridy = 11;
		profiledtlpanel.add(lblphonenum, gBC);
		gBC.gridx = 1;
		gBC.gridy = 11;
		profiledtlpanel.add(txtphonenum, gBC);

		// profiledtlpanel.add(lblname);
		JPanel btnPanel = new JPanel();
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		btnPanel.add(btnBack);
		btnPanel.add(btnEditProfile);
		btnPanel.add(btnSaveProfile);
		btnSaveProfile.setVisible(false);
		contentPane.add(btnPanel, BorderLayout.SOUTH);
		
		
		
	}

	private String getUserName() {
		return this.userName;
	}

	public void setUser(int userId) {
		this.userId = userId;
	}

	private void setUserName(String userName) {
		this.userName = userName;
	}

}
*/