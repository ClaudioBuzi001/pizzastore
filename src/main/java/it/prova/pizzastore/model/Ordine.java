package it.prova.pizzastore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordine")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "data")
	private Date data;
	@Column(name = "closed")
	private boolean closed = false;
	@Column(name = "codice")
	private String codice;
	@Column(name = "costototaleordine")
	private Double costoTotaleOrdine;

	// Ha un cliente(one)
	// OWNER DELLA RELAZIONE
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	// ManyToMany Con Pizza ((OWNER DEL LEGAME))
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "ordine_pizza", joinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"))
	private List<Pizza> pizze = new ArrayList<Pizza>();

	// Ha un utente(one)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utente;

	public Ordine() {
		super();
	}

	public Ordine(Date data, String codice, Cliente cliente, Utente utente) {
		super();
		this.data = data;
		this.codice = codice;
		this.cliente = cliente;
		this.utente = utente;
	}

	public Ordine(Date data, String codice, Cliente cliente, List<Pizza> pizze, Utente utente) {
		super();
		this.data = data;
		this.codice = codice;
		this.cliente = cliente;
		this.pizze = pizze;
		this.utente = utente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Double getCostoTotaleOrdine() {
		return costoTotaleOrdine;
	}

	public void setCostoTotaleOrdine(Double costoTotaleOrdine) {
		this.costoTotaleOrdine = costoTotaleOrdine;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}
