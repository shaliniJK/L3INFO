package adventure.actions;

import adventure.*;
import adventure.entities.*;
import adventure.rooms.*;
import adventure.helpers.*;
import java.util.List;

/**
 * A class to model an attack action
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class Attack implements Action
{
    /* the name of this action */
    private String name;

    /**
     * Instantiates a new attack action
     */
    public Attack() {
        this.name = "Attack ";
    }

    public String getName() {
        return this.name;
    }

    /**
     * Indicates whether an attack action is possible in a given room
     * @param room the Room in which action is to be made
     * @return <tt>true</tt> if this action is possible in the given Room, <tt>false</tt> otherwise
     */
    public boolean isPossible(Room room) {
        return ! room.getTheMonsters().isEmpty();
    }

    /**
     * Allows the execution of this action by a given player
     * @param player the player
     */
    public void execute(Player player) {
        AdventureGame game = player.getGame();
        Room currentRoom   = game.getCurrentRoom();

        List<Monster> theMonsters = currentRoom.getTheMonsters();
        Monster monster = Chooser.choose("Which monster do you want to fight? ", theMonsters);
        Display.displayLine();
        Display.message("You attacked " + monster.getName());

        player.combat(monster);
        if (monster.isAlive()) {
            Display.message("... looks like he didn't die yet ! Hit him harder the next time!");
            Display.message(monster.getName() + " fights back & you lose " + monster.getStrengthPoints()
                + " life points.");
            monster.combat(player);
        } else {
            Display.message("Yesss! " + monster.getName() + " is dead. Look, He left a gold purse!");
            // monster.die(); // monster dies already in combat
            currentRoom.removeMonster(monster);
        }
    }

    /**
     * Returns a description of the action which can be chosen
     */
    public String choiceLabel() {
        return getName();
    }

}