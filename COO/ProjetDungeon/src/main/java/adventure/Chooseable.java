package adventure;

/**
 * A simple interface for all objects which can be chosen in the dungeon game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
*/
public interface Chooseable {
    /**
    * Returns a description of the object which can be chosen
    * @return a string description of this object's label
    */
    public String choiceLabel();
}