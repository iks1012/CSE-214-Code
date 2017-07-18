package Homework07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import big.data.DataSource;


/**
 * The <code>KBCalculator</code> class contains the
 * main method and does all the calculations.
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class KBCalculator{
	
	static String trueTitle;
	static String[] tempActors;
	static int year;
	static ActorGraph aGraph = new ActorGraph();
	static List<String> actorNames = new ArrayList<String>();
	static List<String> movieNames = new ArrayList<String>();
	static Scanner in = new Scanner(System.in);
	
	/**
	 * This is the main method of the program
	 * 
	 * This basically delegates the user input to the desires of said user
	 * 
	 * It just calls the methods that do the series of tasks that each action requires
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println("Welcome to the Kevin Bacon Calculator!");
		boolean isRunning = true;
		char choice;
		menu();
		while(isRunning){
			
			try{
				choice = in.nextLine().toUpperCase().charAt(0);
			}
			catch(Exception e){
				choice = 'Z';
			}
			if(choice == 'I'){
				choiceI();
			}
			else if(choice == 'A'){
				choiceA();
			}
			else if(choice == 'M'){
				choiceM();
			}
			else if(choice == 'P'){
				choiceP();
			}
			else if(choice == 'B'){
				choiceB();
			}
			else if(choice == 'L'){
				choiceL();
			}
			else if(choice == 'Q'){
				isRunning = false;
			}
			else{
				pln("Please enter a valid choice! ");
			}
		}
	}
	
	/**
	 * This the first choice of the Menu, in this you can import movies from the OMDB libraries
	 * 
	 * 
	 */
	public static void choiceI(){
		try{
			String title = "";
			p("Enter a movie title: ");
			title = in.nextLine();
			if (title.length() > 0) {
				String prefix = "http://www.omdbapi.com/?t=";
				String postfix = "&y=&plot=short&r=xml";
				if (title.length() > 0) {
					DataSource ds = DataSource.connectXML(prefix + title.replace(' ', '+') + postfix);
					ds.load();
					trueTitle = ds.fetchString("movie/title");
					tempActors = ds.fetchString("movie/actors").split(", ");
					year = ds.fetchInt("movie/year");
					pln("Movie Details -------------------------------------------");
					showImportedMovieDetails();
					addMovieAndActors();
				}
			}
		}
		catch(Exception e){
			pln("Invalid Input - Try again!");
		}
	}
	
	/**
	 * This method sets up the printing of all the <code>Actor</code>'s that were imported since the
	 * start of the program
	 */
	public static void choiceA(){
		pln("Actors: ");
		for(int actorNumber = 0; actorNumber < actorNames.size(); actorNumber++){
			pln(actorNames.get(actorNumber));
		}
	}
	
	/**
	 * This method sets up the printing of all the <code>Movies</code>'s that were imported since the
	 * start of the program
	 */
	public static void choiceM(){
		pln("Movies: ");
		for(int movieNumber = 0; movieNumber < movieNames.size(); movieNumber++){
			pln(movieNames.get(movieNumber));
		}
	}
	
	/**
	 * This method basically finds the shortest path between two names. 
	 * 
	 * All this is doing is call upon another method that does it.
	 */
	public static void choiceP(){
		try{
			p("First Actor: ");
			String name1 = in.nextLine();
			
			p("Second Actor: ");
			String name2 = in.nextLine();
			
			shortestPath(name1, name2);
		}
		catch(Exception e){
			pln("Name(s) Not Found!");
		}
	}
	
	/**
	 * This is the method that finds the shortestPath between two actors in the graph of actors.
	 * 
	 * It just prints the path if there is one, and if there isn't, it says, "no path"
	 * 
	 * @param name1, first name in the list.
	 * @param name2, destination name in the list.
	 */
	public static void shortestPath(String name1, String name2) {
		boolean shouldTheLoopContinue = true;
		int v = 0;
		Actor[] connections = aGraph.getActor(name1).getAllFriends();
		int i;
		Actor temp;
		Actor next;
		Queue<Actor> q = new LinkedList<Actor>();
		connections[v].setVisited(true);
		q.add(connections[v]);
		while ((!q.isEmpty())&&(shouldTheLoopContinue)) { 
			temp = q.remove();
			if(!name1.equals(temp.getName())){
				
				p(temp.getName()+", ");
				if(temp.getName().equals(name2)){
					shouldTheLoopContinue = false;
				}
				
			}
			connections = temp.getAllFriends();
			
			for (i = 0; (i < connections.length) && (shouldTheLoopContinue); i++) {
				next = connections[i];
				if (!next.getVisited()) {
					next.setVisited(true);
					q.add(next);
				}
			}
		}
		pln("");
		if(shouldTheLoopContinue){
			pln("No Path!");
		}
	}
	
	
	/**
	 * This choice basically initiates the Breadth-First Traversal of the Desired name
	 * 
	 * It just instantiates it
	 * 
	 * 
	 */
	public static void choiceB(){
		try{
			pln("Enter Actors name for BFT");
			String temp = in.nextLine();
			BFS(temp);
		}
		catch(Exception e){
			System.out.println("Not Found!");
		}
	}
	
	
	/**
	 * This method basically prints the BFS of a given name
	 * 
	 * 
	 * 
	 * @param name, the desired starting point for the BreadthFirst Traversal
	 */
	public static void BFS(String name) {
		int v = 0;
		Actor[] connections = aGraph.getActor(name).getAllFriends();
		int i;
		Actor temp;
		Actor next;
		Queue<Actor> q = new LinkedList<Actor>();
		connections[v].setVisited(true);
		q.add(connections[v]);
		while (!q.isEmpty()) {
			temp = q.remove();
			if(!name.equals(temp.getName())){
				p(temp.getName()+", ");
			}
			connections = temp.getAllFriends();
			for (i = 0; i < connections.length; i++) {
				next = connections[i];
				if (!next.getVisited()) {
					next.setVisited(true);
					q.add(next);
				}
			}
		}
		pln("");
	}
	
	/**
	 * This Looks for a specific <code>Actor</code> that is in the database of all <code>Actor</code>'s
	 * 
	 * It also displays all of the <code>Actor</code>'s friends and all of the <code>Actor</code>'s
	 * <code>Movie</code>s that the <code>Actor</code> is associated with.
	 * 
	 */
	public static void choiceL(){
		try{
			pln("Input Actor name: ");
			String name = in.nextLine();
			Actor temp = aGraph.getActor(name);
			pln("Actor: "+temp.getName());
			Actor[] friends = temp.getAllFriends();
			Movie[] movies = temp.getAllMovies();
			p("Friends: ");
			for(int friendNumber = 0; friendNumber < temp.getNumFriends(); friendNumber++){
				p(friends[friendNumber].getName());
				if(friendNumber + 1 == temp.getNumFriends()){
					pln(". ");
				}
				else{
					p(", ");
				}
			}
			
			p("Movies: ");
			for(int movieNumber = 0; movieNumber < temp.getNumMovies(); movieNumber++){
				p(movies[movieNumber].getTitle()+" ("+movies[movieNumber].getYear()+")");
				if(movieNumber + 1 == temp.getNumMovies()){
					pln(". ");
				}
				else{
					p(", ");
				}
			}
			
			
		}
		catch(Exception e){
			pln("Invalid input/Actor Not Found!");
		}
	}
	
	
	/**
	 * 
	 *
	 * Looks at the imported things, and adds whatever is not there.
	 * 
	 * If the <code>Movie</code> is new to the data base, all the <code>Actor</code>s are added to each others 
	 * <code>friend</code> HashSet of each <code>Actor</code>.
	 * 
	 * Repeats/Duplicates are ignored.
	 * 
	 */
	public static void addMovieAndActors(){
		int numActors = tempActors.length;
		Movie tempMovie = new Movie(trueTitle);
		tempMovie.setYear(year);
		Actor[] actors = new Actor[numActors];
		
		

		//This part is for the adding of the Movie
		if(aGraph.containsMovie(trueTitle)){
			pln("Movie Already Imported!");
		}
		else{
			//This is for the Actors adding each other as friends/links
			for(int actorNumber = 0; actorNumber < numActors; actorNumber++){
				if(aGraph.containsActor(tempActors[actorNumber])){
					actors[actorNumber] = aGraph.getActor(tempActors[actorNumber]);
					aGraph.removeActor(tempActors[actorNumber]);
				}
				else{
					actors[actorNumber] = new Actor();
					actors[actorNumber].setName(tempActors[actorNumber]);
				}
				actors[actorNumber].addMovie(tempMovie);
			}
			//pln("Actors obtained!");
			
			
			for(int actorNumber = 0; actorNumber < numActors; actorNumber++){
				for(int friendNumber = 0; friendNumber < numActors; friendNumber++){
					if(actorNumber == friendNumber){
						//the same person
					}
					else{
						actors[actorNumber].addFriend(actors[friendNumber]);
					}
				}
			}
			//pln("Friends Added!");
			
			
			for(int actorNumber = 0; actorNumber<numActors; actorNumber++){
				aGraph.addActor(actors[actorNumber].getName(), actors[actorNumber]);
				actorNames.add(actors[actorNumber].getName());
			}
			//pln("Actors Updated!");
			
			
			//Now for adding the movie
			tempMovie.setYear(year);
			for(int actorNumber = 0; actorNumber<numActors; actorNumber++){
				tempMovie.addActor(actors[actorNumber]);
				
			}
			
			aGraph.addMovie(trueTitle, tempMovie);
			movieNames.add(trueTitle);
			//pln("All Details added to Graph!");
		}
		
		removeDupes();
		
		Collections.sort(movieNames);
		
		Collections.sort(actorNames);
		
	}
	
	
	/**
	 * Removes all the duplicates from the local lists, within which repeats exist.
	 * 
	 */
	public static void removeDupes(){
		//for the movies
		HashSet<String> movies = new HashSet<>();
		movies.addAll(movieNames);
		movieNames.clear();
		movieNames.addAll(movies);
		
		//for the actors
		HashSet<String> actors = new HashSet<>();
		actors.addAll(actorNames);
		actorNames.clear();
		actorNames.addAll(actors);
	}
	
	/**
	 * After a <code>Movie</code> is imported, the console needs to display all the movies details, 
	 * 
	 * It does just that.
	 */
	public static void showImportedMovieDetails(){
		pln("Title: "+trueTitle);
		
		p("Actors: ");
		for(int i = 0; i < tempActors.length; i++){
			p(tempActors[i]+", ");
		}
		pln("");
		
		pln("Year: " + year);
	}
	
	/**
	 * Standard menu layout
	 */
	public static void menu(){
		pln("Options: ");
		pln("  I) Import a Movie");
		pln("  A) Print all Actors");
		pln("  M) Print all Movies");
		pln("  P) Print the shortest path between two actors");
		pln("  B) Print the BFS from a given actor");
		pln("  L) Look up actor by name");
		pln("  Q) Quit");
	}
	
	
	
	/**
	 * 
	 * These next two methods are just custom print methods to make my life easier in terms of keystrokes
	 *
	 * pln prints and moves onto the next line,
	 * 
	 * p prints and stays on the current line.
	 */
	public static void pln(String printThis){
		System.out.println(printThis);
	}
	
	public static void p(String printThis){
		System.out.print(printThis);
	}
	
	
}
