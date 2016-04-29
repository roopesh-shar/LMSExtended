package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import in.co.thingsdata.lms.util.GUIUtil;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.FontUIResource;

public class UploadFileContent {

	public JPanel uploadContent;
	public JPanel fileChooserPanel;
	public JTextField uploadFileName;
	public JLabel selectFile;
	public JButton browseFileButton;
	public JButton uploadFileButton;
	public JComboBox<String> courseList; 
	public String[] courses = {"", "Core Java", "J2EE", "Big Data And Hadoop", "Android", ".NET" };
	public JFileChooser chooseUploadFile;
	public JFrame testFrame;
	public JDialog dialog;
	
	public JPanel createPanelforUplaod(String uploadItem){
		uploadContent = GUIUtil.createPanel();
		uploadFileName = GUIUtil.createTextField(50);
		selectFile = GUIUtil.createLabel("Select File to Uplaod");
		courseList = GUIUtil.createComboBox(courses);
		browseFileButton = GUIUtil.createButton("Browse");
		uploadFileButton = GUIUtil.createButton("Upload");
		GUIUtil.addComponents(uploadContent, selectFile);
		GUIUtil.addComponents(uploadContent, uploadFileName);
		GUIUtil.addComponents(uploadContent, courseList);
		GUIUtil.addComponents(uploadContent, browseFileButton);
		GUIUtil.addComponents(uploadContent, uploadFileButton);
		browseFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame dialogFrame = new JFrame("Dialog");
				JDialog dialog = new JDialog();
				JFileChooser chooseUploadFile = new JFileChooser();
				chooseUploadFile.setDialogType(JFileChooser.OPEN_DIALOG);
				dialogFrame.add(chooseUploadFile);
				dialogFrame.setSize(400, 300);
				dialogFrame.setVisible(true);
								
				chooseUploadFile.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())) {
					          dialogFrame.setVisible(false);
					          uploadFileName.setText(chooseUploadFile.getSelectedFile().getAbsolutePath());
					        } else if (JFileChooser.CANCEL_SELECTION.equals(e.getActionCommand())) {
					          dialogFrame.setVisible(false);
					        }
						
					}
				});
				
				
				
			}
		});
		
		return uploadContent;
	}
	
	
	public void initiallizeScreen(){
		UploadFileContent inst = new UploadFileContent();
		testFrame = GUIUtil.createFrame("Upload Test Frame");
		GUIUtil.setDefaultCloseOperation(testFrame, JFrame.DISPOSE_ON_CLOSE);
		GUIUtil.setResizable(testFrame, true);
		GUIUtil.addPanel(testFrame, inst.createPanelforUplaod("course"));
		//fileChooserPanel = inst.createFileChooserPanel();
		//fileChooserPanel.setVisible(false);
		//testFrame.add(fileChooserPanel, BorderLayout.SOUTH);
		GUIUtil.setVisible(testFrame, true);
	}
	
	
	
	
public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new UploadFileContent().initiallizeScreen();
				
			}
		});
	}
	
}



