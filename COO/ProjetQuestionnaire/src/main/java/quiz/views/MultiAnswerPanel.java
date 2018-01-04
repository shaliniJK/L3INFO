package quiz.views;

import quiz.answers.MultiAnswer;
import javax.swing.*;
import java.awt.*;

public class MultiAnswerPanel extends AnswerPanel<JTextField>
{
    private static final long serialVersionUID = 1L;

    public MultiAnswerPanel(MultiAnswer<?> multiAnswer) {
        super(multiAnswer);
        JTextField aTextField = new JTextField();
        aTextField.setLayout(new FlowLayout());
        aTextField.setPreferredSize(new Dimension(270, 100));
        panel.add(aTextField);
        resultat.add(aTextField);
    }
    
    @Override
    public String getUserInput() {
    	return resultat.get(0).getText();
    }

}