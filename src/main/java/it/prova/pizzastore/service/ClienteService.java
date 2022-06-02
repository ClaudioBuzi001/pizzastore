package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.ClienteDAO;
import it.prova.pizzastore.model.Cliente;



public interface ClienteService {
	public List<Cliente> listAllElements() throws Exception;

	public Cliente caricaSingoloElemento(Long id) throws Exception;
	
	public Cliente caricaSingoloElementoConOrdini(Long id) throws Exception;

	public void aggiorna(Cliente clienteInstance) throws Exception;

	public void inserisciNuovo(Cliente clienteInstance) throws Exception;

	public void rimuovi(Long idCliente) throws Exception;
	
	public void setClienteDAO(ClienteDAO clienteDAO);

}
