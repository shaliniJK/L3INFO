package quiz.views;

import quiz.answers.YesNoAnswer;

import java.awt.Color;

import javax.swing.*;

public class YesNoAnswerPanel extends AnswerPanel<JRadioButton>
{
    private static final long serialVersionUID = 1L;

    public YesNoAnswerPanel(YesNoAnswer yesNoAnswer) {
        super(yesNoAnswer);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ButtonGroup group = new ButtonGroup();
        JRadioButton option = new JRadioButton("Yes");
        panel.add(option);
        group.add(option);
        resultat.add(option);
        option = new JRadioButton("No");
        group.add(option);
        panel.add(option);
        resultat.add(option);
    }
    
    @Override
    public String getUserInput() {
    	String textOutput = "";
    	if (resultat.get(0).isSelected() && !resultat.get(1).isSelected()) textOutput += "true"; 
    	else if (!resultat.get(0).isSelected() && resultat.get(1).isSelected()) textOutput += "false";
    	return textOutput;
    }
    
    @Override
    public void setBorder(Color color) {
    	for (JRadioButton t : resultat) {
    		t.setForeground(color);
    	}
    }
}