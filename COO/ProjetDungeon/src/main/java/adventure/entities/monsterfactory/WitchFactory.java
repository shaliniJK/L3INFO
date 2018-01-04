package adventure.entities.monsterfactory;

import adventure.*;
import adventure.entities.*;
import adventure.entities.monsters.*;

/**
 * A simple factory class to create a witch monster
 *
 */
public class WitchFactory implements MonsterFactory
{
    /**
     * Returns a new Monster Object
     * @param game the game in which Monster evolves
     * @return a new Monster Object
     */
    public Monster createMonster(AdventureGame game) {
        return new Witch(game);
    }
}