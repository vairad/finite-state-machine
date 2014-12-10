package gui;

import java.awt.Color;

/**
 * Objekty teto tridy predstavuji jednotlive stavy automatu.
 * @author Deni Tarantikova, Radek Vais
 * @version 7. 12. 2014
 */
public class Stav {
	/** barva neaktivniho stavu */
	public static final Color BEZNY = new Color(179,196,227);
	/** barva aktivniho stavu */
	public static final Color AKTIVNI = Color.YELLOW;
	/** souradnice x */
	private int x;
	/** souradnice y */
	private int y;
	/** id stavu */
	public int id;
	/** nazev stavu */
	private String nazev;
	/** barva stavu */
	private Color barva;
	
	/**
	 * Vytvori stav se zadanym id na zadanych souradnicich.
	 * Bude mit implicitni barvu(bezny stav) a nazev (prazdny retezec).
	 * @param x souradnice x
	 * @param y souradnice y
	 * @param id id stavu
	 */
	public Stav(int x, int y, int id) {
		this(x, y, id, "");
	}
	
	/**
	 * Vytvori stav se zadanym id, nazvem a s implicitni barvou (bezny stav). 
	 * @param x souradnice x
	 * @param y souradnice y
	 * @param id id stavu
	 * @param nazev nazev stavu
	 */
	public Stav(int x, int y, int id, String nazev) {
		this(x, y, id, nazev, BEZNY);
	}
	
	/**
	 * Vytvori stav na zadanych souradnicich a se zadanym nazvem a barvou.
	 * @param x souradnice x
	 * @param y souradnice y
	 * @param id id stavu
	 * @param nazev nazev stavu
	 * @param barva barva stavu
	 */
	public Stav(int x, int y, int id, String nazev, Color barva) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.nazev = nazev;
		this.setBarva(barva);
	}

	/**
	 * Vrati souradnici x stavu.
	 * @return souradnice x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Nastavi souradnici x stavu.
	 * @param x souradnice x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Vrati souradnici y stavu.
	 * @return souradnice y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Nastavi souradnici y stavu.
	 * @param y souradnice y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Vrati nazev stavu.
	 * @return nazev stavu
	 */
	public String getNazev() {
		return nazev;
	}
	
	/**
	 * Nastavi nazev stavu.
	 * @param nazev nazev stavu
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	/**
	 * Vrati barvu stavu.
	 * @return barva stavu
	 */
	public Color getBarva() {
		return barva;
	}

	/**
	 * Nastavi barvu stavu.
	 * @param barva barva stavu
	 */
	public void setBarva(Color barva) {
		this.barva = barva;
	}
	
}
