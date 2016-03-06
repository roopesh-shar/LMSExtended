package in.co.thingsdata.lms.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class RegistrationDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		RegistrationDialog view = new RegistrationDialog();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				view.draw();

			}
		});
	}

	protected void draw() {
		
		JFrame parent = new JFrame();
	    JOptionPane optionPane = new JOptionPane("Congratulation, Your Registration is Successful!", JOptionPane.INFORMATION_MESSAGE);
	    JDialog dialog = optionPane.createDialog(parent, "Registration Creation");
	    dialog.setVisible(true);
		
	}	

}
