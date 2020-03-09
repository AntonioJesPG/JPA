package controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.Concesionario;
import modelo.Fabricante;

public class GestionConcesionario {

	/**
	 * @throws ParseException
	 * 
	 */
	public static void menuGestion() throws ParseException {

		int opcionElegida = -1;
		do {
			try {
				System.out.println("\n\t\t\tGESTI�N DE CONCESIONARIOS");

				System.out.println("\n\t1.- Listado de concesionario.");
				System.out.println("\t2.- Alta de concesionario.");
				System.out.println("\t3.- Modificaci�n de concesionario.");
				System.out.println("\t4.- Baja de concesionario.");
				System.out.println("\t0.- Salir");
				System.out.println("\n\tElija una opci�n: ");

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
					eliminacion();
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
	 * @throws ParseException
	 */
	private static void modificacion() throws ErrorBBDDException, ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		String str;
		Concesionario c = new Concesionario();
		em.getTransaction().begin();
		System.out.println("Modificaci�n de concesionario");
		System.out.println("Introduce la id del concesionario a modificar");
		str = Utils.getStringConsola();
		int id = Integer.parseInt(str);

		TypedQuery<Concesionario> q = em.createQuery("select c from Concesionario as c where c.id=" + id,
				Concesionario.class);
		List<Concesionario> concesionarios = q.getResultList();
		for (Concesionario concesionario : concesionarios) {

			concesionario.setId(id);
			System.out.println("Introduzca el nombre del concesionario");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				concesionario.setNombre(str);
			}
			System.out.println("Introduzca los cif del concesionario");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				concesionario.setCif(str);

			}
			System.out.println("Introduzca la localidad del concesionario");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				concesionario.setLocalidad(str);

			}
			em.merge(concesionario);
		}
		em.getTransaction().commit();
		em.close();
		System.out.println("Insertado correctamente, pulse intro para continuar");
		Utils.pausa();

	}

	/**
	 * 
	 */
	private static void listado() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("select * from concesionario", Concesionario.class);
		List<Concesionario> c = (List<Concesionario>) q.getResultList();
		for (Concesionario concesionario : c) {
			System.out.println("concesionario: " + concesionario.getId() + " nombre: " + concesionario.getNombre()
					+ " cif: " + concesionario.getCif() + " localidad: " + concesionario.getLocalidad());

		}
		System.out.println("Pulse intro para continuar");
		Utils.pausa();
		em.close();
	}

	/**
	 * 
	 * @throws ErrorBBDDException
	 * @throws ParseException
	 */
	private static void alta() throws ErrorBBDDException, ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		String str;
		Concesionario concesionario = new Concesionario();
		em.getTransaction().begin();
		System.out.println("Alta de concesionario");
		int id = ultimoId();
		concesionario.setId(id);
		System.out.println("Introduzca el nombre del concesionario");
		concesionario.setNombre(Utils.getStringConsola());
		System.out.println("Introduzca el cif del concesionario");
		concesionario.setCif(Utils.getStringConsola());
		System.out.println("Introduzca la localidad del concesionario");
		concesionario.setLocalidad(Utils.getStringConsola());

		em.persist(concesionario);
		em.getTransaction().commit();
		em.close();
		System.out.println("Insertado correctamente, pulse intro para continuar");
		Utils.pausa();
	}

	/**
	 * 
	 * @return
	 */
	private static int ultimoId() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("select * from concesionario", Concesionario.class);
		List<Concesionario> c = (List<Concesionario>) q.getResultList();
		em.close();
		return c.size() + 1;

	}

	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	private static void eliminacion() throws ErrorBBDDException {
		System.out.println("Eliminaci�n de concesionario");
		System.out.println("Introduzca la id del concesionario a eliminar");
		int id = Integer.parseInt(Utils.getStringConsola());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Concesionario> q = em.createQuery("select c from Concesionario as c where c.id=" + id,
				Concesionario.class);
		List<Concesionario> concesionarios = q.getResultList();
		em.getTransaction().begin();
		for (Concesionario concesionario : concesionarios) {
			em.remove(concesionario);
		}
		em.getTransaction().commit();
		em.close();
		System.out.println("Eliminado correctamente, pulse intro para continuar");
		Utils.pausa();
	}
}
