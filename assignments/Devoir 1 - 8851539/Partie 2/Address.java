package Partie2;//Maxime Côté-Gagné(8851539)
/**
 * classe address
 * @author Maxime
 *
 */
public class Address {
	
	//constructeur
	public Address(String street,int number,String postal){
		this.street=street;
		this.number=number;
		this.postal=postal;
	}
	
	
	//variables d'instances
	private String street;
	private int number;
	private String postal;
	
	
	public String getStreet(){
		return street;
	}
	public void setStreet(String street){
		this.street=street;
	}
	public int getNumber(){
		return number;
	}
	public void setNumber(int number){
		this.number=number;
	}
	public String getPostal(){
		return postal;
	}
	public void setPostal(String postal){
		this.postal=postal;
	}
}
