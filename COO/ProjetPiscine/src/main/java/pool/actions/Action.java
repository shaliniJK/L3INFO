package pool.actions;

/**
 * An simple class to model an Action
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public abstract class Action
{
    /* the state of this action */
    protected ActionState state;

    /* The name of user*/
    protected String userName;

    /**
     * Instantiates a new Action
     */
    public Action() {
        this.state = ActionState.READY;
    }

    /**
     * Returns the state of this action
     * @return the current state of this Action
     */
    public ActionState getState() {
        return this.state;
    }

    /**
     * Indicates whether this has finished
     * @return <tt>true</tt> if this Action is completed, <tt>false</tt> otherwise
     */
    public boolean isFinished() {
        return this.state == ActionState.FINISHED;
    }

    /**
     * Executes the passage of this from READY state to FINISHED state
     * @throws ActionFinishedException this cannot doStep if this has already reached FINISHED state
     */
    public void doStep() throws ActionFinishedException {
        if (this.isFinished()) {
            throw new ActionFinishedException("Action is finished: cannot do step again!");
        }
        this.state = ActionState.IN_PROGRESS;
        reallyDoStep();
        if (this.stopCondition()) {
            this.state = ActionState.FINISHED;
        }
    }

    /**
     * Indicates whether this has reached the stop condition
     * @return <tt>true</tt> if this Action should be stopped, <tt>false</tt> otherwise
     */
    protected abstract boolean stopCondition();

    /**
     * Executes the real passage of an Action from READY state to FINISHED state
     */
    protected abstract void reallyDoStep();

    public String getUserName() {
        return userName;
    }
}
