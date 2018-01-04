package quiz;

import java.awt.Color;
import java.util.List;

/**
 * A interface to model a Questionnaire
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */
public interface Questionnaire {

    /**
     * Adds a new question in the Questionnaire
     * @param question the question to be added
     */
	public void addQuestion(Question question);

	/**
	 * Ask all questions in the Questionnaire
	 */
	public void askAll();

    /**
     * Asks a question
     * @param question the question to be asked
     */
	public void askQuestion(Question question);	
	
	/**
	 * Get number of the questions in the questionnaire
	 * @return the number of the questions
	 */
	public int getNumberQuestion();

    /**
     * list of all questions contained in this questionnaire
     * @return a list of all questions contained in this questionnaire
     */
    public List<Question> getTheQuestions();
    
    /**
     * Get all answer of this questionnaire
     * @return all answer of this questionnaire
     */
    public List<String> getTheAnswer();
	
    /**
     * Add an answer to the list of questionnaire's answer
     * @param text an answer to add
     */
	public void addTheAnswer(String text);
	
	/**
	 * Get point of user
	 * @return user's point
	 */
	public int getPoints();

	/**
     * Delete the spaces in the beginning and the end of text
     * @param text a text to delete the spaces
     * @return text without spaces in the beginning and the end
     */
	public String deleteSpace(String text);
	
	/**
	 * Reset user's point = 0
	 */
	public void resetPoint();
	
	/**
	 * get the table of color
	 * @return table of color
	 */
	public Color[] getTheColors();
	
	/**
	 * If all question is filled
	 * @return <tt>true<tt> if all question is filled, otherwise <tt>false</tt>
	 */
	public int getVerif();
}
