package adventure.entities.monsterfactory;

import adventure.*;
import adventure.entities.*;
import adventure.entities.monsters.*;

/**
 * A simple factory class to create a Dragon monster
 *
 */
public class DragonFactory implements MonsterFactory
{
    /**
     * Returns a new Dragon Object
     * @param game the game in which Monster evolves
     * @return a new Dragon Object
     */
    public Monster createMonster(AdventureGame game) {
        return new Dragon(game);
    }
}