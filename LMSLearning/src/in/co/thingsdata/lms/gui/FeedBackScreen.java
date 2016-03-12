package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.client.RPCClient;
import in.sg.rpc.common.RPCService;
import in.sg.rpc.common.domain.FeeDetails;
import in.sg.rpc.common.domain.Feedback;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class FeedBackScreen {

	private JFrame frame;
	private JPanel linkPanel;
	private JPanel headerPanel;
	private JPanel cmpinfoPanel;
	private JLabel cmpnamelebel;
	private JLabel welcomeLabel;
	private JButton goHomePageButton;
	private JPanel btnpnl;
	private JButton printButton;
	private JTable table;
	private DefaultTableModel model;
	private Color color;
	private JTextArea displayFeedbackTxtArea;
	private JTextArea submitFeedbackTxtArea;
	private JPanel feedbackDisplayPanel;
	private JPanel feedbackSubmitPanel;
	private JButton submitFeedbackButton;
	private JLabel feedbackLabel;
	private String[] feedbackArea =  {"", "Course Feedback", "Trainer Feedback"}; 
	private String[] courses = {"", "Core Java", "J2EE", "Big Data And Hadoop", "Android", ".NET" };
	private JComboBox<String> courseList;
	private JComboBox<String> feedbackAreaList;
	
	public static void main(String[] args) {
		FeedBackScreen feedBack = new FeedBackScreen(); // Comments to revert
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					feedBack.go();
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
				contentPane.add(feedbackDisplayPanel,BorderLayout.CENTER);
				displayFeedbackTxtArea = new JTextArea();
				displayFeedbackTxtArea.setPreferredSize(new Dimension(600, 400));
				displayFeedbackTxtArea.setEditable(false);
				displayFeedbackTxtArea.setBackground(UIManager.getColor(feedbackDisplayPanel));
				displayFeedbackTxtArea.setBorder(BorderFactory.createEtchedBorder());
				feedbackDisplayPanel.add(displayFeedbackTxtArea);
				
				
				feedbackSubmitPanel = new JPanel();
				feedbackSubmitPanel.setBorder(BorderFactory.createEtchedBorder());
				contentPane.add(feedbackSubmitPanel,BorderLayout.SOUTH);
				
				btnpnl = new JPanel();
				btnpnl.setBorder(BorderFactory.createEtchedBorder());
				contentPane.add(btnpnl,BorderLayout.WEST);
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
				feedbackSubmitPanel.setPreferredSize(new Dimension(600,100));
				RPCClient client = new RPCClient();
				RPCService stub = null;
				stub =client.getRemoteService();
				GUIDomain.REMOTE_RPC_SERVICE = stub;
				
				Feedback[] feedbackMap =GUIUtil.displayUserFeedback(1);
/*				for(Entry<String, String> m:feedbackMap.entrySet()){  
					   System.out.println(m.getKey()+" "+m.getValue());  
					  } */ 
/*				Iterator<String> itr = feedbackMap.iterator() ;
				while(itr.hasNext()){
					System.out.println(itr.next());
				}*/
				System.out.println(feedbackMap[0].getFeedback());
				System.out.println(feedbackMap[1].getFeedback());
				

				
				
				
				
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
	
	
		

