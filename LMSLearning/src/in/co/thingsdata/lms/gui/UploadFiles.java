package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class UploadFiles {

	private JFrame frame;
	private JPanel linkPanel;
	private JPanel headerPanel;
	private JPanel cmpinfoPanel;
	private JLabel cmpnamelebel;
	private JLabel welcomeLabel;
	private JButton goHomePageButton;
	private JPanel fileUploadPnl;
	private JButton printButton;
	private JTable table;
	private DefaultTableModel model;
	private Color color;
	private JFileChooser filechooser;
	private JPanel btnpnl;
	
	
	
	public static void main(String[] args) {
		UploadFiles uploadFiles = new UploadFiles(); // Comments to revert
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					uploadFiles.go();
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

		frame = new JFrame("File Upload Module");
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
			
			fileUploadPnl = new JPanel();
			contentPane.add(fileUploadPnl, BorderLayout.CENTER);
			fileUploadPnl.setBorder(new EtchedBorder());
			
			
			filechooser = new JFileChooser();
			fileUploadPnl.add(filechooser);
			
			
			
			btnpnl = new JPanel();
			btnpnl.setBorder(BorderFactory.createEtchedBorder());
			contentPane.add(btnpnl,BorderLayout.SOUTH);
			
			goHomePageButton = new JButton("Back");
			btnpnl.add(goHomePageButton);
			
			
		}
		
		
		private String user;

		private String getUser() {
			return this.user;
		}

		public void setUser(String user) {

			this.user = GUIDomain.CURRENT_USER_NAME;

		}
}
