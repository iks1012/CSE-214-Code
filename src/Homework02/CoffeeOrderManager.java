package Homework02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoffeeOrderManager {
	private static boolean isRunning = true;
	static OrderList barista1 = new OrderList();
	static OrderList barista2 = new OrderList();
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args){
		boolean validChoice = false;
		
		String input = "";
		char choice = '\0';
		
		while(isRunning){
			choice = mainMenu();
			if(choice!='0'){
				switch(choice){
				case 'O': order() ;
					break;
				case 'P': print();
					break;
				case 'E': extra();
					break;
				case 'C': cursorOptions();
					break;
				case 'Q': quit();
					break;
				}
			}
			else{
				System.out.println("Input Error: Case Matters by the way");
			}
			
			
		}
	}
	
	
	public static void order(){
		try{
			System.out.println("Drink Name: ");
			String name = in.nextLine();
			System.out.println("Price: ");
			double price = in.nextDouble();
			System.out.println("Special Instructions: ");
			String specIns = in.nextLine();
			System.out.println("Barista 1 or 2?: ");
			int barista = in.nextInt();
			System.out.println("F - Front of list, B - Back, A - After Cursor, S (Did not Implement): ");
			char location = in.nextLine().charAt(0);
			if(location == 'B'){
				if(barista == 1){
					barista1.appendToTail(new Order(name, specIns, price));
				}
				else if(barista ==2 ){
					barista2.appendToTail(new Order(name, specIns, price));
				}
			}
			if(location == 'A'){
				if(barista == 1){
					barista1.insertAfterCursor(new Order(name, specIns, price));
				}
				else if(barista ==2 ){
					barista2.insertAfterCursor(new Order(name, specIns, price));
				}
			}
			if(location == 'F'){
				if(barista == 1){
					barista1.resetCursorToHead();
					barista1.insertAfterCursor(new Order(name, specIns, price));
				}
				else if(barista ==2 ){
					barista2.resetCursorToHead();
					barista2.insertAfterCursor(new Order(name, specIns, price));
				}
			}
		}
		catch(InputMismatchException i){
			System.out.println("Follow the correct input pls, string for name, double for price and string for the spec. Ins.");
		}
		
		
		
	}
	
	public static void print(){
		System.out.println("Barista 1: ");
		for(int i = 0; i < barista1.numOrders(); i++){
			System.out.println("Order"+(i+1)+": " + barista1.getCursor().getOrder() +"| Price: "+barista1.getCursor().getPrice() +"| Special Instructions: "+barista1.getCursor().getSpecialInstruction() );
			barista1.cursorForward();
		}
		System.out.println("Barista 2: ");
		for(int i = 0; i < barista1.numOrders(); i++){
			System.out.println("Order"+(i+1)+": " + barista2.getCursor().getOrder() +"| Price: "+barista2.getCursor().getPrice() +"| Special Instructions: "+barista2.getCursor().getSpecialInstruction() );
			barista2.cursorForward();
		}
	}
	
	public static void extra(){
		//did not implement
	}
	
	public static void cursorOptions(){
		
	}
	
	public static void quit(){
		System.exit(0);
	}
	
	public static char mainMenu(){
		System.out.println("Main Menu---------------------------");
		System.out.println("O) Order");
		System.out.println("P) Print Order Lists");
		//System.out.println("E) Extra Credit Functions");
		System.out.println("C) Cursor Options");
		System.out.println("Q) Quit");
		char option = in.nextLine().charAt(0);
		if(mmCorrectChoice(option)){
			return option;
		}
		else{
			return '0';
		}
	}
	public static boolean mmCorrectChoice(char checkThis){
		boolean returnThis = true;
		if(checkThis == 'O'){
			returnThis = true;
		}
		//else if(checkThis == 'E'){
		//	returnThis = true;
		//}
		else if(checkThis == 'C'){
			returnThis = true;
		}
		else if(checkThis == 'P'){
			returnThis = true;
		}
		else if(checkThis == 'Q'){
			returnThis = true;
		}
		else {
			returnThis = false;
		}
		
		return returnThis;
	}
}
