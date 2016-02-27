package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.common.domain.Course;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CourseContentDetails {
	
	private JFrame frame;
	private JPanel linkPanel;
	private JPanel headerPanel;
	private JPanel cmpinfoPanel;
	private JLabel cmpnamelebel;
	private JLabel welcomeLabel;
	private JPanel contentPanel;
	private JTextArea txtAreacourseContent;
	private JPanel backButtonPanel;
	private JButton goHomePageButton;
	

	public static void main(String[] args) {

		CourseContentDetails courseContent = new CourseContentDetails(); // Comments to revert
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					courseContent.go();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	public void go() throws IOException {

		frame = new JFrame("Course Contents");
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

		goHomePageButton.addActionListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.setVisible(false);
				HomeScreen screen = new HomeScreen(); // Comments to revert
			    SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
					screen.go();
					}
				});
			}
			});
		}

	private void addComponents(Container contentPane, JPanel linkPanel) throws IOException {

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
		Course course= GUIUtil.getCourseDetailForUser(12345);
		String coursePath = course.getContentPath();
		//System.out.println(coursePath);
		String courseContent =GUIUtil.getCourseContent(coursePath).toString();
		contentPanel = new JPanel();
		contentPane.add(contentPanel,BorderLayout.EAST);
		txtAreacourseContent = new JTextArea(100,50);
		txtAreacourseContent.setBackground(UIManager.getColor(contentPanel));
		txtAreacourseContent.setEditable(false);
		txtAreacourseContent.setText(courseContent);
		contentPanel.add(txtAreacourseContent);
		

		backButtonPanel = new JPanel();
		goHomePageButton = new JButton("Back");
		backButtonPanel.add(goHomePageButton);
		contentPane.add(backButtonPanel,BorderLayout.WEST);
		
		cmpinfoPanel.setBorder(BorderFactory.createEtchedBorder());
		contentPanel.setBorder(BorderFactory.createEtchedBorder());
		//backButtonPanel.setBorder(BorderFactory.createEtchedBorder());
		
	}

	private String user;

	private String getUser() {
		return this.user;
	}

	public void setUser(String user) {

		this.user = user;

	}

}
