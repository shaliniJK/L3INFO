package quiz.answers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for class AnswerFactory.
 */
public class AnswerFactoryTest {
	private String className;
	private String answer;
	
	@Before
	public void setUp() {
		className="TextAnswer";
		answer = "A answer test";
	}
	
	@Test
	public void testBuilAnswerSuccess() {
		Answer<?> resultat = AnswerFactory.getInstance().buildAnswer(className, answer);
		assertEquals(resultat.getClass().getSimpleName(), className);
		assertTrue(resultat.isCorrect(answer));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testBuilAnswerUnknownClass() {
		className = "MockAnswer";
		AnswerFactory.getInstance().buildAnswer(className, answer);
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testBuilAnswerSecurityViolation() {
		className = "mocks.MockAnswer1";
		AnswerFactory.getInstance().buildAnswer(className, answer);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testBuilAnswerConstructorIsMissing() {
		className = "mocks.MockAnswer2";
		AnswerFactory.getInstance().buildAnswer(className, answer);
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testBuilAnswerConstructorError() {
		className = "mocks.MockAnswer3";
		AnswerFactory.getInstance().buildAnswer(className, answer);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testBuilAnswerInvocationError() {
		className = "mocks.MockAnswer4";
		AnswerFactory.getInstance().buildAnswer(className, answer);
	}
	
}
