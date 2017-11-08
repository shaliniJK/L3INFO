package pool;

import pool.mocks.ResourceMock;
import pool.resources.Resource;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A Test for ResourceUser
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class ResourceUserTest<R extends Resource> {

    private ResourceUser<ResourceMock> resourceUser;
    private ResourceMock resource;

    @Before
    public void initTest() {
        resourceUser = new ResourceUser<>();
        resource = new ResourceMock();
    }
    
    @Test
    public void testGetSetResource() {
        resourceUser.setResource(resource);
        assertEquals(resource,resourceUser.getResource());
    }
    
    @Test
    public void testResetResource() {
        resourceUser.setResource(resource);
        assertNotNull(resourceUser.getResource());
        resourceUser.resetResource();
        assertNull(resourceUser.getResource());
    }

}