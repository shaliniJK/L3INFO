package adventure;

import adventure.*;
import adventure.actions.*;
import adventure.entities.*;
import adventure.entities.monsterfactory.*;
import adventure.items.*;
import adventure.items.itemfactory.*;
import adventure.rooms.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * A class test for AdventureGame
 */
public class AdventureGameTest {
    protected AdventureGame game;
    protected Player player;
    protected Room room1;
    protected Room room2;
    protected Room room3;
    protected Room room4;
    protected Monster m1;
    protected Monster m2;
    protected Monster m3;
    protected Item i1;
    protected Item i2;
    protected Item i3;
    protected Direction d1;
    protected Direction d2;
    protected Direction d3;
    protected String name;
    protected String description;
    
    @Before
    public void setUpBefore() {
        RoomFactory roomFactory = new RoomFactory();
        this.name = "Light Room";
        this.description =  "It's so bright.. am I even in a dungeon?";
        this.room1  = roomFactory.createRoom(this.name,this.description);
        this.room2  = roomFactory.createRoom("Graveyard Room", "Oops! lots of skeletons in this room");
        this.room3  = roomFactory.createRoom("Monster Party Room", "Run for your lives! Monsters everywhere!");
        this.room4  = roomFactory.createRoom("Chamber of Secrets", "OMG! snakes everywhere... & Voldemort's Nagini");

        this.game = new AdventureGame(room1);

        this.player = new Player("Link", 50, 30, 30, game);
        player.addPossibleAction(new Look());
        player.addPossibleAction(new Move());
        player.addPossibleAction(new Use());
        player.addPossibleAction(new Attack());
        
        game.addPlayer(player);

        MonsterFactory bokoFactory = new BokoblinFactory();
        MonsterFactory dragonFactory = new DragonFactory();
        MonsterFactory marsianFactory = new MarsianFactory();
        this.m1 = bokoFactory.createMonster(game);
        this.m2 = dragonFactory.createMonster(game);
        this.m3 = marsianFactory.createMonster(game);
        room1.addMonster(m1);
        room1.addMonster(m2);
        
        ItemFactory lifePointsFactory = new LifePotionFactory();
        ItemFactory strengthFactory = new StrengthPotionFactory();
        ItemFactory goldFactory = new GoldPurseFactory();
        this.i1 = lifePointsFactory.createItem(3);
        this.i2 = strengthFactory.createItem(5);
        this.i3 = goldFactory.createItem(10);
        game.addItem(i1, room1);
        game.addItem(i2, room1);

        this.d1 = Direction.EAST;
        this.d2 = Direction.UP;
        this.d3 = Direction.NORTH;
        room1.setNeighbour(d1, room2);
        room1.setNeighbour(d2, room3);
    }

    // Test Method getCurrentRoom()
    @Test
    public void getCurrentRoomTest() {
        assertEquals(room1,game.getCurrentRoom());
    }

    // Test Method getPlayer()
    @Test
    public void getPlayerTest() {
        assertEquals(player,game.getPlayer());
    }

    // Test Method addPlayer(Player player)
    @Test
    public void addPlayerTest() {
        Player player2 = new Player("Link 2", 10, 20, 40, game);
        assertEquals(player,game.getPlayer());
        game.addPlayer(player2);
        assertEquals(player2,game.getPlayer());
    }

    // Test Method play(Player player)
    @Test
    public void playTest() {
        //Test dans DungeonMain
    }

    // Test Method addMonster(Monster monster, Room room)
    @Test
    public void addMonsterTest() {
        assertFalse(room1.getTheMonsters().contains(m3));
        game.addMonster(m3,room1);
        assertTrue(room1.getTheMonsters().contains(m3));
    }

    // Test Method addItem(Item item, Room room)
    @Test
    public void addItemTest() {
        assertFalse(room1.getTheItems().contains(i3));
        game.addItem(i3,room1);
        assertTrue(room1.getTheItems().contains(i3));
    }

    // Test Method isFinished()
    @Test
    public void isFinishedTest() {
        assertFalse(game.isFinished());
        player.die();
        assertTrue(game.isFinished());
        Room room2 = new ExitRoom("Exit Room","End Game");
        AdventureGame game2 = new AdventureGame(room2);
        assertTrue(game2.isFinished());

    }

    // Test Method playerMoveTo(Direction direction)
    @Test
    public void playerMoveToTest() {
        assertEquals(room1,player.getGame().getCurrentRoom());
        game.playerMoveTo(d1);
        assertEquals(room2,player.getGame().getCurrentRoom());
    }
}
