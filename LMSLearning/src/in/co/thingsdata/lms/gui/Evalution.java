package in.co.thingsdata.lms.gui;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import in.co.thingsdata.lms.util.GUIUtil;

public class Evalution {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Evalution screen = new Evalution(); // Comments to revert
	    SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				screen.go();
				
			}
		});
	}
protected void go() {
		
		JFrame frame = new JFrame ("Evalution");
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
		JLabel cmpnamelabel = new JLabel(GUIUtil.getHeaderTitle());
		cmpinfoPanel.add(cmpnamelabel);
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
     
	/* Adding Center Panel for Trainer Evalution */	
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
	/*Adding Trainer Evalution in Center panel*/	
		JPanel trainerpanel = new JPanel();
		JLabel noticelabel = new JLabel("Trainer Evalution");
		trainerpanel.add(noticelabel);
		trainerpanel.setBorder(BorderFactory.createEtchedBorder());
		centerpanel.add(trainerpanel);
		
		/*Adding Class Panel in Center panel*/	
		JPanel classspanel = new JPanel();
		JLabel classslabel = new JLabel(" Classs Evalution");
		classspanel.setBorder(BorderFactory.createEtchedBorder());
		classspanel.add(classslabel);
		centerpanel.add(classspanel);
		
		

	}

	private String user;

	private String getUser() {
		return this.user;
	}

	public void setUser(String user) {
	
		this.user = user;
		
	}
	
}

