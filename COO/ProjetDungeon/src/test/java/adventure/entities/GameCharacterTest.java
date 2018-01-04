package adventure.entities;

import adventure.*;
import adventure.actions.*;
import adventure.rooms.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for GameCharacter.
 */
public abstract class GameCharacterTest {
    // Class Test's attributs
    protected String name;
    protected int lifePoints;
    protected int strengthPoints;
    protected int gold;
    protected AdventureGame game;
    protected GameCharacter character;

    // Method create a game character
    public abstract GameCharacter createGameCharacter();

    // Method execute before test each method
    @Before
    public void setUpBefore() {
        Room room = new Room("Hurricane Room", "Too windy in here");
        this.name = "Ctest";
        this.lifePoints = 50;
        this.strengthPoints = 50;
        this.gold = 50;
        this.game = new AdventureGame(room);
        this.character = createGameCharacter();
    }

    // Test method getGame()
    @Test    
    public void getGameTest() {
        assertEquals(this.game,character.getGame());
    }

    // Test method getGold()
    @Test
    public void getGoldTest() {
        assertEquals(this.gold,character.getGold());
    }

    // Test method setGold(int gold)
    @Test
    public void setGoldTest() {
        assertEquals(this.gold,character.getGold());
        character.setGold(this.gold+10);
        assertEquals(this.gold+10,character.getGold());
    }

    // Test method getLifePoints()
    @Test
    public void getLifePointsTest() {
        assertEquals(this.lifePoints,character.getLifePoints());
    }

    // Test method setLifePoints(int lifePoints)
    @Test
    public void setLifePoints() {
        assertEquals(this.lifePoints,character.getLifePoints());
        character.setLifePoints(this.lifePoints+20);
        assertEquals(this.lifePoints+20,character.getLifePoints());
    }

    // Test method getStrengthPoints()
    @Test
    public void getStrengthPointsTest() {
        assertEquals(this.strengthPoints,character.getStrengthPoints());
    }

    // Test method setStrengthPoints(int strengthPoints)
    @Test
    public void setStrengthPointsTest() {
        assertEquals(this.strengthPoints,character.getStrengthPoints());
        character.setStrengthPoints(this.strengthPoints+30);
        assertEquals(this.strengthPoints+30,character.getStrengthPoints());
    }

    // Test method increaseGold(int gold)
    @Test
    public void increaseGoldTest() {
        assertEquals(this.gold,character.getGold());
        character.increaseGold(10);
        assertEquals(this.gold+10,character.getGold());
    }

    // Test method increaseLifePoints(int lifePoints)
    @Test
    public void increaseLifePointsTest() {
        assertEquals(this.lifePoints,character.getLifePoints());
        character.increaseLifePoints(20);
        assertEquals(this.lifePoints+20,character.getLifePoints());
    }

    // Test method increaseStrengthPoints(int strengthPoints)
    @Test
    public void increaseStrengthPointsTest() {
        assertEquals(this.strengthPoints,character.getStrengthPoints());
        character.increaseStrengthPoints(30);
        assertEquals(this.strengthPoints+30,character.getStrengthPoints());
    }

    // Test method decreaseGold(int gold)
    @Test
    public void decreaseGoldTest() {
        assertEquals(this.gold,character.getGold());
        character.decreaseGold(character.getGold()/2);
        assertEquals(this.gold/2,character.getGold());

        character.decreaseGold(character.getGold()+10);
        assertEquals(0,character.getGold());
    }

    // Test method decreaseLifePoints(int lifePoints)
    @Test
    public void decreaseLifePointsTest() {
        assertEquals(this.lifePoints,character.getLifePoints());
        character.decreaseLifePoints(character.getLifePoints()/2);
        assertEquals(this.lifePoints/2,character.getLifePoints());

        character.decreaseLifePoints(character.getLifePoints()+20);
        assertEquals(0,character.getLifePoints());
    }

    // Test method decreaseStrengthPoints(int strengthPoints)
    @Test
    public void decreaseStrengthPointsTest() {
        assertEquals(this.strengthPoints,character.getStrengthPoints());
        character.decreaseStrengthPoints(character.getStrengthPoints()/2);
        assertEquals(this.strengthPoints/2,character.getStrengthPoints());

        character.decreaseStrengthPoints(character.getStrengthPoints()+30);
        assertEquals(0,character.getStrengthPoints());
    }

    // Test method isAlive()
    @Test
    public void isAliveTest() {
        assertTrue(character.isAlive());
        character.setLifePoints(0);
        assertFalse(character.isAlive());
    }

    // Test method die()
    @Test
    public void dieTest() {
        assertTrue(character.isAlive());
        character.die();
        assertFalse(character.isAlive());
    }

    // Test method getName()
    @Test
    public void getNameTest() {
        assertEquals(this.name,character.getName());
    }

    // Test method combat()
    @Test
    public void combatTest() {
        GameCharacter character2 = new GameCharacter("Character test1", character.getStrengthPoints(), 15, 10, game);
        character.combat(character2);
        assertFalse(character2.isAlive());

        int newLife = character.getStrengthPoints()*2;
        int lastLife = newLife - character.getStrengthPoints();
        character2.setLifePoints(newLife);
        character.combat(character2);
        assertTrue(character2.isAlive());
        assertEquals(lastLife,character2.getLifePoints());
        
    }
}
