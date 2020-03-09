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

import modelo.Fabricante;

public class GestionFabricante {

	/**
	 * @throws ParseException
	 * 
	 */
	public static void menuGestion() throws ParseException {

		int opcionElegida = -1;
		do {
			try {
				System.out.println("\n\t\t\tGESTI�N DE FABRICANTES");

				System.out.println("\n\t1.- Listado de fabricantes.");
				System.out.println("\t2.- Alta de fabricantes.");
				System.out.println("\t3.- Modificaci�n de fabricantes.");
				System.out.println("\t4.- Baja de fabricantes.");
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
		Fabricante f = new Fabricante();
		em.getTransaction().begin();
		System.out.println("Modificaci�n de fabricante");
		System.out.println("Introduce la id del fabricante a modificar");
		str = Utils.getStringConsola();
		int id = Integer.parseInt(str);

		TypedQuery<Fabricante> q = em.createQuery("select c from Fabricante as c where c.id=" + id, Fabricante.class);
		List<Fabricante> fabricantes = q.getResultList();
		for (Fabricante fabricante : fabricantes) {

			fabricante.setId(id);
			System.out.println("Introduzca el nombre del fabricante");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				fabricante.setNombre(str);
			}
			System.out.println("Introduzca los cif del fabricante");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				fabricante.setCif(str);

			}
			em.merge(fabricante);
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
		Query q = em.createNativeQuery("select * from fabricante", Fabricante.class);
		List<Fabricante> f = (List<Fabricante>) q.getResultList();
		for (Fabricante fabricante : f) {
			System.out.println("fabricante: " + fabricante.getId() + " nombre: " + fabricante.getNombre() + " cif: "
					+ fabricante.getCif());

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
		Fabricante fabricante = new Fabricante();
		em.getTransaction().begin();
		System.out.println("Alta de fabricante");
		int id = ultimoId();
		fabricante.setId(id);
		System.out.println("Introduzca el nombre del fabricante");
		fabricante.setNombre(Utils.getStringConsola());
		System.out.println("Introduzca el cif del fabricante");
		fabricante.setCif(Utils.getStringConsola());

		em.persist(fabricante);
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
		Query q = em.createNativeQuery("select * from fabricante", Fabricante.class);
		List<Fabricante> c = (List<Fabricante>) q.getResultList();
		em.close();
		return c.size() + 1;

	}

	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	private static void eliminacion() throws ErrorBBDDException {
		System.out.println("Eliminaci�n de fabricante");
		System.out.println("Introduzca la id del fabricante a eliminar");
		int id = Integer.parseInt(Utils.getStringConsola());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Fabricante> q = em.createQuery("select c from Fabricante as c where c.id=" + id, Fabricante.class);
		List<Fabricante> fabricantes = q.getResultList();
		em.getTransaction().begin();
		for (Fabricante fabricante : fabricantes) {
			em.remove(fabricante);
		}
		em.getTransaction().commit();
		em.close();
		System.out.println("Eliminado correctamente, pulse intro para continuar");
		Utils.pausa();
	}
}
