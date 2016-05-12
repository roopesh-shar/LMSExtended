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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import in.co.thingsdata.lms.util.GUIUtil;

public class Evaluation extends Screen {
	
	private JFrame frame;
	private JPanel linkPanel;
	private JPanel headerPanel;
	private JPanel cmpinfoPanel;
	private JLabel cmpnamelabel;
	private JLabel cmpimage;
	private JLabel welcomeLabel;
	private JButton goHomePageButton;
	private JPanel btnpnl;
	private JButton printButton;
	private JTable table;
	private DefaultTableModel model;
	private Color color;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Evaluation eval = new Evaluation(); // Comments to revert
	    SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try{
					eval.open(eval);
				}
				catch (Exception e){
				e.printStackTrace();
			}
			}
		});
	}
	private void initializeEvaluationScreen() {

		
	frame = new JFrame ("Evaluation");
	linkPanel = new JPanel();
		
		addComponents (frame.getContentPane(), linkPanel);
		
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


	private void addComponents(Container contentPane, JPanel linkPanel) {
		
//	 Adding Header panel 
		headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		cmpinfoPanel = new JPanel();
	     cmpnamelabel = new JLabel(GUIUtil.getHeaderTitle());
		cmpinfoPanel.add(cmpnamelabel);
		 cmpimage = new JLabel(GUIUtil.getIcon());
		cmpinfoPanel.add(cmpimage);
		headerPanel.add(cmpinfoPanel);
		
		 welcomeLabel = new JLabel("Welcome " + getUser());
		headerPanel.add(welcomeLabel);
		contentPane.add(headerPanel, BorderLayout.PAGE_START);
	
	// Adding Header Panel	
		DefaultTableModel model = new DefaultTableModel(); 
         table = new JTable(model); 
        model.addColumn("Col1"); 
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(130);
        Color color = UIManager.getColor("Table.gridColor");
        MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
        table.setBorder(border);
     
	// Adding Center Panel for Trainer Evalution 	
		JPanel centerpanel = new JPanel();
		centerpanel.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(centerpanel,BorderLayout.CENTER);
		centerpanel.setLayout(new BoxLayout(centerpanel, 1));
		Object rowData[][] = { {" ", ""," "," "," "," "," "," "," "," " }
		};
Object columnNames[] = { "Course Name", "Topic","Instructor","Location","Date","Start Time","Duration","Download","Status" };
JTable table1 = new JTable(rowData, columnNames);

JScrollPane scrollPane = new JScrollPane(table1);
//frame.add(scrollPane, BorderLayout.CENTER);
//	Adding Trainer Evalution in Center panel	
		JPanel trainerpanel = new JPanel();
		JLabel noticelabel = new JLabel("Trainer Evalution");
		trainerpanel.add(noticelabel);
		trainerpanel.setBorder(BorderFactory.createEtchedBorder());
		centerpanel.add(trainerpanel);
		
	//	Adding Class Panel in Center panel	
		JPanel classspanel = new JPanel();
		JLabel classslabel = new JLabel(" Classs Evalution");
		classspanel.setBorder(BorderFactory.createEtchedBorder());
		classspanel.add(classslabel);
		centerpanel.add(classspanel);
		
		
		JPanel btnpnl = new JPanel();
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
	
		this.user = user;
		
	}
	@Override
	protected void open() throws Exception {
		initializeEvaluationScreen();
		
	}

	
}

