package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * Objekt teto tridy predstavuje okno napovedy, 
 * ve ktere jsou obsazeny informace o aplikaci.
 * @author Deni Tarantikova, Radek Vais
 * @version 16. 11. 2014
 */
public class NapovedaOkno extends JFrame {
	/**	defaultni nastaveni */
	private static final long serialVersionUID = 1L;
	/** stredne zluta barva */
	private final Color STREDNE_ZLUTA = new Color(255,247,195);
	/** stredne modra barva */
	private final Color STREDNE_MODRA = new Color(179,196,227);

	/**
	 * Konstruktor vytvori okno s rozvrzenim aplikace a prislusnym
	 * popisem jednotlivych prvku.
	 */
	public NapovedaOkno() {	
		this.setTitle("Nápovìda");
		this.setLocationRelativeTo(null);
		this.setSize(500, 300);
		this.setResizable(false);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(STREDNE_ZLUTA);
		
		JTextArea popis = new JTextArea("Tato aplikace vznikla v zimním semestru akad. roku 2014/2015 "
				+ "jako semestrální práce z pøedmìtu Teoretická informatika "
				+ "Katedry informatiky a výpoèetní techniky "
				+ "na Západoèeské univerzitì v Plzni.\n\n Nápovìda: "
				+ "\n1) Naètìte ze souboru vstupní automat nebo použijte pøipravený."
				+ "\n2) Zadejte vstupní symbol nebo vstupní øetìzec. "
				+ "Mùžete ho naèíst i ze souboru v textovém formátu."
				+ "\n3) Tlaèítky \"Krok vpøed\" a \"Krok vzad\" krokujete automat."
				+ "\n4) Tlaèítkem \"Reset\" vrátíte automat do pùvodního stavu."
				+ "\n5) V polích v záložce \"Zpracovávání znakù\" vidíte aktuální stav zpracovávání vstupu.");
		popis.setBackground(STREDNE_ZLUTA);
		popis.setLineWrap(true);
		popis.setWrapStyleWord(true);
		popis.setEditable(false);
		popis.setSelectionColor(STREDNE_MODRA);
		JLabel autor = new JLabel("Autoøi: Deni Tarantíková, Radek Vais");
		
		panel.add(popis, BorderLayout.CENTER);
		panel.add(autor, BorderLayout.SOUTH);
		this.add(panel);
		
		this.setLocation(this.getLocation().x - this.getWidth()/2, this.getLocation().y - this.getHeight()/2);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
	}
	
}

