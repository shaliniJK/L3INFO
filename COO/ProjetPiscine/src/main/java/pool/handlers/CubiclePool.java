package pool.handlers;

import pool.resources.Cubicle;

/**
 * A simple class to model CubiclePool to handle the Cubicle Resources
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class CubiclePool extends ResourcePool<Cubicle>
{
    /**
     * Instantiates a new cubicle Pool
     * @param size the number of cubicles to be handled by this pool
     */
    public CubiclePool(int size) {
        super(size);
    }

    /** Creation of a new CubiclePoll
     * @return the resource of this Pool
     */
    protected Cubicle createResource() {
        return new Cubicle();
    }

    /**
     * Description of this class
     * @return the description of this class
     */
    public String description() {
        return "pool cubicle";
    }
}