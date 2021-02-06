 /** 
 * An exception thrown in the RequestQueue class to indicate that
 * the value entered/given is not within the proper range.
 * @author
 *      XinCheng Chi, SBU ID#：111919385,Recition R30
 * Assignment:
 *      Homework #3 for CSE 214, Summer 2020
 * Date:
 *      July 23,2020
 */
public class EmptyListException extends Exception
{
    /**
     * Default constructor for an EmptyListException that
     * passes a default string to the Exception class constructor.
     * Postcondition:
     *    The object is created and contains the default message.
     */
    public EmptyListException()
    {
        super("List is empty");
    }
    /**
     * Second constructor for the EmptyListException that
     * passes a provided string to the Exception class constructor.
     * @param message
     *    the message that the object is to contain
     * Postcondition:
     *    The object is created and contains the provided message.
     */
    public EmptyListException(String message)
    {
        super(message);
    }
}