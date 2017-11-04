import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is responsible for the <code>Folder</code> object. This class stores all the
 * emails in this instance of the program
 *
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class Folder implements Serializable {
    private ArrayList<Email> emails;
    private String name;
    private String currentSortingMethod;


    /**
     * This method returns an instance of the <code>Folder</code> object.
     *
     * @param name
     *      The name of the folder
     */
    public Folder(String name){
        emails = new ArrayList<Email>();
        this.name = name;
        currentSortingMethod = "Recent First";
        sortByDateDescending();
    }


    /**
     * This method returns the name of the Folder upon this is called.
     * @return
     *      The name
     */
    public String getName(){
        return name;
    }

    /**
     * This method returns the sorting method that is in effect
     * @return
     *      The string
     */
    public String getCurrentSortingMethod(){
        return currentSortingMethod;
    }

    /**
     * This method sets the name of the folder
     *
     * @param name
     *  Desired name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * This method sets the state of the sorting method to the desired sorting method.
     *  Simple bubble sort is used for the sorting
     *
     * @param sort
     *  The sorting method
     */
    public void setCurrentSortingMethod(String sort){
        currentSortingMethod = sort;
        if(sort.equalsIgnoreCase("Oldest First")){
            sortByDateAscending();
        }
        else if(sort.equalsIgnoreCase("Recent First")){
            sortByDateDescending();
        }
        else if(sort.equalsIgnoreCase("Subject A->Z")){
            sortBySubjectAscending();
        }
        else if(sort.equalsIgnoreCase("Subject Z->A")){
            sortBySubjectDescending();
        }
     }

    /**
     * This method adds the email to the ArrayList of Emails
     *
     * @param e
     *      This is the email
     */
    public void addEmail(Email e){
        emails.add(e);
    }

    /**
     * This method removes an email from a given index
     *
     * @param index
     *      The index
     * @return
     *      The Email that was removed
     */
    public Email removeEmail(int index){
        return emails.remove(index);
    }

    /**
     * This method just gets the email at the iTH index
     * @param i
     *      The desired index
     * @return
     *      The email
     */
    public Email getEmail(int i){
        return emails.get(i);
    }


    /**
     * This method returns the number of emails in the
     * given Folder
     * @return
     *      The Size
     */
    public int size(){
        return emails.size();
    }


    /**
     * This method sorts the emails by subject from A to Z
     */
    public void sortBySubjectAscending(){
        for(int i = 0; i < emails.size(); i++){
            for(int j = 1; j < (emails.size()-i); j++){
                if(emails.get(j-1).getSubject().compareTo(emails.get(j).getSubject()) > 0){
                    Email temp = emails.get(j-1);
                    emails.set(j-1, emails.get(j));
                    emails.set(j, temp);
                }
            }
        }
    }


    /**
     * This method sorts the emails by subject from Z to A
     */
    public void sortBySubjectDescending(){
        for(int i = 0; i < emails.size(); i++){
            for(int j = 1; j < (emails.size()-i); j++){
                if(emails.get(j-1).getSubject().compareTo(emails.get(j).getSubject()) < 0){
                    Email temp = emails.get(j-1);
                    emails.set(j-1, emails.get(j));
                    emails.set(j, temp);
                }
            }
        }
    }


    /**
     * This method sorts the emails by the most oldest first
     */
    public void sortByDateAscending(){
        for(int i = 0; i < emails.size(); i++){
            for(int j = 1; j < (emails.size()-i); j++){
                if(emails.get(j-1).getTimeStamp().getTimeInMillis() > emails.get(j).getTimeStamp().getTimeInMillis()){
                    Email temp = emails.get(j-1);
                    emails.set(j-1, emails.get(j));
                    emails.set(j, temp);
                }
            }
        }
    }

    /**
     * This method sorts the emails by the most recent first
     */
    public void sortByDateDescending(){
        for(int i = 0; i < emails.size(); i++){
            for(int j = 1; j < (emails.size()-i); j++){
                if(emails.get(j-1).getTimeStamp().getTimeInMillis() < emails.get(j).getTimeStamp().getTimeInMillis()){
                    Email temp = emails.get(j-1);
                    emails.set(j-1, emails.get(j));
                    emails.set(j, temp);
                }
            }
        }
    }


}
