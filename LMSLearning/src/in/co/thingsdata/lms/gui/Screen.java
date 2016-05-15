package in.co.thingsdata.lms.gui;

import javax.swing.SwingUtilities;

public abstract class Screen {

	public void open(Screen screen) {
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

	protected abstract void open() throws Exception;
	
}
