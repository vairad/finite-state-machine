package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import program.Hlavni;

/**
 * Objekt teto tridy predstavuje hlavni okno aplikace, 
 * kde se zobrazuje automat a ovladaci prvky k nìmu.
 * @author Deni Tarantikova, Radek Vais
 * @version 7. 12. 2014
 */
public class HlavniOkno extends JFrame {
	/** defaultni nastaveni */
	private static final long serialVersionUID = 1L;
	
	/** stredne modra barva */
	private final Color STREDNE_MODRA = new Color(179,196,227);
	/** svetle zluta barva */
	private final Color SVETLE_ZLUTA = new Color(255,251,228);
	/** svetle modra barva */
	private final Color SVETLE_MODRA = new Color(223,231,247);
	/** stredne zluta barva */
	private final Color STREDNE_ZLUTA = new Color(253,236,126);
	
	/** kreslici platno */
	private Platno platno;
	
	/** zobrazeni front znaku automatu */
	public JTextField zpracovany;
	/** Textove pole vstupu */
	public JTextField vstup;
	/** Textove pole aktualniho vystupu */
	public JTextField vystup;
	/** Textove pole aktualniho vystupniho retezce */
	public JTextField vystupni_retezec;
	
	/**
	 * Konstruktor vytvori hlavni okno s menu, tlacitky a platnem.
	 */
	public HlavniOkno() {		
		this.setTitle("Koneèný automat s výstupní funkcí");	
		this.setBackground(SVETLE_ZLUTA);
		
		this.setSize(new Dimension(500,500));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.platno = new Platno();
		platno.setBackground(SVETLE_ZLUTA);
		
		initTextPole();
		
		JScrollPane posuvnik = new JScrollPane(platno);
		this.setJMenuBar(getMenu());
		this.add(getHorniPanel(), BorderLayout.NORTH);
		this.add(posuvnik, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		vystupni_retezec.setText(vystupni_retezec.getText()+Hlavni.automat.getVystup());
	}
	
	/**
	 * Inicializuje textova pole.
	 */
	private void initTextPole(){
		Dimension d = new Dimension(150,30);
		
		vystup = new JTextField();
		vystup.setPreferredSize(new Dimension(30,30));
		vystup.setEnabled(false);
		Font f = new Font("Calibri", Font.BOLD, 20);
		vystup.setFont(f);
		vystup.setDisabledTextColor(Color.RED);
		
		vystupni_retezec = new JTextField();
		vystupni_retezec.setPreferredSize(d);
		vystupni_retezec.setEnabled(false);
		vystupni_retezec.setDisabledTextColor(Color.RED);
		
		vstup = new JTextField();
		vstup.setPreferredSize(d);
		vstup.setEnabled(false);
		vstup.setDisabledTextColor(Color.BLACK);
		
		zpracovany = new JTextField();
		zpracovany.setPreferredSize(d);
		zpracovany.setEnabled(false);
		zpracovany.setDisabledTextColor(Color.GRAY);
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
		
		JMenuItem polozkaMenu = new JMenuItem(Akce.automatSoubor);
		polozkaMenu.setBackground(SVETLE_MODRA);
        soubor.add(polozkaMenu);
        
        polozkaMenu = new JMenuItem(Akce.stringSoubor);
        polozkaMenu.setBackground(SVETLE_MODRA);
        soubor.add(polozkaMenu);
        soubor.addSeparator();
        
        polozkaMenu = new JMenuItem(Akce.konec);
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
        
        JMenuItem polozkaMenu= new JMenuItem(Akce.vstup);
        polozkaMenu.setBackground(SVETLE_MODRA);
        automat.add(polozkaMenu);
        
        polozkaMenu= new JMenuItem(Akce.string);
        polozkaMenu.setBackground(SVETLE_MODRA);
        automat.add(polozkaMenu);
        
        automat.addSeparator();
        polozkaMenu = new JMenuItem(Akce.krokVpred);
        polozkaMenu.setBackground(SVETLE_MODRA);
        automat.add(polozkaMenu);
        polozkaMenu = new JMenuItem(Akce.krokVzad);
        polozkaMenu.setBackground(SVETLE_MODRA);
        automat.add(polozkaMenu);
        
        automat.addSeparator();
        polozkaMenu = new JMenuItem(Akce.reset);
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
        
        JMenuItem polozkaMenu = new JMenuItem(Akce.napoveda);
        polozkaMenu.setBackground(SVETLE_MODRA);
        napoveda.add(polozkaMenu);
        
        return napoveda;
	}
	
	/**
	 * Vrati horni panel s tlacitky.
	 * @return horni panel
	 */
	private JScrollPane getHorniPanel() {
		JPanel horniPN = new JPanel(new BorderLayout());
		horniPN.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
		horniPN.setBackground(STREDNE_ZLUTA);
		
		JPanel vrsek = new JPanel(new FlowLayout(FlowLayout.CENTER));
		vrsek.setBackground(STREDNE_ZLUTA);
		JPanel spodek = new JPanel(new FlowLayout(FlowLayout.CENTER));
		spodek.setBackground(STREDNE_ZLUTA);
		
		
		vrsek.add(getTlacitkaAkcePN());
		vrsek.add(getTlacitkaVstupPN());
		
		spodek.add(getTlacitkaVystupPN());
		spodek.add(getVystupPN());
		
		horniPN.add(vrsek, BorderLayout.NORTH);
		horniPN.add(spodek, BorderLayout.SOUTH);
		
		return new JScrollPane(horniPN);
	}
	
	/**
	 * Vrati panel s tlacitky slouzici ke krokovani automatu.
	 * @return panel s tlacitky ke krokovani automatu
	 */
	private JPanel getTlacitkaAkcePN() {
		JPanel tlacitkaPN = new JPanel();
		tlacitkaPN.setBorder(BorderFactory.createTitledBorder("Akce"));
		tlacitkaPN.setBackground(STREDNE_ZLUTA);
	
		JButton btVpred = new JButton(Akce.krokVpred);
		JButton btVzad = new JButton(Akce.krokVzad);
		JButton btReset = new JButton(Akce.reset);

		tlacitkaPN.add(btVzad);
		tlacitkaPN.add(btVpred);
		tlacitkaPN.add(btReset);
		
		return tlacitkaPN;
	}
	
	/**
	 * Vrati panel s komponentami slouzici k informování o prùbìhu zpracování øetìzce.
	 * @return panel s komponentami o práci automatu
	 */
	private JPanel getTlacitkaVystupPN() {
		JPanel tlacitkaPN = new JPanel();
		tlacitkaPN.setBorder(BorderFactory.createTitledBorder("Zpracovávání znakù"));
		tlacitkaPN.setBackground(STREDNE_ZLUTA);
		
		JLabel zprlb = new JLabel("  Zpracované znaky: ");
		tlacitkaPN.add(zprlb);
		tlacitkaPN.add(zpracovany);
		
		JLabel vstuplb = new JLabel("  Nezpracované znaky: ");
		tlacitkaPN.add(vstuplb);
		tlacitkaPN.add(vstup);
		
		return tlacitkaPN;
	}
	
	/**
	 * Vrati panel s komponentami slouzici k zobrazeni vystupu automatu.
	 * @return panel s komponentami k zobrazeni vystupu
	 */
	private JPanel getVystupPN(){
		JPanel vystupPN = new JPanel();
		vystupPN.setBorder(BorderFactory.createTitledBorder("Výstup automatu"));
		vystupPN.setBackground(STREDNE_ZLUTA);
		
		JLabel vystupRlb = new JLabel("Výstupní øetìzec: ");
		vystupPN.add(vystupRlb);
		vystupPN.add(vystupni_retezec);
		
		JLabel vystuplb = new JLabel("Aktuální výstup: ");
		vystupPN.add(vystuplb);
		vystupPN.add(vystup);
		
		return vystupPN;
	}
	
	/**
	 * Vrati panel s tlacitky slouzici ke vstupu.
	 * @return panel s tlacitky ke vstupu
	 */
	private JPanel getTlacitkaVstupPN() {
		JPanel tlacitkaPN = new JPanel();
		tlacitkaPN.setBorder(BorderFactory.createTitledBorder("Vstup"));
		tlacitkaPN.setBackground(STREDNE_ZLUTA);
		
		JButton btVstup = new JButton(Akce.vstup);		
		JButton btString = new JButton(Akce.string);

		tlacitkaPN.add(zpracovany);
		tlacitkaPN.add(vstup);
		tlacitkaPN.add(btVstup);
		tlacitkaPN.add(btString);
		
		return tlacitkaPN;
	}
}
