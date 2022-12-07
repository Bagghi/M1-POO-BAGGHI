package client_serveur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.lang.reflect.Field;
public class Client {
	
	
	public static void Saisie(Object o) {
		
		Scanner sc=new Scanner(System.in);
		//System.out.println(sc);
		ArrayList<String> type_primitif = new ArrayList<String>(Arrays.asList("String","char","int","Integer","Double","double","Boolean","boolean","float","Float","Short","short","Long","long","Byte","byte"));
		Object val=null;
		//Graphique g=new Graphique();
		//g.init();
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
	
	
public static void SaisieGraphique(Object o,Graphique g) {
		
		//Scanner sc=new Scanner(System.in);
		//System.out.println(sc);
		ArrayList<String> type_primitif = new ArrayList<String>(Arrays.asList("String","char","Character","int","Integer","Double","double","Boolean","boolean","float","Float","Short","short","Long","long","Byte","byte"));
		Object val=null;
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setLayout(new FlowLayout());
		
		
		for(Field f :o.getClass().getFields()) {
			
	  		System.out.println(f.getType().getSimpleName()+" : "+f.getName()+"\n");
	  		if(type_primitif.contains(f.getType().getSimpleName())) {
	  			
				
				System.out.println("Entrer le "+f.getName()+"\n"); 
				
		  		g.add2(f.getType().getSimpleName(), f.getName(),panel);
		  		
//		  		try {
//					f.set(o, val);
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		  		
			 }
	  		else{
				 try {
					 JLabel label= new JLabel(f.getType().getSimpleName()+" "+f.getName());
					 label.setForeground(Color.RED);
					 g.contenu.add(label);
					SaisieGraphique(f.get(o),g);
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
			// Cr�ation du socket
			s=new Socket("localhost",50263); 
			
			// R�cup�ration des flux d�entr�e/sortie
			OutputStream out = s.getOutputStream();
		 	ObjectOutputStream objOut = new ObjectOutputStream(out);
			InputStream in = s.getInputStream();
		  	ObjectInputStream objIn = new ObjectInputStream(in);
		 	
		 	// R�cup�ration des flux d�entr�e/sortie
	 		//Lecture de l'objet
		  	System.out.println("C >>> Lecture d'un Object");
	 		Object o=(Object)objIn.readObject();
	 		//System.out.println(o);
	 		//On v�rifie si c'est un message de d�connexion
	 		if(o instanceof String && o.equals("deconnecteToi")) {
	 			System.out.println("C >>> il n'y plus d'objets d�connexion");
	 			s.close();
	 		}
	 		else {
	 			//Saisie des valeurs console 
			  	System.out.println("C >>> Classe de l'objet : "+o.getClass().getSimpleName());
			  	System.out.println("C >>> Attributs :");
//			  	Saisie(o);
	 			
	 			//Saisie des valeurs graphique
	 			Graphique g=new Graphique();
	 			g.init();
	 			g.fenetre.setTitle(o.getClass().getSimpleName());
			  	SaisieGraphique(o,g);
			  	g.contenu.add(g.btnok);
			  	g.fenetre.pack();

			  	
	 			
	 			
			  	//On renvoie l'objet
			  	System.out.println("C>>Envoi d'un objets");
				//objOut.writeObject(o);
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
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}