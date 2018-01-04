package quiz.answers;

import quiz.views.AnswerPanel;
import quiz.views.AnswerPanelFactory;

/**
 * A simple abstract class to model an Answer
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */
public interface Answer<T> {    
    /**
     * Create an answer of type generic
     * @return the answer of type T generic
     */
    public T createAnswer(String text);
    
    /**
     * Indicates whether this answer has the correct type
     * @param text the answer entered by the user
     * @return <tt>true</tt> if the type of this is equal to type of answer, otherwise <tt>false</tt>
     */
    public boolean hasCorrectType(String text);
    
    /**
     * Indicates whether this answer is the correct answer
     * @param text the answer entered by the user
     * @return <tt>true</tt> if the text is equal to the correct Answer, otherwise <tt>false</tt>
     */
    public boolean isCorrect(String text);

    /**
     * Returns the type of answer
     * @return the type of answer expected for this Answer
     */
    public String getTypeAnswer();

    /**
     * Returns a string description of this Answer
     * @return the string description of this Answer
     */
    public String toString();


    /**
     * Delete the spaces in the beginning and the end of text
     * @param text a text to delete the spaces
     * @return text without spaces in the beginning and the end
     */
    public String deleteSpace(String text);


    /**
     * Returns the view for this answer
     * @param answerPanelFactory a factory to create the view for this answer
     * @return returns an AnswerPanel object
     */
    public AnswerPanel<?> createView(AnswerPanelFactory answerPanelFactory);
}
