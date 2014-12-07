package program;

import gui.Stav;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import automat.MoorAutomat;


/**
 * Trida, ktera se stara o nacteni dat o automatu ze souboru.
 * @author Radek VAIS, Deni TARANTIKOVA
 * @version 3. 12. 2014
 *
 */
public class Loader {

	/**
	 * Nacte konecny automat Moorova typu ze souboru. Ve formatu:
	 * 
	 *Vstupni abeceda:
	 * (libovolny pocet znaku, bez pouziti znaku '0')
	 *Vystupni abeceda:
	 * (libovolny pocet znaku)
	 *Vystupni funkce:
	 * (zanaky vystupni funkce oddelene mezerou)
	 *Pocatecni stav:
	 * (id pocatecniho stavu)
	 *Prechodova funkce:
	 *(ctvercova matice kde znak[i,j] znamena prechod pri tomto znaku ze stavu i do stavu j 
	 * pokud neni hrana definovana na poli musi byt znak '0')
	 * napr.:
	 *0 a 0 0 0 0
	 *0 0 b 0 0 0
	 *0 0 0 c 0 0
	 *0 0 0 0 d 0
	 *0 0 0 0 0 a
	 *0 0 0 0 0 0 
	 * 
	 * 
	 * @param filePath cesta k souboru
	 * @return automat dle souboru, null - pokud nacteni neprobehlo uspesne
	 */
	public static MoorAutomat loadAutomat(String filePath){
		char[] vstupni_abeceda = null;
		char[] vystupni_abeceda = null;
		char[] vystupni_funkce = null;
		int pocatecni_stav = -1;
		char[][] prechodova_funkce = null;
		
		try {			
			BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
			String line = reader.readLine();
			while(line!=null && !line.isEmpty()){
				if(line.trim().equals("Vstupni abeceda:")){
					vstupni_abeceda = nactiAbecedu(reader.readLine());
					line = reader.readLine();
				}
				else if(line.trim().equals("Vystupni abeceda:")){
					vystupni_abeceda = nactiAbecedu(reader.readLine());
					line = reader.readLine();
				}
				else if(line.trim().equals("Vystupni funkce:")){
					vystupni_funkce = nactiAbecedu(reader.readLine());
					line = reader.readLine();
				}
				else if(line.trim().equals("Pocatecni stav:")){
					pocatecni_stav = nactiPocatecniStav(reader.readLine());
					line = reader.readLine();
				}
				else if(line.trim().equals("Prechodova funkce:")){
					prechodova_funkce = nactiPrechodFci(reader);
					line = reader.readLine();
				}
				else{
					line = reader.readLine();
				}
			}
		} catch(FileNotFoundException e){
			System.out.println("Neexistujici soubor.");
		} catch (IOException e) {
			System.out.println("Problem s pristupem k souboru.");
		}
		
		if(
			vstupni_abeceda!=null &&
			vystupni_abeceda!=null &&
			vystupni_funkce!=null &&
			pocatecni_stav != -1 &&
			prechodova_funkce !=null
		  ){
			return new MoorAutomat(prechodova_funkce, 
								   vystupni_funkce,
								   vstupni_abeceda,
								   vystupni_abeceda,
								   pocatecni_stav,
								   pocatecni_stav);
		}else{
			return null;
		}
	}
	
	/**
	 * Nacte libovolnou posloupnost znaku. 
	 * @param line radka se znaky oddelenymi mezerou
	 * @return pole nalezenych znaku
	 */
	private static char[] nactiAbecedu(String line){
		List<Character> abeceda = new ArrayList<Character>();
		for (int i = 0; i < line.length(); i++) {
			if(line.charAt(i)!=' '){
				abeceda.add(line.charAt(i));
			}
		}
		
		char[] pole = new char[abeceda.size()];
		int i=0;
		for (Iterator<Character> it = abeceda.iterator(); it.hasNext(); i++) {
			Character ch = it.next();
			pole[i]=ch;
		}
		return pole;
	}
	
	/**
	 * Nacte ze souboru stavy a vrati jejich seznam.
	 * @param filePath cesta k souboru
	 * @return seznam stavu
	 */
	public static List<Stav> loadStavy(String filePath) {
		List<Stav> stavy = new LinkedList<Stav>();
		try {			
			BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
			String line = reader.readLine();
			while(line!=null && !line.isEmpty()){
				if(line.trim().equals("Stavy:")){
					line = reader.readLine();
					int count = Integer.parseInt(line);
					for(int i = 0; i < count; i++ ){
						line = reader.readLine();
						String[] arg = line.split(";");
						stavy.add(i, new Stav(Integer.parseInt(arg[0]), Integer.parseInt(arg[1]), i, arg[2]));
					}
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(FileNotFoundException e){
			System.out.println("Neexistujici soubor.");
		} catch (IOException e) {
			System.out.println("Problem s pristupem k souboru.");
		}
		return stavy;
	}
	
	/**
	 * Nacte integer z predlozeneho retezce
	 * @param line retezec k hledani
	 * @return int nalezene cislo
	 */
	private static int nactiPocatecniStav(String line) {
		try {
			int stav = Integer.parseInt(line);
			return stav;
		} catch (NumberFormatException e) {
			System.out.println("Spatny format cisla pocatecniho stavu");
			System.exit(12);
		}
		return -1;
	}
	
	/**
	 * Nacte prechodovou funkci konecneho automatu.
	 * @param reader - ukazatel na radku "Prechodova funkce:" v souboru s automatem
	 * @return matice, resp. tabulka prechodu automatu
	 */
	private static char[][] nactiPrechodFci(BufferedReader reader){
		List<char[]> stavy = new ArrayList<char[]>();
		try {
			String line = reader.readLine();
			while(line != null && !line.isEmpty()){
				stavy.add(nactiAbecedu(line));
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("Problem se ctenim souboru.");
			System.exit(18);
		}
		
		char[][] prechody = new char[stavy.size()][];
		int i = 0;
		for (Iterator<char[]> it = stavy.iterator(); it.hasNext(); i++) {
			char[] cs =  it.next();
			prechody[i]=cs;
		}
		return prechody;
	}
}
