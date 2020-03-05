package controladores;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;   

public class GestionCliente {

	/**
	 * @throws ParseException 
	 * 
	 */
	public static void menuGestion() throws ParseException {

		int opcionElegida = -1;
		do {
			try {
				System.out.println("\n\t\t\tGESTIÓN DE CLIENTES");
				
				System.out.println("\n\t1.- Listado de clientes.");
				System.out.println("\t2.- Añadir de cliente.");
				System.out.println("\t3.- Modificación de cliente.");
				System.out.println("\t4.- Eliminar de cliente.");
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
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM cliente;", Cliente.class);
		
		List<Cliente> clientes = (List<Cliente>) q.getResultList();
		
		for (Cliente cliente : clientes) {
			System.out.println("Cliente: " + cliente.getId() + " nombre: " + cliente.getNombre()
			+ " apellidos: " + cliente.getApellidos() + " localidad: " + cliente.getLocalidad()
			+ " dniNie: " + cliente.getDniNie()+ " fechaNac: " + cliente.getFechaNac()+ " activo: " + cliente.getActivo());
		}
		System.out.println("\n\tPulse 'Intro' para continuar");
		Utils.pausa();
		em.close();
	}
	
	private static int getLastId() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM cliente;", Cliente.class);
		
		List<Cliente> clientes = (List<Cliente>) q.getResultList();
		
		em.close();
		
		return clientes.size()+1;
	}
	
	
	/**
	 * 
	 * @throws ErrorBBDDException
	 * @throws ParseException 
	 */
	private static void alta () throws ErrorBBDDException, ParseException {
		String str;
		Cliente c = new Cliente();
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");

		EntityManager em = entityManagerFactory.createEntityManager();
		
		em.getTransaction().begin();
		System.out.println("\n\tAlta de cliente\n");
		int id = getLastId();
		c.setId(id);
		System.out.print("\nIntroduzca 'Nombre' del curso: ");
		c.setNombre(Utils.getStringConsola());
		System.out.print("\nIntroduzca 'Apellidos' del curso: ");
		c.setApellidos(Utils.getStringConsola());
		System.out.print("\nIntroduzca 'Localidad' del curso: ");
		c.setLocalidad(Utils.getStringConsola());
		System.out.print("\nIntroduzca 'DNI' del curso: ");
		c.setDniNie(Utils.getStringConsola());
		System.out.print("\nIntroduzca 'Fecha Nacimiento' del curso: ");
		str = Utils.getStringConsola();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		c.setFechaNac(sdf.parse(str));
  
		
		
		em.persist(c);
		em.getTransaction().commit();
		
		TypedQuery<Cliente> q = em.createQuery("SELECT c FROM Cliente as c where c.id = " + id, Cliente.class);
		
		List<Cliente> clientes = q.getResultList();
		
		for (Cliente cliente : clientes) {
			System.out.println("Cliente: " + cliente.getId() + " nombre: " + cliente.getNombre()
			+ " apellidos: " + cliente.getApellidos() + " localidad: " + cliente.getLocalidad()
			+ " dniNie: " + cliente.getDniNie()+ " fechaNac: " + cliente.getFechaNac()+ " activo: " + cliente.getActivo());
		}
		
		em.close();
		
		System.out.println("\n\tInsertado correctamente!. Pulse 'Intro' para continuar");
		Utils.pausa();
	}



	/**
	 * 
	 * @throws ErrorBBDDException
	 * @throws ParseException 
	 */
	private static void modificacion () throws ErrorBBDDException, ParseException {
			System.out.println("\n\tModificación de cliente\n");
			
			int id;
			String str;

			System.out.print("\nIntroduzca 'Id' del cliente a modificar");
			str = Utils.getStringConsola();
			id = Integer.parseInt(str);
			
			
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");

			EntityManager em = entityManagerFactory.createEntityManager();

			TypedQuery<Cliente> q = em.createQuery("SELECT c FROM cliente as c where c.id = " + id, Cliente.class);
			
			List<Cliente> clientes = q.getResultList();
			
			em.getTransaction().begin();
			for (Cliente c : clientes) {
	
				System.out.print("\nIntroduzca 'Id' del cliente a modificar ('Intro' para no modificar): ");
				str = Utils.getStringConsola();
				if (!str.equals("")){
				id = Integer.parseInt(str);
				}
				System.out.print("\nIntroduzca 'Nombre' del cliente a modificar ('Intro' para no modificar): ");
				str = Utils.getStringConsola();
				if (!str.equals("")){
				c.setNombre(str);
				}
				System.out.print("\nIntroduzca 'Apellidos' del cliente a modificar ('Intro' para no modificar): ");
				str = Utils.getStringConsola();
				if (!str.equals("")){
					c.setApellidos(str);
				}
				System.out.print("\nIntroduzca 'Localidad' del cliente a modificar ('Intro' para no modificar): ");
				str = Utils.getStringConsola();
				if (!str.equals("")){
					c.setLocalidad(str);
				}
				System.out.print("\nIntroduzca 'DNI' del cliente a modificar ('Intro' para no modificar): ");
				str = Utils.getStringConsola();
				if (!str.equals("")){
					c.setDniNie(str);
				}
				System.out.print("\nIntroduzca 'Fecha Nacimiento' del cliente a modificar ('Intro' para no modificar): ");
				str = Utils.getStringConsola();
				if (!str.equals("")){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					c.setFechaNac(sdf.parse(str));
				}
				em.persist(c);
			}
			em.getTransaction().commit();
			em.close();
			System.out.println("\n\tModificado correctamente!. Pulse 'Intro' para continuar");
			Utils.pausa();
		}
	

	
	
	/**
	 * 
	 * @throws ErrorBBDDExceptionfabEnLista
	 */
	private static void baja () throws ErrorBBDDException {
		System.out.println("\n\tEliminación de curso\n");
		System.out.print("\n Realmente desea eliminar el registro? (S/N): ");
			String str = Utils.getStringConsola();
			if (str.equalsIgnoreCase("S")) { 	
				int id;
				System.out.print("\nIntroduzca 'Id' del curso a eliminar");
				id = Integer.parseInt(Utils.getStringConsola());
				
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPANEW");

				EntityManager em = entityManagerFactory.createEntityManager();

				TypedQuery<Cliente> q = em.createQuery("SELECT c FROM Cliente as c where c.id = " + id, Cliente.class);
				
				List<Cliente> clientes = q.getResultList();
				
				em.getTransaction().begin();
				for (Cliente cliente : clientes) {
					em.remove(clientes);
				}
				em.getTransaction().commit();
				em.close();
				System.out.println("\n\tEliminado correctamente!. Pulse 'Intro' para continuar");
				Utils.pausa();
			}
		}
		
	}



