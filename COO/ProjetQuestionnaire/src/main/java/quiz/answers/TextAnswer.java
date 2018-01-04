package quiz.answers;

import quiz.views.AnswerPanel;
import quiz.views.AnswerPanelFactory;

/**
 * A simple class TextAnswer
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */
public class TextAnswer extends AbstractAnswer<String>
{
	/**
     * Instantiates a new TextAnswer object
     * @param description the text description of this answer
     */
    public TextAnswer(String answerValue) {
        super(answerValue);
        typeAnswer="text";
    }
    
	@Override
	public String createAnswer(String text) {
		return text;
	}
	
	@Override
	public boolean hasCorrectType(String text) {
		try {
			Integer.parseInt(text);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
    }
    
    @Override
    public boolean isCorrect(String text) {
        return this.answerValue.toLowerCase().equals(deleteSpace(text).toLowerCase());
    }


    /**
     * Returns the view for this answer
     * @param answerPanelFactory a factory to create the view for this answer
     * @return returns an AnswerPanel object
     */
    public AnswerPanel<?> createView(AnswerPanelFactory answerPanelFactory) {
        return answerPanelFactory.createTextAnswerPanel(this);
    }

}
