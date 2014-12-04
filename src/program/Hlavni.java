package program;


import gui.HlavniOkno;
import gui.Stav;

import java.util.List;
import java.util.Scanner;

import automat.MoorAutomat;

public class Hlavni {

	private static Scanner sc = new Scanner(System.in);
	
	public static MoorAutomat automat = null;
	public static List<Stav> stavy = null;
	
	public static void main(String[] args) {
		
		//nacteni dat
		stavy = Loader.loadStavy("01.automat");
		automat = Loader.loadAutomat("01.automat");
		stavy.get(automat.getAktualniStav()).setBarva(Stav.AKTIVNI);
		// konec nacteni dat
		
		if(automat != null && stavy!=null){
			new HlavniOkno();
		}else{
			System.out.println("Data nebyla korektnì naètena.");
		}
	
	}
}
