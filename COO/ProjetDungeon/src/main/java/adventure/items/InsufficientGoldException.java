package adventure.items;

/**
 * A class to implement an exception when a player has insufficient gold to use a
 * OneArmedBandit item
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class InsufficientGoldException extends Exception
{
    public InsufficientGoldException(String message) {
        super(message);
    }
}