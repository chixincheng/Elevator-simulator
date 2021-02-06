import java.text.DecimalFormat;
 /** 
 * A Simulator that will carry out the simulation of the elevator and track the
 * total request that it completes in a given time unit and also the average wait
 * time for an elevator.
 * 
 * @author
 *      XinCheng Chi, SBU ID#：111919385,Recition R30
 * Assignment:
 *      Homework #3 for CSE 214, Summer 2020
 * Date:
 *      July 23,2020
 */
public class Simulator 
{
    private static double requestcount = 0;//A counter to keep track of total request completed
    private static int totalrequest = 0;//A counter to keep track of total request received
    private static double totalwaittime;//total wait time of all passenger that completes their request
    private static double averagewaittime;//the average wait time of all passenger that completes their request
    
    /**
     * Simulation is being completed in this method
     * @param probability
     *      The probability for a request to be handled
     * @param numFloor
     *      The total number of floor of the building
     * @param numElevator
     *      The total number of elevator in the building
     * @param time
     *      The total time that the simulation will run for
     * @throws NotValidEntryException
     *      Indicate the value entered is not valid
     * @throws FullArrayListException
     *      Indicates that the queue is full
     * @throws EmptyListException 
     *      Indicates that the queue is empty
     */
    public static void simulate(double probability, int numFloor, int numElevator, int time) throws NotValidEntryException, FullArrayListException, EmptyListException
    {
        RequestQueue queue = new RequestQueue();//create a RequeQueue object
        BooleanSource execute = new BooleanSource(probability);//create a BooleanSource object
        
        Elevator[] ele = new Elevator[numElevator];//create an array to store all elevators
        for(int k=0; k<numElevator; k++)//instantiate all elevators
            ele[k] = new Elevator();
        
        int simtime = 0;//to track time
        
        for(int i=simtime; i<time; i++)//keep looping until the simulation time is up
        {
            if(execute.requestArrived())//random chance for the request to be placed
            {
                Request info = new Request(numFloor);//create a new request
                
                queue.enqueue(info);//put the request to queue
                
                info.setTimeEntered(0);//set time entered to 0 for tracking waiting time
                
                totalrequest++;//increment when a new request is received
            }
            if(!(queue.isEmpty()))//if queue is not empty
            {
                for(int j=0;j<numElevator;j++)// go through all elevator
                {
                    if(ele[j].getElevatorState() == Elevator.IDLE)//check which is IDLE
                    {
                        if(!(queue.isEmpty()))//if queue is empty do nothing, else give the request to elevator and remove it from queue
                        {
                            ele[j].setInfo(queue.dequeue());//if it is IDLE give the request

                            ele[j].setElevatorState(Elevator.TO_SOURCE);//set elevator state from IDLE to TO_SOURCE
                        }
                    }
                    else
                        ;
                }
            }
            advanceToSource(ele);//move toward source floor
            advanceToDestination(ele);//move toward destination floor
            advanceWaitTime(queue);//if a request is in queue and have not been handled, increase the wait time
        }
        DecimalFormat df = new DecimalFormat("0.00");//round it to 2 decimal place
        averagewaittime = totalwaittime/requestcount;//calculate average wait time
        
        System.out.println("Total Wait Time: "+(int)totalwaittime);
        System.out.println("Total Requests: "+totalrequest);
        System.out.println("Total Requests completed: "+(int)requestcount);
        System.out.println("Average Wait Time: "+ df.format(averagewaittime));
    }
    /**
     * Helper method to advance the elevator to source floor
     * @param e 
     *      the elevator
     */
    private static void advanceToSource(Elevator[] e)
    {
        for(int i=0; i<e.length; i++)//go through all elevator
        {
            if(e[i].getElevatorState() == Elevator.TO_SOURCE)//if elevator is moving to source
            {
                if(e[i].getInfo().getSourceFloor() == e[i].getCurrentFloor())//if elevator is at source floor
                {
                    requestcount++;//increase the count
                    totalwaittime += e[i].getInfo().getTimeEntered();//get the total wait time of this request and add to total wait time
                    e[i].setElevatorState(Elevator.TO_DESTINATION);//change state of elevator to advance to destination
                }
                else//advance to source floor
                {
                    if(e[i].getCurrentFloor() < e[i].getInfo().getSourceFloor())//if current floor is lower than source floor
                        e[i].setCurrentFloor(e[i].getCurrentFloor()+1);
                    else//if current floor is higher than source floor
                        e[i].setCurrentFloor(e[i].getCurrentFloor()-1);

                    e[i].getInfo().setTimeEntered(e[i].getInfo().getTimeEntered()+1);//increase time entered of request use it as waiting time
                }
            }
        }
    }
    /**
     * Helper method to advance the elevator to destination floor
     * @param e 
     *      the elevator
     */
    private static void advanceToDestination(Elevator[] e)
    {
        for(int i=0; i<e.length; i++)//go through all elevator
        {
            if(e[i].getElevatorState() == Elevator.TO_DESTINATION)//if elevator is moving to destination
            {
                if(e[i].getInfo().getDestinationFloor() == e[i].getCurrentFloor())//if elevator is at destination floor
                {
                    e[i].setElevatorState(Elevator.IDLE);//change state of elevator to IDLE
                }
                else//advance to destination floor
                {
                    if(e[i].getCurrentFloor() < e[i].getInfo().getDestinationFloor())//if current floor is lower than destination floor
                        e[i].setCurrentFloor(e[i].getCurrentFloor()+1);
                    else//if current floor is higher than destination floor
                        e[i].setCurrentFloor(e[i].getCurrentFloor()-1);
                }
            }
        }
    }
    /**
     * Helper method to increment the waiting time while a request is in queue
     * @param q 
     */
    private static void advanceWaitTime(RequestQueue q)
    {
        for(int i=0; i<q.size();i++)//go through all request in queue
        {
            Request mirror = (Request)q.get(i);//get the request
            mirror.setTimeEntered(mirror.getTimeEntered()+1);//increment the wait time of that request
        }
    }
}