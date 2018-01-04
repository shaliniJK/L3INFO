package quiz.answers;

import quiz.views.AnswerPanel;
import quiz.views.AnswerPanelFactory;

import java.util.List;
import java.util.ArrayList;

/**
 * A simple class YesNoAnswer
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */

public class YesNoAnswer extends AbstractAnswer<String> {
	/* A list of different input possible to answer true */
	private List<String> answerPossibleTrue;
	
	/* A list of different input possible to answer false */
	private List<String> answerPossibleFalse;
	
	/**
     * Instantiates a new YesNoAnswer
     * @param answerValue : the answer
     */
	public YesNoAnswer (String answerValue) {
		super(answerValue);
		typeAnswer="y/n";
		setUpYesNoAnswer();
	}
	
	/**
     * Set up the lists answerPossibleTrue and answerPossibleFalse
     */
	public void setUpYesNoAnswer() {
		answerPossibleTrue = new ArrayList<String>();
		answerPossibleFalse = new ArrayList<String>();
		answerPossibleTrue.add("yes");
		answerPossibleTrue.add("y");
		answerPossibleTrue.add("oui");
		answerPossibleTrue.add("o");
		answerPossibleTrue.add("true");
		answerPossibleFalse.add("no");
		answerPossibleFalse.add("n");
		answerPossibleFalse.add("non");
		answerPossibleFalse.add("false");
	}

	@Override
	public String createAnswer(String text) {
		return text;
	}	
	
	@Override
	public boolean isCorrect(String text) {
		String text2 = text.toLowerCase();
		if ((answerValue.toLowerCase().equals("true") || answerValue.toLowerCase().equals("vrai")) && answerPossibleTrue.contains(text2)) return true;
		else if ((answerValue.toLowerCase().equals("false") || answerValue.toLowerCase().equals("faux")) && answerPossibleFalse.contains(text2)) return true;
		return false;
	}
	
	@Override
	public boolean hasCorrectType(String text) {
    	if (answerPossibleTrue.contains(text) || answerPossibleFalse.contains(text)) return true;
    	return false;
    }
	
	/**
     * Add a new input possible to the answer true
     * @param t : the new input
     */
	public void addNewTruePossibl(String t) {
		answerPossibleTrue.add(t);
	}
	
	/**
     * Add a new input possible to the answer true
     * @param f : the new input
     */
	public void addNewFalsePossibl(String f) {
		answerPossibleFalse.add(f);
	}


    /**
     * Returns the view for this answer
     * @param answerPanelFactory a factory to create the view for this answer
     * @return returns an AnswerPanel object
     */
    public AnswerPanel<?> createView(AnswerPanelFactory answerPanelFactory) {
        return answerPanelFactory.createYesNoAnswerPanel(this);
    }
	
}
