package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import program.Hlavni;
import program.Loader;

/**
 * Trida slouzi k uchovani vsech akci.
 * @author Deni Tarantikova, Radek Vais
 * @version 7. 12. 2014
 */
public class Akce {

	/** konec programu */
	public static AbstractAction konec = new Akce().new KonecAkce();
	/** napoveda */
	public static AbstractAction napoveda = new Akce().new NapovedaAkce();
	/** nacteni automatu ze souboru */
	public static AbstractAction automatSoubor = new Akce().new NactiAutomatSouborAkce();
	
	/** reset automatu */
	public static AbstractAction reset = new Akce().new ResetAkce();
	/** krok podle znaku */
	public static AbstractAction vstup = new Akce().new ZnakAkce();
	/** vloz retezec ke zpracovani */
	public static AbstractAction string = new Akce().new NactiRetezecAkce();
	/** vloz retezec ze souboru ke zpracovani */
	public static AbstractAction stringSoubor = new Akce().new NactiRetezecSouborAkce();
	/** zpracuje znak ze vstupu */
	public static AbstractAction krokVpred = new Akce().new KrokVpredAkce();
	/** vrati znak do vstupu */
	public static AbstractAction krokVzad = new Akce().new KrokVzadAkce();
	
//===================================================================================================================

	/**
	 * Posune automat o krok dopredu.
	 * @param c zpracovavany znak
	 * @return boolean, zda se operace povedla
	 */
	public static boolean vpred(char c) {
		if(!Hlavni.automat.jePrvkemVstupu(c)){
			JOptionPane.showMessageDialog(Hlavni.okno, "Zadaný znak není prvkem vstupní abecedy.\n"
					+ "Automat ho bude ignorovat.",	"Chyba vstupu", JOptionPane.WARNING_MESSAGE);
			return false;
		}else{
			return Hlavni.automat.zpracujVstup(c);
		}
		
	}
	
