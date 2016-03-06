package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import in.co.thingsdata.lms.util.GUIUtil;

public class Certificate {
	public void initializeCertificate() {
        
		 JFrame frame = new JFrame("Certificates");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    JPanel panelHeader = new JPanel();
		    panelHeader.setLayout(new FlowLayout(SwingConstants.LEFT, 8,8) );
		    
		    JLabel labelHeaderName = new JLabel(GUIUtil.getHeaderTitle());
		    JLabel labelHeaderImage = new JLabel(GUIUtil.getIcon());
		    
		    labelHeaderName.setPreferredSize(new Dimension(500,30));
		    labelHeaderImage.setPreferredSize(new Dimension(400, 100));
		    labelHeaderName.setForeground(Color.blue);
		    labelHeaderName.setFont(new Font("Serif", Font.BOLD, 20));
		    
		    GUIUtil.addComponents(panelHeader, labelHeaderName, labelHeaderImage);
		    
		    frame.getContentPane().add(panelHeader, BorderLayout.PAGE_START) ;
		    
		   // Object RowData
		    frame.setSize(1200, 500);
		    frame.setVisible(true);
		    
		    
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Certificate().initializeCertificate();
			}
		});
	}
	
}
