package quiz;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import quiz.answers.Answer;
import quiz.answers.TextAnswer;

/**
 * Unit test for class Question.
 */
public class QuestionTest {
	private String description;
	private Answer<?> answer;
	private int points;
	private Question question;
	
	@Before
	public void setUp() {
		description = "A test question";
		answer = new TextAnswer("Answer Test");
		points = 10;
		question = new Question(description, answer, points);
	}

	@Test
	public void testGetDescription() {
		assertEquals(description,question.getDescription());
	}
	
	@Test
	public void testGetAnswer() {
		assertEquals(answer,question.getAnswer());
	}
	
	@Test
	public void testGetPoints() {
		assertEquals(points,question.getPoints());
	}
}
