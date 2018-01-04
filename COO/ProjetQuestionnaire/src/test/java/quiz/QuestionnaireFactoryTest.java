package quiz;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for class QuestionnaireFactory.
 */
public class QuestionnaireFactoryTest {
	
	private String description;
	private String className;
	private String answer;
	private String points;
	private QuestionnaireFactory qf;
	
	@Before
	public void setUp() {
		description = "A test question";
		className = "TextAnswer";
		answer = "A Test Answer";
		points = "10";
		qf = new QuestionnaireFactory();
	}
	
	@Test
	public void testCreateQuestion() throws IOException {
		Question question = qf.createQuestion(description, className, answer, points);
		assertEquals(question.getDescription(), description);
		assertEquals(question.getAnswer().getClass().getSimpleName(), className);
		assertEquals(question.getAnswer().toString(), answer);
		assertEquals(question.getPoints(), Integer.parseInt(points));
	}
	
	
	@Test(expected=IOException.class)
	public void testCreateQuestionThrowsIOException() throws IOException {
		points = "a";
		qf.createQuestion(description, className, answer, points);
	}
	
	
	@Test
	public void testCreateQuestionnaire() throws IOException {
		Questionnaire questionnaire = qf.createQuestionnaireConsole("./src/main/java/quiz/questionnaireExemples/questionnaireTest1.txt");
		assertEquals(questionnaire.getNumberQuestion(), 8);
	}
	
	@Test(expected=IOException.class)
	public void testCreateQuestionnaireThrowIOException() throws IOException {
		qf.createQuestionnaireConsole("./src/main/java/quiz/questionnaireExemples/questionnaireTest2.txt");
	}
	
	
	@Test(expected=IOException.class)
	public void testCreateQuestionnaireFileNotFoundException() throws IOException {
		qf.createQuestionnaireConsole("./src/main/java/quiz/questionnaireExemples/questionnaireTest3.txt");
	}    
}
