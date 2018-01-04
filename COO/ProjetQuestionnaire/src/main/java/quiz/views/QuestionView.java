package quiz.views;

import quiz.Question;
import java.awt.*;
import javax.swing.*;

/**
 * A simple class to display a question
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 */
public class QuestionView extends JFrame
{
	private static final long serialVersionUID = 1L;

	/* the panel container of a question */
    protected JPanel panel;
    
    private AnswerPanel<?> answerPanel;

    public QuestionView(Question question, AnswerPanelFactory factory) {
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(1, 2, 5, 5));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        String questionIn = question.getDescription();
        JLabel label;
        if (questionIn.contains("(")) label = new JLabel(questionIn.substring(0,questionIn.indexOf('(')));
        else label = new JLabel(questionIn);
        
        label.setLayout(new FlowLayout(FlowLayout.LEFT));
        label.setSize(250, 50);

        this.panel.add(label);

        // create the view from the factory
        answerPanel = question.getAnswer().createView(factory);

        this.panel.add(answerPanel.getPanel());
    }

    public JPanel getPanel() {
        return this.panel;
    }
    
    public AnswerPanel<?> getAnswerPanel() {
    	return answerPanel;
    }

}
