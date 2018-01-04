package quiz.answers;

import quiz.views.AnswerPanel;
import quiz.views.AnswerPanelFactory;

/**
 * A simple class to model a NumericalAnswer
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */

public class NumericalAnswer extends AbstractAnswer<Integer>
{
    /**
     * Instantiates a new NumericalAnswer
     * @param stringAnswer the answer in string format as parsed from file
     */
	public NumericalAnswer (String stringAnswer) {
		super(stringAnswer);
		this.typeAnswer  = "numeric";
	}
	
	@Override
	public Integer createAnswer(String text) {
		return Integer.parseInt(text);
	}

    /**
     * Indicates whether the user has entered an answer in the numerical format
     * @param text the input answer given by the user
     * @return  @return <tt>true</tt> if the input has the correct format for this answer, otherwise <tt>false</tt>
     */
    public boolean hasCorrectType(String text) {
		try {
			Integer.parseInt(deleteSpace(text));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public boolean isCorrect(String text) {
		return Integer.parseInt(deleteSpace(text))==answerValue;
	}


    /**
     * Returns the view for this answer
     * @param answerPanelFactory a factory to create the view for this answer
     * @return returns an AnswerPanel object
     */
    public AnswerPanel<?> createView(AnswerPanelFactory answerPanelFactory) {
        return answerPanelFactory.createNumericalAnswerPanel(this);
    }

}
