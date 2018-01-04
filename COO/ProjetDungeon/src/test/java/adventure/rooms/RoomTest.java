package adventure.rooms;

import adventure.*;
import adventure.entities.*;
import adventure.entities.monsterfactory.*;
import adventure.items.*;
import adventure.items.itemfactory.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * A class test for Room
 */
public class RoomTest {
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

        AdventureGame game = new AdventureGame(room1);

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

    // Test Method getTheNeighbours()
    @Test
    public void getTheNeighboursTest() {
        Map<Direction, Room> mapTest = new HashMap<Direction, Room>();
        mapTest.put(d1,room2);
        mapTest.put(d2,room3);
        assertEquals(mapTest,room1.getTheNeighbours());
    }

    
    // Test Method setNeighbour(Direction direction, Room room)
    @Test
    public void setNeighbourTest() {
        room1.setNeighbour(d3, room4);
        assertEquals(room1.getTheNeighbours().get(d3),room4);
        assertEquals(room4.getTheNeighbours().get(d3.oppositeDirection()),room1);
    }

    
    // Test Method getTheItems()
    @Test
    public void getTheItemsTest() {
        List<Item> listTest = new ArrayList<Item>();
        listTest.add(i1);
        listTest.add(i2);
        assertEquals(listTest,room1.getTheItems());
    }

    // Test Method addItem(Item item)
    @Test
    public void addItemTest() {
        assertEquals(2,room1.getTheItems().size());
        assertFalse(room1.getTheItems().contains(i3));
        room1.addItem(i3);
        assertEquals(3,room1.getTheItems().size());
        assertTrue(room1.getTheItems().contains(i3));
    }

    // Test Method removeItem(Item item)
    @Test
    public void removeItemTest() {
        assertEquals(2,room1.getTheItems().size());
        assertTrue(room1.getTheItems().contains(i2));
        room1.removeItem(i2);
        assertEquals(1,room1.getTheItems().size());
        assertFalse(room1.getTheItems().contains(i2));
    }

    // Test Method getTheMonsters()
    @Test
    public void getTheMonstersTest() {
        List<Monster> listTest = new ArrayList<Monster>();
        listTest.add(m1);
        listTest.add(m2);
        assertEquals(listTest,room1.getTheMonsters());
    }

    
    // Test Method addMonster(Monster monster)
    @Test
    public void addMonsterTest() {
        assertEquals(2,room1.getTheMonsters().size());
        assertFalse(room1.getTheMonsters().contains(m3));
        room1.addMonster(m3);
        assertEquals(3,room1.getTheMonsters().size());
        assertTrue(room1.getTheMonsters().contains(m3));
    }

    
    // Test Method removeMonster(Monster monster)
    @Test
    public void removeMonsterTest() {
        assertEquals(2,room1.getTheMonsters().size());
        assertTrue(room1.getTheMonsters().contains(m2));
        room1.removeMonster(m2);
        assertEquals(1,room1.getTheMonsters().size());
        assertFalse(room1.getTheMonsters().contains(m2));
        
    }

    
    // Test Method isExit()
    @Test
    public void isExitTest() {
        assertFalse(room1.isExit());
    }

    
    // Test Method getNeighbour(Direction direction)
    @Test
    public void getNeighbourTest() throws InvalidDirectionException {
        assertTrue(room1.getTheNeighbours().containsKey(d1));
        assertTrue(room1.getTheNeighbours().containsKey(d2));
        assertEquals(room2,room1.getNeighbour(d1));
        assertEquals(room3,room1.getNeighbour(d2));
    }
    
    // Test Method getNeighbour(Direction direction) with exception
    @Test(expected=InvalidDirectionException.class)
    public void getNeighbourThrowsInvalidDirectionExceptionTest() throws InvalidDirectionException {
        assertFalse(room1.getTheNeighbours().containsKey(d3));
        room1.getNeighbour(d3);
    }

    // Test Method toString()
    @Test
    public void toStringTest() {
        assertEquals(room1.toString(),this.name + " : " + this.description);
    }
}
