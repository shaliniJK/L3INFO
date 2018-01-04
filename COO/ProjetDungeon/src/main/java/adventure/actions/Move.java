package adventure.actions;

import adventure.*;
import adventure.entities.*;
import adventure.rooms.*;
import adventure.helpers.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * A class to model a move action
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class Move implements Action
{
    /* the name of this action */
    private String name;

    /**
     * Instantiates a new Move Action
     */
    public Move() {
        this.name = "Move";
    }

    public String getName() {
        return this.name;
    }

    /**
     * Indicates whether a move action is possible in a given room
     * @param room the Room in which action is to be made
     * @return <tt>true</tt> if this action is possible in the given Room, <tt>false</tt> otherwise
     */
    public boolean isPossible(Room room) {
        return room.getTheMonsters().isEmpty();
    }

    /**
     * Allows the execution of this action by a given player
     * @param player the player
     */
    public void execute(Player player) {
        AdventureGame game = player.getGame();
        Room currentRoom   = game.getCurrentRoom();

        Map<Direction, Room> theNeighbours = currentRoom.getTheNeighbours();
        List<Direction> theDirections      = new ArrayList<Direction>(theNeighbours.keySet());

        Direction direction = Chooser.choose("In which direction do you want to move? ", theDirections);
        Display.message("You chose : " + direction.choiceLabel());
        Display.displayLine();

        try {
            Room neighbour = currentRoom.getNeighbour(direction);
            Display.message("You're now in : " + neighbour.toString());
        } catch (InvalidDirectionException e) {
            Display.message(e.getMessage());
        }

        game.playerMoveTo(direction);
    }

    /**
     * Returns a description of the action which can be chosen
     */
    public String choiceLabel() {
        return getName();
    }
}

