package pool.actions.schedulers;

import pool.actions.*;

import java.util.List;
import java.util.ArrayList;

/**
 * A simple class to model a Scheduler Action
 * A scheduler possesses a list of action. These sub-actions can be themselves schedulers.
 * The scheduler's role is to advance the execution of each of the sub-actions.
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public abstract class Scheduler extends Action
{
    /* A list of actions which this scheduler will carry out */
    protected final List<Action> theActions;



    /* Instantiates a new scheduler */
    public Scheduler() {
        this.theActions = new ArrayList<>();
    }

    /**
     * Returns the sub-actions of this Scheduler
     * @return a list of the actions of this scheduler
     */
    public List<Action> getTheActions() {
        return theActions;
    }

    /**
     * Adds a sub-action to this scheduler
     * @param subAction the action to be added to this scheduler to be executed
     * @throws ActionFinishedException this cannot add the sub-action when the scheduler is finished
     * @throws SchedulerStartedException this cannot add an already finished sub-action to the scheduler
     */
    public void addAction(Action subAction) throws ActionFinishedException, SchedulerStartedException {
        if (this.state != ActionState.READY) {
            throw new SchedulerStartedException("This Scheduler is in progress or has finished :" +
                    " cannot add new action");
        }
        if (subAction.isFinished()) {
            throw new ActionFinishedException("Cannot add an already finished action to the scheduler");
        }
        this.theActions.add(subAction);
    }


    /**
     * Indicates whether this has reached the stop condition
     * @return <tt>true</tt> if this Action should be stopped, <tt>false</tt> otherwise
     */
    public boolean stopCondition() {
        return this.state == ActionState.IN_PROGRESS && this.theActions.isEmpty();
    }

    /**
     * Executes the real passage of an Action from READY state to FINISHED state
     */
    public void reallyDoStep() {
        if (!theActions.isEmpty()) {
            Action nextAction = nextAction();
            try {
                nextAction.doStep();
            }
            catch (ActionFinishedException e) {
                System.err.println("ActionFinishedException: " + e.getMessage());
            }
            if (nextAction.isFinished()) {
                removeAction(nextAction);
            }
        }
    }

    /**
     * Take the next action in scheduler
     * @return Action the next action in scheduler
     */
    protected abstract Action nextAction();

    /**
     * Remove one of this actions in scheduler
     * @param action the action
     */
    protected abstract void removeAction(Action action);
}