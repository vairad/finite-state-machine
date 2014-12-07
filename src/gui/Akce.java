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
	public static AbstractAction vstup = new Akce().new ZnakAkce();
	/** vloz retezec ke zpracovani */
	public static AbstractAction string = new Akce().new NactiRetezecAkce();
	/** zpracuje znak ze vstupu */
	public static AbstractAction krokVpred = new Akce().new KrokVpredAkce();
	/** vrati znak do vstupu */
	public static AbstractAction krokVzad = new Akce().new KrokVzadAkce();
	
//===================================================================================================================

	public static boolean vpred(char c) {
		if(!Hlavni.automat.jePrvkemVstupu(c)){
			JOptionPane.showMessageDialog(Hlavni.okno, "Zadany znak neni prvkem vstupni abecedy.\n"
					+ "Automat ho bude ignorovat.",	"Chyba vstupu", JOptionPane.WARNING_MESSAGE);
			return false;
		}else{
			return Hlavni.automat.zpracujVstup(c);
		}
		
	}
	
	public static boolean vzad(char c) {
		if(!Hlavni.automat.jePrvkemVstupu(c)){
			JOptionPane.showMessageDialog(Hlavni.okno, "Zadany znak neni prvkem vstupni abecedy.\n"
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
		private static final long serialVersionUID = 1L;
		
		public ZnakAkce() {
			//ImageIcon ikona = new ImageIcon(getClass().getResource("/images/help.gif"));
            putValue(NAME, "Vstup znaku"); // jmeno akce
          //  putValue(SMALL_ICON, ikona); // na tlacitku bude jen ikonka
            putValue(SHORT_DESCRIPTION, "Provede krok automatu"); // popis tlacitka
            putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_D)); // klavesova zkratka pro navigaci v menu
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
		public void actionPerformed(ActionEvent e) {
			Hlavni.getAktualniStav().setBarva(Stav.BEZNY);
			
			String vstup = JOptionPane.showInputDialog(Hlavni.okno, "Zadej jeden znak pro zpracovani automatem", "Zadej znak", JOptionPane.DEFAULT_OPTION);
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
		private static final long serialVersionUID = 1L;
		
		public NactiRetezecAkce() {
			//ImageIcon ikona = new ImageIcon(getClass().getResource("/images/help.gif"));
            putValue(NAME, "Vstup retezce"); // jmeno akce
          //  putValue(SMALL_ICON, ikona); // na tlacitku bude jen ikonka
            putValue(SHORT_DESCRIPTION, "Nacte retezec znaku ke zpracovani automatem"); // popis tlacitka
          //  putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_D)); // klavesova zkratka pro navigaci v menu
          //  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
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
//====================================================================================================================

	/**
	 * Zajistuje zpracovani znaku automatem
	 */
	class KrokVpredAkce extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public KrokVpredAkce() {
			//ImageIcon ikona = new ImageIcon(getClass().getResource("/images/help.gif"));
            putValue(NAME, "Krok vpred"); // jmeno akce
          //  putValue(SMALL_ICON, ikona); // na tlacitku bude jen ikonka
            putValue(SHORT_DESCRIPTION, "Nacte retezec znaku ke zpracovani automatem"); // popis tlacitka
          //  putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_D)); // klavesova zkratka pro navigaci v menu
          //  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
		}
	
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
			private static final long serialVersionUID = 1L;
			
			public KrokVzadAkce() {
				//ImageIcon ikona = new ImageIcon(getClass().getResource("/images/help.gif"));
	            putValue(NAME, "Krok vzad"); // jmeno akce
	          //  putValue(SMALL_ICON, ikona); // na tlacitku bude jen ikonka
	            putValue(SHORT_DESCRIPTION, "Posledni znak na vystupu vrati vstup a provede zmenu automatu"); // popis tlacitka
	          //  putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_D)); // klavesova zkratka pro navigaci v menu
	          //  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK)); // klavesova zkratka, funguje vzdy
			}
		
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
