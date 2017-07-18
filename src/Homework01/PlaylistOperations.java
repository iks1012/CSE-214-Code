import java.util.Scanner;

/**
 * The <code>PlaylistOperations</code> Java application tests the methods of the <code>Playlist</code> class and allows
 * the user to manipulate a single (or more, as per extra credit) <code>Playlist</code> by performing operations on it.
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class PlaylistOperations {

    
	static int maxPlaylists = 10;
    static Playlist[] allPlaylists = new Playlist[maxPlaylists];
    static int numPlaylists = 0;
    static int currentPlaylist = 0;

    /**
     * This is the main method that manages the <code>Playlist</code> class and the <code>SongRecord</code> class
     * @param args
     */
    public static void main(String[] args){
        boolean isRunning = true;
        allPlaylists[currentPlaylist] = new Playlist("Default Playlist");
        numPlaylists++;
        
        pln();
        pln("Max number of playlists allowed: "+maxPlaylists);
        pln("Max number of songs per playlist: "+allPlaylists[currentPlaylist].getCapacity());
        Scanner in = new Scanner(System.in);
        
        while(isRunning){
            printMenu();
            char choice;
            try{
                choice = in.nextLine().toUpperCase().charAt(0);
                pln("---------------------------------------------------");
            }
            catch(Exception e){
                pln("Invalid Input!");
                choice = 0;

            }


            if(choice == 'A'){
                try{
                    p("Enter the song title: ");String title = in.nextLine();
                    p("Enter the song artist: ");String artist = in.nextLine();
                    p("Enter the song length (minutes): ");int minutes = in.nextInt();
                    p("Enter the song length (seconds): ");int seconds = in.nextInt();
                    p("Enter the position (different from index): ");int index = in.nextInt()-1;in.nextLine();
                    SongRecord songToAdd = new SongRecord(title, artist);
                    songToAdd.setMinutes(minutes);
                    songToAdd.setSeconds(seconds);
                    allPlaylists[currentPlaylist].addSong(songToAdd, index);
                    pln("Song Added: "+title+" By "+artist);
                }
                catch(IndexOutOfBoundsException iobe){
                    pln("The values you entered were out of bounds");
                }
                catch(IllegalArgumentException iae){
                    pln("Illegal Argument");
                }
                catch(FullPlaylistException fpe){
                    pln("Playlist is full!!");
                }
                catch(Exception e){
                    pln("Enter something valid!!");
                }

            }
            else if(choice == 'B'){
                try{
                    p("Enter Artist: ");String artist = in.nextLine();
                    p("Enter the name of the new Playlist: "); String name = in.nextLine();
                    int newListIndex = numPlaylists++;
                    allPlaylists[newListIndex] = Playlist.getSongsByArtist(name, allPlaylists[currentPlaylist], artist);
                    allPlaylists[newListIndex].printAllSongs();
                }
                catch(NullPointerException npe){
                    pln("Either no songs were added or the artist is null");
                }
                catch(Exception e){
                	pln("Exception");
                }
            }
            else if(choice == 'C'){
            	try{
            		pln("Current playlist name: "+allPlaylists[currentPlaylist].getName());
            		p("Enter the name of the new playlist with the exact songs of the current playlist"); String name = in.nextLine();
            		Playlist temp =  (Playlist) allPlaylists[currentPlaylist].clone();
            		temp.setName(name);
            		allPlaylists[numPlaylists] = temp;
            		numPlaylists++;
            		pln("The name of the current playlist is still "+allPlaylists[currentPlaylist].getName());
            	}
            	catch(Exception e){
            		pln("Error with copying the playlist into a new one, Try again");
            	}
            	
            }
            else if(choice == 'D'){
            	pln("All the playlists: ");
            	for(int i = 0; i < numPlaylists; i++){
            		pln((i+1)+". "+allPlaylists[i].getName());
        		}
            }
            else if(choice == 'E'){
                try{
                    p("Enter the name of the playlist you would wish to compare "+allPlaylists[currentPlaylist].getName()+" to: ");String compareName = in.nextLine();
                    int compareIndex = getPlaylistIndex(compareName);
                    if(compareIndex==-1){
                        pln("The name you entered does not exist.");
                    }
                    else {
                        int sizeComparison = allPlaylists[currentPlaylist].size() - allPlaylists[compareIndex].size();
                        if (sizeComparison == 0) {
                            if (allPlaylists[currentPlaylist].equals(allPlaylists[compareIndex])) {
                                pln("The Playlists have the same number of songs and the songs are all the same and in the same order");
                            } else {
                                if (allPlaylists[currentPlaylist].equalsIgnoreOrder(allPlaylists[compareIndex])) {
                                    pln("Every song in the current playlist is contained within " + allPlaylists[compareIndex].getName());
                                } else {
                                    pln("The playlists have the same number of songs but the content isn't the same.");
                                }
                            }
                        } else if (sizeComparison > 0) {
                            //curr is greater
                            pln(allPlaylists[currentPlaylist].getName() + " has more songs.");
                        } else if (sizeComparison < 0) {
                            //compare is greater
                            pln(allPlaylists[compareIndex].getName() + " has more songs.");
                        }
                    }
                }
                catch(Exception e){
                    pln("Please input something valid.");
                }
            }
            else if(choice == 'G'){
                try{
                    p("Enter the position (different from index) of the song you wish to retrieve in the current playlist: "); int position = in.nextInt()-1;
                    in.nextLine();
                    SongRecord tempSong = allPlaylists[currentPlaylist].getSong(position);
                    pln(tempSong.toString());
                }
                catch(IllegalArgumentException iae){
                    pln("The position that you entered is out of bounds");
                }
            }
            else if(choice == 'R'){
                p("Enter the position (different from index) of the song that you wish to enter: ");
                try{
                    int index = in.nextInt()-1;
                    in.nextLine();
                    String removedSong = allPlaylists[currentPlaylist].getSong(index).getTitle();
                    allPlaylists[currentPlaylist].removeSong(index);
                    pln(removedSong+" was removed from the playlist");
                }
                catch(IndexOutOfBoundsException iobe){
                    pln("The position you entered resulted in an out of bound index");
                }
                catch(NullPointerException npe){
                    pln("You picked a position with no song, above numSongs, but below the playlist capacity");
                }
                catch(Exception e){
                    pln("Invalid input!");
                }
            }
            else if(choice == 'N'){
            	if(numPlaylists == maxPlaylists){
            		pln("Playlist capacity reached!");
            	}
            	else{
                	try{
                    	pln("Current Playlist: "+allPlaylists[currentPlaylist].getName());
                    	p("Enter the name of the new Playlist: "); String newName = in.nextLine();
                    	int index = getPlaylistIndex(newName);
                    	if(index == -1){
                    		currentPlaylist = numPlaylists++;
                        	allPlaylists[currentPlaylist] = new Playlist(newName);
                        	pln(newName+" was created and set as the current playlist");
                    	}
                    	else{
                    		pln("You need to enter a distinct playlist name!");
                    	}
                    	
                	}
                	catch(Exception e){
                		pln("Error with creating a new playlist");
                	}
            	}
            }
            else if(choice == 'V'){
            	try{
            		p("Enter the name of the playlist you wish to switch to: "); String name = in.nextLine();
            		//Search all playlists name
            		int index = getPlaylistIndex(name);
            		
            		if(index == -1){
            			pln("The playlist "+name+" does not exist, please create it first.");
            		}
            		else{
            			currentPlaylist = index;
            			pln(name+" is the current playlist.");
            		}	
            	}
            	catch(Exception e){
            		pln("Error with changing the playlist");
            		
            	}
            }
            else if(choice == 'P'){
            	allPlaylists[currentPlaylist].printAllSongs();
            }
            else if(choice == 'S'){
                pln("There are "+allPlaylists[currentPlaylist].size()+" songs in the current playlist");
            }
            else if(choice == 'Q'){
                isRunning = false;
            }
            else{
                pln("Wrong Input, Try Agains");
            }

            pln("---------------------------------------------------");
        }


    }
    /**
     * There are multiple instances in the program where the user picks a playlist from the name,
     * here is the method that finds the corresponding playlist in the array.
     * 
     * @param name
     * 		The name of the playlist that the user wishes to manipulate/change
     * 
     * @return
     * 		The Index of the playlist in the array of playlists.
     */
    public static int getPlaylistIndex(String name){
    	int indexOfPlaylist = -1;
		for(int i = 0; i < numPlaylists; i++){
			if(allPlaylists[i]!=null && allPlaylists[i].getName().equals(name)){
				indexOfPlaylist = i;
			}
		}
		return indexOfPlaylist;
    }
    

    /**
     * This is just a method that helps me keep the main method as clean as possible
     */
    public static void printMenu(){
    	pln("------------------Standard Options-----------------");
        pln("A) Add Song");
        pln("B) Print Songs By Artist");
        pln("G) Get Song");
        pln("R) Remove Song");
        pln("P) Print all Songs");
        pln("S) Get Size of the current playlist");
        pln("Q) Quit");
    	pln("----------------Extra Credit Options---------------");
        pln("N) Create a new Playlist and set as the selected playlist");
        pln("C) Duplicate current playlist into a new one");
        pln("D) Display all playlist names");
        pln("E) Compare the songs of the current playlist to another one");
        pln("V) Change the current playlist ");
        pln("---------------------------------------------------");
        p("Select a menu option: ");



    }

    /**
     * Helps with typing speed
     * 
     * @param printThis
     */
    public static void pln(String printThis){
        System.out.println(printThis);
    }

    /**
     * Helps with typing speed
     */
    public static void pln(){
        System.out.println();
    }

    /**
     * Helps with typing speed
     * 
     * @param printThis
     */
    public static void p(String printThis){
        System.out.print(printThis);
    }
}
