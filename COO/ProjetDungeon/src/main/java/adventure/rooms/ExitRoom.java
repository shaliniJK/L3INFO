package adventure.rooms;

/**
 * A class to model an exit room
 *@author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class ExitRoom extends Room
{
    public ExitRoom(String name, String description) {
        super(name, description);
        this.exit = true;
    }

    /**
     * Indicates whether this room is an exit room or not
     * @return <tt>true</tt> if this Room is an ExitRoom, <tt>false</tt> otherwise
     */
    public boolean isExit() {
        return this.exit;
    }
}