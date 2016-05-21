package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.common.Business;

public class HomeScreen extends Screen {
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
	private JTable table;
	private Color color;
	private String user;

	public static void main(String[] args) {
		HomeScreen screen = new HomeScreen(); 
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

	@Override
	public void open() {
		frame = GUIUtil.createFrame("LMS HOME");
		linkPanel = GUIUtil.createPanel();
		addComponents(frame.getContentPane());
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				int row = table.rowAtPoint(event.getPoint());
				int col = table.columnAtPoint(event.getPoint());
				String goToPage = table.getValueAt(row, col).toString();
				try {
					Business.getInstance().setUser(getUser());
					Business.getInstance().goToRequestedPage(goToPage);
				} catch (Exception e) {
					e.printStackTrace();
				}
				frame.setVisible(false);
			}
		});
		GUIUtil.setVisible(frame, true);
	}

	private void addComponents(Container contentPane) {
		createHeader(contentPane);
		createLinks(contentPane);
		createBulletinBoard(contentPane);
		createDiscussionPanel(contentPane);
	}

	private void createDiscussionPanel(Container contentPane) {
		discusspanel = GUIUtil.createPanel();
		discusslabel = GUIUtil.createLabel("Discussion Forum");
		discusspanel.setBorder(BorderFactory.createEtchedBorder());
		GUIUtil.addComponents(discusspanel, discusslabel);
		GUIUtil.addComponents(centerpanel, discusspanel);
	}

	private void createBulletinBoard(Container contentPane) {
		centerpanel = GUIUtil.createPanel();
		centerpanel.setBorder(BorderFactory.createEtchedBorder());
		GUIUtil.setLayout(centerpanel, new BoxLayout(centerpanel, 1));
		GUIUtil.addComponents(contentPane, centerpanel, BorderLayout.CENTER);
		noticepanel = GUIUtil.createPanel();
		noticelabel = GUIUtil.createLabel("Bulletin Board");
		GUIUtil.addComponents(noticepanel, noticelabel);
		noticepanel.setBorder(BorderFactory.createEtchedBorder());
		GUIUtil.addComponents(centerpanel, noticepanel);
	}

	private void createLinks(Container contentPane) {
		TableColumnModel columnModel;
		MatteBorder border;
		String[] linksArray;
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
		table.setRowHeight(40);
		table.setBackground(UIManager.getColor(linkPanel));
		table.setFont(GUIUtil.getTableFont());
		GUIUtil.addComponents(linkPanel, table);
		linkPanel.setBorder(BorderFactory.createEtchedBorder());
		GUIUtil.addComponents(contentPane, linkPanel, BorderLayout.WEST);
	}

	private void createHeader(Container contentPane) {
		headerPanel = GUIUtil.createPanel();
		GUIUtil.setLayout(headerPanel, new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		cmpinfoPanel = GUIUtil.createPanel();
		cmpnamelebel = GUIUtil.createLabel(GUIUtil.getHeaderTitle());
		cmpimage = GUIUtil.createLabel(GUIUtil.getIcon());
		GUIUtil.addComponents(cmpinfoPanel, cmpnamelebel, cmpimage);
		GUIUtil.addComponents(headerPanel, cmpinfoPanel);
		String userId = getUser();
		if (null == userId || userId.isEmpty()) {
			userId = Business.getInstance().getUser();
		}
		welcomeLabel = GUIUtil.createLabel("Welcome " + userId);
		GUIUtil.addComponents(headerPanel, welcomeLabel);
		GUIUtil.addComponents(contentPane, headerPanel, BorderLayout.PAGE_START);
	}

	private String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}