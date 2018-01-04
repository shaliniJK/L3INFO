package adventure;

import adventure.helpers.*;
import java.util.List;

/**
 * A class to generate the Chose
 * @author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class Chooser
{
    /**
     * Displays a choice of a list of Chooseable elements which can be chosen by typing in
     * the integer associated to an element
     * @param message the message to be displayed
     * @param list the list from which an element has to be chosen
     * @param <T> the type of the elements in the list `list` which are all Chooseable
     * @return the element chosen, null if 0 is chosen
     */
    public static <T extends Chooseable> T choose(String message, List<T> list) {
        int size;
        int i = 1;

        System.out.println(message + "\n");
        size = list.size();

		for (T element : list) {
			System.out.println(i++ + " - " + element.choiceLabel());
		}

        int index = ScannerInt.readInt(size + 1);

		if (index == 0)
		    return null;
		return list.get(--index);
    }

}