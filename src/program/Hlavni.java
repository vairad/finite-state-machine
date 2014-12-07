package program;


import gui.HlavniOkno;
import gui.Stav;

import java.util.List;

import automat.MoorAutomat;

/**
 * Hlavní tøída slouží ke spuštìní aplikace.
 * @author Deni Tarantikova, Radek Vais
 * @version 7. 12. 2014
 */
public class Hlavni {
	
	/** automat, se kterym aplikace pracuje */
	public static MoorAutomat automat = null;
	/** Seznam stavu automatu */
	public static List<Stav> stavy = null;
	/** hlavni okno aplikace */
	public static HlavniOkno okno = null;
	
	/**
	 * Hlavni metoda spousti aplikaci.
	 * @param args nepouzito
	 */
	public static void main(String[] args) {
		
		if(args.length==0){
			System.out.println("Nebyl zadán název souboru");
			System.exit(18);
		}
		//nacteni dat
		stavy = Loader.loadStavy(args[0]);
		automat = Loader.loadAutomat(args[0]);
		stavy.get(automat.getAktualniStav()).setBarva(Stav.AKTIVNI);
		// konec nacteni dat
		
		if(automat != null && stavy!=null){
			okno = new HlavniOkno();
		}else{
			System.out.println("Data nebyla korektnì naètena.");
		}
	
	}
	
	/**
	 * Vrati aktualni stav.
	 * @return aktualni stav
	 */
	public static Stav getAktualniStav(){
		return stavy.get(automat.getAktualniStav());
	}
}
