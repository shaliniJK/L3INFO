package adventure.rooms;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * A class test for Direction
 */
public class DirectionTest {
    private Direction d1;
    private Direction d2;
    private Direction d3;
    private Direction d4;
    private Direction d5;
    private Direction d6;

    @Before
    public void setUpBefore() {
        this.d1 = Direction.NORTH;
        this.d2 = Direction.SOUTH;
        this.d3 = Direction.EAST;
        this.d4 = Direction.WEST;
        this.d5 = Direction.UP;
        this.d6 = Direction.DOWN;
    }

    // Test Method oppositeDirection()
    @Test
    public void oppositeDirectionTest() {
        assertEquals(d1,d2.oppositeDirection());
        assertEquals(d2,d1.oppositeDirection());
        assertEquals(d3,d4.oppositeDirection());
        assertEquals(d4,d3.oppositeDirection());
        assertEquals(d5,d6.oppositeDirection());
        assertEquals(d6,d5.oppositeDirection());
    }

    // Test Method getName()
    @Test
    public void getNameTest() {
        assertEquals("North",d1.getName());
        assertEquals("South",d2.getName());
        assertEquals("East",d3.getName());
        assertEquals("West",d4.getName());
        assertEquals("Up",d5.getName());
        assertEquals("Down",d6.getName());
    }

    // Test Method choiceLabel()
    @Test
    public void choiceLabelTest() {
        assertEquals("North",d1.getName());
        assertEquals("South",d2.getName());
        assertEquals("East",d3.getName());
        assertEquals("West",d4.getName());
        assertEquals("Up",d5.getName());
        assertEquals("Down",d6.getName());
    }
}
