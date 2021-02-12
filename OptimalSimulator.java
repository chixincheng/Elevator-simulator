import java.text.DecimalFormat;
 /** 
 * A OptimalSimulator that will carry out the simulation of the elevator and track the
 * total request that it completes in a given time unit and also the average wait
 * time for an elevator.
 * 
 * @author
 *      XinCheng Chi
 * Date:
 *      July 23,2020
 */
public class OptimalSimulator //Extra credit
{
    private static double requestcount = 0;//A counter to keep track of total request completed
    private static int totalrequest = 0;//A counter to keep track of total request received
    private static double totalwaittime;//total wait time of all passenger that completes their request
    private static double averagewaittime;//the average wait time of all passenger that completes their request
    
    /**
     * Optimal Simulation is being completed in this method
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
        
        ExpressElevator[] ele = new ExpressElevator[numElevator];//create an array to store all elevators
        for(int k=0; k<numElevator; k++)//instantiate all elevators
            ele[k] = new ExpressElevator();
        
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
                            Request[] storeinfo = new Request[queue.size()];
                            
                            //set up the direction of elevator to the direction of the first request in queue
                            Request temp = queue.dequeue();
                            if(temp.getSourceFloor() > ele[j].getCurrentFloor())
                            {
                                ele[j].setDir(true);
                            }
                            else if(temp.getSourceFloor() < ele[j].getCurrentFloor())
                            {
                                ele[j].setDir(false);
                            }
                            queue.enqueue(temp);
                            
                            //store all the request that is going to the same direction as the first
                            //passanger
                            for(int n=0; n< queue.size()+1;n++)
                            {
                                temp = queue.dequeue();
                                //only pick up the passengers that want to go in the direction of the elevator
                                if(ele[j].getDir() && temp.getSourceFloor() >= ele[j].getCurrentFloor() && temp.getDestinationFloor() >= temp.getSourceFloor())
                                {
                                    storeinfo[n] = temp;
                                }
                                else if(!(ele[j].getDir()) && ele[j].getCurrentFloor() >= temp.getSourceFloor() && temp.getSourceFloor() >= temp.getDestinationFloor())
                                {
                                    storeinfo[n] = temp;
                                }
                                else
                                {
                                    queue.enqueue(temp);
                                }
                            }
                            ele[j].setInfo(storeinfo);//store all the request infomation to the elevator
                            ele[j].setElevatorState(ExpressElevator.MOVING);//set elevator state from IDLE to MOVING
                        }
                    }
                    else
                        ;
                }
            }
            advanceUpOrDown(ele);//move an elevator up or down depends on its direction
            waitTimeInQueue(queue);//if a request is in queue and have not been handled, increase the wait time
        }
        DecimalFormat df = new DecimalFormat("0.00");//round it to 2 decimal place
        averagewaittime = totalwaittime/requestcount;//calculate average wait time
        
        System.out.println("Total Wait Time: "+(int)totalwaittime);
        System.out.println("Total Requests: "+totalrequest);
        System.out.println("Total Requests completed: "+(int)requestcount);
        System.out.println("Average Wait Time: "+ df.format(averagewaittime));
    }
    /**
     * Helper method to move the elevator up or down depend on direction
     * @param e 
     *      the elevator
     */
    private static void advanceUpOrDown(ExpressElevator[] e)
    {
        int count=0;//count
        for(int i=0; i<e.length; i++)//go through all elevator
        {
            Request[] info = e[i].getInfo();//all request information a elevator is about to handle
            //System.out.println(i+" "+Arrays.toString(info));
            if(e[i].getElevatorState() == ExpressElevator.MOVING)//when elevator is not in IDLE
            {
               // System.out.println(i+" "+Arrays.toString(info));
                for(int j=0; j<info.length; j++)//go through information of all passenger in the elevator
                {
                    if(info[j] != null)
                    {
                        if(e[i].getCurrentFloor() == info[j].getSourceFloor())//if a source floor is reached
                        {
                            requestcount++;//increment count
                            totalwaittime += info[j].getTimeEntered();//get the waiting time
                        }
                        if(e[i].getCurrentFloor() == info[j].getDestinationFloor())//if a destination floor is reached
                        {
                            count++;//increase count
                            if(count == e[i].getInfo().length)//when all destination floor is reach, set elevator to IDLE
                            {
                                e[i].setElevatorState(ExpressElevator.IDLE);
                            }
                        }
                    }
                }
                if(e[i].getDir())//if getDir is true, elevator is moving up
                {
                    e[i].setCurrentFloor(e[i].getCurrentFloor()+1);
                    timeWaitProcessing(info);
                }
                else//if getDir is false, elevator is moving down
                {
                    e[i].setCurrentFloor(e[i].getCurrentFloor()-1);
                    timeWaitProcessing(info);
                }
            }
        }
    }
    /**
     * Helper method to increment the waiting time when waiting to be picked up
     * @param inf 
     */
    private static void timeWaitProcessing(Request[] inf)
    {
        for(int i=0;i<inf.length;i++)
        {
            if(inf[i] != null)
            {
                inf[i].setTimeEntered(inf[i].getTimeEntered()+1);
            }
        }
    }
    /**
     * Helper method to increment the waiting time while a request is in queue
     * @param q 
     */
    private static void waitTimeInQueue(RequestQueue q)
    {
        for(int i=0; i<q.size();i++)//go through all request in queue
        {
            Request mirror = (Request)q.get(i);//get the request and shallow copy it
            mirror.setTimeEntered(mirror.getTimeEntered()+1);//increment the wait time of that request
        }
    }
}
