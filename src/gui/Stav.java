package gui;

import java.awt.Color;


public class Stav {
	public static final Color BEZNY = new Color(179,196,227);
	public static final Color AKTIVNI = new Color(0,196,227);
	
	private int x;
	private int y;
	public int id;
	private String nazev;
	private Color barva;
	
	
	public Stav(int x, int y, int id) {
		this(x, y, id, "");
	}
	
	public Stav(int x, int y, int id, String nazev) {
		this(x, y, id, nazev, BEZNY);
	}
	
	public Stav(int x, int y, int id, String nazev, Color barva) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.nazev = nazev;
		this.setBarva(barva);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	
	public Color getBarva() {
		return barva;
	}

	public void setBarva(Color barva) {
		this.barva = barva;
	}
	
}
