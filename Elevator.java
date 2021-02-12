 /** 
 * An Elevator object that handles the Request, it contains six member variables,
 * an int currentFloor that represent the current floor of elevator, three final int
 * IDLE, TO_SOURCE, TO_DESTINATION that represents the state of the elevator, and 
 * another int elevatorState that changes according to the state of the elevator,
 * lastly it contains a Request object showing the request that is being handled
 * 
 * @author
 *      XinCheng Chi
 * Date:
 *      July 23,2020
 */
public class Elevator 
{
    //Invariant of the Elevator class
    private int currentFloor;//represent the current floor of the elevator
    public static final int IDLE = 0;//when elevator is ready to take a request
    public static final int TO_SOURCE = 1;//when elevator is on its way to pick up the passenger
    public static final int TO_DESTINATION = 2;//when elevator is on its way to drop off the passenger
    private int elevatorState;//To indicate the state of the elevator
    private Request info;//The request that is being handled
    /**
     * Default constructor that sets request to null, elevatorState to IDLE, and
     * currentFloor to 1
     */
    public Elevator()
    {
        info = null;
        
        elevatorState = IDLE;
        
        currentFloor = 1;
    }
    /**
     * Accessor method for current floor
     * @return 
     *      current floor
     */
    int getCurrentFloor()
    {
        return currentFloor;
    }
    /**
     * Mutator method for currentFloor
     * @param floor 
     *      The floor that will be set as current floor
     */
    public void setCurrentFloor(int floor)
    {
        currentFloor = floor;
    }
    /**
     * Accessor method for elevator state
     * @return 
     *      elevator state
     */
    int getElevatorState()
    {
        return elevatorState;
    }
    /**
     * Mutator method for elevatorState
     * @param state 
     *      The state that will be set as elevator state
     */
    public void setElevatorState(int state)
    {
        elevatorState = state;
    }
    /**
     * Accessor method for info
     * @return 
     *      information of the request
     */
    Request getInfo()
    {
        return info;
    }
    /**
     * Mutator method for setting for information of the request
     * @param re 
     *      the information that the request will be set to
     */
    public void setInfo(Request re)
    {
        info = re;
    }
}
