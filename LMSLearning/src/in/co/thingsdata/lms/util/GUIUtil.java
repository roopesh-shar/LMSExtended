package in.co.thingsdata.lms.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import in.sg.rpc.common.Business;
import in.sg.rpc.common.domain.FeeDetails;
import in.sg.rpc.common.domain.Feedback;
import in.sg.rpc.common.domain.User;
import in.sg.rpc.common.exception.UserLoginException;
import in.sg.rpc.server.service.DBService;

public class GUIUtil {

	private static final int SCREEN_WIDTH = 1380;
	private static final int SCREEN_HEIGHT = 740;

	public static void addComponents(Container container, Component... components) {
		for (Component comp : components) {
			container.add(comp);
		}
	}

	public static void refreshGUI(JFrame view) {
		view.getContentPane().invalidate();
		view.getContentPane().validate();
		GUIDomain.PARENT_VIEW = view;
	}

	public static String getHeaderTitle() {
		Business.getInstance().initProperties();
		return PropertiesReader.getInstance().getProperty("company.name");
	}

	public static ImageIcon getIcon() {
		Business.getInstance().initProperties();
		return new ImageIcon(PropertiesReader.getInstance().getProperty("icon.image.path"));
	}

	public static String[] getPageLinks() {
		Business.getInstance().initProperties();
		String contentlist = PropertiesReader.getInstance().getProperty("content.list");
		String[] linkcontent = contentlist.split(",");
		return linkcontent;
	}

	public static User getUserDetails(int userId) throws NumberFormatException, IOException, SQLException {
		return DBService.getInstance().getUserDetails(userId);
	}

	public static String getCourseDetailForUser(int userId) throws Exception {
		Business.getInstance().getCourseDetails(userId);
		return GUIDomain.REMOTE_RPC_SERVICE.getCourseDetailForUser(userId);
	}

	public static FeeDetails getFeeDetailsforUserid(int userId) throws Exception {
		return GUIDomain.REMOTE_RPC_SERVICE.getFeeDetailsforUserid(userId);
	}

	public static int getLoggedInUserId(String userName, String password) throws UserLoginException {
		return GUIDomain.REMOTE_RPC_SERVICE.login(userName, password);
	}
	public static void registernewUser(User user) throws Exception {
		GUIDomain.REMOTE_RPC_SERVICE.registerUser(user);
	}

	public static Feedback[] displayUserFeedback(int userId) throws SQLException {
		return GUIDomain.REMOTE_RPC_SERVICE.getUserFeedback(userId);
	}
	public static JFrame createFrame(String name) {
		JFrame frame = new JFrame(name);
		frame.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		return frame;
	}

	public static void setDefaultCloseOperation(JFrame frame, int disposeOnClose) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void setResizable(JFrame frame, boolean value) {
		frame.setResizable(true);
	}

	public static JPanel createPanel() {
		return new JPanel();
	}
	
	public static JMenuBar createMenuBar(){
		return new JMenuBar();
	}
	
	public static JMenu createMenu(String title){
		return new JMenu(title);
	}
	
	public static JMenuItem createJMenuItem(String title){
		return new JMenuItem(title);
	}
	


	public static void addPanel(JFrame frame, JPanel userDatailPanel) {
		frame.getContentPane().add(userDatailPanel);
	}
	
	public static JFrame createFileChooserDialog(String name){
		JFrame dialogFrame = new JFrame(name);
		JDialog dialog = new JDialog();
		JFileChooser chooseUploadFile = new JFileChooser();
		chooseUploadFile.setDialogType(JFileChooser.OPEN_DIALOG);
		dialogFrame.add(chooseUploadFile);
		dialogFrame.setSize(400, 300);
		//dialogFrame.setVisible(true);
		return dialogFrame;
	}
	
	public static String getSelectedFilePathfromJfile(JFrame frame){
		String filePath = null;
		
		
		return filePath;
	}

	public static void setSize(JFrame frame, int width, int height) {
		frame.setSize(new Dimension(width, height));
	}

	public static void setVisible(JFrame frame, boolean value) {
		frame.setVisible(value);
	}

	public static JLabel createLabel(String name) {
		JLabel label = new JLabel(name);
		return label;
	}

	public static JTextField createTextField(int columns) {
		JTextField textField = new JTextField(columns);
		return textField;
	}
	
	public static JTextField createTextField() {
		JTextField textField = new JTextField();
		return textField;
	}
	
	public static JComboBox<String> createComboBox(String[] list) {
		JComboBox<String> courses = new JComboBox<String>(list);
		return courses;
	}

	public static JPasswordField createPasswordField(int chars) {
		JPasswordField pwdField = new JPasswordField(chars);
		return pwdField;
	}

	public static JButton createButton(String buttonText) {
		JButton button = new JButton(buttonText);
		return button;
	}

	public static void setLayout(JPanel panel, LayoutManager layout) {
		panel.setLayout(layout);
	}

	public static JLabel createLabel(ImageIcon icon) {
		return new JLabel(icon);
	}
	
	public static JFileChooser createFileChooser(){
		return new JFileChooser();
	}

	public static void addComponents(Container contentPane, JPanel panel, String pageLocation) {
		contentPane.add(panel, pageLocation);
	}

	public static AbstractTableModel getDefaultTableModel() {
		DefaultTableModel model = new DefaultTableModel(); 
		return model;
	}

	public static JTable createTable(AbstractTableModel model) {
		JTable table = new JTable(model); 
		return table;
	}

	public static void populateTableRows(String[] linksArray, AbstractTableModel model) {
		for(String link : linksArray) {
        	((DefaultTableModel) model).addRow(new Object[]{link});
        }		
	}

	public static Font getTableFont() {
		return new JLabel().getFont();
	}

	public static JTextArea createTextArea(int rows, int columns) {
		return new JTextArea(rows,columns);
	}

	public static void setBackground(JTextArea txtAreacourseContent, Color color) {
		txtAreacourseContent.setBackground(color);		
	}

	public static void setEditable(JTextArea txtAreacourseContent, boolean value) {
		txtAreacourseContent.setEditable(value);
	}

	public static void setText(JTextArea txtAreacourseContent, String courseContent) {
		txtAreacourseContent.setText(courseContent);		
	}

	public static void setBorder(JPanel detailpanel, Border border) {
		detailpanel.setBorder(border);		
	}

	
	
	public static GridBagConstraints gridbagContraint() {
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		gBC.insets = new Insets(5, 5, 5, 5);
		gBC.gridwidth = 1;
		gBC.weightx = 1.0;
		gBC.gridx = 0;
		gBC.gridy = 0;
		return gBC;
	}

	public static File getFileStreamfromFilePath(String filePath) {
		return new File(filePath);
	}

}
