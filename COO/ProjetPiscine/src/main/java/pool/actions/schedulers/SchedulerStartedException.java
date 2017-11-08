package pool.actions.schedulers;

/**
 * A class to raise a exception when an attempt is made to add an action to a scheduler which
 * has already started
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class SchedulerStartedException extends Exception
{
    private static final long serialVersionUID = 1L;

    public SchedulerStartedException(String message) {
        super(message);
    }
}
