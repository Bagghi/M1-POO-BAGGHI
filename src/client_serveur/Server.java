package client_serveur;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server  {
	private static Integer port= 50263;
	
	//private ArrayList<Point> liste=new ArrayList<Point>();
	

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
				// Création du thread
				System.out.println("S >>> Création du thread");
				MonThread t=new MonThread(client);
				System.out.println("S >>> Lancement du thread");
				t.start();
	
				
				
				}
			}
			catch (IOException e) {
			 System.err.println(e.getMessage());
			 System.exit(1);
			}
			
		
		
			

	}

}








