package adventure.entities.monsters;

import adventure.*;
import adventure.entities.*;
/**
 * A simple class to create a Bokoblin Monster
 */
public class Bokoblin extends Monster {

    public Bokoblin(AdventureGame game) {
        super("Bokoblin", 5, 10, 25, "Bokoblin.. a nasty goblin like creature !", game);
    }

}