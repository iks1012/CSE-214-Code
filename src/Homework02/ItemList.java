/**
 * This class responsible for managing all the nodes and the LinkedList as a whole. Operations such as getting the next and
 * the previous is possible through this class. This allows for traversing and other complex operations.
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *      Recitation TA: Aynoor Saleem
 *      Recitation Section: 2
 *
 */
public class ItemList {

    private ItemInfoNode head;
    private ItemInfoNode tail;
    private ItemInfoNode cursor;
    private int totalNodes=0;
    /**
     * This constructor returns an instance of the <code>ItemList</code> object, in which all the references are null
     */
    public ItemList(){
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     *
     * <dt>Preconditions
     *  <dd>The RFID must be a base-16 number in that all the characters must either be [a,f] (case insensitive) or [0,9]
     *  <dd>The price cant be negative
     *
     *
     * This method basically inserts a node into the LinkedList and makes sure that the lists order is preserved by placing
     * the node in the correct spot based on the RFID
     *
     *
     * @param name
     *      The name of the inserted data
     * @param rfidTag
     *      The rfid of the inserted data
     * @param price
     *      The price of the inserted data
     * @param initPosition
     *      The initial position of the inserted data
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition){
        try{
            //Create New Node
            ItemInfo t = new ItemInfo(name, price);
            t.setOriginalLocation(initPosition);
            t.setCurrentLocation(initPosition);
            t.setRfidTagNumber(rfidTag);
            ItemInfoNode newNode = new ItemInfoNode(t);

            ItemInfoNode temp = head;
            ItemInfoNode curr = head;
            //O(n) if the RFID is "FFFFFFFFF", this will guarantee it to loop to the end, or 'n' nodes.
            int numIncs = 0;
            while((temp!=null)  && (newNode.getInfo().getRfidTagNumber().compareTo(temp.getInfo().getRfidTagNumber()) > 0)){
                temp = temp.getNext();
                numIncs++;
            }
            if(temp == null){
                if(head == null && tail==null){
                    head = newNode;
                    tail = newNode;
                }
                else{
                    newNode.setPrev(tail);
                    tail.setNext(newNode);
                    tail = newNode;
                }

            }
            else if(numIncs==0){
                newNode.setNext(head);
                head.setPrev(newNode);
                head = newNode;
            }
            else{
                newNode.setPrev(temp.getPrev());
                newNode.setNext(temp);
                temp.getPrev().setNext(newNode);
                temp.setPrev(newNode);
            }
            System.out.println("The item that was added: ");
            System.out.println(newNode.toString());
            totalNodes++;
        }
        catch(IllegalArgumentException iae){
            System.out.println("The Arguments you entered for the new node is illegal (not hex or not 6 in length)");
            throw new IllegalArgumentException();
        }
    }


    /**
     * This method basically removes all the nodes that have been purchased
     */
    public void removeAllPurchases(){
        try{
            System.out.println("The following node(s) were removed: ");
            cursor = head;
            int i =0;
            //All the items can have been purchased, so all the items will be removed so O(n).
            while(cursor != null){
                if(cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")){
                    if(cursor==head){
                        head = cursor.getNext();
                        cursor = head;
                    }
                    else if(cursor == tail){
                        tail = cursor.getPrev();
                        tail.setPrev(cursor.getPrev());
                        cursor.getPrev().setNext(tail);
                    }
                    else {
                        cursor.getNext().setPrev(cursor.getPrev());
                        cursor.getPrev().setNext(cursor.getNext());
                    }


                }
                cursor = cursor.getNext();
            }



        }
        catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }






    /**
     * <dt>Precondition
     * <dd> The SRC, DEST and the RFID all must be of correct format.
     * Moves an item with a given rfidTag from src location to a dest location.
     *
     *
     * @param rfidTag
     * the rfid tag
     * @param src
     * the src location
     * @param dest
     * the dest location
     * @return
     *  Indicates whether or not an item of the given rfidTag was found at the given src location.
     * @exception IllegalArgumentException is thrown when the precondition is violated
     */
    public boolean moveItem(String rfidTag, String src, String dest){
        boolean location = false;
        boolean isValid = isValidLocation(src) && isValidLocation(dest);
        if(isValid){
            cursor = head;

            //O(n) because all the items can have the same src location and they can all be same item so keeping the same
            //RFID.
            while(cursor!=null && !location){
                if(cursor.getInfo().getCurrentLocation().equals(src) && cursor.getInfo().getRfidTagNumber().equals(rfidTag)){
                    cursor.getInfo().setCurrentLocation(dest);//here
                    location = true;
                }
                cursor = cursor.getNext();
            }
            cursor = head;
        }
        else{
            throw new IllegalArgumentException();
        }

        return location;
    }

    /**
     * this method basically checks if the given location is a valid location.
     *
     * <dt>Precondition
     *  <dd>dest cannot be null
     * @param loc
     *      The location
     * @return
     *      T/F based on if it is a valid location
     */
    public static boolean isValidLocation(String loc){
        boolean isValid = true;
        loc = loc.toUpperCase();
        if(loc.equals("out")){
            return true;
        }
        else if((loc.charAt(0)=='S' && loc.length()==6)   ||   (loc.charAt(0)=='C' && loc.length()==4)){
            //loc = "S#####" || loc = "C###"
            for(int i = 1; i<loc.length(); i++){
                if(!Character.isDigit(loc.charAt(i))){
                    return false;
                }
            }

        }
        else{
            return false;
        }



        return isValid;
    }

