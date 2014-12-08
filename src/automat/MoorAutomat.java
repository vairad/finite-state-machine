package automat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Tøída implementuje koneèný automat Moorova typu.
 * Tøída navíc obsahuje možnost logovat èinnost automatu do souboru.
 * 
 * @author Radek VAIS, Deni TARANTIKOVA
 * @version 3.12.2014
 */
public class MoorAutomat {
	
	/** soubor logu akci automatu */
	private BufferedWriter log = null;
	
	/** prechodova funkce */
	private final char[][] prechodova_funkce;
	/** vystupni funkce */
	private final char[] vystupni_funkce;
	
	/** vstupni abeceda */
	private final char[] vstupni_abeceda;
	/** vystupni abeceda */
	private final char[] vystupni_abeceda;
	
	/** pocatecni stav */
	private final int pocatecni_stav;
	/** aktualni stav */
	private int aktualni_stav;
	
	/**
	 * Vytvori automat Moorova typu podle zadanych parametru.
	 * @param prechodova_funkce prechodova funkce
	 * @param vystupni_funkce vystupni funkce
	 * @param vstupni_abeceda vstupni abeceda
	 * @param vystupni_abeceda vystupni abeceda
	 * @param aktualni_stav aktualni stav
	 * @param pocatecni_stav pocatecni stav
	 */
	public MoorAutomat(char[][] prechodova_funkce, char[] vystupni_funkce,
			char[] vstupni_abeceda, char[] vystupni_abeceda, int aktualni_stav,
			int pocatecni_stav) {
		super();
		this.prechodova_funkce = prechodova_funkce;
		this.vystupni_funkce = vystupni_funkce;
		this.vstupni_abeceda = vstupni_abeceda;
		this.vystupni_abeceda = vystupni_abeceda;
		this.aktualni_stav = aktualni_stav;
		this.pocatecni_stav = pocatecni_stav;
	}
	
	/**
	 * Vrátí aktuální hladinu výstupu, respektive znak
	 * @return znak z výstupní abecedy
	 */
	public char getVystup(){
		return vystupni_funkce[aktualni_stav];
	}
	
	/**
	 * Po obdrzeni znaku ze vstupni abecedy, provede prechod dle prechodove funkce.
	 * Pokud neni pro tento vstup definovana hrana, automat nezmeni stav.
	 * 
	 * @param c znak ze vstupni abecedy
	 * @return vraci pravdivostni hodnotu, zda byl prechod proveden dle tabulky, nebo doslo k chybe
	 */
	public boolean zpracujVstup(char c){
		if(!jePrvkemVstupu(c)){
			log("Znak \""+c+"\" neni prvkem vstupni abecedy");
			return false;
		}
		for(int i = 0; i < prechodova_funkce[aktualni_stav].length; i++){
			if(prechodova_funkce[aktualni_stav][i]==c){
				log("Automat pøešel ze stavu: "+aktualni_stav+" do stavu: "+i+" znakem: "+c);
				aktualni_stav=i;
				log("Automat je na vystpni hladine: "+vystupni_funkce[aktualni_stav]+" a ve stavu: "+aktualni_stav);
				return true;
			}
		}
		log("Stav nemá definovanou hranu pro "+c);
		return false;
	}
	
	/**
	 * Po obdrzeni znaku ze vstupni abecedy, provede reverzni prechod dle prechodove funkce. (proti standardnimu smeru)
	 * Pokud neni pro tento vstup definovana hrana, automat nezmeni stav.
	 * 
	 * @param c znak ze vstupni abecedy
	 * @return vraci pravdivostni hodnotu, zda byl prechod proveden dle tabulky, nebo doslo k chybe
	 */
	public boolean zpracujVstupReverse(char c){
		if(!jePrvkemVstupu(c)){
			log("Znak \""+c+"\" neni prvkem vstupni abecedy");
			return false;
		}
		for(int i = 0; i < prechodova_funkce[aktualni_stav].length; i++){
			if(prechodova_funkce[i][aktualni_stav]==c){
				log("Automat byl navracen ze stavu: "+aktualni_stav+" do stavu: "+i+" znakem: "+c);
				aktualni_stav=i;
				return true;
			}
		}
		log("Stav nemá definovanou vstupni hranu pro "+c);
		return false;
	}
	
	/**
	 * Prevede automat do pocatecniho stavu
	 */
	public void reset(){
		log("Automat byl resetovan ze stavu: "+aktualni_stav+" na pocatecni stav: "+pocatecni_stav);
		aktualni_stav=pocatecni_stav;
	}
	
//====================================================================================================================
	/**
	 * Vrati aktualni stav.
	 * @return aktualni stav
	 */
	public int getAktualniStav(){
		return aktualni_stav;
	}
	
	/**
	 * Vrati prechodovou funkci.
	 * @return prechodova funkce
	 */
	public char[][] getPrechodovaFce(){
		return prechodova_funkce;
	}
	
	
//===========================Kontorla abeced==========================================================================
	/**
	 * Kontroluje, zda je znak prvkem vstupni abecedy
	 * @param c znak ke kontorle
	 * @return odpoved true/false
	 */
	public boolean jePrvkemVstupu(char c){
		for (int i = 0; i < vstupni_abeceda.length; i++) {
			if(c==vstupni_abeceda[i]){
				return true;
			}
		}
		return false;
	}

	/**
	 * Kontroluje, zda je znak prvkem vystupni abecedy
	 * @param c znak ke kontorle
	 * @return odpoved true/false
	 */
	public boolean jePrvkemVystupu(char c){
		for (int i = 0; i < vystupni_abeceda.length; i++) {
			if(c==vystupni_abeceda[i]){
				return true;
			}
		}
		return false;
	}
	
//===========================Logovani vystupu==================================================================
	
	/**
	 * Otevre a vytvori soubor logu dle zadane cesty.
	 * @param filePath cesta k souboru
	 */
	public void startLogovaniAutomatu(String filePath){
		try {
			log = new BufferedWriter(new FileWriter(new File(filePath)));
		} catch (IOException e) {
			System.out.println("Nepodaøilo se vytvoøit soubor pro logovani");
		} 
	}
	
	/**
	 * Zapise radek resp. retezec do souboru a nakonec odradkuje
	 * @param s retezec pro zápis
	 */
	public void log(String s){
		if(log!=null){
			try {
				log.write(s);
				log.newLine();
			} catch (IOException e) {
				log=null;
				System.out.println("Pristup do souboru s logem nebyl mozny");
				// pokud se nepovede log, neni treba upozornovat uzivatele...
				// po vetsinou by to melo projit, a zastavit se jiz pri inicializaci
			}
		}
	}
	
	/**
	 * Uzavre soubor logu
	 */
	public void close(){
		if(log!=null){
			try {
				log.close();
			} catch (IOException e) {
				log=null;
				//System.err.println("Pristup do souboru s logem nebyl mozny");
				// pokud se nepovede log, neni treba upozornovat uzivatele...
				// po vetsinou by to melo projit, a zastavit se jiz pri inicializaci
			}
		}
	}
	
}
