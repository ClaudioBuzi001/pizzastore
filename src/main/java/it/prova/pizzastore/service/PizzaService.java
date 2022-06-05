package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.PizzaDAO;
import it.prova.pizzastore.model.Pizza;



public interface PizzaService {

	public List<Pizza> listAllElements() throws Exception;

	public Pizza caricaSingoloElemento(Long id) throws Exception;
	
	public Pizza caricaSingoloElementoConOrdini(Long id) throws Exception;

	public void aggiorna(Pizza pizzaInstance) throws Exception;

	public void inserisciNuovo(Pizza pizzaInstance) throws Exception;

	public void rimuovi(Long idPizza) throws Exception;
	
	public void setPizzaDAO(PizzaDAO pizzaDAO);
	
	public void rimuoviLogico(Long idPizza) throws Exception;
	
	public List<Pizza> cerca(Pizza example) throws Exception;
}
