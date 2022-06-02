package it.prova.pizzastore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pizza")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "ingredienti")
	private String ingredienti;
	@Column(name = "prezzobase")
	private Integer prezzoBase;
	@Column(name = "attivo")
	private boolean attivo = true;
	// anche se ordine è l owner della relazione
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "pizze")
	private Set<Ordine> ordini = new HashSet<Ordine>(0);

	public Pizza() {
		super();
	}

	public Pizza(String descrizione, String ingredienti, Integer prezzoBase, Set<Ordine> ordini) {
		super();
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
		this.prezzoBase = prezzoBase;
		this.ordini = ordini;
	}

	public Pizza(String descrizione, String ingredienti, Integer prezzoBase) {
		super();
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
		this.prezzoBase = prezzoBase;
	}

	public Pizza(long id, String descrizione, String ingredienti, Integer prezzoBase, Set<Ordine> ordini) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
		this.prezzoBase = prezzoBase;
		this.ordini = ordini;
	}

	public Pizza(long id, String descrizione, String ingredienti, Integer prezzoBase) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
		this.prezzoBase = prezzoBase;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(String ingredienti) {
		this.ingredienti = ingredienti;
	}

	public Integer getPrezzoBase() {
		return prezzoBase;
	}

	public void setPrezzoBase(Integer prezzoBase) {
		this.prezzoBase = prezzoBase;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public Set<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(Set<Ordine> ordini) {
		this.ordini = ordini;
	}

}
