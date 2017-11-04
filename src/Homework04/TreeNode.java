/**
 * This class is responsible for managing a <code>TreeNode</code> object. This class is also responsible for storing
 * the children for this.node
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *      Recitation TA: Aynoor Saleem
 *      Recitation Section: 2
 *
 */
public class TreeNode {
    private int emptyChildLocation;
    private int maxChildren;
    private TreeNode[] children;

    //"X-X-X or root"
    private String label = "";

    //The Users Input
    private String message;

    //The Computers Output
    private String prompt;


    /**
     * <dt>Precondition</dt>
     * <dd>The label must be a valid String</dd>
     *
     * This method returns an instance of the <code>TreeNode</code> object which is
     * hopefully added to the tree.
     * @param label
     *  The label of the node
     */
    public TreeNode(String label){
        this.label = label;



        //Change this. or set it in the Tree class
        message = "";
        prompt = "";
    }


    /**
     * This method basically initializes the array that holds all the children nodes
     * The only precondition is that the file must be valid
     *
     * @param numChildren
     */
    public void setChildren(int numChildren){
        emptyChildLocation = 0;
        maxChildren = numChildren;
        children = new TreeNode[numChildren];
    }

    /**
     * This method basically returns the maximum number of children that the node can hold
     * This number was obtained from the file which should be in the correct format
     *
     *
     * @return
     *      The max number of children
     */
    public int getMaxChildren(){
        return maxChildren;
    }

    /**
     * This method returns the child of this node at the index spot in
     * the array of children
     *
     * @param index
     *      The index
     *
     * @return
     *      The correct node
     *
     * @exception - IllegalArgumentException
     * this is thrown when the number requested is more then the
     * node can handle.
     */
    public TreeNode getChild(int index){
        if(index<maxChildren){
            return children[index];
        }
        else{
            throw new IllegalArgumentException();
        }
    }


    /**
     * <dt>Precondition</dt>
     * The file must be of valid format.
     *
     * This method adds a node to the most open index
     *
     * @param temp
     *      The node that is added as a child
     *
     * @exception IllegalArgumentException
     * This is thrown when the file is of invalid format or when more nodes are added
     * then the node can handle.
     */
    public void addNode(TreeNode temp){
        if(emptyChildLocation<maxChildren && ! isLeaf()){
            children[emptyChildLocation] = temp;
            emptyChildLocation++;
        }
        else{
            throw new IllegalArgumentException();
        }

    }


    /**
     * This method returns if the node is a leaf or not
     *
     * @return
     *      The boolean value
     */
    public boolean isLeaf(){
        return maxChildren==0;
    }

    /**
     * This method returns the message associated with the node
     * @return
     *      The message
     */
    public String getMessage(){
        return message;
    }

    /**
     * This method sets the message of the node to <code>newMessage</code>
     * @param newMessage
     *      The new message
     */
    public void setMessage(String newMessage){
        message = newMessage;
    }

    /**
     * This method gets the prompt of the node
     * @return
     *      The prompt
     */
    public String getPrompt(){
        return prompt;
    }

    /**
     * This method sets the prompt of the node to new prompt
     *
     * @param newPrompt
     *      The new prompt
     */
    public void setPrompt(String newPrompt){
        prompt = newPrompt;
    }

    /**
     * This method returns the label of the node
     *
     * @return
     *      The node
     */
    public String getLabel() {
        return label;
    }


}
