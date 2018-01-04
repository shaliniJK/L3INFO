package adventure.items.itemfactory;

import adventure.items.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Unit test for StrengthPotionFactory.
 */
public class StrengthPotionFactoryTest extends ItemFactoryTest {
    // Method create a factory of Item
    public ItemFactory createFactory() {
        return new StrengthPotionFactory();
    }

    // Method create a Item
    public Item getItem() {
        return new StrengthPotion(super.value);
    }   
}
