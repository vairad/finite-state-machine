package automat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Platno extends JPanel {

	/**	defaultni nastaveni */
	private static final long serialVersionUID = 1L;
	/** sirka platna */
	private int sirka;
	/** vyska platna */
	private int vyska; 
	/** sirka vsech vykreslovanych stavu */
	private final int SIRKA_UZLU = 50;
	public final Color CHYBOVY = Color.RED;
	public final Color AKTUALNI = Color.YELLOW;
	public final Color BEZNY = new Color(179,196,227);
	
	
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
		Graphics2D g2 = (Graphics2D) g;
		
		Stav stav1 = new Stav(100,100,"S1", CHYBOVY);
		vykresliStav(g2, stav1, stav1.getBarva());
		
		Stav stav2 = new Stav(200,300,"S2");
		vykresliStav(g2, stav2, stav2.getBarva());
		prebarviStav(g2, stav2, AKTUALNI);
		
		Stav stav3 = new Stav(400,500,"S3");
		vykresliStav(g2, stav3, stav3.getBarva());
		kresliHranu(g2, stav2, stav3, "gama");
		
		kresliHranu(g2, stav1, stav1, "alfa");
		kresliHranu(g2, stav3, stav1, "beta");
	}
	
	
	public void vykresliStav(Graphics2D g, Stav stav, Color barva) {
		g.setColor(barva);
		g.fillOval(stav.getX(), stav.getY(), SIRKA_UZLU, SIRKA_UZLU);
		g.setColor(Color.BLACK);
		g.drawOval(stav.getX(), stav.getY(), SIRKA_UZLU, SIRKA_UZLU);
		g.drawString(stav.getNazev(), 
				stav.getX() + SIRKA_UZLU/2 - g.getFontMetrics().stringWidth(stav.getNazev())/2, 
				stav.getY() + SIRKA_UZLU/2 + g.getFont().getSize()/2);
	}
	
	public void prebarviStav(Graphics2D g, Stav stav, Color barva) {
		g.setColor(barva);
		stav.setBarva(barva);
		g.fillOval(stav.getX(), stav.getY(), SIRKA_UZLU, SIRKA_UZLU);
		g.setColor(Color.BLACK);
		g.drawOval(stav.getX(), stav.getY(), SIRKA_UZLU, SIRKA_UZLU);	
	}

	public void kresliHranu(Graphics2D g, Stav pocatek, Stav konec, String funkce) {
		g.setColor(Color.BLACK);
		double meritkoSipky = 15;	
		
		double x1 = pocatek.getX() + SIRKA_UZLU/2;
		double x2 = konec.getX() + SIRKA_UZLU/2;
		double y1 = pocatek.getY() + SIRKA_UZLU/2;
		double y2 = konec.getY() + SIRKA_UZLU/2;
		
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
			g.drawOval(pocatek.getX(), pocatek.getY() - SIRKA_UZLU, SIRKA_UZLU, 4*SIRKA_UZLU/3);	
			g.drawString(funkce, 
					pocatek.getX() + SIRKA_UZLU/2 - g.getFontMetrics().stringWidth(funkce)/2, 
					pocatek.getY() - 4*SIRKA_UZLU/3 + g.getFont().getSize()/2);
		}
			
		vykresliStav(g, pocatek, pocatek.getBarva());
		vykresliStav(g, konec, konec.getBarva());
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
