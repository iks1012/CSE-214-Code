package Homework02;

public class OrderListNode {
	private Order order;
	private OrderListNode next;
	private OrderListNode prev;
	
	
	public OrderListNode(){
		order = new Order("","",0.00);
		next = null;
		prev = null;
		
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order data) {
		this.order = data;
	}
	public OrderListNode getNext() {
		return next;
	}
	public void setNext(OrderListNode next) {
		this.next = next;
	}
	public OrderListNode getPrev() {
		return prev;
	}
	public void setPrev(OrderListNode prev) {
		this.prev = prev;
	}
	
}
