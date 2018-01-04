package quiz.answers;

import quiz.views.AnswerPanel;
import quiz.views.AnswerPanelFactory;

import java.util.ArrayList;
import java.util.List;


public abstract class MultiAnswer<T> extends AbstractAnswer<List <Answer<T>>> {
	/* A list of answer correct */
	protected List<Answer<T>> theAnswers;
	
	/* A list of sign to split an answer */
	protected List<Character> theSigns;
	
	/**
     * Instantiates a new MultiAnswer object
     * @param description the text description of this answer
     */
	public MultiAnswer(String listAnswer) {
		super(listAnswer);
		typeAnswer = theAnswers.size() + " possible answers";
	}
	
	@Override
	public List<Answer<T>> createAnswer(String listAnswer) {
		theAnswers = new ArrayList<>();
		theSigns = new ArrayList<>();
		theSigns.add(';'); theSigns.add('|');
		
		int x=0,y=0;
		while (y<listAnswer.length()) {
			if(theSigns.contains(listAnswer.charAt(y))) { 				
				theAnswers.add(createOneAnswer(listAnswer.substring(x, y)));
				x=y+1;
			}
			y++;
		} theAnswers.add(createOneAnswer(listAnswer.substring(x, y)));
		return theAnswers;
	}
	
	@Override
	public boolean hasCorrectType(String text) {
		if (theAnswers.get(0).hasCorrectType(text)) return true;
		return false;
	}
	
	@Override
	public boolean isCorrect(String text) {
		String[] answerInput = text.split(",");
		int res=0;
		for (String a : answerInput) {
			for (Answer<T> answer : theAnswers) {
				if (answer.isCorrect(a)) res++;
			}
		}
		if (res==answerInput.length && res!=0) return true;
		return false;
	}
	
	/**
     * Create one answer
     * @param text : the new answer to create
     * @return a answer type text or numerical
     */
	protected abstract Answer<T> createOneAnswer(String text);
	
	/**
     * Add a new answer possible
     * @param text : the new answer
     */
	public void addNewChoice (String text) {
		Answer<T> answer = createOneAnswer(text);
		theAnswers.add(answer);
	}
	
	/**
     * Add a new sign possible
     * @param sign : the new sign
     */
	public void addNewString (Character sign) {
		theSigns.add(sign);
	}
	
	/**
	 * Get the list of answer of this question
	 * @return the list of answer of this question
	 */
	public List<Answer<T>> getTheAnswers() {
		return theAnswers;
	}

    /**
     * Returns the view for this answer
     * @param answerPanelFactory a factory to create the view for this answer
     * @return returns an AnswerPanel object
     */
    public abstract AnswerPanel<?> createView(AnswerPanelFactory answerPanelFactory);
}
