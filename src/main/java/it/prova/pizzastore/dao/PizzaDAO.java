package it.prova.pizzastore.dao;

import java.util.Optional;

import it.prova.pizzastore.model.Pizza;

public interface PizzaDAO extends IBaseDAO<Pizza>{
	
	public Pizza findOneEager(Long id) throws Exception;

}