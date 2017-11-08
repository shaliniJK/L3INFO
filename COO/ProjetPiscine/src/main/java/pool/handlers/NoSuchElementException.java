package pool.handlers;

/**
 * A class NoSuchElementException which is raised an exception whenever an attempt is made to take
 * resources when no there are no free resources
 *@author Koodun Jayjaywantee, Tran Thi Ngoc Anh
 */
public class NoSuchElementException extends Exception
{
    private static final long serialVersionUID = 1L;

    public NoSuchElementException(String message) {
        super(message);
    }
}
