package it.prova.pizzastore.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.PizzaDAO;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class PizzaServiceImpl implements PizzaService {

	private PizzaDAO pizzaDAO;

	@Override
	public List<Pizza> listAllElements() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return pizzaDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Pizza caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return pizzaDAO.findOne(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Pizza caricaSingoloElementoConOrdini(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return pizzaDAO.findOneEager(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Pizza pizzaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			// grazie al fatto che il regista ha un id viene eseguito il merge
			// automaticamente
			// se quell'id non ha un corrispettivo in tabella viene lanciata una eccezione
			pizzaDAO.update(pizzaInstance);

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
	public void inserisciNuovo(Pizza pizzaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			// grazie al fatto che il regista ha un id viene eseguito il merge
			// automaticamente
			// se quell'id non ha un corrispettivo in tabella viene lanciata una eccezione
			pizzaDAO.insert(pizzaInstance);

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
	public void rimuovi(Long idPizza) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPizzaDAO(PizzaDAO pizzaDAO) {
		this.pizzaDAO = pizzaDAO;
	}

	@Override
	public void rimuoviLogico(Long idPizza) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			pizzaDAO.deleteLogico(pizzaDAO.findOne(idPizza).orElse(null));

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
	public List<Pizza> cerca(Pizza example) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			pizzaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return pizzaDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
