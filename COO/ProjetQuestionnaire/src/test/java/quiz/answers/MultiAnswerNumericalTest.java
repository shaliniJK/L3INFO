package quiz.answers;

/**
 * Unit test for class MultiAnswerNumerical.
 */
public class MultiAnswerNumericalTest extends MultiAnswerTest {

	@Override
	protected String createAnswerValue() {
		return "0 ; 2 ; 4";
	}

	@Override
	protected String createText1() {
		return " 2 ";
	}

	@Override
	protected String createText2() {
		return "two";
	}

	@Override
	protected String createText3() {
		return " 5 ";
	}

	@Override
	protected Answer<?> createAnswerTest(String text) {
		return new MultiAnswerNumerical(text);
	}

	@Override
	protected String createType() {
		return "3 possible answers";
	}

}
