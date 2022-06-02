package it.prova.pizzastore.dao;

import it.prova.pizzastore.model.Cliente;

public interface ClienteDAO extends IBaseDAO<Cliente>{

	
	public Cliente findOneEager(Long id) throws Exception;
}
