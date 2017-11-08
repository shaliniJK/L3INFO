package pool.actions;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class ForeseeableActionTest extends ActionTest
{
    private int n;

    @Override
    protected Action createAction() {
        return new ForeseeableAction("test",5) ;
    }

    @Before
    public void initAction() {
        this.n = (int) (Math.random() * 100) + 1;
        super.action = new ForeseeableAction("test",n);
    }

    @Test
    public void testStopCondition() throws ActionFinishedException {
        assertFalse(action.stopCondition());
        for(int i=0; i<n; i++) {
            action.doStep();
        }
        assertTrue(action.stopCondition());
    }


    @Test
    public void testDoStepFixedExecutionTime() throws ActionFinishedException {
        for (int i = 0; i < n; i++) {
            action.doStep();
        }
        assertTrue(action.isFinished());
    }
}
