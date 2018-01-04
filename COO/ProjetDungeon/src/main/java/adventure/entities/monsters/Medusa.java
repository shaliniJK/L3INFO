package adventure.entities.monsters;

import adventure.*;
import adventure.entities.*;

/**
 * A simple class to create a Medusa Monster
 */
public class Medusa extends Monster {

    public Medusa(AdventureGame game) {
        super("Medusa", 50, 20, 20, "Medusa's head is filled with snakes ! Don't look in its eyes", game);
    }

}