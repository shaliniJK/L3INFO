package pool.actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * A abstract class to test Actions
 * All Actions must verify this test.
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public abstract class ActionTest
{
    /* the action to be tested */
    protected Action action;

    /**
     * Instantiates the action
     * @return the Action to be tested
     */
    protected abstract Action createAction();

    @Before
    public void initAction() {
        this.action = createAction();
    }

    @Test
    public void testNewActionInReadyState() {
        assertEquals(action.getState(), ActionState.READY);
    }

    @Test(timeout = 1000)
    public void testDoStepFinishedAction() throws ActionFinishedException {
        assertEquals(action.getState(), ActionState.READY);
        action.doStep();
        assertNotEquals(action.getState(), ActionState.READY);
        while (!action.isFinished()) {
            action.doStep();
        }
        assertEquals(action.getState(), ActionState.FINISHED);
    }

    @Test(expected = ActionFinishedException.class, timeout = 1000)
    public void testDoStepFinishedActionThrowsException() throws ActionFinishedException {
        while (! action.isFinished()) {
            action.doStep();
        }
        action.doStep();
    }
}