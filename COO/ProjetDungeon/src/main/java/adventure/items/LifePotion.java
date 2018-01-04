package adventure.items;

import adventure.entities.Player;
import adventure.helpers.Display;

/**
 * A class to model a LifePotion item in this game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class LifePotion implements Item
{
    /* the number of lives this Life Potion can restore */
    private int life;

    /**
     * Instantiates a new Life Potion
     * @param life the number of lives this Life Potion can restore
     */
    public LifePotion(int life) {
        this.life = life;
    }

    /**
     * Returns the number of lives this Potion Item can restore
     * @return the number of lives
     */
    public int getValue() {
        return life;
    }

    /**
     * An item can be used by a player.
     * This method allows player to use this item.
     * @param player a player
     */
    public void use(Player player) {
        player.increaseLifePoints(this.life);
        Display.message("Great! Your Life Points are now " + player.getLifePoints() + " !");
    }

    /**
     * Returns a description of this Item
     * @return a string description of this Item
     */
    public String choiceLabel() {
        return "Life potion ( increases your life by " + this.life + " points! )";
    }

}