package quiz.views;

import java.awt.Color;
import java.util.List;

import javax.swing.JCheckBox;

import quiz.answers.Answer;
import quiz.answers.MultiChoiceAnswer;

public class MultiChoiceAnswerPanel extends AnswerPanel<JCheckBox> {
	private static final long serialVersionUID = 1L;

	public MultiChoiceAnswerPanel(Answer<?> answer) {
		super(answer);
        JCheckBox theCheckBox;
        MultiChoiceAnswer<?> multiAnswer = (MultiChoiceAnswer<?>) answer;
        List<String> theAnswerProposes = multiAnswer.getAnswerPropose();
        for (String anAnswer : theAnswerProposes) {
        	theCheckBox = new JCheckBox(anAnswer,false);
        	panel.add(theCheckBox);
        	resultat.add(theCheckBox);
        }
	}
	
	@Override
    public String getUserInput() {
    	String textOutput = "";
    	for (JCheckBox jc : resultat) {
    		if (jc.isSelected()) textOutput = textOutput + jc.getText() + ',';
    	}
    	return textOutput;
    }

	@Override
    public void setBorder(Color color) {
    	for (JCheckBox t : resultat) {
    		t.setForeground(color);
    	}
    }

}