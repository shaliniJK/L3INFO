package adventure.items.itemfactory;

import adventure.items.*;

public class GoldPurseFactory implements ItemFactory {
    /**
     * Returns a new GoldPurse Object
     * @return a new GoldPurse Object
     */
    public Item createItem(int value) {
        return new GoldPurse(value);
    }
}
