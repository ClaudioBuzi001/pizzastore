package it.prova.pizzastore.dao;

import it.prova.pizzastore.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine>{
	
	//eager cliente e utente
	public Ordine findOneEagerClienteEUtente(Long id) throws Exception;
	//eager cliente utente e pizze
	public Ordine findOneEagerAll(Long id) throws Exception;

}
