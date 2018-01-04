package adventure.items.itemfactory;

import adventure.items.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Unit test for GoldPurseFactory.
 */
public class GoldPurseFactoryTest extends ItemFactoryTest {
    // Method create a factory of Item
    public ItemFactory createFactory() {
        return new GoldPurseFactory();
    }

    // Method create a Item
    public Item getItem() {
        return new GoldPurse(super.value);
    }   
}
