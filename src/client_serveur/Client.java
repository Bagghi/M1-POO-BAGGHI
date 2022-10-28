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
			// Cr�ation du socket
			s=new Socket("localhost",50263); 
			
			// R�cup�ration des flux d�entr�e/sortie
			InputStream in = s.getInputStream();
		  	ObjectInputStream objIn = new ObjectInputStream(in);
		 	OutputStream out = s.getOutputStream();
		 	ObjectOutputStream objOut = new ObjectOutputStream(out);
		 	// R�cup�ration des flux d�entr�e/sortie
	 		//Lecture de l'objet
		  	System.out.println("C >>> Lecture d'un Object");
	 		Object o=(Object)objIn.readObject();
	 		System.out.println(o);
	 		//On v�rifie si c'est un message de d�connexion
	 		if(o instanceof String && o.equals("deconnecToi")) {
	 			System.out.println("il n'y plus d'objets d�connexion");
	 			s.close();
	 		}
	 		else {
	 			//Saisie des valeurs
			  	
	 			
	 			
			  	//On renvoie l'objet
			  	System.out.println("C>>Envoi d'un objets");
				objOut.writeObject(o);
				//D�connexion
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