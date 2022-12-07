package client_serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class MonThread extends Thread {
	
	//Attributs
	public Socket client;
	ArrayList<Object> l_send;
	ArrayList<Object> l_receive;
	
	//constructeur
	public MonThread(Socket c,ArrayList<Object> l_s,ArrayList<Object> l_r) {
		client=c;
		this.l_send=l_s;
		this.l_receive=l_r;
	}
	
	//M�thode run
	public void run() {
		try {
			
			OutputStream out = client.getOutputStream();
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			InputStream in = client.getInputStream();
			ObjectInputStream objIn = new ObjectInputStream(in);
			
			//On verrouille la liste d'objets �  envoy�s
			Object o=null;
			synchronized(l_send){
				//On v�rifie si elle est vide
				//si elle est vide, on envoie un message de d�connexion au client,
				//on arrete le thread et on d�connecte le client.
				//Si elle n'est pas vide on r�cup�re un objet dans la liste, 
				//on enl�ves l'objet de la liste et on l'envoie au client
				if(l_send.isEmpty()){
					this.interrupt();
					
				}
				else {
						
						o=l_send.get(l_send.size()-1);
						l_send.remove(l_send.get(l_send.size()-1));
						
				}
			}
			if(o==null) {
				System.out.println("T >>> Envoie du message de d�connexion");
				String deco=new String("deconnecteToi");
				objOut.writeObject(deco);
				client.close();
			}
			else {
				System.out.println("T>>Envoi d'un objets et modification liste");
				objOut.writeObject(o);
			}
			
			//On v�rifie si le thread est arr�t� pour savoir si on attend une r�ponse du client 
			if(!this.isInterrupted()) {
				System.out.println("T>>R�ceptions objet");
				//On lit l'objet et on l'ajoute a la liste des objets re�u
				o=(Object)objIn.readObject();
				System.out.println("T>>objet recu");
				synchronized(l_receive) {
					
					
					System.out.println("T >>> Ajout de l'objet re�u � la liste "+o);
					l_receive.add(o);
					
				}
			}
			
			
			//d�connexion du client
			client.close();
			System.out.println("T >>> Client d�connect�");
			
			
		}catch (IOException | ClassNotFoundException e) {
			 System.err.println(e.getMessage());
			 System.out.println("iciiiiiii");
			 System.exit(1);
			} 
			
			
		
	}
}
