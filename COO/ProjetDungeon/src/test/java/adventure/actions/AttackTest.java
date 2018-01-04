package adventure.actions;

import adventure.*;
import adventure.entities.*;
import adventure.entities.monsters.Bokoblin;
import adventure.rooms.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Unit test for action Attack.
 */
public class AttackTest extends ActionTest
{
    protected Attack attackAction;
    protected MockObject mock;

    public Attack createAction() {
        return new Attack();
    }

    @Before
    public void setUpBefore() {
        super.setUpBefore();
        this.attackAction = createAction();
        this.mock         = new MockObject();
    }

    @Test
    public void isPossibleTestFalse_WithoutMonster() {
        assertFalse(attackAction.isPossible(mock.startRoom));
    }

    @Test
    public void isPossibleTestTrue_WithMonster() {
        mock.game.addMonster(mock.monsterBoko, mock.startRoom);
        assertTrue(attackAction.isPossible(mock.startRoom));
    }

    // @Test
    // public void executeTestMonsterDies() {
    //     int initMonsterLife = mock.monsterBoko.getLifePoints();
    //     attackAction.execute(mock.player);
    //     if (mock.monsterBoko.isAlive()) {
    //         assertTrue(initMonsterLife - mock.monsterBoko.getLifePoints() == mock.player.getStrengthPoints());
    //     }
    //     else {
    //         assertTrue(mock.game.getCurrentRoom().getTheMonsters().isEmpty());
    //     }
    // }


    @Test
    public void getNameTest() {
        assertEquals("Attack ",attackAction.getName());
    }

    @Test
    public void choiceLabelTest(){
        assertEquals("Attack ",attackAction.choiceLabel());
    }

    /**
     * A private mock class allowing us to simulate a game environment to test the Attack action
     */
    private class MockObject
    {
        public AdventureGame game;
        public Room startRoom;
        public Player player;
        public Monster monsterBoko;

        /**
         * Instantiate a new mock object
         */
        public MockObject() {
            this.startRoom   = new Room("testRoom", "Test");
            this.game        = new AdventureGame(startRoom);
            this.player      = new Player("Mockito", 100, 100, 100, game);
            this.monsterBoko = new Bokoblin(game);
        }
    }

}
