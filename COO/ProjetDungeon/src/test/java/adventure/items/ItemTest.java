package adventure.items;

import adventure.*;
import adventure.rooms.*;
import adventure.entities.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Item.
 */
public abstract class ItemTest {
    // Class Test's attributs
    protected Item item;
    protected Player player;
    protected int value;

    // Method create an item
    public abstract Item createItem();

    // Method execute before test each method
    @Before
    public void setUpBefore() {
        Room room = new Room("Hurricane Room", "Too windy in here");
        AdventureGame game = new AdventureGame(room);
        this.player = new Player("Ctest",this.value*2,20,30,game);
        this.value = 10;
    }

    // Test method use(Player player)
    public void useTest() {}

    // Test method choiceLabel()
    public void choiceLabelTest() { }
}