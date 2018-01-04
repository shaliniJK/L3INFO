package quiz.views;

import quiz.answers.Answer;

import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import java.util.ArrayList;

public class AnswerPanel<T extends JComponent> extends JFrame
{
    private static final long serialVersionUID = 1L;

    protected JPanel panel;

    protected Answer<?> answer;
    
    protected List<T> resultat;

    /**
     * Instantiates a new AnswerPanel
     * @param answer the answer to create
     */
    public AnswerPanel(Answer<?> answer) {
        this.answer = answer;
        this.panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
        resultat=new ArrayList<T>();
    }
    
    /**
     * Get this panel
     * @return the panel
     */
    public JPanel getPanel() {
        return this.panel;
    }
    
    /**
     * Get the input from user
     * @return get the input from user
     */
    public String getUserInput() {
    	return null;
    }
    
    /**
     * Set color to the element in panel
     * @param color to make color the bordel of element in panel
     */
    public void setBorder(Color color) {
    	for (T t : resultat) {
    		t.setBorder(BorderFactory.createLineBorder(color));
    	}
    }

}