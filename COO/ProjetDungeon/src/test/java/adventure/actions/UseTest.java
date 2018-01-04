package adventure.actions;

import adventure.*;
import adventure.entities.*;
import adventure.items.*;
import adventure.rooms.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for action Use.
 */
public class UseTest extends ActionTest {

    protected Use useAction;
    protected MockObject mock;

    public Use createAction() {
        return new Use();
    }

    @Before
    public void setUpBefore() {
        super.setUpBefore();
        this.useAction = createAction();
        this.mock      = new MockObject();
    }

    @Test
    public void isPossibleTestFalse_WithoutItem() {
        assertFalse(useAction.isPossible(mock.startRoom));
    }

    @Test
    public void isPossibleTestTrue_WithItem() {
        mock.game.addItem(mock.goldPurse, mock.startRoom);
        assertTrue(useAction.isPossible(mock.startRoom));
    }

    // @Test
    // public void executeTest() {
    //     int initGold = mock.player.getGold();
    //     useAction.execute(mock.player);
    //     assertTrue(mock.player.getGold() - initGold == mock.goldPurse.getValue());
    //     assertTrue(mock.game.getCurrentRoom().getTheItems().isEmpty());
    // }

    @Test
    public void getNameTest() {
        assertEquals("Use ", useAction.getName());
    }

    @Test
    public void choiceLabelTest(){
        assertEquals("Use ", useAction.choiceLabel());
    }

    /**
     * A private mock class allowing us to simulate a game environment to test the Use action
     */
    private class MockObject
    {
        public AdventureGame game;
        public Room startRoom;
        public Player player;
        public GoldPurse goldPurse;

        /**
         * Instantiate a new mock object
         */
        public MockObject() {
            this.startRoom = new Room("testRoom", "Test");
            this.game      = new AdventureGame(startRoom);
            this.player    = new Player("Mockito", 100, 100, 100, game);
            this.goldPurse = new GoldPurse(25);
        }
    }

}
