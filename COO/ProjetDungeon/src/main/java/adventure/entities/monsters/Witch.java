package adventure.entities.monsters;

import adventure.*;
import adventure.entities.*;

/**
 * A simple class to create a Witch Monster
 */
public class Witch extends Monster {

    public Witch(AdventureGame game) {
        super("Witch", 25, 40, 15, "Witch waves a wand & casts a spell on you !", game);
    }

}