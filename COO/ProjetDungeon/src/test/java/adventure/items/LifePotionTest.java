package adventure.items;

import adventure.entities.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for LifePotion.
 */
public class LifePotionTest extends ItemTest {
    // Test's attribut
    private LifePotion life;

    // Method create an item
    public LifePotion createItem() {
        return new LifePotion(super.value);
    }

    // Method execute before test each method
    @Before
    public void setUpBefore() {
        super.setUpBefore();
        this.life = createItem();
    }

    // Test method use(Player player)
    @Test
    public void useTest() {
        int lifeInit = player.getLifePoints();
        int lifeFinal = lifeInit+super.value;
        this.life.use(player);
        assertEquals(lifeFinal,player.getLifePoints());
    }

    // Test method choiceLabel()
    @Test
    public void choiceLabelTest() { 
        assertEquals("Life potion ( increases your life by " + super.value + " points! )",life.choiceLabel());
    }
}