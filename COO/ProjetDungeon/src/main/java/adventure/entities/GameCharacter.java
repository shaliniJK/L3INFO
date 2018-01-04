package adventure.entities;

import adventure.*;

/**
 * A class to model characters in the dungeon game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class GameCharacter
{
    /* the name of this character*/
    protected String name;

    /* the life points that a character has */
    protected int lifePoints;

    /* the strength points that a character has */
    protected int strengthPoints;

    /* the gold pieces that a character carries */
    protected int gold;

    /* the current game in which this character evolves */
    protected AdventureGame game;

    /**
     * Instantiates a new character in the game
     * @param name the name of this character
     * @param lifePoints the life point of this character
     * @param strengthPoints the strength points of this character
     * @param gold the number of gold pieces carried by this character
     * @param game the game in which the character evolves
     */
    public GameCharacter(String name, int lifePoints, int strengthPoints, int gold, AdventureGame game) {
        this.name           = name;
        this.lifePoints     = lifePoints;
        this.strengthPoints = strengthPoints;
        this.gold           = gold;
        this.game           = game;
    }

    /**
     * Returns the game in which character is found
     * @return the game in which this character is found
     */
    public AdventureGame getGame() {
        return game;
    }

    /**
     * Returns the amount of gold pieces carried
     * @return the amount of gold carried
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * Sets the gold carried by character to the amount given
     * @param gold the number of gold pieces
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * Returns the lifepoints of this character
     * @return the lifepoints
     */
    public int getLifePoints() {
        return this.lifePoints;
    }

    /**
     * Sets the life points of this character to the amount given
     * @param lifePoints the life points of this character
     */
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    /**
     * Returns the strength points of this character
     * @return the strength points of this character
     */
    public int getStrengthPoints() {
        return this.strengthPoints;
    }

    /**
     * Sets the strength of this character to the amount given
     * @param strengthPoints the strength points
     */
    public void setStrengthPoints(int strengthPoints) {
        this.strengthPoints = strengthPoints;
    }

    /**
     * Increases the gold of this character by amount of gold pieces given
     * @param gold the number of pieces of gold by which it is increased
     */
    public void increaseGold(int gold) {
        this.setGold(this.gold + gold);
    }

    /**
     * Increases the strength of this character by given amount of strength
     * @param strengthPoints the amount of strength points
     */
    public void increaseStrengthPoints(int strengthPoints) {
        this.setStrengthPoints(this.strengthPoints + strengthPoints);
    }

    /**
     * Increase the life of this character by given amount
     * @param lifePoints the amount of life points
     */
    public void increaseLifePoints(int lifePoints) {
        this.setLifePoints(this.lifePoints + lifePoints);
    }

    /**
     * Decreases the gold of this character by amount of gold pieces given
     * @param gold the number of pieces of gold by which it is decreased
     */
    public void decreaseGold(int gold) {
        if (gold >= this.gold) {
            this.setGold(0);
        } else {
            this.setGold(this.gold - gold);
        }
    }

    /**
     * Decreases the lifePoints of this character by amount of life points given
     * @param lifePoints the number of points by which life is to be decreased
     */
    public void decreaseLifePoints(int lifePoints) {
        if (lifePoints >= this.lifePoints) {
            this.setLifePoints(0);
        } else {
            this.setLifePoints(this.lifePoints - lifePoints);
        }
    }

    /**
     * Decreases the strengthPoints of this character by amount of strength points given
     * @param strengthPoints the number of points by which strength is to be decreased
     */
    public void decreaseStrengthPoints(int strengthPoints) {
        if (strengthPoints >= this.strengthPoints) {
            this.setStrengthPoints(0);
        } else {
            this.setStrengthPoints(this.strengthPoints - strengthPoints);
        }
    }

    /**
     * Indicates whether character is dead or alive
     * @return <tt>true</tt> if this character is still alive, <tt>false</tt> otherwise
     */
    public boolean isAlive() {
        return this.lifePoints > 0;
    }

    /**
     * Makes this character die
     */
    public void die() {
        this.setLifePoints(0);
    }

    /**
     * Returns the name of this character
     * @return the name of this Character
     */
    public String getName() {
        return this.name;
    }

    /**
     * Makes this character combat with another character
     * @param gameCharacter the character who is to be fought
     */
    public void combat(GameCharacter gameCharacter) {
        int damage = this.strengthPoints;
        if (damage >= gameCharacter.getLifePoints()) {
            gameCharacter.die();
        } else {
            gameCharacter.decreaseLifePoints(damage);
        }
    }

}
