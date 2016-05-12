package in.co.thingsdata.lms.gui;


import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Software extends Screen {

	private JFrame frame;
	private JPanel linkPanel;
	private JPanel headerPanel;
	private JPanel cmpinfoPanel;
	private JLabel cmpnamelabel;
	private JLabel welcomeLabel;
	private JButton goHomePageButton;
	private JPanel btnpnl;
	private JButton okButton;
	private JTable table;
	private DefaultTableModel model;
	private Color color;
	
	public static void main(String[] args) {
		Software cer = new Software(); // Comments to revert
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					cer.open(cer);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}
	
		protected void open() throws Exception {



			frame = new JFrame("Softwares");
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
						screen.open(screen);
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
				cmpnamelabel = new JLabel(GUIUtil.getHeaderTitle());
				cmpinfoPanel.add(cmpnamelabel);
				
				JLabel cmpimage = new JLabel(GUIUtil.getIcon());
				cmpinfoPanel.add(cmpimage);
				cmpnamelabel.setForeground(Color.blue);
				cmpnamelabel.setFont(new Font("Serif", Font.BOLD, 20));
				headerPanel.add(cmpinfoPanel);

				welcomeLabel = new JLabel("Welcome " + getUser());
				headerPanel.add(welcomeLabel);
				contentPane.add(headerPanel, BorderLayout.PAGE_START);
				
				JPanel certificatePanel = new JPanel();
				certificatePanel.setBorder(BorderFactory.createEtchedBorder());
				
				contentPane.add(certificatePanel,BorderLayout.CENTER);
				
				
				btnpnl = new JPanel();
				btnpnl.setBorder(BorderFactory.createEtchedBorder());
				contentPane.add(btnpnl,BorderLayout.SOUTH);
				
				okButton = new JButton("OK");
				goHomePageButton = new JButton("Back");
				
				btnpnl.add(okButton);
				btnpnl.add(goHomePageButton);
				
				
				
				Object rowData[][] = { {" ", ""," "," "," "," "," "," "," "," " }
	    		};
	    Object columnNames[] = { "Software Name","Download","Remarks" };
	    JTable table1 = new JTable(rowData, columnNames);

	    JScrollPane scrollPane = new JScrollPane(table1);
	    frame.add(scrollPane, BorderLayout.CENTER);
				
				
			}

			private String user;

			private String getUser() {
				return this.user;
			}

			public void setUser(String user) {

				this.user = GUIDomain.CURRENT_USER_NAME;

			}



	
	
	}
	
	
		


