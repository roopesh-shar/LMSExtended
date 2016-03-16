package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.common.Business;
import in.sg.rpc.common.domain.User;

public class ProfileScreen extends Screen {

	private JFrame frame;
	private JPanel profilepanel;
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

	private int i = 100;

	private User user = null;
	private JPanel detailpanel;
	private JPanel btnPanel;
	private JPanel profiledtlpanel;
	private JPanel imagepanel;
	private JLabel profileimage;

	public static void main(String[] args) {
		ProfileScreen screen = new ProfileScreen(); 
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
	@Override
	public void open() throws Exception {

		frame = GUIUtil.createFrame("Profile");
		profilepanel = GUIUtil.createPanel();
		try {
			user = Business.getInstance().getUserDetails(GUIDomain.CURRENT_USER_ID);
			setUserName(user.getName());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		addComponents(frame.getContentPane());
		setUserData(user);
		setTextUneditable();
		btnEditProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setTextEditable();
				btnEditProfile.setVisible(false);
				btnSaveProfile.setVisible(true);
			}
		});

		btnSaveProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					saveProfile(user);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
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

		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				frame.setVisible(false);
				HomeScreen screen = new HomeScreen();
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

	private void addComponents(Container contentPane) {
		createHeaderPanel(contentPane);
		createDetailsPanel(contentPane);
		profiledtlpanel = GUIUtil.createPanel();
		GUIUtil.setLayout(detailpanel, new BorderLayout());
		GUIUtil.setBorder(profiledtlpanel, BorderFactory.createEtchedBorder());
		imagepanel = GUIUtil.createPanel();
		profileimage = GUIUtil.createLabel(GUIUtil.getIcon());
		GUIUtil.addComponents(imagepanel, profileimage);
		GUIUtil.addComponents(detailpanel, profiledtlpanel, BorderLayout.CENTER);
		GUIUtil.addComponents(detailpanel, imagepanel, BorderLayout.EAST);
		GUIUtil.setLayout(profiledtlpanel, new GridBagLayout());
		GridBagConstraints gBC = GUIUtil.gridbagContraint();
		createProfileDataUI(gBC);
		createButtonPanel(contentPane);
	}

	private void createProfileDataUI(GridBagConstraints gBC) {
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
	}

	private void createButtonPanel(Container contentPane) {
		// GUIUtil.setLayout(detailpanel, new BorderLayout());
		btnPanel = GUIUtil.createPanel();
		GUIUtil.setBorder(btnPanel, BorderFactory.createEtchedBorder());
		btnSaveProfile.setVisible(false);
		GUIUtil.addComponents(btnPanel, btnBack, btnEditProfile, btnSaveProfile);
		GUIUtil.addComponents(contentPane, btnPanel, BorderLayout.SOUTH);
	}

	private void createDetailsPanel(Container contentPane) {
		detailpanel = GUIUtil.createPanel();
		GUIUtil.setBorder(detailpanel, BorderFactory.createEtchedBorder());
		// createProfiledDtPanel();
		// GUIUtil.addComponents(detailpanel, profiledtlpanel,
		// BorderLayout.CENTER);
		GUIUtil.addComponents(contentPane, detailpanel, BorderLayout.CENTER);
	}

	/*
	 * private void createProfiledDtPanel() { profiledtlpanel =
	 * GUIUtil.createPanel(); JPanel imagepanel = GUIUtil.createPanel();
	 * profiledtlpanel.setBorder(BorderFactory.createEtchedBorder()); JLabel
	 * profileimage = new JLabel(new
	 * ImageIcon("C:/Users/roopesh.sharma/workspace/logo_6.png"));
	 * imagepanel.add(profileimage); profiledtlpanel.setLayout(new
	 * GridBagLayout()); GridBagConstraints gBC = new GridBagConstraints();
	 * gBC.fill = GridBagConstraints.HORIZONTAL; gBC.insets = new Insets(5, 5,
	 * 5, 5); gBC.gridwidth = 1; gBC.weightx = 1.0; gBC.gridx = 0; gBC.gridy =
	 * 0; profiledtlpanel.add(lblname, gBC); gBC.gridx = 1; gBC.gridy = 0;
	 * profiledtlpanel.add(txtname, gBC); gBC.gridx = 0; gBC.gridy = 2;
	 * profiledtlpanel.add(lbladdress, gBC); gBC.gridx = 1; gBC.gridy = 2;
	 * profiledtlpanel.add(txtaddress, gBC); gBC.gridx = 0; gBC.gridy = 4;
	 * profiledtlpanel.add(lbldob, gBC); gBC.gridx = 1; gBC.gridy = 4;
	 * profiledtlpanel.add(txtdob, gBC); gBC.gridx = 0; gBC.gridy = 6;
	 * profiledtlpanel.add(lblemail, gBC); gBC.gridx = 1; gBC.gridy = 6;
	 * profiledtlpanel.add(txtemail, gBC); gBC.gridx = 0; gBC.gridy = 7;
	 * profiledtlpanel.add(lblcourse, gBC); gBC.gridx = 1; gBC.gridy = 7;
	 * profiledtlpanel.add(txtcourse, gBC); GUIUtil.addComponents(detailpanel,
	 * imagepanel, BorderLayout.EAST); }
	 */
	private void createHeaderPanel(Container contentPane) {
		JPanel headerPanel = GUIUtil.createPanel();
		GUIUtil.setLayout(headerPanel, new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		JPanel cmpinfoPanel = GUIUtil.createPanel();
		JLabel cmpnamelebel = GUIUtil.createLabel(GUIUtil.getHeaderTitle());
		GUIUtil.addComponents(cmpinfoPanel, cmpnamelebel);
		JLabel cmpimage = GUIUtil.createLabel(GUIUtil.getIcon());
		GUIUtil.addComponents(cmpinfoPanel, cmpimage);
		GUIUtil.addComponents(headerPanel, cmpinfoPanel);
		JLabel welcomeLabel = GUIUtil.createLabel("Welcome " + getUserName());
		GUIUtil.addComponents(headerPanel, welcomeLabel);
		GUIUtil.addComponents(contentPane, headerPanel, BorderLayout.PAGE_START);
	}

	private void setUserData(User user) {

		txtname.setText(user.getName());
		txtaddress.setText(user.getAddress());
		txtemail.setText(user.getEmailid());
		txtdob.setText(user.getDob());
		txtcourse.setText(user.getCourse());

	}

	protected void setTextUneditable() {
		txtname.setEditable(false);
		txtaddress.setEditable(false);
		txtemail.setEditable(false);
		txtdob.setEditable(false);
		txtcourse.setEditable(false);
	}

	protected void setTextEditable() {
		txtname.setEditable(true);
		txtaddress.setEditable(true);
		txtemail.setEditable(true);
		txtdob.setEditable(true);
		txtcourse.setEditable(true);
	}

	private void saveProfile(User user) throws Exception {
		// user.setUserid(12345);

		user.setName(txtname.getText());
		user.setEmailid(txtemail.getText());
		user.setDob(txtdob.getText());
		user.setCourse(txtcourse.getText());
		user.setAddress(txtaddress.getText());
		boolean status = Business.getInstance().saveUserDetails(user);
		if (status = true) {
			JOptionPane.showMessageDialog(new JFrame("Success"), "Profile Successfully Updated");
		} else {
			JOptionPane.showMessageDialog(new JFrame("Success"), "Error in Profile Update, Please try again later");
		}
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
