package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import program.Hlavni;

/**
 * Objekt teto tridy predstavuje platno, kam se vykresluje automat.
 * @author Deni Tarantikova, Radek Vais
 * @version 7. 12. 2014
 */
public class Platno extends JPanel {

	/**	defaultni nastaveni */
	private static final long serialVersionUID = 1L;
	/** sirka platna */
	private int sirka;
	/** vyska platna */
	private int vyska; 
	/** sirka vsech vykreslovanych stavu */
	private final int SIRKA_UZLU = 50;
	
	
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
	 * Konstruktor vytvori prazdne platno o defaultni velikosti 2000x2000 pixelu.
	 */
	public Platno() {
		this.sirka = 2000;
		this.vyska = 2000;
		this.setPreferredSize(new Dimension(this.sirka, this.vyska));
	}
	
		
	/**
	 * Vykresli automat.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// vykresleni mrizky
		g2.setColor(Color.LIGHT_GRAY);
		for(int i = 0; i < this.sirka; i += 100) {
			g2.drawLine(i, 0, i, this.vyska);
		}
		for(int j = 0; j < this.vyska; j += 100) {
			g2.drawLine(0, j, this.sirka, j);
		}
		
		
		//vykresli hrany
		
		char[][] prechodova_funkce = Hlavni.automat.getPrechodovaFce();
		for (int i = 0; i < prechodova_funkce.length; i++) {
			for(int j = 0; j<prechodova_funkce[i].length; j++){
				if(prechodova_funkce[i][j]!='0'){
					kresliHranu(g2, Hlavni.stavy.get(i), Hlavni.stavy.get(j), ""+prechodova_funkce[i][j]);
				}
			}
		}
		
		
		//vykresli stavy
		g2.setColor(Color.BLACK);
		for (Stav stav : Hlavni.stavy) {
			vykresliStav(g2, stav, stav.getBarva());
		}
	
		
		// aktualizace hodnoty vstupu
		Hlavni.okno.vystup.setText(" "+Hlavni.automat.getVystup());
	}
	
	/**
	 * Vykresli stav automatu.
	 * @param g pouzita grafika
	 * @param stav stav
	 * @param barva barva stavu
	 */
	public void vykresliStav(Graphics2D g, Stav stav, Color barva) {
		AffineTransform tmp = g.getTransform();
		
		g.translate(stav.getX(), stav.getY());
		g.setColor(barva);
		g.fillOval(-(SIRKA_UZLU/2), -(SIRKA_UZLU/2), SIRKA_UZLU, SIRKA_UZLU);
		g.setColor(Color.BLACK);
		g.drawOval(-(SIRKA_UZLU/2), -(SIRKA_UZLU/2), SIRKA_UZLU, SIRKA_UZLU);
		g.drawString(stav.getNazev(), 
				0 - g.getFontMetrics().stringWidth(stav.getNazev())/2, 
				0 + g.getFont().getSize()/2);
		
		g.setTransform(tmp);
	}
	
	/**
	 * Prebarvi dany stav.
	 * @param g grafika
	 * @param stav stav
	 * @param barva barva, na kterou se ma stav prebarvit
	 */
	public void prebarviStav(Graphics2D g, Stav stav, Color barva) {
		g.setColor(barva);
		stav.setBarva(barva);
		g.fillOval(stav.getX(), stav.getY(), SIRKA_UZLU, SIRKA_UZLU);
		g.setColor(Color.BLACK);
		g.drawOval(stav.getX(), stav.getY(), SIRKA_UZLU, SIRKA_UZLU);	
	}

	/**
	 * Vykresli hranu mezi stavy.
	 * @param g grafika
	 * @param pocatek odkud vede hrana
	 * @param konec kam vede hrana
	 * @param funkce prechodova funkce
	 */
	public void kresliHranu(Graphics2D g, Stav pocatek, Stav konec, String funkce) {
		g.setColor(Color.BLACK);
		double meritkoSipky = 15;	
		
		double x1 = pocatek.getX();
		double x2 = konec.getX();
		double y1 = pocatek.getY();
		double y2 = konec.getY();
		
		if (!pocatek.equals(konec)) {
			g.draw(new Line2D.Double(x1, y1, x2, y2));
			Double sx, sy, dv, kx, ky;
			  
		    // smerovy vektor dlouhe cary
		    sx = x2 - x1;
		    sy = y2 - y1;
		    // delka smeroveho vektoru
		    dv = Math.sqrt(sx*sx + sy*sy);
		    // normalizace smeroveho vektoru
		    sx /= dv;
		    sy /= dv;
		    // vektor kolmy ke smerovemu
		    kx = -sy;
		    ky = sx;
			// upravit delky vektoru podle meritka sipky
			kx *= meritkoSipky;
			ky *= meritkoSipky;
			sx *= meritkoSipky;
			sy *= meritkoSipky;
			// stred usecky
			double py = (y1+y2)/2;
			double px = (x1+x2)/2;
			  
			g.draw(new Line2D.Double(px - sx + kx, py - sy + ky, px, py));
			g.draw(new Line2D.Double(px - sx - kx, py - sy - ky, px, py));
			
			g.drawString(funkce, 
					(int)px - g.getFontMetrics().stringWidth(funkce)/2 + 10, 
					(int)py + g.getFont().getSize()/2 - 10);
			
		} else {
			int width = SIRKA_UZLU;
			int height = 4*SIRKA_UZLU/3;
			g.drawOval(pocatek.getX(), pocatek.getY() - SIRKA_UZLU, width, height);
			
			 // smerovy teèny oválu
		    int sx = 0;
		    int sy = 1;
		    // vektor kolmy ke smerovemu
		    int kx = -1;
		    int ky = 0;
			
		    // upravit delky vektoru podle meritka sipky
			kx *= meritkoSipky;
			ky *= meritkoSipky;
			sx *= meritkoSipky;
			sy *= meritkoSipky;
			
			// bod sipky
			double px = pocatek.getX()+width;
			double py = pocatek.getY();
			
			g.draw(new Line2D.Double(px - sx + kx, py - sy + ky, px, py));
			g.draw(new Line2D.Double(px - sx - kx, py - sy - ky, px, py));
			
			g.drawString(funkce, 
					pocatek.getX() + SIRKA_UZLU/2 - g.getFontMetrics().stringWidth(funkce)/2, 
					pocatek.getY() - 4*SIRKA_UZLU/3 + g.getFont().getSize()/2);
		}
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
