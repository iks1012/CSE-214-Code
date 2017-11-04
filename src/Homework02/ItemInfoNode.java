/**
 * This class is responsible for managing a <code>ItemInfo</code> object. This class is also responsible for storing
 * the locations of the next and prev nodes to maintain the LinkedList Data Structure.
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *      Recitation TA: Aynoor Saleem
 *      Recitation Section: 2
 *
 */
public class ItemInfoNode {
    private ItemInfo info;
    private ItemInfoNode next;
    private ItemInfoNode prev;


    /**
     * This is just a method that gives me a easy way to make the information tabular
     */
    public String toString(){
        return String.format("| %-15s | %-10s | %-15s  | %-15s | %.02f |",info.getName(), info.getRfidTagNumber(), info.getOriginalLocation(), info.getCurrentLocation(), info.getPrice());
    }

    /**
     * This returns an instance of the <code>ItemInfoNode</code> object with <code>in</code> as the <code>ItemInfo</code> instance variable
     * 
     * @param in
     */
    public ItemInfoNode(ItemInfo in){
        info = in;
    }


    /**
     * This is a getter for the <code>ItemInfo</code> object 
     * 
     * @return
     * 		The info
     */
    public ItemInfo getInfo() {
        return info;
    }
    
    
    /**
     * This is a setter for the <code>ItemInfo</code> object 
     * 
     * @param - info
     * 		The info
     */
    public void setInfo(ItemInfo info) {
        this.info = info;
    }

    /**
     * This basically returns a reference for the next node relative to the one it is called upon
     * 
     * @return
     * 		The next node
     */
    public ItemInfoNode getNext() {
        return next;
    }

    /**
     * This basically sets the reference of the next node to the one that is passed through in the params
     * @param next
     * 		The new next node
     * 
     */
    public void setNext(ItemInfoNode next) {
        this.next = next;
    }

    /**
     * This method basically returns the reference for the previous node relative to the one it is called upon
     * 
     * @return
     * 		The previous node
     */
    public ItemInfoNode getPrev() {
        return prev;
    }

    /**
     * This method basically sets the reference for the previous node relative to the one it is called upon to the one that is
     * passed through in the parameters
     * 
     * @param prev
     * 		The new previous node
     */
    public void setPrev(ItemInfoNode prev) {
        this.prev = prev;
    }
}
