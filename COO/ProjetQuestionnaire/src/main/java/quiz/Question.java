package quiz;

import quiz.answers.Answer;

/**
 * A simple class to model a Question
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */
public class Question
{
	/** A description of the question*/
	private String description;
	
	/** the correct answer to this question */
	private Answer<?> answer;
	
	/** the number of points for this question */
	private int points;

	/**
     * Instantiates a new Questionnaire
     * @param description description of this question
     * @param answer answer for this question
     * @param questionPoint point for this question
     */
	public Question(String description, Answer<?> answer, int points) {
		this.description = description;
		this.answer      = answer;
		this.points      = points;
	}
	
	/** 
	 * Return a description of this question
	 * @return the description of this question
	 */
	public String getDescription() {
		return description;
	}

    /**
     * Returns the answer of this question
     * @return the answer to this question
     */
     public Answer<?> getAnswer() {
		return answer;
	}

	/** 
	 * Returns the number of points for this question
	 * @return question's point
	 */
	public int getPoints() {
	    return points;
    }
	
}

 