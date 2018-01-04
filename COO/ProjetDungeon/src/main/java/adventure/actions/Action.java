package adventure.actions;

import adventure.*;
import adventure.entities.Player;
import adventure.rooms.Room;

/**
 * A basic interface for all Actions which are possible in the game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public interface Action extends Chooseable {
    /**
     * Allows the execution of this action by a given player
     * @param player the player
     */
    public void execute(Player player);

    /**
     * Indicates whether an action is possible in a given room
     * @param room the Room in which action is to be made
     * @return <tt>true</tt> if this action is possible in the given Room, <tt>false</tt> otherwise
     */
    public boolean isPossible(Room room);

    /**
     * @return the name of this action
     */
    public String getName();

    /**
     * Returns a description of the action which can be chosen
     * @return a description for when this action has to be chosen
     */
    public String choiceLabel();

}
