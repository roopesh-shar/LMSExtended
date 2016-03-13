package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import in.co.thingsdata.lms.util.GUIUtil;

public class CourseContentDetails extends Screen {
	
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
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	public void go() throws Exception {

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
					try {
						screen.open();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				});
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
		String courseContent= GUIUtil.getCourseDetailForUser(12345);
		//System.out.println(course.getCourseId()+","+course.getContentPath());
		//String coursePath = course.getContentPath();
		//System.out.println(coursePath);
		//String courseContent =GUIDomain.REMOTE_RPC_SERVICE.displayCourseDetails(course.getContentPath());//GUIUtil.getCourseContent(coursePath).toString();
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

	@Override
	public void open() throws Exception {
		/*SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					go();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		*/
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

}
