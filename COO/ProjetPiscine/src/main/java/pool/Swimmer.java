package pool;

import pool.actions.*;
import pool.handlers.*;
import pool.actions.actionresources.*;
import pool.actions.schedulers.*;
import pool.resources.*;
/**
 * A class Swimmer 
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class Swimmer extends SequentialScheduler
{
    /* name of swimmer */
    private String name;

    /* Handler of basket */
    private BasketPool basketPool;

    /* Handler of cubicle */
    private CubiclePool cubiclePool;

    /* Undress duration*/
    private int undressDuration;

    /* Swim duration*/
    private int swimDuration;

    /* Dress duration*/
    private int dressDuration;

    /** Instantiates a new Swimmer
     * @param name name of swimmer
     * @param basketPool handler of basket
     * @param cubiclePool handler of cubicle
     * @param undressDuration undress duration
     * @param swimDuration swim duration
     * @param dressDuration dress duration
     */
    public Swimmer(String name, BasketPool basketPool, CubiclePool cubiclePool, int undressDuration, int swimDuration, int dressDuration) {
        this.name            = name;
        this.basketPool      = basketPool;
        this.cubiclePool     = cubiclePool;
        this.undressDuration = undressDuration;
        this.swimDuration    = swimDuration;
        this.dressDuration   = dressDuration;
        this.initAction();
    }

    /** Initiation the swimmer
     * A swimmer will   1. take a basket in resource pool
     *                  2. take a cubicle in resource pool
     *                  3. undress
     *                  4. free a cubicle in resource pool
     *                  5. swim
     *                  6. take a cubicle in resource pool
     *                  7. dress
     *                  8. free a cubicle in resource pool
     *                  9. free a basket in resource pool
     */
    public void initAction() {
        ResourceUser<Basket> basketResourceUser   = new ResourceUser<Basket>();
        ResourceUser<Cubicle> cubicleResourceUser = new ResourceUser<Cubicle>();

        try {
        this.addAction(new TakeResourceAction<>(getBasketPool(), basketResourceUser, getUserName()));
        this.addAction(new TakeResourceAction<>(getCubiclePool(), cubicleResourceUser, getUserName()));
        this.addAction(new ForeseeableAction("undressing", getUndressDuration()));
        this.addAction(new FreeResourceAction<>(getCubiclePool(), cubicleResourceUser, getUserName()));
        this.addAction(new ForeseeableAction("swimming", getSwimDuration()));
        this.addAction(new TakeResourceAction<>(getCubiclePool(), cubicleResourceUser, getUserName()));
        this.addAction(new ForeseeableAction("dressing", getDressDuration()));
        this.addAction(new FreeResourceAction<>(getCubiclePool(), cubicleResourceUser, getUserName()));
        this.addAction(new FreeResourceAction<>(getBasketPool(), basketResourceUser, getUserName()));
        }
        catch (ActionFinishedException e) {
            System.err.println("ActionFinishedException:" + e.getMessage());
        }
        catch (SchedulerStartedException e) {
            System.err.println("SchedulerStartedException:" + e.getMessage());
        }
    }

    /**
     * Get the name of swimmer
     * @return name of swimmer
     */
    public String getUserName() {
        return this.name;
    }


    /**
     * Set the name to swimmer
     * @param name name of swimmer
     */
    public void setName(String name) {
        this.name=name;
    }


    /**
     * Get the basket's handler of swimmer
     * @return basket's handler of swimmer
     */
    public BasketPool getBasketPool() {
        return this.basketPool;
    }


    /**
     * Set the basket's handler of swimmer
     * @param basketPool basket's handler of swimmer
     */
    public void setBasketPool(BasketPool basketPool) {
        this.basketPool=basketPool;
    }


    /**
     * Get the cubicle's handler of swimmer
     * @return cubicle's handler of swimmer
     */
    public CubiclePool getCubiclePool() {
        return this.cubiclePool;
    }


    /**
     * Set the cubicle's handler of swimmer
     * @param cubiclePool cubicle's handler of swimmer
     */
    public void setCubiclePool(CubiclePool cubiclePool) {
        this.cubiclePool=cubiclePool;
    }

    /**
     * Get the duration to undress of swimmer
     * @return duration to undress of swimmer
     */
    public int getUndressDuration() {
        return this.undressDuration;
    }

    
    /**
     * set the duration to undress of swimmer
     * @param duration duration to undress of swimmer
     */
    public void setUndressDuration(int duration) {
        this.undressDuration=duration;
    }


    /**
     * Get the duration to swim of swimmer
     * @return duration to swim of swimmer
     */
    public int getSwimDuration() {
        return this.swimDuration;
    }


    /**
     * set the duration to swim of swimmer
     * @param duration duration to swim of swimmer
     */
    public void setSwimDuration(int duration) {
        this.swimDuration = duration;
    }


    /**
     * Get the duration to dress of swimmer
     * @return duration to dress of swimmer
     */
    public int getDressDuration() {
        return this.dressDuration;
    }

    /**
     * set the duration to dress of swimmer
     * @param duration duration to dress of swimmer
     */
    public void setDressDuration(int duration) {
        this.dressDuration=duration;
    }


    

}