package pool.actions;

/**
 * A class to raise a exception when a FINISHED action is executed again
 * @author Koodun Jayjaywantee, Tran Thi-Ngoc-Anh
 */
public class ActionFinishedException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ActionFinishedException(String message) {
        super(message);
    }
}
