package adventure.items;

import adventure.entities.Player;
import adventure.helpers.Display;

/**
 * A class to model a StrengthPotion in the dungeon game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class StrengthPotion implements Item
{
    /*  the amount of strength this potions restores*/
    private int strength;

    /**
     * Instantiates a new Strength Potion
     * @param strength the value
     */
    public StrengthPotion(int strength) {
        this.strength = strength;
    }

    /**
     * Returns the amount of strength in this potion
     * @return the amount of strength this potion restores
     */
    public int getValue() {
        return strength;
    }

    /**
     * An item can be used by a player.
     * This method allows player to use this item.
     * @param player a player
     */
    public void use(Player player) {
        player.increaseStrengthPoints(this.strength);
        Display.message("Great! Your Strength Points are now " + player.getStrengthPoints() + " !");
    }

    /**
     * Returns a description of this Item
     * @return a string description of this Item
     */
    public String choiceLabel() {
        return "Strength Potion ( increases your strength by " + this.strength + " points! ) ";
    }

}