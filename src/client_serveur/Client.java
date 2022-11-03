package client_serveur;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;
import java.lang.reflect.Field;
public class Client {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Socket s=null;
		Scanner sc=new Scanner(System.in);
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
	 		//System.out.println(o);
	 		//On vérifie si c'est un message de déconnexion
	 		if(o instanceof String && o.equals("deconnecteToi")) {
	 			System.out.println("C >>> il n'y plus d'objets déconnexion");
	 			s.close();
	 		}
	 		else {
	 			//Saisie des valeurs
			  	System.out.println("C >>> Classe de l'objet : "+o.getClass().getSimpleName());
			  	System.out.println("C >>> Attributs :");
			  	
			  	
			  	for(Field f :o.getClass().getFields()) {
			  		System.out.println(f.getType().getSimpleName()+" : "+f.getName()+"\n");
			  		Object val=null;
			  		System.out.println("Entrer le "+f.getName()+"\n");
			  		switch (f.getType().getSimpleName()) {
					case "String":
						val=sc.nextLine();
						break;
						
					case "int":
					case "Integer":
						val=sc.nextInt();
						break;
					case "double":
					case "Double":
						val=sc.nextDouble();
						break;
					case "float":
					case "Float":
						val=sc.nextFloat();
						break;
					case "short":
					case "Short":
						val=sc.nextShort();
						break;
					case "byte":
					case "Byte":
						val=sc.nextInt();
						break;
					case "boolean":
					case "Boolean":
						val=sc.nextBoolean();
						break;
					case "long":
					case "Long":
						val=sc.nextLong();
						break;	
					default:
						System.out.println("Erreur");
					}
			  		
			  		
			  		f.set(o, val);
			  		
			  	}
	 			
	 			
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
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}