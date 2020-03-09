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

import modelo.Cliente;
import modelo.Coche;
import modelo.Concesionario;
import modelo.Cliente;
import modelo.Venta;

public class GestionVenta {

	/**
	 * @throws ParseException
	 * 
	 */
	public static void menuGestion() throws ParseException {

		int opcionElegida = -1;
		do {
			try {
				System.out.println("\n\t\t\tGESTI�N DE VENTAS");

				System.out.println("\n\t1.- Listado de ventas.");
				System.out.println("\t2.- Alta de venta.");
				System.out.println("\t3.- Modificaci�n de venta.");
				System.out.println("\t4.- Baja de venta.");
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
		Venta c = new Venta();
		em.getTransaction().begin();
		System.out.println("Modificaci�n de venta");
		System.out.println("Introduce la id de la venta a modificar");
		str = Utils.getStringConsola();
		int id = Integer.parseInt(str);

		TypedQuery<Venta> q = em.createQuery("select c from Venta as c where c.id=" + id, Venta.class);
		List<Venta> ventas = q.getResultList();
		for (Venta venta : ventas) {

			venta.setId(id);
			System.out.println("Introduzca el precio de venta");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				venta.setPrecioVenta(str);
			}
			System.out.println("Introduzca el DNI del cliente de la venta");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				venta.setCliente(buscarCliente(str));
			}
			System.out.println("Introduzca la matricula del coche de la venta");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				venta.setCoche(buscarCoche(str));
			}
			System.out.println("Introduzca el cif del concesionario de la venta");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				venta.setConcesionario(buscarConcesionario(str));
			}
			System.out.println("Introduzca la fecha de la venta");
			str = Utils.getStringConsola();
			if (!str.equals(" ")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				venta.setFecha(sdf.parse(str));

			}
			em.merge(venta);
		}

		em.getTransaction().commit();
		em.close();
		System.out.println("Insertado correctamente, pulse intro para continuar");
		Utils.pausa();

	}

	/**
	 * 
	 * @param dni
	 * @return
	 */
	private static Cliente buscarCliente(String dni) {
		Cliente c = new Cliente();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("select * from cliente as c where c.DniNie ='" + dni +"'", Cliente.class);
		List<Cliente> clientes = (List<Cliente>) q.getResultList();
		for (Cliente cliente : clientes) {
			c = cliente;
		}
		em.close();
		return c;

	}

	/**
	 * 
	 * @param bastidor
	 * @return
	 */
	private static Coche buscarCoche(String bastidor) {
		Coche c = new Coche();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("select * from coche as c where c.bastidor ='" + bastidor +"'", Coche.class);
		List<Coche> coches = (List<Coche>) q.getResultList();
		for (Coche coche : coches) {
			c = coche;
		}
		em.close();
		return c;

	}

	/**
	 * 
	 * @param cif
	 * @return
	 */
	private static Concesionario buscarConcesionario(String cif) {
		Concesionario c = new Concesionario();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("select * from concesionario as c where c.cif ='" + cif + "'", Concesionario.class);
		List<Concesionario> concesionarios = (List<Concesionario>) q.getResultList();
		for (Concesionario concesionario : concesionarios) {
			c = concesionario;
		}
		em.close();
		return c;

	}

	/**
	 * 
	 */
	private static void listado() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("select * from venta", Venta.class);
		List<Venta> v = (List<Venta>) q.getResultList();
		for (Venta venta : v) {
			System.out.println("venta: " + venta.getId() + " fecha: " + venta.getFecha() + " precioVenta: "
					+ venta.getPrecioVenta() + " Dni: " + venta.getCliente().getDniNie() + " matricula: "
					+ venta.getCoche().getBastidor() + " cif: " + venta.getConcesionario().getCif());

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
		Venta v = new Venta();
		em.getTransaction().begin();
		System.out.println("Alta de venta");
		int id = ultimoId();
		v.setId(id);
		System.out.println("Introduzca el precio de la venta");
		v.setPrecioVenta(Utils.getStringConsola());
		System.out.println("Introduzca el DNI del cliente de la venta");
		v.setCliente(buscarCliente(Utils.getStringConsola()));
		System.out.println("Introduzca el bastidor del coche de la venta");
		v.setCoche(buscarCoche(Utils.getStringConsola()));
		System.out.println("Introduzca cif del concesionario de la venta");
		v.setConcesionario(buscarConcesionario(Utils.getStringConsola()));
		System.out.println("Introduzca la fecha de la venta");
		str = Utils.getStringConsola();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		v.setFecha(sdf.parse(str));

		em.persist(v);
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
		Query q = em.createNativeQuery("select * from venta", Venta.class);
		List<Venta> c = (List<Venta>) q.getResultList();
		em.close();
		return c.size() + 1;

	}

	/**
	 * 
	 * @throws ErrorBBDDException
	 */
	private static void eliminacion() throws ErrorBBDDException {
		System.out.println("Eliminaci�n de venta");
		System.out.println("Introduzca la id de la venta a eliminar");
		int id = Integer.parseInt(Utils.getStringConsola());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPANEW");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Venta> q = em.createQuery("select c from Venta as c where c.id=" + id, Venta.class);
		List<Venta> ventas = q.getResultList();
		em.getTransaction().begin();
		for (Venta venta : ventas) {
			em.remove(venta);
		}
		em.getTransaction().commit();
		em.close();
		System.out.println("Eliminado correctamente, pulse intro para continuar");
		Utils.pausa();
	}
}
