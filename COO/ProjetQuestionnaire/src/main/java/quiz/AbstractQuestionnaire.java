package quiz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple class to model a abstract Questionnaire
 * A Questionnaire contains a list of questions, and methods to ask the questions and evaluate the correct answer
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */
public abstract class AbstractQuestionnaire implements Questionnaire
{
	/* the questions in questionnaire */
	protected List<Question> theQuestions;

	/* score of player for this questionnaire */
	protected int points;

	/**
     * Instantiates a new Questionnaire
     */
	public AbstractQuestionnaire() {
		this.theQuestions = new ArrayList<>();
		this.points       = 0;
	}

    @Override
	public void addQuestion(Question question) {
		this.theQuestions.add(question);
    }

    @Override
	public abstract void askAll();

    @Override
	public abstract void askQuestion(Question question);	
	
    @Override
	public int getNumberQuestion() {
		return theQuestions.size();
	}

    @Override
    public List<Question> getTheQuestions() {
        return theQuestions;
    }
    
    @Override
    public abstract List<String> getTheAnswer();
	
    @Override
	public abstract void addTheAnswer(String text);
	
    @Override
	public int getPoints() {
		return points;
	}

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
	
    @Override
	public void resetPoint() {
		points=0;
	}
    
    @Override
    public abstract Color[] getTheColors();
    
    @Override
    public int getVerif() {
    	return 0;
    }
}
