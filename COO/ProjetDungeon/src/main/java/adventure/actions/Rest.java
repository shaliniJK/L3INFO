package adventure.actions;

import adventure.entities.*;
import adventure.rooms.*;

/**
 * A simple class to model a Rest action in the dungeon game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class Rest implements Action
{
    private String name;
    /**
     * Instantiates a new Rest Action
     */
    public Rest() {
        this.name = "Rest ";
    }

    public String getName() {
        return this.name;
    }

    /**
     * Allows the execution of this action by a given player
     * @param player the player
     */
    public void execute(Player player) {
        player.increaseLifePoints(2);
        player.increaseStrengthPoints(2);
//        check if player has already rested in this room
    }

    /**
     * Indicates whether an action is possible in a given room
     * @param room the Room in which action is to be made
     * @return <tt>true</tt> if a Rest action is possible in the given Room, <tt>false</tt> otherwise
     */
    public boolean isPossible(Room room) {
        return room.getTheMonsters().isEmpty();
//        can rest only if no monsters => dead monsters
    }

    /**
     * returns a description of this Action
     * @return the description of this Action
     */
    public String choiceLabel() {
        return getName();
    }


}