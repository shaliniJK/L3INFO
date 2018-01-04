package adventure.rooms;

/**
 * A class to raise an exception when an Invalid Direction is encountered in this game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class InvalidDirectionException extends Exception
{
    public InvalidDirectionException(String message) {
        super(message);
    }
}
