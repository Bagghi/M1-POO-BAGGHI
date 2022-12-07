package client_serveur;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Vector;
public class Graphique {
	
	JFrame fenetre ;
	Container contenu ; 
	JButton btnok;
	public void init() {
		fenetre =new JFrame();
		fenetre.setTitle("Client_server");
		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		contenu=fenetre.getContentPane();
		contenu.setLayout(new BoxLayout(contenu,BoxLayout.Y_AXIS));
		btnok=new JButton("OK");
		fenetre.setVisible(true);
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
				//Ici je vais mettre Integer.class, Integer.MIN_VALUE,
				//Integer.MAX_VALUE pour pouvoir mettre le signe "-" 
				// car Double.MIN_VALUE et Float.MIN_VAlue sont positifs.
				 format.setMinimumFractionDigits(1);
				 format.setMaximumFractionDigits(5);
				 formatter.setValueClass(Integer.class);
				 formatter.setMinimum(Integer.MIN_VALUE);
				 formatter.setMaximum(Integer.MAX_VALUE);
				 formatter.setAllowsInvalid(false);
				 champ = new JFormattedTextField(formatter);
				break;
			case "float":
			case "Float":
				format.setMinimumFractionDigits(1);
				format.setMaximumFractionDigits(5);
				formatter.setValueClass(Integer.class);
				formatter.setMinimum(Integer.MIN_VALUE);
				formatter.setMaximum(Integer.MAX_VALUE);
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
		        champ = new JComboBox(item);
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


