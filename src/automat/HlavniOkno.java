package automat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

public class HlavniOkno extends JFrame {
	
	/** stredne modra barva */
	private final Color STREDNE_MODRA = new Color(179,196,227);
	/** svetle zluta barva */
	private final Color SVETLE_ZLUTA = new Color(255,251,228);
	/** svetle modra barva */
	private final Color SVETLE_MODRA = new Color(223,231,247);
	/** stredne zluta barva */
	private final Color STREDNE_ZLUTA = new Color(253,236,126);
	/** defaultni nastaveni */
	private static final long serialVersionUID = 1L;
	/** kreslici platno */
	private Platno platno;
	/** umoznuje pristup k jednotlivym akcim */
	private Akce akce;
	
	public HlavniOkno() {		
		this.setTitle("Koneèný automat s výstupní funkcí");	
		this.setBackground(SVETLE_ZLUTA);
		this.setLocationRelativeTo(null);
		this.setSize(1000, 800);
		this.setLocation(this.getLocation().x - this.getWidth()/2, this.getLocation().y - this.getHeight()/2);
		this.platno = new Platno();
		platno.setBackground(SVETLE_ZLUTA);
		akce = new Akce(platno);
		JScrollPane posuvnik = new JScrollPane(platno);
		this.setJMenuBar(getMenu());
		this.add(getHorniPanel(), BorderLayout.NORTH);
		this.add(posuvnik, BorderLayout.CENTER);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	/**
	 * Metoda vytvori cele menu s polozkami.
	 * @return lista menu
	 */
	private JMenuBar getMenu() {
		JMenuBar hlavniMB = new JMenuBar();
		
		hlavniMB.setBackground(STREDNE_MODRA);
		hlavniMB.add(menuSoubor());
        hlavniMB.add(menuAutomat());
        hlavniMB.add(menuNapoveda());
        
        return hlavniMB;
	}
	
	/**
	 * Vytvori menu soubor
	 * @return menu soubor
	 */
	private JMenu menuSoubor() {
		JMenu soubor = new JMenu("Soubor");
		soubor.setBackground(SVETLE_MODRA);
		
		JMenuItem polozkaMenu = new JMenuItem("Naèíst automat ze souboru...");
		polozkaMenu.setBackground(SVETLE_MODRA);
        soubor.add(polozkaMenu);
        
        polozkaMenu = new JMenuItem("Naèíst vstup ze souboru...");
        polozkaMenu.setBackground(SVETLE_MODRA);
        soubor.add(polozkaMenu);
        soubor.addSeparator();
        
        polozkaMenu = new JMenuItem("Uložit výstup");
        polozkaMenu.setBackground(SVETLE_MODRA);
        soubor.add(polozkaMenu);  
        soubor.addSeparator();
        
        polozkaMenu = new JMenuItem(akce.new KonecAkce());
        polozkaMenu.setBackground(SVETLE_MODRA);
        soubor.add(polozkaMenu);
        
        return soubor;
	}
	
	/**
	 * Vytvori menu automat.
	 * @return menu automat
	 */
	private JMenu menuAutomat() {
        JMenu automat = new JMenu("Automat");
        
        JMenuItem polozkaMenu= new JMenuItem("Zadat nový vstup");
        polozkaMenu.setBackground(SVETLE_MODRA);
        automat.add(polozkaMenu);
        automat.addSeparator();
        
        polozkaMenu = new JMenuItem("Spustit/zastavit animaci");
        polozkaMenu.setBackground(SVETLE_MODRA);
        automat.add(polozkaMenu);
        automat.addSeparator();
        
        polozkaMenu = new JMenuItem("Na zaèátek");
        polozkaMenu.setBackground(SVETLE_MODRA);
        automat.add(polozkaMenu);
        polozkaMenu = new JMenuItem("Na konec");
        polozkaMenu.setBackground(SVETLE_MODRA);
        automat.add(polozkaMenu);
        automat.addSeparator();
        
        polozkaMenu = new JMenuItem("Krok vpøed");
        polozkaMenu.setBackground(SVETLE_MODRA);
        automat.add(polozkaMenu);
        polozkaMenu = new JMenuItem("Krok vzad");
        polozkaMenu.setBackground(SVETLE_MODRA);
        automat.add(polozkaMenu);
        
        return automat;
	}
	
	/**
	 * Vytvori menu napoveda.
	 * @return menu napoveda
	 */
	private JMenu menuNapoveda() {
        JMenu napoveda = new JMenu("Nápovìda");
        
        JMenuItem polozkaMenu = new JMenuItem(akce.new NapovedaAkce());
        polozkaMenu.setBackground(SVETLE_MODRA);
        napoveda.add(polozkaMenu);
        
        return napoveda;
	}
	
	/**
	 * Vrati horni panel s tlacitky.
	 * @return horni panel
	 */
	private JPanel getHorniPanel() {
		JPanel horniPN = new JPanel(new BorderLayout());
		horniPN.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
		horniPN.setBackground(STREDNE_ZLUTA);
		
		horniPN.add(getTlacitkaAkcePN(), BorderLayout.CENTER);
	    
		return horniPN;
	}
	
	/**
	 * Vrati panel s tlacitky slouzici ke krokovani automatu.
	 * @return panel s tlacitky ke krokovani automatu
	 */
	private JPanel getTlacitkaAkcePN() {
		JPanel tlacitkaPN = new JPanel();
		tlacitkaPN.setBorder(BorderFactory.createTitledBorder("Akce"));
		tlacitkaPN.setBackground(STREDNE_ZLUTA);
		
		JButton btVstup = new JButton();
		btVstup.setText("Zadat nový vstup");
		//btVstup.setPreferredSize(new Dimension(30,30));
		
		JButton btZacatek = new JButton();
		btZacatek.setText("Na zaèátek");
		//btZacatek.setPreferredSize(new Dimension(30,30));
		
		JButton btVzad = new JButton();
		btVzad.setText("Krok vzad");
		//btVzad.setPreferredSize(new Dimension(30,30));
		
		JButton btAnimace = new JButton();
		btAnimace.setText("Spustit/zastavit animaci");
		//btAnimace.setPreferredSize(new Dimension(30,30));
		
		JButton btVpred = new JButton();
		btVpred.setText("Krok vpøed");
		//btVpred.setPreferredSize(new Dimension(30,30));
		
		JButton btKonec = new JButton();
		btKonec.setText("Na konec");
		//btKonec.setPreferredSize(new Dimension(30,30));
			
		tlacitkaPN.add(btVstup);
		tlacitkaPN.add(new JSeparator());
		tlacitkaPN.add(btZacatek);
		tlacitkaPN.add(btVzad);
		tlacitkaPN.add(btAnimace);
		tlacitkaPN.add(btVpred);
		tlacitkaPN.add(btKonec);
		
		return tlacitkaPN;
	}
}
