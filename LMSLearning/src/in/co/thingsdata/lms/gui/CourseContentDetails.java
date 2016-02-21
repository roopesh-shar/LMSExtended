package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.util.GUIUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CourseContentDetails {

	public static void main(String[] args) {

		CourseContentDetails courseContent = new CourseContentDetails(); // Comments to revert
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				courseContent.go();

			}
		});
	}

	protected void go() {

		JFrame frame = new JFrame("Course Contents");
		JPanel linkPanel = new JPanel();

		addComponents(frame.getContentPane(), linkPanel);

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

	private void addComponents(Container contentPane, JPanel linkPanel) {

		/* Adding Header panel */
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		JPanel cmpinfoPanel = new JPanel();
		JLabel cmpnamelebel = new JLabel(GUIUtil.getHeaderTitle());
		cmpinfoPanel.add(cmpnamelebel);
		JLabel cmpimage = new JLabel(GUIUtil.getIcon());
		cmpinfoPanel.add(cmpimage);

		headerPanel.add(cmpinfoPanel);

		JLabel welcomeLabel = new JLabel("Welcome " + getUser());
		headerPanel.add(welcomeLabel);
		contentPane.add(headerPanel, BorderLayout.PAGE_START);

		
	}

	private String user;

	private String getUser() {
		return this.user;
	}

	public void setUser(String user) {

		this.user = user;

	}

}
