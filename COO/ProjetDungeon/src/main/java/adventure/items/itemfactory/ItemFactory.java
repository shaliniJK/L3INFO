package adventure.items.itemfactory;

import adventure.items.*;

/**
 * A simple interface for creating an item factory items
 */
public interface ItemFactory {

    /**
     * Returns a new Item Object
     * @param value the value
     * @return a new Item Object
     */
    public Item createItem(int value);
}