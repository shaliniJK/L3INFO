package adventure.entities.monsters;

import adventure.*;
import adventure.entities.*;

/**
 * A simple class to create a Marsian Monster
 */
public class Marsian extends Monster {

    public Marsian(AdventureGame game) {
        super("Marsian", 5, 40, 50, "Marsian Robot! Kills you with laser sword !", game);
    }

    public Marsian(AdventureGame game, int strength) {
        super("Marsian", 5, strength, 50, "Marsian Robot! Kills you with laser sword !", game);
    }

}