package controladores;

import java.util.Scanner;

public class Utils {

	private static Scanner scanner = null;
	
	
	/**
	 * 
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	public static int getIntConsola(int minimo, int maximo) {
		boolean numeroValido = false;
		int numero = 0;
		
		do {
			try {
				numero = Integer.parseInt (getScanner().nextLine());
				if (numero >= minimo && numero <= maximo) {
					numeroValido = true;
				}
			} catch (Exception e) {}
			if (!numeroValido) {
				System.out.println("Número incorrecto. Debe introducir valor entre " + minimo + " y " + maximo);
			}
		} while (!numeroValido);
		
		return numero;
	}

	
	
	/**
	 * 
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	public static int getIntConsola(int minimo) {
		boolean numeroValido = false;
		int numero = 0;
		
		do {
			try {
				numero = Integer.parseInt (getScanner().nextLine());
				if (numero >= minimo) {
					numeroValido = true;
				}
			} catch (Exception e) {}
			if (!numeroValido) {
				System.out.println("Número incorrecto. Debe introducir valor mayor que " + minimo);
			}
		} while (!numeroValido);
		
		return numero;
	}

	
	
	/**
	 * 
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	public static String getStringConsola() {
		return getScanner().nextLine();
	}

	
	
	/**
	 * 
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	public static void pausa() {
		getScanner().nextLine();
	}


	
	public static Scanner getScanner () {
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}
		return scanner;
	}
}
