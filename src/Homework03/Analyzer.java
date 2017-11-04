import java.util.Scanner;

/**
 * This classes named <code>Analyzer</code> containing a main method which prompts the user, on separate lines,
 * for each of the 4 parameters required for the simulate method of the <code>Simulator</code> class.
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class Analyzer {

    //Make this true to get print statements to show the progress
    static boolean isTesting = true;


    /**
     * This method basically takes all user input and starts up the simulation.
     */
    public static void main(String[] args){
        pln("Welcome to the Elevator simulator!");pln();
        double probability = 0.0;
        int numFloors = -1;
        int numElevators = -1;
        int time = -1;
        Scanner in = new Scanner(System.in);




        boolean correct = false;
        while(!correct){
            try{
                p("Please enter the probability of arrival for Requests: ");probability = in.nextDouble();
                p("Please enter the number of floors: ");numFloors = in.nextInt();
                p("Please enter the number of elevators: ");numElevators = in.nextInt();
                p("Please enter the length of the simulation (in time units): ");time = in.nextInt();

                if( (0.0<=probability && probability <= 1.0) &&
                    (0<numElevators) &&
                    (0<time) &&
                    (1<numFloors)){
                   correct = true;
                }


            }
            catch(Exception e){
                pln("Input Error - Try Again.");
            }

            if(!correct){
                pln("Invalid input - Try Again.");
            }
        }

        //Boom
        Simulator.simulate(probability, numFloors, numElevators, time);



    }

    public static boolean isCorrectProbability(double probability){
        return (0.0<=probability && probability <= 1.0);
    }




    public static void p(String p){
        System.out.print(p);
    }

    public static void pln(){
        System.out.println();
    }

    public static void pln(String pln){
        System.out.println(pln);
    }
}
