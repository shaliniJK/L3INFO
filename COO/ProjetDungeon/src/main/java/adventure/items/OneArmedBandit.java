package adventure.items;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import adventure.entities.Player;
import adventure.helpers.Display;
import adventure.rooms.Room;

/**
 * A class to model a OneArmedBandit item in this game
 * This item requires the player to give a certain amount of gold in exchange for another item
 * which the player can use immediately.
 * If player has insufficient gold, this item disappears without any other effect.
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class OneArmedBandit implements Item
{
    /* the amount of gold which a player should give to use this item */
    private int gold;

    /* the possible items which a OneArmedBandit can generate for the player in exchange of gold */
    private List<Item> possibleItems;

    /**
     * Instantiates a new OneArmedBandit
     * @param gold the gold needed to use this item
     * @param possibleItems the items which this OneArmedBandit can generate
     */
    public OneArmedBandit(int gold, List<Item> possibleItems) {
        this.gold          = gold;
        this.possibleItems = possibleItems;
    }

    /**
     * An item can be used by a player.
     * This method allows player to use this item.
     * @param player a player
     */
    public void use(Player player) {
        if (! this.sufficientGold(player)) {
            Display.message("You can't afford to use the One Armed Bandit with the little gold you now possess !");
            Display.message("Sorry mate! I'll have to leave you to your bad luck !");

            Room currentRoom = player.getGame().getCurrentRoom();
            currentRoom.removeItem(this);
        } else {
            player.decreaseGold(this.gold);
            Display.message("You've spent " + this.gold + " gold to pursue your fortune.. Let's see what you receive now!");
            Item randomItem = this.produceRandomItem();

            Display.message("One Armed Bandit has graced you with a " + randomItem.choiceLabel());
            Display.message("Go ahead & use your gift !");
            randomItem.use(player);
        }
    }

    /**
     * Indicates whether the player has enough gold to use this item
     * @param player the player
     * @return  <tt>true</tt> if this player has enough gold to use this item, <tt>false</tt> otherwise
     */
    public boolean sufficientGold(Player player) {
        return player.getGold() >= this.gold;
    }

    /**
     * Adds a item to the possible items which this OneArmedBandit can create
     * @param item an Item
     */
    public void addItem(Item item) {
        this.possibleItems.add(item);
    }

    /**
     * List of possible item an Item
     * @return list of possible item an Item
     */
    public List<Item> getPossibleItem() {
        return this.possibleItems;
    }

    /**
     * Returns a random item out of the list of possible items which this OneArmedBandit can create
     * @return a random item
     */
    public Item produceRandomItem() {
        Random random = new Random();
        int n         = random.nextInt(this.possibleItems.size());
        return this.possibleItems.get(n);
    }

    /**
     * Returns a description of this Item
     * @return a string description of this Item
     */
    public String choiceLabel() {
        return "One Armed Bandit ( requires " + this.gold + " pieces of gold to be used ! )";
    }

}