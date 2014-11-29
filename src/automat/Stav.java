package automat;

import java.awt.Color;


public class Stav {
	private int x;
	private int y;
	private String nazev;
	private Color barva;
	public static final Color BEZNY = new Color(179,196,227);
	
	public Stav(int x, int y) {
		this(x, y, "");
	}
	
	public Stav(int x, int y, String nazev) {
		this(x, y, nazev, BEZNY);
		
	}
	
	public Stav(int x, int y, String nazev, Color barva) {
		this.x = x;
		this.y = y;
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
