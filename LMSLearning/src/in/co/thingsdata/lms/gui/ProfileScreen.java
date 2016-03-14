package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.common.domain.User;

public class ProfileScreen extends Screen {

	private JLabel lblname = new JLabel("Name");
	private JLabel lbladdress = new JLabel("Address");
	private JLabel lbldob = new JLabel("Date of Birth");
	private JLabel lblemail = new JLabel("Email Id");
	private JLabel lblcourse = new JLabel("Registered Course");
	private JTextField txtname = new JTextField();
	private JTextField txtaddress = new JTextField();
	private JTextField txtdob = new JTextField();
	private JTextField txtemail = new JTextField();
	private JTextField txtcourse = new JTextField();
	private int userId;
	private String userName;
	private JButton btnEditProfile = new JButton("Edit Profile");
	private JButton btnSaveProfile = new JButton("Save");
	private JButton btnBack = new JButton("Back");


	private int i=100;

	User user = null;
	public static void main(String[] args) {

		ProfileScreen screen = new ProfileScreen(); // Comments to revert
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					screen.open();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
		});

	}
	@Override
	public void open() throws Exception {

		JFrame frame = new JFrame("Profile");
		JPanel profilepanel = new JPanel();

		try {
			user = GUIUtil.getUserDetails(12345);
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
		
		
		/*Handle Click Edit profile button*/
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
				HomeScreen screen = new HomeScreen(); // Comments to revert
			    SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
					try {
						screen.open();
					} catch (Exception e) {
						e.printStackTrace();
					}
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

	}

	protected  void setTextUneditable() {
		txtname.setEditable(false);
		txtaddress.setEditable(false);
		txtemail.setEditable(false);
		txtdob.setEditable(false);
		txtcourse.setEditable(false);
	}
	
	protected  void setTextEditable() {
		txtname.setEditable(true);
		txtaddress.setEditable(true);
		txtemail.setEditable(true);
		txtdob.setEditable(true);
		txtcourse.setEditable(true);
	}
	
	private void saveProfile(User user) throws NumberFormatException, FileNotFoundException, IOException{
		//user.setUserid(12345);

		user.setName(txtname.getText());
		user.setEmailid(txtemail.getText());
		user.setDob(txtdob.getText());
		user.setCourse(txtcourse.getText());
		user.setAddress(txtaddress.getText());
		boolean status =GUIUtil.saveUserDetails(user);
		if (status=true){
			JOptionPane.showMessageDialog(new JFrame("Success"), "Profile Successfully Updated");
		}
		else{
			JOptionPane.showMessageDialog(new JFrame("Success"), "Error in Profile Update, Please try again later");
		}
	}

	private void addComponents(Container contentPane, JPanel profilepanel) {

		/* Adding Header panel */
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		JPanel cmpinfoPanel = new JPanel();
		JLabel cmpnamelebel = new JLabel(GUIUtil.getHeaderTitle());
		cmpinfoPanel.add(cmpnamelebel);
		JLabel cmpimage = new JLabel(GUIUtil.getIcon());
		cmpinfoPanel.add(cmpimage);
		headerPanel.add(cmpinfoPanel);
		JLabel welcomeLabel = new JLabel("Welcome " + getUserName());
		headerPanel.add(welcomeLabel);
		contentPane.add(headerPanel, BorderLayout.PAGE_START);

		/* Adding panel for details */
		JPanel detailpanel = new JPanel();
		detailpanel.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(detailpanel, BorderLayout.CENTER);

		/* Adding Panel for Profile details under detailpanel */
		JPanel profiledtlpanel = new JPanel();
		detailpanel.setLayout(new BorderLayout());
		profiledtlpanel.setBorder(BorderFactory.createEtchedBorder());

		/* Adding imagepanel for Profile Image */
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
		profiledtlpanel.add(lbladdress, gBC);
		gBC.gridx = 1;
		gBC.gridy = 2;
		profiledtlpanel.add(txtaddress, gBC);
		gBC.gridx = 0;
		gBC.gridy = 4;
		profiledtlpanel.add(lbldob, gBC);
		gBC.gridx = 1;
		gBC.gridy = 4;
		profiledtlpanel.add(txtdob, gBC);
		gBC.gridx = 0;
		gBC.gridy = 6;
		profiledtlpanel.add(lblemail, gBC);
		gBC.gridx = 1;
		gBC.gridy = 6;
		profiledtlpanel.add(txtemail, gBC);
		gBC.gridx = 0;
		gBC.gridy = 7;
		profiledtlpanel.add(lblcourse, gBC);
		gBC.gridx = 1;
		gBC.gridy = 7;
		profiledtlpanel.add(txtcourse, gBC);

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
