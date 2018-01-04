package quiz;

import java.awt.Color;
import java.util.List;
import java.util.Scanner;

import quiz.answers.Answer;

/**
 * A simple class to model a Questionnaire on console
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */
public class QuestionnaireConsole extends AbstractQuestionnaire {
	
	/* A scanner to get user's */
	private Scanner scanner;
	
	/* To know if the scanner is open=1 or close=0*/
	private int verif=0;

	/**
     * Instantiates a new Questionnaire
     */
	public QuestionnaireConsole() {
		super();
	}

	@Override	
	public void askAll() {
		scanner = new Scanner(System.in);
		verif=1;
		for (Question question : theQuestions) {
			System.out.print("\n");
			askQuestion(question);
		}
		System.out.println("Vous avez obtenu " + points + " points." );
		scanner.close();
	}

	@Override
	public void askQuestion(Question question) {
	    String userAnswer;

        Answer<?> correctAnswer = question.getAnswer();
	    System.out.println(question.getDescription());
	    
	    if(verif==0) scanner = new Scanner(System.in);
	    
        do {
        	System.out.print("(" + correctAnswer.getTypeAnswer() + ") ");
            userAnswer = deleteSpace(scanner.nextLine());
        } while (userAnswer.length()==0 || !correctAnswer.hasCorrectType(userAnswer));

        
        if (correctAnswer.isCorrect(userAnswer)) {
            points += question.getPoints();
            System.out.println("Correct (" + question.getPoints() + " points)" );
        }
        else {
            System.out.println("Incorrect! La bonne réponse est: " + correctAnswer.toString());
        }
        if(verif==0) scanner.close();
    }

	@Override
	public List<String> getTheAnswer() {
		return null;
	}
	
	@Override
	public void addTheAnswer(String text) {	}

	@Override
    public Color[] getTheColors() {
		return null;
	}
}
