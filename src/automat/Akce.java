package automat;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/**
 * Trida predstavuje "kontejner" pro vsechny tridy, ktere
 * zajistuji akce.
 * @author Denisa Tarantikova A13B0445P
 * @version 1 - 16. 5. 2014
 */
public class Akce {
	/** kreslici platno */
	private Platno platno;
	
	/**
	 * Konstruktor vytvori objekt, 
	 * ktery umozni ziskat pristup k jednotlivym akcim.
	 * @param platno kreslici platno
	 */
	public Akce(Platno platno) {
		this.platno = platno;
	}

	/**
	 * Zajistuje akci ukonceni programu.
	 * @author Denisa Tarantikova A13B0445P
	 * @version 1 - 1. 5. 2014
	 */
	class KonecAkce extends AbstractAction {
		/** defaultni nastaveni */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Konstruktor vytvori akci tlacitko s ikonkou, 
		 * jmenem a mnemonic a accelerator klavesovou zkratkou.
		 */
		public KonecAkce() {
			//ImageIcon ikonaKonec = new ImageIcon(getClass().getResource("/images/exit.gif"));
			ImageIcon ikonaKonec = new ImageIcon("/images/button_back.png");
			putValue(NAME, "Konec"); // jmeno akce
            putValue(SMALL_ICON, ikonaKonec); // na tlacitku bude jen ikonka
            putValue(SHORT_DESCRIPTION, "Ukonèí aplikaci"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_K)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
		
		/**
		 * Metoda zajisti provedeni akce konec.
		 */
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	
	/**
	 * Zajistuje akci otevreni okna s napovedou.
	 * @author Denisa Tarantikova A13B0445P
	 * @version 1 - 10. 5. 2014
	 */
	class NapovedaAkce extends AbstractAction {
		/**	defaultni nastaveni */
		private static final long serialVersionUID = 1L;

		/**
		 * Konstruktor vytvori akci tlacitko s ikonkou, 
		 * jmenem a mnemonic a accelerator klavesovou zkratkou.
		 */
		public NapovedaAkce() {
			//ImageIcon ikona = new ImageIcon(getClass().getResource("/images/help.gif"));
            putValue(NAME, "Nápovìda"); // jmeno akce
          //  putValue(SMALL_ICON, ikona); // na tlacitku bude jen ikonka
            putValue(SHORT_DESCRIPTION, "Zobrazí nápovìdu k aplikaci"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_N)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
		
		/**
		 * Metoda zajisti provedeni akce.
		 */
		public void actionPerformed(ActionEvent e) {
			new NapovedaOkno();
		}
	}
}
