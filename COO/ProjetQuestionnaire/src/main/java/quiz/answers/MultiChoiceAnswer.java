package quiz.answers;

import java.util.ArrayList;
import java.util.List;

import quiz.views.AnswerPanel;
import quiz.views.AnswerPanelFactory;

public abstract class MultiChoiceAnswer<T> extends MultiAnswer<T> {
	
	protected List<String> answerPropose;
	
	/**
     * Instantiates a new MultiAnswer object
     * @param description the text description of this answer
     */
	public MultiChoiceAnswer(String listAnswer) {
		super(listAnswer.substring(listAnswer.indexOf(')')+1));		
		typeAnswer = "multiple choice answers";
		createAnswerPropose(listAnswer.substring(1,listAnswer.indexOf(')')));		
	}
	
	/**
	 * Create a list of answer propose to user
	 * @param text the answers 
	 */
	private void createAnswerPropose (String text) {
		answerPropose = new ArrayList<>();
		theSigns.add(';'); theSigns.add('|');
		
		int x=0,y=0;
		while (y<text.length()) {
			if(theSigns.contains(text.charAt(y))) { 				
				answerPropose.add(text.substring(x, y));
				x=y+1;
			}
			y++;
		} answerPropose.add(text.substring(x, y));
	}

	
	@Override
	public boolean hasCorrectType(String text) {
		String[] answerInput = text.split(",");
		return theAnswers.get(0).hasCorrectType(answerInput[0]);
	}
	
	@Override
	public boolean isCorrect(String text) {
		String[] answerInput = text.split(",");
		if (answerInput.length !=theAnswers.size()) return false;
		int res=0;
		for (String a : answerInput) {
			for (Answer<T> answer : theAnswers) {
				if (answer.isCorrect(a)) res++;
			}
		}
		
		if (res==theAnswers.size()) return true;
		return false;
	}
	
	/**
	 * Get the list of answer propose to this question
	 * @return the list of answer propose
	 */
	public List<String> getAnswerPropose() {
		return answerPropose;		
	}
		
    /**
     * Returns the view for this answer
     * @param answerPanelFactory a factory to create the view for this answer
     * @return returns an AnswerPanel object
     */
    public abstract AnswerPanel<?> createView(AnswerPanelFactory answerPanelFactory);
}
