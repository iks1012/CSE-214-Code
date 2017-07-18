package Homework07;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * The <code>Actor</code> class implements the 
 * <code>Actor</code> object
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class Actor{
	
	//The name.
	private String name;
	
	//The Movies.
	private LinkedHashSet<Movie> movies = new LinkedHashSet<>();
	
	//All the Friends.
	private LinkedHashSet<Actor> friends = new LinkedHashSet<>();
	
	//Visited or not, used for traversing.
	private boolean visited;
	
	//Used to find the shortest path.
	private LinkedList<String> path;

	
	/**
	 * This is the setter for the <code>Actor</code> object
	 * you can set it with a name
	 * 
	 * 
	 * @param name to set it with
	 */
	public void setName(String name){
		this.name = name;
	}
	
	
	
	/**
	 * This is the getter method for the <code>name</code> instance variable
	 * 
	 * @return's the current name
	 */
	public String getName(){
		return name;
	}
	
	
	public void setPath(LinkedList<String> tempPath){
		path = tempPath;
	}
	
	
	public LinkedList<String> getPath(){
		
		return path;
	}
	
	
	/**
	 * This is the setter method
	 * 
	 * @param isVisited
	 */
	public void setVisited(boolean isVisited){
		this.visited = isVisited;
	}
	
	
	
	
	/**
	 * When called upon, this method tells us if said <code>Actor</code> has been visited or not
	 * 
	 * This is used for traversals involving graphs
	 * 
	 * @return's the boolean value <code>visited</code>
	 */
	public boolean getVisited(){
		return visited;
	}
	
	/**
	 * 
	 * When called upon, it adds <code>movie</code> to the <code>movies</code> HashSet
	 * 
	 * @param movie this is of type <code>Movie</code> and this is added to the <code>movies</code> HashSet
	 */
	public void addMovie(Movie movie){
		if(movies.contains(movie)){
			//Dont Add it
		}
		else{
			movies.add(movie);
		}
	}
	
	
	/**
	 * returns an array of all the <code>Movie</code>'s that the <code>Actor</code> has taken part in
	 * 
	 * converts it from and array of objects that is obtained by the HashSet.
	 * 
	 * 
	 * 
	 * @return's an array of all the <code>Movie</code>'s that the <code>Actor</code> has taken part in
	 * 
	 */
	public Movie[] getAllMovies(){
		Object[] tempMovies = movies.toArray();
		Movie[] allMovies = new Movie[tempMovies.length];
		
		for(int tempMovieIndex = 0; tempMovieIndex < tempMovies.length ; tempMovieIndex++ ){
			allMovies[tempMovieIndex] = (Movie) tempMovies[tempMovieIndex];
		}
		
		return allMovies;
	}
	
	
	
	/**
	 * This method adds an <code>Actor</code> to the HashSet of all the main <code>Actor</code>'s coworkers
	 * 
	 * 
	 * @param friend is the actual <code>Actor</code> that is added to the HashSet
	 */
	public void addFriend(Actor friend){
		if(friends.contains(friend)){
			//Dont Add it
		}
		else{
			friends.add(friend);
		}
	}
	
	/**
	 * returns an array of all the <code>Actor</code>'s that the <code>Actor</code> has filmed with
	 * 
	 * converts it from and array of objects that is obtained by the HashSet.
	 * 
	 * 
	 * 
	 * @return's an array of all the <code>Actor</code>'s that the <code>Actor</code> has filmed with
	 * 
	 */
	public Actor[] getAllFriends(){
		Object[] tempFriends = friends.toArray();
		Actor[] allFriends = new Actor[tempFriends.length];
		
		for(int tempFriendIndex = 0; tempFriendIndex < tempFriends.length ; tempFriendIndex++ ){
			allFriends[tempFriendIndex] = (Actor) tempFriends[tempFriendIndex];
		}
		
		
		return allFriends;
	}

	/**
	 * This method returns the size of the HashSet of <code>Actor</code>'s. This also tells
	 * the user the amount of people he has worked with.
	 * 
	 * @return's the number of friends
	 */
	public int getNumFriends(){
		return friends.size();
	}
	
	
	
	/**
	 * This method returns the size of the HashSet of <code>Movie</code>'s. This also tells
	 * the user the amount of <code>Movie</code>'s he has starred in.
	 * 
	 * @return's the number of movies.
	 */
	public int getNumMovies(){
		return movies.size();
	}
	
}
