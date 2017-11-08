package pool.handlers;

import pool.resources.Basket;

/**
 * A test for class BasketPool
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class BasketPoolTest extends ResourcePoolTest<Basket>
{
    public BasketPool createResourcePool(int size) {
        return new BasketPool(size);
    }
}