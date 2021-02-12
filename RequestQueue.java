import java.util.ArrayList;
 /** 
 * An Queue that contains up to 100 Request objects.Derived as a subclass of
 * ArrayList.
 * 
 * @author
 *      XinCheng Chi
 * Date:
 *      July 23,2020
 */
public class RequestQueue extends ArrayList
{
    //Invariant of the RequestQueue class
    public final int CAPACITY = 100;//Maximum amount of request the queue can handle
    private ArrayList<Request> DATA; //where the request is stored
    private int front;//front index of the queue
    private int rear;//rear index of the queue
    /**
     * Constructor for the RequestQueue
     */
    public RequestQueue()
    {
        front = -1;//queue is empty so set to -1
        rear = -1;//queue is empty so set to -1
        DATA = new ArrayList<Request>(CAPACITY);
    }
    /**
     * To insert a Request object into the queue
     * @param info
     *      The Request object to be inserted
     * @throws FullArrayListException 
     *      Indicates that the queue is full
     */
    public void enqueue(Request info) throws FullArrayListException
    {
        try
        {
            if(size() == CAPACITY)//check if the queue is full
                throw new FullArrayListException();//throw the exception if it is full
            else
            {
                if(isEmpty())//check if list is empty
                {
                    //if it is empty, set front and rear to index 0 and add to index 0
                    front = 0;
                    rear = 0 ;
                    this.add(rear, info);
                }
                else//if queue is not empty, advance rear index and add to rear
                {
                    rear = (rear+1) % CAPACITY;
                    this.add(rear, info);
                }
            }
        }
        catch(FullArrayListException e)
        {
            System.out.println("The queue is full");
        }
    }
    /**
     * To remove a Request object in the queue
     * @return
     *      The Request object that is being removed
     * @throws EmptyListException 
     *      Indicates that the queue is empty
     */
    public Request dequeue() throws EmptyListException
    {
        Request ret = null;
        try
        {
            if(isEmpty())//check if the queue is empty
                throw new EmptyListException();//throw the exception is it is empty
            else//if queue is not empty, remove index 0
            {
                
                ret = (Request)this.remove(0);
                if(front == rear)//if it is the last request object in queue
                {
                    //after removal, set front and rear to -1 to indicate queue is empty
                    front = -1;
                    rear = -1;
                }
                rear--;//after removal, decrease the index of rear
            }
        }
        catch(EmptyListException e)
        {
            System.out.println("The queue is empty");
        }
        return ret;
    }
    /**
     * Check if the queue is empty
     * @return 
     *      True if the queue is empty, otherwise false
     */
    /*public boolean isEmpty()
    {
        return front == -1;
    }*/
    
    /*public static void main(String[]args) throws FullArrayListException
    {
        Request te = new Request(5);
        RequestQueue q = new RequestQueue();
        
        q.enqueue(te);
       
        
        System.out.println(q.toString());
        System.out.println(q.size());
        System.out.println(q.isEmpty());
    }*/
}
