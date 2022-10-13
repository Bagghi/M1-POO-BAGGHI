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
			s=new Socket("localhost",50263); // Création du socket
		 // Récupération des flux d’entrée/sortie
			InputStream in = s.getInputStream();
		  	ObjectInputStream objIn = new ObjectInputStream(in);
		 	OutputStream out = s.getOutputStream();
		 	ObjectOutputStream objOut = new ObjectOutputStream(out);
		  	
		 	System.out.println("C >>> Lecture d'un integer");
		  	Integer I = (Integer) objIn.readObject();
		  	System.out.println(I);
		  	System.out.println("C >>> Envoi d'un integer");
		  	Integer i = Integer.valueOf(50);
		  	objOut.writeObject(i);
		  	
		 //UnObjet O= new UnObjet() ;
		 //objOut.writeObject(O);
		  	s.close();
		} catch (IOException e) {System.err.println(e);} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
