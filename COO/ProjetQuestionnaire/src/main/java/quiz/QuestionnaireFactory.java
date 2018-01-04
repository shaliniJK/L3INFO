package quiz;

import quiz.answers.AnswerFactory;

import java.io.*;

/**
 * A simple class to create a Questionnaire from a file having a correct prescribed form
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */
public class QuestionnaireFactory
{
    /**
     * Creates a question object from input given in string format
     * @param description the description formulating the question
     * @param answer the correct answer to the question, in string format
     * @param points the number of points awarded on correct answering of this question
     * @return a Question object built from a given description, the answer, and the points
     * @throws IOException when arguments are given in an illegal format
     */
    public Question createQuestion(String description, String className, String answer, String points) throws IOException
    {
        try {
            int nbPoints = Integer.parseInt(points);
            return new Question(description, AnswerFactory.getInstance().buildAnswer(className, answer), nbPoints);
        }
        catch (NumberFormatException e) {
            throw new IOException("Bad format");
        }
    }

    /**
     * Creates a Questionnaire object from the a given file, supposed to be in a correct form
     * @param fileName the name of the file to be parsed
     * @return a Questionnaire object correctly representing the questions found in the file fileName
     * @throws IOException when the file corresponding to fileName doesn't exist
     */
    public Questionnaire createQuestionnaireConsole(String fileName) throws IOException
    {
        Questionnaire questionnaire = new QuestionnaireConsole();
        FileReader source           = new FileReader(fileName);
        BufferedReader in           = null;
        
        try {
            String description; 
            in = new BufferedReader(source);
            
            while ((description = in.readLine()) != null) {
                String answer    = in.readLine();
                String points    = in.readLine();
                String className = in.readLine();

                if (answer == null || points == null || className == null) {
                    throw new IOException("Bad format");
                }

                questionnaire.addQuestion(this.createQuestion(description, className, answer, points));
            }
            
        }
        catch (FileNotFoundException e) {
            throw new IOException(e);
        }
        finally {
        	in.close();
		}
        
        return questionnaire;
    }

	public Questionnaire createQuestionnaireWithGraphic(String fileName) throws IOException {
		Questionnaire questionnaire = new QuestionnaireWithGraphic();
        FileReader source           = new FileReader(fileName);
        BufferedReader in           = null;
        
        try {
            String description; 
            in = new BufferedReader(source);
            
            while ((description = in.readLine()) != null) {
                String answer    = in.readLine();
                String points    = in.readLine();
                String className = in.readLine();

                if (answer == null || points == null || className == null) {
                    throw new IOException("Bad format");
                }

                questionnaire.addQuestion(this.createQuestion(description, className, answer, points));
            }
            
        }
        catch (FileNotFoundException e) {
            throw new IOException(e);
        }
        finally {
        	in.close();
		}
        
        return questionnaire;
	}

}
