package pool.mocks;

import pool.handlers.ResourcePool;

/**
 * An class Mock extends ResourcePool
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class ResourcePoolMock extends ResourcePool<ResourceMock>
{
    /**
     * Instantiates a new ResourcePoolMock
     * @param size the number of baskets to be handled by this Pool
     */
    public ResourcePoolMock(int size) {
        super(size);
    }

    @Override
    protected ResourceMock createResource() {
        return new ResourceMock();
    }

    @Override
    public String description() {
        return "pool mock";
    }
}
