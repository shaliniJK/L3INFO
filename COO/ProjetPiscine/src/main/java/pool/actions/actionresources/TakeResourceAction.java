package pool.actions.actionresources;

import pool.ResourceUser;
import pool.actions.actionresources.AbstractResourceAction;
import pool.handlers.*;
import pool.resources.Resource;

/**
 * A simple class to model TakeResourceAction
 * A TakeResourceAction allows a ResourceUser to take a Resource from a ResourcePool
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class TakeResourceAction<R extends Resource> extends AbstractResourceAction<R>
{
    /* the status of whether this user managed to take a resource or not */
    private boolean status;

    /**
     * Instantiates a new Take Resource Action
     * @param resourcePool the pool
     * @param resourceUser the user of the resource
     * @param userName the name of the user
     */
    public TakeResourceAction(ResourcePool<R> resourcePool, ResourceUser<R> resourceUser, String userName) {
        super(resourcePool, resourceUser, userName);
        this.status = false;
    }


    /**
     * Executes the real passage of an Action from READY state to FINISHED state
     */
    public void reallyDoStep() {
        System.out.print(" " + this.userName + " trying to take resource from " + resourcePool.description() +"... ");
        try {
            R resource = this.resourcePool.provideResource();
            this.resourceUser.setResource(resource);
            this.status = true;
            System.out.println("success");
        }
        catch (NoSuchElementException e) {
            System.out.println("failed");
        }
    }


    /**
     * Indicates whether this has reached the stop condition
     * @return <tt>true</tt> if this Action should be stopped, <tt>false</tt> otherwise
     */
    public boolean stopCondition() {
        return this.status && this.resourceUser.getResource() != null;
    }


}
