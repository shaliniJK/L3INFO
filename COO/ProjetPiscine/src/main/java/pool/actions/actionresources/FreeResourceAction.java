package pool.actions.actionresources;

import pool.ResourceUser;
import pool.actions.actionresources.AbstractResourceAction;
import pool.handlers.*;
import pool.resources.Resource;

/**
 * A simple class to model FreeResourceAction
 * A FreeResourceAction allows a ResourceUser to return a Resource to a ResourcePool
 * making it available again.
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class FreeResourceAction<R extends Resource> extends AbstractResourceAction<R>
{

    /**
     * Instantiates a new Free Resource Action
     * @param resourcePool the pool
     * @param resourceUser the user of the resource
     * @param userName the name of the user
     */
    public FreeResourceAction(ResourcePool<R> resourcePool, ResourceUser<R> resourceUser, String userName) {
        super(resourcePool, resourceUser, userName);
    }

    /**
     * Executes the real passage of an Action from READY state to FINISHED state
     */
    public void reallyDoStep() {
        System.out.println(" " + this.userName + " freeing resource from " + resourcePool.description());
        R resource = this.resourceUser.getResource();
        try {
            this.resourcePool.recoverResource(resource);
            this.resourceUser.resetResource();
        }
        catch (IllegalArgumentException e) {
            System.err.println("IllegalArgumentException:" + e.getMessage());
        }
    }


    /**
     * Indicates whether this has reached the stop condition
     * @return <tt>true</tt> if this Action should be stopped, <tt>false</tt> otherwise
     */
    public boolean stopCondition() {
        return resourceUser.getResource() == null;
    }

}
