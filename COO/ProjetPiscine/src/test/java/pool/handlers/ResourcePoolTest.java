package pool.handlers;

import pool.resources.*;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * An Test for class ResourcePool
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public abstract class ResourcePoolTest<R extends Resource> {
    protected ResourcePool<R> resourcePool;

    protected abstract ResourcePool<R> createResourcePool(int size);

    @Test
    public void testProvideResourceSuccess() throws NoSuchElementException {
        resourcePool = createResourcePool(5);
        int nb_resourceA = resourcePool.resourcesAvailable.size();
        int nb_resourceB = resourcePool.resourcesBusy.size();
        assertNotNull(resourcePool.provideResource());
        assertEquals(nb_resourceA-1,resourcePool.resourcesAvailable.size());
        assertEquals(nb_resourceB+1,resourcePool.resourcesBusy.size());
    }

    @Test (expected=NoSuchElementException.class)
    public void testProvideResourceFail() throws NoSuchElementException {
        resourcePool = createResourcePool(0);
        resourcePool.provideResource();
    }

    @Test
    public void testRecoverResourceSuccess() throws IllegalArgumentException,NoSuchElementException {
        resourcePool = createResourcePool(5);
        R resource = resourcePool.provideResource();
        int nb_resourceA = resourcePool.resourcesAvailable.size();
        int nb_resourceB = resourcePool.resourcesBusy.size();

        resourcePool.recoverResource(resource);
        assertEquals(nb_resourceA+1,resourcePool.resourcesAvailable.size());
        assertEquals(nb_resourceB-1,resourcePool.resourcesBusy.size());
    }

    @Test (expected=IllegalArgumentException.class)
    public void testRecoverResourceFail() throws IllegalArgumentException,NoSuchElementException {
        resourcePool = createResourcePool(5);
        ResourcePool<R> resourcePool2 = createResourcePool(4);
        R resource = resourcePool2.provideResource();
        resourcePool.recoverResource(resource);
    }
}
