package pool;

import pool.resources.*;

/**
 * A ResourceUser the user of resource
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class ResourceUser<R extends Resource>
{
    /* A resource to use*/
    protected R resource;

    /**
     * Getter of a resource used by User
     * @return the resource of this user
     */
    public R getResource() {
        return resource;
    }
    
    /**
     *  Setter of a resource to the User
     * @param resource the resource to be used by this user
     */
    public void setResource(R resource) {
        this.resource = resource;
    }
    
    /**
     * Resets a resource to null for User
     */
    public void resetResource() {
        this.resource = null;
    }

}