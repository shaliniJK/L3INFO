package quiz.answers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for class Answer.
 */
public abstract class AnswerTest {
	protected Answer<?> answer;
	protected String answerValue;
	protected String text1;
	protected String text2;
	protected String text3;
	protected String type;
	
	protected abstract String createAnswerValue();
	protected abstract String createText1();
	protected abstract String createText2();
	protected abstract String createText3();
	protected abstract String createType();
	protected abstract Answer<?> createAnswerTest(String text);
	
	@Before
	public void setUp() {
		text1 = createText1();		// Text correct to this answer
		text2 = createText2();		// Text is not correct type to this answer
		text3 = createText3();		// Text is not correct to this answer
		type = createType();
		answerValue = createAnswerValue();
		answer = createAnswerTest(answerValue);
	}
    
	@Test
    public void testHasCorrectType() {
    	assertTrue(answer.hasCorrectType(text1));
    	assertFalse(answer.hasCorrectType(text2));
    }
    
	@Test
    public void testIsCorrect() {
		assertTrue(answer.isCorrect(text1));
		assertFalse(answer.isCorrect(text3));
	}
    
	@Test
    public void testGetTypeAnswer() { 
		assertEquals(type, answer.getTypeAnswer());
	}
	
	@Test
	public void testToString() {
		assertEquals(answerValue, answer.toString());
	}
    
	@Test
    public void testDeleteSpace () {
		assertEquals("test", answer.deleteSpace(" test "));
		assertEquals("test", answer.deleteSpace(" test"));
		assertEquals("test", answer.deleteSpace("test "));
	}
}
