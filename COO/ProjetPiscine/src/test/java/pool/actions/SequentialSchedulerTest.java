package pool.actions;

import pool.actions.schedulers.Scheduler;
import pool.actions.schedulers.SchedulerStartedException;
import pool.actions.schedulers.SequentialScheduler;

import static org.junit.Assert.*;
import org.junit.Test;

public class SequentialSchedulerTest extends SchedulerTest
{
    /**
     * Instantiates the action
     * @return the Action to be tested
     * */
    protected Action createAction() {
        return new SequentialScheduler();
    }

    /**
     * Instantiates the Scheduler action
     * @return the Scheduler to be tested
     */
    protected Scheduler createScheduler() {
        return new SequentialScheduler();
    }

    @Test
    public void TestSubActionsProcessedLinearly() throws SchedulerStartedException,
            ActionFinishedException
    {
        Scheduler sequentialScheduler = createScheduler();
        for (int i = 0; i < 3; i++) {
            sequentialScheduler.addAction(new ThreeStepActionMock());
        }
        for (int i = 0; i < 3; i++) {
            sequentialScheduler.doStep();
        }
        assertTrue(sequentialScheduler.getTheActions().size() == 2);
        assertEquals(sequentialScheduler.getTheActions().get(0).getState(), ActionState.READY);
    }
}
