package pool.actions.schedulers;

import pool.actions.*;

/**
 * A Class to model a Sequential Scheduler.
 * The next action is the previous action executed if it is not finished yet. If finished,
 * the next action is the next one in list.
 * A Sequential Scheduler finishes the action it started before moving on to the next.
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class SequentialScheduler extends Scheduler
{
    /**
     * Returns the next action to be processed by this scheduler
     * @return Action the next action in scheduler
     */
    public Action nextAction() {
        return this.theActions.get(0);
    }

    /**
     * Remove an action from this Scheduler
     * @param action the action to be removed
     */
    public void removeAction(Action action) {
        this.theActions.remove(action);
    }

}