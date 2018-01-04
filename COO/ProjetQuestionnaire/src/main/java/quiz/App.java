package quiz;

import quiz.QuestionnaireFactory;
import quiz.views.AnswerPanelFactory;
import quiz.views.QuestionnaireView;

import java.io.IOException;
import java.util.Scanner;

/**
 * A simple class to launch the quiz application
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 */
public class App 
{
    public static void main( String[] args )
    {
        int choice = 0;
        
        Questionnaire quiz;

        QuestionnaireFactory questionnaireFactory = new QuestionnaireFactory();
        AnswerPanelFactory answerPanelFactory     = new AnswerPanelFactory();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Graphical questionnaire : 1 \n");
        System.out.println("Console questionnaire   : 2 \n");
        System.out.println("Choose by entering integer : 1 or 2 \n");

        boolean done = false;

        while (!done) {
            choice = scanner.nextInt();
            if (choice == 1 || choice == 2) {
                done = true;
            }
        }
  
        try {
            if (choice == 1) {
                quiz = questionnaireFactory.createQuestionnaireWithGraphic("./src/main/java/quiz/questionnaireExemples/questionnaire1.txt");
                System.out.println("Graphical questionnaire \n");    
                QuestionnaireView questionnaireView = new QuestionnaireView(quiz, answerPanelFactory);
                questionnaireView.display();
            }
            else {
                quiz = questionnaireFactory.createQuestionnaireConsole("./src/main/java/quiz/questionnaireExemples/questionnaire1.txt");
                System.out.println("Console questionnaire \n");
                quiz.askAll();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        scanner.close();

    }

}
