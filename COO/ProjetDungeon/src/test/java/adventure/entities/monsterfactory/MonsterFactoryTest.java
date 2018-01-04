package adventure.entities.monsterfactory;

import adventure.*;
import adventure.rooms.*;
import adventure.entities.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for MonsterFactory.
 */
public abstract class MonsterFactoryTest {
    // Test's attributs
    protected AdventureGame game;
    protected MonsterFactory monsterFactory;
    protected Monster monster;

    // Method create a factory of monster
    public abstract MonsterFactory createFactory();

    // Method create a monster
    public abstract Monster getMonster();

    // Method execute before test each method
    @Before
    public void setUpBefore() {
        Room room = new Room("Hurricane Room", "Too windy in here");
        this.game = new AdventureGame(room);
    }

    // Methode Test for createMonster
    @Test
    public void createMonsterTest() {
        this.monsterFactory = createFactory();
        this.monster = monsterFactory.createMonster(this.game);
        assertEquals(this.monster.getClass(), getMonster().getClass());
    }


    
}
