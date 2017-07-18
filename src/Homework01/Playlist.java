/**
 * The <code>Playlist</code> class contains a list of <code>SongRecord</code> objects.
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class Playlist {

    private int MAX_SONGS = 50;
    private SongRecord[] songs;
    private int numSongs;
    private String name = "";

    /**
     * This returns an instance of a <code>Playlist</code>
     *
     * @param name
     * 		This is set to the name of the playlist
     *	
     * the playlist is initialized to and empty list of <code>SongRecord</code>s
     */
    public Playlist(String name){
    	this.name = name;
        numSongs = 0;
        songs = new SongRecord[MAX_SONGS];
        for(int i = 0; i<MAX_SONGS ; i++){
            songs[i] = new SongRecord("","");
        }
    }

    /**
     * This also returns an instance of a <code>Playlist</code>
     * Except this time an array is passed through and the <code>Playlist</code> is initialized to the array that is passed
     *
     * @param presetSongs
     *      The preset Songs
     */
    public Playlist(SongRecord[] presetSongs, int size){
        songs = presetSongs;
        numSongs = size;
    }


    /**
     * This returns the number of songs in the playlist
     *
     * Order of Complexity: O(1)
     *
     *
     * @return
     *      The Size of the playlist
     */
    public int size(){
        return numSongs;
    }


    /**
     * <dt>Preconditions:
     *  <dd>This <code>SongRecord</code> object has been instantiated and 1 <= position <= numSongs + 1.
     *  <dd>The number of <code>SongRecord</code> objects in this <code>Playlist</code> is less than max_songs.
     * <dt>Postconditions:
     *  <dd>The new <code>SongRecord</code> is now stored at the desired position in the <code>Playlist</code>. All <code>SongRecord</code>s that
     *  <dd>were originally in positions greater than or equal to position are moved back one position.
     *
     *
     * This method adds a song to the <code>Playlist</code>
     * if the <code>Playlist</code> is full , it is expanded
     * if the <code>position</code> that is passed through is out of bounds, an exception is thrown
     *
     * @param songToAdd
     *      The song to add
     * @param position
     *      The position at wish the user desires
     *
     * @exception  -  IndexOutOfBoundsException(), if the position is out of the array size or if it is negative
     */
    public void addSong(SongRecord songToAdd, int position) throws FullPlaylistException {
        if(position>=0 && position<=numSongs+1){
            if(songs[position].getTitle().equals("")){
                songs[position] = songToAdd;
                numSongs++;
                //System.out.println("Blank spot | new number of songs: "+numSongs);
            }
            else{
                boolean currIsEmpty = songs[position].getTitle().equals("");
                int startPosition = position;
                SongRecord tempSong = null;
                while(!currIsEmpty){
                    if(startPosition >= numSongs+1){


                        throw new FullPlaylistException();
                        /*
                        MAX_SONGS*=2;
                        SongRecord[] tempPlaylist = new SongRecord[MAX_SONGS];
                        System.arraycopy(songs,0,tempPlaylist,0,MAX_SONGS);
                        songs = new SongRecord[MAX_SONGS];
                        songs = tempPlaylist;
                        for(int i = MAX_SONGS/2; i<MAX_SONGS ; i++){
                            songs[i] = new SongRecord("","");
                        }
                        */
                    }
                    else{
                        tempSong = songs[startPosition]; //tempSong.clone(songs[startPosition]); // =
                        songs[startPosition] = songToAdd; // = songs[startPosition].clone(songToAdd);
                        //System.out.println("Song Moved: "+songToAdd.getTitle());
                        songToAdd = tempSong;
                        startPosition++;

                    }



                    //Check to see if the current is Empty (Ends the Loop)
                    if(songToAdd.getTitle().equals("")){
                        currIsEmpty = true;
                    }
                }
                numSongs++;
                //System.out.println("Filled spot, songs moved down | new number of songs: "+numSongs);
            }
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }


    /**
     * <dt>Preconditions:
     *  <dd>This <code>SongRecord</code> object has been instantiated and 1 < position < numSongs.
     * <dt>Postcondition:
     *  <dd>The <code>SongRecord</code> at the desired position in the <code>Playlist</code> has been removed. All <code>SongRecord</code>s
     *  <dd>that were originally in positions greater than or equal to position are moved forward one position.
     *
     * This method organizes the removal of a song at a certain position
     *
     *
     * @param position
     *      The desired position of removal
     *
     * @exception - IndexOutOfBoundsException() if the position desired if out of bounds
     * @exception - NullPointerException() if the song DNE at that position
     */
    public void removeSong(int position){
        if((position >= 0 && position <= numSongs)){
            if(songs[position].getTitle().equals("")){
                throw new NullPointerException();
            }
            else{
                //remove song
                deleteAtPosition(position);
                numSongs--;

                //shift others up
                for(int i = position; i < numSongs+1;i++){
                    songs[i] = songs[i+1];
                }
            }
        }
        else{
            throw new IndexOutOfBoundsException();
        }

    }

    /**
     * This is the actual method that removes the song
     * No checks are needed because this method is only called in the appropriate conditions
     *
     *
     * @param position
     *      Position of delete
     *
     */
    private void deleteAtPosition(int position){
        songs[position].setTitle("");
        songs[position].setArtist("");
        songs[position].setMinutes(0);
        songs[position].setSeconds(0);
    }

    /**
     * <dt>Preconditions:
     *  <dd>This <code>Playlist</code> object has been instantiated and 1 < position < numSongs.
     *
     * Order of Complexity: O(1)
     *
     * @param position
     *  The desired position
     * @return
     *  The SongRecord at the specified position in this Playlist object.
     *
     * @exception - IllegalArgumentException; Indicates that position is not within the valid range.
     */
    public SongRecord getSong(int position){
        if(position >= 0 && position <= numSongs){
            return songs[position];
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * <dt>Preconditions:
     *  <dd>The Playlist referred to by originalList has been instantiated.
     *
     *
     * Generates a new <code>Playlist</code> containing all <code>SongRecord</code>s in the original <code>Playlist</code> performed by the specified artist.
     *
     *
     * @param originalList
     *      The original <code>Playlist</code>
     * @param artist
     *      The artist to extrapolate the data by.
     *
     * @return
     *      A new <code>Playlist</code> object containing all <code>SongRecord</code>s in the original <code>Playlist</code> performed by the specified artist.
     *
     */
    public static Playlist getSongsByArtist(String name, Playlist originalList, String artist){
        Playlist newPlaylist = new Playlist(name);
        if(artist == null){
            throw new NullPointerException();
        }
        int numSongsByThatArtist = 0;
        try {
            for(int i = 0; i < originalList.size(); i++){
                if(originalList.getSong(i).getArtist().equals(artist)){
                    try{
                        newPlaylist.addSong(originalList.getSong(i), numSongsByThatArtist);
                    }
                    catch(FullPlaylistException fpe){
                        //Do Nothing, this will never be reached
                        System.out.println("Method: getSongByArtist, Exception: FullPlaylistException");
                    }
                    numSongsByThatArtist++;
                }
            }
        }
        catch(NullPointerException npe){
            throw new NullPointerException();
        }


        return newPlaylist;
    }

    /**
     * This method is responsible for returning the name of <code>this.Playlist</code>
     * 
     * @return name
     * 		The name of the current <code>Playlist</code>
     * 
     */
    public String getName(){
    	return name;
    }

    /**
     * This method was made in case the name of the <code>Playlist</code> ever want to be changed to something else
     * 
     * @param name
     */
    public void setName(String name){
    	this.name = name;
    }

    /**
     * Preconditions:
     * This <code>SongRecord</code> object has been instantiated.
     * Postcondition:
     * A neatly formatted table of each <code>SongRecord</code> in the <code>Playlist</code> on its own line with its position
     * number has been displayed to the user.
     *
     * Prints a neatly formatted table of each <code>SongRecord</code> in the <code>Playlist</code> on its own line with its position
     * number as shown in the sample output.
     */
    public void printAllSongs(){
        System.out.println(String.format("|%-5s| %-15s | %-15s |%-7s|", "Song#", "Title", "Artist", "Length"));
        System.out.println("---------------------------------------------------");
        for(int i = 0; i < numSongs; i++){
            System.out.println("| "+i+" "+" "+" | "+songs[i].toString()+" |");
        }
    }

    /**
     * This returns the maximum number off songs that a given <code>Playlist</code> can hold.
     * 
     * 
     * @return
     * 		<code>MAX_SONGS</code> is returned
     */
    public int getCapacity(){
    	return MAX_SONGS;
    }
    
    
    /**
     * This returns a T/F value based on if the passed obj is equal to this one
     * @param obj
     *      The object/<code>Playlist</code>
     * @return
     *      The boolean value
     */
    public boolean equals(Object obj){
        boolean isEqual = true;

        Playlist playlist = (Playlist) obj;
        SongRecord[] playList1Songs = this.songs;
        SongRecord[] playList2Songs = playlist.songs;

        if(obj != null && playList1Songs.length == playList2Songs.length){
            for(int i = 0; i < playList1Songs.length ; i ++){
                //Checks if the songs at index i is the same
                isEqual &= playList1Songs[i].equals(playList2Songs[i]);
            }
        }
        else{
            isEqual &= false;
        }


        return isEqual;
    }

    /**
     * Returns an exact copy of the <code>Playlist</code> that this is called on
     * @return
     *      The <code>Playlist</code> as an Object
     */
    public Object clone(){
        return new Playlist(this.songs, this.size());
    }


    /**
     * Gets the String representation of this <code>Playlist</code> object, which is a neatly formatted table of each <code>SongRecord</code>
     * in the <code>Playlist</code> on its own line with its position number as shown in the sample output.
     *
     * @return
     *      The String representation of this <code>Playlist</code> object.
     */
    public String toString(){
        String returnThis = "";
        for(int i = 0; i < numSongs; i++){
            returnThis+=("| Song "+i+" | "+songs[i].toString()+" |"+"\n");
        }

        return returnThis ;
    }

    /**
     * This method is used to see if two playlists are equal regardless of the order.
     * This helps when playlists are compared to each other, when the actual songs may be the
     * same but the order is different
     *
     * @param playlist
     *      The actual playlist that this is being compared to.
     *
     * @return
     *      A true or false value that is returned on whether if they are the same or not.
     *
     */
    public boolean equalsIgnoreOrder(Playlist playlist){
        boolean isEqual = true;
        SongRecord[] playList1Songs = this.songs;
        SongRecord[] playList2Songs = playlist.songs;
        boolean[] containsSong = new boolean[playList1Songs.length];
        for(int i = 0; i < containsSong.length; i++){
            containsSong[i] = false;
        }

        if(playlist!=null && playList1Songs.length == playList2Songs.length){
            for(int i = 0; i < playList1Songs.length ; i ++){
                for(int j = 0; j < playList2Songs.length; j++){
                    if(playList1Songs[i].equals(playList2Songs[j])){
                        containsSong[i] |= true;
                    }
                }
            }
        }
        else{
            isEqual &= false;
        }

        return isEqual;
    }


}
