package adventure.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;


/**
 * A simple class to implement unit tests for all Action classes in the game
 * The @before method allows us to replicate the actions of the `Scanner` class to verify
 * a user input
 */
public abstract class ActionTest {

    protected Action action;

    public abstract Action createAction();

    /**
     * Allows an automatic input of 1 to be entered from ByteArrayInputStream class so that
     * everytime a call is made to Scanner.readint, Action.execute() doesn't fail
     */
    @Before
    public void setUpBefore() {
        this.action = createAction();

        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    // using input ________ example
    // assertEquals("1", inputOutput.getInput());

}
