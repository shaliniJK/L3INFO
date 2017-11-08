package pool.actions.actionresources;

import pool.ResourceUser;
import pool.actions.Action;
import pool.actions.ActionState;
import pool.handlers.ResourcePool;
import pool.resources.Resource;


/**
 * A simple class to model an AbstractResourceAction which deals with
 * a resource pool
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public abstract class AbstractResourceAction<R extends Resource> extends Action
{
    /* A resource pool to handle a particular resource */
    protected ResourcePool<R> resourcePool;

    /* The use of the resource */
    protected ResourceUser<R> resourceUser;

    /**
     * Instantiates a new abstract resource action
     * @param resourcePool the resource handler for a particular resource
     * @param resourceUser the user of the resource
     * @param userName name of user
     */
    public AbstractResourceAction(ResourcePool<R> resourcePool, ResourceUser<R> resourceUser, String userName) {
        this.resourcePool = resourcePool;
        this.resourceUser = resourceUser;
        this.userName     = userName;
        this.state        = ActionState.READY;
    }

    /**
     * Indicates whether this has reached the stop condition
     * @return <tt>true</tt> if this Action should be stopped, <tt>false</tt> otherwise
     */
    public abstract boolean stopCondition();

    /**
     * Executes the real passage of an Action from READY state to FINISHED state
     */
    public abstract void reallyDoStep();
}