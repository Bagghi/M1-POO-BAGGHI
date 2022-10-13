package client_serveur;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {
	private static Integer port= 50263;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ecoute;
		
		try {
			// On écoute sur le port <PORT>
			ecoute=new ServerSocket(port);
			System.out.println("S >>> Lancement du serveur");
			while (true) {
				// On accepte une demande de connexion d'un client
				System.out.println("S >>> En attente de connexion");
				Socket client=ecoute.accept();
				System.out.println("S >>> Client connecté");
				OutputStream out = client.getOutputStream();
				ObjectOutputStream objOut = new ObjectOutputStream(out);
				InputStream in = client.getInputStream();
				ObjectInputStream objIn = new ObjectInputStream(in);
	
				Integer I= Integer.valueOf(3);
				System.out.println("S >>> Envoi d'un integer");
				objOut.writeObject(I);
				System.out.println("S >>> Lecture d'un integer");
				Integer i= (Integer)objIn.readObject();
				System.out.println("S >>> Objet lu : "+i);
				client.close();
				System.out.println("S >>> Client déconnecté");
				}
			}
			catch (IOException e) {
			 System.err.println(e.getMessage());
			 System.exit(1);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
			

	}

}
