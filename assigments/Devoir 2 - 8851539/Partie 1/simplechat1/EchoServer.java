// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import ocsf.server.*;

import java.net.InetAddress;
/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************

  /*
   * Modifi� pour E49
   * Override la m�thode clientConnected(dans AbstractServer) pour afficher un message si un client se connecte
   * MCG
   */
  @Override
  public synchronized void clientConnected(ConnectionToClient client) {
      String msg = "SERVER: Un client s'est connect�.";//un message envoy� par le serveur
     
      this.sendToAllClients(msg);//ceci permet d'envoyer un message aux autres client pour faire savoir qu'un autre client est connect�
      System.out.println(msg);//ceci print le message sur la console du serveur(EchoServer)
  }

  /*
   * Modifi� pour E49
   * Override la m�thode clientDisconnected(dans AbstractServer) pour afficher un message si un client se deconnecte
   * MCG
   */
  @Override
  public synchronized void clientDisconnected(ConnectionToClient client) {
	  String msg = "SERVER: Un client s'est deconnect�.";//un message envoy� par le serveur
	  
      this.sendToAllClients(msg);//ceci permet d'envoyer un message aux autres client pour faire savoir qu'un autre client est connect�
      System.out.println(msg);//ceci print le message sur la console du serveur(EchoServer)
  }

  /*
   * Modifi� pour E49
   * Override la m�thode clientException(dans AbstractServer) pour afficher un message si un client se deconnecte
   * MCG
   */
  @Override
  public synchronized void clientException(ConnectionToClient client, Throwable exception) {
	  String msg = "SERVER: Un client s'est deconnect�.";//un message envoy� par le serveur
	  
      this.sendToAllClients(msg);//ceci permet d'envoyer un message aux autres client pour faire savoir qu'un autre client est connect�
      System.out.println(msg);//ceci print le message sur la console du serveur(EchoServer)
  }

  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
    System.out.println("Message received: " + msg + " from " + client);
    this.sendToAllClients(msg);
  }
    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of EchoServer class
