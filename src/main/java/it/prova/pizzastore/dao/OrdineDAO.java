package it.prova.pizzastore.dao;

import java.util.List;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;

public interface OrdineDAO extends IBaseDAO<Ordine>{
	
	//eager cliente e utente
	public Ordine findOneEagerClienteEUtente(Long id) throws Exception;
	//eager cliente utente e pizze
	public Ordine findOneEagerAll(Long id) throws Exception;

	public Integer sumPizzeOrdine(Ordine ordineInstance) throws Exception;
	
	public List<Ordine> findAllAttiviOfFattorino(Utente utente) throws Exception;
	
}
