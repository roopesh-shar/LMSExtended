package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;



import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.common.Business;

public class AdminUploadModule extends Screen {

	public static JFrame frame  = GUIUtil.createFrame("Admin Management");;
	
	private JButton goHomePageButton;
	
	private JMenuBar mainMenu;
	private JMenu adminMenu;
	private JMenuItem adminFeeUploadMenu;
	private JMenuItem adminCourseUploadMenu;
	private JMenuItem adminCertificateUploadMenu;
	private JMenuItem adminFeedbackApprovalUploadMenu;
	private JMenuItem adminScheduleUploadMenu;
	private JMenuItem adminAssignmentUploadMenu;
	private JMenuItem adminUserCreationMenu;
	private static JPanel feeUploadPanel;
	private JPanel courseUploadPanel;
	private JFileChooser feeFileChooser;
	private JPanel uploadPanel = new JPanel();
	//private JMenuItem[] adminMenuItems = {adminFeeUploadMenu,adminCourseUploadMenu, adminCertificateUploadMenu,adminFeedbackApprovalUploadMenu,adminScheduleUploadMenu,adminAssignmentUploadMenu,adminUserCreationMenu };   

	public static void main(String[] args) {
		AdminUploadModule courseContent = new AdminUploadModule(); 
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					courseContent.open();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	private JMenuBar createMenuForAdminUpload(Container contentPane) {
		mainMenu = GUIUtil.createMenuBar();
		adminMenu = GUIUtil.createMenu("Admin Upload Menu");
		adminCourseUploadMenu = GUIUtil.createJMenuItem("Course Uplaod");
		adminFeeUploadMenu =GUIUtil.createJMenuItem("Fees Upload");
		adminCertificateUploadMenu =GUIUtil.createJMenuItem("Certificate Uplaod");
		adminFeedbackApprovalUploadMenu =GUIUtil.createJMenuItem("Feedback Approval");
		adminScheduleUploadMenu = GUIUtil.createJMenuItem("Schedule Uplaod");
		adminAssignmentUploadMenu = GUIUtil.createJMenuItem("Assignment Uplaod");
		adminUserCreationMenu = GUIUtil.createJMenuItem("User Creation");
		adminMenu.add(adminCourseUploadMenu);
		adminMenu.add(adminFeeUploadMenu);
		adminMenu.add(adminCertificateUploadMenu);
		adminMenu.add(adminFeedbackApprovalUploadMenu);
		adminMenu.add(adminScheduleUploadMenu);
		adminMenu.add(adminAssignmentUploadMenu);
		adminMenu.add(adminUserCreationMenu);
		//menuPanel.add(mainMenu);
		mainMenu.add(adminMenu);
		writeActionsOnFileMenuItems(adminFeeUploadMenu, frame);
		writeActionsOnFileMenuItems(adminCourseUploadMenu, frame);
		writeActionsOnFileMenuItems(adminCertificateUploadMenu, frame);
		writeActionsOnFileMenuItems(adminFeedbackApprovalUploadMenu, frame);
		writeActionsOnFileMenuItems(adminScheduleUploadMenu, frame);
		writeActionsOnFileMenuItems(adminAssignmentUploadMenu, frame);
		writeActionsOnFileMenuItems(adminUserCreationMenu, frame);
		
		
		return mainMenu;
		
		
	}
	
	public  void writeActionsOnFileMenuItems(JMenuItem menuItem, JFrame frame){
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("seleted"+ menuItem.getText());
				frame.remove(uploadPanel);
				//frame.invalidate();
				uploadPanel = openModuleBasedOnSelection(menuItem.getText());
				frame.add(uploadPanel, BorderLayout.CENTER);
				frame.revalidate();
				//frame.pack();
				
				//openModuleBasedOnSelection(menuItem.getText());
				
			}
		});
		
	}
	
/*	private JPanel createFeeUploadPanel(Container contentPane){
		feeUploadPanel = GUIUtil.createPanel();
		GUIUtil.addComponents(contentPane, feeUploadPanel, BorderLayout.CENTER);
		return feeUploadPanel;
	}*/
	
	public JPanel openModuleBasedOnSelection(String moduleName)
	{
		
			UploadFileContent getPanel = new UploadFileContent();
			feeUploadPanel = getPanel.createPanelforUplaod(moduleName);
			
		
		return feeUploadPanel;
	}
	
	
	
	@Override
	public void open() throws Exception {
		
		frame.setSize(600, 400);
		GUIUtil.setDefaultCloseOperation(frame, JFrame.DISPOSE_ON_CLOSE);
		AdminUploadModule upload = new AdminUploadModule();
		frame.setJMenuBar(upload.createMenuForAdminUpload(frame));
		GUIUtil.setVisible(frame, true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		/*goHomePageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.setVisible(false);
				Screen screen = new HomeScreen(); 
				screen.open(screen);
			}
		});*/
		
	}




}
