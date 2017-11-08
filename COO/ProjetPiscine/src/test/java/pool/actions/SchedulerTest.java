package pool.actions;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import pool.actions.schedulers.Scheduler;
import pool.actions.schedulers.SchedulerStartedException;

public abstract class SchedulerTest extends ActionTest
{
    protected Scheduler scheduler;
    /**
     * Instantiates the action
     * @return the action to be tested
     */
    protected abstract Action createAction();

    /**
     * Instantiates the Scheduler action
     * @return the Scheduler to be tested
     */
    protected abstract Scheduler createScheduler();

    /**
     * A class to mock an Action
     * The ThreeStepActionMock takes place in 3 steps
     */
    protected class ThreeStepActionMock extends Action {

        private int step = 0;

        @Override
        protected boolean stopCondition() {
            return this.step == 3;
        }

        @Override
        protected void reallyDoStep() {
            this.step++;
        }
    }

    @Before
    public void initAction() {
        this.action = createAction();
        this.scheduler = createScheduler();
    }

    @Test
    public void testAddActionOk() throws SchedulerStartedException,
            ActionFinishedException
    {
        int nbAction = scheduler.getTheActions().size() + 1;
        scheduler.addAction(new ThreeStepActionMock());
        assertEquals(scheduler.getTheActions().size(), nbAction);
    }

    @Test(expected = SchedulerStartedException.class)
    public void testSchedulerStartedExceptionAddAction() throws SchedulerStartedException,
            ActionFinishedException
    {
        scheduler.addAction(new ThreeStepActionMock());
        scheduler.doStep();
        scheduler.addAction(new ThreeStepActionMock());
    }

    @Test(expected = ActionFinishedException.class)
    public void testActionFinishedExceptionAddAction() throws SchedulerStartedException,
            ActionFinishedException
    {
        Action subAction = new ThreeStepActionMock();
        while (! subAction.isFinished()) {
            subAction.doStep();
        }
        scheduler.addAction(subAction);
    }
    
/*
    @Test
    public void testStopCondition() throws ActionFinishedException, SchedulerStartedException {
        assertFalse(action.stopCondition());
        scheduler.addAction(new ThreeStepActionMock());
        for(int i = 0; i < 4; i++) {
            action.doStep();
        }
//        assertFalse(action.stopCondition());
        action.doStep();
        assertTrue(action.stopCondition());
    }
    */

    @Test
    public void testAllActionsFinishedWhenSchedulerFinished() throws ActionFinishedException ,
            SchedulerStartedException {
        for (int i = 0; i < 4; i++) {
            scheduler.addAction(new ThreeStepActionMock());
        }
        while (! scheduler.isFinished()) {
            scheduler.doStep();
        }
        assertTrue(scheduler.isFinished());
        for (Action a: scheduler.getTheActions()) {
            assertTrue(a.isFinished());
        }
    }

}
