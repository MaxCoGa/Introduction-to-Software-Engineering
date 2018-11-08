package Partie2;//Maxime Côté-Gagné(8851539)
/**
 * Classe permettant de créer des employé avec leurs addresses.
 * @author Maxime
 *
 */
public class Partie2TEST {
	public static void main(String[] args) {
		
		//créé les deux addresses de l'employée dans une liste d'address / 6 dans ce cas
		Address[] list = new Address[5];   
		list [0] = new Address ("King Edward", 800, "K1N6N5");
		list [1] = new Address ( "Queen", 48 ,"K1P1N2");
	
		//emplyoyé o1 créé
		Employee o1 =  new Employee ("Falcao",40,15.50,list );
		
		//le message est imprimé si aucun erreur n'est trouvé.
		System.out.println("Enregistrer"); 
	}
}
