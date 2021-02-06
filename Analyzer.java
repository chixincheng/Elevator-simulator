import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 /** 
 * The analyzer class will prompts the user on separate lines, for each of the 
 * four parameters required for the simulate method of the Simulator class
 * 
 * @author
 *      XinCheng Chi, SBU ID#：111919385,Recition R30
 * Assignment:
 *      Homework #3 for CSE 214, Summer 2020
 * Date:
 *      July 23,2020
 */
public class Analyzer 
{
    /**
     * The main operating method for this program.Data is obtained from the
     * user and is placed into simulate method in the Simulator class which are 
     * next operated
     * @throws IOException
     *      Indicates an error reading in from the keyboard.
     * @throws NotValidEntryException
     *      Indicate the value entered is not valid
     * @throws FullArrayListException
     *      Indicates that the queue is full
     * @throws EmptyListException 
     *      Indicates that the queue is empty   
     */
    public static void main(String[]args) throws IOException, NotValidEntryException, FullArrayListException, EmptyListException
    {
        System.out.print("Welcome to the Elevator simulator!");
        System.out.println("");
        
        BufferedReader input= new BufferedReader(new InputStreamReader(System.in));//keyboard input
        
        System.out.print("Please enter the probability of arrival for Requests: ");
        double probability = Double.parseDouble(input.readLine());//set probability to user input
        
        System.out.print("Please enter the number of floors: ");
        int numFloor = Integer.parseInt(input.readLine());//set numFloor to user input
        
        System.out.print("Please enter the number of elevators: ");
        int numElevator = Integer.parseInt(input.readLine());//set numElevator to user input
        
        System.out.print("Please enter the length of the simulation (in time units): ");
        int time = Integer.parseInt(input.readLine());//set time to user input
        
        System.out.print("Please enter 'M' to run optimal simulator, 'R' to run regular simulator: ");//extra credit
        char userseletion = Character.toUpperCase(input.readLine().charAt(0));//set userselection to user input
        
        System.out.println("");
        
        switch(userseletion)
        {
            case'R':
                Simulator.simulate(probability, numFloor, numElevator, time);//use the user inputs and execute simulate method in the Simulator class
                break;
            case'M'://extra credit
                OptimalSimulator.simulate(probability, numFloor, numElevator, time);//use the user inputs and execute simulate method in the Simulator class
                break;
        }
    }
}