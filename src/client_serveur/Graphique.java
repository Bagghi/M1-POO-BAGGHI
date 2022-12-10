package client_serveur;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.Vector;
public class Graphique extends WindowAdapter {
	
	JFrame fenetre ;
	Container contenu ; 
	JButton btnok;
	public void init() {
		fenetre =new JFrame();
		fenetre.setTitle("Client_server");
		fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		contenu=fenetre.getContentPane();
		contenu.setLayout(new BoxLayout(contenu,BoxLayout.Y_AXIS));
		btnok=new JButton("OK");
		
		fenetre.setVisible(true);
		
		
	}
	public void unSeulChar(JTextField champ) {
		champ.addKeyListener(new KeyAdapter() {
			  public void keyTyped(KeyEvent e) {
			       if( ( (JTextField)champ ).getText().length()>0    ) {
			        e.consume(); 
			    }
			   }
			});
	}
	public void actionOnclose(Object o,Socket s,ObjectOutputStream objOut) {
		fenetre.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	try {
            		System.out.println("Renvoie de l'objet");
        			objOut.writeObject(o);
        			System.out.println("Déconnexion");
        			s.close();
        		} catch (IOException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
            	fenetre.setVisible(false);
            	fenetre.dispose();
            }
		});
	}
	
	public void add2(String type,String nomChamp,JPanel panel) {
		
		
		JLabel label= new JLabel(type+" "+nomChamp+ " :");
		
		JComponent champ=null;
		NumberFormat format=NumberFormat.getNumberInstance();
		NumberFormatter formatter= new NumberFormatter(format);
		
		switch (type) 
  		{
			case "String":
				champ=new JTextArea(new String(""));
				((JTextArea)champ).setColumns(10);
				break;
			case "char":
			case "Character":
				champ=(JTextField)new JTextField(new String(""));
				
				unSeulChar((JTextField)champ);
				((JTextField)champ).setColumns(10);
				
				break;
				
			case "int":
			case "Integer":
				
			
				formatter.setValueClass(Integer.class);
				formatter.setMinimum(Integer.MIN_VALUE);
				formatter.setMaximum(Integer.MAX_VALUE);
				formatter.setAllowsInvalid(false);
				champ = new JFormattedTextField(formatter);
				 
			
				
				break;
			case "double":
			case "Double":
				 format.setMinimumFractionDigits(1);
				 format.setMaximumFractionDigits(5);
				 formatter.setValueClass(Double.class);
				 formatter.setMinimum(Double.MIN_VALUE);
				 formatter.setMaximum(Double.MAX_VALUE);
				 formatter.setAllowsInvalid(false);
				 champ = new JFormattedTextField(formatter);
				
				break;
			case "float":
			case "Float":
				format.setMinimumFractionDigits(1);
				format.setMaximumFractionDigits(5);
				formatter.setValueClass(Float.class);
				formatter.setMinimum(Float.MIN_VALUE);
				formatter.setMaximum(Float.MAX_VALUE);
				formatter.setAllowsInvalid(false);
				champ = new JFormattedTextField(formatter);
				
				break;
			case "short":
			case "Short":
				formatter.setValueClass(Short.class);
				formatter.setMinimum(Short.MIN_VALUE);
				formatter.setMaximum(Short.MAX_VALUE);
				formatter.setAllowsInvalid(false);
				champ = new JFormattedTextField(formatter);
				break;
			case "byte":
			case "Byte":
				formatter.setValueClass(Byte.class);
				formatter.setMinimum(Byte.MIN_VALUE);
				formatter.setMaximum(Byte.MAX_VALUE);
				formatter.setAllowsInvalid(false);
				champ = new JFormattedTextField(formatter);
				break;
			case "boolean":
			case "Boolean":
				Vector<Boolean> item= new Vector<Boolean>();
				item.add(Boolean.TRUE);
		        item.add(Boolean.FALSE);
		        champ = new JComboBox<Boolean>(item);
				break;
			case "long":
			case "Long":
				formatter.setValueClass(Long.class);
				formatter.setMinimum(Long.MIN_VALUE);
				formatter.setMaximum(Long.MAX_VALUE);
				formatter.setAllowsInvalid(false);
				champ = new JFormattedTextField(formatter);
				break;	
			default:
				System.out.println("Erreur");
		}
		
		label.setLabelFor(champ);
		panel.add(label);
		champ.setPreferredSize(new Dimension(150,20));
		
		
		panel.add(champ);
		contenu.add(panel);
		//fenetre.pack();
	}
}


