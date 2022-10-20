package client_serveur;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {
	private static Integer port= 50263;
	private static Boolean enDev= Boolean.valueOf(true);
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test
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
	
				System.out.println("S >>> Lecture d'un integer");
				Integer i= (Integer)objIn.readObject();
				System.out.println("S >>> Objet lu : "+i);
				System.out.println("S >>> Lecture d'un Point");
			  	Point p2=(Point)objIn.readObject();
			  	System.out.println(p2.toString());
			  	
			  	System.out.println("S >>> Envoi d'un integer");
				Integer I= Integer.valueOf(3);
				objOut.writeObject(I);
				System.out.println("S >>> Envoi d'un Point");
			  	Point p=new Point(4,5,"Point_serveur");
			  	objOut.writeObject(p);
				client.close();
				System.out.println("S >>> Client déconnecté");
				if(enDev.equals(true))
					break;
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








