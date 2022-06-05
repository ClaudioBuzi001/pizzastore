package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import it.prova.pizzastore.model.Pizza;

public interface PizzaDAO extends IBaseDAO<Pizza>{
	
	public Pizza findOneEager(Long id) throws Exception;
	
	public void deleteLogico(Pizza pizzaInstance) throws Exception;
	
	public List<Pizza> findByExample(Pizza example) throws Exception;

}
