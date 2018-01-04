package adventure.rooms;

import adventure.items.*;
import adventure.entities.*;
import java.util.*;

/**
 * A simple class to model a Room in the game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class Room {
    /* the rooms neighbouring this room along with their directions */
    protected Map<Direction, Room> theNeighbours;

    /* the items that this room contains */
    protected List<Item> theItems;

    /* the monsters that live in this room */
    protected List<Monster> theMonsters;

    /* the exit status of this room */
    protected boolean exit;

    /* the name of the room */
    protected String name;

    /* a simple description of the room */
    protected String description;

    public Room(String name, String description) {
        this.name          = name;
        this.description   = description;
        this.theNeighbours = new HashMap<>();
        this.theItems      = new ArrayList<>();
        this.theMonsters   = new ArrayList<>();
        this.exit          = false;
    }

    /**
     * Returns a collection of the Rooms and their directions
     * @return a map of all the neighbouring rooms along with their directions
     */
    public Map<Direction, Room> getTheNeighbours() {
        return this.theNeighbours;
    }

    /**
     * Adds a neighbour to this room, given the room and the direction in which it is found
     * Automatically adds this as neighbour to the room given in parameter with opposite direction
     * @param direction a direction
     * @param room the room found in this direction
     */
    public void setNeighbour(Direction direction, Room room) {
        this.theNeighbours.put(direction, room);
        Direction oppositeDirection = direction.oppositeDirection();
        room.getTheNeighbours().put(oppositeDirection, this);
    }

    /**
     * Returns a list of items in this room
     * @return a list of all items found in this room
     */
    public List<Item> getTheItems() {
        return this.theItems;
    }

    /**
     * Adds an item to this room
     * @param item an item
     */
    public void addItem(Item item) {
        this.theItems.add(item);
    }

    /**
     * Removes an item from this room
     * @param item the item to be removed
     */
    public void removeItem(Item item) {
        this.theItems.remove(item);
    }

    /**
     * Returns a list of all monsters living in this room
     * @return a list of all monsters living in this room
     */
    public List<Monster> getTheMonsters() {
        return this.theMonsters;
    }

    /**
     * Adds a monster to this room
     * @param monster a monster
     */
    public void addMonster(Monster monster) {
        this.theMonsters.add(monster);
    }

    /**
     * Removes a monster from this room
     * @param monster the monster to be removed
     */
    public void removeMonster(Monster monster) {
        this.theMonsters.remove(monster);
    }


    /**
     * Indicates whether this room is an exit room or not§
     * @return <tt>true</tt> if this Room is an ExitRoom, <tt>false</tt> otherwise
     */
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Returns the room found in a given direction
     * @param direction the direction in which room is found
     * @return the room found in a given direction
     * @throws InvalidDirectionException the exception when there is no neighbour room
     * in the given direction
     */
    public Room getNeighbour(Direction direction) throws InvalidDirectionException {
        if (! this.theNeighbours.containsKey(direction)) {
            throw new InvalidDirectionException("Invalid direction: no neighbour room in this direction!");
        }
        return this.theNeighbours.get(direction);
    }

    /**
     * Returns a string description of this room
     * @return a simple string description of this room
     */
    public String toString() {
        return this.name + " : " + this.description;
    }
}