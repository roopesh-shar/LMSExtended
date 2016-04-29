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
import in.sg.rpc.common.Business;

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
		CourseContentDetails courseContent = new CourseContentDetails(); 
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					courseContent.open();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void addComponents(Container contentPane, JPanel linkPanel) throws Exception {
		createHeaderPanel(contentPane);
		createContentPanel(contentPane);
		createCourseContentTextArea(contentPane);
		backButtonPanel = new JPanel();
		goHomePageButton = new JButton("Back");
		backButtonPanel.add(goHomePageButton);
		contentPane.add(backButtonPanel, BorderLayout.WEST);
		cmpinfoPanel.setBorder(BorderFactory.createEtchedBorder());
		contentPanel.setBorder(BorderFactory.createEtchedBorder());
	}
	private void createCourseContentTextArea(Container contentPane) {
		String courseContent = null;
		try {
			courseContent = GUIUtil.getCourseDetailForUser(12345);
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtAreacourseContent = GUIUtil.createTextArea(100, 50);
		GUIUtil.setBackground(txtAreacourseContent, UIManager.getColor(contentPanel));
		GUIUtil.setEditable(txtAreacourseContent, false);
		GUIUtil.setText(txtAreacourseContent, courseContent);
		GUIUtil.addComponents(contentPanel, txtAreacourseContent);
	}

	private void createContentPanel(Container contentPane) {
		contentPanel = GUIUtil.createPanel();
		GUIUtil.addComponents(contentPane, contentPanel, BorderLayout.CENTER);
	}

	private void createHeaderPanel(Container contentPane) {
		headerPanel = GUIUtil.createPanel();
		GUIUtil.setLayout(headerPanel, new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		cmpinfoPanel = GUIUtil.createPanel();
		cmpnamelebel = GUIUtil.createLabel(GUIUtil.getHeaderTitle());
		GUIUtil.addComponents(cmpinfoPanel, cmpnamelebel);
		JLabel cmpimage = GUIUtil.createLabel(GUIUtil.getIcon());
		GUIUtil.addComponents(cmpinfoPanel, cmpimage);
		GUIUtil.addComponents(headerPanel, cmpinfoPanel);
		welcomeLabel = GUIUtil.createLabel("Welcome " + Business.getInstance().getUser());
		GUIUtil.addComponents(headerPanel, welcomeLabel);
		GUIUtil.addComponents(contentPane, headerPanel, BorderLayout.PAGE_START);
	}
	@Override
	public void open() throws Exception {
		frame = GUIUtil.createFrame("Course Contents");
		GUIUtil.setDefaultCloseOperation(frame, JFrame.DISPOSE_ON_CLOSE);
		linkPanel = new JPanel();
		addComponents(frame.getContentPane(), linkPanel);
		GUIUtil.setVisible(frame, true);
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
				Screen screen = new HomeScreen(); 
				screen.open(screen);
			}
		});
	}

}
