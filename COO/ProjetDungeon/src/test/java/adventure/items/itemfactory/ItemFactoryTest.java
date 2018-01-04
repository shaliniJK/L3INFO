package adventure.items.itemfactory;

import adventure.items.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for ItemFactory.
 */
public abstract class ItemFactoryTest {
    // Test's attributs
    protected ItemFactory itemFactory;
    protected Item item;
    protected int value;

    // Method create a factory of item
    public abstract ItemFactory createFactory();

    // Method create a item
    public abstract Item getItem();

    // Method execute before test each method
    @Before
    public void beforeSetUp() {
        this.value = 10;
    }

    // Methode Test for createItem
    @Test
    public void createItemTest() {
        this.itemFactory = createFactory();
        this.item = itemFactory.createItem(10);
        assertEquals(this.item.getClass(), getItem().getClass());
    }    
}
