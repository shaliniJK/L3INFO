package adventure.helpers;


import adventure.Chooseable;
import java.util.List;

/**
 * A simple class to display messages across the Dungeon game
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class Display {
    /**
     * Displays the given message
     * @param message the message to be displayed
     */
    public static void message(String message) {
        System.out.println(message + "\n");
    }

    public static void displayWelcome() {
        System.out.println("##################################################################################\n");
        System.out.println("                       DUNGEON GAME\n");
        System.out.println("##################################################################################\n");
        System.out.println("----------------------------------------------------------------------------------\n");
        System.out.println("Welcome brave one! Find your way out of the dungeon & you'll be remembered in glory!");
        System.out.println("Let me warn you though, you risk losing your life reckless as you are!\n");
        System.out.println("----------------------------------------------------------------------------------\n");
        System.out.println("##################################################################################\n");
    }

    public static void displayWin() {
        System.out.println("----------------------------------------------------------------------------------\n");
        System.out.println("Bravo! Look at you all alive & kicking coming from the dungeon! You totally win!");
        System.out.println("Farewell & Good Fortune to you !!!");
        System.out.println("----------------------------------------------------------------------------------\n");
    }

    public static void displayDead() {
        System.out.println("----------------------------------------------------------------------------------\n");
        System.out.println("HAHAHAHAHAHA!!! They killed you! Your bones will lie for eternity in the dungeon now!");
        System.out.println("You're dead! Game Over!");
        System.out.println("----------------------------------------------------------------------------------\n");
    }


    public static void displayLine() {
        System.out.println("----------------------------------------------------------------------------------\n");
    }

    /**
     * Prints out the elements of a list
     * @param title the title of the elements to be displayed
     * @param list a generic list
     * @param <T> a generic type which extends the Chooseable type
     */
    public static <T extends Chooseable> void listOut(String title, List<T> list) {
        if (! list.isEmpty()) {
            System.out.println(title);
            for (T element : list) {
                System.out.println("\t" + element.choiceLabel());
            }
        }
        System.out.println();
    }

}