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
 * @author Deni Tarantikova
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
		
		JTextArea popis = new JTextArea("Tato aplikace vznikla v rámci semestrální práce "
				+ "z pøedmìtu Teoretická informatika Katedry informatiky a výpoèetní techniky "
				+ "na Západoèeské univerzitì v Plzni");
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

