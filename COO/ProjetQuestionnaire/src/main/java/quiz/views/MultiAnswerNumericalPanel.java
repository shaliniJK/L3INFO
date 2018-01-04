package quiz.views;

import quiz.answers.MultiAnswer;

import java.awt.Dimension;

import javax.swing.*;

public class MultiAnswerNumericalPanel extends AnswerPanel<JSpinner>
{
    private static final long serialVersionUID = 1L;
   
    public MultiAnswerNumericalPanel(MultiAnswer<?> multiAnswer) {
        super(multiAnswer);
        JSpinner answer;
        int nb = multiAnswer.getTheAnswers().size();
        for (int i=0;i<nb;i++) {
        	answer = new JSpinner();
        	answer.setPreferredSize(new Dimension(70, 30));
        	panel.add(answer);
        	resultat.add(answer);
        }
    }
    
    @Override
    public String getUserInput() {
    	String textOutput = "";
    	for (JSpinner js : resultat) {
    		textOutput = textOutput + js.getValue() + ',';
    	}
    	return textOutput;
    }
}