package adventure.rooms;

import adventure.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * A class test for ExitRoom
 */
public class ExitRoomTest {
    private String name;
    private String description;
    private Room room;

    @Before
    public void setUpBefore() {
        this.name = "Exit Room";
        this.description =  "End game";
        this.room = new ExitRoom(name,description);
    }

    // Test method isExit()
    @Test
    public void isExitTest() {
        assertTrue(this.room.isExit());
    }
}
