package adventure.items;

import adventure.entities.Player;
import adventure.helpers.Display;

/**
 * A class to model a gold purse item in the dungeon game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class GoldPurse implements Item
{
    /* the number of gold pieces in this gold item */
    private int gold;

    /**
     * Instantiates a new Gold Item
     * @param gold the number of gold pieces in this gold item
     */
    public GoldPurse(int gold) {
        this.gold = gold;
    }

    /**
     * Returns the amount of gold pieces
     * @return the number of gold in this item
     */
    public int getValue() {
        return gold;
    }

    /**
     * An item can be used by a player.
     * This method allows player to use this item.
     * @param player a player
     */
    public void use(Player player) {
        player.increaseGold(this.gold);
        Display.message("Lovely! You now have " + player.getGold() + " Gold pieces in total !");
    }

    /**
     * Returns a description of this Item
     * @return a string description of this Item
     */
    public String choiceLabel() {
        return "Gold Purse ( increases your gold by " +this.gold+ " pieces! )";
    }	

}