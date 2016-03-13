package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.util.GUIUtil;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;



public class HomeScreen {

	private JFrame frame;
	private JPanel linkPanel;
	private JPanel headerPanel;
	private JPanel cmpinfoPanel;
	private JPanel centerpanel;
	private JLabel cmpnamelebel;
	private JLabel cmpimage;
	private JLabel welcomeLabel;
	private JLabel noticelabel;
	private JPanel noticepanel;
	private AbstractTableModel model;
	private JPanel discusspanel;
	private JLabel discusslabel;
	private JScrollPane scrollpane;
	private JTable table;
	private Color color;
	private String user;
	
	public static void main (String[] args) {
		HomeScreen screen = new HomeScreen(); // Comments to revert
	    SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				screen.go();
			}
		});
	}

	public void go() {
		frame = GUIUtil.createFrame("LMS HOME");
		linkPanel = GUIUtil.createPanel();
		addComponents (frame.getContentPane(), linkPanel);
		GUIUtil.setDefaultCloseOperation(frame, JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		table.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent event){
				int row=table.rowAtPoint(event.getPoint());
				int col= table.columnAtPoint(event.getPoint());
				String goToPage =table.getValueAt(row,col).toString();
				GUIUtil.goToRequestedPage(goToPage);
				frame.setVisible(false);
			}
		});		
		GUIUtil.setVisible(frame, true);
	}

	private void addComponents(Container contentPane, JPanel linkPanel) {
		 TableColumnModel columnModel;
		 MatteBorder border;
		 String[] linksArray;
	/* Adding Header panel */
		headerPanel = GUIUtil.createPanel();
		GUIUtil.setLayout(headerPanel, new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		cmpinfoPanel = GUIUtil.createPanel();
		cmpnamelebel = GUIUtil.createLabel(GUIUtil.getHeaderTitle());
		//cmpinfoPanel.add(cmpnamelebel);
		cmpimage = GUIUtil.createLabel(GUIUtil.getIcon());
	//	cmpinfoPanel.add(cmpimage);
		GUIUtil.addComponents(cmpinfoPanel, cmpnamelebel,cmpimage);
		GUIUtil.addComponents(headerPanel, cmpinfoPanel);
		
	//	headerPanel.add(cmpinfoPanel);
		
		welcomeLabel = GUIUtil.createLabel("Welcome " + getUser());
		GUIUtil.addComponents(headerPanel, welcomeLabel);
		//headerPanel.add(welcomeLabel);
	//	contentPane.add(headerPanel, BorderLayout.PAGE_START);
		GUIUtil.addComponents(contentPane, headerPanel, BorderLayout.PAGE_START);
	
	/* Adding Header Panel*/	
		model = GUIUtil.getDefaultTableModel(); 
        table = GUIUtil.createTable(model); 
        ((DefaultTableModel) model).addColumn("Col1"); 
        columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(130);
        color = UIManager.getColor("Table.gridColor");
        border = new MatteBorder(1, 1, 0, 0, color);
        table.setBorder(border);
        linksArray = GUIUtil.getPageLinks();
        GUIUtil.populateTableRows(linksArray, model);
        /*for(String link : linksArray) {
        	((DefaultTableModel) model).addRow(new Object[]{link});
        }*/
        table.setRowHeight(40);
        table.setBackground(UIManager.getColor(linkPanel));
        table.setFont(GUIUtil.getTableFont());
        GUIUtil.addComponents(linkPanel, table);
    //    linkPanel.add(table);
        linkPanel.setBorder(BorderFactory.createEtchedBorder());
		//contentPane.add(linkPanel, BorderLayout.WEST);
		GUIUtil.addComponents(contentPane, linkPanel, BorderLayout.WEST);
	/* Adding Center Panel for Bulletin Board and */	
		centerpanel = GUIUtil.createPanel();
		centerpanel.setBorder(BorderFactory.createEtchedBorder());
		//contentPane.add(centerpanel,BorderLayout.CENTER);
		GUIUtil.setLayout(centerpanel, new BoxLayout(centerpanel, 1));
		GUIUtil.addComponents(contentPane, centerpanel, BorderLayout.CENTER);
		//centerpanel.setLayout(new BoxLayout(centerpanel, 1));
	/*Adding Bulletin Panel in Center panel*/	
		noticepanel = GUIUtil.createPanel();
		noticelabel = GUIUtil.createLabel("Bulletin Board");
		//noticepanel.add(noticelabel);
		GUIUtil.addComponents(noticepanel, noticelabel);
		noticepanel.setBorder(BorderFactory.createEtchedBorder());
		//centerpanel.add(noticepanel);
		GUIUtil.addComponents(centerpanel,noticepanel);
		/*Adding Discussion Panel in Center panel*/	
		discusspanel = GUIUtil.createPanel();
		discusslabel = GUIUtil.createLabel("Discussion Forum");
		discusspanel.setBorder(BorderFactory.createEtchedBorder());
		GUIUtil.addComponents(discusspanel, discusslabel);
		//discusspanel.add(discusslabel);
		//centerpanel.add(discusspanel);
		GUIUtil.addComponents(centerpanel, discusspanel);
	}
	private String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}