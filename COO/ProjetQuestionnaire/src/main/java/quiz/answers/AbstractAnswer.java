package quiz.answers;

import quiz.views.AnswerPanel;
import quiz.views.AnswerPanelFactory;

/**
 * A simple class to model an AbstractAnswer
 * This class is abstract
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */
public abstract class AbstractAnswer<T> implements Answer<T> {
    /* the correct AbstractAnswer*/
    protected T answerValue;
    
    /* the description of AbstractAnswer's type*/
    protected String typeAnswer;
    
    /* the AbstractAnswer */
    protected String stringAnswer;

    /**
     * Instantiates a new AbstractAnswer object
     * @param description the text description of this AbstractAnswer
     */
    public AbstractAnswer(String answerValue) {
    	String answerValue2 = deleteSpace(answerValue);
    	this.answerValue = createAnswer(answerValue2);
        this.stringAnswer = answerValue2;
    }
    
    @Override
    public abstract T createAnswer(String text);
    
    @Override
    public boolean hasCorrectType(String text) {
    	return true;
    }
    
    @Override
    public abstract boolean isCorrect(String text);
    
    @Override
    public String getTypeAnswer() {
    	return typeAnswer;		
	}
    
    @Override
    public String toString() {
    	return stringAnswer;
    }

    /**
     * Returns the view for this answer
     * @param answerPanelFactory a factory to create the view for this answer
     * @return returns an AnswerPanel object
     */
    public abstract AnswerPanel<?> createView(AnswerPanelFactory answerPanelFactory);
    
    @Override
    public String deleteSpace(String text) {
    	String text2 = text;
    	int len = text2.length();
		while (len>0 && text2.charAt(0)==' ') {
			text2=text2.substring(1,len--);
		}
		while (len>0 && text2.charAt(len-1)==' ') {
			text2=text2.substring(0,--len);
		}
		return text2;
    }
}
