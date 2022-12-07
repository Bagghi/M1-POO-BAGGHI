package client_serveur;

import java.io.Serializable;

public class Point implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Attributs
	public String nom;
	public double x;
	public double y;
//	public Character character;
//	public char chare;
//	public int t;
//	public Integer s;
//	public Boolean w;
//	public Double j;
//	public float a;
//	public Float b;
//	public Short e;
//	public short r;
//	public Long yu;
//	public long u;
//	public Byte i;
//	public byte o;
//	public boolean bu;
	
	
	
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
		
		this.nom="";
		x=0;
		y=0;
//		t=0;
//		this.s=0;
//	    this.w=true;
//	    this.j=null;
//	    this.a=0;
//	    this.b=Float.valueOf(0);
//	    this.e=0;
//	    this.r=0;
//	    this.yu= Long.valueOf(0);
//	    this.u= 0;
//	    this.i=Byte.valueOf("0") ;
//	    this.o= 0;
//	    this.bu= true;
	}

	//Methodes
	public String toString() {
		return  nom+"(x:"+x+", y:"+y+")";
	}
	
}
