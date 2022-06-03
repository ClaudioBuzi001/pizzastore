package it.prova.pizzastore.dao;

import java.util.List;

import it.prova.pizzastore.model.Cliente;

public interface ClienteDAO extends IBaseDAO<Cliente>{

	
	public Cliente findOneEager(Long id) throws Exception;
	
	public List<Cliente> findAllAttivi() throws Exception;
}
