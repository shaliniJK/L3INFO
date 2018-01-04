package adventure.actions;

import adventure.*;
import adventure.entities.*;
import adventure.rooms.*;
import adventure.items.*;
import adventure.helpers.*;
import java.util.List;

/**
 * A class to model a use action
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class Use implements Action
{
    /* the name of this action */
    private String name;

    /**
     * Instantiates a new Use action
     */
    public Use() {
        this.name = "Use ";
    }
    public String getName() {
        return this.name;
    }

    /**
     * Indicates whether a Use action is possible in a given room, at least one item in the room
     * @param room the Room in which action is to be made
     * @return <tt>true</tt> if this action is possible in the given Room, <tt>false</tt> otherwise
     */
    public boolean isPossible(Room room) {
        return ! room.getTheItems().isEmpty();
    }

    /**
     * Allows the execution of this action by a given player
     * @param player the player
     */
    public void execute(Player player) {
        AdventureGame game  = player.getGame();
        Room currentRoom    = game.getCurrentRoom();
        List<Item> theItems = currentRoom.getTheItems();

        Item item = Chooser.choose("Which item do you want to use? ", theItems);
        Display.message("You chose : " + item.choiceLabel());
        Display.displayLine();
        item.use(player);
        currentRoom.removeItem(item);
    }

    /**
     * Returns a description of the action which can be chosen
     */
    public String choiceLabel() {
        return getName();
    }

}