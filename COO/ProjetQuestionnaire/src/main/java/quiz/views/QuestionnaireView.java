package quiz.views;

import quiz.*;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

/**
 * A simple class to create a display a Questionnaire using the java.swing API
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 */
public class QuestionnaireView
{
    /* the button to valid and submit the questionnaire  */
    private JButton submitButton;

    /* the questionnaire */
    private Questionnaire questionnaire;

    /* the answer panel factory*/
    private AnswerPanelFactory answerPanelFactory;

    private List<QuestionView> theQuestions;
    /**
     *
     * @param questionnaire the questionnaire to be displayed
     */
    public QuestionnaireView(Questionnaire questionnaire, AnswerPanelFactory factory) {
        this.questionnaire      = questionnaire;
        this.answerPanelFactory = factory;
        submitButton            = new JButton("Submit");
        theQuestions 			= new ArrayList<QuestionView>();
    }

    public void display() {
        JFrame frame = new JFrame("Questionnaire");
        frame.addWindowListener(new CloseWindowEvent());
        frame.setLocation(100, 200);
        frame.setSize(600, 600);

        // add listener to submit button to display correct answers and points
        // submitButton.addActionListener(new SubmitActionListener());
        submitButton.addActionListener(new SubmitActionListenerTest());
        
        frame.add(submitButton, BorderLayout.SOUTH);

        /* questions content */
        Container questionContent = this.QuestionsPanel();
        questionContent.setLayout(new BoxLayout(questionContent, BoxLayout.Y_AXIS));
        frame.add(questionContent);

        frame.pack();
        frame.setVisible(true);
    }


    public Container QuestionsPanel() {
        Container content = new JPanel();
        QuestionView questionV;
        JPanel questionPanel;
        content.add(new JLabel("Answer the questions"));
        for (Question question: questionnaire.getTheQuestions()) {
        	questionV = new QuestionView(question, answerPanelFactory);
        	theQuestions.add(questionV);
            questionPanel = questionV.getPanel();
            content.add(questionPanel);
            
        }
        return content;
    }


    public void displayScore() {
      JOptionPane.showMessageDialog(null, "Points obtained : " + questionnaire.getPoints());
    //  int result = JOptionPane.showConfirmDialog(null, "un exemple de ConfirmDialog");
    //  System.out.println("result vaut :" + result);
    }

    public void displayCorrectAnswers() {
        Container content = new JPanel();
        content.add(new JLabel("Correct answers"));
    }

    /**
     * A class to handle the closing of the app when the window is closed
     */
    private class CloseWindowEvent extends WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent e) {
            System.exit(0);
        }
    }
    
    public void displayAnswerColor(Color color) {
    	AnswerPanel<?> c;
    	for (int i=0;i<theQuestions.size();i++) {
        	c = theQuestions.get(i).getAnswerPanel();
        	c.setBorder(color);
    	}
    }
    
    public void displayAnswerColor(Color[] theColors) {
    	AnswerPanel<?> c;
    	if (theColors.length == theQuestions.size()) {
    		for (int i=0;i<theQuestions.size();i++) {
        		c = theQuestions.get(i).getAnswerPanel();
                c.setBorder(theColors[i]);
        	}
    	}
    }
    
    public void displayWarning() {
        JOptionPane.showMessageDialog(null, "You must fill all the questions");
      }
    
    public class SubmitActionListenerTest implements ActionListener
    {
    	
        public void actionPerformed(ActionEvent e) {
            submitAction(e);
        }

        public void submitAction(ActionEvent event) {
        	AnswerPanel<?> c;
        	questionnaire.resetPoint();
        	questionnaire.getTheAnswer().clear();
        	for (QuestionView q : theQuestions) {
        		c = q.getAnswerPanel();
                if (c.getUserInput()=="") questionnaire.addTheAnswer(",");
        		else questionnaire.addTheAnswer(c.getUserInput());
        	}
        	questionnaire.askAll();
        	if (questionnaire.getVerif()==0) displayScore();
        	else displayWarning(); 
        	//displayCorrectAnswers();
        	displayAnswerColor(questionnaire.getTheColors());
        }
    }

}
