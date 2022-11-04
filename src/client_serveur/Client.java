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
	
	
	public static void Saisie(Object o) {
		
		Scanner sc=new Scanner(System.in);
		//System.out.println(sc);
		ArrayList<String> type_primitif = new ArrayList<String>(Arrays.asList("String","char","int","Integer","Double","double","Boolean","boolean","float","Float","Short","short","Long","long","Byte","byte"));
		Object val=null;
		for(Field f :o.getClass().getFields()) {
	  		System.out.println(f.getType().getSimpleName()+" : "+f.getName()+"\n");
	  		if(type_primitif.contains(f.getType().getSimpleName())) {
	  			
				
				System.out.println("Entrer le "+f.getName()+"\n"); 
				boolean err_saisie;
		  		switch (f.getType().getSimpleName()) 
		  		{
					case "String":
					case "char":
						do {
							err_saisie=false;
							try {
								
								val=sc.nextLine();
								
							}catch(InputMismatchException e) {
								err_saisie=true;
								sc.nextLine();
								System.out.println("Saisie incorrecte.");
							}
						}while(err_saisie);
						
						
						break;
						
					case "int":
					case "Integer":
						do {
							err_saisie=false;
							try {
								
								val=sc.nextInt();
							}catch(InputMismatchException e) {
								err_saisie=true;
								sc.nextLine();
								System.out.println("Saisie incorrecte.");
							}
						}while(err_saisie);
						
						break;
					case "double":
					case "Double":
						do {
							//System.out.println("coucou");
							err_saisie=false;
							try {
								
								val=sc.nextDouble();
							}catch(InputMismatchException e) {
								err_saisie=true;
								sc.nextLine();
								System.out.println("Saisie incorrecte.");
							}
						}while(err_saisie);
						break;
					case "float":
					case "Float":
						do {
							err_saisie=false;
							try {
								
								val=sc.nextFloat();
							}catch(InputMismatchException e) {
								err_saisie=true;
								sc.nextLine();
								System.out.println("Saisie incorrecte.");
							}
						}while(err_saisie);
						break;
					case "short":
					case "Short":
						do {
							err_saisie=false;
							try {
								
								val=sc.nextShort();
							}catch(InputMismatchException e) {
								err_saisie=true;
								sc.nextLine();
								System.out.println("Saisie incorrecte.");
							}
						}while(err_saisie);
						break;
					case "byte":
					case "Byte":
						do {
							err_saisie=false;
							try {
							
								val=sc.nextByte();
							}catch(InputMismatchException e) {
								err_saisie=true;
								sc.nextLine();
								System.out.println("Saisie incorrecte.");
							}
						}while(err_saisie);
						break;
					case "boolean":
					case "Boolean":
						do {
							err_saisie=false;
							try {
								
								val=sc.nextBoolean();
							}catch(InputMismatchException e) {
								err_saisie=true;
								sc.nextLine();
								System.out.println("Saisie incorrecte.");
							}
						}while(err_saisie);
						break;
					case "long":
					case "Long":
						do {
							err_saisie=false;
							try {
								
								val=sc.nextLong();
							}catch(InputMismatchException e) {
								err_saisie=true;
								sc.nextLine();
								System.out.println("Saisie incorrecte.");
							}
						}while(err_saisie);
						break;	
					default:
						System.out.println("Erreur");
				}
		  		try {
					f.set(o, val);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  		
			 }
	  		else{
				 try {
					Saisie(f.get(o));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }	
	  	}
		 
		//sc.close();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Socket s=null;
		
		try {
			
			System.out.println("C >>> Demande de connexion au serveur");
			// Création du socket
			s=new Socket("localhost",50263); 
			
			// Récupération des flux d’entrée/sortie
			OutputStream out = s.getOutputStream();
		 	ObjectOutputStream objOut = new ObjectOutputStream(out);
			InputStream in = s.getInputStream();
		  	ObjectInputStream objIn = new ObjectInputStream(in);
		 	
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
			  	
			  	Saisie(o);
			  	
	 			
	 			
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
		} 
	}
}