    /**
     * This method basically checks if the given location is a valid cart number ONLY, Shelf Values are invalid.
     *
     * <dt>Precondition
     *  <dd>dest cannot be null
     *
     * @param loc
     *      The location
     * @return
     *      T/F based on if it is a valid cart number ONLY
     */
    public static boolean isValidCartNumber(String loc){
        loc = loc.toUpperCase();
        if((loc.charAt(0)=='C' && loc.length()==4)){
            //loc = "C###"
            for(int i = 1; i<loc.length(); i++){
                if(!Character.isDigit(loc.charAt(i))){
                    return false;
                }
            }

        }
        else{
            return false;
        }
        return true;
    }


    /**
     * Makes life easy with one heading when needed.
     */
    public void printHeader(){
        System.out.println(String.format("%60s","---------------------------------------------------------------------------------"));
        System.out.println(String.format("| %-15s | %-10s | %-15s | %-15s | %-5s |","Name", "RFID Tag", "Original Location", "Current Location", "Price"));
        System.out.println(String.format("%60s","---------------------------------------------------------------------------------"));
    }

    /**
     * This method basically prints out all the nodes in the list in an orderly fashion.
     */
    public void printAll(){
        printHeader();
        cursor = head;

        //Order of Complexity = O(n) because it has to go through all the nodes to print all the nodes.
        while(cursor!=null){
            System.out.println(cursor.toString());
            cursor = cursor.getNext();
        }
    }

    /**
     * This method checks for all the items in a given location and then prints them
     * The only precondition is that the location must be a valid location or else
     * an exception is thrown
     * @param location
     *      The location
     *
     * @exception IllegalArgumentException this is the exception that thrown when the
     * Precondition is violated.
     */
    public void printByLocation(String location){
        System.out.println("These are the item(s) that were found.");
        printHeader();
        if(isValidLocation(location)){
            cursor = head;

            //Order of Complexity = O(n) because in the worst case, all the items can be in the same location, this will
            //require to go through all the nodes, hence O(n).
            while(cursor!=null){
                if(cursor.getInfo().getCurrentLocation().equals(location)){
                    System.out.println(cursor.toString());
                }
                cursor=cursor.getNext();
            }
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This basically goes through all the items in the store and places them in the correct location
     * Items that have been checked out are ignored
     */
    public void cleanStore(){
        System.out.println("These are the item(s) that were found out of place.");
        printHeader();

        cursor=head;

        //This is O(n) because it will go until the cursor is null or better phrased, the tails next node, which is null.
        //Head -> tail = O(n)
        while(cursor!=null){
            if((!cursor.getInfo().getCurrentLocation().equals(cursor.getInfo().getOriginalLocation())) && !cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")){
                System.out.println(cursor.toString());
                cursor.getInfo().setCurrentLocation(cursor.getInfo().getOriginalLocation());
            }
            cursor = cursor.getNext();
        }
    }

    /**
     * This method checks for all the items with a given RFID and then prints them
     * The only precondition is that the RFID must be a valid RFID or else
     * an exception is thrown
     * @param desiredRFID
     *      The RFID to look for
     *
     * @exception IllegalArgumentException this is the exception that thrown when the
     * Precondition is violated.
     */
    public void printByRFID(String desiredRFID){
        boolean isHex = true;
        desiredRFID=desiredRFID.toUpperCase();
        if(desiredRFID.length()==9){
            char temp;
            for(int i = 0; i<desiredRFID.length();i++){
                temp = desiredRFID.charAt(i);
                isHex&=(('0'<=temp&&temp<='9')   ||   ('A'<=temp&&temp<='F'));
            }
        }
        else{
            isHex=false;
        }

        if(isHex){
            cursor = head;
            printHeader();

            //O(n) if all the items have the SAME RFID
            while(cursor != null){
                if(cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(desiredRFID)){
                    DepartmentStore.pln(cursor.toString());
                }

                cursor = cursor.getNext();
            }
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method basically checks out all the items in the store that is in a given cart.
     * <dt>Preconditions</dt>
     * <dd>The cart Number must be a valid number</dd>
     *
     *
     * @param cartNumber
     *      The cart number
     *
     * @return
     *      The price that is the total
     *
     * @exception IllegalArgumentException is thrown if the precondition is violated.
     */
    public double checkOut(String cartNumber){
        cartNumber = cartNumber.toUpperCase();//Just so everything is uniform.
        if(isValidCartNumber(cartNumber)){
            double totalPrice = 0.0;
            System.out.println("These are the item(s) that were checked out by cart "+cartNumber+".");
            printHeader();
            cursor = head;

            //The customer could have bought every item so O(n).
            while(cursor!=null){
                if(cursor.getInfo().getCurrentLocation().equals(cartNumber)){
                    totalPrice+= cursor.getInfo().getPrice();
                    System.out.println(cursor.toString());
                    cursor.getInfo().setCurrentLocation("out");
                }
                cursor = cursor.getNext();
            }
            return totalPrice;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
