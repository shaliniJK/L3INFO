package quiz.answers;

import java.lang.reflect.*;


/**
 * A simple class AnswerFactory to handle the creation of the correct Answer object to a given Question
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 * @version 1.0
 */
public class AnswerFactory
{
    /* the instance of the AnswerFactory */
    private static AnswerFactory instance = new AnswerFactory();

    /**
     * Returns an instance of the AnswerFactory
     * @return the singleton instance of the AnswerFactory
     */
    public static AnswerFactory getInstance() {
        return instance;
    }

    /**
     * Instantiates a new AnswerFactory
     */
    private AnswerFactory() {}


    /**
     * Builds an Answer object based on the name of the class given in parameters and the string consisting of answer
     * @param className the name of the class to be constructed
     * @param answer the string consisting of the answer
     * @return a new Answer object created using the reflect API
     */
    public Answer<?> buildAnswer(String className, String answer)
    {
        Class<?> cls;
        Constructor<?> constructor;

        try {
            cls = Class.forName("quiz.answers." + className);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unknown class : " + className);
        }

        try {
            constructor = cls.getConstructor(String.class);
        }
        catch (SecurityException e) {
            throw new IllegalArgumentException("Security violation");
        }
        catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Constructor is missing");
        }

        try {
            return (Answer<?>) constructor.newInstance(answer);
        }
        catch (InstantiationException e) {
            throw new IllegalArgumentException("Construction error");
        }
        catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Security violation");
        }
        catch (InvocationTargetException e) {
            throw new IllegalArgumentException("Invocation error");
        }
    }

}
