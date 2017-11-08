package pool.actions.schedulers;

import pool.actions.*;

/**
 * A simple class to model a FairScheduler Action.
 * Execute the actions in parallel, if the last action is not finished yet, runs the next one anyway.
 * The next action is the non-terminated action following the current action.
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class FairScheduler extends Scheduler
{
    /* the index of the current action in progress */
    protected int currentIndex;

    public FairScheduler() {
        this.currentIndex = -1;
    }

    /**
     * Returns the next action to be processed by this scheduler
     * @return Action the next action in scheduler
     */
    public Action nextAction() {
        currentIndex++;
        if (currentIndex == this.theActions.size()) {
            currentIndex = 0;
        }
        Action action = this.theActions.get(currentIndex);
        System.out.println(action.getUserName() + "'s turn" );
        return action;
    }


    /**
     * Remove one of this actions in scheduler
     * @param action the action
     */
    protected void removeAction(Action action) {
        if (action.isFinished()) {
            this.theActions.remove(action);
            if(currentIndex==0) {currentIndex = this.theActions.size()-1;}
            else currentIndex--;
        }
    }

}