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
		this.setTitle("N�pov�da");
		this.setLocationRelativeTo(null);
		this.setSize(500, 300);
		this.setResizable(false);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(STREDNE_ZLUTA);
		
		JTextArea popis = new JTextArea("Tato aplikace vznikla v zimn�m semestru akad. roku 2014/2015 "
				+ "jako semestr�ln� pr�ce z p�edm�tu Teoretick� informatika "
				+ "Katedry informatiky a v�po�etn� techniky "
				+ "na Z�pado�esk� univerzit� v Plzni.\n\n N�pov�da: "
				+ "\n1) Na�t�te ze souboru vstupn� automat nebo pou�ijte p�ipraven�."
				+ "\n2) Zadejte vstupn� symbol nebo vstupn� �et�zec. "
				+ "M��ete ho na��st i ze souboru v textov�m form�tu."
				+ "\n3) Tla��tky \"Krok vp�ed\" a \"Krok vzad\" krokujete automat."
				+ "\n4) Tla��tkem \"Reset\" vr�t�te automat do p�vodn�ho stavu."
				+ "\n5) V pol�ch v z�lo�ce \"Zpracov�v�n� znak�\" vid�te aktu�ln� stav zpracov�v�n� vstupu.");
		popis.setBackground(STREDNE_ZLUTA);
		popis.setLineWrap(true);
		popis.setWrapStyleWord(true);
		popis.setEditable(false);
		popis.setSelectionColor(STREDNE_MODRA);
		JLabel autor = new JLabel("Auto�i: Deni Tarant�kov�, Radek Vais");
		
		panel.add(popis, BorderLayout.CENTER);
		panel.add(autor, BorderLayout.SOUTH);
		this.add(panel);
		
		this.setLocation(this.getLocation().x - this.getWidth()/2, this.getLocation().y - this.getHeight()/2);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
	}
	
}

