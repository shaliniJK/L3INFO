package adventure.items;

import adventure.entities.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for GoldPurse.
 */
public class GoldPurseTest extends ItemTest {
    // Test's attribut
    private GoldPurse gold;

    // Method create an item
    public GoldPurse createItem() {
        return new GoldPurse(super.value);
    }

    // Method execute before test each method
    @Before
    public void setUpBefore() {
        super.setUpBefore();
        this.gold = createItem();
    }

    // Test method use(Player player)
    @Test
    public void useTest() {
        int goldInit = player.getGold();
        int goldFinal = goldInit+super.value;
        this.gold.use(player);
        assertEquals(goldFinal,player.getGold());
    }

    // Test method choiceLabel()
    @Test
    public void choiceLabelTest() { 
        assertEquals("Gold Purse ( increases your gold by " +super.value+ " pieces! )",gold.choiceLabel());
    }
}