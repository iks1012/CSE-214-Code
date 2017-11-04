/**
 * This class is responsible for managing the <code>ItemInfo</code> object that stores all the information for a given
 * item
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *      Recitation TA: Aynoor Saleem
 *      Recitation Section: 2
 *      
 *
 */
public class ItemInfo {
    private String name;
    private double price;

    private String rfidTagNumber;
    private String originalLocation;
    private String currentLocation;

    /**
     * This method returns an instance of the <code>ItemInfo</code> with the parameters that are passed through as the properties
     * 
     * @param tempName
     * 		The name that will be assigned
     * 
     * @param tempPrice
     * 		The name that will be assigned
     * 
     */
    public ItemInfo(String tempName, double tempPrice){
        if(price >= 0.0){
            name = tempName;
            price = tempPrice;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method returns the <code>name</code> of the <code>ItemInfo</code>
     * 
     * @return
     * 		The name of the <code>ItemInfo</code>
     */
    public String getName() {
        return name;
    }

    /**
     * This method assigns the <code>name</code> of the <code>ItemInfo</code>
     * 
     * @param name
     * 		The name of the <code>ItemInfo</code>
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the <code>price</code> of the <code>ItemInfo</code>
     * 
     * @return
     * 		The price of the <code>ItemInfo</code> that is returned
     */
    public double getPrice() {
        return price;
    }

	/**
	 * This method assigns the price of the <code>ItemInfo</code> to the parameter that is passed through
	 * 
	 * @param price
	 * 		The price of the <code>ItemInfo</code>.
	 */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method returns the <code>RFID</code> of the <code>ItemInfo</code>.
     * 
     * @return
     * 		The <code>RFID</code> that is returned.
     */
    public String getRfidTagNumber() {
        return rfidTagNumber;
    }

    /**
     * 
     * <dt>Preconditions
     * 	<dd>The <code>RFID</code> has to be 9 digits and Base-16. 
     * 
     * 
     * This method is a setter for the <code>RFID</code> of the <code>ItemInfo</code>.
     * 
     * 
     * @param rfid
     * 		The <code>RFID</code>
     * 
     * @exception - IllegalArgumentException()
     * This isi thrown if the Precondition is violated
     */
    public void setRfidTagNumber(String rfid) {
        boolean isHex = true;
        rfid = rfid.toUpperCase();
        for(int i = 0; i < rfid.length(); i++){
            char temp = rfid.charAt(i);
            if(('A' <= temp && temp <= 'F') || ('0'<=temp  && temp <='9')){
                isHex&=true;
            }
            else{
                isHex &=false;
            }
        }
        if(isHex && rfid.length()==9){
            this.rfidTagNumber = rfid;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method returns the <code>originalLocation</code> of the <code>ItemInfo</code>
     * 
     * @return
     * 		The <code>originalLocation</code> of the <code>ItemInfo</code>.
     */
    public String getOriginalLocation() {
        return originalLocation;
    }

    
    /**
     * <dt>Precondition
     * 	<dd>The location has to be 6 characters, the first has to be an 'S' or an 's' and the last 5 have to be numbers.
     * 
     * This method basically is a setter for the <code>originalLocation</code> instance variable in the <code>ItemInfo</code> object
     * 
     * @param ogLocation
     * 		The new value of the original location
     * 
     * @exception - IllegalArgumentException
     * 		This is thrown if the precondition is violated
     */
    public void setOriginalLocation(String ogLocation) {
    	boolean isValid = ItemList.isValidLocation(currentLocation);
        if(ogLocation.length() != 6 || isValid){
            throw new IllegalArgumentException();
        }
        else{
            this.originalLocation = ogLocation;
        }
    }

    /**
     * This method basically is a setter for the <code>currentLocation</code> instance variable in the <code>ItemInfo</code> object
     * 
     * @return currentLocation
     * 		The current Location
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * <dt>Precondition
     * 	<dd>The location has to be 6 characters, the first has to be an 'S' or an 's' and the last 5 have to be numbers.
     * 	<dd>OR the location can be 4 characters, first being a 'C' or a 'c' and the last 3 are numbers
     * 	<dd>OR the location can be "OUT" or "out"
     * 
     * This method basically is a setter for the <code>currentLocation</code> instance variable in the <code>ItemInfo</code> object
     * 
     * @param currLocation
     * 		The new value of the current location
     * 
     * @exception - IllegalArgumentException
     * 		This is thrown if the precondition is violated
     */
    public void setCurrentLocation(String currLocation) {
        currLocation = currLocation.toUpperCase();
        boolean isValid = ItemList.isValidLocation(currLocation) || currLocation.equals("OUT");
        if(isValid){
            this.currentLocation = currLocation;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
