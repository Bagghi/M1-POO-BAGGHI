package client_serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MonThread extends Thread {
	public Socket client;
	public MonThread(Socket c) {
		client=c;
	}
	public void run() {
		try {
			OutputStream out = client.getOutputStream();
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			InputStream in = client.getInputStream();
			ObjectInputStream objIn = new ObjectInputStream(in);
			
			System.out.println("T >>> Lecture d'un integer");
			Integer i= (Integer)objIn.readObject();
			System.out.println("T >>> Objet lu : "+i);
			System.out.println("T >>> Lecture d'un Point");
		  	Point p2=(Point)objIn.readObject();
		  	System.out.println(p2.toString());
		  	
		  	System.out.println("T >>> Envoi d'un integer");
			Integer I= Integer.valueOf(3);
			objOut.writeObject(I);
			System.out.println("T >>> Envoi d'un Point");
		  	Point p=new Point(4,5,"Point_serveur");
		  	objOut.writeObject(p);
			client.close();
			System.out.println("T >>> Client déconnecté");
			
		}catch (IOException e) {
			 System.err.println(e.getMessage());
			 System.exit(1);
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
