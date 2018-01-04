package quiz.answers;


/**
 * Unit test for class TextAnswer.
 */
public class TextAnswerTest extends AbstractAnswerTest {

	@Override
	protected String createAnswerValue() {
		return "A TextAnswer Test";
	}

	@Override
	protected String createText1() {
		return "A TextAnswer Test";
	}

	@Override
	protected String createText2() {
		return "10";
	}

	@Override
	protected String createText3() {
		return "Not the correct answer";
	}

	@Override
	protected Answer<?> createAnswerTest(String text) {
		return new TextAnswer(text);
	}

	@Override
	protected String createType() {
		return "text";
	}

}
