package Homework07;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * The <code>ActorGraph</code> class implements the 
 * <code>Movie</code> database and the <code>Actor</code>
 * database.
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class ActorGraph {
	private static HashMap<String, Actor> actorsByName = new HashMap<String,Actor>();
	private static HashMap<String, Movie> moviesByTitle = new HashMap<String,Movie>();
	
	
	/**
	 * This method is supposed to return the list of names for the BFS, but I was able to overcome this by implementing it differently in the KBCalculator class.
	 * 
	 * 
	 * @param name, this name is the desired name for the BFS.
	 * @return
	 */
	public static LinkedList<String> bfs(String name){
		//UnImplemented
		return null;
	}
	
	
	/**
	 * This adds an <code>Actor</code> to the database of <code>Actor</code>s
	 * 
	 * @param name, this is the key that the object is stored by
	 * @param actor, this is the actual object that is stored in the map
	 * 
	 * @exception If the actor already exists, an IllegalArgumentException is Thrown. this is avoided through certain checks done throughout the program,
	 * but its there in case I forget.
	 */
	public void addActor(String name, Actor actor){
		if(actorsByName.get(name) != null){
			throw new IllegalArgumentException();
		}
		else{
			actorsByName.put(name, actor);
		}
	}
	
	
	/**
	 * This adds a <code>Movie</code> to the database of <code>Movie</code>s
	 * 
	 * @param name, this is the key that the object is stored by
	 * @param movie, this is the actual object that is stored in the map
	 * 
	 * @exception If the <code>Movie</code> already exists, an IllegalArgumentException is Thrown. this is avoided through certain checks done throughout the program,
	 * but its there in case I forget.
	 */
	public void addMovie(String name, Movie movie){
		if(moviesByTitle.get(name) != null){
			throw new IllegalArgumentException();
		}
		else{
			moviesByTitle.put(name, movie);
		}
	}
	
	
	/**
	 * This gets the <code>Movie</code> at the desired key
	 * @param name, the key
	 * @return, the <code>Movie</code> that is returned.
	 */
	public Movie getMovie(String name){
		return moviesByTitle.get(name);
	}
	
	/**
	 * This gets the <code>Actor</code> at the desired key
	 * @param name, the key
	 * @return, the <code>Actor</code> that is returned.
	 */
	public Actor getActor(String name){
		return actorsByName.get(name);
	}
	
	
	/**
	 * This just checks if the database has a certain <code>Actor</code> by the name of <code>name</code>
	 * @param name, name of the <code>Actor</code>
	 * @return's a true or false if it is there or not
	 */
	public boolean containsActor(String name){
		return actorsByName.containsKey(name);
	}
	
	
	/**
	 * This just checks if the database has a certain <code>Movie</code> by the title of <code>title</code>
	 * @param title, title of the <code>Movie</code>
	 * @return's a true or false if it is there or not
	 */
	public boolean containsMovie(String title){
		return moviesByTitle.containsKey(title);
	}
	
	
	
	/**
	 * This method removes a <code>Movie</code> based on a certain key <code>name</code>
	 * @param name, the key
	 * 
	 * @exception If the <code>Movie</code> does not exist, an IllegalArgumentException is Thrown. this is avoided through certain checks done throughout the program,
	 * but its there in case I forget.
	 */
	public void removeMovie(String name){
		if(moviesByTitle.get(name) == null){
			throw new IllegalArgumentException();
		}
		else{
			moviesByTitle.remove(name);
		}
	}
	
	/**
	 * This method removes a <code>Actor</code> based on a certain key <code>name</code>
	 * @param name, the key
	 * 
	 * @exception If the <code>Actor</code> does not exist, an IllegalArgumentException is Thrown. this is avoided through certain checks done throughout the program,
	 * but its there in case I forget.
	 */
	public void removeActor(String name){
		if(actorsByName.get(name) == null){
			throw new IllegalArgumentException();
		}
		else{
			actorsByName.remove(name);
		}
	}
	
}
