package pool.handlers;

import pool.resources.Cubicle;

/**
 * A test for class CubiclePool
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class CubiclePoolTest extends ResourcePoolTest<Cubicle>
{
    public CubiclePool createResourcePool(int size) {
        return new CubiclePool(size);
    }
}