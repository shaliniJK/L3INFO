package adventure.entities.monsterfactory;

import adventure.*;
import adventure.rooms.*;
import adventure.entities.*;
import adventure.entities.monsters.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for DragonMonsterFactory.
 */
public class DragonFactoryTest extends MonsterFactoryTest {
    // Method create a factory of monster
    public MonsterFactory createFactory() {
        return new DragonFactory();
    }

    // Method create a monster
    public Monster getMonster() {
        return new Dragon(super.game);
    }   
}
