package adventure.items;

import adventure.entities.Player;
import adventure.Chooseable;

/**
 * A simple interface to model an Item in the dungeon game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public interface Item extends Chooseable {
    /**
     * An item can be used by a player.
     * This method allows player to use this item.
     * @param player a player
     */
    public void use(Player player);

    /**
     * Returns a description of the object which can be chosen
     */
    public String choiceLabel();
}
