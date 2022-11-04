package client_serveur;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server  {
	private static Integer port= 50263;
	
	private static ArrayList<Object> liste_send=new ArrayList<Object>();
	private static ArrayList<Object> liste_receive=new ArrayList<Object>();
	
	

	public static void main(String[] args) 
	{
		//TODO Auto-generated method stub
		for(int i=0;i<5;i++) {
			Point p=new Point();
			Segment s=new Segment();
			liste_send.add(s);
			liste_send.add(p);
		}
		ServerSocket ecoute;
		
		try 
		{
			// On écoute sur le port <PORT>
			ecoute=new ServerSocket(port);
			System.out.println("S >>> Lancement du serveur");
			while (true) 
			{
				
				// On accepte une demande de connexion d'un client
				System.out.println("S >>> En attente de connexion");
				Socket client=ecoute.accept();
				System.out.println("S >>> Client connecté");
				// Création du thread
				System.out.println("S >>> Création du thread");
				MonThread t=new MonThread(client,liste_send,liste_receive);
				System.out.println("S >>> Lancement du thread");
				t.start();
			}
		}
		catch (IOException e) {
		 System.err.println(e.getMessage());
		 System.out.println("ici");
		 System.exit(1);
		}
			
		
		
			

	}

}








