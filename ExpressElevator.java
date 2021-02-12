 /** 
 * An ExpressElevator object that handles the Request, it contains six member variables,
 * an int currentFloor that represent the current floor of elevator, two final int
 * IDLE,and MOVING that represents the state of the elevator, and another int 
 * elevatorState that changes according to the state of the elevator,it contains
 * an array of Request object showing the requests that is being handled, lastly,
 * it contains a boolean to show direction of the elevator
 * 
 * @author
 *      XinCheng Chi
 * Date:
 *      July 23,2020
 */
public class ExpressElevator //Extra credit
{
    //Invariant of the Elevator class
    private int currentFloor;//represent the current floor of the elevator
    public static final int IDLE = 0;//when elevator is ready to take a request
    public static final int MOVING = 1;//when elevator is on its way to pick up the passenger
    private int elevatorState;//To indicate the state of the elevator
    private Request[] info;//The request that is being handled
    private boolean dir;//true = up, false = down
    /**
     * Default constructor that sets request to null, elevatorState to IDLE, and
     * currentFloor to 1
     */
    public ExpressElevator ()
    {
        dir = true;
        
        info = null;
        
        elevatorState = IDLE;
        
        currentFloor = 1;
    }
    /**
     * Mutator method for direction
     * @param dire 
     *      the direction that will be set to
     */
    public void setDir(boolean dire)
    {
        dir = dire;
    }
    /**
     * Accessor method for direction
     * @return 
     *      The direction
     */
    boolean getDir()
    {
        return dir;
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
    Request[] getInfo()
    {
        return info;
    }
    /**
     * Mutator method for setting for information of the request
     * @param re 
     *      the information that the request will be set to
     */
    public void setInfo(Request[] re)
    {
        info = re;
    }
}
