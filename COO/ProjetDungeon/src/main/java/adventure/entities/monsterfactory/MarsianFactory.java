package adventure.entities.monsterfactory;

import adventure.*;
import adventure.entities.*;
import adventure.entities.monsters.*;

/**
 * A simple factory class to create a martian monster
 *
 */
public class MarsianFactory implements MonsterFactory
{
    /**
     * Returns a new Marsian Object
     * @param game the game in which Monster evolves
     * @return a new Marsian Object
     */
    public Monster createMonster(AdventureGame game) {
        return new Marsian(game);
    }

    /**
     * Returns a new Marsian Object
     * @param game the game in which Monster evolves
     * @param strength the strength of this martian
     * @return a new Marsian Object
     */
    public Monster createMonster(AdventureGame game, int strength) {
        return new Marsian(game, strength);
    }
}