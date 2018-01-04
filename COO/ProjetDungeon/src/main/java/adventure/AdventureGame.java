package adventure;

import adventure.entities.*;
import adventure.items.*;
import adventure.rooms.*;
import adventure.helpers.*;

/**
 * The class AdventureGame
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class AdventureGame
{
    /* The current room in the game*/
    private Room currentRoom;

    /* The player */
    private Player player;

    /**
     * Instantiates a new game
     * @param startingRoom the Room from which the game is started
     */
    public AdventureGame(Room startingRoom) {
        this.currentRoom = startingRoom;
    }

    /**
     * Returns the current room in which player is found
     * @return the current room
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Returns the player
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Add the player
     * @param the player
     */
    public void addPlayer(Player player) {
        this.player=player;
    }

    /**
     * Plays the game until its end
     * @param player the player
     */
    public void play(Player player) {
        this.addPlayer(player);
        Display.displayWelcome();
        Display.message("You're now in : " + this.currentRoom.toString());
        while (!this.isFinished()) {
            player.act();
        }
        if (player.isAlive())
            Display.displayWin();
        else
            Display.displayDead();
    }

    /**
     * Adds a monster in a room
     * @param monster a monster
     * @param room a room in the game
     */
    public void addMonster(Monster monster, Room room) {
        room.addMonster(monster);
    }

    /**
     * Adds an item in a room
     * @param item an Item
     * @param room a room in the game
     */
    public void addItem(Item item, Room room) {
        room.addItem(item);
    }

    /**
     * Indicates whether the game is over or not
     * @return <tt>true</tt> if game is finished <tt>false</tt> otherwise
     */
    public boolean isFinished() {
        return this.currentRoom.isExit() || ! this.player.isAlive();
    }

    /**
     * Moves a player in the given direction
     * @param direction a direction in the game
     */
    public void playerMoveTo(Direction direction) {
        try {
            this.currentRoom = currentRoom.getNeighbour(direction);
        }
        catch (InvalidDirectionException e) {
            System.out.println(e.getMessage());
        }
    }
}