package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.domain.User;
import in.co.thingsdata.lms.util.GUIUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import in.co.thingsdata.lms.util.GUIUtil;

public class ProfileScreen {

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

	public static void main(String[] args) {

		ProfileScreen screen = new ProfileScreen(); // Comments to revert
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				screen.go();

			}
		});

	}

	protected void go() {

		JFrame frame = new JFrame("Profile");
		JPanel profilepanel = new JPanel();
		User user = null;
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

	}

	private void setUserData(User user) {

		txtname.setText(user.getName());
		txtaddress.setText(user.getAddress());
		txtemail.setText(user.getEmailid());
		txtdob.setText(user.getDob());
		txtcourse.setText(user.getCourse());

	}

	protected void setUser(JFrame frame) {

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
