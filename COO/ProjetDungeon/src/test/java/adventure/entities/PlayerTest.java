package adventure.entities;

import adventure.*;
import adventure.actions.*;
import adventure.rooms.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Player.
 */
public class PlayerTest extends GameCharacterTest {
    // Test's attributs
    private Player player;
    private Action action1;
    private Action action2;
    private List<Action> listAction;

    // Methode create GameCharacter
    public Player createGameCharacter() {
        return new Player(super.name, super.lifePoints, super.strengthPoints, super.gold, super.game);
    }

    
    @Before
    // Method execute before test each method
    public void setUpBefore() {
        super.setUpBefore();
        this.player = createGameCharacter();
        this.action1 = new ActionMock1();
        this.action2 = new ActionMock2();
        this.listAction = new ArrayList<Action>();
        this.listAction.add(this.action1);
    }

    // Test method getPossibleActions()
    @Test
    public void getPossibleActionsTest() {
        assertEquals(0,player.getPossibleActions().size());
        player.addPossibleAction(this.action1);
        assertEquals(this.listAction,player.getPossibleActions());
    }

    // Test method addPossibleAction(Action action)
    @Test
    public void addPossibleActionTest() {
        assertEquals(0,player.getPossibleActions().size());
        player.addPossibleAction(this.action1);
        assertEquals(1,player.getPossibleActions().size());
    }

    // Test method getPossibleActionsInCurrentRoom()
    @Test
    public void getPossibleActionsInCurrentRoom() {
        assertEquals(0,player.getPossibleActionsInCurrentRoom().size());
        player.addPossibleAction(this.action1); // action possible
        player.addPossibleAction(this.action2); // action imposbile
        assertEquals(1,player.getPossibleActionsInCurrentRoom().size());
        assertEquals(this.listAction,player.getPossibleActionsInCurrentRoom());
    }

    // Test method act()
    @Test
    public void actTest() {
        
    }

    // Test method toString()
    @Test
    public void toStringTest() {
        assertEquals("Player " + player.getName() + " ( " + player.getLifePoints() + " Life Points | " + player.getStrengthPoints() + " Strength Points | "+ player.getGold() + " Gold ) ",player.toString());
    }

    /**
    * A basic Mock for Action which are possible in the game
    * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
    */
    public class ActionMock1 implements Action {
        private String name;
        /**
        * Allows the execution of this action by a given player
        * @param player the player
        */
        public void execute(Player player) {}

        /**
        * Indicates whether an action is possible in a given room
        * @param room the Room in which action is to be made
        * @return <tt>true</tt> if this action is possible in the given Room, <tt>false</tt> otherwise
        */
        public boolean isPossible(Room room) {
            return true;
        }

        /**
        * @return the name of this action
        */
        public String getName() {
            return this.name;
        }

        /**
         * Returns a description of the action which can be chosen
        * @return a description for when this action has to be chosen
         */
        public String choiceLabel() {
            return this.name;
        }

    }//Fin class ActionMock1

    /**
    * A basic Mock for Action which are possible in the game
    * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
    */
    public class ActionMock2 implements Action {
        private String name;
        /**
        * Allows the execution of this action by a given player
        * @param player the player
        */
        public void execute(Player player) {}

        /**
        * Indicates whether an action is possible in a given room
        * @param room the Room in which action is to be made
        * @return <tt>true</tt> if this action is possible in the given Room, <tt>false</tt> otherwise
        */
        public boolean isPossible(Room room) {
            return false;
        }

        /**
        * @return the name of this action
        */
        public String getName() {
            return this.name;
        }

        /**
         * Returns a description of the action which can be chosen
        * @return a description for when this action has to be chosen
         */
        public String choiceLabel() {
            return this.name;
        }

    }//Fin class ActionMock2
}
