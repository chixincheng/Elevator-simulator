 /** 
 * An BooleanSource object which contains the probability for a request to be handled
 * have one member variable probability which is a double.
 * 
 * @author
 *      XinCheng Chi, SBU ID#：111919385,Recition R30
 * Assignment:
 *      Homework #3 for CSE 214, Summer 2020
 * Date:
 *      July 23,2020
 */
public class BooleanSource 
{
    //Invariant of the BooleanSource class
    private double probability;//the probability for a request to be handled
    /**
     * Constructor of the BooleanSource object that accepts a double as a parameter
     * as the value of the member variable probability.
     * @param prob
     *      A double between 0 and 1 exclusive
     * @throws NotValidEntryException 
     *      Indicate the value entered is not valid
     */
    public BooleanSource(double prob) throws NotValidEntryException
    {
        try
        {
            if(prob < 0 || prob >1)//check if the value entered is valid
                throw new NotValidEntryException();//if it is not, throw the exception
            else//if it is, set the member variable to that value
                probability = prob;
        }
        catch(NotValidEntryException e)
        {
            System.out.println("Not a valid entry for probability");
        }
    }
    /**
     * Determine whether or no a request will be handled
     * @return 
     *      True a percentage of the time equal to probability, otherwise, false
     */
    public boolean requestArrived()
    {
        //create another double within the range of 0 and 1 exclusive
        //and compare it with probability
        double compare = Math.random();
        return compare < probability;
    }
}
