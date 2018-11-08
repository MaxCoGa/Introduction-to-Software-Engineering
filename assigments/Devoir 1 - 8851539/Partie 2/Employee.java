package Partie2;//Maxime Côté-Gagné(8851539)
/**
 * classe employee
 * @author Maxime
 *
 */
public class Employee {
	
	
	public Employee(String name, double hours, double rate, Address[] address){
		this.name=name;
		this.hours=hours;
		this.rate=rate;
		this.address=address;
		
	}
	
	private String name;
	private double hours;
	private double rate;
	private Address[] address;
	

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public double getHours(){
		return hours;
	}
	public void setHours(double hours){
		this.hours=hours;
	}
	public double getRate(){
		return rate;
	}
	public void setRate(double rate){
		this.rate=rate;
	}
	public Address[] getAddress(){
		return address;
	}
	public void setAddress(Address[] address){
		this.address=address;
	}
}
