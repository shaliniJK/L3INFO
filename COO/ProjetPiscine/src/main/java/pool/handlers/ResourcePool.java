package pool.handlers;

import pool.resources.*;

import java.util.List;
import java.util.ArrayList;

/**
 * An abstract class ResourcePool to handle resources
 * A ResourcePool provides resources from its pool (being informed if no resources are
 * available), and it recovers resources to add it back to the pool, making it available.
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public abstract class ResourcePool<R extends Resource>
{
    /* number of resources handled by this */
    protected int size;

    /* List of available resources */
    protected List<R> resourcesAvailable;

    /* List of busy resources */
    protected List<R> resourcesBusy;

    /** Instantiates a new ResourcePool
     * @param size number of resources which this pool will handle
     */
    public ResourcePool(int size) {
        this.size = size;
        this.resourcesAvailable = new ArrayList<R>();
        this.resourcesBusy      = new ArrayList<R>();
        for (int i=0; i<size; i++) {
            this.resourcesAvailable.add(createResource());
        }
    }
    
    /** Creates an instance of the resource handled by this pool
     * @return a resource to be handled by this Pool
     */
    protected abstract R createResource();

    /**
     * Returns a resource from this pool, if there is any resource available
     * @return the resource of this Pool
     * @exception NoSuchElementException  when there are no resources available
     */ 
    public R provideResource() throws NoSuchElementException {
        if (this.resourcesAvailable.isEmpty()) {
            throw new NoSuchElementException("There are no resources available");
        }
        R resource = resourcesAvailable.remove(0);
        resourcesBusy.add(resource);
        return resource;
    }

    /**
     * Recovers a resource if it's free, and adds it back to this pool, makes it available again
     * @param resource the resource to be returned to the pool, must have been obtained from this
     * very pool
     * @exception IllegalArgumentException when the returned resource was not obtained from this
     */ 
    public void recoverResource(R resource) throws IllegalArgumentException {
        if (this.resourcesBusy.contains(resource)) {
            this.resourcesBusy.remove(resource);
            this.resourcesAvailable.add(resource);
        } else {
            throw new IllegalArgumentException("Resource was not obtained from this pool");
        }
    }

    /**
     * Description of this class
     * @return the description of this class
     */
    public abstract String description();

    
    /**
     * Return a list of resourcesAvailable 
     * @return list of resourcesAvailable
     */
    public List<R> getResourcesAvailable() {
        return this.resourcesAvailable;
    }

    /**
     * Return a list of resourcesBusy 
     * @return list of resourcesBusy
     */
    public List<R> getResourcesBusy() {
        return this.resourcesBusy;
    }
}
