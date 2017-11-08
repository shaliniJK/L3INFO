package pool.actions.actionresources;

import pool.actions.ActionTest;
import pool.ResourceUser;
import pool.handlers.ResourcePool;
import pool.mocks.*;
import pool.resources.Resource;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A test for class AbstractResourceAction
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public abstract class AbstractResourceActionTest<R extends Resource> extends ActionTest {
    protected ResourcePool<ResourceMock> resourcePool;
    protected ResourceUser<ResourceMock> resourceUser;
    protected AbstractResourceAction<ResourceMock> action;
    protected ResourceMock resource;

    /**
     * Instantiates the action
     * @param resourcePool a resource from pool
     * @param resourceUser a user of resource
     * @param name name of user
     * @return the AbstractResourceAction to be tested
     */
    protected abstract AbstractResourceAction<ResourceMock> createResourceAction(ResourcePool<ResourceMock> resourcePool, ResourceUser<ResourceMock> resourceUser, String name);

    @Override
    public AbstractResourceAction<? extends Resource> createAction() {
        initTest();
        return action;
    }

    @Before
    public void initTest() {
        resourcePool = new ResourcePoolMock(5);
        resourceUser = new ResourceUser<ResourceMock>();
        resource = new ResourceMock();
        action = createResourceAction(resourcePool,resourceUser,"Test");
    }

    @Test
    public void testStopCondition() {
        assertFalse(action.stopCondition());
        action.reallyDoStep();
        assertTrue(action.stopCondition());
    }
}
