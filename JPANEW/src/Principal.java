import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.Coche;
import modelo.Fabricante;
import modelo.Venta;

public class Principal {


	/**
	 * 
	 */
	private static void obtencionUnaSolaEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");

		EntityManager em = entityManagerFactory.createEntityManager();

		Coche coche = (Coche) em.find(Coche.class, 1);
		
		System.out.println("Coche localizado -> " + coche.getId() + " " + coche.getModelo() + " " +
				coche.getColor());
		
		em.close();
	}
	
	
	
	/**
	 * 
	 */
	private static void obtencionUnaSolaEntidadSegundoMetodo () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");

		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM coche where id = ?", Coche.class);
		q.setParameter(1, 100);
		Coche coche = (Coche) q.getSingleResult();
		
		if (coche != null) {
			System.out.println("Coche localizado -> " + coche.getId() + " " + coche.getModelo() + " " +
				coche.getColor());
		}
		
		em.close();
	}
	
	
	
	/**
	 * 
	 */
	private static void obtencionUnaSolaEntidadTercerMetodo () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Coche> q = em.createQuery("SELECT c FROM Coche c where c.id = :id", Coche.class);
		q.setParameter("id", 100);
		Coche coche = (Coche) q.getSingleResult();
		
		if (coche != null) {
			System.out.println("Coche localizado -> " + coche.getId() + " " + coche.getModelo() + " " +
				coche.getColor());
		}
		
		em.close();
	}
	
	
	
	/**
	 * 
	 */
	private static void listadoEntidades () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM coche;", Coche.class);
		
		List<Coche> coches = (List<Coche>) q.getResultList();
		
		for (Coche coche : coches) {
			System.out.println("Coche: " + coche.getId() + " modelo: " + coche.getModelo());
		}
		
		em.close();
	}
	
	
	/**
	 * 
	 */
	private static void listadoEntidadesSegundoMetodo () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		TypedQuery<Coche> q = em.createQuery("SELECT c FROM Coche c", Coche.class);
		
		List<Coche> coches = (List<Coche>) q.getResultList();
		
		for (Coche coche : coches) {
			System.out.println("Coche: " + coche.getId() + " modelo: " + coche.getModelo());
		}
		
		em.close();
	}
	
	
	/**
	 * 
	 */
	private static void listadoEntidadesTercerMetodo () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNamedQuery("Coche.findAll");//("SELECT c FROM Coche c", Coche.class);
		
		List<Coche> coches = (List<Coche>) q.getResultList();
		
		for (Coche coche : coches) {
			System.out.println("Coche: " + coche.getId() + " modelo: " + coche.getModelo());
		}
		
		em.close();
	}
	
	
	/**
	 * 
	 */
	private static void obtencionEntidadesRelacionadas () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");

		EntityManager em = entityManagerFactory.createEntityManager();

		Coche coche = (Coche) em.find(Coche.class, 1);
		
		System.out.println("Fabricante -> " + coche.getFabricante().getId() + " " + coche.getFabricante().getNombre());
		
		for (Venta venta : coche.getVentas()) {
			System.out.println("Venta -> " + venta.getId() + " " + venta.getPrecioVenta() + " " + 
					venta.getFecha());
		}
		
		em.close();
		
	}
	
	
	
	/**
	 * 
	 */
	private static void creacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");

		EntityManager em = entityManagerFactory.createEntityManager();

		Fabricante fab = new Fabricante();
		fab.setCif("12345678A");
		fab.setNombre("Coches Rafa");
		
		em.getTransaction().begin();
		em.persist(fab);
		em.getTransaction().commit();
		
		TypedQuery<Fabricante> q = em.createQuery("SELECT f FROM Fabricante as f", Fabricante.class);
		
		List<Fabricante> fabricantes = q.getResultList();
		
		for (Fabricante fabEnLista : fabricantes) {
			System.out.println("Fabricante: " + fabEnLista.getId() + " CIF: " + fabEnLista.getCif() + " Nombre: " + fabEnLista.getNombre());
		}
		
		em.close();
	}
	
	
	
	/**
	 * 
	 */
	private static void modificacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Fabricante> q = em.createQuery("SELECT f FROM Fabricante as f where f.cif = '12345678A'", Fabricante.class);
		
		List<Fabricante> fabricantes = q.getResultList();
		
		em.getTransaction().begin();
		for (Fabricante fabEnLista : fabricantes) {
			fabEnLista.setNombre("Modificado");
			em.persist(fabEnLista);
		}
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	
	/**
	 * 
	 */
	private static void eliminacionEntidad () {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");

		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Fabricante> q = em.createQuery("SELECT f FROM Fabricante as f where f.cif = '12345678A'", Fabricante.class);
		
		List<Fabricante> fabricantes = q.getResultList();
		
		em.getTransaction().begin();
		for (Fabricante fabEnLista : fabricantes) {
			em.remove(fabEnLista);
		}
		em.getTransaction().commit();
		
		
		em.close();
	}
	
	
	
	

	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//obtencionUnaSolaEntidad();
		obtencionUnaSolaEntidadSegundoMetodo();
		//obtencionUnaSolaEntidadTercerMetodo();
		//listadoEntidades();
		//listadoEntidadesSegundoMetodo();
		//listadoEntidadesTercerMetodo();
		//obtencionEntidadesRelacionadas();
		//creacionEntidad();
		//modificacionEntidad();
		//eliminacionEntidad();
	}

}
