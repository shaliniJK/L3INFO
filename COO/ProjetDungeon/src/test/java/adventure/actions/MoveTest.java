package adventure.actions;

import adventure.*;
import adventure.actions.*;
import adventure.entities.*;
import adventure.entities.monsters.Dragon;
import adventure.rooms.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for action Move.
 */
public class MoveTest extends ActionTest
{

    protected Move moveAction;
    protected MockObject mock;

    public Move createAction() {
        return new Move();
    }

    @Before
    public void setUpBefore() {
        super.setUpBefore();
        this.moveAction = createAction();
        this.mock       = new MockObject();
    }

    @Test
    public void isPossibleTestTrue_WithoutMonster() {
        assertTrue(moveAction.isPossible(mock.startRoom));
    }

    @Test
    public void isPossibleTestFalse_WithMonster() {
        mock.game.addMonster(mock.monster, mock.startRoom);
        assertFalse(moveAction.isPossible(mock.startRoom));
    }

    // @Test
    // public void executeTest() {
    //     mock.startRoom.removeMonster(mock.monster);
    //     mock.startRoom.setNeighbour(Direction.EAST, mock.nextRoom);
    //     moveAction.execute(mock.player);
    //     assertSame(mock.game.getCurrentRoom(), mock.nextRoom);
    // }

    @Test
    public void getNameTest() {
        assertEquals("Move", moveAction.getName());
    }

    @Test
    public void choiceLabelTest(){
        assertEquals("Move", moveAction.choiceLabel());
    }

    /**
     * A private mock class allowing us to simulate a game environment to test the Move action
     */
    private class MockObject
    {
        public AdventureGame game;
        public Room startRoom;
        public Room nextRoom;
        public Player player;
        public Monster monster;

        /**
         * Instantiate a new mock object
         */
        public MockObject() {
            this.startRoom = new Room("StartRoom", "Test");
            this.nextRoom  = new Room("testRoom", "Test");
            this.game      = new AdventureGame(startRoom);
            this.player    = new Player("Mockito", 100, 100, 100, game);
            this.monster   = new Dragon(game);
        }
    }

}
