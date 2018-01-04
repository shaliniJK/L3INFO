package adventure.items.itemfactory;

import adventure.items.*;

public class StrengthPotionFactory implements ItemFactory {
    /**
     * Returns a new StrengthPotion Object
     * @return a new StrengthPotion Object
     */
    public Item createItem(int value) {
        return new StrengthPotion(value);
    }
}
