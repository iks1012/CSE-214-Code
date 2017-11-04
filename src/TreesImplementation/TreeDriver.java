import java.io.*;
import java.util.Scanner;

/**
 * This class is responsible for managing all the objects and this is the
 * Driver class to make the program, well, function.
 *
 * This is also the class with the main method.
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class TreeDriver {

    //THIS IS THE EXTRA CREDIT TOGGLE.
    static boolean EXTRA_CREDIT = true;



    static Scanner in = new Scanner(System.in);
    static boolean treeLoaded = false;
    static int numberLines = 0;
    static Tree tree;


    /**
     * This is the main method and this just ties together all the other classes that were required to
     * make this program function as need be.
     *
     * @param args
     */
    public static void main(String[] args){
        boolean isRunning = true;
        pln("EXTRA CREDIT: "+EXTRA_CREDIT);
        pln("Can handle as many nodes per parent as the txt file provides [0, infinity).");

        while(isRunning){
            printMenu();
            p("Choice> ");
            char choice;
            try{
                choice = in.nextLine().toUpperCase().charAt(0);
            }
            catch(Exception e){
                choice = '\0';
            }

            if(choice == 'L'){
                if(loadTree()){
                    treeLoaded = true;
                    pln("Tree created Successfully!");
                }
                else{
                    pln("File has error");
                }
            }
            else if(choice == 'H'){
                if(treeLoaded){
                    tree.beginSession(tree.getRoot());
                }
                else{
                    pln("You must load the tree first!");
                }
            }
            else if(choice == 'T'){
                if(treeLoaded){
                    preorderPrint();
                }
                else{
                    pln("You must load the tree first!");
                }
            }
            else if(choice == 'Q'){
                isRunning = false;
                pln("Thanks for using our services.");
            }
            else{
                pln("Invalid Input Try Again.");
            }

        }
    }

    /**
     * Precondition - the tree must be loaded
     *
     * This method just instantiates the printing of the loaded tree in a preorder traversal.
     *
     *
     */
    public static void preorderPrint(){
        pln();
        tree.preorderSearch(true, tree.getRoot(), "root");
    }


    /**
     * This method basically loads the tree from the entered text file
     * There is no pre condition because it is all taken care of in the method itself
     *
     * @return
     *      If the tree was loaded or not.
     *
     */
    public static boolean loadTree(){
        try {
            String fileString;
            try {
                p("Enter the file name> ");
                String fileName = in.nextLine();
                File file = new File(fileName);
                fileString = fileToString(file);
            } catch (IOException ioe) {
                pln("IOException");
                return false;
            }

            if (numberLines == 0) {
                return false;
            } else {
                String[] treeInfo = fileString.split('\n' + "");
                String parentLabel;
                String label = treeInfo[0], msg = treeInfo[1], prompt = treeInfo[2];
                TreeNode root = new TreeNode(label);
                root.setPrompt(prompt);
                root.setMessage(msg);

                tree = new Tree(root);

                int numChildren = 0;
                for (int i = 3; i < numberLines - 1; i++) {
                    String tempString = treeInfo[i];
                    if (tempString.contains(" ")  && hasNumber(tempString) && numSpaces(tempString)==1) {


                        int indexOfSpace = tempString.indexOf(' ');
                        label = tempString.substring(0, indexOfSpace);
                        numChildren = Integer.parseInt(tempString.substring(indexOfSpace).trim());
                        try {
                            tree.preorderSearch(false,tree.getRoot(), label);
                            TreeNode cursor = tree.getNodeReference(label);
                            cursor.setChildren(numChildren);
                            tree.setCursor(cursor);
                        } catch (Exception e) {
                            return false;
                        }
                    }
                    else{
                        return false;
                    }



                    parentLabel = label;
                    if (numChildren != 0) {

                        for (int j = 1; j <= (3 * numChildren - 1); j += 3) {

                            label = treeInfo[i + j];
                            prompt = treeInfo[i + j + 1];
                            msg = treeInfo[i + j + 2];
                            tree.preorderSearch(false,tree.getRoot(), parentLabel);
                            if (tree.addNode(label, msg, prompt, parentLabel)) {
                                //Do nothing
                            } else {
                                return false;
                            }
                        }
                        i += (3 * numChildren);
                    }




                }

            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


    /**
     * This method returns the number of spaces in a given String
     * @param str
     *      The string for testing
     * @return
     *      The number
     */
    public static int numSpaces(String str){
        int numSpaces = 0;

        for(int i = 0; i<str.length(); i++){
            if(str.charAt(i) == ' '){
                numSpaces++;
            }
        }
        return numSpaces;
    }

    /**
     * Precondition - The string must be valid
     * This method basically traverses through a string and checks if it has digit
     * @param str
     *      The string
     * @return
     *      If it has a digit or not.
     */
    public static boolean hasNumber(String str){
        for(int i = 0; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * This is another helper method to simplify the main method.
     */
    public static void printMenu(){
        pln("L - Load a Tree.\n" +
            "H - Begin a Help Session.\n" +
            "T - Traverse the Tree in preorder.\n" +
            "Q - Quit");
    }


    /**
     * This method basically converts a file to a string that can be traversed through
     * The only precondition is that the file must be a valid file.
     *
     *
     * @param f
     *      The file that is to be converted
     *
     * @return
     *      The finished String
     * @throws IOException
     *  If the file is not valid
     */
    public static String fileToString(File f) throws IOException {
        BufferedReader bR = new BufferedReader(new FileReader(f));
        String s = null;
        StringBuilder  sB = new StringBuilder();
        String ls = '\n'+"";
        try {
            while((s = bR.readLine()) != null) {
                s = s.trim();
                if(s.length()!=0){
                    sB.append(s);
                    numberLines++;
                    sB.append(ls);
                }
            }

            return sB.toString();
        }
        finally {
            bR.close();
        }
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
