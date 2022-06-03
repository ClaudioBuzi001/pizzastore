package it.prova.pizzastore.dao;

import java.util.Optional;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{
	
	public Optional<Utente> findByUsernameAndPassword(String username, String password) throws Exception;
	
	public Optional<Utente> login(String username, String password) throws Exception;

	public Utente findOneEager(long id) throws Exception;
	
	public Utente checkSeCeAlmenoUn(Ruolo ruoloInstance) throws Exception;
	
	
}
