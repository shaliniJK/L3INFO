package adventure.entities.monsterfactory;

import adventure.*;
import adventure.entities.*;
import adventure.entities.monsters.*;

/**
 * A simple factory class to create a medusa monster
 *
 */
public class MedusaFactory implements MonsterFactory
{
    /**
     * Returns a new Medusa Object
     * @param game the game in which Monster evolves
     * @return a new Medusa Object
     */
    public Monster createMonster(AdventureGame game) {
        return new Medusa(game);
    }
}