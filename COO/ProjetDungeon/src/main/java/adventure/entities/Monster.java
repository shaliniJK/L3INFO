package adventure.entities;

import adventure.*;
import adventure.items.*;
import adventure.rooms.*;
import adventure.Chooseable;
import java.util.List;
import java.util.ArrayList;

/**
 * A class to model Monsters in the dungeon game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class Monster extends GameCharacter implements Chooseable
{
    /* A simple description of this monster */
    protected String description;

    public Monster(String name, int lifePoints, int strengthPoints, int gold, String description, AdventureGame game) {
        super(name, lifePoints, strengthPoints, gold, game);
        this.description = description;
    }

    /**
     * Returns a simple description of the monster
     * @return a simple description of the monster
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a description of the monster at time of choice
     */
    public String choiceLabel() {
        return this.getName() + " ( " + this.getLifePoints() + " Life Points | " +
                this.getStrengthPoints() + " Strength Points ) : " + this.description;
    }

    /**
     * Makes this monster die
     * When a monster dies, it leaves a purse of gold in the room and disappears
     */
    public void die() {
        super.die();
        Room currentRoom = this.getGame().getCurrentRoom();
        Item goldPurse   = new GoldPurse(this.getGold());
        currentRoom.addItem(goldPurse);
        currentRoom.removeMonster(this);
    }

}