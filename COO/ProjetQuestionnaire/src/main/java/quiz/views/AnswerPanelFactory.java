package quiz.views;

import javax.swing.JComponent;

import quiz.answers.*;


/**
 * A simple class to create the views for each type of answer
 * @author Jayjaywantee Koodun, Thi-Ngoc-Anh Tran
 */
public class AnswerPanelFactory
{
    /* the instance of the AnswerFactory */
    private static AnswerPanelFactory instance = new AnswerPanelFactory();

    /**
     * Returns an instance of the AnswerFactory
     * @return the singleton instance of the AnswerFactory
     */
    public static AnswerPanelFactory getInstance() {
        return instance;
    }

    /**
     * Instantiates a new AnswerPanelFactory
     */
    public AnswerPanelFactory() {}

    public AnswerPanel<?> createAnswerPanel(Answer<?> answer) {
        return new AnswerPanel<JComponent>(answer);
    }

    public AnswerPanel<?> createTextAnswerPanel(TextAnswer textAnswer) {
        return new TextAnswerPanel(textAnswer);
    }

    public AnswerPanel<?> createYesNoAnswerPanel(YesNoAnswer yesNoAnswer) {
        return new YesNoAnswerPanel(yesNoAnswer);
    }

    public AnswerPanel<?> createNumericalAnswerPanel(NumericalAnswer numericalAnswer) {
        return new NumericalAnswerPanel(numericalAnswer);
    }

    public AnswerPanel<?> createMultiAnswerTextPanel(MultiAnswerText multiAnswerText) {
        return new MultiAnswerTextPanel(multiAnswerText);
    }

    public AnswerPanel<?> createMultiAnswerNumericalPanel(MultiAnswerNumerical multiAnswerNumerical) {
        return new MultiAnswerNumericalPanel(multiAnswerNumerical);
    }
    
    public AnswerPanel<?> createMultiChoiceAnswerTextPanel(MultiChoiceAnswerText multiChoiceAnswerText) {
        return new MultiChoiceAnswerPanel(multiChoiceAnswerText);
    }
    
    public AnswerPanel<?> createMultiChoiceAnswerNumericalPanel(MultiChoiceAnswerNumerical multiChoiceAnswerNumerical) {
        return new MultiChoiceAnswerPanel(multiChoiceAnswerNumerical);
    }

}