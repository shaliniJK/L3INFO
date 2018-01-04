package adventure.entities;

import adventure.*;
import adventure.actions.*;
import adventure.rooms.*;
import adventure.helpers.*;
import java.util.List;
import java.util.ArrayList;

/**
 * A class to model Players in the dungeon game
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 * @version 1.0
 */
public class Player extends GameCharacter
{
//    /* the maximum amount of lives possible for any player */
//    public static final int MAX_LIVES = 999;

    /* the actions which this player can execute */
    private List<Action> possibleActions;


    /**
     * Instantiates a new player
     * @param name the player's name
     * @param lifePoints the lifepoints
     * @param strengthPoints the strengthpoints
     * @param gold the gold
     * @param game the game
     */
    public Player(String name, int lifePoints, int strengthPoints, int gold, AdventureGame game) {
        super(name, lifePoints, strengthPoints, gold, game);
        this.possibleActions = new ArrayList<Action>();
    }


    /**
     * Returns the possible actions of this player
     * @return the possible actions of this player
     */
    public List<Action> getPossibleActions() {
        return possibleActions;
    }

    /**
     * Add a possible action
     * @param action an action which player can do
     */
    public void addPossibleAction(Action action) {
        this.possibleActions.add(action);
    }

    /**
     * Returns a list of all actions which player can possibly do in the current room
     * @return a list of all actions which are possible in the current room
     */
    public List<Action> getPossibleActionsInCurrentRoom() {
        List<Action> possibleActionsInCurrentRoom = new ArrayList<Action>();
        Room currentRoom = this.game.getCurrentRoom();
        for (Action action: this.possibleActions) {
            if (action.isPossible(currentRoom)) {
                possibleActionsInCurrentRoom.add(action);
            }
        }
        return possibleActionsInCurrentRoom;
    }


    /**
     * Allows a player to perform an action he chooses from a list
     * of actions possible in the current room
     */
    public void act() {
        List<Action> possibleActionsInCurrentRoom = getPossibleActionsInCurrentRoom();

        Action action = Chooser.choose("What do you want to do next? ", possibleActionsInCurrentRoom);
        Display.message("You chose : " + action.choiceLabel());
        Display.displayLine();
        action.execute(this);
    }


    public String toString() {
        return "Player " + this.getName() + " ( " + this.getLifePoints() + " Life Points | " +
                this.getStrengthPoints() + " Strength Points | "+ this.getGold() + " Gold ) ";
    }
}