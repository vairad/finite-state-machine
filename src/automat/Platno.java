package automat;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Platno extends JPanel {

	/**	defaultni nastaveni */
	private static final long serialVersionUID = 1L;
	/** sirka platna */
	private int sirka;
	/** vyska platna */
	private int vyska; 
	/** platno (obrazek, do ktereho se kresli) */
	private BufferedImage obrazek;
	
	/**
	 * Konstruktor vytvori prazdne platno se zadanymi rozmery
	 * @param sirka sirka platna
	 * @param vyska vyska platna
	 */
	public Platno(int sirka, int vyska) {
		this.sirka = sirka;
		this.vyska = vyska;
		this.setPreferredSize(new Dimension(this.sirka, this.vyska));
	}
	
	/**
	 * Konstruktor vytvori prazdne platno o defaultni velikosti 800x600 pixelu.
	 */
	public Platno() {
		this.sirka = 800;
		this.vyska = 600;
		this.setPreferredSize(new Dimension(this.sirka, this.vyska));
	}
	
		
	/**
	 * Vykresli obrazek.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(obrazek, 0, 0, null);
	}

	/**
	 * Vrati obrazek, ktery je vykresleny na platne.
	 * @return obrazek na platne
	 */
	public BufferedImage getObrazek() {
		return obrazek;
	}
	
	/**
	 * Nastavi vykreslovany obrazek.
	 * @param obrazek novy obrazek
	 */
	public void setObrazek(BufferedImage obrazek) {
		this.obrazek = obrazek;
	}

	/**
	 * Vrati sirku platna.
	 * @return sirka platna
	 */
	public int getSirka() {
		return sirka;
	}

	/**
	 * Nastavi sirku platna.
	 * @param sirka sirka platna
	 */
	public void setSirka(int sirka) {
		this.sirka = sirka;
	}

	/**
	 * Vrati vysku platna.
	 * @return vyska platna
	 */
	public int getVyska() {
		return vyska;
	}

	/**
	 * Nastavi vysku platna.
	 * @param vyska vyska platna
	 */
	public void setVyska(int vyska) {
		this.vyska = vyska;
	}

}
