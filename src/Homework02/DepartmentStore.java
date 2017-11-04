import java.util.Scanner;

/**
 * This class manages the data structure to make it into a usable interface for any given user.
 *
 *  @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *      Recitation TA: Aynoor Saleem
 *      Recitation Section: 2
 *
 */
public class DepartmentStore {





	/**
	 * This is the main method that basically conducts all the operations and 
	 * makes the homework, well, a homework.
	 * @param args
	 * 		The arguments that can be passed with the main method in the command line.
	 */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ItemList list = new ItemList();
        boolean isRunning = true;
        pln("Welcome!~)");
        while(isRunning) {
            printMenu();
            char choice;
                try {
                choice = in.nextLine().toUpperCase().charAt(0);
                pln("---------------------------------------------------");
            } catch (Exception e) {
                pln("Invalid Input!");
                choice = 0;

            }

            //Insert Item
            if (choice == 'I') {
                try{
                    p("Enter the name: "); String name = in.nextLine();
                    p("Enter the RFID: "); String RFID = in.nextLine();
                    p("Enter the original locaiton: ");String ogLocation = in.nextLine(); ogLocation = ogLocation.toUpperCase();
                    p("Enter the price: ");double price = in.nextDouble();in.nextLine();
                    list.insertInfo(name,RFID,price,ogLocation);
                }
                catch(IllegalArgumentException IAE){
                    pln("Invalid input!");
                }
            }

            //Move item
            else if (choice == 'M') {
                try{
                    p("Enter the RFID: "); String RFID = in.nextLine();
                    p("Enter the current location: ");String ogLocation = in.nextLine();
                    p("Enter the new location: ");String newLocation = in.nextLine();

                    boolean moved = list.moveItem(RFID,ogLocation,newLocation);

                    if(moved){
                        pln(RFID+" was moved from "+ogLocation+" to "+newLocation);
                    }
                    else{
                        pln("Item not found!");
                    }
                }
                catch(IllegalArgumentException IAE){
                    pln("Invalid input!");
                }
            }

            //List by location
            else if (choice == 'L') {
                try{
                    p("Enter the current location: ");String location = in.nextLine();location=location.toUpperCase();
                    list.printByLocation(location);
                }
                catch(IllegalArgumentException IAE){
                    pln("Invalid input");
                }
            }

            //Print all items in store
            else if (choice == 'P') {
                list.printAll();
            }

            //Checkout
            else if (choice == 'O') {
                try{
                    p("Enter the cart location: ");String location = in.nextLine();
                    double total = list.checkOut(location);
                    pln("Total: $"+String.format("%.02f",total));
                }
                catch(IllegalArgumentException IAE){
                    pln("Invalid");
                }
            }

            //clean store
            else if (choice == 'C') {
                try{
                    list.cleanStore();
                }
                catch(IllegalArgumentException IAE){
                    pln("IAE with cleanstore()");
                }
            }

            //Update Inventory System
            else if (choice == 'U') {
                try{
                    list.removeAllPurchases();
                }
                catch(IllegalArgumentException IAE){
                    IAE.printStackTrace();
                    pln("IAE with update()");
                }
            }

            //Print RFID inventory system.
            else if (choice == 'R') {
                try{
                    p("Enter RFID: ");String desiredRFID = in.nextLine();desiredRFID=desiredRFID.toUpperCase();
                    list.printByRFID(desiredRFID);
                }
                catch(IllegalArgumentException IAE){

                }
            }

            //Quit
            else if (choice == 'Q') {
                pln("Good Bye!");
                isRunning = false;
            }
            else {
                pln("Wrong Input, Try Agains");
            }

            pln("---------------------------------------------------");
        }



    }


    /**
     * This is just a method that helps me keep the main method as clean as possible
     */
    public static void printMenu(){
        pln("------------------Standard Options-----------------");
        pln("I) Insert an item into the list");
        pln("M) Move an item in the store");
        pln("L) List by location");
        pln("P) Prints all items in store");
        pln("O) Checkout");
        pln("C) Clean Store");
        pln("U) Update Inventory system");
        pln("Q) Quit");
        pln("----------------Extra Credit Options---------------");
        pln("R) Print by RFID tag number");
        pln("---------------------------------------------------");
        p("Select a menu option: ");



    }

    /**
     * Helps with typing speed
     *
     * @param printThis
     */
    public static void pln(String printThis){
        System.out.println(printThis);
    }

    /**
     * Helps with typing speed
     */
    public static void pln(){
        System.out.println();
    }

    /**
     * Helps with typing speed
     *
     * @param printThis
     */
    public static void p(String printThis){
        System.out.print(printThis);
    }


}


