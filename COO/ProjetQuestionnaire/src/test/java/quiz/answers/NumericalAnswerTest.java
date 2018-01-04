package quiz.answers;

/**
 * Unit test for class NumericalAnswer.
 */
public class NumericalAnswerTest extends AbstractAnswerTest {

	@Override
	protected String createAnswerValue() {
		return "10";
	}

	@Override
	protected String createText1() {
		return " 10 ";
	}

	@Override
	protected String createText2() {
		return "ten";	
	}

	@Override
	protected String createText3() {
		return " 11 ";	}

	@Override
	protected Answer<?> createAnswerTest(String text) {
		return new NumericalAnswer(text);
	}

	@Override
	protected String createType() {
		return "numeric";
	}

}
