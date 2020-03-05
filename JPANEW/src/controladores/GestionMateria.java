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

public class GestionMateria {

	/**
	 * 
	 */
	public static void menuGestion() {

		int opcionElegida = -1;
		do {
			try {
				System.out.println("\n\t\t\tGESTIÓN DE MATERIA");
				
				System.out.println("\n\t1.- Listado de materia.");
				System.out.println("\t2.- Añadir de materia.");
				System.out.println("\t3.- Modificación de materia.");
				System.out.println("\t4.- Eliminar de materia.");
				System.out.println("\t0.- Salir");
				System.out.println("\n\tElija una opción: ");
				
				opcionElegida = Utils.getIntConsola(0, 4);
				
				switch (opcionElegida) {
				case 0:
					break;
				case 1:
					listado(true);
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
	private static void listado(boolean pausafinal) throws ErrorBBDDException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM materia;", Materia.class);
		
		List<Materia> materias = (List<Materia>) q.getResultList();
		
		for (Materia materia : materias) {
			System.out.println("Materia: " + materia.getId() + " nombre: " + materia.getNombre()+ 
			" acronimo: " + materia.getAcronimo() + " curso: " + materia.getCurso().getDescripcion());
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
		int id, idCurso;
		String nombre, acronimo;
		
		System.out.println("\n\tAlta de materia\n");
		System.out.print("\nIntroduzca 'ID' de la materia: ");
		id = (Integer.parseInt(Utils.getStringConsola()));
		System.out.print("\nIntroduzca 'nombre' de la materia: ");
		nombre = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'Acronimo' de la materia: ");
		acronimo = (Utils.getStringConsola());
		System.out.print("\nIntroduzca 'ID' del curso: ");
		idCurso = (Integer.parseInt(Utils.getStringConsola()));
		
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");

		EntityManager em = entityManagerFactory.createEntityManager();

		Materia m = new Materia();
		m.setId(id);
		m.setNombre(nombre);
		m.setAcronimo(acronimo);
		
		TypedQuery<Curso> q = em.createQuery("SELECT c FROM Curso as c where c.id = " + idCurso, Curso.class);
		List<Curso> Cursos = q.getResultList();
		for (Curso cur : Cursos) {
			m.setCurso(cur);
		}
		
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
		
		TypedQuery<Materia> qe = em.createQuery("SELECT c FROM Materia as c", Materia.class);
		List<Materia> Materias = qe.getResultList();
		
		for (Materia materia : Materias) {
			System.out.println("Materia: " + materia.getId() + " nombre: " + materia.getNombre()+ 
					" acronimo: " + materia.getAcronimo() + " curso: " + materia.getCurso().getDescripcion());
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
			System.out.println("\n\tModificación de materia\n");
			int id, idCurso;
			String nombre, acronimo, str;
		
			System.out.print("\nIntroduzca 'Id' de la materia ('Intro' para no modificar): ");
			str = Utils.getStringConsola();
			if (!str.equals("")){
			id = Integer.parseInt(str);
			System.out.print("\nIntroduzca 'nombre' de la materia  ('Intro' para no modificar): ");
			nombre = Utils.getStringConsola();
			System.out.print("\nIntroduzca 'acronimo' de la materia  ('Intro' para no modificar): ");
			acronimo = Utils.getStringConsola();
			System.out.print("\nIntroduzca 'ID' del curso  ('Intro' para no modificar): ");
			idCurso = Integer.parseInt(Utils.getStringConsola());
			
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");

			EntityManager em = entityManagerFactory.createEntityManager();

			TypedQuery<Materia> q = em.createQuery("SELECT c FROM Materia as c where c.id = " + id, Materia.class);
			
			List<Materia> Materias = q.getResultList();
			
			em.getTransaction().begin();
			for (Materia m : Materias) {
				m.setNombre(nombre);
				m.setAcronimo(acronimo);
				TypedQuery<Curso> qu = em.createQuery("SELECT c FROM Curso as c where c.id = " + idCurso, Curso.class);
				List<Curso> Cursos = qu.getResultList();
				for (Curso cur : Cursos) {
					m.setCurso(cur);
				}
				em.persist(m);
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
		
		System.out.println("\n\tEliminación de materia\n");
		System.out.print("\n Realmente desea eliminar el registro? (S/N): ");
			String str = Utils.getStringConsola();
			if (str.equalsIgnoreCase("S")) { 	
				int id;
				System.out.print("\nIntroduzca 'Id' de la materia a eliminar");
				id = Integer.parseInt(Utils.getStringConsola());
				
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAKK");

				EntityManager em = entityManagerFactory.createEntityManager();

				TypedQuery<Materia> q = em.createQuery("SELECT c FROM Materia as c where c.id = " + id, Materia.class);
				
				List<Materia> materias = q.getResultList();
				
				em.getTransaction().begin();
				for (Materia mat : materias) {
					em.remove(mat);
				}
				em.getTransaction().commit();
				em.close();
				System.out.println("\n\tEliminado correctamente!. Pulse 'Intro' para continuar");
				Utils.pausa();
		}
	}

	
}
