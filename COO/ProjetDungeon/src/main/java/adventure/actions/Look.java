package adventure.actions;

import adventure.entities.*;
import adventure.items.*;
import adventure.rooms.*;
import adventure.helpers.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * A class to model a look action in this game
 *@author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class Look implements Action
{
    /* the name of this action */
    private String name;

    /**
     * Instantiates a new look action
     */
    public Look() {
        this.name = "Look ";
    }

    public String getName() {
        return this.name;
    }

    /**
     * Indicates whether a look action is possible in a given room
     * A look action is always possible, irrespective of the room
     * @param room the Room in which action is to be made
     * @return <tt>true</tt> because it is always possible for player to Look in any Room
     */
    public boolean isPossible(Room room) {
        return true;
    }

    /**
     * Allows the execution of this action by a given player
     * @param player the player
     */
    public void execute(Player player) {
        Room currentRoom = player.getGame().getCurrentRoom();

        Display.message(player.toString());
        Display.message("Currently in: ");
        Display.message("\t"+ currentRoom.toString());

        List<Item> theItems                = currentRoom.getTheItems();
        List<Monster> theMonsters          = currentRoom.getTheMonsters();
        Map<Direction, Room> theNeighbours = currentRoom.getTheNeighbours();
        List<Direction> theDirections      = new ArrayList<Direction>(theNeighbours.keySet());

        Display.listOut("Items : ", theItems);
        Display.listOut("Monsters living here : ", theMonsters);
        Display.listOut("Doors found in Directions : ", theDirections);
    }

    /**
     * Returns a description of the action which can be chosen
     */
    public String choiceLabel() {
        return getName();
    }
}