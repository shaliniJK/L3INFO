package adventure.rooms;

/**
 * A class to model a room factory to create rooms
 */
public class RoomFactory {

    /**
     * Returns a new Room Object
     * @param name the name of room
     * @param description the description of room
     * @return a new Room Object
     */
    public Room createRoom(String name, String description) {
        return new Room(name, description);
    }
}
