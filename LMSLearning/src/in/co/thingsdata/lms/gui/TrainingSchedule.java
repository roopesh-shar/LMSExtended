package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import in.co.thingsdata.lms.util.GUIUtil;


	public class TrainingSchedule {
	

		public void initializeTrainingSchedule() {
	        
			 JFrame frame = new JFrame("Training Schedules");
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    JPanel panelHeader = new JPanel();
								
				panelHeader.setLayout(new FlowLayout(SwingConstants.LEFT, 8, 8));
				
				JLabel labelHeaderName = new JLabel(GUIUtil.getHeaderTitle());
				
				JLabel labelHeaderImage = new JLabel(GUIUtil.getIcon());
				labelHeaderName.setPreferredSize(new Dimension(500, 30));
				labelHeaderImage.setPreferredSize(new Dimension(400, 100));
				labelHeaderName.setForeground(Color.blue);
				labelHeaderName.setFont(new Font("Serif", Font.BOLD, 20));
				
				GUIUtil.addComponents(panelHeader, labelHeaderName, labelHeaderImage);
				
				frame.getContentPane().add(panelHeader, BorderLayout.PAGE_START);
				
			    Object rowData[][] = { {" ", ""," "," "," "," "," "," "," "," " }
			    		};
			    Object columnNames[] = { "Course Name", "Topic","Instructor","Location","Date","Start Time","Duration","Download","Status" };
			    JTable table1 = new JTable(rowData, columnNames);

			    JScrollPane scrollPane = new JScrollPane(table1);
			    frame.add(scrollPane, BorderLayout.CENTER);
			    frame.setSize(1000, 400);
			    frame.setVisible(true);
			    

			  }
		
					
		
		
		  public static void main(String args[]) {
			  SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new TrainingSchedule().initializeTrainingSchedule();
						
					}
				});
			}
	}
		   