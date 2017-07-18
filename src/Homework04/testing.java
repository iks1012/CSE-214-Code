package Homework04;

import java.util.InputMismatchException;
import java.util.Scanner;

public class testing {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the Office Hours Simulation.");
		System.out.println("Would you like to assign the base \n variables manually or from a file?");
		System.out.println("F - For file; M - Manually");
		char fileOrMan = in.nextLine().charAt(0);
		Simulation dayOne = null;
		if(fileOrMan == 'F' || fileOrMan == 'f'){
			System.out.print("Enter the file name: ");
			String fileName = in.nextLine();
			 dayOne = new Simulation(fileName);
		}
		else if(fileOrMan == 'M' || fileOrMan == 'm'){//Implement
			System.out.println("");
		}
		else{
			System.out.println("Try Again with the correct input.");
		}
		char choice;
		boolean simulating = true;
		int currentTime = 1;
		while(simulating){
			System.out.println("Minute "+currentTime);
			System.out.println("Time PAUSED");
			System.out.println("S - Move up Time Step");
			choice = in.nextLine().charAt(0);
			if(choice == 'S' || choice == 's'){
				currentTime++;
				
				Simulation.simulate(currentTime);
				
				
			}
			if(currentTime == dayOne.simulationTime){
				simulating = false;
			}
		}
		
		
		
	}
}
