import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * This class is responsible for tying together all the other classes and making this the homework
 * that it was asked to be.
 *
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class Mailbox implements Serializable{
    private Folder inbox;
    private Folder trash;
    private ArrayList<Folder> folders;
    static Scanner in = new Scanner(System.in);
    static Folder currentFolder;

    public static Mailbox mailbox;


    /**
     * This method gets the folder at the ith index
     * @param i
     *      The index
     * @return
     *      The Folder
     */
    public Folder getFolder(int i){
        return folders.get(i);
    }

    /**
     * This method returns the number of folders.
     * @return
     *      Number of Folders
     */
    public int numFolders(){
        return folders.size();
    }



    /**
     * This returns an instance of the <code>Mailbox</code> object
     * as this is a constructor for it.
     */
    public Mailbox(){
        folders = new ArrayList<Folder>();
        inbox = new Folder("Inbox");
        trash = new Folder("Trash");
    }

    /**
     * This method adds a new folder to the set of all folders.
     *
     */
    public void addFolder(){
        String name = "";
        try{
            p("Enter the folder name: ");name = in.nextLine();
            folders.add(new Folder(name));
            printFolders();

        }
        catch(Exception e){
            pln("Enter something valid");
        }
    }

    /**
     * This method removes a folder with the desired name if it exists
     *
     * @param name
     *      This is the desired folders name.
     */
    public void deleteFolder(String name){
        try{
            if(name.equals("Inbox")||name.equals("Trash")){
                pln("These folders can't be deleted.");
                return;
            }
            for(int i = 0;i < folders.size(); i++){
                if(folders.get(i).getName().equals(name)){
                    Folder temp = folders.remove(i);
                    pln(temp.getName()+" was removed");
                    return;
                }
            }
            pln("Folder not found");
        }
        catch (Exception e){
            pln("Please enter a valid name, thanks. ");
        }
    }

    /**
     * This method conducts the initlizing of a new email.
     *
     * All the fields are initialized to everything is taken care of.
     */
    public void composeEmail(){
        p("Enter the recipient (To): ");
        try{
            String to = in.nextLine();
            p("Enter the carbon copy recipients (CC): ");String cc = in.nextLine();
            p("Enter the blind carbon copy recipients (BCC): ");String bcc = in.nextLine();
            p("Enter subject line: ");String subject = in.nextLine();
            p("Enter body: ");String body = in.nextLine();
            Email email = new Email(to,cc,bcc,subject,body,new GregorianCalendar());
            inbox.addEmail(email);
            pln("Email Added to Inbox");
        }
        catch(Exception e){
            pln("Invalid input");
        }
    }

    /**
     * This method deletes an email in a folder
     *
     * @param email
     *      The email
     */
    public void deleteEmail(Email email){
        p("Enter the index of the email you would like to remove: ");
        int index = in.nextInt()-1;
        in.nextLine();
        if(!(0 <= index && index<=currentFolder.size()-1)){
            throw new IllegalArgumentException();
        }
        email = currentFolder.removeEmail(index);
        trash.addEmail(email);
        pln("Added to the trash.");
    }

    /**
     * This method just empties the trash
     */
    public void clearTrash(){
        for(int i = 0; i < trash.size(); i ++){
            trash.removeEmail(i);
        }
    }


    /**
     * This method moves an email from the currentFolder to the target Folder
     *
     * @param email
     *      The email
     * @param target
     *      The target
     *
     * @throws IllegalArgumentException if the entered index of out of bounds for the emails.
     */
    public void moveEmail(Email email, Folder target){
        boolean moved = false;
        p("Enter the index of the email you would like to move: ");
        int index = in.nextInt()-1;in.nextLine();
        if(!(0 <= index && index<=currentFolder.size()-1)){
            throw new IllegalArgumentException();
        }
        printFolders();
        p("Enter the name of the folder you would like to move the email too: ");String name = in.nextLine();

        if(name.equalsIgnoreCase("Inbox")){
            email = currentFolder.removeEmail(index);
            inbox.addEmail(email);
            moved = true;
        }
        if(name.equalsIgnoreCase("Trash")){
            email = currentFolder.removeEmail(index);
            trash.addEmail(email);
            moved = true;
        }

        for(int i = 0; i < mailbox.numFolders()  && !moved; i++){
            if(mailbox.getFolder(i).getName().equalsIgnoreCase(name)){
                email = currentFolder.removeEmail(index);
                mailbox.getFolder(i).addEmail(email);
                pln("Moved!"); moved = true;
            }
        }
        if(!moved){
            throw new IllegalArgumentException();
        }
    }


    /**
     * This method pretty much gets the
     * @param name
     * @return
     */
    public Folder getFolder(String name){
        Folder returnThis = new Folder(name);
        if(name.equals("Inbox")){
            return inbox;
        }
        if(name.equals("Trash")){
            return trash;
        }
        for(int i = 0; i < folders.size(); i++){
            if(folders.get(i).getName().equalsIgnoreCase(name)){
                return folders.get(i);
            }
        }
        throw new IllegalArgumentException();
    }


    /**
     * This is the main method and this method ties together everything
     *
     * @param args
     */
    public static void main(String[] args){
        boolean isRunning = true;
        mailbox = new Mailbox();
        currentFolder = mailbox.getFolder("Inbox");


        try {
            FileInputStream file = new FileInputStream("mailbox.obj");
            ObjectInputStream fin  = new ObjectInputStream(file);
            mailbox = (Mailbox) fin.readObject();
            file.close();
        }
        catch(IOException a){

        }
        catch(ClassNotFoundException c){

        }

        while(isRunning){
            try{
                printMenu();
                char choice = '\0';
                try{
                    p("Enter a user option: "); choice = in.nextLine().toUpperCase().charAt(0);
                }
                catch(Exception e) {
                    pln("Valid Choice Please!");
                }
                if(choice == 'A'){
                    mailbox.addFolder();
                }
                else if(choice == 'R'){
                    try{
                        printFolders();
                        p("Enter the name of the folder you would like to remove: ");String name = in.nextLine();
                        mailbox.deleteFolder(name);
                    }
                    catch (IllegalArgumentException iae){
                        pln("That folder does not exist");
                    }
                }
                else if(choice == 'C'){
                    mailbox.composeEmail();
                }
                else if(choice == 'F'){
                    try{
                        printFolders();
                        p("Enter the name of the folder you would like to open: ");String name = in.nextLine();
                        currentFolder = mailbox.getFolder(name);
                        folderUI();
                    }
                    catch (IllegalArgumentException iae){
                        pln("That folder does not exist");
                    }
                }
                else if(choice == 'I'){
                    currentFolder = mailbox.getFolder("Inbox");
                    folderUI();
                    //Display current folder
                }
                else if(choice == 'T'){
                    currentFolder = mailbox.getFolder("Trash");
                    folderUI();
                    //Display current folder
                }
                else if(choice == 'E'){
                    mailbox.clearTrash();
                }
                else if(choice == 'Q'){
                    try {
                        FileOutputStream file = new FileOutputStream("mailbox.obj");
                        ObjectOutputStream fout = new ObjectOutputStream(file);
                        fout.writeObject(mailbox);
                        fout.close();
                    }
                    catch(IOException a) {

                    }
                    isRunning = false;
                }
                else{
                    pln("Invalid input!! ");
                }
                pln("----------------------------------------------------------------------------------------------------");
            }
            catch(Exception e){
                pln("Please Enter something Valid!");
            }
        }
    }


    /**
     * This method just prints out all the folders that are initialized
     */
    public static void printFolders(){
        pln("----------------------------------------------------------------------------------------------------");
        pln("Folders:\n" +
            "Inbox\n" +
            "Trash");
        for(int i = 0; i < mailbox.numFolders(); i++){
            pln(mailbox.getFolder(i).getName());
        }
        pln("----------------------------------------------------------------------------------------------------");
    }

    /**
     * This method is responsible for carrying out all the folder submenu options and making sure that
     * they get executed properly
     */
    public static void folderUI(){
        pln("----------------------------------------------------------------------------------------------------");

        try{
            boolean folderSubRun = true;
            while(folderSubRun){
                printEmailsIn(currentFolder);
                folderSubMenu();
                p("Enter a user option: ");String subChoice = "";
                try{
                    subChoice = in.nextLine();
                }
                catch (Exception e){
                    pln("Enter something valid");
                }
                if(subChoice.equalsIgnoreCase("M")){
                    //Move Email
                    try{
                        mailbox.moveEmail(null, currentFolder);
                    }
                    catch(IllegalArgumentException e){
                        pln("Not found!");
                    }
                }
                else if(subChoice.equalsIgnoreCase("D")){
                    //Delete Email
                    try{
                        mailbox.deleteEmail(null);
                    }
                    catch(IllegalArgumentException e){
                        pln("Invalid, try again!");
                    }

                }
                else if(subChoice.equalsIgnoreCase("V")){
                    //View Email Contents
                    try{
                        p("Enter the index of the email you would like to view: ");int index = in.nextInt()-1;in.nextLine();
                        if(0<=index && index <= currentFolder.size()-1){
                            Email temp = currentFolder.getEmail(index);
                            pln("To: "+ temp.getTo());
                            pln("CC: "+ temp.getCc());
                            pln("BCC: "+ temp.getBcc());
                            pln("Subject: "+temp.getSubject());
                            pln("Body: "+temp.getBody());
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    catch(IllegalArgumentException e){
                        pln("Invalid Index");
                    }

                }
                else if(subChoice.equalsIgnoreCase("SA")){
                    currentFolder.setCurrentSortingMethod("Subject A->Z");
                }
                else if(subChoice.equalsIgnoreCase("SD")){
                    currentFolder.setCurrentSortingMethod("Subject Z->A");
                }
                else if(subChoice.equalsIgnoreCase("DA")){
                    currentFolder.setCurrentSortingMethod("Oldest First");
                }
                else if(subChoice.equalsIgnoreCase("DD")){
                    currentFolder.setCurrentSortingMethod("Recent First");
                }
                else if(subChoice.equalsIgnoreCase("R")){
                    folderSubRun = false;
                }
                else{
                    pln("Invalid Input.");
                }
                pln("----------------------------------------------------------------------------------------------------");
            }
        }
        catch(Exception e){
            pln("Please input something valid.");
        }
    }


    /**
     * This method just prints all the emails in a folder.
     *
     * @param folder
     *      The Folder that the email prints the contents of.
     */
    public static void printEmailsIn(Folder folder){
        pln("----------------------------------------------------------------------------------------------------");
        pln("Folder: "+currentFolder.getName());
        pln(String.format("| %-5s | %-35s | %-20s |","Index", "Time", "Subject"));

        for(int i = 0; i < folder.size(); i++){
            Email temp = folder.getEmail(i);
            pln(String.format("| %-5s | %-35s | %-20s |", (i+1), temp.getTimeStamp().getTime() , temp.getSubject()));
        }
        pln("----------------------------------------------------------------------------------------------------");
    }


    /**
     * This method just displays the regular options
     */
    public static void printMenu(){
        pln("A – Add folder\n" +
            "R – Remove folder\n" +
            "C – Compose email\n" +
            "F – Open folder\n" +
            "I – Open Inbox\n" +
            "T – Open Trash\n" +
            "E – Empty Trash\n" +
            "Q - Quit");
    }


    /**
     * This method is just displays the folder submenu options
     */
    public static void folderSubMenu(){
        pln("M – Move email\n" +
            "D – Delete email\n" +
            "V – View email contents\n" +
            "SA – Sort by subject line in ascending order\n" +
            "SD – Sort by subject line in descending order\n" +
            "DA – Sort by date in ascending order\n" +
            "DD – Sort by date in descending order\n" +
            "R – Return to mailbox");
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
