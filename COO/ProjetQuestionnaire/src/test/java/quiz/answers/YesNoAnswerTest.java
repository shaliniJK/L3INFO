package quiz.answers;

/**
 * Unit test for class YesNoAnswer.
 */
public class YesNoAnswerTest extends AbstractAnswerTest {

	@Override
	protected String createAnswerValue() {
		return "true";
	}

	@Override
	protected String createText1() {
		return "oui";
	}

	@Override
	protected String createText2() {
		return "accept";	}

	@Override
	protected String createText3() {
		return "non";
	}

	@Override
	protected Answer<?> createAnswerTest(String text) {
		return new YesNoAnswer(text);
	}

	@Override
	protected String createType() {
		return "y/n";
	}
}
