package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the venta database table.
 * 
 */
@Entity
@Table(name="venta")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String precioVenta;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Coche
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCoche")
	private Coche coche;

	//bi-directional many-to-one association to Concesionario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idConcesionario")
	private Concesionario concesionario;

	public Venta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(String str) {
		this.precioVenta = str;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Coche getCoche() {
		return this.coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public Concesionario getConcesionario() {
		return this.concesionario;
	}

	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}

}