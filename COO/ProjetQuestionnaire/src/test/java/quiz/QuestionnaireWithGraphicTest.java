package quiz;

import org.junit.Test;

/**
 * Unit test for class Questionnaire.
 */
public class QuestionnaireWithGraphicTest extends QuestionnaireTest {
	
	@Override
	protected QuestionnaireWithGraphic createQuestionnaire() {
		return new QuestionnaireWithGraphic();
	}
	
	@Test
	public void testAskAll() {
		//TODO
	}
	
	
	@Test
	public void testAskQuestion() {
		//TODO		
	}
	
	/*@Test
    public void testGetTheAnswer() {
		List<String> listTest = new ArrayList<String>();
		listTest.add(answer1.toString());
		listTest.add(answer2.toString());
		questionnaire.addQuestion(question2);
		System.out.println("size" + questionnaire.getTheAnswer().size());
		assertTrue(listTest.size()==questionnaire.getTheAnswer().size());
		for (String a : listTest) {
			assertTrue(questionnaire.getTheAnswer().contains(a));
		}
	}

	@Test	 
	public void testAddTheAnswer(String text) {
		String answer = "New answer";
		questionnaire.addTheAnswer(answer);
		assertTrue(questionnaire.getTheAnswer().contains(answer));
	}*/
}
