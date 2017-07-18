/**
 * The <code>SongRecord</code> class which contains all the information about a particular audio file.
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class SongRecord {

    private String title;
    private String artist;

    private int minutes;
    private int seconds;


    /**
     * This returns an instance of the <code>SongRecord</code>
     *
     * @param name
     *      The Title of the song
     *
     * @param author
     *      The author of the song
     */
    public SongRecord(String name, String author){
        title = name;
        artist = author;
        minutes = 0;
        seconds = 0;
    }


    /**
     * This returns the current title of the song at the time of calling
     *
     * @return
     *      The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This sets the title of the song to the String that is passed
     * @param name
     *      The String that is assigned as the title
     */
    public void setTitle(String name) {
        title = name;
    }

    /**
     * This returns the artist of the song
     *
     * @return
     *      The artist
     */
    public String getArtist() {
        return artist;
    }


    /**
     * This sets the artist of the song to the String that is passed through.
     * @param author
     *      This artist
     */
    public void setArtist(String author) {
        artist = author;
    }


    /**
     * This returns the number of minutes the song is
     *
     * @return
     *      This returns a value that is positive if and only if it was assigned to begin with.
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     *
     * Sets the songs number of minutes to the value that is passed through
     *
     * @param mins
     *      This is the value that minutes is assigned to if the value is not negative
     *
     * @exception IllegalArgumentException
     *      This is thrown if and only if the value of mins is negative.
     */
    public void setMinutes(int mins) {
        if(mins >= 0){
            minutes = mins;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the number of seconds of the song
     *
     * @return
     *      This is a value in between [0,59].
     */
    public int getSeconds() {
        return seconds;
    }


    /**
     *
     * Sets the songs number of seconds to the value that is passed through
     *
     * @param secs
     *      This is the value that minutes is assigned to if the value is not negative or not 60+.
     *
     * @exception IllegalArgumentException
     *      This is thrown if and only if the value of secs is negative or 60+.
     */
    public void setSeconds(int secs) {
        if(secs >= 0 && secs <= 59){
            seconds = secs;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This is a toString method that returns a String of the attributes of the <code>SongRecord</code> beautifully formatted
     * @return
     *      The String
     */
    public String toString(){
        return String.format("%-15s | %-15s | %2d:%02d", title, artist, minutes, seconds);
    }


    public boolean equals(SongRecord second){
        if( this.getTitle().equals(second.getTitle()) &&
                this.getArtist().equals(second.getArtist()) &&
                this.getMinutes() == second.getMinutes() &&
                this.getSeconds() == second.getSeconds()){


            return true;
        }
        else{
            return false;
        }
    }

}
