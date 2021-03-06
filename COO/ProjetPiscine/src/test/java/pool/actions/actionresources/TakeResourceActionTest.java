package pool.actions.actionresources;

import pool.ResourceUser;
import pool.handlers.ResourcePool;
import pool.mocks.ResourceMock;
import pool.resources.Resource;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A test for class TakeResourceAction
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class TakeResourceActionTest<R extends Resource> extends AbstractResourceActionTest<R> {
    @Override
    public TakeResourceAction<ResourceMock> createResourceAction(ResourcePool<ResourceMock> resourcePool, ResourceUser<ResourceMock> resourceUser, String name) {
        return new TakeResourceAction<>(resourcePool,resourceUser,name);
    }

    @Before
    public void initTest() {
        super.initTest();
        resourcePool.getResourcesAvailable().add(resource);
    }


    @Test
    public void testReallyDoStep() {
        int nb_resourceFree = resourcePool.getResourcesAvailable().size();
        int nb_resourceBusy = resourcePool.getResourcesBusy().size();
        assertNull(resourceUser.getResource());
        action.reallyDoStep();
        assertNotNull(resourceUser.getResource());
        assertEquals(nb_resourceFree-1,resourcePool.getResourcesAvailable().size());
        assertEquals(nb_resourceBusy+1,resourcePool.getResourcesBusy().size());
    }
}
