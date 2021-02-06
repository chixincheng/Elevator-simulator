 /** 
 * An Request object which contains information about a request for an elevator,
 * it have three member variables for the sourceFloor, destinationFloor, and
 * timeEntered, all three member variables are ints.
 * 
 * @author
 *      XinCheng Chi, SBU ID#：111919385,Recition R30
 * Assignment:
 *      Homework #3 for CSE 214, Summer 2020
 * Date:
 *      July 23,2020
 */
public class Request 
{
    //Invariant of the Request class
    private int sourceFloor;//The floor where the passenger at
    private int destinationFloor;//The floor where the passenger wants to go
    private int timeEntered;//The time when the passenger is being picked up
    /**
     * Constructor that takes number of floor in the building as parameter
     * @param numFloor 
     *      Total number of floor in the building
     */
    public Request(int numFloor)
    {
        sourceFloor = (int) (Math.random()*numFloor+1);
        
        destinationFloor = (int) (Math.random()*numFloor+1);
    }
    /**
     * Accessor method for source floor
     * @return 
     *      source floor
     */
    int getSourceFloor()
    {
        return sourceFloor;
    }
    /**
     * Accessor method for destination floor
     * @return 
     *      destination floor
     */
    int getDestinationFloor()
    {
        return destinationFloor;
    }
    /**
     * Accessor method for time entered
     * @return 
     *      time entered
     */
    int getTimeEntered()
    {
        return timeEntered;
    }
    /**
     * Mutator method for source floor
     * @param floor 
     *      The floor that will be set as source floor
     */
    public void setSourceFloor(int floor)
    {
        sourceFloor = floor;
    }
    /**
     * Mutator method for destination floor
     * @param floor 
     *      The floor that will be set as destination floor
     */
    public void setDestinationFloor(int floor)
    {
        destinationFloor = floor;
    }
    /**
     * Mutator method for time entered
     * @param time 
     *      The time that will be set as time entered
     */
    public void setTimeEntered(int time)
    {
        timeEntered = time;
    }
}