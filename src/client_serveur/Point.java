package client_serveur;

import java.io.Serializable;

public class Point implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Attributs
	private String nom;
	private double x;
	private double y;
	//getters et setters
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	//Constructeurs

	public Point(double x,double y ,String nom ){
		this.x=x;
		this.y=y;
		this.nom=nom;
	}
	public Point(double x ,double y) {
		this(x,y,"");
	}
	public Point() {
		this.x=x;
		this.y=y;
		this.nom="";
	}

	//Methodes
	public String toString() {
		return  nom+"(x:"+x+", y:"+y+")";
	}
	
}
