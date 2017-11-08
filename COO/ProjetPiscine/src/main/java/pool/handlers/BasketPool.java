package pool.handlers;

import pool.resources.Basket;

/**
 *A simple class to model BasketPool to handle the Basket Resources
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class BasketPool extends ResourcePool<Basket>
{

    /**
     * Instantiates a new BasketPool
     * @param size the number of baskets to be handled by this Pool
     */
    public BasketPool(int size) {
        super(size);
    }

    /** Creation of a new BasketPool 
     * @return the resource of this Pool
     */
    protected Basket createResource() {
        return new Basket();
    }

    /**
     * Description of this class
     * @return the description of this class
     */
    public String description() {
        return "pool basket";
    }
}