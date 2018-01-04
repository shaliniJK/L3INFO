package quiz;

import quiz.answers.Answer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple class to model a Questionnaire with graphic
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */
public class QuestionnaireWithGraphic extends AbstractQuestionnaire {
	
	protected List<String> theAnswers;

	protected static Color[] theColors;
	
	private static int verif;

	/**
     * Instantiates a new QuestionnaireWithGraphic
     */
	public QuestionnaireWithGraphic() {
		super();
		theAnswers = new ArrayList<String>();
	}

	/**
	 * Ask all questions in the Questionnaire
	 */
	public void askAll() {
		theColors = new Color[theAnswers.size()];
		for (int i=0;i<theAnswers.size();i++) {
			theColors[i]=Color.gray;
		}
		
		for (int i=0;i<theQuestions.size();i++) {
			askQuestion(theQuestions.get(i),theAnswers.get(i),i);
		}
	}
	
	@Override
	public void askQuestion(Question question) {}

	public void askQuestion(Question question, String answer, int indexQuestion) {
        Answer<?> correctAnswer = question.getAnswer();
        String text = deleteSpace(answer);
        verif=0;
        if (text.contains("NoAnswer") || textIsEmpty(text)) {
        	theColors[indexQuestion]=Color.red;
        	verif=1;
        }
        
        if (verif==0) {
        	if (text.length()>0) {
    			if (correctAnswer.isCorrect(text)) {
    				points += question.getPoints();
    				theColors[indexQuestion]=Color.blue;
    			} else {
    				theColors[indexQuestion]=Color.black;
    			}
    		}
        }
    }

	@Override
	public List<String> getTheAnswer() {
		return theAnswers;
	}

	@Override
	public void addTheAnswer(String text) {
		theAnswers.add(text);
	}
	
	@Override
    public Color[] getTheColors() {
		return theColors;
	}
	
	public boolean textIsEmpty(String text) {
		if (text.isEmpty()) return true;
		if (text.contains(",")) {
			String[] tableText = text.split(",");
			if (tableText.length==0) return true;
		}
		return false;
	}
	
	@Override
	public int getVerif() {
		return verif;
	}
}
