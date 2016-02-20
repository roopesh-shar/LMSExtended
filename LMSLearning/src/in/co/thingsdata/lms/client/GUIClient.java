package in.co.thingsdata.lms.client;

import javax.swing.SwingUtilities;

import in.co.thingsdata.lms.gui.View;

public class GUIClient {
	
public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				View view = new View();
				view.setView(view);
				view.go();
				
			}
		});
	}

}
