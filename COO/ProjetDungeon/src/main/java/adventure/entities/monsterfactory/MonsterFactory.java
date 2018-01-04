package adventure.entities.monsterfactory;

import adventure.AdventureGame;
import adventure.entities.*;

/**
 * A simple interface for a monster creating factory
 */
public interface MonsterFactory {

    /**
     * Returns a new Monster Object
     * @param game the game in which Monster evolves
     * @return a new Monster Object
     */
    public Monster createMonster(AdventureGame game);
}