/*package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.util.GUIDomain;
import in.co.thingsdata.lms.util.GUIUtil_bkp;
import in.sg.rpc.server.service.DBService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.charset.MalformedInputException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;



public class HomeScreen_bkp {

	
	private JPanel headerPanel;
	private JPanel cmpinfoPanel;
	private JPanel centerpanel;
	private JLabel cmpnamelebel;
	private JLabel cmpimage;
	private JLabel welcomeLabel;
	private JLabel noticelabel;
	private JPanel noticepanel;
	private DefaultTableModel model;
	private JPanel discusspanel;
	private JLabel discusslabel;
	private JScrollPane scrollpane;
	private JTable table;
	private Color color;

	
	
	
	
	public static void main (String[] args) {
	
		HomeScreen_bkp screen = new HomeScreen_bkp(); // Comments to revert
	    SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				screen.go();
				
			}
		});
		
	}

	public void go() {
		
		JFrame frame = new JFrame ("LMS HOME");
		JPanel linkPanel = new JPanel();
		
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
		
		table.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent e){
				int row=table.rowAtPoint(e.getPoint());
				int col= table.columnAtPoint(e.getPoint());
				String goToPage =table.getValueAt(row,col).toString();
				try {
					GUIUtil_bkp.goToRequestedPage(goToPage);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);

			}
			
			
		});		
	}

	private void addComponents(Container contentPane, JPanel linkPanel) {
		
	 Adding Header panel 
		headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		cmpinfoPanel = new JPanel();
		cmpnamelebel = new JLabel(GUIUtil_bkp.getHeaderTitle());
		cmpinfoPanel.add(cmpnamelebel);
		cmpimage = new JLabel(GUIUtil_bkp.getIcon());
		cmpinfoPanel.add(cmpimage);
		
		
		headerPanel.add(cmpinfoPanel);
		
		welcomeLabel = new JLabel("Welcome " + getUser());
		headerPanel.add(welcomeLabel);
		contentPane.add(headerPanel, BorderLayout.PAGE_START);
	
	 Adding Header Panel	
		model = new DefaultTableModel(); 
        table = new JTable(model); 
        
        model.addColumn("Col1"); 
        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(130);
        color = UIManager.getColor("Table.gridColor");
        MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
        table.setBorder(border);
        String[] linksArray;
        //String[] linksArray = new String[]{"Course Content", "Training Schedule","Profile","Evaluation","Certificate","Fee Receipt","Service Request","Softwares","Downloads","Uploads","Assignments","E-Quiz","FeedBack"};
        if(GUIDomain.CURRENT_USER_NAME.equalsIgnoreCase("admin")){
        	linksArray= GUIUtil_bkp.getContentList(1);	
        }
        else{
        	linksArray = GUIUtil_bkp.getContentList(2);
        }

        for(String link : linksArray) {
        	model.addRow(new Object[]{link});
        }
        
        table.setRowHeight(40);
        table.setBackground(UIManager.getColor(linkPanel));
        table.setFont(new JLabel().getFont());
        linkPanel.add(table);
        linkPanel.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(linkPanel, BorderLayout.WEST);
	
	 Adding Center Panel for Bulletin Board and 	
		centerpanel = new JPanel();
		centerpanel.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(centerpanel,BorderLayout.CENTER);
		centerpanel.setLayout(new BoxLayout(centerpanel, 1));
	Adding Bulletin Panel in Center panel	
		noticepanel = new JPanel();
		noticelabel = new JLabel("Bulletin Board");
		noticepanel.add(noticelabel);
		noticepanel.setBorder(BorderFactory.createEtchedBorder());
		
		centerpanel.add(noticepanel);
		
		
		
		Adding Discussion Panel in Center panel	
		discusspanel = new JPanel();
		discusslabel = new JLabel(" Discussion Forum");
		discusspanel.setBorder(BorderFactory.createEtchedBorder());
		discusspanel.add(discusslabel);
		centerpanel.add(discusspanel);
		
		

	}
	
	

	private String user;

	private String getUser() {
		return this.user;
	}

	public void setUser(String user) {
	
		this.user = user;
		
	}
	
}*/