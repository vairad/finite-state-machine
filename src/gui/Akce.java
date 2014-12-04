package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import program.Hlavni;


public class Akce {

	/** konec programu */
	public static AbstractAction konec = new Akce().new KonecAkce();
	/** napoveda */
	public static AbstractAction napoveda = new Akce().new NapovedaAkce();
	
	
	
	/** reset automatu */
	public static AbstractAction reset = new Akce().new ResetAkce();
	/** krok podle znaku */
	public static AbstractAction vstup = new Akce().new KrokAkce();
	
	
//===================================================================================================================	
	/**
	 * Zajistuje ukonceni programu.
	 */
	class KonecAkce extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public KonecAkce() {
			//ImageIcon ikonaKonec = new ImageIcon(getClass().getResource("/images/exit.gif"));
			ImageIcon ikonaKonec = new ImageIcon("/images/button_back.png");
			putValue(NAME, "Konec"); // jmeno akce
            putValue(SMALL_ICON, ikonaKonec); // na tlacitku bude jen ikonka
            putValue(SHORT_DESCRIPTION, "Ukonèí aplikaci"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_K)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
//===================================================================================================================
	
	/**
	 * Zajistuje akci otevreni okna s napovedou.
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
	
//===================================================================================================================

	/**
	 * Zajistuje reset automatu do pocatecniho stavu
	 */
	class ResetAkce extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public ResetAkce() {
			//ImageIcon ikona = new ImageIcon(getClass().getResource("/images/help.gif"));
            putValue(NAME, "Reset"); // jmeno akce
          //  putValue(SMALL_ICON, ikona); // na tlacitku bude jen ikonka
            putValue(SHORT_DESCRIPTION, "Nastaví automat do výchozího (poèáteèního) stavu."); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_R)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
		public void actionPerformed(ActionEvent e) {
			Hlavni.automat.reset();
			for (Stav stav : Hlavni.stavy) {
				stav.setBarva(Stav.BEZNY);
			}
			Hlavni.stavy.get(Hlavni.automat.getAktualniStav()).setBarva(Stav.AKTIVNI);;
		}
	}
	
//===================================================================================================================

	/**
	 * Zajistuje zpracovani znaku automatem
	 */
	class KrokAkce extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public KrokAkce() {
			//ImageIcon ikona = new ImageIcon(getClass().getResource("/images/help.gif"));
            putValue(NAME, "Krok"); // jmeno akce
          //  putValue(SMALL_ICON, ikona); // na tlacitku bude jen ikonka
            putValue(SHORT_DESCRIPTION, "Provede krok automatu"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_D)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
		public void actionPerformed(ActionEvent e) {
			Hlavni.stavy.get(Hlavni.automat.getAktualniStav()).setBarva(Stav.BEZNY);
			Hlavni.automat.zpracujVstup(((String)new JOptionPane("Zadej znak").getValue()).trim().charAt(0));
			Hlavni.stavy.get(Hlavni.automat.getAktualniStav()).setBarva(Stav.AKTIVNI);
		}
	}
	
//===================================================================================================================

}
