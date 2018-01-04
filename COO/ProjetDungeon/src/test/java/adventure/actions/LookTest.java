package adventure.actions;

import adventure.*;
import adventure.entities.*;
import adventure.rooms.*;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for action Look.
 */
public class LookTest extends ActionTest {

    protected Look lookAction;
    protected MockObject mock;

    /**
     * @return a Look action
     */
    public Look createAction() {
        return new Look();
    }

    @Before
    public void setUpBefore() {
        super.setUpBefore();
        this.lookAction = createAction();
        this.mock       = new MockObject();
    }

    @Test
    public void isPossibleTest() {
        assertTrue(action.isPossible(mock.room));
    }

    // @Test
    // public void executeTestNotFailing() {
    //     lookAction.execute(mock.player);
    // }

    @Test
    public void getNameTest() {
        assertEquals("Look ", lookAction.getName());
    }

    @Test
    public void choiceLabelTest(){
        assertEquals("Look ", lookAction.choiceLabel());
    }

    /**
     * A private mock class allowing us to simulate a game environment to test the Look action
     */
    private class MockObject {
        public AdventureGame game;
        public Room room;
        public Player player;

        /**
         * Instantiate a new mock object
         */
        public MockObject() {
            this.room = new Room("TestRoom", "Test");
            this.game = new AdventureGame(room);
            this.player = new Player("Mockito", 1, 1, 1, game);
        }
    }

}
