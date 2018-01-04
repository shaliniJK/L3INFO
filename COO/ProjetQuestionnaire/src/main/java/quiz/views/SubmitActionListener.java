package quiz.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A simple class to listen when the questionnaire is submitted when the submit button is pressed
 */
public class SubmitActionListener implements ActionListener
{
	
	public SubmitActionListener() {
	}
	
    public void actionPerformed(ActionEvent e) {
        submitAction(e);
    }

    public static void submitAction(ActionEvent event) {
    	//theAnswers
    }
}
