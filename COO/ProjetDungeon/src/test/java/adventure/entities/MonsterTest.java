package adventure.entities;

import adventure.*;
import adventure.actions.*;
import adventure.rooms.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Monster.
 */
public class MonsterTest extends GameCharacterTest {
    // Test's Attributs
    private String description = "A test's monster";
    private Monster monster;
    private Room roomMonster;

    // Methode create GameCharacter
    public Monster createGameCharacter() {
        return new Monster(super.name, super.lifePoints, super.strengthPoints, super.gold,this.description, super.game);
    }

    
    @Before
    // Method execute before test each method
    public void setUpBefore() {
        super.setUpBefore();
        this.monster = createGameCharacter();
        this.roomMonster = super.game.getCurrentRoom();
        this.roomMonster.addMonster(monster);
    }
    
    // Test for method getDescription()
    @Test
    public void getDescriptionTest() {
        assertEquals(description,monster.getDescription());
    }

    // Test for method choiceLabel()
    @Test
    public void choiceLabelTest() {
        assertEquals(monster.getName() + " ( " + monster.getLifePoints() + " Life Points | " + monster.getStrengthPoints() + " Strength Points ) : " + this.description,monster.choiceLabel());
    }

    // Test for method die()
    @Test
    public void dieTest() {
        int nbMonster = roomMonster.getTheMonsters().size();
        int nbItem = roomMonster.getTheItems().size();
        assertTrue(monster.getLifePoints()>0);

        monster.die();
        assertTrue(monster.getLifePoints()==0);
        assertEquals(roomMonster.getTheMonsters().size(), nbMonster-1);
        assertEquals(roomMonster.getTheItems().size(), nbItem+1);
    }

}
