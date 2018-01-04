package adventure.entities.monsterfactory;

import adventure.*;
import adventure.rooms.*;
import adventure.entities.*;
import adventure.entities.monsters.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for WitchMonsterFactory.
 */
public class WitchFactoryTest extends MonsterFactoryTest {
    // Method create a factory of monster
    public MonsterFactory createFactory() {
        return new WitchFactory();
    }

    // Method create a monster
    public Monster getMonster() {
        return new Witch(super.game);
    }   
}
