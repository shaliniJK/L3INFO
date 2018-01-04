package quiz;

import static org.junit.Assert.*;

import java.util.List;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import quiz.answers.Answer;
import quiz.answers.TextAnswer;
import quiz.answers.YesNoAnswer;

/**
 * Unit test for class Questionnaire.
 */
public abstract class QuestionnaireTest {
	protected Questionnaire questionnaire;
	protected Question question1;
	protected Question question2;
	protected Answer<?> answer1;
	protected Answer<?> answer2;
	
	protected abstract Questionnaire createQuestionnaire();
	
	@Before
	public void setUp() {
		this.questionnaire = createQuestionnaire();
		answer1 = new TextAnswer("TextAnswer Test");
		question1 = new Question("A test TextQuestion", answer1, 10);
		questionnaire.addQuestion(question1);
		
		answer2 = new YesNoAnswer("YesNoAnswer Test");
		question2 = new Question("A test YesNoQuestion", answer2, 5);
	}

	@Test
	public void testAddQuestion() {
		assertEquals(questionnaire.getNumberQuestion(),1);
		questionnaire.addQuestion(question2);
		assertEquals(questionnaire.getNumberQuestion(),2);
	}

	@Test
	public void testGetNumberQuestion() {
		assertEquals(questionnaire.getNumberQuestion(),1);
		questionnaire.addQuestion(question2);
		assertEquals(questionnaire.getNumberQuestion(),2);
	}
	
	@Test
    public void testGetTheQuestions() {
		List<Question> listTest = new ArrayList<Question>();
		listTest.add(question1);
		listTest.add(question2);
		questionnaire.addQuestion(question2);
		assertTrue(listTest.size()==questionnaire.getTheQuestions().size());
		for (Question q : listTest) {
			assertTrue(questionnaire.getTheQuestions().contains(q));
		}
	}
	
	@Test
	public void testGetPoints() {
		assertTrue(questionnaire.getPoints()==0);
		//TODO
	}
	
	@Test
	public void testResetPoint() {
		//TODO
		questionnaire.resetPoint();
		assertTrue(questionnaire.getPoints()==0);
	}
}
