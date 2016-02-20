package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.util.GUIUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import in.co.thingsdata.lms.util.GUIUtil;

public class ProfileScreen {
	
		public static void main (String[] args) {
		
		ProfileScreen screen = new ProfileScreen(); // Comments to revert
	    SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				screen.go();
				
			}
		});
		
	}

		
		protected void go() {
			
			JFrame frame = new JFrame ("Profile");
			JPanel profilepanel = new JPanel();
			
			addComponents (frame.getContentPane(), profilepanel);
			
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			frame.setSize(800, 600);
			//frame.pack();
			
			frame.setVisible(true);
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					System.exit(0);
				}
			});
			
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
				JLabel welcomeLabel = new JLabel("Welcome " + getUser());
				headerPanel.add(welcomeLabel);
				contentPane.add(headerPanel, BorderLayout.PAGE_START);
				
				/*Adding panel for details*/
				JPanel detailpanel = new JPanel();
				detailpanel.setBorder(BorderFactory.createEtchedBorder());
				contentPane.add(detailpanel,BorderLayout.CENTER);
				
				/*Adding Panel for Profile details under detailpanel*/
				JPanel profiledtlpanel = new JPanel();
				detailpanel.setLayout(new BorderLayout());
				profiledtlpanel.setBorder(BorderFactory.createEtchedBorder());
				
				
				/*Adding imagepanel for Profile Image*/
				JPanel imagepanel = new JPanel();
				
				JLabel profileimage = new JLabel(new ImageIcon("C:/Users/roopesh.sharma/workspace/logo_6.png"));
				imagepanel.add(profileimage);
				
				
				detailpanel.add(profiledtlpanel,BorderLayout.CENTER);
				detailpanel.add(imagepanel, BorderLayout.EAST);
								
				

			}
		
		
		private String user;

		private String getUser() {
			return this.user;
		}

		public void setUser(String user) {
		
			this.user = user;
			
		}
		
}
