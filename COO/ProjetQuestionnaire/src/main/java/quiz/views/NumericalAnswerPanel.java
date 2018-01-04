package quiz.views;

import quiz.answers.NumericalAnswer;

import java.awt.Dimension;

import javax.swing.*;

public class NumericalAnswerPanel extends AnswerPanel <JSpinner>
{
    private static final long serialVersionUID = 1L;

    public NumericalAnswerPanel(NumericalAnswer numericalAnswer) {
        super(numericalAnswer);
        JSpinner answer = new JSpinner();
        answer.setPreferredSize(new Dimension(70, 30));
        panel.add(answer);
        resultat.add(answer);
    }
    
    
    @Override
    public String getUserInput() {
    	return resultat.get(0).getValue()+"";
    }
}
