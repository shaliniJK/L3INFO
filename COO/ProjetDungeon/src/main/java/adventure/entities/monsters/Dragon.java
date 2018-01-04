package adventure.entities.monsters;

import adventure.*;
import adventure.entities.*;
/**
 * A simple class to create a Dragon Monster
 */
public class Dragon extends Monster {

    public Dragon(AdventureGame game) {
        super("Dragon", 90, 75, 80, "Beware the Dragon! A winged beast which breathes out flames!", game);
    }

}