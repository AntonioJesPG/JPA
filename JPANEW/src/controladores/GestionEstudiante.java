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

public class GestionEstudiante {

	/**
	 * 
	 */
	public static void menuGestion() {

		int opcionElegida = -1;
		do {
			try {
				System.out.println("\n\t\t\tGESTIÓN DE ESTUDIANTES");
				
				System.out.println("\n\t1.- Listado de estudiantes.");
				System.out.println("\t2.- Añadir de estudiante.");
				System.out.println("\t3.- Modificación de estudiante.");
				System.out.println("\t4.- Eliminar de estudiante.");
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
		
		Query q = em.createNativeQuery("SELECT * FROM estudiante;", Estudiante.class);
		
		List<Estudiante> estudiantes = (List<Estudiante>) q.getResultList();
		
		for (Estudiante estudiante : estudiantes) {
			System.out.println("Estudiante: " + estudiante.getId() + " nombre: " + estudiante.getNombre()+ 
			" apellido1: " + estudiante.getApellido1() + " apellido2: " + estudiante.getApellido2()
			+ " DNI: " + estudiante.getDni()+ " dirección: " + estudiante.getDireccion()
			+ " email: " + estudiante.getEmail()+ " teléfono: " + estudiante.getTelefono());
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
		
		System.out.println("\n\tAlta de estudiante\n");
		System.out.print("\nIntroduzca 'ID' del estudiante: ");
		id = (Integer.parseInt(Utils.getStringConsola()));
		System.out.print("\nIntroduzca 'nombre' del estudiante: ");
		nombre = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'primerApellido' del estudiante: ");
		apellido1 = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'segundoApellido' del estudiante: ");
		apellido2 = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'DNI' del estudiante: ");
		dni = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'dirección' del estudiante: ");
		direccion = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'email' del estudiante: ");
		email = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'teléfono' del estudiante: ");
		telefono = (Utils.getStringConsola());
  
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");

		EntityManager em = entityManagerFactory.createEntityManager();

		Estudiante e = new Estudiante();
		e.setId(id);
		e.setNombre(nombre);
		e.setApellido1(apellido1);
		e.setApellido2(apellido2);
		e.setDni(dni);
		e.setDireccion(direccion);
		e.setEmail(email);
		e.setTelefono(telefono);
		
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		
		TypedQuery<Estudiante> q = em.createQuery("SELECT c FROM Estudiante as c", Estudiante.class);
		
		List<Estudiante> Estudiantes = q.getResultList();
		
		for (Estudiante estudiante : Estudiantes) {
			System.out.println("Estudiante: " + estudiante.getId() + " nombre: " + estudiante.getNombre()+ 
					" apellido1: " + estudiante.getApellido1() + " apellido2: " + estudiante.getApellido2()
					+ " DNI: " + estudiante.getDni()+ " dirección: " + estudiante.getDireccion()
					+ " email: " + estudiante.getEmail()+ " teléfono: " + estudiante.getTelefono());
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
		System.out.println("\n\tModificación de estudiantes\n");
		int id;
		String str,nombre, apellido1, apellido2, dni, direccion, email, telefono;;
	
		System.out.print("\nIntroduzca 'Id' del estudiante a modificar ('Intro' para no modificar): ");
		str = Utils.getStringConsola();
		if (!str.equals("")){
		id = Integer.parseInt(str);
		System.out.print("\nIntroduzca 'nombre' del estudiante ('Intro' para no modificar): ");
		nombre = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'primerApellido' del estudiante ('Intro' para no modificar): ");
		apellido1 = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'segundoApellido' del estudiante ('Intro' para no modificar): ");
		apellido2 = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'DNI' del estudiante ('Intro' para no modificar): ");
		dni = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'dirección' del estudiante ('Intro' para no modificar): ");
		direccion = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'email' del estudiante ('Intro' para no modificar): ");
		email = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'teléfono' del estudiante ('Intro' para no modificar): ");
		telefono = (Utils.getStringConsola());
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Estudiante> q = em.createQuery("SELECT c FROM Estudiante as c where c.id = " + id, Estudiante.class);
		
		List<Estudiante> Estudiantes = q.getResultList();
		
		em.getTransaction().begin();
		for (Estudiante e : Estudiantes) {
			e.setNombre(nombre);
			e.setApellido1(apellido1);
			e.setApellido2(apellido2);
			e.setDni(dni);
			e.setDireccion(direccion);
			e.setEmail(email);
			e.setTelefono(telefono);
			em.persist(e);
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
		System.out.println("\n\tEliminación de estudiante\n");
		System.out.print("\n Realmente desea eliminar el registro? (S/N): ");
			String str = Utils.getStringConsola();
			if (str.equalsIgnoreCase("S")) { 	
				int id;
				System.out.print("\nIntroduzca 'Id' del estudiante a eliminar");
				id = Integer.parseInt(Utils.getStringConsola());
				
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");

				EntityManager em = entityManagerFactory.createEntityManager();

				TypedQuery<Estudiante> q = em.createQuery("SELECT c FROM Estudiante as c where c.id = " + id, Estudiante.class);
				
				List<Estudiante> estudiantes = q.getResultList();
				
				em.getTransaction().begin();
				for (Estudiante e : estudiantes) {
					em.remove(e);
				}
				em.getTransaction().commit();
				em.close();
				System.out.println("\n\tEliminado correctamente!. Pulse 'Intro' para continuar");
				Utils.pausa();
			}
		}
	
	}

