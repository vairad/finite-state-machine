package program;


import gui.HlavniOkno;
import gui.Stav;

import java.util.List;

import automat.MoorAutomat;

public class Hlavni {
	
	public static MoorAutomat automat = null;
	public static List<Stav> stavy = null;
	
	public static HlavniOkno okno = null;
	
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
	
	public static Stav getAktualniStav(){
		return stavy.get(automat.getAktualniStav());
	}
}
