package adventure.items.itemfactory;

import adventure.items.*;

public class LifePotionFactory implements ItemFactory {
    /**
     * Returns a new LifePotion Object
     * @return a new LifePotion Object
     */
    public Item createItem(int value) {
        return new LifePotion(value);
    }
}
