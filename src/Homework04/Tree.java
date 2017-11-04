import java.util.Scanner;

/**
 * This class is responsible for managing all the <code>TreeNode</code> objects as a <code>Tree</code> data structure.
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *      Recitation TA: Aynoor Saleem
 *      Recitation Section: 2
 *
 */
public class Tree {
    private TreeNode root;
    private TreeNode parent;
    private TreeNode cursor;
    private int numNodes = 0;
    private static Scanner in = new Scanner(System.in);

    /**
     * <dt>Preconditions</dt>
     *  <dd>The root must be predefined</dd>
     *
     *  This method returns an instance of the <code>Tree</code> object with root as the root
     *
     *
     * @param root
     *   The root
     */
    public Tree(TreeNode root){
        this.root = root;
        cursor = root;
        parent = root;
    }

    /**
     * This method sets the cursor of the Tree to the passed through node
     *
     * @param cursor
     *      the new cursor memory address.
     */
    public void setCursor(TreeNode cursor) {
        this.cursor = cursor;
    }

    /**
     * Preconditions
     * all the parameters must be defined
     *
     * A method to add a TreeNode to the tree. The location will be a child of parentLabel.
     * The child node should be left justified meaning that it should first be placed in the
     * left most TreeNode reference, then the middle, then the right. A return value of true
     * indicates that the node was successfully added to the tree. Otherwise, the return value
     * is false.
     *
     *
     * @param label
     * @param prompt
     * @param message
     * @param parentLabel
     * @return
     *      If the node was added
     * @exception IllegalArgumentException
     * @exception NullPointerException
     *      These are thrown if the file is invalid or if the parameters were messed up
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel){
        try{
            numNodes++;
            TreeNode temp = new TreeNode(label);
            temp.setMessage(message);
            temp.setPrompt(prompt);
            cursor.addNode(temp);
            return true;
        }
        catch(IllegalArgumentException e){
            return false;
        }
        catch(NullPointerException npe){
            return false;
        }
    }


    /**
     * Preconditions
     * The label must be valid.
     * And the method preOrderSearch() must be called before this
     *
     *
     *  Returns a reference to the TreeNode that has the given label. The return value is null if the label is not found.
     *
     * @param label
     *      The label
     * @return
     *      The cursor at "label"'s spot
     */
    public TreeNode getNodeReference(String label){
        try{
            return cursor;
        }
        catch(NullPointerException e){
            pln("Parent not found");
        }
        return null;
    }

    /**
     * <dt>Preconditions
     *  <dd>All the parameters must be defined, and the root must be a node within the tree and the label must be the label
     *  you are looking for.
     *
     * @param isPrinting
     *      If you want the details printed or not while traversing. (For testing and the "T" option)
     * @param root
     *      The current node that is being searched
     * @param label
     *      The label that we are looking for if we are not printing
     */
    public void preorderSearch(boolean isPrinting, TreeNode root, String label) {
        if (!isPrinting && root != null) {
            if (label.equals(root.getLabel())) {
                cursor = root;
            }
        }
        else if(isPrinting){
            pln("Label: "+root.getLabel());
            pln("Prompt: "+root.getPrompt());
            pln("Message: "+root.getMessage());
            pln("----------------------------");
        }
        else {
            return;
        }

        for (int i = 0; i < root.getMaxChildren(); i++) {
            preorderSearch(isPrinting,root.getChild(i), label);
        }
    }


    /**
     * This method returns the root node of the tree.
     *
     * @return
     *      The root of the tree.
     */
    public TreeNode getRoot(){
        return root;
    }


    /**
     * <dt>Preconditions</dt>
     *  <dd>The tree must be loaded validly and the root must be the root node of the tree. (Only in the initial call)</dd>
     *      This uses a modified breadth first traversal algorithm
     *
     *      This method is responsible for conducting the tech support session.
     *
     * @param root
     *      The node that is being checked
     */
    public void beginSession(TreeNode root){
        if(root!=null && root.getMaxChildren()>0){
            cursor = root;
            pln(root.getPrompt());
            for(int i = 0; i < root.getMaxChildren(); i++){
                pln((i+1)+") "+root.getChild(i).getMessage());
            }

            if(cursor!=parent && TreeDriver.EXTRA_CREDIT){
                pln("Extra Credit - B) Go Back");
            }



            pln("0) Exit Session");
            p("Choice> ");char choice = in.nextLine().toUpperCase().charAt(0);
            pln();
            if(choice == '0'){
                return;
            }
            else if(choice == 'B' && TreeDriver.EXTRA_CREDIT){
                cursor = parent;
                beginSession(parent);
            }
            else if(0<(choice-48) && (choice-48)<=root.getMaxChildren()){
                parent = root;
                if(root.getChild(choice-49).getMaxChildren()==0){
                    pln(root.getChild(choice-49).getPrompt());
                    pln();
                }
                beginSession(root.getChild(choice-49));
            }
            else{
                pln("Invalid Input - Try Again");
                beginSession(root);
            }


        }
        else{
            pln("Thank you for using this automated help service. ");
        }
        return;
    }


    /**
     * This is a helper method to make my life easier when testing along with the debug mode.
     *
     * @param pln
     */
    public static void pln(String pln){
        System.out.println(pln);
    }


    /**
     * This is a helper method to make my life easier when testing along with the debug mode.
     * @param p
     */
    public static void p(String p){
        System.out.print(p);
    }

    /**
     * This is a helper method to make my life easier when testing along with the debug mode.
     */
    public static void pln(){
        pln("");
    }

}
