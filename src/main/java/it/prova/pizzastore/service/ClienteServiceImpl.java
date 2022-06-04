package it.prova.pizzastore.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.ClienteDAO;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class ClienteServiceImpl implements ClienteService {

	private ClienteDAO clienteDAO;

	@Override
	public List<Cliente> listAllElements() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return clienteDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Cliente caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return clienteDAO.findOne(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Cliente caricaSingoloElementoConOrdini(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return clienteDAO.findOneEager(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Cliente clienteInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			// grazie al fatto che il regista ha un id viene eseguito il merge
			// automaticamente
			// se quell'id non ha un corrispettivo in tabella viene lanciata una eccezione
			clienteDAO.update(clienteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Cliente clienteInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			// grazie al fatto che il regista ha un id viene eseguito il merge
			// automaticamente
			// se quell'id non ha un corrispettivo in tabella viene lanciata una eccezione
			clienteDAO.update(clienteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Long idCliente) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;

	}

	@Override
	public List<Cliente> listAllAttivi() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return clienteDAO.findAllAttivi();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Cliente> cerca(Cliente example) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			clienteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return clienteDAO.findAllByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
