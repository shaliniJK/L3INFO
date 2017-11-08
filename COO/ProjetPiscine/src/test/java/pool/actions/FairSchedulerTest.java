package pool.actions;

import org.junit.Test;
import static org.junit.Assert.*;

import pool.actions.schedulers.FairScheduler;
import pool.actions.schedulers.Scheduler;
import pool.actions.schedulers.SchedulerStartedException;

public class FairSchedulerTest extends SchedulerTest
{
    @Override
    protected Action createAction() {
        return new FairScheduler();
    }

    /**
     * Instantiates the Scheduler action
     * @return the Scheduler to be tested
     */
    protected Scheduler createScheduler() {
        return new FairScheduler();
    }


    @Test
    public void testSubActionsProcessedInParallel() throws SchedulerStartedException,
            ActionFinishedException
    {
        Scheduler fairScheduler = createScheduler();
        for (int i = 0; i < 3; i++) {
            fairScheduler.addAction(new ThreeStepActionMock());
        }
        for (int i = 0; i < 3; i++) {
            fairScheduler.doStep();
        }
        for (Action a: fairScheduler.getTheActions()) {
            assertTrue(a.getState() == ActionState.IN_PROGRESS);
        }
        assertEquals(fairScheduler.getTheActions().size(), 3) ;
    }

}
