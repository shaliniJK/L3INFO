package pool.actions;

/**
 * A Class Enum for Action
 * READY : initial state,
 * IN_PROGRESS : action not completed yet,
 * FINISHED : final state, action is complete
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public enum ActionState
{
    READY, IN_PROGRESS, FINISHED;
}