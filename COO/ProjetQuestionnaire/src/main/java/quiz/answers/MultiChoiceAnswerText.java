package quiz.answers;

import quiz.views.AnswerPanel;
import quiz.views.AnswerPanelFactory;

public class MultiChoiceAnswerText extends MultiChoiceAnswer<String> {
	
	public MultiChoiceAnswerText(String listAnswer) {
		super(listAnswer);
	}
	
	@Override
	public TextAnswer createOneAnswer(String text) {
		return new TextAnswer(text);
	}

    /**
     * Returns the view for this answer
     * @param answerPanelFactory a factory to create the view for this answer
     * @return returns an AnswerPanel object
     */
    public AnswerPanel<?> createView(AnswerPanelFactory answerPanelFactory) {
       return answerPanelFactory.createMultiChoiceAnswerTextPanel(this);
    }
}