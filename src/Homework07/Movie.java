package Homework07;
import java.util.HashSet;


/**
 * The <code>Movie</code> class implements the 
 * <code>Movie</code> object
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class Movie{
	//The title
	private String title;
	
	//All the actors in the Movie
	private HashSet<Actor> actors = new HashSet<>();
	
	//The Year this film was made
	private int year;
	
	
	/**
	 * This returns an instance of the <code>Movie</code> object. This can be initialized with 
	 * the title to be set as <code>name</code>
	 * 
	 * @param name  This is the name
	 * 
	 * 
	 */
	public Movie(String name){
		title = name;
	}
	
	/**
	 * 
	 * This is the setter method for the <code>title</code> variable.
	 * 
	 * This isnt really needed but in the event the title needs to be changed the method is here.
	 * 
	 * @param name this is the name of the title for said <code>Movie</code>.
	 */
	public void setTitle(String name){
		this.title = name;
	}
	
	
	/**
	 * 
	 * This is the getter method for the <code>title</code> variable.
	 *
	 * @return's the current title of said <code>Movie</code>.
	 */
	public String getTitle(){
		return title;
	}
	
	
	/**
	 * This method is used to set the year of when the <code>Movie</code> was made.
	 * 
	 * 
	 * @param year, this is the year of the <code>Movie</code>
	 */
	public void setYear(int year){
		this.year = year;
	}
	
	/**
	 * This method is used to get the year of when the <code>Movie</code> was made.
	 * 
	 * 
	 * @returns the year, this is the year of the <code>Movie</code>
	 */
	public int getYear(){
		return year;
	}
	
	/**
	 * For said <code>Movie</code>, there are many <code>Actors</code>s for the <code>Movie</code>, so those are added
	 * 
	 * @param actor that is added.
	 */
	public void addActor(Actor actor){
		if(actors.contains(actor)){
			throw new IllegalArgumentException();
		}
		else{
			actors.add(actor);
		}
	}
	
	/**
	 * For any given <code>Movie</code>, this method returns an array of all the actors in it
	 * This is good for listing what <code>Actor</code>s were in a <code>Movie</code>.
	 * 
	 * @return's the array of <code>Actor</code>'s
	 */
	public Actor[] getAllActors(){
		Object[] tempActors = actors.toArray();
		Actor[] allActors = new Actor[tempActors.length];
		
		for(int tempActorIndex = 0; tempActorIndex < tempActors.length ; tempActorIndex++ ){
			allActors[tempActorIndex] = (Actor) tempActors[tempActorIndex];
		}
		return allActors;
	}
	
}
