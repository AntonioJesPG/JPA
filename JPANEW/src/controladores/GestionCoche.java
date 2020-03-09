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

import modelo.Coche;
import modelo.Fabricante;
import modelo.Coche;

public class GestionCoche {

	/**
	 * @throws ParseException
	 * 
	 */
	public static void menuGestion() throws ParseException {

		int opcionElegida = -1;
		do {
			try {
				System.out.println("\n\t\t\tGESTI�N DE COCHES");

				System.out.println("\n\t1.- Listado de coches.");
				System.out.println("\t2.- Alta de coches.");
				System.out.println("\t3.- Modificaci�n de coches.");
				System.out.println("\t4.- Baja de coches.");
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
		Coche c = new Coche();
		em.getTransaction().begin();
		System.out.println("Modificaci�n de coche");
		System.out.println("Introduce la id del coche a modificar");
		str = Utils.getStringConsola();
		int id = Integer.parseInt(str);

		TypedQuery<Coche> q = em.createQuery("select c from Coche as c where c.id=" + id, Coche.class);
		List<Coche> coches = q.getResultList();
		for (Coche coche : coches) {

			c.setId(id);
			System.out.println("Introduzca el modelo del coche");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				coche.setModelo(str);
			}
			System.out.println("Introduzca el bastidor del coche");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				coche.setBastidor(str);
			}
			System.out.println("Introduzca el color del coche");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				coche.setColor(str);
			}
			System.out.println("Introduzca el cif del fabricante del coche");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				coche.setFabricante(buscarFabricante(str));

			}
			em.merge(coche);
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
		Query q = em.createNativeQuery("select * from coche", Coche.class);
		List<Coche> c = (List<Coche>) q.getResultList();
		for (Coche coche : c) {
			System.out.println("coche: " + coche.getId() + " fabricante:" + coche.getFabricante().getCif() + " modelo: "
					+ coche.getModelo() + " bastidor: " + coche.getBastidor() + " color: " + coche.getColor());

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
		Coche c = new Coche();
		em.getTransaction().begin();
		System.out.println("Alta de coche");
		int id = ultimoId();
		c.setId(id);
		System.out.println("Introduzca el modelo del coche");
		c.setModelo(Utils.getStringConsola());
		System.out.println("Introduzca el bastidor del coche");
		c.setBastidor(Utils.getStringConsola());
		System.out.println("Introduzca el color del coche");
		c.setColor(Utils.getStringConsola());
		System.out.println("Introduzca el cif del fabricante del coche");
		c.setFabricante(buscarFabricante(Utils.getStringConsola()));

		em.persist(c);
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
		Query q = em.createNativeQuery("select * from coche", Coche.class);
		List<Coche> c = (List<Coche>) q.getResultList();
		em.close();
		return c.size() + 1;

	}

	/**
	 * 
	 * @param cif
	 * @return
	 */
	private static Fabricante buscarFabricante(String cif) {
		Fabricante f = new Fabricante();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("select * from Fabricante as c where c.cif =" + cif, Fabricante.class);
		List<Fabricante> c = (List<Fabricante>) q.getResultList();
		for (Fabricante fabricante : c) {
			f = fabricante;
		}
		em.close();
		return f;

	}

	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	private static void eliminacion() throws ErrorBBDDException {
		System.out.println("Eliminaci�n de coche");
		System.out.println("Introduzca la id del coche a eliminar");
		int id = Integer.parseInt(Utils.getStringConsola());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Coche> q = em.createQuery("select c from Coche as c where c.id=" + id, Coche.class);
		List<Coche> coches = q.getResultList();
		em.getTransaction().begin();
		for (Coche coche : coches) {
			em.remove(coche);
		}
		em.getTransaction().commit();
		em.close();
		System.out.println("Eliminado correctamente, pulse intro para continuar");
		Utils.pausa();
	}
}
