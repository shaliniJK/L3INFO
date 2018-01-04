package adventure.items;

import adventure.entities.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for StrengthPotion.
 */
public class StrengthPotionTest extends ItemTest {
    // Test's attribut
    private StrengthPotion strength;

    // Method create an item
    public StrengthPotion createItem() {
        return new StrengthPotion(super.value);
    }

    // Method execute before test each method
    @Before
    public void setUpBefore() {
        super.setUpBefore();
        this.strength = createItem();
    }

    // Test method use(Player player)
    @Test
    public void useTest() {
        int strengthInit = player.getStrengthPoints();
        int strengthFinal = strengthInit+super.value;
        this.strength.use(player);
        assertEquals(strengthFinal,player.getStrengthPoints());
    }

    // Test method choiceLabel()
    @Test
    public void choiceLabelTest() { 
        assertEquals("Strength Potion ( increases your strength by " + super.value + " points! ) ",strength.choiceLabel());
    }
}