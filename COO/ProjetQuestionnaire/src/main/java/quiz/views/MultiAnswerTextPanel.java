package quiz.views;

import quiz.answers.MultiAnswer;
import javax.swing.*;
import java.awt.*;

public class MultiAnswerTextPanel extends AnswerPanel<JTextField>
{
    private static final long serialVersionUID = 1L;

    public MultiAnswerTextPanel(MultiAnswer<?> multiAnswer) {
        super(multiAnswer);
        JTextField aTextField;
        int nb = multiAnswer.getTheAnswers().size();
        for (int i=0;i<nb;i++) {
        	aTextField = new JTextField();
            aTextField.setPreferredSize(new Dimension(100, 50));
            panel.add(aTextField);
            resultat.add(aTextField);
        }
    }
    
    @Override
    public String getUserInput() {
    	String textOutput = "";
    	for (JTextField jt : resultat) {
    		textOutput = textOutput + jt.getText() + ',';
    	}
    	return textOutput;
    }

}