package it.prova.pizzastore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	// Proprieta istanza
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "indirizzo")
	private String indirizzo;
	@Column(name = "attivo")
	private boolean attivo = true;

	// Un cliente ha tanti ordini, Cliente(one) Ordine(many) ORDINE OWNER DELLA
	// RELAZINe
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private Set<Ordine> ordini = new HashSet<Ordine>();

	public Cliente(String nome, String cognome, String indirizzo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
	}

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cognome, String indirizzo, Set<Ordine> ordini) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.ordini = ordini;
	}

	public Cliente(long id, String nome, String cognome, String indirizzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
	}

	public Cliente(long id, String nome, String cognome, String indirizzo, Set<Ordine> ordini) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.ordini = ordini;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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
