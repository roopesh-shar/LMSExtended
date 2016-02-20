package in.co.thingsdata.lms.gui;

import in.co.thingsdata.lms.util.GUIUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.sun.prism.Image;

public class HomeScreen {

	
	public static void main (String[] args) {
	
		HomeScreen screen = new HomeScreen(); // Comments to revert
	    SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				screen.go();
				
			}
		});
		
	}

	protected void go() {
		
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
	
	/* Adding Header Panel*/	
		DefaultTableModel model = new DefaultTableModel(); 
        JTable table = new JTable(model); 
        model.addColumn("Col1"); 
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(130);
        Color color = UIManager.getColor("Table.gridColor");
        MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
        table.setBorder(border);
        
        //String[] linksArray = new String[]{"Course Content", "Training Schedule","Profile","Evaluation","Certificate","Fee Receipt","Service Request","Softwares","Downloads","Uploads","Assignments","E-Quiz","FeedBack"};
        String[] linksArray = GUIUtil.getContentList();
        for(String link : linksArray) {
        	model.addRow(new Object[]{link});
        }
        table.setRowHeight(40);
        table.setBackground(UIManager.getColor(linkPanel));
        table.setFont(new JLabel().getFont());
        linkPanel.add(table);
        linkPanel.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(linkPanel, BorderLayout.WEST);
	
	/* Adding Center Panel for Bulletin Board and */	
		JPanel centerpanel = new JPanel();
		centerpanel.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(centerpanel,BorderLayout.CENTER);
		centerpanel.setLayout(new BoxLayout(centerpanel, 1));
	/*Adding Bulletin Panel in Center panel*/	
		JPanel noticepanel = new JPanel();
		JLabel noticelabel = new JLabel("Bulletin Board");
		noticepanel.add(noticelabel);
		noticepanel.setBorder(BorderFactory.createEtchedBorder());
		
		centerpanel.add(noticepanel);
		
		/*Adding Discussion Panel in Center panel*/	
		JPanel discusspanel = new JPanel();
		JLabel discusslabel = new JLabel(" Discussion Forum");
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
	
}