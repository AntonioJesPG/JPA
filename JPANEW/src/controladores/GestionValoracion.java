package controladores;

import java.util.Date;
import java.util.List;

import model.Gestiones.Elementos.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;   

public class GestionValoracion {
	
	/**
	 * 
	 */
	public static void menuGestion() {

		int opcionElegida = -1;
		do {
				System.out.println("\n\t\t\tGESTIÓN DE VALORACION");
				
				System.out.println("\n\t1.- Valoración de Materia.");
				System.out.println("\t2.- Valoración Trimestral.");
				System.out.println("\t3.- Tipo de valoración Trimestral.");
				System.out.println("\t0.- Salir");
				System.out.println("\n\tElija una opción: ");
				
				opcionElegida = Utils.getIntConsola(0, 4);
				
				switch (opcionElegida) {
				case 0:
					break;
				case 1:
					GestionValoracionMateria.menuGestion();
				case 2: 
					GestionValoracionTrimestral.menuGestion();
				case 3: 
					GestionTipoValoracionTrimestral.menuGestion();
				}
			
		} while (opcionElegida != 0);
	}

	
}
