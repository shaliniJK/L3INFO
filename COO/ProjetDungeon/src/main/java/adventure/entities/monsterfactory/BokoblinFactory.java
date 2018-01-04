package adventure.entities.monsterfactory;

import adventure.*;
import adventure.entities.*;
import adventure.entities.monsters.*;

/**
 * A simple factory class to create a Bokoblin monster
 *
 */
public class BokoblinFactory implements MonsterFactory
{
    /**
     * Returns a new Bokoblin Object
     * @param game the game in which Monster evolves
     * @return a new Bokoblin Object
     */
    public Monster createMonster(AdventureGame game) {
        return new Bokoblin(game);
    }
}