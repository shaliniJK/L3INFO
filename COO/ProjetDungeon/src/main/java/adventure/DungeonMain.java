package adventure;

import adventure.entities.*;
import adventure.rooms.*;
import adventure.entities.monsterfactory.*;
import adventure.items.*;
import adventure.items.itemfactory.*;
import adventure.actions.*;
import java.util.List;
import java.util.ArrayList;

/**
 *  A class to play out the Dungeon Game in its entirety
 */
public class DungeonMain
{
    public static void main(String[] args) {

        /** Room Factory */
        RoomFactory roomFactory = new RoomFactory();

        /** Create the starting room */
        Room startingRoom = roomFactory.createRoom("Entrance Room", "Your descent into the dungeon starts from here.");

        /** Setup the game */
        AdventureGame game = new AdventureGame(startingRoom);

         /** Instantiate the player */
        Player player = new Player("Link", 50, 30, 30, game);

        /** Add the player actions */
        player.addPossibleAction(new Look());
        player.addPossibleAction(new Move());
        player.addPossibleAction(new Use());
        player.addPossibleAction(new Attack());

        /** Create the other rooms */
        Room room1  = roomFactory.createRoom("Light Room", "It's so bright.. am I even in a dungeon?");
        Room room2  = roomFactory.createRoom("Graveyard Room", "Oops! lots of skeletons in this room");
        Room room3  = roomFactory.createRoom("Monster Party Room", "Run for your lives! Monsters everywhere!");
        Room room4  = roomFactory.createRoom("Chamber of Secrets", "OMG! snakes everywhere... & Voldemort's Nagini");
        Room room5  = roomFactory.createRoom("Happy Palace", "This room has kittens & ladders");
        Room room6  = roomFactory.createRoom("Sad Room", "Sad sad place");
        Room room7  = roomFactory.createRoom("Medusa's Hall", "The abode of the snakes");
        Room room8  = roomFactory.createRoom("Anansi's Chamber", "Spiders & tricksers dance here");
        Room room9  = roomFactory.createRoom("3 Witches Room", "Wicked witches live in this room");
        Room room10 = roomFactory.createRoom("SpaceX Lab", "It's like you went to Mars and got MarsAttacked !");
        Room room11 = roomFactory.createRoom("Mighty Dragon Hall", "Flying beasts everywhere in this room");
        Room room12 = roomFactory.createRoom("Trap Room", "Can't go anywhere from here");
        Room room13 = roomFactory.createRoom("Anansi's Cave", "Dark in here");

        /** Create the exit room */
        ExitRoom exitRoom = new ExitRoom("Exit Room", "You can finally leave the dungeon! Breathe, you're safe..");


         /** Monster Factories */

         BokoblinFactory bokoFactory   = new BokoblinFactory();
         DragonFactory dragonFactory   = new DragonFactory();
         MarsianFactory marsianFactory = new MarsianFactory();
         MedusaFactory medusaFactory   = new MedusaFactory();
         WitchFactory witchFactory     = new WitchFactory();

        /* Item Factories */
        GoldPurseFactory goldFactory          = new GoldPurseFactory();
        LifePotionFactory lifeFactory         = new LifePotionFactory();
        StrengthPotionFactory strengthFactory = new StrengthPotionFactory();

        /** Handle list of possible items for One Armed Bandit */

        List<Item> possibleItems = new ArrayList<Item>();
        possibleItems.add(strengthFactory.createItem(40));
        possibleItems.add(goldFactory.createItem(40));
        possibleItems.add(lifeFactory.createItem(40));
        possibleItems.add(strengthFactory.createItem(30));
        possibleItems.add(goldFactory.createItem(30));
        possibleItems.add(lifeFactory.createItem(30));

        Item oneArmedBandit = new OneArmedBandit(35, possibleItems);

        /** Add monsters & items to the rooms */

        room1.addMonster(bokoFactory.createMonster(game));
        game.addItem(strengthFactory.createItem(5), room1);

        room2.addMonster(bokoFactory.createMonster(game));
        room2.addMonster(bokoFactory.createMonster(game));
        game.addItem(strengthFactory.createItem(5), room2);

        room3.addMonster(bokoFactory.createMonster(game));
        room3.addMonster(witchFactory.createMonster(game));
        room3.addMonster(marsianFactory.createMonster(game));
        game.addItem(strengthFactory.createItem(5), room3);
        game.addItem(lifeFactory.createItem(30), room3);

        room4.addMonster(medusaFactory.createMonster(game));
        game.addItem(oneArmedBandit, room4);
        game.addItem(strengthFactory.createItem(10), room4);


        game.addItem(oneArmedBandit, room5);
        game.addItem(lifeFactory.createItem(100), room5);
        game.addItem(goldFactory.createItem(100), room5);
        game.addItem(strengthFactory.createItem(100), room5);

        room6.addMonster(bokoFactory.createMonster(game));
        game.addItem(oneArmedBandit, room6);

        room7.addMonster(medusaFactory.createMonster(game));

        room8.addMonster(marsianFactory.createMonster(game));
        room8.addMonster(dragonFactory.createMonster(game));
        game.addItem(lifeFactory.createItem(20), room9);

        for (int i=0; i<3; i++) {
            room9.addMonster(witchFactory.createMonster(game));
        }
        game.addItem(strengthFactory.createItem(5), room9);

        for (int i=0; i<4; i++) {
            room10.addMonster(marsianFactory.createMonster(game, 500));
        }

        room11.addMonster(dragonFactory.createMonster(game));
        room11.addMonster(dragonFactory.createMonster(game));
        game.addItem(strengthFactory.createItem(100), room9);
        game.addItem(lifeFactory.createItem(100), room9);

        for (int i=0; i<3; i++) {
            room12.addMonster(marsianFactory.createMonster(game));
            room12.addMonster(witchFactory.createMonster(game));
            room12.addMonster(medusaFactory.createMonster(game));
        }

        room13.addMonster(dragonFactory.createMonster(game));
        game.addItem(goldFactory.createItem(100), room9);

        /** define the neighbours for each room */

        /** neighbours for starting room */
        startingRoom.setNeighbour(Direction.EAST, room1);
        startingRoom.setNeighbour(Direction.NORTH, room4);

        /** neighbours for other rooms */
        room1.setNeighbour(Direction.EAST, room2);
        room2.setNeighbour(Direction.NORTH,room3);
        room3.setNeighbour(Direction.NORTH, room7);
        room7.setNeighbour(Direction.EAST, room12);
        room7.setNeighbour(Direction.UP, room13);
        room4.setNeighbour(Direction.NORTH, room6);
        room4.setNeighbour(Direction.WEST, room5);
        room6.setNeighbour(Direction.EAST, room7);
        room6.setNeighbour(Direction.DOWN, room9);
        room5.setNeighbour(Direction.DOWN, room8);
        room8.setNeighbour(Direction.NORTH, room10);
        room8.setNeighbour(Direction.EAST, room9);
        room9.setNeighbour(Direction.SOUTH, room11);

        /** neighbours for exit room */
        room5.setNeighbour(Direction.WEST, exitRoom);
        room11.setNeighbour(Direction.SOUTH, exitRoom);
        room13.setNeighbour(Direction.DOWN, exitRoom);

        /** Plays the game */
        game.play(player);

    }

}