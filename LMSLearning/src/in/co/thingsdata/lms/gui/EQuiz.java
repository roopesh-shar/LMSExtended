package in.co.thingsdata.lms.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import in.co.thingsdata.lms.util.GUIUtil;
import in.sg.rpc.common.domain.Feedback;
import in.sg.rpc.common.domain.QuizQuestion;

public class EQuiz {
	String[][] questionSet;
	String[][] questionAnswerSet;
	QuizQuestion[] quizQuestions;
	ButtonGroup bg;
	JFrame questionFrame;
	JFrame reportFrame;
    JPanel panel;
    JPanel panelresult;
	JRadioButton choice1;
    JRadioButton choice2;
    JRadioButton choice3;
    JRadioButton choice4;
    JLabel lblmess;
    private JButton btnext;
    int questionId;
    HashMap<Integer, String> map;
    JLabel[] lblQuestion;
    JLabel[] lblAnswer;
    JLabel[] lblUserAnswer;
    JPanel reportPanel;
    JLabel lblCountCorrectAnswer;

	public EQuiz() throws SQLException {
		loadAllQuestionsForUser();
		initializeData(quizQuestions);
		dislayQuestionFrame();
		btnext.addActionListener(new ActionListener() {
			@Override
			   public void actionPerformed(ActionEvent e){
		        
		        if(btnext.getText().equals("Next")){
		                    if(questionId<quizQuestions.length-1){
		                               	map.put(questionId,getSelection());
		                                questionId++;
		                                readqa(questionId);
		                                }
		                    else {
		                                map.put(questionId,getSelection());
		                                btnext.setText("Show answers");
		                               
		                             }
		                    }
		        else if(btnext.getText().equals("Show answers")){
		        	questionFrame.dispose();
		        	displayReport();
		        	
		        }
		                    
		       
		       
		}
		});
		
	}
	
public void loadAllQuestionsForUser() throws SQLException{
		quizQuestions =GUIUtil.getQuizQuestionfromDB(5);
	}
	
	public void displayQuestionForQuestionNumber(int questionNumber){
/*		set Question questionSet[questionNumber-1][0];
		set choiceA questionSet[questionNumber-1][1];
		set ChoiceB questionSet[questionNumber-1][2];
		set ChoiceC questionSet[questionNumber-1][3];
		Set ChoiceD  questionSet[questionNumber-1][4];	*/
	}
	
	
	
	
	public void dislayQuestionFrame(){
		questionFrame = new JFrame();
		questionFrame.setTitle("Quiz");
		questionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		questionFrame.setSize(430,350);
		questionFrame.setLocation(300,100);
		questionFrame.setResizable(false);
		Container cont=questionFrame.getContentPane();
        cont.setLayout(null);          
        cont.setBackground(Color.GRAY);
        bg=new ButtonGroup();     
        choice1=new JRadioButton("Choice1",false);
        choice2=new JRadioButton("Choice2",false);
        choice3=new JRadioButton("Choice3",false);
        choice4=new JRadioButton("Choice4",false);
        bg.add(choice1);
        bg.add(choice2);
        bg.add(choice3);
        bg.add(choice4);
        lblmess=new JLabel("Choose a correct anwswer");
        lblmess.setForeground(Color.BLUE);
        lblmess.setFont(new Font("Arial", Font.BOLD, 11));
        btnext=new JButton("Next");
        btnext.setForeground(Color.BLUE);                
        
        panel=new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLocation(10,10);
        panel.setSize(400,300);
        panel.setLayout(new GridLayout(6,2));
        panel.add(lblmess);
        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(choice4);
        panel.add(btnext);
        cont.add(panel);
        questionFrame.setVisible(true);
        int questionId=0;
        readqa(questionId);

		
	}
	
	 public void readqa(int questionId){
         lblmess.setText("  "+questionSet[questionId][0]);
         choice1.setText(questionSet[questionId][1]);
         choice1.setSelected(false);
         choice2.setText(questionSet[questionId][2]);
         choice2.setSelected(false);
         choice3.setText(questionSet[questionId][3]);
         choice3.setSelected(false);
         choice4.setText(questionSet[questionId][4]);
         choice4.setSelected(false);
        
         
	 }

	public void initializeData(QuizQuestion[] quizQuestions){
			questionSet = new String [quizQuestions.length][5];
			questionAnswerSet= new String [quizQuestions.length][2];
			for (int i = 0; i < quizQuestions.length; i++){
				questionSet[i][0] = quizQuestions[i].getQuestion();
				questionSet[i][1] = quizQuestions[i].getChoiceA();
				questionSet[i][2] = quizQuestions[i].getChoiceB();
				questionSet[i][3] = quizQuestions[i].getChoiceC();
				questionSet[i][4] = quizQuestions[i].getChoiceD();
				
				questionAnswerSet[i][0] = quizQuestions[i].getQuestion();
				questionAnswerSet[i][1] = quizQuestions[i].getCorrectAnswer();
				map=new HashMap<Integer, String>();
			}
				
				
			
	}
	
    public String getSelection(){
        String selectedChoice=null;
        Enumeration<AbstractButton> buttons=bg.getElements(); 
        while(buttons.hasMoreElements()) 
        { 
        JRadioButton temp=(JRadioButton)buttons.nextElement(); 
        if(temp.isSelected()) 
                    { 
                                selectedChoice=temp.getText();
                    } 
         }  
       // System.out.println("You have Selected"+selectedChoice);
        return(selectedChoice);
}
	
    public void reset(){
        questionId=0;
        map.clear();
        readqa(questionId);
        btnext.setText("Next");
    }

    public int calCorrectAnswer(){
        int count=0;
        for(int i=0; i<quizQuestions.length; i++)
                    if(questionAnswerSet[i][1].equals(map.get(i))) 
                    {
                    	count++;
                    }
             	
        return count;
}	
 
    public void displayReport()
    {
    	reportFrame = new JFrame();
    	reportFrame.setTitle("Answers");
    	reportFrame.setSize(850,550);
    	reportFrame.setBackground(Color.WHITE);
    	reportPanel = new JPanel();
    	
    	
    	lblQuestion = new JLabel[quizQuestions.length];
    	lblAnswer = new JLabel[quizQuestions.length];
    	lblUserAnswer = new JLabel[quizQuestions.length];
    	for (int i=0; i< quizQuestions.length;i++)
    	{
    		lblQuestion[i] = new JLabel("Question "+ (i+1)+": "+questionAnswerSet[i][0]);
    		lblAnswer[i] = new JLabel("Correct Answer : "+questionAnswerSet[i][1]);
    		lblUserAnswer[i]= new JLabel("Your Answer : "+map.get(i));
    		reportPanel.add(lblQuestion[i]);
    		reportPanel.add(Box.createRigidArea(new Dimension(0,10)));
    		reportPanel.add(lblAnswer[i]);
    		reportPanel.add(Box.createRigidArea(new Dimension(0,10)));
    		reportPanel.add(lblUserAnswer[i]);
    		reportPanel.add(Box.createRigidArea(new Dimension(0,10)));
    		
    	}
    	reportPanel.add(Box.createRigidArea(new Dimension(0,10)));
    	lblCountCorrectAnswer = new JLabel("Count of Correct Answer is :"+calCorrectAnswer());
    	reportPanel.add(lblCountCorrectAnswer);
    	reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
    	reportFrame.add(reportPanel, BorderLayout.CENTER);
    	reportFrame.setVisible(true);
    }
    
    
    public static void main(String[] args) throws SQLException {
		new EQuiz();

	}

}
