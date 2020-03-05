package controladores;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Gestiones.Elementos.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;   

public class GestionProfesor {

	/**
	 * 
	 */
	public static void menuGestion() {

		int opcionElegida = -1;
		do {
			try {
				System.out.println("\n\t\t\tGESTIÓN DE PROFESORES");
				
				System.out.println("\n\t1.- Listado de profesores.");
				System.out.println("\t2.- Añadir de profesor.");
				System.out.println("\t3.- Modificación de profesor.");
				System.out.println("\t4.- Eliminar de profesor.");
				System.out.println("\t0.- Salir");
				System.out.println("\n\tElija una opción: ");
				
				opcionElegida = Utils.getIntConsola(0, 4);
				
				switch (opcionElegida) {
				case 0:
					break;
				case 1:
					listado();
					break;
				case 2: 
					alta();
					break;
				case 3: 
					modificacion();
					break;
				case 4: 
					baja();
					break;
				}
			} catch (ErrorBBDDException e) {
				System.out.println("\n\t\t\tError de acceso a datos: " + e.getMessage() + "\n");
				e.printStackTrace();
			}
		} while (opcionElegida != 0);
	}

	
	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	private static void listado(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM profesor;", Profesor.class);
		
		List<Profesor> profesores = (List<Profesor>) q.getResultList();
		
		for (Profesor profesor : profesores) {
			System.out.println("Profesor: " + profesor.getId() + " nombre: " + profesor.getNombre()+ 
			" apellido1: " + profesor.getApellido1() + " apellido2: " + profesor.getApellido2()
			+ " DNI: " + profesor.getDni()+ " dirección: " + profesor.getDireccion()
			+ " email: " + profesor.getEmail()+ " teléfono: " + profesor.getTelefono());
		}
		System.out.println("\n\tPulse 'Intro' para continuar");
		Utils.pausa();
		em.close();
	}
	
	
	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	private static void alta () throws ErrorBBDDException {
		int id;
		String nombre, apellido1, apellido2, dni, direccion, email, telefono;
		
		System.out.println("\n\tAlta de profesor\n");
		System.out.print("\nIntroduzca 'ID' del profesor: ");
		id = (Integer.parseInt(Utils.getStringConsola()));
		System.out.print("\nIntroduzca 'nombre' del profesor: ");
		nombre = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'primerApellido' del profesor: ");
		apellido1 = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'segundoApellido' del profesor: ");
		apellido2 = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'DNI' del profesor: ");
		dni = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'dirección' del profesor: ");
		direccion = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'email' del profesor: ");
		email = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'teléfono' del profesor: ");
		telefono = (Utils.getStringConsola());
  
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");

		EntityManager em = entityManagerFactory.createEntityManager();

		Profesor p = new Profesor();
		p.setId(id);
		p.setNombre(nombre);
		p.setApellido1(apellido1);
		p.setApellido2(apellido2);
		p.setDni(dni);
		p.setDireccion(direccion);
		p.setEmail(email);
		p.setTelefono(telefono);
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
		TypedQuery<Profesor> q = em.createQuery("SELECT c FROM Profesor as c", Profesor.class);
		
		List<Profesor> Profesores = q.getResultList();
		
		for (Profesor profesor : Profesores) {
			System.out.println("Profesor: " + profesor.getId() + " nombre: " + profesor.getNombre()+ 
					" apellido1: " + profesor.getApellido1() + " apellido2: " + profesor.getApellido2()
					+ " DNI: " + profesor.getDni()+ " dirección: " + profesor.getDireccion()
					+ " email: " + profesor.getEmail()+ " teléfono: " + profesor.getTelefono());
		}
		
		em.close();
		
		System.out.println("\n\tInsertado correctamente!. Pulse 'Intro' para continuar");
		Utils.pausa();
	}



	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	private static void modificacion () throws ErrorBBDDException {
		System.out.println("\n\tModificación de profesores\n");
		int id;
		String str,nombre, apellido1, apellido2, dni, direccion, email, telefono;;
	
		System.out.print("\nIntroduzca 'Id' del profesor a modificar ('Intro' para no modificar): ");
		str = Utils.getStringConsola();
		if (!str.equals("")){
		id = Integer.parseInt(str);
		System.out.print("\nIntroduzca 'nombre' del profesor ('Intro' para no modificar): ");
		nombre = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'primerApellido' del profesor ('Intro' para no modificar): ");
		apellido1 = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'segundoApellido' del profesor ('Intro' para no modificar): ");
		apellido2 = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'DNI' del profesor ('Intro' para no modificar): ");
		dni = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'dirección' del profesor ('Intro' para no modificar): ");
		direccion = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'email' del profesor ('Intro' para no modificar): ");
		email = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'teléfono' del profesor ('Intro' para no modificar): ");
		telefono = (Utils.getStringConsola());
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Profesor> q = em.createQuery("SELECT c FROM Profesor as c where c.id = " + id, Profesor.class);
		
		List<Profesor> Profesores = q.getResultList();
		
		em.getTransaction().begin();
		for (Profesor p : Profesores) {
			p.setNombre(nombre);
			p.setApellido1(apellido1);
			p.setApellido2(apellido2);
			p.setDni(dni);
			p.setDireccion(direccion);
			p.setEmail(email);
			p.setTelefono(telefono);
			em.persist(p);
		}
		em.getTransaction().commit();
		em.close();
		}
		System.out.println("\n\tModificado correctamente!. Pulse 'Intro' para continuar");
		Utils.pausa();
		
		}
	

	
	
	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	private static void baja () throws ErrorBBDDException {
		System.out.println("\n\tEliminación de profesor\n");
		System.out.print("\n Realmente desea eliminar el registro? (S/N): ");
			String str = Utils.getStringConsola();
			if (str.equalsIgnoreCase("S")) { 	
				int id;
				System.out.print("\nIntroduzca 'Id' del profesor a eliminar");
				id = Integer.parseInt(Utils.getStringConsola());
				
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");

				EntityManager em = entityManagerFactory.createEntityManager();

				TypedQuery<Profesor> q = em.createQuery("SELECT c FROM Profesor as c where c.id = " + id, Profesor.class);
				
				List<Profesor> profesores = q.getResultList();
				
				em.getTransaction().begin();
				for (Profesor p : profesores) {
					em.remove(p);
				}
				em.getTransaction().commit();
				em.close();
				System.out.println("\n\tEliminado correctamente!. Pulse 'Intro' para continuar");
				Utils.pausa();
			}
		}
	
	}

