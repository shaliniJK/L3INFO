package adventure.items;

import adventure.entities.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for OneArmedBandit.
 */
public class OneArmedBanditTest extends ItemTest {
    // Test's attribut
    private OneArmedBandit bandit;
    private int gold;
    private int life;
    private int strength;

    // Method create an item
    public OneArmedBandit createItem() {
        List<Item> possibleItems = new ArrayList<Item>();
        this.gold = 30;
        possibleItems.add(new GoldPurse(gold));
        this.life = 20;
        possibleItems.add(new LifePotion(life));
        this.strength = 10;
        possibleItems.add(new StrengthPotion(strength));
        return new OneArmedBandit(super.value,possibleItems);
    }

    // Method execute before test each method
    @Before
    public void setUpBefore() {
        super.setUpBefore();
        this.bandit = createItem();
    }

    // Test method use(Player player)
    @Test
    public void useTest() {
        int goldInit = player.getGold();
        int lifeInit = player.getLifePoints();
        int strengthInit = player.getStrengthPoints(); 
        int goldFinal = goldInit-super.value;
        this.bandit.use(player);
        if (goldFinal == player.getGold()) {                    // If bandit change gold to another item
            assertEquals(goldFinal,player.getGold());
            if (lifeInit != player.getLifePoints()) {           // If bandit change gold to lifePoints
                assertEquals(lifeInit+life,player.getLifePoints());
            } else if (lifeInit != player.getLifePoints()) {    // If bandit change gold to strengPoints
                assertEquals(strengthInit+strength,player.getStrengthPoints());
            }
        } else {                                                // If bandit change gold to gold
            assertEquals(goldFinal+gold,player.getGold());

        }

        
        
    }

    // Test method sufficientGold(Player player)
    @Test
    public void sufficientGoldTest() {
        assertTrue(this.bandit.sufficientGold(player));
        player.setGold(super.value-10);
        assertFalse(this.bandit.sufficientGold(player));
    }

    // Test method addItem(Item item)
    @Test
    public void addItemTest() {
        int nbItem = bandit.getPossibleItem().size();
        Item newItem = new GoldPurse(10);
        bandit.addItem(newItem);
        assertEquals(nbItem+1, bandit.getPossibleItem().size());
    }

    // Test method getPossibleItem()
    @Test
    public void getPossibleItemTest() {
        assertEquals(3,this.bandit.getPossibleItem().size());
     }

    // Test method produceRandomItem()
    @Test
    public void produceRandomItemTest() {
        List<Item> listInit = bandit.getPossibleItem();
        Item newItem = this.bandit.produceRandomItem();
        assertTrue(listInit.contains(newItem));
    }

    // Test method choiceLabel()
    @Test
    public void choiceLabelTest() { 
        assertEquals("One Armed Bandit ( requires " + super.value + " pieces of gold to be used ! )",bandit.choiceLabel());
    }
}