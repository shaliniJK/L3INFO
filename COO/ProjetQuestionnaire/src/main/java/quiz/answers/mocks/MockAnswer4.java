package quiz.answers.mocks;

/**
 * A simple class MockAnswer4 to test the AnswerFactory
 * When this class throws an exception
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */

public class MockAnswer4 {

	public MockAnswer4(String text) throws NumberFormatException {
		try {
			Integer.parseInt(text);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}	

	}
	
}