	/**
	 * Vrati automat o krok zpet.
	 * @param c zpracovavany znak
	 * @return boolean, zda se operace povedla
	 */
	public static boolean vzad(char c) {
		if(!Hlavni.automat.jePrvkemVstupu(c)){
			JOptionPane.showMessageDialog(Hlavni.okno, "Zadaný znak není prvkem vstupní abecedy.\n"
					+ "Automat ho bude ignorovat.",	"Chyba vstupu", JOptionPane.WARNING_MESSAGE);
			return false;
		}else{
			return Hlavni.automat.zpracujVstupReverse(c);
		}
	}
	
//===================================================================================================================	
	/**
	 * Zajistuje ukonceni programu.
	 */
	class KonecAkce extends AbstractAction {
		/** defaultni nastaveni */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Konstruktor nastavi popis tlacitka a klavesove zkratky.
		 */
		public KonecAkce() {
			putValue(NAME, "Konec"); // jmeno akce
            putValue(SHORT_DESCRIPTION, "Ukonèí aplikaci"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_K)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
		
		/**
		 * Ukonci aplikaci.
		 */
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
            putValue(NAME, "Nápovìda"); // jmeno akce
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
	 * Zajistuje nacteni automatu ze souboru
	 */
	class NactiAutomatSouborAkce extends AbstractAction {
		/** defaultni nastaveni */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Konstruktor vytvori popis tlacitka a klavesove zkratky.
		 */
		public NactiAutomatSouborAkce() {
            putValue(NAME, "Naèíst automat ze souboru..."); // jmeno akce
            putValue(SHORT_DESCRIPTION, "Naète automat ze souboru"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
		/**
		 * Nacte automat ze souboru.
		 */
		public void actionPerformed(ActionEvent e) {	
			JFileChooser oteviraciDialog = new JFileChooser("."); // "." znamena aktualni adresar
			
			File vstupni = null;
			String filepath = "";
			
			if (oteviraciDialog.showOpenDialog(Hlavni.okno) == JFileChooser.APPROVE_OPTION) { // nacte data ze souboru				
				vstupni = new File(oteviraciDialog.getSelectedFile().getAbsolutePath());
				filepath = vstupni.getAbsolutePath();
				
				Hlavni.stavy = Loader.loadStavy(filepath);
				Hlavni.automat = Loader.loadAutomat(filepath);
				Hlavni.stavy.get(Hlavni.automat.getAktualniStav()).setBarva(Stav.AKTIVNI);
				
				if(Hlavni.automat != null && Hlavni.stavy!=null){
					Hlavni.okno.repaint();
				}else{
					System.out.println("Data nebyla korektnì naètena.");
				}
				Hlavni.automat.close();
			}
			
		}
	}
	
//===================================================================================================================
	
	/**
	 * Zajistuje reset automatu do pocatecniho stavu
	 */
	class ResetAkce extends AbstractAction {
		/** defaultni nastaveni */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Konstruktor vytvori popis tlacitka a klavesove zkratky.
		 */
		public ResetAkce() {
            putValue(NAME, "Reset"); // jmeno akce
            putValue(SHORT_DESCRIPTION, "Nastaví automat do výchozího (poèáteèního) stavu."); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_R)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
		/**
		 * Resetuje automat.
		 */
		public void actionPerformed(ActionEvent e) {
			Hlavni.getAktualniStav().setBarva(Stav.BEZNY);
			Hlavni.automat.reset();
			Hlavni.getAktualniStav().setBarva(Stav.AKTIVNI);
			
			Hlavni.okno.vstup.setText("");
			Hlavni.okno.zpracovany.setText("");

			Hlavni.okno.repaint();
			
		}
	}
	
//===================================================================================================================

	/**
	 * Zajistuje zpracovani znaku automatem
	 */
	class ZnakAkce extends AbstractAction {
		/** defaultni nastaveni */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Konstruktor vytvori popis tlacitka a klavesove zkratky.
		 */
		public ZnakAkce() {
            putValue(NAME, "Vstup znaku"); // jmeno akce
            putValue(SHORT_DESCRIPTION, "Provede krok automatu"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_N)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
		/**
		 * Zpracuje znak.
		 */
		public void actionPerformed(ActionEvent e) {
			Hlavni.getAktualniStav().setBarva(Stav.BEZNY);
			
			String vstup = JOptionPane.showInputDialog(Hlavni.okno, "Zadej jeden znak pro zpracování automatem", "Zadej znak", JOptionPane.DEFAULT_OPTION);
			if(vstup!=null){
					vstup = vstup.trim();
				if(vstup.length()>0){
					char ch = vstup.charAt(0);
					if(vpred(ch)){
						Hlavni.okno.zpracovany.setText(Hlavni.okno.zpracovany.getText()+ch);
					}else{
						JOptionPane.showMessageDialog(Hlavni.okno, "Pro znak "+ch+" není definovaná pøechodová funkce\n"
								+ "Automat ho bude ignorovat.",	"Chyba vstupu", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			Hlavni.getAktualniStav().setBarva(Stav.AKTIVNI);
			Hlavni.okno.repaint();
		}
	}
	
//===================================================================================================================

	/**
	 * Zajistuje nacteni znaku do vstupniho pole
	 */
	class NactiRetezecAkce extends AbstractAction {
		/** defaultni nastaveni */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Konstruktor vytvori popis tlacitka a klavesove zkratky.
		 */
		public NactiRetezecAkce() {
            putValue(NAME, "Vstup øetìzce"); // jmeno akce
            putValue(SHORT_DESCRIPTION, "Naète øetìzec znakù ke zpracování automatem"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_T)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
		/**
		 * Nacte retezec.
		 */
		public void actionPerformed(ActionEvent e) {	
			String vstup = JOptionPane.showInputDialog(Hlavni.okno, "Øetìzec pro zpracování automatem", "Zadej øetìzec", JOptionPane.DEFAULT_OPTION);
			if(vstup!=null){
					vstup = vstup.trim();
				if(vstup.length()>0){
					Hlavni.okno.vstup.setText(vstup);
				}
			}
			Hlavni.okno.repaint();
		}
	}
	
	/**
	 * Zajistuje nacteni retezce ze souboru do vstupniho pole
	 */
	class NactiRetezecSouborAkce extends AbstractAction {
		/** defaultni nastaveni */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Konstruktor vytvori popis tlacitka a klavesove zkratky.
		 */
		public NactiRetezecSouborAkce() {
            putValue(NAME, "Naèíst vstup ze souboru..."); // jmeno akce
            putValue(SHORT_DESCRIPTION, "Naète øetìzec znakù ke zpracování automatem ze souboru"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_V)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
		/**
		 * Nacte retezec ze souboru.
		 */
		public void actionPerformed(ActionEvent e) {	
			JFileChooser oteviraciDialog = new JFileChooser("."); // "." znamena aktualni adresar
			File vstupni = null;
			String vstup = "";
			
			if (oteviraciDialog.showOpenDialog(Hlavni.okno) == JFileChooser.APPROVE_OPTION) { // nacte data ze souboru				
				Scanner sc;
				try {
					vstupni = new File(oteviraciDialog.getSelectedFile().getAbsolutePath());
					sc = new Scanner(vstupni);
					vstup = sc.next();
					sc.close();
					
					if(vstup!=null){
							vstup = vstup.trim();
						if(vstup.length()>0){
							Hlavni.okno.vstup.setText(vstup);
						}
					}
				} catch (IOException e1) {
					System.out.println("Chyba pøi ètení souboru.");
				}
			}
			
			Hlavni.okno.repaint();
		}
	}
//====================================================================================================================

	/**
	 * Zajistuje zpracovani znaku automatem
	 */
	class KrokVpredAkce extends AbstractAction {
		/** defaultni nastaveni */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Konstruktor vytvori popis tlacitka a klavesove zkratky.
		 */
		public KrokVpredAkce() {
            putValue(NAME, "Krok vpøed"); // jmeno akce
            putValue(SHORT_DESCRIPTION, "Zpracuje jeden znak"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_P)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
		/**
		 * Zpracuje znak (posune automat o krok vpred).
		 */
		public void actionPerformed(ActionEvent e) {	
			Hlavni.getAktualniStav().setBarva(Stav.BEZNY);
			
			String vstup = Hlavni.okno.vstup.getText().trim();
			if(vstup != null && vstup.length()>0){
				char c = vstup.charAt(0);
				if(vpred(c)){
					Hlavni.okno.zpracovany.setText(Hlavni.okno.zpracovany.getText()+c);	
				}
				else{
					JOptionPane.showMessageDialog(Hlavni.okno, "Pro znak "+c+" není definovaná pøechodová funkce\n"
							+ "Automat ho bude ignorovat.",	"Chyba vstupu", JOptionPane.WARNING_MESSAGE);
				}
				Hlavni.okno.vstup.setText(vstup.substring(1));
			}
			
			Hlavni.getAktualniStav().setBarva(Stav.AKTIVNI);
			Hlavni.okno.repaint();
		}
	}
//====================================================================================================================

		/**
		 * Zajistuje vraceni zaku z vystupu na vstup
		 */
		class KrokVzadAkce extends AbstractAction {
			/** defaultni nastaveni */
			private static final long serialVersionUID = 1L;
			
			/**
			 * Konstruktor vytvori popis tlacitka a klavesove zkratky.
			 */
			public KrokVzadAkce() {
	            putValue(NAME, "Krok vzad"); // jmeno akce
	            putValue(SHORT_DESCRIPTION, "Poslední znak na výstupu bude prvním znakem na vstupu"); // popis tlacitka
	            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_Z)); // klavesova zkratka pro navigaci v menu
	            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
			}
		
			/**
			 * Vrati automat o krok zpet (posledni znak na vystupu bude prvnim znakem na vstupu).
			 */
			public void actionPerformed(ActionEvent e) {	
				Hlavni.getAktualniStav().setBarva(Stav.BEZNY);
				
				String vystup = Hlavni.okno.zpracovany.getText().trim();
				if(vystup != null && vystup.length()>0){
					char c = vystup.charAt(vystup.length()-1);
					vzad(c);
					
					Hlavni.okno.vstup.setText(c+Hlavni.okno.vstup.getText());
					Hlavni.okno.zpracovany.setText(vystup.substring(0,vystup.length()-1));
				}
				
				Hlavni.getAktualniStav().setBarva(Stav.AKTIVNI);
				Hlavni.okno.repaint();
			}
		}
}
