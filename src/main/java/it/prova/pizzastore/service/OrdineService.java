package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.OrdineDAO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;



public interface OrdineService {
	public List<Ordine> listAllElements() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;
	
	public Ordine caricaSingoloElementoConUtenteECliente(Long id) throws Exception;
	
	public Ordine caricaSingoloElementoConTutto(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Long idOrdine) throws Exception;
	
	public Integer calcolaPrezzoOrdine(Ordine ordineInstance) throws Exception;
	
	public void aggiungiPizza(Ordine ordineInstance, Pizza pizza) throws Exception;
	
	public void setOrdineDAO(OrdineDAO ordineDAO);
}
