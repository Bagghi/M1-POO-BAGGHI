package client_serveur;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
public class Client {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Socket s=null;
		try {
			
			System.out.println("C >>> Demande de connexion au serveur");
			// Création du socket
			s=new Socket("localhost",50263); 
			
			// Récupération des flux d’entrée/sortie
			InputStream in = s.getInputStream();
		  	ObjectInputStream objIn = new ObjectInputStream(in);
		 	OutputStream out = s.getOutputStream();
		 	ObjectOutputStream objOut = new ObjectOutputStream(out);
		 	// Récupération des flux d’entrée/sortie
	 		//Lecture de l'objet
		  	System.out.println("C >>> Lecture d'un Object");
	 		Object o=(Object)objIn.readObject();
	 		System.out.println(o);
	 		//On vérifie si c'est un message de déconnexion
	 		if(o instanceof String && o.equals("deconnecToi")) {
	 			System.out.println("il n'y plus d'objets déconnexion");
	 			s.close();
	 		}
	 		else {
	 			//Saisie des valeurs
			  	
	 			
	 			
			  	//On renvoie l'objet
			  	System.out.println("C>>Envoi d'un objets");
				objOut.writeObject(o);
				//Déconnexion
			  	s.close();
	 		}
		  	//System.out.println(o);
		  	//System.out.println(s.isClosed());
			  	
		} catch (IOException e) {
			//System.err.println(e);
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}