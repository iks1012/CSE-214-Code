package Homework02;

public class Order {
	private String order;
	private String specialInstruction;
	private double price;
	

	public Order(String name, String specInc, double price){
		order = name;
		specialInstruction = specInc;
		this.price = price;
	}
	public boolean equals(Order obj){
		return ((this.getOrder().equals(obj.getOrder())) && (this.getPrice() == obj.getPrice())  && (this.getSpecialInstruction().equals(obj.getSpecialInstruction())));
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
