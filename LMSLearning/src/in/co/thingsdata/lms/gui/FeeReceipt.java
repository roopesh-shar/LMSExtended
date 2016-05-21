package in.co.thingsdata.lms.gui;

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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.common.Business;
import in.sg.rpc.common.domain.FeeDetails;

public class FeeReceipt extends Screen {

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
	
	

	public static void main(String[] args) {

		FeeReceipt feeReciept = new FeeReceipt(); // Comments to revert
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					feeReciept.open();
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
		
		JPanel feeReceiptpnl = new JPanel();
		feeReceiptpnl.setBorder(BorderFactory.createEtchedBorder());
		
		contentPane.add(feeReceiptpnl,BorderLayout.CENTER);
		
		
		btnpnl = new JPanel();
		btnpnl.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(btnpnl,BorderLayout.SOUTH);
		
		printButton = new JButton("print");
		goHomePageButton = new JButton("Back");
		
		btnpnl.add(printButton);
		btnpnl.add(goHomePageButton);
		
		/*Adding JTable for Receipt*/
		model = new DefaultTableModel(); 
		table = new JTable(model); 

		model.addColumn("");
		model.addColumn(""); 	
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(200);
		color = UIManager.getColor("Table.gridColor");
		MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
		table.setBorder(border);
		
		String[] linksArray = {"Name", "Course Name","Course Fees","Fees Submitted", "Fees pending"};
        for(String link : linksArray) {
        	model.addRow(new Object[]{link});
        }
        
        table.setRowHeight(50);
        table.setBackground(UIManager.getColor(linkPanel));
        table.setFont(new JLabel().getFont());
		feeReceiptpnl.add(table);
		
		
		FeeDetails feeDetails = Business.getFeeDetailsforUserid(12345);
		table.setValueAt((Object)GUIDomain.CURRENT_USER_NAME, 0, 1);
		table.setValueAt((Object)String.valueOf(feeDetails.getCourseName()), 1, 1);
		table.setValueAt((Object)String.valueOf(feeDetails.getCourseFee()).concat(" INR"), 2, 1);
		table.setValueAt((Object)String.valueOf(feeDetails.getPaidFees()).concat(" INR"), 3, 1);
		table.setValueAt((Object)String.valueOf(feeDetails.getRemainingFees()).concat(" INR"), 4, 1);
		
	}

	private String user;

	private String getUser() {
		return this.user;
	}

	public void setUser(String user) {

		this.user = GUIDomain.CURRENT_USER_NAME;

	}

}
