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
public class FullArrayListException extends Exception
{
    /**
     * Default constructor for an FullArrayListException that
     * passes a default string to the Exception class constructor.
     * Postcondition:
     *    The object is created and contains the default message.
     */
    public FullArrayListException()
    {
        super("List is full");
    }
    /**
     * Second constructor for the FullArrayListException that
     * passes a provided string to the Exception class constructor.
     * @param message
     *    the message that the object is to contain
     * Postcondition:
     *    The object is created and contains the provided message.
     */
    public FullArrayListException(String message)
    {
        super(message);
    }
}