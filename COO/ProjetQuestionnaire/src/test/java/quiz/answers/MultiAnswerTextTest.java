package quiz.answers;

/**
 * Unit test for class MultiAnswerText.
 */
public class MultiAnswerTextTest extends MultiAnswerTest {

	@Override
	protected String createAnswerValue() {
		return "Frodo ; Pippin ; Merry ; Sam";
	}

	@Override
	protected String createText1() {
		return " frodo ";
	}

	@Override
	protected String createText2() {
		return "2";
	}

	@Override
	protected String createText3() {
		return "merry christmas ";
	}

	@Override
	protected Answer<?> createAnswerTest(String text) {
		return new MultiAnswerText(text);
	}

	@Override
	protected String createType() {
		return "4 possible answers";
	}
}
