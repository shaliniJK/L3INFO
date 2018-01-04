package adventure.rooms;

import adventure.Chooseable;

/**
 * An ENUM to model direction in the dungeon game
 * A player can only go in 6 directions: North, South, East, West, Up and Down
 * This allows a dungeon to have several levels where player can go upstairs or downstairs
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public enum Direction implements Chooseable {
    NORTH("North"), SOUTH("South"), EAST("East"), WEST("West"), UP("Up"), DOWN("Down");

    /* the name of this direction */
    private String name;

    /**
     * Instantiates a new Direction enum
     * @param name the name of this Direction
     */
    Direction(String name) {
        this.name = name;
    }

    /**
     * @return a direction opposite to this direction
     */
    public Direction oppositeDirection() {
        switch(this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                return null;
        }
    }

    /**
     * returns the name of this Direction
     * @return the name of this Direction
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a description of the object which can be chosen
     * @return returns a description
     */
    public String choiceLabel() {
        return this.getName();
    }

}