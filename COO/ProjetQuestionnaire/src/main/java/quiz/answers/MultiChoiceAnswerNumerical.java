package quiz.answers;

import quiz.views.AnswerPanel;
import quiz.views.AnswerPanelFactory;

public class MultiChoiceAnswerNumerical extends MultiChoiceAnswer<Integer> {
	
	public MultiChoiceAnswerNumerical(String listAnswer) {
		super(listAnswer);
	}
	
	@Override
	public NumericalAnswer createOneAnswer(String text) {
		return new NumericalAnswer(text);
	}

    /**
     * Returns the view for this answer
     * @param answerPanelFactory a factory to create the view for this answer
     * @return returns an AnswerPanel object
     */
    public AnswerPanel<?> createView(AnswerPanelFactory answerPanelFactory) {
        return answerPanelFactory.createMultiChoiceAnswerNumericalPanel(this);
    }
}
