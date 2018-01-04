package quiz.views;

import quiz.answers.TextAnswer;

import javax.swing.*;
import java.awt.*;

public class TextAnswerPanel extends AnswerPanel<JTextField>
{
    private static final long serialVersionUID = 1L;
    

    public TextAnswerPanel(TextAnswer textAnswer) {
        super(textAnswer);
        JTextField aTextField = new JTextField();
        aTextField.setLayout(new FlowLayout());
        aTextField.setPreferredSize(new Dimension(100, 50));
        panel.add(aTextField);
        resultat.add(aTextField);
    }
    
    @Override
    public String getUserInput() {
    	
    	return resultat.get(0).getText();
    }
}