package Homework02;

public class OrderList {
	private OrderListNode head;
	private OrderListNode tail;
	private OrderListNode cursor;
	private int orders;
	
	public OrderList(){
		head = new OrderListNode();
		head.setNext(tail);
		tail = new OrderListNode();
		tail.setPrev(head);
		cursor = head;
		orders = 0;
	}
	
	public int numOrders(){
		return orders;
	}
	
	public Order getCursor(){
		return cursor.getOrder();
	}
	
	public void resetCursorToHead(){
		cursor = head;
	}
	
	public void cursorForward(){
		try{
			cursor = cursor.getNext();
		}
		catch(NullPointerException e){
			System.out.println("Cursor cant move ahead because no more links");
		}
	}
	
	public void cursorBackward(){
		try{
			cursor = cursor.getPrev();
		}
		catch(NullPointerException e){
			System.out.println("Cursor cant move back because no more links");
		}
		
	}
	
	public void insertAfterCursor(Order newOrder){
		try{
			if(cursor.getNext()!=null){
				orders++;
				OrderListNode newNode = new OrderListNode();
				cursor.getNext().setPrev(newNode);
				newNode.setNext(cursor.getNext());
				newNode.setPrev(cursor);
				cursor.setNext(newNode);
			}
			else{
				throw new EndOfListException();
			}
		}
		catch(EndOfListException e){
			System.out.println("You Tried to insert something to the end, use the append method instead");
		}
	}
	
	public void appendToTail(Order newOrder){
		try{
			orders++;
			OrderListNode newNode = new OrderListNode();
			newNode.setOrder(newOrder);
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		catch(IllegalArgumentException i){
			System.out.println("You tried to append something to the list but entered an Illegal Argument");
		}
	}
	
	public Order removeCursor(){//order--;
		Order temp = cursor.getOrder();
		
		return temp;
	}
	
	
	
	
}
