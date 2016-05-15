package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.common.Business;
import in.sg.rpc.common.domain.Feedback;

public class FeedBackScreen extends Screen {

	private JFrame frame;
	private JPanel linkPanel;
	private JPanel headerPanel;
	private JPanel cmpinfoPanel;
	private JLabel cmpnamelebel;
	private JLabel welcomeLabel;
	private JButton goHomePageButton;
	private JPanel btnpnl;
	private JTextArea displayFeedbackTxtArea;
	private JTextArea submitFeedbackTxtArea;
	private JPanel feedbackDisplayPanel;
	private JPanel feedbackSubmitPanel;
	private JButton submitFeedbackButton;
	private JLabel feedbackLabel;
	private String[] feedbackArea = { "", "Course Feedback", "Trainer Feedback" };
	private String[] courses = { "", "Core Java", "J2EE", "Big Data And Hadoop", "Android", ".NET" };
	private JComboBox<String> courseList;
	private JComboBox<String> feedbackAreaList;

	public static void main(String[] args) {
		FeedBackScreen feedBack = new FeedBackScreen(); // Comments to revert
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					feedBack.open();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	@Override
	public void open() throws Exception {
		frame = new JFrame("Fee Receipt");
		linkPanel = new JPanel();
		addComponents(frame.getContentPane(), linkPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});

		goHomePageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.setVisible(false);
				HomeScreen screen = new HomeScreen(); // Comments to revert
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							screen.open(screen);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		submitFeedbackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Feedback feedbackSubmit = new Feedback();
				feedbackSubmit.setUserId(GUIDomain.CURRENT_USER_ID);
				feedbackSubmit.setUserName(GUIDomain.CURRENT_USER_NAME);
				feedbackSubmit.setFeedbackArea(feedbackAreaList.getSelectedItem().toString());
				feedbackSubmit.setFeedback(submitFeedbackTxtArea.getText());
				try {
					Business.getInstance();
					Business.submitFeedback(feedbackSubmit);
					JOptionPane.showMessageDialog(new JFrame("Success"), "Feedback Successfully posted");
					displayFeedbackTxtArea.append(feedbackSubmit.getUserName());
					displayFeedbackTxtArea.append(" wrote :");
					displayFeedbackTxtArea.append(submitFeedbackTxtArea.getText() + "\n\n");
					submitFeedbackTxtArea.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void addComponents(Container contentPane, JPanel linkPanel) throws Exception {

		/* Adding Header panel */
		headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		cmpinfoPanel = new JPanel();
		cmpnamelebel = new JLabel(GUIUtil.getHeaderTitle());
		cmpinfoPanel.add(cmpnamelebel);
		JLabel cmpimage = new JLabel(GUIUtil.getIcon());
		cmpinfoPanel.add(cmpimage);

		headerPanel.add(cmpinfoPanel);

		welcomeLabel = new JLabel("Welcome " + getUser());
		headerPanel.add(welcomeLabel);
		contentPane.add(headerPanel, BorderLayout.PAGE_START);

		feedbackDisplayPanel = new JPanel();
		feedbackDisplayPanel.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(feedbackDisplayPanel, BorderLayout.CENTER);
		displayFeedbackTxtArea = new JTextArea();
		displayFeedbackTxtArea.setPreferredSize(new Dimension(600, 400));
		displayFeedbackTxtArea.setEditable(false);
		displayFeedbackTxtArea.setBackground(UIManager.getColor(feedbackDisplayPanel));
		displayFeedbackTxtArea.setBorder(BorderFactory.createEtchedBorder());
		feedbackDisplayPanel.add(displayFeedbackTxtArea);

		feedbackSubmitPanel = new JPanel();
		feedbackSubmitPanel.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(feedbackSubmitPanel, BorderLayout.SOUTH);

		btnpnl = new JPanel();
		btnpnl.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(btnpnl, BorderLayout.WEST);
		goHomePageButton = new JButton("Back");
		btnpnl.add(goHomePageButton);

		feedbackAreaList = new JComboBox<String>(feedbackArea);
		courseList = new JComboBox<String>(courses);

		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();

		upperPanel.add(new JLabel("Select Feedback Area"));
		upperPanel.add(feedbackAreaList);
		upperPanel.add(courseList);

		feedbackLabel = new JLabel("Type feedback here");
		lowerPanel.add(feedbackLabel);
		submitFeedbackTxtArea = new JTextArea("");
		submitFeedbackTxtArea.setPreferredSize(new Dimension(500, 40));
		submitFeedbackTxtArea.setBorder(BorderFactory.createEtchedBorder());
		submitFeedbackTxtArea.setLineWrap(true);
		JScrollPane jp = new JScrollPane(submitFeedbackTxtArea);
		lowerPanel.add(jp);
		jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		submitFeedbackButton = new JButton("Submit");
		lowerPanel.add(submitFeedbackButton);
		feedbackSubmitPanel.add(upperPanel, BorderLayout.NORTH);
		feedbackSubmitPanel.add(lowerPanel, BorderLayout.CENTER);
		feedbackSubmitPanel.setPreferredSize(new Dimension(600, 100));
		GUIUtil.displayUserFeedback(1);

	}

	private String user;
	private int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = GUIDomain.CURRENT_USER_ID;
	}

	private String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = GUIDomain.CURRENT_USER_NAME;
	}

}
