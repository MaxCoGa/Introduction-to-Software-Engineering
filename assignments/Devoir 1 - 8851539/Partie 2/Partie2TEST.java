package Partie2;//Maxime C�t�-Gagn�(8851539)
/**
 * Classe permettant de cr�er des employ� avec leurs addresses.
 * @author Maxime
 *
 */
public class Partie2TEST {
	public static void main(String[] args) {
		
		//cr�� les deux addresses de l'employ�e dans une liste d'address / 6 dans ce cas
		Address[] list = new Address[5];   
		list [0] = new Address ("King Edward", 800, "K1N6N5");
		list [1] = new Address ( "Queen", 48 ,"K1P1N2");
	
		//emplyoy� o1 cr��
		Employee o1 =  new Employee ("Falcao",40,15.50,list );
		
		//le message est imprim� si aucun erreur n'est trouv�.
		System.out.println("Enregistrer"); 
	}
}
