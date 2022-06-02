package it.prova.pizzastore.dao;

import it.prova.pizzastore.model.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo>{
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception;

}
