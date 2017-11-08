package pool.actions;

/**
 * A simple class to model a Foreseeable Action
 * A Foreseeable Action can be executed in a predetermined number of steps which
 * can be assumed to the "time needed to execute this action".
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class ForeseeableAction extends Action
{
    /* the name of this action */
    protected String actionName;

    /* the total time needed to execute this action from READY to FINISHED */
    protected final int totalTime;

    /* the time left for this to be finished */
    protected int remainingTime;

    /**
     * Instantiates a foreseeable action
     * @param name the name of the action to be done
     * @param totalTime indicate the number of <code>doStep()</code> calls required
     */
     public ForeseeableAction(String name, int totalTime) {
        this.actionName    = name;
        this.totalTime     = totalTime;
        this.state         = ActionState.READY;
        this.remainingTime = totalTime;
    }

    /**
     * Returns the name of this action
     * @return the name of this foreseeable action
     */
    public String getActionName() {
        return actionName;
    }

    @Override
    public boolean stopCondition() {
        return this.remainingTime == 0;
    }

    @Override
    public void reallyDoStep() {
        System.out.println(" " + this.actionName + " (" + (totalTime-remainingTime+1) + "/" + totalTime + ")" );
        this.remainingTime--;
    }
}


